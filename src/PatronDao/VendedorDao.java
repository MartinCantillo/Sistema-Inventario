/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PatronDao;

import Entidades.Entidad_Vendedor;
import javax.swing.JTable;

/**
 *
 * @author marti
 */
public interface VendedorDao {

    public void Insertar(Entidad_Vendedor Vendedor);

    public void modificar(Entidad_Vendedor Vendedor);

    public void buscar(Entidad_Vendedor Vendedor);

    public void Eliminar(Entidad_Vendedor Vendedor);

    public void Mostrar(JTable a);
}
