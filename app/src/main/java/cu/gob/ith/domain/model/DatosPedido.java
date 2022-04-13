package cu.gob.ith.domain.model;

import java.util.List;

public class DatosPedido {

    private String number;
    private String provider;
    private String codeITH;
    private String addressITH;
    private String code;
    private String address;
    private String date;
    private String secondDate;
    private String client;
    private String bankAccount;
    private String sucursal;
    private String tipoCliente;
    private String descripcionTipoCliente;
    private float importeTotal;
    private List<Producto> productoList;

    private String observaciones;
    private String detalle;
    private Integer estado;

    public DatosPedido(String number, String provider, String codeITH, String addressITH,
                       String code, String address, String date, String client,
                       String bankAccount, String sucursal, float importeTotal,
                       String tipoCliente, String descripcionTipoCliente,
                       List<Producto> productoList, String observaciones, String detalle, Integer estado) {
        this.number = number;
        this.provider = provider;
        this.codeITH = codeITH;
        this.addressITH = addressITH;
        this.code = code;
        this.address = address;
        this.date = date;
        this.client = client;
        this.bankAccount = bankAccount;
        this.sucursal = sucursal;
        this.importeTotal = importeTotal;
        this.productoList = productoList;
        this.tipoCliente = tipoCliente;
        this.descripcionTipoCliente = descripcionTipoCliente;
        this.observaciones = observaciones;
        this.detalle = detalle;
        this.estado = estado;
    }

    public DatosPedido(String number, String date, String secondDate, String client) {
        this.number = number;
        this.date = date;
        this.secondDate = secondDate;
        this.client = client;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getCodeITH() {
        return codeITH;
    }

    public void setCodeITH(String codeITH) {
        this.codeITH = codeITH;
    }

    public String getAddressITH() {
        return addressITH;
    }

    public void setAddressITH(String addressITH) {
        this.addressITH = addressITH;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public float getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(float importeTotal) {
        this.importeTotal = importeTotal;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getDescripcionTipoCliente() {
        return descripcionTipoCliente;
    }

    public void setDescripcionTipoCliente(String descripcionTipoCliente) {
        this.descripcionTipoCliente = descripcionTipoCliente;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getSecondDate() {
        return secondDate;
    }

    public void setSecondDate(String secondDate) {
        this.secondDate = secondDate;
    }
}
