package cu.gob.ith.data.services;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;

import cu.gob.ith.R;
import cu.gob.ith.data.preferences.UserAppPreferences;
import cu.gob.ith.data.repository.ImplRepository;
import cu.gob.ith.data.util.RxBus;
import cu.gob.ith.presentation.util.PreferencesConstants;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.ResponseBody;

@AndroidEntryPoint
public class DownloadService extends IntentService {


    private String channelName = "notificacionDescarga";
    private int notificationId = 0;
    private String url = "";
    private String documentName = "";
    Disposable disposable;

    @Inject
    ImplRepository repositorioImple;
    @Inject
    UserAppPreferences preferencias;

    public DownloadService() {
        super("DownloadService");
    }

    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;
    private int totalFileSize;

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        configDownload();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            String channelIdValue = String.valueOf(preferencias.getPreferenceInt(PreferencesConstants.NOTIFICATION_ID));
            notificationId = preferencias.getPreferenceInt(PreferencesConstants.NOTIFICATION_ID);
            channelName = documentName;
            createNotificationChannel(notificationManager, channelIdValue, channelName);
            notificationBuilder = new NotificationCompat.Builder(this, channelIdValue)
                    .setSmallIcon(R.drawable.ic_all_pedidos)
                    .setContentTitle("Descargando")
                    .setContentText(documentName)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setProgress(0, 0, true)
                    .setAutoCancel(true);
            notificationManager.notify(notificationId, notificationBuilder.build());

        } else {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_all_pedidos)
                    .setContentTitle("Descargando")
                    .setContentText(documentName)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setProgress(0, 0, true)
                    .setAutoCancel(true);
            notificationManager.notify(notificationId, notificationBuilder.build());
        }
        Log.e("Param", "logg: " + preferencias.getPreferenceString("url", "") + "notif");
        initDownload();
    }

    private void configDownload() {
        url = preferencias.getPreferenceString(PreferencesConstants.URL_EXPORT, "");
        documentName = preferencias.getPreferenceString(PreferencesConstants.VERSION_APK_NAME, "");
        notificationId = preferencias.getPreferenceInt(PreferencesConstants.NOTIFICATION_ID);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(NotificationManager notificationManager, String channelId, String channelName) {
        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});
        notificationManager.createNotificationChannel(notificationChannel);
    }

    private void initDownload() {
        repositorioImple.getApkFile(url)
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        downloadFile(responseBody);
                        Log.e("onNext: ", "Tamaño del archivo: " + responseBody.contentLength());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        RxBus.getInstance().postDownloadFinished(0);

                        if (!disposable.isDisposed()) {
                            disposable.dispose();
                        }
                        //En caso necesario notificar conexion caida
                        notificationManager.cancel(notificationId);

                        Log.e("onError: ", "downLoad Service: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete: ", "downLoad Service: ");
                        RxBus.getInstance().postDownloadFinished(100);
                        ;
                    }
                });
    }

    private void downloadFile(ResponseBody responseBody) {

        long fileSize = responseBody.contentLength();
        long startTime = System.currentTimeMillis();
        int timeCount = 1;

        File carpeta = new File(Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS, "MisPedidos");
        Log.e("Carpeta", "onNext: " + Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS);
        if (!carpeta.exists()) {
            Log.e("Carpeta", "If: " + carpeta.exists());
            carpeta.mkdir();
        }
        Log.e("Carpeta", "onNext: " + carpeta.exists());
        if (carpeta.exists()) {
            File excelFile = new File(Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS + File.separator +
                    "MisPedidos" + File.separator + documentName);
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                long fileSizeDownloaded = 0;
                inputStream = responseBody.byteStream();
                outputStream = new FileOutputStream(excelFile);
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1)
                        break;
                    fileSizeDownloaded += read;
                    totalFileSize = (int) (fileSize / (Math.pow(1024, 2)));
                    double currentFileSize = Math.round(fileSizeDownloaded / (Math.pow(1024, 2)));
                    int progress = (int) ((fileSizeDownloaded * 100) / fileSize);
                    long currentTime = System.currentTimeMillis() - startTime;
                    if (currentTime > 1000L * timeCount) {
                        Log.e("Read", "onNext: Download Size " + fileSizeDownloaded);
                        sendNotification(progress, currentFileSize);
                        timeCount++;
                    }
                    outputStream.write(fileReader, 0, read);
                    Log.e("Read", "onNext: Download Size " + fileSizeDownloaded);
                }
                onDownloadComplete();
                outputStream.flush();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void sendNotification(int progress, double currentFileSize) {
        notificationBuilder.setProgress(100, progress, false);
        notificationBuilder.setContentText("Descargando archivo " + currentFileSize + "/" + totalFileSize + " MB");
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("on Destroy: ", "Cancel Notification");
    }

    private void onDownloadComplete() {

        notificationManager.cancel(notificationId);
        Log.e("DownloadComplete: ", "ID download: " + notificationId);
        Log.e("DownloadComplete: ", "Name download: " + channelName);
        notificationBuilder.setProgress(100, 100, false);
        notificationBuilder.setContentText("Descarga Completada");

        if (channelName.length() > 30)
            notificationBuilder.setContentTitle(channelName.substring(6, 30) + "..");
        else notificationBuilder.setContentTitle(channelName);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.e("onTaskRemoved: ", "Cancel Notification");
        notificationManager.cancel(notificationId);
    }

}
