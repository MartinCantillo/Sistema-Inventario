/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PatronDao;

import Entidades.Entidad_Proveedor;
import javax.swing.JTable;

/**
 *
 * @author marti
 */
public interface ProveedorDao {

    public void Insertar( Entidad_Proveedor   Proveedor );
     
    public void modificar(Entidad_Proveedor   Proveedor );

    public void buscar(Entidad_Proveedor   Proveedor );

    public void Eliminar(Entidad_Proveedor  Proveedor );

    public void MostrarProveedorDao(JTable a);
}
