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
public class Entidad_RegistrarFactura {

    private int idcompra;
    private String formadepago;
    private int contado;
    private Date fechacompra;
    private int totalcompra;
    private int idvendedor;
    private int cantidad;
    private int idcliente;
    

    public Entidad_RegistrarFactura() {
    }

    public Entidad_RegistrarFactura(int idcompra, String formadepago, int contado, Date fechacompra, int totalcompra, int idvendedor, int cantidad, int idcliente) {
        this.idcompra = idcompra;
        this.formadepago = formadepago;
        this.contado = contado;
        this.fechacompra = fechacompra;
        this.totalcompra = totalcompra;
        this.idvendedor = idvendedor;
        this.cantidad = cantidad;
        this.idcliente = idcliente;
    }

    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public String getFormadepago() {
        return formadepago;
    }

    public void setFormadepago(String formadepago) {
        this.formadepago = formadepago;
    }

    public int getContado() {
        return contado;
    }

    public void setContado(int contado) {
        this.contado = contado;
    }

    public Date getFechacompra() {
        return fechacompra;
    }

    public void setFechacompra(Date fechacompra) {
        this.fechacompra = fechacompra;
    }

    public int getTotalcompra() {
        return totalcompra;
    }

    public void setTotalcompra(int totalcompra) {
        this.totalcompra = totalcompra;
    }

    public int getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(int idvendedor) {
        this.idvendedor = idvendedor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }
    
    
  

}