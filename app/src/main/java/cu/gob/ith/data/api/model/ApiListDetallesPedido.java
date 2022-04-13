package cu.gob.ith.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiListDetallesPedido {

    @SerializedName("success")
    private boolean success;

    @SerializedName("pedido")
    private List<ApiProducto> apiDatosPedido;

    public ApiListDetallesPedido() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ApiProducto> getApiDatosPedido() {
        return apiDatosPedido;
    }

    public void setApiDatosPedido(List<ApiProducto> apiDatosPedido) {
        this.apiDatosPedido = apiDatosPedido;
    }
}
