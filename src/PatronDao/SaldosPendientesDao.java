/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PatronDao;

import Entidades.Entidad_Cliente;
import Entidades.Entidad_SaldosPendientes;
import javax.swing.JTable;

/**
 *
 * @author marti
 */
public interface SaldosPendientesDao {

    public void insertar(Entidad_SaldosPendientes e);

    public void Buscar(Entidad_SaldosPendientes e);

    public void Mostrar(JTable a);

    public void Eliminar(JTable a);
    
    public void buscarsielclienteestaregistrado(Entidad_SaldosPendientes e);
}
