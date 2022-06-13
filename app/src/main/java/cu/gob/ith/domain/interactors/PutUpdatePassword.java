package cu.gob.ith.domain.interactors;

import java.util.Map;

import javax.inject.Inject;

import cu.gob.ith.data.repository.ImplRepository;
import io.reactivex.rxjava3.core.Completable;

public class PutUpdatePassword implements GlobalUseCase<Completable, Map<String,String>>{

    private final ImplRepository repository;

    @Inject
    public PutUpdatePassword(ImplRepository repository) {
        this.repository = repository;
    }

    @Override
    public Completable execute(Map<String, String> param) {
        return repository.updatePassword(param);
    }
}
