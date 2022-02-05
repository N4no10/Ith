package cu.gob.ith.presentation.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import javax.inject.Inject;

import cu.gob.ith.R;
import cu.gob.ith.databinding.ActivityLoginBinding;
import cu.gob.ith.domain.interactors.LoginUseCase;
import cu.gob.ith.domain.model.login.LoginBody;
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

    @Inject
    LoginUseCase loginUseCase;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiBind = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setContentView(uiBind.getRoot());

        initView();
    }

    private void initView() {
        uiBind.layoutFormLogin.buttonLoginIB.setOnClickListener(v -> {
            uiBind.layoutFormLogin.buttonLoginIB.startAnimation();

            LoginBody loginBody = new LoginBody(uiBind.layoutFormLogin.userTextInputET.getText().toString(),
                    uiBind.layoutFormLogin.passTextInputET.getText().toString());
            loginUseCase.execute(loginBody)
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

                            Log.e("TAG", "onError: " + e.getMessage());
                        }
                    });


        });

    }
}