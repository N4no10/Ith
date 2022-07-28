package cu.gob.ith.presentation.activities.main.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.util.Objects;

import javax.inject.Inject;

import cu.gob.ith.BuildConfig;
import cu.gob.ith.R;
import cu.gob.ith.data.preferences.UserAppPreferences;
import cu.gob.ith.data.services.DownloadService;
import cu.gob.ith.data.util.RxBus;
import cu.gob.ith.databinding.ActivityMainBinding;
import cu.gob.ith.domain.model.ApkVersion;
import cu.gob.ith.presentation.activities.main.recyclerview.ClickItemMenuInterface;
import cu.gob.ith.presentation.activities.main.recyclerview.ItemMenuAdapter;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import cu.gob.ith.presentation.model.ItemMenuNavView;
import cu.gob.ith.presentation.util.PreferencesConstants;
import cu.gob.ith.presentation.util.Util;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements ClickItemMenuInterface {

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding uiBind;
    private ItemMenuAdapter itemMenuAdapter;
    private ApkVersion apkVersion = null;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    PackageInfo infoVersion = null;
    ActivityResultLauncher<String[]> activityResultLauncher;
    final private String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    @Inject
    UserAppPreferences userAppPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        uiBind = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(uiBind.getRoot());

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        requestPermissions();
        initView();
        configPermissions();
        downloadUpdateFinished();
        getApkData();
//        verifyApkVersion();

    }

    private void initView() {
        initNavView();
        initTransformMotionLayout();
        observerChangeToolBar();
        initShopCar();
    }

    //region Permissions
    private void requestPermissions() {
        if (!enabledPermissionReadAndWrite())
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, 23);
    }

    private boolean enabledPermissionReadAndWrite() {
        return checkPermissionRead() && checkPermissionWrite();
    }

    private boolean checkPermissionWrite() {
        return ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean checkPermissionRead() {
        return ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }
    //endregion Permissions

    private void initShopCar() {
        uiBind.contentMainActivityLayout.toolbarLayout.shopCarIV.setOnClickListener(v ->
                initNavigateWithoutPopUpTo(R.id.to_pedidoListFragment, "itemMenuNavView.getTitle()"));
        mainActivityViewModel.getCantProductos().observe(this, integer -> {
            if (uiBind.contentMainActivityLayout.toolbarLayout.shopCarIV.getVisibility() == View.VISIBLE) {
                uiBind.contentMainActivityLayout.toolbarLayout.setCantProductos(integer);
            } else {
                uiBind.contentMainActivityLayout.toolbarLayout.productsBadgeTV.setVisibility(View.GONE);
            }
        });
    }

    private void initNavView() {
        uiBind.contentNavViewLayout.userHeaderTV.setText(userAppPreferences.getNamePreferences("not Found"));
        uiBind.contentNavViewLayout.emailHeaderTV.setText(userAppPreferences.getEmailPreferences("not Found"));
        initAdapterNavMenu();
    }

    private void initAdapterNavMenu() {
        itemMenuAdapter = new ItemMenuAdapter(mainActivityViewModel.getItemMenuNavViewList(),
                false);
        itemMenuAdapter.setClickItemMenuInterface(this);
        uiBind.contentNavViewLayout.setAdapter(itemMenuAdapter);
        uiBind.contentNavViewLayout.logoutItemLayout.getRoot().setOnClickListener(v -> logout());
    }

    private boolean goToStartTransitionNavView() {
        Log.e("aaa", "aaaa " + uiBind.motionLayoutDrawerMainActivity.getProgress());
        if (uiBind.motionLayoutDrawerMainActivity.getProgress() > 0.0) {
            uiBind.motionLayoutDrawerMainActivity.transitionToStart();
            return true;
        }
        return false;
    }

    private void initTransformMotionLayout() {
        MotionLayout ml = uiBind.motionLayoutDrawerMainActivity;
        ConstraintSet constraintSetEnd = ml.getConstraintSet(R.id.opcion2);
        ConstraintSet.Transform transformContentMainActiv = constraintSetEnd
                .getConstraint(R.id.contentMainActivityLayout).transform;
        transformContentMainActiv.elevation = 40;

        transformContentMainActiv.translationX = ((int) Util.widthDp(ml.getContext())) -
                (constraintSetEnd.getConstraint(R.id.guideline2).layout.guideEnd);

        uiBind.motionLayoutDrawerMainActivity.addTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
                mainActivityViewModel.setColapsedMainContent(motionLayout.getProgress() != 0.0);
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {

            }
        });


    }

    private void observerChangeToolBar() {
        mainActivityViewModel.getTitleToolBar().observe(this,
                title -> {
                    uiBind.contentMainActivityLayout.setTitle(title);
                    if (title.equals(this.getString(R.string.menu_nuevo_pedido))) {
                        uiBind.contentMainActivityLayout.toolbarLayout.shopCarIV.setVisibility(View.VISIBLE);
                        if (mainActivityViewModel.getProductosParaPedidosList().size() != 0)
                            uiBind.contentMainActivityLayout.toolbarLayout.setCantProductos(mainActivityViewModel.getProductosParaPedidosList().size());

//                            uiBind.contentMainActivityLayout.toolbarLayout.productsBadgeTV.setVisibility(View.VISIBLE);
                    } else {
                        uiBind.contentMainActivityLayout.toolbarLayout.shopCarIV.setVisibility(View.GONE);
                        uiBind.contentMainActivityLayout.toolbarLayout.productsBadgeTV.setVisibility(View.GONE);
                    }
                });

        mainActivityViewModel.getShowMenuOrBack().observe(this,
                showMenuOrBack -> {
                    if (!showMenuOrBack) {
                        uiBind.contentMainActivityLayout.toolbarLayout.menuIV.setVisibility(View.GONE);
                        uiBind.contentMainActivityLayout.toolbarLayout.backIV.setVisibility(View.VISIBLE);
                        uiBind.contentMainActivityLayout.toolbarLayout.backIV.setOnClickListener(v -> onBackPressed());
                    } else {
                        uiBind.contentMainActivityLayout.toolbarLayout.backIV.setVisibility(View.GONE);
                        uiBind.contentMainActivityLayout.toolbarLayout.menuIV.setVisibility(View.VISIBLE);
                    }
                });

        mainActivityViewModel.setShowMenuOrBack(true);
    }

    private void logout() {
        userAppPreferences.clearPreferences();
        onBackPressed();
    }

    @Override
    public void clickItemMenu(ItemMenuNavView itemMenuNavView) {
        Log.e("click", "click " + itemMenuNavView.getTitle());
        if (mainActivityViewModel.isColapsedMainContent().getValue() != null &&
                mainActivityViewModel.isColapsedMainContent().getValue()) {
            goToStartTransitionNavView();

            if (!itemMenuNavView.isSelected()) {
                itemMenuAdapter.selectedItemMenu(itemMenuNavView);
                navigateByItemMenu(itemMenuNavView);
            }
        }
    }

    //region Navigations
    private void navigateByItemMenu(ItemMenuNavView itemMenuNavView) {
        if (itemMenuNavView.getTitle().equals(getString(R.string.inicio_fragment)))
            initNavigate(R.id.inicioFragment, itemMenuNavView.getTitle());
        else if (itemMenuNavView.getTitle().equals(getString(R.string.nuevo_pedido_fragment)))
            initNavigate(R.id.menuFragment, itemMenuNavView.getTitle());
        else if (itemMenuNavView.getTitle().equals(getString(R.string.menu_historial_pedido)))
            initNavigate(R.id.misPedidosFragment, itemMenuNavView.getTitle());
        else if(itemMenuNavView.getTitle().equals(getString(R.string.productos_existentes_opc_menu)))
            initNavigate(R.id.productosExistenciaFragment, getString(R.string.productos_existentes));
        else if (itemMenuNavView.getTitle().equals(getString(R.string.menu_settings)))
            initNavigate(R.id.ajustesFragment, itemMenuNavView.getTitle());
    }

    private void initNavigate(int destino, String title) {
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView2);
        if (navController.getCurrentDestination() != null)
            navController.navigate(destino, new Bundle(),
                    new NavOptions.Builder().setPopUpTo(Objects.requireNonNull(
                                    navController.getCurrentDestination())
                            .getId(), true).build());
    }

    private void initNavigateWithoutPopUpTo(int destino, String title) {
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView2);
        if (navController.getCurrentDestination() != null)
            navController.navigate(destino, new Bundle());
    }
    //endregion Navigations

    private void verifyApkVersion() {

        compositeDisposable.add(
                mainActivityViewModel.getGetApkVersionUseCase()
                        .execute(infoVersion.versionCode)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(apkVersionList -> {
                                    if (!apkVersionList.isEmpty()) {
                                        apkVersion = apkVersionList.get(0);
                                        Log.e("ApkVersion", "verifyApkVersion: " + apkVersion.getVersion());
                                    }
                                },
                                throwable -> Log.e("ApkVersion", "verifyApkVersion: " + throwable.getMessage()),
                                this::createUpdateDialog)

        );

    }

    private void getApkData() {

        try {
            infoVersion = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (Exception e) {
//            e.getMessage();
        }

        if (infoVersion != null) {
            verifyApkVersion();
        }
    }

    private void createUpdateDialog() {
//        String peticionApk = apkVersion.getUrl().replace("~", "");
        userAppPreferences.setPreferenceString(PreferencesConstants.URL_EXPORT, apkVersion.getUrl());
        userAppPreferences.setPreferenceString(PreferencesConstants.VERSION_APK_NAME, "MisPedidos-Update" + apkVersion.getVersion() + ".apk");
        userAppPreferences.setPreferenceString(PreferencesConstants.FRAGMENT_NAME, "AutenticationFragment");
        userAppPreferences.setPreferenceInt(PreferencesConstants.NOTIFICATION_ID, PreferencesConstants.DOWNLOAD_APK);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Actualizar")
                .setMessage("Actualización disponible de MisPedidos.apk\nDesea Instalar?")
                .setPositiveButton("Actualizar", (dialog, which) -> verificarPermisos())
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }

    public void verificarPermisos() {

        Log.e("verificarPermisos: ", "MainActivity");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            Log.e("Permisos: ", "Storage autorizado: " + Environment.isExternalStorageManager());
            Log.e("Permisos: ", "Chewkeando Manage: " + ContextCompat.checkSelfPermission(this, Manifest.permission.MANAGE_EXTERNAL_STORAGE));
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.MANAGE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                shouldShowRequestPermissionRationale(Manifest.permission.MANAGE_EXTERNAL_STORAGE);
                Log.e("Permisos: ", "IF: " + shouldShowRequestPermissionRationale(Manifest.permission.MANAGE_EXTERNAL_STORAGE));
                //todo con true muestra una interfaz educativa AL USUARIO SOBRE LOS PERMISOS.
            }
            //todo: para saber si tengo permiso a todos los archivos llamo a Enviroment.isExternalStorageManager()
            if (!Environment.isExternalStorageManager()) {
                try {
                    Uri uri = Uri.parse("package:" + BuildConfig.APPLICATION_ID);
                    Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("verificarPermisos: ", "ERROR TRY: " + e.getMessage());
                    Uri uri = Uri.parse("package:" + BuildConfig.APPLICATION_ID);
                    Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION, uri);
                    startActivity(intent);
                }
            } else {
                initDownloadUpdateFile();
            }
        } else {
            activityResultLauncher.launch(PERMISSIONS);
        }
    }

    private void initDownloadUpdateFile() {

        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        Snackbar snackbar = Snackbar.make(uiBind.getRoot(), "Descarga Iniciada", Snackbar.LENGTH_SHORT);
        View view = snackbar.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.TOP;
        view.setLayoutParams(params);
        snackbar.show();

    }

    public void configPermissions() {

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(), isGranted -> {
                    if (isGranted.containsValue(true)) {
                        Log.e("MainActivity: ", "Permisos" + isGranted.keySet());
                        initDownloadUpdateFile();
                    } else {
                        AlertDialog alertDialog = new AlertDialog.Builder(this)
                                .setTitle("Permisos de Escritura")
                                .setMessage("Los permisos de escritura son necesarios para guardar los documentos que se soliciten en la aplicacion")
                                .setCancelable(false)
                                .setPositiveButton("Aceptar", (dialog, which) -> dialog.dismiss())
                                .create();
                        alertDialog.show();
                    }
                }
        );
    }

    public void downloadUpdateFinished() {

        compositeDisposable.add(RxBus.getInstance().getDownloadFinished().observeOn(AndroidSchedulers.mainThread())
                .subscribe(finalDownload -> {
                    try {
                        if (finalDownload == 100) {
                            Log.e("Ruta", " Documento: " + Environment.getExternalStorageDirectory().getPath() + "/"
                                    + Environment.DIRECTORY_DOWNLOADS
                                    + "/MisPedidos/" + userAppPreferences.getPreferenceString(PreferencesConstants.VERSION_APK_NAME, ""));
                            Intent descargar;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                File excelFile = new File(Environment.getExternalStorageDirectory().getPath() + "/" + Environment.DIRECTORY_DOWNLOADS + File.separator +
                                        "MisPedidos" + File.separator + userAppPreferences.getPreferenceString(PreferencesConstants.VERSION_APK_NAME, ""));
                                descargar = new Intent(Intent.ACTION_INSTALL_PACKAGE);
                                Uri uri = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", excelFile);
                                descargar.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                descargar.setData(uri);
                            } else {
                                descargar = new Intent(Intent.ACTION_VIEW);
                                Uri uri = Uri.parse("file:/" + Environment.getExternalStorageDirectory().getPath() + "/"
                                        + Environment.DIRECTORY_DOWNLOADS
                                        + "/MisPedidos/" + userAppPreferences.getPreferenceString(PreferencesConstants.VERSION_APK_NAME, ""));
                                descargar.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//TODO AVERIguar que es esto
                                descargar.setDataAndType(uri, "application/vnd.android.package-archive");// Este debe ser el formato en el que entiende que es un archivo tipo apk
                            }
                            startActivity(descargar);
                        }

                    } catch (Exception e) {
                        Log.e("ErrorCatchApk", " AutenticacionFragment: " + e.getMessage());
                    }
                }, throwable -> {
                    Log.e("FinalDownLoadApk: ", "Error AutenticcionFra: " + throwable.getMessage());
                }));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
}