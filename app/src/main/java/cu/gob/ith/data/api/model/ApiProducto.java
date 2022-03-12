package cu.gob.ith.data.api.model;

import com.google.gson.annotations.SerializedName;

public class ApiProducto {

    @SerializedName(value = "Referencia",
            alternate = {"Codigo"})
    private String referencia;

    @SerializedName(value = "Descripcion",
            alternate = {"Producto"})
    private String descripcion;

    @SerializedName(value = "CodUm",
            alternate = {"UM"})
    private String codUm;

    @SerializedName("CodFamilia")
    private String codFamilia;

    @SerializedName("NombreFamilia")
    private String nombreFamilia;

    @SerializedName(value = "PV",
            alternate = {"PVP"})
    private float pv;

    @SerializedName("Cantidad")
    private float cantidad;

    @SerializedName("Importe")
    private float importe;


    public ApiProducto() {
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodUm() {
        return codUm;
    }

    public void setCodUm(String codUm) {
        this.codUm = codUm;
    }

    public String getCodFamilia() {
        return codFamilia;
    }

    public void setCodFamilia(String codFamilia) {
        this.codFamilia = codFamilia;
    }

    public String getNombreFamilia() {
        return nombreFamilia;
    }

    public void setNombreFamilia(String nombreFamilia) {
        this.nombreFamilia = nombreFamilia;
    }

    public float getPv() {
        return pv;
    }

    public void setPv(float pv) {
        this.pv = pv;
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
