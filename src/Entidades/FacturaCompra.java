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
public class FacturaCompra {

    private int idfacturacompra;
    private Date fechafacturacion;
    private int id_cliente;
    private int idcompra;
    private int idproducto;

    public FacturaCompra() {
    }

    public FacturaCompra(int idfacturacompra, Date fechafacturacion, int id_cliente, int idcompra, int idproducto) {
        this.idfacturacompra = idfacturacompra;
        this.fechafacturacion = fechafacturacion;
        this.id_cliente = id_cliente;
        this.idcompra = idcompra;
        this.idproducto = idproducto;
    }

    public int getIdfacturacompra() {
        return idfacturacompra;
    }

    public void setIdfacturacompra(int idfacturacompra) {
        this.idfacturacompra = idfacturacompra;
    }

    public Date getFechafacturacion() {
        return fechafacturacion;
    }

    public void setFechafacturacion(Date fechafacturacion) {
        this.fechafacturacion = fechafacturacion;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

}
