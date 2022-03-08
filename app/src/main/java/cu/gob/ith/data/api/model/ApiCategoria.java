package cu.gob.ith.data.api.model;

import com.google.gson.annotations.SerializedName;

public class ApiCategoria {

    @SerializedName("CodFamilia")
    private String codFamilia;

    @SerializedName("NombreFamilia")
    private String nombreFamilia;

    @SerializedName("ProductosTotales")
    private String productosTotales;

    public ApiCategoria() {
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

    public String getProductosTotales() {
        return productosTotales;
    }

    public void setProductosTotales(String productosTotales) {
        this.productosTotales = productosTotales;
    }
}
