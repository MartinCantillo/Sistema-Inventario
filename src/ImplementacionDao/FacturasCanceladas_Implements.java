/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementacionDao;

import static Datos.Conexion.close;
import static Datos.Conexion.getConnection;
import Entidades.Entidad_FacturasCanceladas;
import PatronDao.ConsultarFacturasCanceladasDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marti
 */
public class FacturasCanceladas_Implements implements ConsultarFacturasCanceladasDao {

    private static final String SQL_INSERT = "INSERT INTO  facturasdevueltas VALUES(?, ?, ?,?,?)";

    DefaultTableModel modelo = new DefaultTableModel();

    public void agregar() {
        modelo.addColumn("N°Factura");
        modelo.addColumn("Fecha ");
        modelo.addColumn("N° Cliente");
        modelo.addColumn("N° Producto");
        modelo.addColumn("Cantidad");

    }

    @Override
    public void registrar(Entidad_FacturasCanceladas r) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, r.getIdfactura());
            stmt.setDate(2, r.getFecha());
            stmt.setInt(3, r.getIdcliente());
            stmt.setInt(4, r.getIdproucto());
            stmt.setInt(5, r.getCantidad());
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
    public void Mostrar(JTable a) {
        String sql = "select*from facturasdevueltas";
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
