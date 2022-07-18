package cu.gob.ith.data.api.model;

import com.google.gson.annotations.SerializedName;

public class ApiApkVersion {
    @SerializedName("Id")
    private int id;

    @SerializedName("Version")
    private String version;

    @SerializedName("Url")
    private String url;

    public ApiApkVersion() {
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
