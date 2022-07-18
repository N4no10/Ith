package cu.gob.ith.data.util;


import io.reactivex.rxjava3.subjects.PublishSubject;

public class RxBus {
    private static RxBus _instance;

    private RxBus() {
    }

    public static RxBus getInstance() {
        if (_instance == null) {
            _instance = new RxBus();
        }
        return _instance;
    }

    private PublishSubject<Integer> downloadFinished = PublishSubject.create();

    public PublishSubject<Integer> getDownloadFinished() {
        return downloadFinished;
    }

    public void postDownloadFinished(Integer downloadFinished) {
        this.downloadFinished.onNext(downloadFinished);
    }
}
