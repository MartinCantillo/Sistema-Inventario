/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import Entidades.Entidad_EntradaProductos;
import Entidades.Entidad_Producto;
import Entidades.Entidad_Proveedor;
import ImplementacionDao.Entradas_Implements;
import PatronDao.EntradasDao;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marti
 */
public class Entradas extends javax.swing.JFrame {

    /**
     * Creates new form Entradas
     */
    // cargo ahora el combobox
    Entradas_Implements cargarcombobox = new Entradas_Implements();
    DefaultTableModel modelo = new DefaultTableModel();

    public Entradas() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cargarcombobox.jcommboxProveedor(this.jComboBox3Proveedor);
        cargarcombobox.jcommboxPruducto(jComboBox3Producto);
        String titulo[] = new String[]{"N°Producto", "N°Proveedor", "Cantidad", "Fecha Ingreso", "N° Entrada", "Stock"};
        modelo.setColumnIdentifiers(titulo);
        this.setTitle("Entrada Productos");
        Mostrar();
        this.jTextField8Stock.setEditable(false);
        this.jTable1Tabla.setEnabled(false);

    }

    public void Mostrar() {
        //Instancio para mostrar los datos de la bd en el jtable
        Entradas_Implements Mostrar = new Entradas_Implements();
        Mostrar.Mostrar(jTable1Tabla);
    }

    public void limpiar() {
        this.jTextField4numentrada.setText("");
        this.jDateChooser1fechaderegistro.setDate(null);
        this.jTextField8cantidad.setText("");
        this.jComboBox3Producto.setSelectedIndex(0);
        this.jComboBox3Proveedor.setSelectedIndex(0);
        this.jTextField8Stock.setText("");
    }

    public int actualizarStock() {
        int idproducto = Integer.parseInt(this.jComboBox3Producto.getSelectedItem().toString());
        int operacion = 0, entradas = 0;
        entradas = consultarStock();
        EntradasDao h = new Entradas_Implements();
        Entidad_EntradaProductos j = new Entidad_EntradaProductos(idproducto);
        int operacionstock = (entradas - j.getObtenerstockfactura());
        //JOptionPane.showMessageDialog(null,"stock extraido de la factura + el de la entrada"+operacionstock + " metodo actualizar stock");
        h.ExtraerStockdeFacturayActualizar(j);

        operacion = (operacionstock - j.getObtenerstockfactura());
        j.setOperacionstock(operacion);
        return operacion;
    }

    public void ExtraerProductodelaBD() {

        int idproducto = Integer.parseInt(this.jComboBox3Producto.getSelectedItem().toString());
        EntradasDao ExtraerDao = new Entradas_Implements();
        Entidad_Producto EntidadExtraerProducto = new Entidad_Producto(idproducto);
        ExtraerDao.ExtraerProductoBD(EntidadExtraerProducto);

        //ahora lo que haya extraido de la bd paso al jtexfiel
        this.jTextField8NombreProducto.setText(String.valueOf(EntidadExtraerProducto.getDescripcion()));

    }

    public void ExtraerProveedordelaBD() {

        int idproveedor = Integer.parseInt(this.jComboBox3Proveedor.getSelectedItem().toString());
        EntradasDao ExtraerDao = new Entradas_Implements();
        Entidad_Proveedor EntidadProveedor = new Entidad_Proveedor(idproveedor);
        ExtraerDao.ExtraerProveedorBD(EntidadProveedor);

        //ahora lo que haya extraido de la bd paso al jtexfiel
        this.jTextField8NombreProveedor.setText(String.valueOf(EntidadProveedor.getNombreProveedor()));

    }

    public void Deshabilitarjtexfielnombres() {
        this.jTextField8NombreProducto.setEditable(false);
        this.jTextField8NombreProveedor.setEditable(false);
    }

    public java.sql.Date convertirfechaamsql(Date fech) {
        fech = this.jDateChooser1fechaderegistro.getDate();
        long d = fech.getTime();
        java.sql.Date f = new java.sql.Date(d);
        return f;
    }

    public void agregar() {
        Date fech = this.jDateChooser1fechaderegistro.getDate();
        java.sql.Date dato = convertirfechaamsql(fech);
        modelo.addRow(new Object[]{
            this.jComboBox3Producto.getSelectedItem().toString(), this.jComboBox3Proveedor.getSelectedItem().toString(), this.jTextField8cantidad.getText(),
            dato, this.jTextField4numentrada.getText(), this.jTextField8Stock.getText()});
        this.jTable1Tabla.setModel(modelo);
    }

    public int consultarStock() {
        int existencias, cantidad, suma;
        int idproducto = Integer.parseInt(this.jComboBox3Producto.getSelectedItem().toString());
        //encuentro la existencias iniciales del producto
        Entidad_Producto en = new Entidad_Producto(idproducto);
        EntradasDao d = new Entradas_Implements();
        d.ExtraerExistenciasBD(en);
        existencias = en.getExistencias_iniciales();
        //luego sumo las existencia con la cantidad
        cantidad = Integer.parseInt(this.jTextField8cantidad.getText());
        suma = (cantidad + existencias);
        this.jTextField8Stock.setText(String.valueOf(suma));

        return suma;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField4numentrada = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jDateChooser1fechaderegistro = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1Tabla = new javax.swing.JTable();
        jButtonEliminar = new javax.swing.JButton();
        jButtonRegistrar = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jComboBox3Producto = new javax.swing.JComboBox<>();
        jComboBox3Proveedor = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jTextField8cantidad = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField8NombreProducto = new javax.swing.JTextField();
        jTextField8NombreProveedor = new javax.swing.JTextField();
        jButton1Buscar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTextField8Stock = new javax.swing.JTextField();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel2.setText("ENTRADA DE PRODUCTOS");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("N° Entrada:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Fecha de ingreso:");

        jTable1Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°Producto", "N°proveedor", "Cantidad", "Fecha Ingreso", "N°Entrada", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1Tabla);
        if (jTable1Tabla.getColumnModel().getColumnCount() > 0) {
            jTable1Tabla.getColumnModel().getColumn(5).setResizable(false);
        }

        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Eliminar 72px.png"))); // NOI18N
        jButtonEliminar.setBorder(null);
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Registrar 72 px.png"))); // NOI18N
        jButtonRegistrar.setBorder(null);
        jButtonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarActionPerformed(evt);
            }
        });

        jButtonModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Modificar 72 px.png"))); // NOI18N
        jButtonModificar.setBorder(null);
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel12.setText("N° Proveedor:");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel13.setText("N° Producto :");

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton4.setText("SALIR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBox3Producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ProductoActionPerformed(evt);
            }
        });

        jComboBox3Proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ProveedorActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel14.setText("Cantidad:");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel17.setText("Nombre Producto:");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel18.setText("Nombre Proveedor:");

        jTextField8NombreProducto.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextField8NombreProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jTextField8NombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8NombreProductoActionPerformed(evt);
            }
        });

        jTextField8NombreProveedor.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextField8NombreProveedor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jTextField8NombreProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8NombreProveedorActionPerformed(evt);
            }
        });

        jButton1Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar72px.png"))); // NOI18N
        jButton1Buscar.setBorder(null);
        jButton1Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1BuscarActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel15.setText("Stock de entrada:");

        jTextField8Stock.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextField4numentrada, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jTextField8cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(39, 39, 39)
                                            .addComponent(jTextField8NombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel14)
                                            .addGap(50, 50, 50)
                                            .addComponent(jLabel17)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox3Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox3Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel18)
                            .addComponent(jTextField8NombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1fechaderegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField8Stock, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jButtonRegistrar)
                        .addGap(28, 28, 28)
                        .addComponent(jButtonEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonModificar)
                        .addGap(41, 41, 41)
                        .addComponent(jButton1Buscar)
                        .addGap(46, 46, 46)
                        .addComponent(jButton4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4numentrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox3Producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox3Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser1fechaderegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel17))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8NombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8NombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8Stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1Buscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Inicio i = new Inicio();
        i.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox3ProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ProductoActionPerformed
        ExtraerProductodelaBD();
        Deshabilitarjtexfielnombres();
        
        
    }//GEN-LAST:event_jComboBox3ProductoActionPerformed

    private void jComboBox3ProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ProveedorActionPerformed
        ExtraerProveedordelaBD();
        Deshabilitarjtexfielnombres();
    }//GEN-LAST:event_jComboBox3ProveedorActionPerformed

    private void jTextField8NombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8NombreProductoActionPerformed
        this.jTextField8NombreProducto.setEnabled(false);
    }//GEN-LAST:event_jTextField8NombreProductoActionPerformed

    private void jTextField8NombreProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8NombreProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8NombreProveedorActionPerformed

    private void jButton1BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1BuscarActionPerformed
        EntradasDao EntradaDao = new Entradas_Implements();
        if (this.jTextField4numentrada.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor Ingrese el N° de entrada  a buscar ");
        } else {
            int numEntrada = Integer.parseInt(this.jTextField4numentrada.getText());
            Entidad_EntradaProductos EntradaProducto = new Entidad_EntradaProductos(numEntrada);
            EntradaDao.buscar(EntradaProducto);
            this.jTextField4numentrada.setText(String.valueOf(EntradaProducto.getNumEntrada()));
            this.jComboBox3Producto.setSelectedItem(EntradaProducto.getIdproducto());
            this.jComboBox3Proveedor.setSelectedItem(EntradaProducto.getIdproveedor());
            this.jDateChooser1fechaderegistro.setDate(EntradaProducto.getFecha());
            this.jTextField8cantidad.setText(String.valueOf(EntradaProducto.getCantidad()));
            this.jTextField8Stock.setText(String.valueOf(EntradaProducto.getStock()));
            if (EntradaProducto.getFecha() == null) {

            } else {
                JOptionPane.showMessageDialog(null, " Registro encontrado ");
            }
            limpiar();
        }

    }//GEN-LAST:event_jButton1BuscarActionPerformed

    private void jButtonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarActionPerformed

        if (this.jTextField4numentrada.getText().equals("") || this.jTextField8cantidad.getText().equals("")
                || this.jDateChooser1fechaderegistro.getDate() == null || this.jComboBox3Producto.getSelectedIndex() == 0 || this.jComboBox3Proveedor.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, " Por favor ingresar los datos de todos los campos ");
        } else {
            // ahora comparo si el producto es registrado 

            EntradasDao EntradaDao = new Entradas_Implements();

            if (this.jTable1Tabla.getRowCount() == 0) { // 4 es como el tamaño por defecto de la tabla , pero lo cambie a 0
                // inicializo para registrar una entrada de producto nuevo
                int idProducto = Integer.parseInt(this.jComboBox3Producto.getSelectedItem().toString());
                int idproveedor = Integer.parseInt(this.jComboBox3Proveedor.getSelectedItem().toString());
                int cantidad = Integer.parseInt(this.jTextField8cantidad.getText());
                Date fech = this.jDateChooser1fechaderegistro.getDate();
                java.sql.Date dato = convertirfechaamsql(fech);
                int numEntrada = Integer.parseInt(this.jTextField4numentrada.getText());
                int stock = consultarStock();

                Entidad_EntradaProductos EntradaProducto = new Entidad_EntradaProductos(idProducto, idproveedor, cantidad, dato, numEntrada, stock, stock);
                EntradaDao.Insertar(EntradaProducto);

                agregar();

                limpiar();
            } else {
                boolean f = false, n = false;
                for (int i = 0; i < jTable1Tabla.getRowCount(); i++) { // recorro la tabla para verificar que el producto a ingresa no sea el mismo 
                    if (jTable1Tabla.getValueAt(i, 0).equals(this.jComboBox3Producto.getSelectedItem())) { // verifico el producto al igual que ya esta registrado 1 que es la colunna del producto
                        f = true;

                    }

                    if (jTable1Tabla.getValueAt(i, 4).toString().equals(this.jTextField4numentrada.getText())) { // verifico el producto al igual que ya esta registrado 1 que es la colunna del producto
                        n = true;

                    }
                }
                if (f == true || n == true) {
                    JOptionPane.showMessageDialog(null, "el Producto y/o numero de entrada ya esta registrado");
                    limpiar();
                } else {
                    // inicializo para registrar una entrada de producto nuevo
                    int idProducto = Integer.parseInt(this.jComboBox3Producto.getSelectedItem().toString());
                    int idproveedor = Integer.parseInt(this.jComboBox3Proveedor.getSelectedItem().toString());
                    int cantidad = Integer.parseInt(this.jTextField8cantidad.getText());
                    Date fech = this.jDateChooser1fechaderegistro.getDate();
                    java.sql.Date dato = convertirfechaamsql(fech);
                    int numEntrada = Integer.parseInt(this.jTextField4numentrada.getText());
                    int stock = consultarStock();

                    //instancio 
                    Entidad_EntradaProductos EntradaProducto = new Entidad_EntradaProductos(idProducto, idproveedor, cantidad, dato, numEntrada, stock, stock);
                    EntradaDao.Insertar(EntradaProducto);

                    //ahora con el metodo agregar agrego los datos a la tabla
                    agregar();
                    //limpio los campos
                    limpiar();
                }
            }
        }


    }//GEN-LAST:event_jButtonRegistrarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed

        EntradasDao EntradaDao = new Entradas_Implements();
        if (this.jTextField4numentrada.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor Ingrese el N° de la entrada  a eliminar ");
        } else {
            int idEntrada = Integer.parseInt(this.jTextField4numentrada.getText());
            Entidad_EntradaProductos entidadcliente = new Entidad_EntradaProductos(idEntrada);
            EntradaDao.Eliminar(entidadcliente);
            JOptionPane.showMessageDialog(null, "EL registro se elimino satisfactoriamente de la base de datos ");
            limpiar();
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed

        if (this.jTextField4numentrada.getText().equals("") || this.jTextField8cantidad.getText().equals("")
                || this.jDateChooser1fechaderegistro.getDate() == null || this.jComboBox3Producto.getSelectedIndex() == 0 || this.jComboBox3Proveedor.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, " Por favor ingresar los datos de todos los campos ");
        } else { // inicializo 
            int idProducto = Integer.parseInt(this.jComboBox3Producto.getSelectedItem().toString());
            int idproveedor = Integer.parseInt(this.jComboBox3Proveedor.getSelectedItem().toString());
            int cantidad = Integer.parseInt(this.jTextField8cantidad.getText());
            Date fech = this.jDateChooser1fechaderegistro.getDate();
            java.sql.Date dato = convertirfechaamsql(fech);
            int numEntrada = Integer.parseInt(this.jTextField4numentrada.getText());
            int stock = consultarStock();
            EntradasDao ModificarDao = new Entradas_Implements();
            Entidad_EntradaProductos EntradaProducto = new Entidad_EntradaProductos(idProducto, idproveedor, cantidad, dato, numEntrada, stock, stock);
            ModificarDao.modificar(EntradaProducto);
            JOptionPane.showMessageDialog(null, " Registro modificado con exito");
        }

        limpiar();
    }//GEN-LAST:event_jButtonModificarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Entradas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Entradas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Entradas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Entradas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Entradas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1Buscar;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JButton jButtonRegistrar;
    private javax.swing.JComboBox<String> jComboBox3Producto;
    private javax.swing.JComboBox<String> jComboBox3Proveedor;
    private com.toedter.calendar.JDateChooser jDateChooser1fechaderegistro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1Tabla;
    private javax.swing.JTextField jTextField4numentrada;
    private javax.swing.JTextField jTextField8NombreProducto;
    private javax.swing.JTextField jTextField8NombreProveedor;
    private javax.swing.JTextField jTextField8Stock;
    private javax.swing.JTextField jTextField8cantidad;
    // End of variables declaration//GEN-END:variables
}
