package cu.gob.ith.presentation.activities.main.fragments.ajustes.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import cu.gob.ith.domain.interactors.LoginUseCase;
import cu.gob.ith.domain.interactors.PutUpdatePassword;
import cu.gob.ith.domain.model.login.LoginBody;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class AjustesViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final PutUpdatePassword putUpdatePassword;
    private final LoginUseCase loginUseCase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Inject
    public AjustesViewModel(PutUpdatePassword putUpdatePassword, LoginUseCase loginUseCase) {
        this.putUpdatePassword = putUpdatePassword;
        this.loginUseCase = loginUseCase;
    }

    public Completable updatePassword(String username, String passOld, String passnew) {
        Map<String, String> params = new HashMap<>();
        params.put("oldPassword", passOld);
        params.put("newPassword", passnew);

        return putUpdatePassword.execute(params)
                .andThen(loginUseCase.execute(addLoginBody(username, passnew)));
    }

    public void addCompositeDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    private LoginBody addLoginBody(String username, String pass) {
        return new LoginBody(username, pass);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}