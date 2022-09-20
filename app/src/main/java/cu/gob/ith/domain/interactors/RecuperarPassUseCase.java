package cu.gob.ith.domain.interactors;

import javax.inject.Inject;

import cu.gob.ith.data.repository.ImplRepository;
import cu.gob.ith.domain.mappers.TransformLoginBodyToApiLoginBody;
import cu.gob.ith.domain.model.login.LoginBody;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;


public class RecuperarPassUseCase implements GlobalUseCase<Completable, LoginBody> {

    private ImplRepository implRepository;

    @Inject
    public RecuperarPassUseCase(ImplRepository implRepository) {
        this.implRepository = implRepository;
    }

    @Override
    public Completable execute(LoginBody param) {
        return implRepository.recuperarPass(TransformLoginBodyToApiLoginBody.map(param));
    }
}
