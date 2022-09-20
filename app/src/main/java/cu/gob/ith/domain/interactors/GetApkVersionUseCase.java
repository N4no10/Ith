package cu.gob.ith.domain.interactors;

import java.util.List;

import javax.inject.Inject;

import cu.gob.ith.data.repository.ImplRepository;
import cu.gob.ith.domain.mappers.TransformApiApkVersionToApkVersion;
import cu.gob.ith.domain.model.ApkVersion;
import io.reactivex.rxjava3.core.Observable;

public class GetApkVersionUseCase implements GlobalUseCase<Observable<ApkVersion>, Integer> {

    private final ImplRepository implRepository;

    @Inject
    public GetApkVersionUseCase(ImplRepository implRepository) {
        this.implRepository = implRepository;
    }

    @Override
    public Observable<ApkVersion> execute(Integer param) {
        return implRepository.getApkVersion(param)
                .map(TransformApiApkVersionToApkVersion::map);
    }
}
