/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author marti
 */
public class Entidad_Producto {

    private int idProducto;
    private String descripcion;
    private String categoria;
    private int precio;
    private int existencias_iniciales;

    public Entidad_Producto() {
    }

    public Entidad_Producto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Entidad_Producto(int idProducto, String descripcion, String categoria, int precio, int existencias_iniciales) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.existencias_iniciales = existencias_iniciales;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getExistencias_iniciales() {
        return existencias_iniciales;
    }

    public void setExistencias_iniciales(int existencias_iniciales) {
        this.existencias_iniciales = existencias_iniciales;
    }

}
