package cu.gob.ith.data.api.model;

import com.google.gson.annotations.SerializedName;

public class ApiLoginResponse {

    @SerializedName("Username")
    private String username;
    @SerializedName("Nombre")
    private String name;
    @SerializedName("Email")
    private String email;
    @SerializedName("Token")
    private String token;

    public ApiLoginResponse(String username, String name, String email, String token) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.token = token;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
