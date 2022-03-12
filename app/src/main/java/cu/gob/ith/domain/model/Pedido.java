package cu.gob.ith.domain.model;

import com.google.gson.annotations.SerializedName;

public class Pedido {

    private String reference;
    private float pvp;
    private float cantidad;
    private float importe;

    public Pedido(String reference, float pvp, float cantidad, float importe) {
        this.reference = reference;
        this.pvp = pvp;
        this.cantidad = cantidad;
        this.importe = importe;
    }


    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public float getPvp() {
        return pvp;
    }

    public void setPvp(float pvp) {
        this.pvp = pvp;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }
}
