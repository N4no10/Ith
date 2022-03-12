package cu.gob.ith.data.api.model;

import com.google.gson.annotations.SerializedName;

public class ApiPedidoResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("pedido")
    private ApiDatosPedido apiDatosPedido;

    public ApiPedidoResponse(boolean success, ApiDatosPedido apiDatosPedido) {
        this.success = success;
        this.apiDatosPedido = apiDatosPedido;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ApiDatosPedido getApiDatosPedido() {
        return apiDatosPedido;
    }

    public void setApiDatosPedido(ApiDatosPedido apiDatosPedido) {
        this.apiDatosPedido = apiDatosPedido;
    }
}
