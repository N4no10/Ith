package cu.gob.ith.data.api.model;

import com.google.gson.annotations.SerializedName;

public class ApiLoginBody {

    @SerializedName("Username")
    private String username;
    @SerializedName("Password")
    private String password;

    public ApiLoginBody(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
