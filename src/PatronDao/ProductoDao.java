/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PatronDao;

import Entidades.Entidad_Producto;
import javax.swing.JTable;

/**
 *
 * @author marti
 */
public interface ProductoDao {

    public void Insertar(Entidad_Producto Producto);

    public void modificar(Entidad_Producto Producto);

    public void buscar(Entidad_Producto Producto);

    public void Eliminar(Entidad_Producto Producto);
    
    public void MostrarProductoDao(JTable a);
}
