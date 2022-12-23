/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PatronDao;

import Entidades.Entidad_Cliente;
import javax.swing.JTable;

/**
 *
 * @author marti
 */
public interface ClientesDao {
    public  void Insertar( Entidad_Cliente cliente);

    public void modificar(Entidad_Cliente cliente);

    public void buscar(Entidad_Cliente cliente);
    
    public void Eliminar(Entidad_Cliente cliente);
 
     public void MostrarClienteDao(JTable a);
}
