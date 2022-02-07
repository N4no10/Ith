package cu.gob.ith.presentation.activities.login.viewmodel;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cu.gob.ith.domain.interactors.LoginUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginActivityViewModel extends ViewModel {

    private final LoginUseCase loginUseCase;

    @Inject
    public LoginActivityViewModel(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    public LoginUseCase getLoginUseCase() {
        return loginUseCase;
    }

}
