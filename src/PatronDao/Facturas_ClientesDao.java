/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PatronDao;

import Entidades.Entidad_Cliente;
import Entidades.Entidad_DetalleFactura;
import Entidades.Entidad_Facturav;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marti
 */
public interface Facturas_ClientesDao {

    public void ExtraerFactura(Entidad_Facturav e);

    public void ExtraerDetallesFactura();

    public void ExtraerTabla(JTable a, Entidad_Facturav  p ,Entidad_DetalleFactura fv);

    public void ExtraerCliente(Entidad_Facturav e);

    public void ExtraerNombreCLiente(Entidad_Cliente e);

    public void jcommboxClientes(JComboBox cliente);
    
    public void EliminarTabla(JTable b);
}
