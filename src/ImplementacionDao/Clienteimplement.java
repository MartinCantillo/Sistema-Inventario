/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementacionDao;

import static Datos.Conexion.close;
import static Datos.Conexion.getConnection;
import Entidades.Entidad_Cliente;
import Interfaces.RegistrarClientes;
import PatronDao.*;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marti
 */
public class Clienteimplement implements ClientesDao {

    private static final String SQL_SELECT = "SELECT*FROM  clientes where id_cliente =?";
    private static final String SQL_INSERT = "INSERT INTO  clientes VALUES(?, ?, ?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE clientes SET  nombre_cliente = ?,edad_cliente= ? , direccion= ? ,sexo= ?,telefono= ?, correo_electronico= ?WHERE id_cliente =?";
    private static final String SQL_DELETE = "DELETE FROM clientes WHERE id_cliente = ?";
    DefaultTableModel modelo = new DefaultTableModel();

    public void agregar() {
        modelo.addColumn("N°Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Edad");
        modelo.addColumn("Direccion");
        modelo.addColumn("sexo");
        modelo.addColumn("Telefono");
        modelo.addColumn("CorreoElectronico");
        modelo.addColumn("Telefono");
    }

    @Override
    public void Insertar(Entidad_Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, cliente.getNumCedula());
            stmt.setString(2, cliente.getNombreCliente());
            stmt.setInt(3, cliente.getEdad());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getSexo());
            stmt.setString(6, cliente.getTelefono());
            stmt.setString(7, cliente.getCorreoElectronico());
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
    public void modificar(Entidad_Cliente cliente) {

        Connection conn = null;
        PreparedStatement modificar = null;
        try {
            conn = getConnection();
            modificar = conn.prepareStatement(SQL_UPDATE);
//            modificar.setInt(1, cliente.getNumCedula());
            modificar.setString(1, cliente.getNombreCliente());
            modificar.setInt(2, cliente.getEdad());
            modificar.setString(3, cliente.getDireccion());
            modificar.setString(4, cliente.getSexo());
            modificar.setString(5, cliente.getTelefono());
            modificar.setString(6, cliente.getCorreoElectronico());
            modificar.setInt(7, cliente.getNumCedula());
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
    public void buscar(Entidad_Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, cliente.getNumCedula());

            var consulta = stmt.executeQuery(); // actualizo el registro

            if (consulta.next()) {
                cliente.setNumCedula(Integer.parseInt(consulta.getString("id_cliente")));
                cliente.setNombreCliente(consulta.getString("nombre_cliente"));
                cliente.setEdad(Integer.parseInt(consulta.getString("edad_cliente")));
                cliente.setDireccion(consulta.getString("direccion"));
                cliente.setSexo(consulta.getString("sexo"));
                cliente.setTelefono(consulta.getString("telefono"));
                cliente.setCorreoElectronico(consulta.getString("correo_electronico"));

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
    public void Eliminar(Entidad_Cliente cliente) {
        Connection conn = null;
        PreparedStatement eliminar = null;
        try {
            conn = getConnection();
            eliminar = conn.prepareStatement(SQL_DELETE);
            eliminar.setInt(1, cliente.getNumCedula());
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
    public void MostrarClienteDao(JTable a) {
        String sql = "SELECT*FROM  clientes";
        PreparedStatement st = null;// esto es para ejecutar las sentencias sql
        ResultSet rst = null; // Se utiliza para almacenar los datos que se devuelven desde la tabla de la base de datos después de la ejecución de las sentencias SQL
        Connection conn = null;
        agregar();

        try {
            conn = getConnection();
            st = conn.prepareStatement(sql);
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
