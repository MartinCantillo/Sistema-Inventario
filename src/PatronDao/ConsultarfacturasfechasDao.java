/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PatronDao;

import Entidades.Entidad_ConsultarRangoFecha;
import javax.swing.JTable;

/**
 *
 * @author marti
 */
public interface ConsultarfacturasfechasDao {
    
    public void consultar(JTable a ,Entidad_ConsultarRangoFecha e);
    public void EliminarTabla(JTable b);
}
