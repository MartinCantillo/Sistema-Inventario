/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementacionDao;

import static Datos.Conexion.close;
import static Datos.Conexion.getConnection;
import Entidades.Entidad_Producto;
import PatronDao.ProductoDao;
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
public class Producto_Implement implements ProductoDao {

    private static final String SQL_SELECT = "SELECT*FROM producto where id_producto=?";
    private static final String SQL_INSERT = "INSERT INTO producto VALUES(?, ? ,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE producto  SET descripcion_producto= ?,categoria_producto= ? , precio_unidad=? , existencias_iniciales=?  WHERE id_producto= ?";
    private static final String SQL_DELETE = "DELETE FROM  producto  WHERE id_producto = ?";
    DefaultTableModel modelo = new DefaultTableModel();

    public void agregar() {

        modelo.addColumn("N° Producto");
        modelo.addColumn("Descipcion del Producto");
        modelo.addColumn("Categoria del Producto");
        modelo.addColumn("Precio Por unidad ");
        modelo.addColumn("Existencias iniciales");
    }

    @Override
    public void Insertar(Entidad_Producto Producto) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, Producto.getIdProducto());
            stmt.setString(2, Producto.getDescripcion());
            stmt.setString(3, Producto.getCategoria());
            stmt.setInt(4, Producto.getPrecio());
            stmt.setInt(5, Producto.getExistencias_iniciales());
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
    public void modificar(Entidad_Producto Producto) {
        Connection conn = null;
        PreparedStatement modificar = null;
        try {
            conn = getConnection();
            modificar = conn.prepareStatement(SQL_UPDATE);
            modificar.setString(1, Producto.getDescripcion());
            modificar.setString(2, Producto.getCategoria());
            modificar.setInt(3, Producto.getPrecio());
            modificar.setInt(4, Producto.getExistencias_iniciales());
            modificar.setInt(5, Producto.getIdProducto());

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

    @Override
    public void buscar(Entidad_Producto Producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, Producto.getIdProducto());

            ResultSet consulta = stmt.executeQuery(); // actualizo el registro

            if (consulta.next()) {
                Producto.setIdProducto(Integer.parseInt(consulta.getString("id_producto")));
                Producto.setDescripcion(consulta.getString("descripcion_producto"));
                Producto.setCategoria(consulta.getString("categoria_producto"));
                Producto.setPrecio(Integer.parseInt(consulta.getString("precio_unidad")));
                Producto.setExistencias_iniciales(Integer.parseInt(consulta.getString("existencias_iniciales")));

            } else {
                JOptionPane.showMessageDialog(null, " El registro no se encuentra en la Base de datos ");
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

    @Override
    public void Eliminar(Entidad_Producto Producto) {
        Connection conn = null;
        PreparedStatement eliminar = null;
        try {
            conn = getConnection();
            eliminar = conn.prepareStatement(SQL_DELETE);
            eliminar.setInt(1, Producto.getIdProducto());
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
    public void MostrarProductoDao(JTable a) {
        String sql = "SELECT*FROM producto";
        PreparedStatement st = null;// ejecutar las sentencias sql
        ResultSet rst = null; // Se utiliza para almacenar los datos que se devuelven desde la tabla de la base de datos después de la ejecución de las sentencias SQL
        Connection conn = null;
        agregar();

        try {
            conn = getConnection();
            st = conn.prepareStatement(sql);
            rst = st.executeQuery();
            ResultSetMetaData metadata = rst.getMetaData(); // le paso los datos de la BD a la tabla
            int CantidadColunna = metadata.getColumnCount();  // para saber la cantidad de datos que le devuelve la consulta
            while (rst.next()) { //para ir recorriendo los datos de la consulta
                //  recorro la tabla con un vector de objetos y con un for

                Object[] filas = new Object[CantidadColunna];

                for (int i = 0; i < filas.length; i++) {
                    filas[i] = rst.getObject(i + 1); // voy obteniendo cada dato y lo voy guardando en el vector filas
                }
                //ahora que ya tengo los datos lo agrego al modelo
                modelo.addRow(filas);
                a.setModel(modelo);
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
