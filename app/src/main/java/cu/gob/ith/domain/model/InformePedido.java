package cu.gob.ith.domain.model;

import com.google.gson.annotations.SerializedName;

import cu.gob.ith.data.api.model.ApiDatosPedido;

public class InformePedido {

    private boolean success;
    private DatosPedido datosPedido;

    public InformePedido(boolean success, DatosPedido datosPedido) {
        this.success = success;
        this.datosPedido = datosPedido;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DatosPedido getDatosPedido() {
        return datosPedido;
    }

    public void setDatosPedido(DatosPedido datosPedido) {
        this.datosPedido = datosPedido;
    }
}
