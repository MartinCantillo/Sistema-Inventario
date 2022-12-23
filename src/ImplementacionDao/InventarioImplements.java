/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementacionDao;

import static Datos.Conexion.close;
import static Datos.Conexion.getConnection;
import Entidades.Entidad_EntradaProductos;
import Entidades.Entidad_Producto;
import PatronDao.InventarioDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marti
 */
public class InventarioImplements implements InventarioDao{

    private static final String SQL_SELECT = "SELECT*FROM prove_producto where num_entrada=?";
    private static final String SQL_INSERT = "INSERT INTO prove_producto VALUES(?, ? ,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE prove_producto  SET id_producto= ?,id_proveedor= ? , cantidad_productos=? , fecha_registro=?  WHERE num_entrada= ?";
    private static final String SQL_DELETE = "DELETE FROM  prove_producto  WHERE num_entrada = ?";

    DefaultTableModel modelo = new DefaultTableModel();

    @Override
    public void ExtraerPorudctoycantidad(Entidad_EntradaProductos p) {
        String sql = "SELECT id_producto,cantidad_productos FROM prove_producto where id_producto=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, p.getIdproducto());
            stmt.setInt(2, p.getCantidad());

            ResultSet consulta = stmt.executeQuery(); // actualizo el registro

            if (consulta.next()) {
                p.setIdproducto(consulta.getInt("id_producto"));
                p.setCantidad(consulta.getInt("cantidad_productos"));

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
    public void ExtraerProducto(Entidad_Producto producto) {
        String sql = "SELECT descripcion_producto FROM producto where id_producto=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, producto.getIdProducto());

            ResultSet consulta = stmt.executeQuery(); // actualizo el registro

            if (consulta.next()) {

                producto.setDescripcion(consulta.getString("descripcion_producto"));

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
    public void extraer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
