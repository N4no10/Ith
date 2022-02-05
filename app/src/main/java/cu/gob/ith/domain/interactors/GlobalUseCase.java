package cu.gob.ith.domain.interactors;

public interface GlobalUseCase<T, P> {

    default T execute() {
        return null;
    }

    default T execute(P param) {
        return null;
    }

}
