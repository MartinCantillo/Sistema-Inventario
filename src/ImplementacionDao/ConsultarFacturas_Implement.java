/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementacionDao;

import static Datos.Conexion.close;
import static Datos.Conexion.getConnection;
import Entidades.Entidad_Cliente;
import Entidades.Entidad_Facturav;
import PatronDao.ConsultarFacturasDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author marti
 */
public class ConsultarFacturas_Implement implements ConsultarFacturasDao {

    public void ExtraerClienteJcombobox(JComboBox cliente) {

        String sql = "SELECT id_cliente FROM clientes ";
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
    public void ExtraerCLienteBD(Entidad_Cliente cliente) {

        String sql = "SELECT nombre_cliente FROM clientes where id_cliente=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cliente.getNumCedula());

            ResultSet consulta = stmt.executeQuery(); // actualizo el registro

            if (consulta.next()) {

                cliente.setNombreCliente(consulta.getString("nombre_cliente"));

            } else {

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

}
