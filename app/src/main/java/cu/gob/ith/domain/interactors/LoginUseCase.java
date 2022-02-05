package cu.gob.ith.domain.interactors;

import javax.inject.Inject;

import cu.gob.ith.data.repository.ImplRepository;
import cu.gob.ith.domain.mappers.TransformLoginBodyToApiLoginBody;
import cu.gob.ith.domain.model.login.LoginBody;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class LoginUseCase implements GlobalUseCase<Completable, LoginBody> {

    private ImplRepository implRepository;

    @Inject
    public LoginUseCase(ImplRepository implRepository) {
        this.implRepository = implRepository;
    }

    @Override
    public Completable execute(LoginBody param) {
        return implRepository.login(TransformLoginBodyToApiLoginBody.map(param))
                .concatMapCompletable(apiLoginResponse -> Completable.create(CompletableEmitter::onComplete)
                );
    }
}
