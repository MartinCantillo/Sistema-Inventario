/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementacionDao;

import static Datos.Conexion.close;
import static Datos.Conexion.getConnection;
import Entidades.Entidad_Proveedor;
import PatronDao.ProveedorDao;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marti
 */
public class Proveedorimplement implements ProveedorDao {

    private static final String SQL_SELECT = "SELECT*FROM proveedor  where idproveedor =?";
    private static final String SQL_INSERT = "INSERT INTO  proveedor VALUES(?, ?, ?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE proveedor SET nombre_proveedor= ?,pagina_web= ? , telefono_proveedor = ? ,direccion_proveedor= ?,correo_electronico_proveedor= ? WHERE id_proveedor = ?";
    private static final String SQL_DELETE = "DELETE FROM proveedor  WHERE id_proveedor= ?";
    DefaultTableModel modelo = new DefaultTableModel();

    public void agregar() {

        modelo.addColumn("N°Proveedor");
        modelo.addColumn("Nombre");
        modelo.addColumn("Pagina web ");
        modelo.addColumn("Direccion");
        modelo.addColumn("Telefono");
        modelo.addColumn("CorreoElectronico");
    }

    @Override
    public void Insertar(Entidad_Proveedor Proveedor) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, Proveedor.getIdProveedor());
            stmt.setString(2, Proveedor.getNombreProveedor());
            stmt.setString(3, Proveedor.getPaginaweb());
            stmt.setString(4, Proveedor.getDireccionProveedor());
            stmt.setString(5, Proveedor.getTelefonoProveedor());
            stmt.setString(6, Proveedor.getCorreoElectronico());
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
    public void modificar(Entidad_Proveedor Proveedor) {
        Connection conn = null;
        PreparedStatement modificar = null;
        try {
            conn = getConnection();
            modificar = conn.prepareStatement(SQL_UPDATE);
            modificar.setString(1, Proveedor.getNombreProveedor());
            modificar.setString(2, Proveedor.getPaginaweb());
            modificar.setString(3, Proveedor.getTelefonoProveedor());
            modificar.setString(4, Proveedor.getDireccionProveedor());
            modificar.setString(5, Proveedor.getCorreoElectronico());
            modificar.setInt(6, Proveedor.getIdProveedor());
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
    public void buscar(Entidad_Proveedor Proveedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, Proveedor.getIdProveedor());

            ResultSet consulta = stmt.executeQuery(); // actualizo el registro

            if (consulta.next()) {
                Proveedor.setIdProveedor(Integer.parseInt(consulta.getString("idproveedor")));
                Proveedor.setNombreProveedor(consulta.getString("nombre_proveedor"));
                Proveedor.setPaginaweb(consulta.getString("pagina_web"));
                Proveedor.setTelefonoProveedor(consulta.getString("telefono_proveedor"));
                Proveedor.setDireccionProveedor(consulta.getString("direccion_proveedor"));
                Proveedor.setCorreoElectronico(consulta.getString("correo_electronico_proveedor"));
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
    public void Eliminar(Entidad_Proveedor Proveedor) {
        Connection conn = null;
        PreparedStatement eliminar = null;
        try {
            conn = getConnection();
            eliminar = conn.prepareStatement(SQL_DELETE);
            eliminar.setInt(1, Proveedor.getIdProveedor());
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
    public void MostrarProveedorDao(JTable a) {
        String sql = "SELECT*FROM proveedor";
        PreparedStatement st = null;// ejecutar las sentencias sql
        ResultSet rst = null; // Se utiliza para almacenar los datos que se devuelven desde la tabla de la base de datos después de la ejecución de las sentencias SQL
        Connection conn = null;

        try {
            agregar();
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
