package cu.gob.ith.domain.model;

import com.google.gson.annotations.SerializedName;

public class Categoria {
    private String nombreFamilia;
    private String codFamilia;
    private String productosFamilia;
    private boolean selected;

    public String getCodFamilia() {
        return codFamilia;
    }

    public void setCodFamilia(String codFamilia) {
        this.codFamilia = codFamilia;
    }

    public String getProductosFamilia() {
        return productosFamilia;
    }

    public void setProductosFamilia(String productosFamilia) {
        this.productosFamilia = productosFamilia;
    }

    public Categoria(String nombreFamilia) {
        this.nombreFamilia = nombreFamilia;
    }

    public Categoria(String nombreFamilia, String codFamilia, String productosFamilia) {
        this.nombreFamilia = nombreFamilia;
        this.codFamilia = codFamilia;
        this.productosFamilia = productosFamilia;
    }

    public String getNombreFamilia() {
        return nombreFamilia;
    }

    public void setNombreFamilia(String nombreFamilia) {
        this.nombreFamilia = nombreFamilia;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
