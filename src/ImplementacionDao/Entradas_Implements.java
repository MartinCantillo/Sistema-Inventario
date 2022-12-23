/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementacionDao;

import static Datos.Conexion.close;
import static Datos.Conexion.getConnection;
import Entidades.Entidad_DetalleFactura;
import Entidades.Entidad_EntradaProductos;
import Entidades.Entidad_Producto;
import Entidades.Entidad_Proveedor;
import PatronDao.EntradasDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marti
 */
public class Entradas_Implements implements EntradasDao {

    private static final String SQL_SELECT = "SELECT*FROM prove_producto where num_entrada=?";
    private static final String SQL_INSERT = "INSERT INTO prove_producto VALUES(?, ? ,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE prove_producto  SET id_producto= ?,id_proveedor= ? , cantidad_productos=? , fecha_registro=?,entradas=?, actualizarstock=? WHERE num_entrada= ?";
    private static final String SQL_DELETE = "DELETE FROM  prove_producto  WHERE num_entrada = ?";

    DefaultTableModel modelo = new DefaultTableModel();

    public void agregar() {

        modelo.addColumn("N°Producto");
        modelo.addColumn("N°Proveedor");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Fecha Registro ");
        modelo.addColumn("N°Entrada");
        modelo.addColumn("Entradas Producto");
        modelo.addColumn("Stock");
    }

    public void jcommboxProveedor(JComboBox Proveedor) {
        String sql = "SELECT id_proveedor FROM proveedor";
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
            Proveedor.addItem(0);
            while (rst.next()) {
                Proveedor.addItem(rst.getInt(1));

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

    @Override
    public void Insertar(Entidad_EntradaProductos entrada) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, entrada.getIdproducto());
            stmt.setInt(2, entrada.getIdproveedor());
            stmt.setInt(3, entrada.getCantidad());
            stmt.setDate(4, entrada.getFecha());
            stmt.setInt(5, entrada.getNumEntrada());
            stmt.setInt(6, entrada.getStock());
            stmt.setInt(7, entrada.getStock());
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
    public void modificar(Entidad_EntradaProductos ModificarE) {

        Connection conn = null;
        PreparedStatement modificar = null;
        try {
            conn = getConnection();
            modificar = conn.prepareStatement(SQL_UPDATE);
            modificar.setInt(1, ModificarE.getIdproducto());
            modificar.setInt(2, ModificarE.getIdproveedor());
            modificar.setInt(3, ModificarE.getCantidad());
            modificar.setDate(4, ModificarE.getFecha());
            modificar.setInt(5, ModificarE.getStock());
            modificar.setInt(6, ModificarE.getStock());
            modificar.setInt(7, ModificarE.getNumEntrada());

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
    public void buscar(Entidad_EntradaProductos entrada) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, entrada.getNumEntrada());

            ResultSet consulta = stmt.executeQuery(); // actualizo el registro

            if (consulta.next()) {
                entrada.setIdproducto(Integer.parseInt(consulta.getString("id_producto")));
                entrada.setIdproveedor(Integer.parseInt(consulta.getString("idproveedor")));
                entrada.setCantidad(Integer.parseInt(consulta.getString("cantidad_productos")));
                entrada.setFecha(consulta.getDate(4));// le paso el numero de la colunna
                entrada.setStock(Integer.parseInt(consulta.getString("entradas")));
                entrada.setNumEntrada(Integer.parseInt(consulta.getString("num_entrada")));
            } else {
                JOptionPane.showMessageDialog(null, " No existe el N° entrada en la base de datos ");
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
    public void Eliminar(Entidad_EntradaProductos entrada) {
        Connection conn = null;
        PreparedStatement eliminar = null;
        try {
            conn = getConnection();
            eliminar = conn.prepareStatement(SQL_DELETE);
            eliminar.setInt(1, entrada.getNumEntrada());
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
    public void ExtraerProveedorBD(Entidad_Proveedor proveedor) {
        String sql = "SELECT nombre_proveedor FROM proveedor where id_proveedor=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, proveedor.getIdProveedor());

            ResultSet consulta = stmt.executeQuery(); // actualizo el registro

            if (consulta.next()) {

                proveedor.setNombreProveedor(consulta.getString("nombre_proveedor"));

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
    public void Mostrar(JTable a) {

        String sql = "SELECT*FROM  prove_producto";
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

    @Override
    public void ExtraerExistenciasBD(Entidad_Producto p) {
        String sql = "SELECT existencias_iniciales FROM producto where id_producto=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, p.getIdProducto());

            ResultSet consulta = stmt.executeQuery(); // actualizo el registro

            if (consulta.next()) {

                p.setExistencias_iniciales(Integer.parseInt(consulta.getString("existencias_iniciales")));

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
    public void ExtraerStockdeFacturayActualizar(Entidad_EntradaProductos e) {

        String sql = "SELECT stockfinal FROM detallefactura where idproductof=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, e.getIdproducto());

            ResultSet consulta = stmt.executeQuery(); // actualizo el registro

            if (consulta.next()) {

                e.setObtenerstockfactura(Integer.parseInt(consulta.getString("cantidad")));

            }
//            JOptionPane.showMessageDialog(null, "cantidad (salida) extraida" + e.getObtenerstockfactura() + " id producto" + +e.getIdproducto());
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

}
