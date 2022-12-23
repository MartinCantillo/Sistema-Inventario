/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementacionDao;

import static Datos.Conexion.close;
import static Datos.Conexion.getConnection;
import Entidades.Entidad_ConsultarRangoFecha;
import PatronDao.ConsultarfacturasfechasDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marti
 */
public class Consultarfacturasfechas_Implement implements ConsultarfacturasfechasDao {

    DefaultTableModel modelo = new DefaultTableModel();

    public void agregar() {
        modelo.addColumn("N°Cliente");
        modelo.addColumn("N° Vendedor");
        modelo.addColumn("Fecha");
        modelo.addColumn("Forma Pago");
        modelo.addColumn("N° Factura");

    }

    @Override
    public void consultar(JTable a, Entidad_ConsultarRangoFecha e) {

        String sql = "select*from facturav   where  fecha BETWEEN ? and ?";
        PreparedStatement st = null;// esto es para ejecutar las sentencias sql
        ResultSet rst = null; // Se utiliza para almacenar los datos que se devuelven desde la tabla de la base de datos después de la ejecución de las sentencias SQL
        Connection conn = null;

        agregar();

        try {

            conn = getConnection();
            st = conn.prepareStatement(sql);
            st.setDate(1, e.getFecha1());
            st.setDate(2, e.getFecha2());
            rst = st.executeQuery();

            ResultSetMetaData metadata = rst.getMetaData(); // para pasarle los datos de la BD a la tabla
            int CantidadColunna = metadata.getColumnCount();  // para saber la cantidad de datos que le devuelve la consulta
            if (rst.next()) { //para ir recorriendo los datos de la consulta
                // ahora recorro la tabla con un vector de objetos y con un for

                Object[] filas = new Object[CantidadColunna];

                for (int i = 0; i < filas.length; i++) {
                    filas[i] = rst.getObject(i + 1); // voy obteniendo cada dato y lo voy guardando en el vector filas
                }
                //ahora que ya tengo los datos lo agrego al modelo
                modelo.addRow(filas);
                a.setModel(modelo);
//                a.setModel(mo);
            }else{
                JOptionPane.showMessageDialog(null, " El registro no se encuentra en la base de datos  ");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rst);
                close(st);
                close(conn);
            } catch (SQLException ex) {
                System.out.println("Error" + ex);
            }
        }
    }
        @Override
    public void EliminarTabla(JTable b) {
        b.setModel(modelo);
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
        }
    }

}
