package cu.gob.ith.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiListProductos {
    @SerializedName("success")
    private boolean success;

    @SerializedName(value = "producto", alternate = {"pedido"})
    private List<ApiProducto> apiProductoList;

    public ApiListProductos() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ApiProducto> getApiProductoList() {
        return apiProductoList;
    }

    public void setApiDatosPedido(List<ApiProducto> apiProductos) {
        this.apiProductoList = apiProductos;
    }
}
