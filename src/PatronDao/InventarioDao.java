/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PatronDao;

import Entidades.Entidad_EntradaProductos;
import Entidades.Entidad_Producto;

/**
 *
 * @author marti
 */
public interface InventarioDao {
    
    public void ExtraerPorudctoycantidad(Entidad_EntradaProductos p);
    public void ExtraerProducto(Entidad_Producto producto);
    public void extraer();
    
}
