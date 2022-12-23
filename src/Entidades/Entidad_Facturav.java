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
public class Entidad_Facturav {

    private int idcliente;
    private int idvendedor;
    private java.sql.Date fecha;
    private String formapago;

    private int idfactura;

    public Entidad_Facturav() {
    }

    public Entidad_Facturav(int idcliente, int idvendedor) {
        this.idcliente = idcliente;
        this.idvendedor = idvendedor;
    }
    

    public Entidad_Facturav(int idcliente, int idvendedor, Date fecha, String formapago, int idfactura) {
        this.idcliente = idcliente;
        this.idvendedor = idvendedor;
        this.fecha = fecha;
        this.formapago = formapago;
        this.idfactura = idfactura;
    }

    public Entidad_Facturav(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(int idvendedor) {
        this.idvendedor = idvendedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }

    public int getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(int idfactura) {
        this.idfactura = idfactura;
    }

}
