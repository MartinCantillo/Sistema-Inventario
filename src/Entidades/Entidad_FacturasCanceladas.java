/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.sql.Date;

/**
 *
 * @author marti
 */
public class Entidad_FacturasCanceladas {

    private int idfactura;
    private java.sql.Date fecha;
    private int idcliente;
    private int idproucto;
    private int cantidad;

    public Entidad_FacturasCanceladas() {
    }

    public Entidad_FacturasCanceladas(int idfactura) {
        this.idfactura = idfactura;
    }

    public Entidad_FacturasCanceladas(int idfactura, Date fecha, int idcliente, int idproucto, int cantidad) {
        this.idfactura = idfactura;
        this.fecha = fecha;
        this.idcliente = idcliente;
        this.idproucto = idproucto;
        this.cantidad = cantidad;
    }

    public int getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(int idfactura) {
        this.idfactura = idfactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdproucto() {
        return idproucto;
    }

    public void setIdproucto(int idproucto) {
        this.idproucto = idproucto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
