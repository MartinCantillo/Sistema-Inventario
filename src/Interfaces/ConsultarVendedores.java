/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import Entidades.Entidad_Cliente;
import Entidades.Entidad_DetalleFactura;
import Entidades.Entidad_Facturav;
import ImplementacionDao.ConsultarVendedores_Implement;
import PatronDao.ConsultarVendedoresDao;
import javax.swing.JOptionPane;

/**
 *
 * @author marti
 */
public class ConsultarVendedores extends javax.swing.JFrame {

    /**
     * Creates new form ConsultarVendedores
     */
    ConsultarVendedoresDao h = new ConsultarVendedores_Implement();
    
    public ConsultarVendedores() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        this.jTable1.setEnabled(false);
        this.jTextField2nombrecliente.setEditable(false);
        this.setTitle("Consultar vendedores");
        this.setResizable(false);
        h.jcommboxClientes(jComboBox1idcliente1);
        
    }

//    public void Extraercodigocliente() {
//        int cliente = Integer.parseInt(this.jComboBox1idcliente1.getSelectedItem().toString());
//        Entidad_Cliente c = new Entidad_Cliente(cliente);
//        ConsultarVendedoresDao d = new ConsultarVendedores_Implement();
//        d.jcommboxClientes(jComboBox1idcliente1);
//        d.ExtraerNombreCLiente(c);
//    }
    public void Extraernombrecliente() {
        int cliente = Integer.parseInt(this.jComboBox1idcliente1.getSelectedItem().toString());
        Entidad_Cliente c = new Entidad_Cliente(cliente);
        ConsultarVendedoresDao d = new ConsultarVendedores_Implement();
        d.ExtraerNombreCLiente(c);
        this.jTextField2nombrecliente.setText(c.getNombreCliente());
    }
    
    public void ExtraerTabla() {
        ConsultarVendedoresDao d = new ConsultarVendedores_Implement();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTextField2nombrecliente = new javax.swing.JTextField();
        jButton1consultar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1idcliente1 = new javax.swing.JComboBox<>();
        jButton3Limpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("N° Cliente:");

        jButton1consultar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1consultar.setText("CONSULTAR");
        jButton1consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1consultarActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "N°Factura", "N° vendedor", "Nombre vendedor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton2.setText("SALIR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setText("Consultar vendedor!");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Nombre:");

        jComboBox1idcliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1idcliente1ActionPerformed(evt);
            }
        });

        jButton3Limpiar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton3Limpiar.setText("LIMPIAR");
        jButton3Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3LimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2))
                            .addComponent(jComboBox1idcliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2nombrecliente, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton3Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1idcliente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2nombrecliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Inicio i = new Inicio();
        i.setVisible(true);
        this.setVisible(false);
          this.jButton1consultar.setEnabled(true);
        this.jButton3Limpiar.setEnabled(true);
        

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1idcliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1idcliente1ActionPerformed
        Extraernombrecliente();
    }//GEN-LAST:event_jComboBox1idcliente1ActionPerformed

    private void jButton1consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1consultarActionPerformed
        if (this.jComboBox1idcliente1.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor Ingrese el numero de cedula del cliente  a consultar");
        } else {
            int cliente = Integer.parseInt(this.jComboBox1idcliente1.getSelectedItem().toString());
//            Entidad_Cliente c = new Entidad_Cliente(cliente);
            Entidad_Facturav p = new Entidad_Facturav(cliente);
            ConsultarVendedoresDao d = new ConsultarVendedores_Implement();
            d.ExtraerTabla(jTable1, p);
            this.jButton1consultar.setEnabled(false);
             this.jButton3Limpiar.setEnabled(true);
        }
    }//GEN-LAST:event_jButton1consultarActionPerformed

    private void jButton3LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3LimpiarActionPerformed
        
        ConsultarVendedoresDao d = new ConsultarVendedores_Implement();
        d.EliminarTabla(jTable1);
        this.jComboBox1idcliente1.setSelectedItem(0);
        this.jTextField2nombrecliente.setText("");
        this.jButton1consultar.setEnabled(true);
        this.jButton3Limpiar.setEnabled(false);
    }//GEN-LAST:event_jButton3LimpiarActionPerformed

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
            java.util.logging.Logger.getLogger(ConsultarVendedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarVendedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarVendedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarVendedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultarVendedores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1consultar;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3Limpiar;
    private javax.swing.JComboBox<String> jComboBox1idcliente1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2nombrecliente;
    // End of variables declaration//GEN-END:variables
}