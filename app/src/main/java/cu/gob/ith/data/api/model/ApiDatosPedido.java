package cu.gob.ith.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiDatosPedido {
    @SerializedName(value = "Numero", alternate = "NumDocumento")
    private String number;

    @SerializedName("Proveedor")
    private String provider;

    @SerializedName("CodigoITH")
    private String codeITH;

    @SerializedName("DireccionITH")
    private String addressITH;

    @SerializedName(value = "Codigo", alternate = "CodEntidad")
    private String code;

    @SerializedName("Direccion")
    private String address;

    @SerializedName(value = "Fecha", alternate = {"FechaDocumento", "FechaPedido"})
    private String date;

    @SerializedName(value = "FechaDespacho")
    private String dateDespacho;

    @SerializedName(value = "Cliente", alternate = {"User_Name", "UserName"})
    private String client;

    @SerializedName("CuentaBancaria")
    private String bankAccount;

    @SerializedName("Sucursal")
    private String sucursal;

    @SerializedName(value = "ImporteTotal", alternate = "Importe")
    private float importeTotal;

    @SerializedName("Productos")
    private List<ApiProducto> apiProductoList;

    @SerializedName("TipoCliente")
    private String tipoCliente;

    @SerializedName("DescripciomTipoCliente")
    private String descripcionTipoCliente;

    @SerializedName("Observaciones")
    private String observaciones;

    @SerializedName("PEDIDO_DETALLE")
    private String detalle;

    @SerializedName("Estado")
    private Integer estado;

    public ApiDatosPedido() {
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

    public List<ApiProducto> getApiProductoList() {
        return apiProductoList;
    }

    public void setProductoList(List<ApiProducto> apiProductoList) {
        this.apiProductoList = apiProductoList;
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

    public String getDateDespacho() {
        return dateDespacho;
    }

    public void setDateDespacho(String dateDespacho) {
        this.dateDespacho = dateDespacho;
    }
}
