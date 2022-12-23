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
public interface FacturavDao {

    public void InsertarCliente(Entidad_Facturav registrarf);

    public void modificar(Entidad_Facturav registrarf);

    public void buscar(Entidad_Facturav registrarf);

    public void Eliminar(Entidad_Facturav1Objeto registrarf);

    public void Mostrar(Entidad_Facturav registrarf);

    public void ExtraerClienteBD(Entidad_Cliente cliente);

    public void ExtraerVendedorBD(Entidad_Vendedor vendedor);


}
