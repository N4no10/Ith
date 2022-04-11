package cu.gob.ith.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiListPedidos {
    @SerializedName("success")
    private boolean success;

    @SerializedName("pedido")
    private List<ApiDatosPedido> apiDatosPedido;

    public ApiListPedidos() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ApiDatosPedido> getApiDatosPedido() {
        return apiDatosPedido;
    }

    public void setApiDatosPedido(List<ApiDatosPedido> apiDatosPedido) {
        this.apiDatosPedido = apiDatosPedido;
    }
}
