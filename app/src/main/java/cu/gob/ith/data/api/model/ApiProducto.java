package cu.gob.ith.data.api.model;

import com.google.gson.annotations.SerializedName;

public class ApiProducto {

    @SerializedName("Referencia")
    private String referencia;

    @SerializedName("Descripcion")
    private String descripcion;

    @SerializedName("CodUm")
    private String codUm;

    @SerializedName("CodFamilia")
    private String codFamilia;

    @SerializedName("NombreFamilia")
    private String nombreFamilia;

    @SerializedName("PV")
    private String pv;

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

    public String getPv() {
        return pv;
    }

    public void setPv(String pv) {
        this.pv = pv;
    }
}
