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
public class Entidad_ConsultarRangoFecha {

    private java.sql.Date fecha1;
    private java.sql.Date fecha2;

    public Entidad_ConsultarRangoFecha() {
    }

    public Entidad_ConsultarRangoFecha(Date fecha1, Date fecha2) {
        this.fecha1 = fecha1;
        this.fecha2 = fecha2;
    }

    public java.sql.Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(java.sql.Date fecha1) {
        this.fecha1 = fecha1;
    }

    public java.sql.Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(java.sql.Date fecha2) {
        this.fecha2 = fecha2;
    }

}
