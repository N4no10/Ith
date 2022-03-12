package cu.gob.ith.domain.model;

import com.google.gson.annotations.SerializedName;

public class Producto {
    private String descripcion;
    private String referencia;
    private String codUm;
    private String codFamilia;
    private String nombreFamilia;
    private float pv;
    private float cantProducto = 0;

    public Producto(String descripcion) {
        this.descripcion = descripcion;
    }

    public Producto(String descripcion, String referencia, String codUm,
                    String codFamilia, String nombreFamilia, float pv) {
        this.descripcion = descripcion;
        this.referencia = referencia;
        this.codUm = codUm;
        this.codFamilia = codFamilia;
        this.nombreFamilia = nombreFamilia;
        this.pv = pv;
    }

    public Producto(String descripcion, String referencia,
                    String codUm, String codFamilia, String nombreFamilia,
                    float pv, float cantProducto) {
        this.descripcion = descripcion;
        this.referencia = referencia;
        this.codUm = codUm;
        this.codFamilia = codFamilia;
        this.nombreFamilia = nombreFamilia;
        this.pv = pv;
        this.cantProducto = cantProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
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

    public float getCantProducto() {
        return cantProducto;
    }

    public void setCantProducto(float cantProducto) {
        this.cantProducto = cantProducto;
    }
}
