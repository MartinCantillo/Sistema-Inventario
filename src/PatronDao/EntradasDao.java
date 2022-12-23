/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PatronDao;

import Entidades.*;
import javax.swing.JTable;

/**
 *
 * @author marti
 */
public interface EntradasDao {

    public void Insertar(Entidad_EntradaProductos entrada);

    public void modificar(Entidad_EntradaProductos entrada);

    public void buscar(Entidad_EntradaProductos entrada);

    public void Eliminar(Entidad_EntradaProductos entrada);

    public void ExtraerProductoBD(Entidad_Producto producto);

    public void ExtraerProveedorBD(Entidad_Proveedor proveedor);
    
    public void ExtraerExistenciasBD(Entidad_Producto p);
    
      public void ExtraerStockdeFacturayActualizar(Entidad_EntradaProductos e);
 
    public void Mostrar(JTable table);
    public void EliminarTabla(JTable b);
}
