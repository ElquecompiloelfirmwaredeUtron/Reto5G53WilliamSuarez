package Vistas;

import Controlador.EnumDepartamento;
import Controlador.EnumTipoCalle;
import Controlador.EnumZona;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class UserMenu2 extends javax.swing.JFrame {

    ComboBoxModel modeloEnumTipoDocumento;

    Conexion conexion = new Conexion();
    Connection connection;
    Statement st;
    ResultSet rs;
    DefaultTableModel contenidoTabal, contenidoTablaEmpleados;
    ComboBoxModel enumDepartamentos, enumZonas, enumTipoCalles;
    private DefaultTableModel contenidoTablaDepartamento;

    public UserMenu2() {
        //Days days[] = Days.values();
        EnumDepartamento.EnumMunicipios enumMunicipios[] = EnumDepartamento.EnumMunicipios.values();
        enumDepartamentos = new DefaultComboBoxModel(enumMunicipios);

        EnumZona enumzona[] = EnumZona.values();
        enumZonas = new DefaultComboBoxModel(enumzona);

        EnumTipoCalle enumTipoCalle[] = EnumTipoCalle.values();
        enumTipoCalles = new DefaultComboBoxModel(enumTipoCalle);
        initComponents();
        setLocationRelativeTo(null);
        ListarEmpleados();
        ListarDepartamentos();

    }

    private void ListarEmpleados() {
        String filtroBusqueda = txtBuscarEMP.getText();
        if (filtroBusqueda.isEmpty()) {
            String queryConsulta = "SELECT nombreEmp, apellidos, tipoDocumento, documento, correo, nombreSucursal\n"
                    + "FROM empleado\n"
                    + "INNER JOIN sucursal\n"
                    + "ON empleado.FK_idSucursal = sucursal.idSucursal";

            try {
                connection = conexion.getConection();
                st = connection.createStatement();
                rs = st.executeQuery(queryConsulta);

                Object[] empleados = new Object[6];
                contenidoTablaEmpleados = (DefaultTableModel) tblEmpleados.getModel();
                while (rs.next()) {

                    empleados[0] = rs.getString("nombreEmp");
                    empleados[1] = rs.getString("apellidos");
                    empleados[2] = rs.getString("tipoDocumento");
                    empleados[3] = rs.getString("documento");
                    empleados[4] = rs.getString("correo");
                    empleados[5] = rs.getString("nombreSucursal");

                    contenidoTablaEmpleados.addRow(empleados);
                    //System.out.println("Id:" + empleados[0] + "nombre " + empleados[1]
                    //+ "apellidos: " + empleados[2] + "tipoDocumento" + empleados[3]
                    //+ "documento" + empleados[4] + "correo" + empleados[5]);
                    tblEmpleados.setModel(contenidoTablaEmpleados);
                }

            } catch (SQLException e) {
                System.out.println("Error de sistema");
            }

        } else {
            String queryConsulta = "SELECT nombreEmp, apellidos, tipoDocumento, documento, correo, nombreSucursal \n"
                    + "FROM empleado INNER JOIN sucursal WHERE empleado.FK_idSucursal = sucursal.idSucursal AND nombreEmp LIKE '%\" + filtroBusqueda + OR apellidos\n"
                    + "LIKE '%\" + filtroBusqueda+ '%";
            System.out.println(queryConsulta); //"SELECT * FROM empleados WHERE nombreEmp LIKE '%" + filtroBusqueda + "%' OR apellidos LIKE '%" + filtroBusqueda + "%' ";

        }
        try {
            connection = conexion.getConection();
            st = connection.createStatement();
            rs = st.executeQuery(filtroBusqueda);

            Object[] empleados = new Object[6];
            contenidoTablaEmpleados = (DefaultTableModel) tblEmpleados.getModel();
            while (rs.next()) {
                empleados[0] = rs.getString("nombreEmp");
                empleados[1] = rs.getString("apellidos");
                empleados[2] = rs.getString("tipoDocumento");
                empleados[3] = rs.getString("documento");
                empleados[4] = rs.getString("correo");
                empleados[5] = rs.getString("nombreSucursal");

                contenidoTablaEmpleados.addRow(empleados);
                //System.out.println("Id:" + empleados[0] + "nombre " + empleados[1]
                // + "apellidos: " + empleados[2] + "tipoDocumento" + empleados[3]
                //+ "documento" + empleados[4] + "correo" + empleados[5]);
                tblEmpleados.setModel(contenidoTablaEmpleados);
            }

        } catch (SQLException e) {
            System.out.println("este es el Error");
        }

    }

    private void listarDepartamentos() {
        String nombreDepartamento = txtDepartamento.getText();
        if (nombreDepartamento.isEmpty()) {
            String query = "SELECT nombreSucursal, nombreDepartamento, CONCAT('Zona',Zona,'.',tipoCalle,' ', numero1,' #No.',numero2,' - ' ,numero3)\n"
                    + "AS direccion\n"
                    + "FROM direccion \n"
                    + "INNER JOIN sucursal\n"
                    + "WHERE idDireccion = FK_idDireccion\n"
                    + "AND nombreDepartamento\n"
                    + "Like'%%' ORDER BY nombreDepartamento;";
            try {
                connection = conexion.getConection();
                st = connection.createStatement();
                rs = st.executeQuery(query);
                Object[] departamentos = new Object[3];
                contenidoTablaDepartamento = (DefaultTableModel) tblDepartamentos.getModel();
                while (rs.next()) {
                    departamentos[0] = rs.getString("nombreSucursal");
                    departamentos[0] = rs.getString("nombreDepartamento");
                    departamentos[0] = rs.getString("direccion");
                    contenidoTablaDepartamento.addRow(departamentos);
                    tblDepartamentos.setModel(contenidoTablaDepartamento);
                }

            } catch (SQLException e) {
                System.out.println("Error");
            }
        } else {
            String query = "SELECT nombreSucursal, nombreDepartamento, CONCAT('Zona',Zona,'.',tipoCalle,' ', numero1,' #No.',numero2,' - ' ,numero3)\n"
                    + "AS direccion\n"
                    + "FROM direccion \n"
                    + "INNER JOIN sucursal\n"
                    + "WHERE idDireccion = FK_idDireccion\n"
                    + "AND nombreDepartamento\n"
                    + "Like'%%' ORDER BY nombreDepartamento;";
            try {
                connection = conexion.getConection();
                st = connection.createStatement();
                rs = st.executeQuery(query);
                Object[] departamentos = new Object[3];
                contenidoTablaDepartamento = (DefaultTableModel) tblDepartamentos.getModel();
                while (rs.next()) {
                    departamentos[0] = rs.getString("nombreSucursal");
                    departamentos[0] = rs.getString("nombreDepartamento");
                    departamentos[0] = rs.getString("direccion");
                    contenidoTablaDepartamento.addRow(departamentos);
                    tblDepartamentos.setModel(contenidoTablaDepartamento);
                }

            } catch (SQLException e) {
                System.out.println("Error");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnAddUser = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        txtBuscarEMP = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpleados = new javax.swing.JTable();
        jpanel111 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtnumero1 = new javax.swing.JTextField();
        txtnumero2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDepartamentos = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        cbDepartamento = new javax.swing.JComboBox<>();
        cbZona = new javax.swing.JComboBox<>();
        cbCalle = new javax.swing.JComboBox<>();
        txtnumero3 = new javax.swing.JTextField();
        txtDepartamento = new javax.swing.JTextField();
        BUSCAR = new javax.swing.JButton();
        btnPuestosTrabajo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAddUser.setText("Nuevo");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        btnSearch.setText("Buscar");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtBuscarEMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarEMPActionPerformed(evt);
            }
        });

        tblEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellidos", "Tipo documento", "Documento", "Correo", "Sucursal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmpleados);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddUser)
                    .addComponent(btnSearch))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscarEMP, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddUser)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBuscarEMP))
                .addContainerGap(130, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Empleados", jPanel1);

        jLabel1.setText("Departamento");

        jLabel2.setText("Tipo calle");

        jLabel3.setText("Zona");

        jLabel4.setText("#");

        jLabel5.setText("-");

        txtnumero1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnumero1ActionPerformed(evt);
            }
        });

        txtnumero2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnumero2ActionPerformed(evt);
            }
        });

        tblDepartamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sucursal", "Departamento", "Dirección"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblDepartamentos);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnListar.setText("Ver sucursales");

        cbDepartamento.setModel(enumDepartamentos);

        cbZona.setModel(enumZonas);

        cbCalle.setModel(enumTipoCalles);
        cbCalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCalleActionPerformed(evt);
            }
        });

        txtnumero3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnumero3ActionPerformed(evt);
            }
        });

        txtDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDepartamentoActionPerformed(evt);
            }
        });

        BUSCAR.setText("BUSCAR");

        btnPuestosTrabajo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/yo3.png"))); // NOI18N
        btnPuestosTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPuestosTrabajoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanel111Layout = new javax.swing.GroupLayout(jpanel111);
        jpanel111.setLayout(jpanel111Layout);
        jpanel111Layout.setHorizontalGroup(
            jpanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel111Layout.createSequentialGroup()
                .addGroup(jpanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanel111Layout.createSequentialGroup()
                        .addGroup(jpanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpanel111Layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(jLabel1)
                                    .addGap(11, 11, 11)
                                    .addComponent(cbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(jLabel2)
                                    .addGap(16, 16, 16)
                                    .addComponent(cbCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(15, 15, 15)
                                    .addComponent(txtnumero1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(11, 11, 11)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4)
                                    .addComponent(txtnumero2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpanel111Layout.createSequentialGroup()
                                .addGap(513, 513, 513)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(204, 204, 204)))
                        .addGap(18, 18, 18)
                        .addGroup(jpanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnumero3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPuestosTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpanel111Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3)
                        .addGap(46, 46, 46)
                        .addComponent(cbZona, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanel111Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(btnGuardar)
                        .addGap(49, 49, 49)
                        .addComponent(btnListar)
                        .addGap(89, 89, 89)
                        .addComponent(txtDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanel111Layout.createSequentialGroup()
                        .addGap(550, 550, 550)
                        .addComponent(BUSCAR)))
                .addContainerGap())
        );
        jpanel111Layout.setVerticalGroup(
            jpanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel111Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jpanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpanel111Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel111Layout.createSequentialGroup()
                        .addComponent(btnPuestosTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)))
                .addGap(9, 9, 9)
                .addGroup(jpanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpanel111Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2))
                    .addComponent(cbCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnumero1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtnumero2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnumero3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jpanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jpanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar)
                    .addComponent(btnListar)
                    .addComponent(txtDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(BUSCAR))
        );

        jTabbedPane1.addTab("Sucursales", jpanel111);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarEMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarEMPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarEMPActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String departamentoOption = cbDepartamento.getSelectedItem().toString();
        String zonaOption = cbZona.getSelectedItem().toString();
        String tipoCalleOption = cbCalle.getSelectedItem().toString();
        String numero1 = "0", numero2 = "0", numero3 = "0";
        numero1 = txtnumero1.getText();
        numero2 = txtnumero2.getText();
        numero3 = txtnumero3.getText();

        String queryDireccion = "INSERT INTO `direccion`(`zona`, `tipoCalle`, `numero1`, `numero2`, `numero3`, `nombreDepartamento`) VALUES ('"
                + zonaOption + "','" + tipoCalleOption + "','" + numero1 + "','" + numero2 + "','" + numero3 + "','" + departamentoOption + "');";
        try {
            connection = conexion.getConection();
            st = connection.createStatement();
            System.out.println("Antes de query");
            st.executeUpdate(queryDireccion);
            System.out.println("Despues de query");
            SucursalForm sucursalForm = new SucursalForm(this, true);
            sucursalForm.setVisible(true);
            String queryIdDireccion = "SELECT `idDireccion` FROM `direccion` WHERE nombreDepartamento = '"
                    + departamentoOption + "' AND zona = '" + zonaOption + "' AND tipoCalle = '" + tipoCalleOption
                    + "' AND numero1 = '" + numero1 + "' AND numero2 = '" + numero2 + "' AND numero3 = '" + numero3 + "';";
            try {
                connection = conexion.getConection();
                st = connection.createStatement();
                System.out.println("Antes de hacer query");
                rs = st.executeQuery(queryIdDireccion);
                System.out.println("Despeus de hacer query");
                int idDireccion;
                while (rs.next()) {
                    int direccion = rs.getInt("idDireccion");
                    sucursalForm.recibeDatosDireccion(direccion);
                }
                //borrarDatosTabladepartamentos();
                //ListarDepartamentos();
            } catch (SQLException e) {
                System.out.println(e);
            }
            JOptionPane.showMessageDialog(this, "Sucursal creada existosamente");

        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "No se pudo crear la sucursal", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    public void ListarDepartamentos() {
        String query = "SELECT DISTINCT nombreDepartamento, zona FROM `direccion`;";
        System.out.println("Entró al metodo de listarDepartamentos");
        try {
            connection = conexion.getConection();
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Object[] departamento = new Object[2];
            contenidoTablaDepartamento = (DefaultTableModel) tblDepartamentos.getModel();
            while (rs.next()) {
                departamento[0] = rs.getString("nombreDepartamento");
                departamento[1] = rs.getString("zona");
                System.out.println("deparatamento: " + departamento[0] + ", zona: " + departamento[1]);
                contenidoTablaDepartamento.addRow(departamento);
                tblDepartamentos.setModel(contenidoTablaDepartamento);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void borrarDatosTabla() {
        for (int i = 0; i < tblEmpleados.getRowCount(); i++) {
            contenidoTablaEmpleados.removeRow(i);
            i -= 1;
        }

    }
    private void cbCalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCalleActionPerformed

    private void txtnumero2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnumero2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnumero2ActionPerformed

    private void txtnumero1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnumero1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnumero1ActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        AddUserForm addUserForm = new AddUserForm(this, rootPaneCheckingEnabled);
        addUserForm.setVisible(rootPaneCheckingEnabled);
        borrarDatosTabla();
        ListarEmpleados();
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tblEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadosMouseClicked
        int row = tblEmpleados.getSelectedRow();
        String sucursal = (String) tblDepartamentos.getValueAt(row, 0);
        System.out.println("Fila Seleccionada n°:" + row);
        System.out.println(sucursal);
        String departamento = (String) tblDepartamentos.getValueAt(row, 0);
        String queryIdSucursal = "SELECT `idSucursal` FROM `sucursal` INNER JOIN `direccion` WHERE FK_idDireccion = idDireccion AND nombreSucursal = '"+ sucursal +"';";
        try{
            connection = conexion.getConection();
            st =connection.createStatement();
            rs = st.executeQuery(queryIdSucursal);
            while (rs.next()){
                int idSucursal = rs.getInt("idSucursal");
                GestionarSucursalesForm gestionarSucursal = new GestionarSucursalesForm(this, true);
                gestionarSucursal.setVisible(true);
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }//GEN-LAST:event_tblEmpleadosMouseClicked

    private void txtnumero3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnumero3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnumero3ActionPerformed

    private void txtDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDepartamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepartamentoActionPerformed

    private void btnPuestosTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPuestosTrabajoActionPerformed
        PuestosTrabajo puestosTrabajo = new PuestosTrabajo(this,true);
        puestosTrabajo.setVisible(true);
    }//GEN-LAST:event_btnPuestosTrabajoActionPerformed

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
            java.util.logging.Logger.getLogger(UserMenu2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserMenu2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserMenu2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserMenu2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserMenu2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BUSCAR;
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnPuestosTrabajo;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbCalle;
    private javax.swing.JComboBox<String> cbDepartamento;
    private javax.swing.JComboBox<String> cbZona;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpanel111;
    private javax.swing.JTable tblDepartamentos;
    private javax.swing.JTable tblEmpleados;
    private javax.swing.JTextField txtBuscarEMP;
    private javax.swing.JTextField txtDepartamento;
    private javax.swing.JTextField txtnumero1;
    private javax.swing.JTextField txtnumero2;
    private javax.swing.JTextField txtnumero3;
    // End of variables declaration//GEN-END:variables
}
