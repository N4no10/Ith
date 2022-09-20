package cu.gob.ith.presentation.activities.login.viewmodel;

import android.os.Build;
import android.util.Base64;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModel;

import java.nio.charset.StandardCharsets;

import javax.inject.Inject;

import cu.gob.ith.common.Util;
import cu.gob.ith.domain.interactors.LoginUseCase;
import cu.gob.ith.domain.interactors.RecuperarPassUseCase;
import cu.gob.ith.domain.model.login.LoginBody;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@HiltViewModel
public class LoginActivityViewModel extends ViewModel {

    private final LoginUseCase loginUseCase;
    private final RecuperarPassUseCase recuperarPassUseCase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Inject
    public LoginActivityViewModel(LoginUseCase loginUseCase, RecuperarPassUseCase recuperarPassUseCase) {
        this.loginUseCase = loginUseCase;
        this.recuperarPassUseCase = recuperarPassUseCase;
    }

    public LoginUseCase getLoginUseCase() {
        return loginUseCase;
    }

    public Completable recuperarPass(String user, String ci, String pass){
        LoginBody loginBody = new LoginBody(user,ci, Util.codificarTextBase64(pass));
        return recuperarPassUseCase.execute(loginBody);
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if(compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
}
