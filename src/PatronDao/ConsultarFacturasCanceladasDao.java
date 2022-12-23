/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PatronDao;

import Entidades.Entidad_FacturasCanceladas;
import javax.swing.JTable;

/**
 *
 * @author marti
 */
public interface ConsultarFacturasCanceladasDao {

    public void registrar(Entidad_FacturasCanceladas r);

    public void Mostrar(JTable a);
}
