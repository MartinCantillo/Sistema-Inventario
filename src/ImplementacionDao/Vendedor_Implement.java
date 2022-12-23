/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementacionDao;

import static Datos.Conexion.close;
import static Datos.Conexion.getConnection;
import Entidades.Entidad_Vendedor;
import PatronDao.VendedorDao;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marti
 */
public class Vendedor_Implement implements VendedorDao {

    private static final String SQL_SELECT = "SELECT*FROM vendedor where id_vendedor=?";
    private static final String SQL_INSERT = "INSERT INTO  vendedor VALUES(?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE vendedor  SET nombre_vendedor= ?,telefono_vendedor= ?  WHERE id_vendedor = ?";
    private static final String SQL_DELETE = "DELETE FROM  vendedor  WHERE id_vendedor= ?";
    DefaultTableModel modelo = new DefaultTableModel();

    public void agregar() {

        modelo.addColumn("N° Vendedor");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono ");
    }

    @Override
    public void Insertar(Entidad_Vendedor Vendedor) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, Vendedor.getIdvendedor());
            stmt.setString(2, Vendedor.getNombrevendedor());
            stmt.setString(3, Vendedor.getTelefonovendedor());
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
    public void modificar(Entidad_Vendedor Vendedor) {
        Connection conn = null;
        PreparedStatement modificar = null;
        try {
            conn = getConnection();
            modificar = conn.prepareStatement(SQL_UPDATE);
            modificar.setString(1, Vendedor.getNombrevendedor());
            modificar.setString(2, Vendedor.getTelefonovendedor());
            modificar.setInt(3, Vendedor.getIdvendedor());
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
    public void buscar(Entidad_Vendedor Vendedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, Vendedor.getIdvendedor());

            ResultSet consulta = stmt.executeQuery(); // actualizo el registro

            if (consulta.next()) {
                Vendedor.setIdvendedor(Integer.parseInt(consulta.getString("id_vendedor")));
                Vendedor.setNombrevendedor(consulta.getString("nombre_vendedor"));
                Vendedor.setTelefonovendedor(consulta.getString("telefono_vendedor"));
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
    public void Eliminar(Entidad_Vendedor Vendedor) {
        Connection conn = null;
        PreparedStatement eliminar = null;
        try {
            conn = getConnection();
            eliminar = conn.prepareStatement(SQL_DELETE);
            eliminar.setInt(1, Vendedor.getIdvendedor());
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
    public void Mostrar(JTable a) {
        String sql = "SELECT*FROM  Vendedor";
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
