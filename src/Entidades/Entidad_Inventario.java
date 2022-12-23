/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author marti
 */
public class Entidad_Inventario {

    private int Id_Producto;
    private String Descripcion;
    private int existencias_iniciales;
    private int entrada;
    private int salidas;
    private int Stock;

    public Entidad_Inventario(int Id_Producto) {
        this.Id_Producto = Id_Producto;
    }

    public Entidad_Inventario() {
    }

    public Entidad_Inventario(int Id_Producto, String Descripcion, int existencias_iniciales, int entrada, int salidas, int Stock) {
        this.Id_Producto = Id_Producto;
        this.Descripcion = Descripcion;
        this.existencias_iniciales = existencias_iniciales;
        this.entrada = entrada;
        this.salidas = salidas;
        this.Stock = Stock;
    }

    public int getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(int Id_Producto) {
        this.Id_Producto = Id_Producto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getExistencias_iniciales() {
        return existencias_iniciales;
    }

    public void setExistencias_iniciales(int existencias_iniciales) {
        this.existencias_iniciales = existencias_iniciales;
    }

    public int getEntrada() {
        return entrada;
    }

    public void setEntrada(int entrada) {
        this.entrada = entrada;
    }

    public int getSalidas() {
        return salidas;
    }

    public void setSalidas(int salidas) {
        this.salidas = salidas;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

}
