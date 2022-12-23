/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PatronDao;

import Entidades.Entidad_DetalleFactura;
import Entidades.Entidad_DetalleFacturaObjeto;
import Entidades.Entidad_EntradaProductos;
import Entidades.Entidad_Facturav;
import Entidades.Entidad_Facturav1Objeto;
import Entidades.Entidad_Producto;

/**
 *
 * @author marti
 */
public interface DetalleFacturaDao {
    
    public void InsertarProducto( Entidad_DetalleFactura registrarf);

    public void modificar(  Entidad_DetalleFactura  registrarf);

    public void ExtraerProducto( Entidad_Producto  registrarf);
    
    public void Eliminar(Entidad_DetalleFacturaObjeto e );
 
    public void Mostrar(  Entidad_DetalleFactura registrarf);
    
    public void ExtraerStockIncial(Entidad_DetalleFactura  e);
    
    public void ActualizarStockEntrada(Entidad_DetalleFactura e);
}
