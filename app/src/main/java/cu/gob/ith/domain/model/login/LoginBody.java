package cu.gob.ith.domain.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginBody {

    private String username;
    private String ci;
    private String password;

    public LoginBody(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginBody(String username, String ci, String password) {
        this.username = username;
        this.ci = ci;
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

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }
}
