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
    private float importe;
    private String estado;
    private String numDocumento;
    private int cantPedida;
    private int cantDespachada;

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

    public Producto(String descripcion, String referencia, String codUm, float pv,
                    float importe, String estado, String numDocumento, int cantPedida,
                    int cantDespachada) {
        this.descripcion = descripcion;
        this.referencia = referencia;
        this.codUm = codUm;
        this.pv = pv;
        this.importe = importe;
        this.estado = estado;
        this.numDocumento = numDocumento;
        this.cantPedida = cantPedida;
        this.cantDespachada = cantDespachada;
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

    public Producto(String descripcion, String referencia, String codUm, float pv,
                    float cantProducto, float importe) {
        this.descripcion = descripcion;
        this.referencia = referencia;
        this.codUm = codUm;
        this.pv = pv;
        this.cantProducto = cantProducto;
        this.importe = importe;
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

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public int getCantPedida() {
        return cantPedida;
    }

    public void setCantPedida(int cantPedida) {
        this.cantPedida = cantPedida;
    }

    public int getCantDespachada() {
        return cantDespachada;
    }

    public void setCantDespachada(int cantDespachada) {
        this.cantDespachada = cantDespachada;
    }
}
