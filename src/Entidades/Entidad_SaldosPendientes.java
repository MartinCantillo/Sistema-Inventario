/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author marti
 */
public class Entidad_SaldosPendientes {

    private int idclientes;
    private String nombrev;
    private int  saldo;

    public Entidad_SaldosPendientes() {
    }

    public Entidad_SaldosPendientes(int idclientes) {
        this.idclientes = idclientes;
    }

    public Entidad_SaldosPendientes(int idclientes, String nombrev, int saldo) {
        this.idclientes = idclientes;
        this.nombrev = nombrev;
        this.saldo = saldo;
    }

    public int getIdclientes() {
        return idclientes;
    }

    public void setIdclientes(int idclientes) {
        this.idclientes = idclientes;
    }

    public String getNombrev() {
        return nombrev;
    }

    public void setNombrev(String nombrev) {
        this.nombrev = nombrev;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

}
