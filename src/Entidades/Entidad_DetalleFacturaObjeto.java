/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author marti
 */
public class Entidad_DetalleFacturaObjeto {

    private int idFactura;
    private int idProducto;
    private String idDescripcion;
    private int cantidad;
    private int precio;
    private int stocfinal;
    private int stock;

    public Entidad_DetalleFacturaObjeto() {
    }

    public Entidad_DetalleFacturaObjeto(int idFactura, int idProducto) {
        this.idFactura = idFactura;
        this.idProducto = idProducto;
    }

    public Entidad_DetalleFacturaObjeto(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getStocfinal() {
        return stocfinal;
    }

    public void setStocfinal(int stocfinal) {
        this.stocfinal = stocfinal;
    }

    public Entidad_DetalleFacturaObjeto(int idFactura, int idProducto, String idDescripcion, int cantidad, int precio, int stocfinal) {
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.idDescripcion = idDescripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.stocfinal = stocfinal;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getIdDescripcion() {
        return idDescripcion;
    }

    public void setIdDescripcion(String idDescripcion) {
        this.idDescripcion = idDescripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
