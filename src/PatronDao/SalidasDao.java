/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PatronDao;

import Entidades.*;

/**
 *
 * @author marti
 */
public interface SalidasDao {
      public void Insertar( Entidad_RegistrarFactura Compras);

    public void modificar( Entidad_RegistrarFactura Compras);

    public void buscar( Entidad_RegistrarFactura Compras);
    
    public void Eliminar( Entidad_RegistrarFactura Compras);
 
    public void Mostrar( Entidad_RegistrarFactura Compras);
}
