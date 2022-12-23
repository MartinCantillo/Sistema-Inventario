/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import Entidades.*;

import ImplementacionDao.*;

import PatronDao.*;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marti
 */
public class RegistrarFactura extends javax.swing.JFrame {

    /**
     * Creates new form RegistroFactura
     */
    // cargo ahora el combobox
    Facturasv_Implement cargarcombobox = new Facturasv_Implement();
    DefaultTableModel modelo = new DefaultTableModel();

    public RegistrarFactura() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Facturas");
        //cargo comboboz de cliente
        cargarcombobox.jcommboxClientes(this.jComboBox2Cliente);
        cargarcombobox.jcommboxPruducto(this.jComboBox3Producto);
        cargarcombobox.jcommboxvendedor(jComboBox1vendedor);
        this.jComboBox3FormaPago.addItem("Por favor seleccione una opcion");
        this.jComboBox3FormaPago.addItem("Credito");
        this.jComboBox3FormaPago.addItem("contado");
        this.jComboBox2numcuotas.addItem("Por favor seleccione una opcion");
        this.jComboBox2numcuotas.addItem("12");
        this.jComboBox2numcuotas.addItem("24");
        String titulo[] = new String[]{"N° Producto", "Descripcion Producto", "Precio ", "Cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(titulo);
        this.jTextField9Totalb.setEditable(false);
        this.setResizable(false);
        this.jTable1.setEnabled(false);
        this.jtexfielStockfactura.setEditable(false);

        this.jTextField8Precio.setEditable(false);
        this.jComboBox2numcuotas.setEnabled(true);
    }

    public java.sql.Date convertirfechaamsql(Date fech) {
        fech = this.jDateChooser1fechaderegistro.getDate();
        long d = fech.getTime();
        java.sql.Date f = new java.sql.Date(d);
        return f;
    }

    public double creditointereses() { // cuando es a credito registrar 
        // primero reviso que el cliente no tenga compra a credito
        int idc = Integer.parseInt(this.jComboBox2Cliente.getSelectedItem().toString());
//        JOptionPane.showMessageDialog(null, "cliente enviado a la bd" + idc);
        SaldosPendientesDao d = new SaldosPendientes_Implements();
        Entidad_SaldosPendientes e = new Entidad_SaldosPendientes(idc);
        d.buscarsielclienteestaregistrado(e);
        String nombre = this.jTextField8Nombrecliente.getText();
        double t = 0;
//        JOptionPane.showMessageDialog(null, "Metodo credito intereses cliente enviado de la bd" + e.getIdclientes());
//        JOptionPane.showMessageDialog(null, "Metodo credito intereses totalb" + this.jTextField9Totalb.getText());
        if (e.getIdclientes() == 0) {// revisar bien este if
//            JOptionPane.showMessageDialog(null, "Metodo credito intereses el if donde el cliente no ha registrado compra a credito"); 
            int cuota = Integer.parseInt(this.jComboBox2numcuotas.getSelectedItem().toString());// error desde aqui
            int totall = Integer.parseInt(this.jTextField9Totalb.getText());
            int cant1 = Integer.parseInt(this.jTextField8cantidad.getText());
            int cant2 = calculartotalmuldecantidadporprecio(cant1);
            t = totall;
//            JOptionPane.showMessageDialog(null, "total antes del if es de =" + totall);

            double doce; // 10% al de 12 cuotas
            double veint; // 20% al de 24 cuotas
            double totaldoc;
            double totalveint;
            if (cuota == 12) {
                totaldoc = (totall + cant2);
//                   JOptionPane.showMessageDialog(null, "operacion total1+cant2 =" + totaldoc);
                doce = totaldoc * 0.1;
//                JOptionPane.showMessageDialog(null, "operacion totaldoc*0.1=" + totaldoc);
                t = doce + totaldoc;
//                  JOptionPane.showMessageDialog(null, "operacion t = doce+totaldoc" + t);
                e = new Entidad_SaldosPendientes(idc, nombre, (int) t);
                d.insertar(e);
                this.jTextField2totall.setText(String.valueOf(t));
                JOptionPane.showMessageDialog(null, "El credito fue a 12 cuotas total saldo pendiente =" + t);
                this.jComboBox2numcuotas.setEnabled(false);
            } else {
                totalveint = (totall + cant2);
                veint = totalveint * 0.2;
                t = veint + totalveint;
                e = new Entidad_SaldosPendientes(idc, nombre, (int) t);
                d.insertar(e);
                this.jTextField2totall.setText(String.valueOf(t));
                JOptionPane.showMessageDialog(null, "El credito fue a 24 cuotas total saldo pendiente =" + t);
            }
        } else {
            JOptionPane.showMessageDialog(null, " El cliente ya ha registrado compra a credito , por lo cual se elimino la factura registrada");
            // elimino todo el detalle de la factura
            int idfac = Integer.parseInt(this.jTextField8numfactura.getText());
            int idfacc = Integer.parseInt(this.jTextField8numfactura.getText());
//            JOptionPane.showMessageDialog(null, "id factura enviada a la bd" + idfac);
            // ahora elimino la facturav
            FacturavDao dao = new Facturasv_Implement();
            Entidad_Facturav1Objeto enti = new Entidad_Facturav1Objeto(idfac);
            dao.Eliminar(enti);

            DetalleFacturaDao ExtraerDao = new DetalleFactura_Implement();
//            JOptionPane.showMessageDialog(null, "id factura enviada a la bd" + idfac);
            Entidad_DetalleFacturaObjeto ñ = new Entidad_DetalleFacturaObjeto(idfac);
            ExtraerDao.Eliminar(ñ);
//            JOptionPane.showMessageDialog(null, "id factura extraida estado del objeto a la bd" + ñ.getIdFactura());

        }
        this.jComboBox2numcuotas.setEditable(true);
        this.jTextField2totall.setText("");
        return t;

    }

    public void limpiarcanceladas() {
        this.jTextField8numfactura.setText("");
        this.jDateChooser1fechaderegistro.setDate(null);
        this.jComboBox2Cliente.setSelectedIndex(0);
        this.jComboBox3Producto.setSelectedIndex(0);
        this.jTextField8cantidad.setText("");
    }

    public void ExtraerProductodelaBD() {

        DetalleFacturaDao ExtraerDao = new DetalleFactura_Implement();
        int idproducto = Integer.parseInt(this.jComboBox3Producto.getSelectedItem().toString());
        Entidad_Producto EntidadExtraerProducto = new Entidad_Producto(idproducto);
        ExtraerDao.ExtraerProducto(EntidadExtraerProducto);
        this.jTextField8NombreProducto.setText(String.valueOf(EntidadExtraerProducto.getDescripcion()));
        this.jTextField8Precio.setText(String.valueOf(EntidadExtraerProducto.getPrecio()));

    }

    public void ExtraerClientedelaBD() {

        FacturavDao ExtraerClienteDao = new Facturasv_Implement();
        int idcliente = Integer.parseInt(this.jComboBox2Cliente.getSelectedItem().toString());
        Entidad_Cliente EntidadExtraercliente = new Entidad_Cliente(idcliente);
        ExtraerClienteDao.ExtraerClienteBD(EntidadExtraercliente);
        this.jTextField8Nombrecliente.setText(String.valueOf(EntidadExtraercliente.getNombreCliente()));

    }

    public void ExtraerVendedorBD() {

        FacturavDao ExtraerVendedorDao = new Facturasv_Implement();
        int idvendedor = Integer.parseInt(this.jComboBox1vendedor.getSelectedItem().toString());
        Entidad_Vendedor EntidadExtraerVendedor = new Entidad_Vendedor(idvendedor);
        ExtraerVendedorDao.ExtraerVendedorBD(EntidadExtraerVendedor);
        this.jTextField9nombrevendedor.setText(EntidadExtraerVendedor.getNombrevendedor());

    }

    public void agregar() {
        if (this.jTextField8cantidad.getText().equals("") || this.jTextField8Precio.getText().equals("")) {
            this.jTextField8cantidad.setText("0");
            this.jTextField8Precio.setText("0");

        } else {
            int cantidad = Integer.parseInt(this.jTextField8cantidad.getText());
            int precio = Integer.parseInt(this.jTextField8Precio.getText());
            int total = cantidad * precio;

            modelo.addRow(new Object[]{
                this.jComboBox3Producto.getSelectedItem(), this.jTextField8NombreProducto.getText(), this.jTextField8Precio.getText(),
                this.jTextField8cantidad.getText(), total});
            this.jTable1.setModel(modelo);
        }
    }

    public int operacion_stock() {
        int operacion, extraido;
        int idproducto = Integer.parseInt(this.jComboBox3Producto.getSelectedItem().toString());
        DetalleFacturaDao f = new DetalleFactura_Implement();
        Entidad_DetalleFactura d = new Entidad_DetalleFactura(idproducto);
        int cantidad = Integer.parseInt(this.jTextField8cantidad.getText());
        f.ExtraerStockIncial(d);
        extraido = d.getStock();
        operacion = (extraido - cantidad);
////        JOptionPane.showMessageDialog(null, " funcion operacion stock");
//        JOptionPane.showMessageDialog(null, "el stock resultante es " + operacion);
        return operacion;
    }

    public int extraerstock() {
        int extraido;
        int idproducto = Integer.parseInt(this.jComboBox3Producto.getSelectedItem().toString());
        DetalleFacturaDao f = new DetalleFactura_Implement();
        Entidad_DetalleFactura d = new Entidad_DetalleFactura(idproducto);
        //extraigo el stock de la base de datos e inicializo
        f.ExtraerStockIncial(d);
        extraido = d.getStock();
        this.jtexfielStockfactura.setText(String.valueOf(d.getStock()));
        return extraido;
    }

    public void deshabilitarjtexfiel() {

        this.jTextField8Nombrecliente.setEditable(false);
        this.jtexfielStockfactura.setEditable(false);
        this.jTextField8NombreProducto.setEditable(false);
        this.jTextField9nombrevendedor.setEditable(false);

    }
    // metodo para limpiar la tabla

    public void LimpiarTabla() {
        // recorro la tabla y voy eliminando
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
//        this.jTable1.setModel(modelo);
    }

    //metodo para calcular total cuando son mas de dos producctos
    public void calculartotalsumadeproductos() {
        //recorro la tabla
        Double iva = 0.19, total1, total2;
        int j;
        //primero calculo la cantidad del ultimo producto
        int cant = Integer.parseInt(this.jTextField8cantidad.getText());
        int suma = 0, registro;
        registro = calculartotalmuldecantidadporprecio(cant);
//        JOptionPane.showMessageDialog(null, "cantidad * precio =" + registro);
        suma = Integer.parseInt(this.jTextField9Totalb.getText());
//        JOptionPane.showMessageDialog(null, "totalb" + suma);
//        JOptionPane.showMessageDialog(null, "entro al metodo calcularsumaproducto dos o mas productos");
        j = (suma + registro);
        total1 = (j * 0.19);
//        JOptionPane.showMessageDialog(null, "totall (suma * 0.19) " + total1);
        total2 = (total1 + j);
//        JOptionPane.showMessageDialog(null, "total2 cuando calculo ya el porcentaje y lo sumo" + total2);
        this.jTextField2totall.setText(String.valueOf(total2));
    }

    public int calculartotald() { // metodo para calcular el total del ultimo producto y el interes del 19%

        int cant = Integer.parseInt(this.jTextField8cantidad.getText());
        // primero obtengo la cantidad del ultimo producto a registrar
        int precio2 = calculartotalmuldecantidadporprecio(cant);
        int suma = Integer.parseInt(this.jTextField9Totalb.getText());
        //JOptionPane.showMessageDialog(null, "metodo para  calcular el total del ultimo producto y el interes del 19% totalbruto = " + suma);
        Double total1, total2;

        total1 = (suma * 0.19);
        total2 = total1 + suma + precio2;
        this.jTextField2totall.setText(String.valueOf(total2));
        this.jTextField9Totalb.setText(String.valueOf(suma));
        return suma;
    }

    public double calculartotalunsoloproducto() { // metodo para calcular el totall mas el interes del 19% de iva cuando es un solo producto
        //recorro la tabla
        Double iva = 0.19, total1, total2, j;
        // multiplico la cantidad por  el precio 
        int cant = Integer.parseInt(this.jTextField8cantidad.getText());
        int h = calculartotalmuldecantidadporprecio(cant);
        // JOptionPane.showMessageDialog(null, "entro al metodo calcularsumaproducto un solo producto");
        // como ya tengo la cantidad , ahora lo que hago es agregar el resultado al totalbruto y calculo el iva para agregarselo al totall

        total1 = (h * 0.19);
        total2 = total1 + h;
        this.jTextField9Totalb.setText(String.valueOf(h));
//        JOptionPane.showMessageDialog(null, "total bruto" + h);
//        JOptionPane.showMessageDialog(null, "totall " + total2);
        this.jTextField2totall.setText(String.valueOf(total2));
        return total2;
    }

    public int calculartotalmuldecantidadporprecio(int cantidad) {

        int precio = Integer.parseInt(this.jTextField8Precio.getText());
        int total = (cantidad * precio);
        return total;
    }

    public void deshabilitarjcombox() {

        this.jComboBox2Cliente.setEnabled(false);
        this.jComboBox1vendedor.setEnabled(false);
        this.jComboBox3FormaPago.setEnabled(false);
        this.jComboBox3Producto.setEnabled(false);
        this.jDateChooser1fechaderegistro.setEnabled(false);
        this.jTextField8cantidad.setEditable(false);

    }

    public void deshabilitarcomprademasdeunproducto() {
        this.jComboBox2Cliente.setEnabled(false);
        this.jComboBox1vendedor.setEnabled(false);
        this.jComboBox3FormaPago.setEnabled(false);
        this.jComboBox3Producto.setEnabled(true);
        this.jDateChooser1fechaderegistro.setEnabled(false);
        this.jTextField8cantidad.setEditable(true);
        this.jTextField8numfactura.setEditable(false);
    }

    public void Habilitartodo() {
        this.jComboBox2Cliente.setEnabled(true);
        this.jComboBox1vendedor.setEnabled(true);
        this.jComboBox3FormaPago.setEnabled(true);
        this.jComboBox3Producto.setEnabled(true);
        this.jDateChooser1fechaderegistro.setEnabled(true);
        this.jTextField8cantidad.setEditable(true);
        this.jTextField8numfactura.setEditable(true);

    }

    public void habilitardespuesdeexcederstock() {
        this.jTextField8numfactura.setEditable(false);
        this.jDateChooser1fechaderegistro.setEnabled(false);
        this.jComboBox2Cliente.setEditable(false);
        this.jComboBox1vendedor.setEditable(false);
        this.jComboBox3FormaPago.setEditable(false);
    }

    public int totalproductosbruto() { // metodo para calcular el total de los productos que estan registrado en la tabla añadir
        //recorro la tabla
        int suma = 0, registro;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            // aqui le mando la colunna de donde esta el precio de los productos
            registro = Integer.parseInt(modelo.getValueAt(i, 4).toString());
            suma = suma + registro;
        }
        this.jTextField9Totalb.setText(String.valueOf(suma));

        return suma;
    }

    public void limpiar() {
        this.jComboBox2Cliente.setSelectedItem(0);
        this.jComboBox1vendedor.setSelectedItem(0);
        this.jComboBox3FormaPago.setSelectedItem("Por favor seleccione una opcion");
        this.jComboBox3Producto.setSelectedItem(0);
        this.jDateChooser1fechaderegistro.setDate(null);
        this.jTextField8cantidad.setText("");
        this.jTextField8numfactura.setText("");
    }

    public void limpiardespuesdeunproducto() {
        this.jComboBox3Producto.setSelectedItem(0);
        this.jTextField8cantidad.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jDateChooser1fechaderegistro = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField8numfactura = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3Registrar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton5DevolverFactura = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1vendedor = new javax.swing.JComboBox<>();
        jComboBox2Cliente = new javax.swing.JComboBox<>();
        jComboBox3Producto = new javax.swing.JComboBox<>();
        jButton1añadir = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField8NombreProducto = new javax.swing.JTextField();
        jTextField8cantidad = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField8Nombrecliente = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jtexfielStockfactura = new javax.swing.JTextField();
        jComboBox3FormaPago = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jTextField9nombrevendedor = new javax.swing.JTextField();
        jTextField9Totalb = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField8Precio = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jButtonsalir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2numcuotas = new javax.swing.JComboBox<>();
        jTextField1iva = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField2totall = new javax.swing.JTextField();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel2.setText("REGISTRO DE FACTURAS");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Fecha:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Stock:");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel12.setText("Cantidad:");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel14.setText("N° Factura:");

        jTextField8numfactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8numfacturaActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel15.setText(" vendedor:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Producto", "Descripcion", "Precio", "Cantidad", "SubTotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton3Registrar.setBackground(new java.awt.Color(242, 242, 242));
        jButton3Registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Guardarfacturaregistrar.png"))); // NOI18N
        jButton3Registrar.setBorder(null);
        jButton3Registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3RegistrarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Forma de pago:");

        jButton5DevolverFactura.setBackground(new java.awt.Color(242, 242, 242));
        jButton5DevolverFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes2/DevolverFactura32 px.png"))); // NOI18N
        jButton5DevolverFactura.setBorder(null);
        jButton5DevolverFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5DevolverFacturaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("Cancelar ");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setText("Registrar ");

        jComboBox1vendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1vendedorActionPerformed(evt);
            }
        });

        jComboBox2Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ClienteActionPerformed(evt);
            }
        });

        jComboBox3Producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ProductoActionPerformed(evt);
            }
        });

        jButton1añadir.setBackground(new java.awt.Color(242, 242, 242));
        jButton1añadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes2/añadir32 px factura.png"))); // NOI18N
        jButton1añadir.setBorder(null);
        jButton1añadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1añadirañadirActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Añadir ");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel16.setText("Cliente:");

        jTextField8NombreProducto.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextField8NombreProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jTextField8NombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8NombreProductoActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel13.setText("Nombre Producto:");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel17.setText("Nombre Cliente:");

        jTextField8Nombrecliente.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextField8Nombrecliente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jTextField8Nombrecliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8NombreclienteActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel18.setText("Precio:");

        jtexfielStockfactura.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jtexfielStockfactura.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N
        jtexfielStockfactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtexfielStockfacturaActionPerformed(evt);
            }
        });

        jComboBox3FormaPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3FormaPagoActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel19.setText("Total Bruto:");

        jTextField9nombrevendedor.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextField9nombrevendedor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jTextField9nombrevendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9nombrevendedorActionPerformed(evt);
            }
        });

        jTextField9Totalb.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextField9Totalb.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jTextField9Totalb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9TotalbActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel20.setText("Nombre Vendedor:");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel21.setText(" Producto :");

        jTextField8Precio.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextField8Precio.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N
        jTextField8Precio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8PrecioActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel22.setText("Iva:");

        jButtonsalir.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButtonsalir.setText("SALIR");
        jButtonsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonsalirActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("N° Cuotas:");

        jComboBox2numcuotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2numcuotasActionPerformed(evt);
            }
        });

        jTextField1iva.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField1iva.setText("19%");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel23.setText("Total:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(225, 225, 225))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField8numfactura, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14)
                                        .addComponent(jTextField8cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel17))
                                    .addGap(32, 32, 32)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBox1vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel8)
                                                .addComponent(jDateChooser1fechaderegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel15)
                                                .addComponent(jLabel13)
                                                .addComponent(jTextField8NombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(33, 33, 33)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jComboBox2Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel16)
                                                .addComponent(jComboBox3FormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel9)
                                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextField8Precio, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(41, 41, 41)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel21)
                                                        .addComponent(jComboBox3Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jComboBox2numcuotas, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(27, 27, 27)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel7)
                                                        .addComponent(jtexfielStockfactura, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField9nombrevendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                                .addComponent(jTextField8Nombrecliente, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(40, 40, 40)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1añadir, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton5DevolverFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(jButtonsalir))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel1)))
                        .addGap(71, 71, 71)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(67, 67, 67)
                                .addComponent(jTextField1iva, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField2totall, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField9Totalb, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel2)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jLabel21)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1fechaderegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox2Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox3Producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtexfielStockfactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField8numfactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel15)
                    .addComponent(jLabel9)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3FormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2numcuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel13)
                    .addComponent(jLabel18)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8Nombrecliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8NombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8Precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9nombrevendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jTextField1iva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jTextField9Totalb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jTextField2totall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton3Registrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                .addComponent(jButton5DevolverFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1añadir, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel6))))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1añadirañadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1añadirañadirActionPerformed

        int id = Integer.parseInt(this.jComboBox2Cliente.getSelectedItem().toString());
        if (this.jTextField8numfactura.getText().equals("") || this.jDateChooser1fechaderegistro.getDate() == null || this.jComboBox2Cliente.getSelectedIndex() == 0
                || this.jComboBox3Producto.getSelectedIndex() == 0 || this.jComboBox1vendedor.getSelectedIndex() == 0 || this.jTextField8cantidad.getText().equals("") || this.jComboBox3FormaPago.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese todo los campos ");
        } else {
            // si es el primer registro y no hay registros en la tabla
            if (modelo.getRowCount() == 0) {
                // aqui voty insertando productos a la tabla(primer producto     
                // lleno mi tabla producto
                this.jTextField8numfactura.setEditable(false);
                deshabilitarjcombox();
                this.jComboBox3Producto.setEditable(true);
                this.jComboBox2numcuotas.setEnabled(false);
                int idproducto = Integer.parseInt(this.jComboBox3Producto.getSelectedItem().toString());
                String nombre = this.jTextField8NombreProducto.getText();
                int idFactura = Integer.parseInt(this.jTextField8numfactura.getText());
                int cantidad = Integer.parseInt(this.jTextField8cantidad.getText());
                int precio2 = calculartotalmuldecantidadporprecio(cantidad);
                int stock_disponible = extraerstock();
                if (cantidad > stock_disponible) {
                    JOptionPane.showMessageDialog(null, "La cantidad Excede el Stock del producto");
                    habilitardespuesdeexcederstock();
                    this.jTextField8cantidad.setText("");
                    this.jComboBox3Producto.setEditable(true);
                    this.jTextField8cantidad.setEditable(true);
                } else {

                    int stockfinal = operacion_stock();
                    DetalleFacturaDao registtrarproductoDao = new DetalleFactura_Implement();
                    Entidad_DetalleFactura entidaddefac = new Entidad_DetalleFactura(idFactura, idproducto, nombre, cantidad, precio2, stockfinal);
                    registtrarproductoDao.InsertarProducto(entidaddefac);
                    registtrarproductoDao.ActualizarStockEntrada(entidaddefac);

                    agregar();
                    totalproductosbruto();
                    this.jComboBox3Producto.setEnabled(true);
                    this.jTextField8cantidad.setEditable(true);
                    this.jTextField8cantidad.setText("");
                }
            } else {
                //si hay mas de un registros en la tabla del mismo cliente

                for (int i = 0; i < modelo.getRowCount(); i++) { // recorro la tabla para verificar que el producto a ingresa no sea el mismo 
                    if (modelo.getValueAt(i, 1).toString().equals(this.jTextField8NombreProducto.getText())) { // verifico el producto al igual que ya esta registrado 1 que es la colunna del producto
                        JOptionPane.showMessageDialog(null, "el Producto ya esta registrado");
//                        modelo.removeRow(i);

                    }
                }
//                JOptionPane.showMessageDialog(null, "entro al else de hay un producto registrado");
                // ya hay un producto en la tabla
                int idFactura = Integer.parseInt(this.jTextField8numfactura.getText());
                this.jComboBox3Producto.setEditable(true);
                int idproducto = Integer.parseInt(this.jComboBox3Producto.getSelectedItem().toString());
                String nombre = this.jTextField8NombreProducto.getText();
                int cantidad = Integer.parseInt(this.jTextField8cantidad.getText());

                int precio2 = calculartotalmuldecantidadporprecio(cantidad); // aqui me extrae el precio del producto por la cantidad
                int stock_disponible = extraerstock();
                if (cantidad > stock_disponible) {
                    JOptionPane.showMessageDialog(null, "La cantidad Excede el Stock del producto");
                    this.jTextField8cantidad.setText("");
                    this.jComboBox3Producto.setEditable(true);
                } else {

                    int stockfinal = operacion_stock();
                    DetalleFacturaDao registtrarproductoDao = new DetalleFactura_Implement();
                    Entidad_DetalleFactura entidaddefac = new Entidad_DetalleFactura(idFactura, idproducto, nombre, cantidad, precio2, stockfinal);
                    registtrarproductoDao.InsertarProducto(entidaddefac);
                    registtrarproductoDao.ActualizarStockEntrada(entidaddefac);
                    agregar();
                    totalproductosbruto();
                    JOptionPane.showMessageDialog(null, "Producto agregado con exito");
                    deshabilitarcomprademasdeunproducto();
                    limpiardespuesdeunproducto();

                }

            }
        }
    }//GEN-LAST:event_jButton1añadirañadirActionPerformed

    private void jComboBox3FormaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3FormaPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3FormaPagoActionPerformed

    private void jTextField8numfacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8numfacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8numfacturaActionPerformed

    private void jComboBox3ProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ProductoActionPerformed
        ExtraerProductodelaBD();
        deshabilitarjtexfiel();
        extraerstock();

    }//GEN-LAST:event_jComboBox3ProductoActionPerformed

    private void jComboBox2ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ClienteActionPerformed
        ExtraerClientedelaBD();
        deshabilitarjtexfiel();

    }//GEN-LAST:event_jComboBox2ClienteActionPerformed

    private void jComboBox1vendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1vendedorActionPerformed
        ExtraerVendedorBD();
        deshabilitarjtexfiel();
    }//GEN-LAST:event_jComboBox1vendedorActionPerformed

    private void jButton3RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3RegistrarActionPerformed
        if (this.jTextField8numfactura.getText().equals("") || this.jDateChooser1fechaderegistro.getDate() == null || this.jComboBox2Cliente.getSelectedIndex() == 0
                || this.jComboBox3Producto.getSelectedIndex() == 0 || this.jComboBox1vendedor.getSelectedIndex() == 0 || this.jTextField8cantidad.getText().equals("") || this.jComboBox3FormaPago.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese todo los campos ");
        } else {
            this.jComboBox2numcuotas.setEditable(false);
            // si el idfcatura es igual al idfacturadetalle, entonces creo mi nueva facturav
            if (modelo.getRowCount() != 0) {// si la tabla no esta vacia , guardo los productos ..     //registra los productos que ya estan guardados en detallefactura que estan agregados en la tabla

                //ya agrego varios productos
                // lleno el facturavDao
                boolean f = false;
                for (int i = 0; i < modelo.getRowCount(); i++) { // recorro la tabla para verificar que el producto a ingresa no sea el mismo 
                    if (modelo.getValueAt(i, 1).toString().equals(this.jTextField8NombreProducto.getText())) { // verifico el producto al igual que ya esta registrado 1 que es la colunna del producto
                        JOptionPane.showMessageDialog(null, "el Producto ya esta registrado");
//                        modelo.removeRow(i);
                        f = true;
                    }

                }
                if (f == true) { // si el producto no esta registrado y la tabla no esta vacia

                    JOptionPane.showMessageDialog(null, "el Producto no se registro en la factura");
                    //y solamente registro la factura NO el detellae factura
                    FacturavDao registrarclientesDao = new Facturasv_Implement();
                    int numfactura = Integer.parseInt(this.jTextField8numfactura.getText());
                    int idcliente = Integer.parseInt(this.jComboBox2Cliente.getSelectedItem().toString());

                    int idvendedor = Integer.parseInt(this.jComboBox1vendedor.getSelectedItem().toString());

                    Date fech = this.jDateChooser1fechaderegistro.getDate();
                    int cant = Integer.parseInt(this.jTextField8cantidad.getText());
                    java.sql.Date dato = convertirfechaamsql(fech);
//                    int precio2 = calculartotalmuldecantidadporprecio(cant);
//                    JOptionPane.showMessageDialog(null, "precio2(multiplicalacantidaddeproductoporelprecio) " + precio2);
                    String formapago = this.jComboBox3FormaPago.getSelectedItem().toString();
                    Entidad_Facturav insertar = new Entidad_Facturav(idcliente, idvendedor, dato, formapago, numfactura);
                    registrarclientesDao.InsertarCliente(insertar);

                } else {
                    //sino es porque el producto no esta registrado
                    // la forma de pago es a contado 
//                    calculartotald(); // obtengo ya el total de la factura con el interes del 19%
                    FacturavDao registrarclientesDao = new Facturasv_Implement();
                    int numfactura = Integer.parseInt(this.jTextField8numfactura.getText());
                    int idcliente = Integer.parseInt(this.jComboBox2Cliente.getSelectedItem().toString());

                    int idvendedor = Integer.parseInt(this.jComboBox1vendedor.getSelectedItem().toString());
                    Date fech = this.jDateChooser1fechaderegistro.getDate();
                    int cant = Integer.parseInt(this.jTextField8cantidad.getText());
                    java.sql.Date dato = convertirfechaamsql(fech);
                    int precio2 = calculartotalmuldecantidadporprecio(cant);

//                    JOptionPane.showMessageDialog(null, "precio2(multiplicalacantidaddeproductoporelprecio) " + precio2);
                    String formapago = this.jComboBox3FormaPago.getSelectedItem().toString();
                    Entidad_Facturav insertar = new Entidad_Facturav(idcliente, idvendedor, dato, formapago, numfactura);
                    registrarclientesDao.InsertarCliente(insertar);

//                    JOptionPane.showMessageDialog(null, "total(me extrajo lo que hay en totaldetodo) " + totalsumproducto);
//                    JOptionPane.showMessageDialog(null, "entro al if  donde la tabla no  esta vacia ");
                    //agrego el ultimo producto aqui el de detalle factura
                    int idproducto = Integer.parseInt(this.jComboBox3Producto.getSelectedItem().toString());
                    String nombre = this.jTextField8NombreProducto.getText();
                    int idFactura = Integer.parseInt(this.jTextField8numfactura.getText());
                    int cantidad = Integer.parseInt(this.jTextField8cantidad.getText());
                    int precio5 = calculartotalmuldecantidadporprecio(cantidad);
                    int stock_disponible = extraerstock();

                    if (cantidad > stock_disponible) {
                        JOptionPane.showMessageDialog(null, "La cantidad Excede el Stock del producto");

                    } else {

                        int stockfinal = operacion_stock();
                        DetalleFacturaDao registtrarproductoDao = new DetalleFactura_Implement();
                        Entidad_DetalleFactura entidaddefac = new Entidad_DetalleFactura(idFactura, idproducto, nombre, cantidad, precio5, stockfinal);
                        registtrarproductoDao.InsertarProducto(entidaddefac);
                        registtrarproductoDao.ActualizarStockEntrada(entidaddefac);
                        //ahora verifico la forma de pago 
                        this.jComboBox3FormaPago.getSelectedIndex();
                        if (this.jComboBox3FormaPago.getSelectedIndex() == 1) {
                            // JOptionPane.showMessageDialog(null, "entro al if deonde el metodo de pago es credito y no esta repetido el producto");
                            creditointereses();
                            Habilitartodo();
                            LimpiarTabla();
                            limpiar();
                            this.jTextField9Totalb.setText("");
                        } else {
//                            JOptionPane.showMessageDialog(null, "entro al else donde la tabla no esta vacia ");
                            calculartotalsumadeproductos();
                            Habilitartodo();
                            LimpiarTabla();
                            limpiar();
                            this.jTextField9Totalb.setText("");
                            this.jComboBox2numcuotas.setEditable(true);
                            JOptionPane.showMessageDialog(null, "Factura registrada ");
                            this.jTextField2totall.setText("");

//            JOptionPane.showMessageDialog(null, "tabla NO VACIA");
                            //////////////////////////////////////////////////////////////////////////////////////////////////////
                        }
                    }
                }
            } else {
                // lleno el facturavDao si solo es un producto
                //  JOptionPane.showMessageDialog(null, "entro al else donde es un solo producto");
                int cantidad = Integer.parseInt(this.jTextField8cantidad.getText());
                int stock_disponible = extraerstock();
                if (cantidad > stock_disponible) {
                    JOptionPane.showMessageDialog(null, "La cantidad Excede el Stock del producto");
                    Habilitartodo();
                    limpiar();
                    this.jTextField9Totalb.setText("");
                } else {

                    FacturavDao registrarclientesDao = new Facturasv_Implement();
                    int numfactura = Integer.parseInt(this.jTextField8numfactura.getText());
                    int idcliente = Integer.parseInt(this.jComboBox2Cliente.getSelectedItem().toString());
//            int idproducto = Integer.parseInt(this.jComboBox3Producto.getSelectedItem().toString());
                    int idvendedor = Integer.parseInt(this.jComboBox1vendedor.getSelectedItem().toString());
                    int precio = Integer.parseInt(this.jTextField8Precio.getText());
                    Date fech = this.jDateChooser1fechaderegistro.getDate();
                    java.sql.Date dato = convertirfechaamsql(fech);
                    String formapago = this.jComboBox3FormaPago.getSelectedItem().toString();
                    Entidad_Facturav insertar = new Entidad_Facturav(idcliente, idvendedor, dato, formapago, numfactura);
                    registrarclientesDao.InsertarCliente(insertar);

                    agregar();
                    int precio2 = cantidad * precio;
                    // ahora agrego lo que es el detalle de la factura
                    this.jTextField8numfactura.setEditable(false);
                    deshabilitarjcombox();
                    this.jComboBox3Producto.setEditable(true);
                    int idproducto = Integer.parseInt(this.jComboBox3Producto.getSelectedItem().toString());
                    String nombre = this.jTextField8NombreProducto.getText();
                    int idFactura = Integer.parseInt(this.jTextField8numfactura.getText());

                    int stockfinal = operacion_stock();
                    DetalleFacturaDao registtrarproductoDao = new DetalleFactura_Implement();
//                DetalleFacturaDao registtrarproductoDao = new DetalleFactura_Implement();
                    Entidad_DetalleFactura entidaddefac = new Entidad_DetalleFactura(idFactura, idproducto, nombre, cantidad, precio2, stockfinal);
                    registtrarproductoDao.InsertarProducto(entidaddefac);
                    registtrarproductoDao.ActualizarStockEntrada(entidaddefac);
                    JOptionPane.showMessageDialog(null, "Factura  registrada con exito");
                    // JOptionPane.showMessageDialog(null, "else de que la tabla esta vacia");

                    this.jComboBox3FormaPago.getSelectedIndex();
                    if (this.jComboBox3FormaPago.getSelectedIndex() == 1) {
                        // JOptionPane.showMessageDialog(null, "entro al if deonde el metodo de pago es credito y es un solo producto");
                        calculartotalunsoloproducto();
                        creditointereses();
                        limpiar();
                        Habilitartodo();
                        LimpiarTabla();

                        this.jTextField9Totalb.setText("");
                    } else {

                        calculartotalunsoloproducto();
                        limpiar();
                        Habilitartodo();
                        LimpiarTabla();

                        this.jTextField9Totalb.setText("");
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton3RegistrarActionPerformed

    private void jTextField9nombrevendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9nombrevendedorActionPerformed

    }//GEN-LAST:event_jTextField9nombrevendedorActionPerformed

    private void jtexfielStockfacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtexfielStockfacturaActionPerformed

    }//GEN-LAST:event_jtexfielStockfacturaActionPerformed

    private void jTextField8NombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8NombreProductoActionPerformed
        this.jTextField8NombreProducto.setEnabled(false);
    }//GEN-LAST:event_jTextField8NombreProductoActionPerformed

    private void jTextField8NombreclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8NombreclienteActionPerformed

    }//GEN-LAST:event_jTextField8NombreclienteActionPerformed

    private void jTextField9TotalbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9TotalbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9TotalbActionPerformed

    private void jTextField8PrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8PrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8PrecioActionPerformed

    private void jButtonsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonsalirActionPerformed
        // TODO add your handling code here:
        Inicio i = new Inicio();
        i.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonsalirActionPerformed

    private void jComboBox2numcuotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2numcuotasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2numcuotasActionPerformed

    private void jButton5DevolverFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5DevolverFacturaActionPerformed

        if (this.jTextField8numfactura.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por lo menos Ingrese el codigo de la factura");
        } else {
            int idn = Integer.parseInt(this.jTextField8numfactura.getText());
            int idp = Integer.parseInt(this.jComboBox3Producto.getSelectedItem().toString());
            int idcl = Integer.parseInt(this.jComboBox2Cliente.getSelectedItem().toString());
            Date fech = this.jDateChooser1fechaderegistro.getDate();
            int cant = Integer.parseInt(this.jTextField8cantidad.getText());
            java.sql.Date dato = convertirfechaamsql(fech);
            ConsultarFacturasCanceladasDao is = new FacturasCanceladas_Implements();
            Entidad_FacturasCanceladas es = new Entidad_FacturasCanceladas(idn, dato, idcl, idp, cant);
            is.registrar(es);
            JOptionPane.showMessageDialog(null, "Factura Cancelada con exito");
            limpiarcanceladas();
        }
    }//GEN-LAST:event_jButton5DevolverFacturaActionPerformed

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
            java.util.logging.Logger.getLogger(RegistrarFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarFactura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1añadir;
    private javax.swing.JButton jButton3Registrar;
    private javax.swing.JButton jButton5DevolverFactura;
    private javax.swing.JButton jButtonsalir;
    private javax.swing.JComboBox<String> jComboBox1vendedor;
    private javax.swing.JComboBox<String> jComboBox2Cliente;
    private javax.swing.JComboBox<String> jComboBox2numcuotas;
    private javax.swing.JComboBox<String> jComboBox3FormaPago;
    private javax.swing.JComboBox<String> jComboBox3Producto;
    private com.toedter.calendar.JDateChooser jDateChooser1fechaderegistro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1iva;
    private javax.swing.JTextField jTextField2totall;
    private javax.swing.JTextField jTextField8NombreProducto;
    private javax.swing.JTextField jTextField8Nombrecliente;
    private javax.swing.JTextField jTextField8Precio;
    private javax.swing.JTextField jTextField8cantidad;
    private javax.swing.JTextField jTextField8numfactura;
    private javax.swing.JTextField jTextField9Totalb;
    private javax.swing.JTextField jTextField9nombrevendedor;
    private javax.swing.JTextField jtexfielStockfactura;
    // End of variables declaration//GEN-END:variables
}
