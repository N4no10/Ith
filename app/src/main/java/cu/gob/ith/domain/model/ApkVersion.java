package cu.gob.ith.domain.model;

public class ApkVersion {
    private int id;
    private String version;
    private String url;

    public ApkVersion() {
    }

    public ApkVersion(int id, String version, String url) {
        this.id = id;
        this.version = version;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
