package cu.gob.ith.domain.model;

public class Categoria {
    private String nombreFamilia;
    private String codFamilia;
    private String productosTotales;
    private boolean selected;

    public String getCodFamilia() {
        return codFamilia;
    }

    public void setCodFamilia(String codFamilia) {
        this.codFamilia = codFamilia;
    }

    public String getProductosTotales() {
        return productosTotales;
    }

    public void setProductosTotales(String productosTotales) {
        this.productosTotales = productosTotales;
    }

    public Categoria(String nombreFamilia) {
        this.nombreFamilia = nombreFamilia;
    }

    public Categoria(String nombreFamilia, String codFamilia, String productosTotales) {
        this.nombreFamilia = nombreFamilia;
        this.codFamilia = codFamilia;
        this.productosTotales = productosTotales;
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
