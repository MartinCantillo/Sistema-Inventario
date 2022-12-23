/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author marti
 */
public class Entidad_Proveedor {

    private int idProveedor;
    private String nombreProveedor;
    private String paginaweb;
    private String telefonoProveedor;
    private String direccionProveedor;
    private String correoElectronico;

    public Entidad_Proveedor() {
    }

    public Entidad_Proveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Entidad_Proveedor(int idProveedor, String nombreProveedor, String paginaweb, String telefonoProveedor, String direccionProveedor, String correoElectronico) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.paginaweb = paginaweb;
        this.telefonoProveedor = telefonoProveedor;
        this.direccionProveedor = direccionProveedor;
        this.correoElectronico = correoElectronico;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getPaginaweb() {
        return paginaweb;
    }

    public void setPaginaweb(String paginaweb) {
        this.paginaweb = paginaweb;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

}
