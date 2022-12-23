/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PatronDao;

import Entidades.Entidad_Cliente;
import Entidades.Entidad_Facturav;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author marti
 */
public interface ConsultarVendedoresDao {

    public void jcommboxClientes(JComboBox cliente);

    public void ExtraerNombreCLiente(Entidad_Cliente e);

    public void EliminarTabla(JTable b);

    public void ExtraerTabla(JTable a, Entidad_Facturav p);
}
