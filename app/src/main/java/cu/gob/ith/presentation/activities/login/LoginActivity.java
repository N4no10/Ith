package cu.gob.ith.presentation.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import javax.inject.Inject;

import cu.gob.ith.R;
import cu.gob.ith.databinding.ActivityLoginBinding;
import cu.gob.ith.domain.model.login.LoginBody;
import cu.gob.ith.presentation.activities.login.viewmodel.LoginActivityViewModel;
import cu.gob.ith.presentation.activities.main.fragments.informe.pdf.InformePedidoPDFGenerator;
import cu.gob.ith.presentation.activities.main.ui.MainActivity;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding uiBind;
    private LoginActivityViewModel loginActivityViewModel;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiBind = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setContentView(uiBind.getRoot());
        loginActivityViewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);
        
        initView();
    }

    private void initView() {
        uiBind.layoutFormLogin.buttonLoginIB.setOnClickListener(v -> {
            uiBind.layoutFormLogin.buttonLoginIB.startAnimation();

            LoginBody loginBody = new LoginBody(Objects.requireNonNull(uiBind.layoutFormLogin.userTextInputET.getText()).toString(),
                    Objects.requireNonNull(uiBind.layoutFormLogin.passTextInputET.getText()).toString());
            loginActivityViewModel.getLoginUseCase().execute(loginBody)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onComplete() {
                            uiBind.layoutFormLogin.buttonLoginIB.revertAnimation();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            uiBind.layoutFormLogin.buttonLoginIB.revertAnimation();

                            if (e.getMessage().contains("401"))
                                Snackbar.make(uiBind.getRoot(),
                                        R.string.unauthorized_401,
                                        Snackbar.LENGTH_SHORT).show();
                            Log.e("TAG", "onError: " + e.getMessage());
                        }
                    });

        });

    }
}