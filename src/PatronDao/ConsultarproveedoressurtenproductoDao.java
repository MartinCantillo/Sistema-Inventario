/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PatronDao;

import Entidades.Entidad_EntradaProductos;
import Entidades.Entidad_Producto;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author marti
 */
public interface ConsultarproveedoressurtenproductoDao {
     public void jcommboxPruducto(JComboBox producto);
       public void ExtraerProductoBD(Entidad_Producto producto) ;
        public void EliminarTabla(JTable b);
        public void Mostrar(JTable a , Entidad_EntradaProductos e);
}
