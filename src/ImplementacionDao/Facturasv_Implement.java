/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementacionDao;

import static Datos.Conexion.close;
import static Datos.Conexion.getConnection;
import Entidades.Entidad_Cliente;
import Entidades.Entidad_Facturav;
import Entidades.Entidad_Facturav1Objeto;
import Entidades.Entidad_RegistrarFactura;
import Entidades.Entidad_Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JComboBox;
import PatronDao.FacturavDao;
import javax.swing.JOptionPane;

/**
 *
 * @author marti
 */
public class Facturasv_Implement implements FacturavDao {

    private static final String SQL_SELECT = "SELECT*FROM producto where id_producto=?";
    private static final String SQL_INSERT = "INSERT INTO facturav VALUES(?, ? ,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE producto  SET descripcion_producto= ?,categoria_producto= ? , precio_unidad=?  WHERE id_producto= ?";
    private static final String SQL_DELETE = "DELETE FROM  producto  WHERE id_producto = ?";

    public void jcommboxClientes(JComboBox cliente) {
        String sql = "SELECT id_cliente FROM clientes";
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

    public void jcommboxPruducto(JComboBox producto) {
        String sql = "SELECT id_producto FROM producto";
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
            producto.addItem(0);
            while (rst.next()) {
                producto.addItem(rst.getInt(1));

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

    public void jcommboxvendedor(JComboBox vendedor) {
        String sql = "SELECT idvendedor FROM vendedor";
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
            vendedor.addItem(0);

            while (rst.next()) {
                vendedor.addItem(rst.getInt(1));

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
    public void InsertarCliente(Entidad_Facturav registrarf) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, registrarf.getIdcliente());
            stmt.setInt(2, registrarf.getIdvendedor());
            stmt.setDate(3, registrarf.getFecha());
            stmt.setString(4, registrarf.getFormapago());
            stmt.setInt(5, registrarf.getIdfactura());
            stmt.executeUpdate(); // actualizo el registro
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                // cierro las conexiones

                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    @Override
    public void modificar(Entidad_Facturav registrarf) {

    }

    @Override
    public void buscar(Entidad_Facturav registrarf) {

    }

    @Override
    public void Eliminar(Entidad_Facturav1Objeto  rtl) {

        String sql = "delete FROM facturav where idfacturav=?";
        Connection conn = null;
        PreparedStatement eliminar = null;
        try {
            conn = getConnection();
            eliminar = conn.prepareStatement(sql);
            eliminar.setInt(1, rtl.getIdfactura());
            JOptionPane.showMessageDialog(null, "id facturav recibida en bd" + rtl.getIdfactura());
            eliminar.executeUpdate(); // actualizo el registro
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                // cierro las conexiones

                close(eliminar);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
    }

    @Override
    public void Mostrar(Entidad_Facturav registrarf) {

    }

    @Override
    public void ExtraerClienteBD(Entidad_Cliente ExtraerCliente) {
        String sql = "SELECT nombre_cliente FROM clientes where id_cliente=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ExtraerCliente.getNumCedula());

            ResultSet consulta = stmt.executeQuery(); // actualizo el registro

            if (consulta.next()) {

                ExtraerCliente.setNombreCliente(consulta.getString("nombre_cliente"));

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
    public void ExtraerVendedorBD(Entidad_Vendedor Extraervendedor) {
        String sql = "SELECT nombre_vendedor FROM vendedor where idvendedor=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Extraervendedor.getIdvendedor());

            ResultSet consulta = stmt.executeQuery(); // actualizo el registro

            if (consulta.next()) {

                Extraervendedor.setNombrevendedor(consulta.getString("nombre_vendedor"));

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
