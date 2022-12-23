/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementacionDao;

import static Datos.Conexion.close;
import static Datos.Conexion.getConnection;
import Entidades.Entidad_EntradaProductos;
import Entidades.Entidad_Producto;
import PatronDao.ConsultarproveedoressurtenproductoDao;
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
public class Consultarproveedoressurtenproducto_Implement implements ConsultarproveedoressurtenproductoDao {

    
      DefaultTableModel modelo = new DefaultTableModel();

    public void agregar() {
        modelo.addColumn("N°Producto");
        modelo.addColumn("N° Proveedor");
        modelo.addColumn("Cantidad");
        

    }

    @Override
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

    @Override
    public void ExtraerProductoBD(Entidad_Producto producto) {
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
    public void EliminarTabla(JTable b) {
        b.setModel(modelo);
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
        }
    }
    
    @Override
    public void Mostrar(JTable a, Entidad_EntradaProductos e) {
        String sql = "SELECT  idproducto,  idproveedor, cantidad_productos  FROM   prove_producto   , proveedor ;";
        PreparedStatement st = null;// ejecutar las sentencias sql
        ResultSet rst = null; // Se utiliza para almacenar los datos que se devuelven desde la tabla de la base de datos después de la ejecución de las sentencias SQL
        Connection conn = null;
        agregar();

        try {
            conn = getConnection();
            st = conn.prepareStatement(sql);
           //st.setInt(1, e.getIdproducto());
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
