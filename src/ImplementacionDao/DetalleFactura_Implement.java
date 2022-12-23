/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementacionDao;

import static Datos.Conexion.close;
import static Datos.Conexion.getConnection;
import Entidades.Entidad_Cliente;
import Entidades.Entidad_DetalleFactura;
import Entidades.Entidad_DetalleFacturaObjeto;
import Entidades.Entidad_EntradaProductos;
import Entidades.Entidad_Facturav;
import Entidades.Entidad_Facturav1Objeto;
import Entidades.Entidad_Producto;
import PatronDao.DetalleFacturaDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author marti
 */
public class DetalleFactura_Implement implements DetalleFacturaDao {

    private static final String SQL_SELECT = "SELECT descripcion_producto , precio_unidad FROM producto  where id_producto =?";
    private static final String SQL_INSERT = "INSERT INTO  detallefactura VALUES(?, ?,?, ?,?,?)";
    private static final String SQL_UPDATE = "UPDATE proveedor SET nombre_proveedor= ?,pagina_web= ? , telefono_proveedor = ? ,direccion_proveedor= ?,correo_electronico_proveedor= ? WHERE id_proveedor = ?";
    private static final String SQL_DELETE = "DELETE FROM proveedor  WHERE id_proveedor= ?";

    @Override
    public void InsertarProducto(Entidad_DetalleFactura Producto) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, Producto.getIdFactura());
            stmt.setInt(2, Producto.getIdProducto());
            stmt.setString(3, Producto.getIdDescripcion());
            stmt.setInt(4, Producto.getCantidad());
            stmt.setInt(5, Producto.getPrecio());
            stmt.setInt(6, Producto.getStocfinal());
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
    public void modificar(Entidad_DetalleFactura registrarf) {

    }

    @Override
    public void ExtraerProducto(Entidad_Producto Extraer) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, Extraer.getIdProducto());

            ResultSet consulta = stmt.executeQuery(); // actualizo el registro

            if (consulta.next()) {

                Extraer.setDescripcion(consulta.getString("descripcion_producto"));
                Extraer.setPrecio(consulta.getInt("precio_unidad"));

            }
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

    public void ExtraerCliente(Entidad_Cliente ExtraerCliente) {
//        String sql = "SELECT nombre_cliente FROM producto";
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        try {
//
//            conn = getConnection();
//            stmt = conn.prepareStatement(sql);
//            stmt.setInt(1, ExtraerCliente.getNumCedula());
//
//            ResultSet consulta = stmt.executeQuery(); // actualizo el registro
//
//            if (consulta.next()) {
//
//                ExtraerCliente.setNombreCliente(consulta.getString("descripcion_producto"));
//
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace(System.out);
//        } finally {
//            try {
//                // cierro las conexiones
//
//                close(stmt);
//                close(conn);
//            } catch (SQLException ex) {
//                ex.printStackTrace(System.out);
//            }
//        }
    }

    @Override
    public void Eliminar(Entidad_DetalleFacturaObjeto  eliminart) {
        String sql = "delete FROM detallefactura where idFactura=?";
        Connection conn = null;
        PreparedStatement eliminar = null;
        try {
            conn = getConnection();
            eliminar = conn.prepareStatement(sql);
            eliminar.setInt(1, eliminart.getIdFactura());
            JOptionPane.showMessageDialog(null, "id  detalle factura recibida en bd id " + eliminart.getIdFactura());
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
    public void Mostrar(Entidad_DetalleFactura registrarf) {

    }

    @Override
    public void ExtraerStockIncial(Entidad_DetalleFactura e) {
        String sql = "SELECT actualizarstock FROM prove_producto where idproducto=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, e.getIdProducto());
            ResultSet consulta = stmt.executeQuery(); // actualizo el registro

            if (consulta.next()) {

                e.setStock(Integer.parseInt(consulta.getString("actualizarstock")));

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
    public void ActualizarStockEntrada(Entidad_DetalleFactura e) {
        String actualizar = "UPDATE prove_producto SET actualizarstock= ?  WHERE idproducto = ?";

        Connection conn = null;
        PreparedStatement modificar = null;
        try {
            conn = getConnection();
            modificar = conn.prepareStatement(actualizar);
            modificar.setInt(1, e.getStocfinal());
            modificar.setInt(2, e.getIdProducto());

            modificar.executeUpdate(); // actualizo el registro
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                // cierro las conexiones

                close(modificar);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }

    }

}
