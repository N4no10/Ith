package cu.gob.ith.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import cu.gob.ith.data.api.model.ApiProducto;

public class DatosPedido {

    private String number;
    private String provider;
    private String codeITH;
    private String addressITH;
    private String code;
    private String address;
    private String date;
    private String client;
    private String bankAccount;
    private String sucursal;
    private float importeTotal;
    private List<Producto> productoList;

    public DatosPedido(String number, String provider, String codeITH, String addressITH,
                       String code, String address, String date, String client,
                       String bankAccount, String sucursal, float importeTotal,
                       List<Producto> productoList) {
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

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }
}
