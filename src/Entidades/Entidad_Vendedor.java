/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author marti
 */
public class Entidad_Vendedor {

    private int idvendedor;
    private String nombrevendedor;
    private String telefonovendedor;

    public Entidad_Vendedor() {
    }

    public Entidad_Vendedor(int idvendedor) {
        this.idvendedor = idvendedor;
    }

    public Entidad_Vendedor(int idvendedor, String nombrevendedor, String telefonovendedor) {
        this.idvendedor = idvendedor;
        this.nombrevendedor = nombrevendedor;
        this.telefonovendedor = telefonovendedor;
    }

    public int getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(int idvendedor) {
        this.idvendedor = idvendedor;
    }

    public String getNombrevendedor() {
        return nombrevendedor;
    }

    public void setNombrevendedor(String nombrevendedor) {
        this.nombrevendedor = nombrevendedor;
    }

    public String getTelefonovendedor() {
        return telefonovendedor;
    }

    public void setTelefonovendedor(String telefonovendedor) {
        this.telefonovendedor = telefonovendedor;
    }

}
