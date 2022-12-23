/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.Date;

/**
 *
 * @author marti
 */
public class Entidad_EntradaProductos {

    private int idproducto;
    private int idproveedor;
    private int cantidad;
    private java.sql.Date fecha;
    private int numEntrada;
    private int stock;
    private int actualizarstock;
    private int obtenerstockfactura;
    private int operacionstock;

    public Entidad_EntradaProductos() {
    }

    public Entidad_EntradaProductos(int numEntrada) {
        this.numEntrada = numEntrada;
    }

    public Entidad_EntradaProductos(int idproducto, int idproveedor, int cantidad, java.sql.Date fecha, int numEntrada, int stock) {
        this.idproducto = idproducto;
        this.idproveedor = idproveedor;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.numEntrada = numEntrada;
        this.stock = stock;
    }

    public Entidad_EntradaProductos(int idproducto, int idproveedor, int cantidad, java.sql.Date fecha, int numEntrada, int stock, int actualizarstock) {
        this.idproducto = idproducto;
        this.idproveedor = idproveedor;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.numEntrada = numEntrada;
        this.stock = stock;
        this.actualizarstock = actualizarstock;
    }

    public Entidad_EntradaProductos(int idproducto, int idproveedor, int cantidad, java.sql.Date fecha, int numEntrada, int stock, int actualizarstock, int obtenerstockfactura) {
        this.idproducto = idproducto;
        this.idproveedor = idproveedor;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.numEntrada = numEntrada;
        this.stock = stock;
        this.actualizarstock = actualizarstock;
        this.obtenerstockfactura = obtenerstockfactura;
    }

    public Entidad_EntradaProductos(int idproducto, int idproveedor, int cantidad, java.sql.Date fecha, int numEntrada, int stock, int actualizarstock, int obtenerstockfactura, int operacionstock) {
        this.idproducto = idproducto;
        this.idproveedor = idproveedor;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.numEntrada = numEntrada;
        this.stock = stock;
        this.actualizarstock = actualizarstock;
        this.obtenerstockfactura = obtenerstockfactura;
        this.operacionstock = operacionstock;
    }
    

    public int getActualizarstock() {
        return actualizarstock;
    }

    public void setActualizarstock(int actualizarstock) {
        this.actualizarstock = actualizarstock;
    }

    public Entidad_EntradaProductos(int idproducto, int idproveedor, int cantidad, java.sql.Date fecha, int numEntrada) {
        this.idproducto = idproducto;
        this.idproveedor = idproveedor;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.numEntrada = numEntrada;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public java.sql.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    public int getNumEntrada() {
        return numEntrada;
    }

    public void setNumEntrada(int numEntrada) {
        this.numEntrada = numEntrada;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getObtenerstockfactura() {
        return obtenerstockfactura;
    }

    public void setObtenerstockfactura(int obtenerstockfactura) {
        this.obtenerstockfactura = obtenerstockfactura;
    }

    public int getOperacionstock() {
        return operacionstock;
    }

    public void setOperacionstock(int operacionstock) {
        this.operacionstock = operacionstock;
    }
    
    

}
