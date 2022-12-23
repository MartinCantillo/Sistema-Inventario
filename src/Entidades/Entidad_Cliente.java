/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author marti
 */
public class Entidad_Cliente {

    private int NumCedula;
    private String NombreCliente;
    private int edad;
    private String direccion;
    private String sexo;
    private String correoElectronico;
    private String telefono;

    public Entidad_Cliente(int NumCedula) {
        this.NumCedula = NumCedula;
    }

    public Entidad_Cliente() {
    }

    public Entidad_Cliente(int NumCedula, String NombreCliente, int edad, String direccion, String sexo, String correoElectronico, String telefono) {
        this.NumCedula = NumCedula;
        this.NombreCliente = NombreCliente;
        this.edad = edad;
        this.direccion = direccion;
        this.sexo = sexo;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getNumCedula() {
        return NumCedula;
    }

    public void setNumCedula(int NumCedula) {
        this.NumCedula = NumCedula;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String NombreCliente) {
        this.NombreCliente = NombreCliente;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

}
