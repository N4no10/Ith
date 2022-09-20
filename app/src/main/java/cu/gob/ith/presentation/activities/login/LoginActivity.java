package cu.gob.ith.presentation.activities.login;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import javax.inject.Inject;

import cu.gob.ith.R;
import cu.gob.ith.common.Util;
import cu.gob.ith.data.preferences.PreferenceConstants;
import cu.gob.ith.databinding.ActivityLoginBinding;
import cu.gob.ith.databinding.OlvidoPassDialogBinding;
import cu.gob.ith.domain.model.login.LoginBody;
import cu.gob.ith.presentation.activities.login.viewmodel.LoginActivityViewModel;
import cu.gob.ith.presentation.activities.main.fragments.informe.pdf.InformePedidoPDFGenerator;
import cu.gob.ith.presentation.activities.main.ui.MainActivity;
import cu.gob.ith.presentation.util.MyProgressDialog;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding uiBind;
    private LoginActivityViewModel loginActivityViewModel;
    private AlertDialog alertDialog;
    private MyProgressDialog myProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiBind = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setContentView(uiBind.getRoot());
        loginActivityViewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);

        initView();

    }

    private void initView() {
        uiBind.layoutFormLogin.forgotPassTV.setOnClickListener((v) -> initOlvidoPass());
        uiBind.layoutFormLogin.buttonLoginIB.setOnClickListener(v -> {
            // Toast.makeText(this, "Press Button", Toast.LENGTH_SHORT).show();
            uiBind.layoutFormLogin.buttonLoginIB.startAnimation();

            LoginBody loginBody = new LoginBody(Objects.requireNonNull(uiBind.layoutFormLogin.userTextInputET.getText()).toString(),
                    Util.codificarTextBase64(Objects.requireNonNull(uiBind.layoutFormLogin.passTextInputET.getText()).toString()));
            loginActivityViewModel.getLoginUseCase().execute(loginBody)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            loginActivityViewModel.getCompositeDisposable().add(d);
                        }

                        @Override
                        public void onComplete() {
                            uiBind.layoutFormLogin.buttonLoginIB.revertAnimation();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                            // Toast.makeText(uiBind.getRoot().getContext(), "Complete login ", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            uiBind.layoutFormLogin.buttonLoginIB.revertAnimation();

                            Toast.makeText(uiBind.getRoot().getContext(), "Error login " + e.getMessage(), Toast.LENGTH_SHORT).show();

                            if (e.getMessage().contains("401"))
                                Snackbar.make(uiBind.getRoot(),
                                        R.string.unauthorized_401,
                                        Snackbar.LENGTH_SHORT).show();
                            Log.e("TAG", "onError: " + e.getMessage());
                        }
                    });

        });

    }

    private void createAlertDialogolvidoPass(View v) {
        if (alertDialog == null) {
            alertDialog = new MaterialAlertDialogBuilder(this)
                    .setCancelable(false)
                    .create();

            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        alertDialog.setView(v);
        alertDialog.show();
    }

    private void initOlvidoPass() {
        OlvidoPassDialogBinding olvidoPassDialogBinding = DataBindingUtil.inflate(
                getLayoutInflater(), R.layout.olvido_pass_dialog, null, false);

        if (uiBind.layoutFormLogin.userTextInputET.getText() != null &&
                !uiBind.layoutFormLogin.userTextInputET.getText().toString().isEmpty())
            olvidoPassDialogBinding.userTextInputET.setText(uiBind.layoutFormLogin.userTextInputET.getText());

        olvidoPassDialogBinding.buttonSaveIB.setOnClickListener(v2 -> {
            olvidoPassDialogBinding.setValidate(true);
            if (olvidoPassDialogBinding.userTextInputET.getText() != null &&
                    olvidoPassDialogBinding.passNewTextInputET.getText() != null &&
                    olvidoPassDialogBinding.ciTextInputET.getText() != null) {


                String passUser = olvidoPassDialogBinding.userTextInputET.getText().toString();
                String passCI = olvidoPassDialogBinding.ciTextInputET.getText().toString();
                String passNew = olvidoPassDialogBinding.passNewTextInputET.getText().toString();

                if (!passUser.isEmpty() && !passCI.isEmpty() && !passNew.isEmpty())
                    recuperarPass(passUser, passCI, passNew);
                else {
                    olvidoPassDialogBinding.msgErrorTV.setText(R.string.credentials_empty);
                    olvidoPassDialogBinding.msgErrorTV.setVisibility(View.VISIBLE);
                }
            }

        });


        olvidoPassDialogBinding.buttonCancelIB.setOnClickListener(v2 -> alertDialog.dismiss());
        createAlertDialogolvidoPass(olvidoPassDialogBinding.getRoot());
    }


    private void recuperarPass(String passUser, String passCI, String passNew) {
        if (myProgressDialog == null)
            myProgressDialog = new MyProgressDialog(this, getString(R.string.msg_change_pass_dialog));

        alertDialog.dismiss();
        myProgressDialog.showProgress();
        loginActivityViewModel.recuperarPass(passUser, passCI, passNew)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        loginActivityViewModel.getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onComplete() {
                        clearDataAlertDialog();
                        myProgressDialog.dissmisProgress();
                        Snackbar.make(uiBind.getRoot(), getString(R.string.success_pass_dialog),
                                Snackbar.LENGTH_LONG).show();
                        uiBind.layoutFormLogin.passTextInputET.setText(passNew);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        clearDataAlertDialog();
                        myProgressDialog.dissmisProgress();
                            Log.e("Error","error " + e.getMessage());

                            Snackbar.make(uiBind.getRoot(), e.getMessage().contains("401") ?
                                            getString(R.string.unauthorized) : "Error " + e.getMessage(),
                                    Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    private void clearDataAlertDialog(){
        ((TextInputEditText)alertDialog.getWindow().findViewById(R.id.ciTextInputET)).setText(null);
        ((TextInputEditText)alertDialog.getWindow().findViewById(R.id.passNewTextInputET)).setText(null);
    }
}