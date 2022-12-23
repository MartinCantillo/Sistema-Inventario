/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementacionDao;

import static Datos.Conexion.close;
import static Datos.Conexion.getConnection;
import Entidades.Entidad_Cliente;
import Entidades.Entidad_Facturav;
import PatronDao.ConsultarVendedoresDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marti
 */
public class ConsultarVendedores_Implement implements ConsultarVendedoresDao {

    DefaultTableModel modelo = new DefaultTableModel();

    public void agregar() {
        modelo.addColumn("N°Factura");
        modelo.addColumn("N° Vendedor");
        modelo.addColumn("Nombre Vendedor");

    }

    @Override
    public void jcommboxClientes(JComboBox cliente) {
        String sql = "SELECT id_cliente FROM facturav";
        PreparedStatement st = null;// ejecutar las sentencias sql
        ResultSet rst = null;
        Connection conn = null;

        try {
            // abro la conexion 
            conn = getConnection();
            //preparo la sentencia
            st = conn.prepareStatement(sql);
            //ejecuto la sentencia
            rst = st.executeQuery();

            //lleno el combobox
            cliente.addItem(0);
            while (rst.next()) {
                cliente.addItem(rst.getInt(1));

            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                // cierro las conexiones

                close(st);
                close(conn);
                close(rst);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    @Override
    public void ExtraerNombreCLiente(Entidad_Cliente e) {

        String sql = "SELECT nombre_cliente FROM clientes where id_cliente=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, e.getNumCedula());
            ResultSet consulta = stmt.executeQuery(); // actualizo el registro

            if (consulta.next()) {

                e.setNombreCliente(consulta.getString("nombre_cliente"));

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                // cierro las conexiones
                close(conn);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
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

    @Override
    public void ExtraerTabla(JTable a, Entidad_Facturav p) {

        String sql = "select idfacturav ,id_vendedor,nombre_vendedor   from facturav f,vendedor v  where  f.id_cliente=? and f.id_vendedor= v.idvendedor  ";
        PreparedStatement st = null;// esto es para ejecutar las sentencias sql
        ResultSet rst = null; // Se utiliza para almacenar los datos que se devuelven desde la tabla de la base de datos después de la ejecución de las sentencias SQL
        Connection conn = null;

        agregar();

        try {

            conn = getConnection();
            st = conn.prepareStatement(sql);
            st.setInt(1, p.getIdcliente());
//            st.setInt(2, p.getIdfactura());
//            st.setInt(2, fv.getIdFactura());
            rst = st.executeQuery();

            ResultSetMetaData metadata = rst.getMetaData(); // para pasarle los datos de la BD a la tabla
            int CantidadColunna = metadata.getColumnCount();  // para saber la cantidad de datos que le devuelve la consulta
            while (rst.next()) { //para ir recorriendo los datos de la consulta
                // ahora recorro la tabla con un vector de objetos y con un for

                Object[] filas = new Object[CantidadColunna];

                for (int i = 0; i < filas.length; i++) {
                    filas[i] = rst.getObject(i + 1); // voy obteniendo cada dato y lo voy guardando en el vector filas
                }
                //ahora que ya tengo los datos lo agrego al modelo
                modelo.addRow(filas);
                a.setModel(modelo);
//                a.setModel(mo);
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

}
