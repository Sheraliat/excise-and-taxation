/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data.Phases;
import data.Tools;
import data.UserSystem;
import db.DBConnection;
import db.DBHandleData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author tanzil
 */
public class CreateReceiveInvoice extends javax.swing.JDialog {

    /**
     * Creates new form CreateReceiveInvoice
     */
    UserSystem user;
    private List<String> list;
    private List<String> list1;
    int Maxe;
    CreateSaleInvoice parent;
    public CreateReceiveInvoice(java.awt.Dialog parent, boolean modal, UserSystem user, String n) {
        super(parent, modal);
        Tools.LookAndFeel();
        initComponents();
        this.user = user;
        this.setLocationRelativeTo(null);
        if (parent != null) {
            this.parent = (CreateSaleInvoice) parent;
        }
        setData();
        list = new ArrayList<String>();
        list1 = new ArrayList<String>();
        Tools.initlist("SELECT SuppName FROM Supplier", list);
        Tools.initlist("SELECT CarName FROM Car", list1);

        Tools.autoComplete(txtSuppName, list);
        Tools.autoComplete(txtVehicleName, list1);
        if (!n.equals("")) {
            EvtVehicleName(n);
        }
    }

    public void setData() {
        Date aDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = formatter.format(aDate);
        txtDSale.setText(formattedDate);
        txtPName.setText(user.getTendd());
    }

    public void Reset() {
        txtAddSupp.setText("");
        txtIDSupp.setText("");
        txtBrand.setText("");
        txtIDVehicle.setText("");
        txtPrice.setText("");
        txtModel.setText("");
        txtEmailSupp.setText("");
        txtPhoneSupp.setText("");
        txtQuantity.setText("");
        txtYear.setText("");
        txtSuppName.setText("");
        txtVehicleName.setText("");
    }

    public void EvtSuppName(String Name) {
        list1 = new ArrayList<String>();
        Tools.initlist("SELECT SuppName FROM Supplier", list1);
        txtSuppName.setText(Name);
        ResultSet rs = null;
        try {
            rs = DBHandleData.SelectData("SELECT * FROM Supplier WHERE SuppName like '%" + Name + "%'");
            if (rs.next()) {
                txtIDSupp.setText(rs.getString(1));
                txtAddSupp.setText(rs.getString(3));
                txtPhoneSupp.setText(rs.getString(4));
                txtEmailSupp.setText(rs.getString(5));
            } else {
                int c = JOptionPane.showConfirmDialog(this, Phases.QUESTION_ADD_SUPPLIER, Phases.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                if (c == JOptionPane.OK_OPTION) {
                    new AddSupplier(this, true).setVisible(true);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateReceiveInvoice.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConn(null, null, rs);
        }
    }

    public void EvtVehicleName(String Name) {
        txtVehicleName.setText(Name);
        ResultSet rs = null;
        try {
            rs = DBHandleData.SelectData("SELECT Car.IDCar, Model.ModelName, Brand.BrandName, Car.YearCar, Car.Status "
                    + "FROM Car INNER JOIN Model ON Car.IDModel = Model.IDModel "
                    + "INNER JOIN Brand ON Model.IDBrand = Brand.IDBrand "
                    + "WHERE Car.CarName like '" + Name + "'");
            if (rs.next()) {
                Maxe = Integer.parseInt(rs.getString(1));
                if (Integer.parseInt(rs.getString(5)) == 1) {
                    int c = JOptionPane.showConfirmDialog(this, Phases.QUESTION_ADD_SaleInvoice, Phases.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                    if (c == JOptionPane.OK_OPTION) {
                        new AddCar(this, true, 2, user).setVisible(true);
                    }
                    txtQuantity.requestFocus();
                } else {
                    txtIDVehicle.setText(rs.getString(1));
                    txtModel.setText(rs.getString(2));
                    txtBrand.setText(rs.getString(3));
                    txtYear.setText(rs.getString(4));
                }
            } else {
                int c = JOptionPane.showConfirmDialog(this, Phases.QUESTION_ADD_Car, Phases.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                if (c == JOptionPane.OK_OPTION) {
                    new AddCar(this, true, 2, user).setVisible(true);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateReceiveInvoice.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConn(null, null, rs);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblSupplierName = new javax.swing.JLabel();
        lblIDSupp = new javax.swing.JLabel();
        txtIDSupp = new javax.swing.JTextField();
        lblAddSupp = new javax.swing.JLabel();
        txtAddSupp = new javax.swing.JTextField();
        lblPhoneSupp = new javax.swing.JLabel();
        txtPhoneSupp = new javax.swing.JTextField();
        lblEmailSupp = new javax.swing.JLabel();
        txtEmailSupp = new javax.swing.JTextField();
        btnAddSupp = new javax.swing.JButton();
        txtSuppName = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txtIDVehicle = new javax.swing.JTextField();
        lblModel = new javax.swing.JLabel();
        txtYear = new javax.swing.JTextField();
        txtModel = new javax.swing.JTextField();
        lblVehicleName = new javax.swing.JLabel();
        lblBrand = new javax.swing.JLabel();
        txtVehicleName = new javax.swing.JTextField();
        txtBrand = new javax.swing.JTextField();
        lblYear = new javax.swing.JLabel();
        lblIDSupp1 = new javax.swing.JLabel();
        btnAddVehicle = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtPName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDSale = new javax.swing.JTextField();
        lblQuantity = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        lblPrice = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        btnSubmit = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create A Bill");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Create Receive  Invoice", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(0, 0, 0))); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Supplier", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(0, 102, 204))); // NOI18N

        lblSupplierName.setText("Supplier Name:");

        lblIDSupp.setText("ID:");
        lblIDSupp.setEnabled(false);

        txtIDSupp.setEditable(false);

        lblAddSupp.setText("Address:");
        lblAddSupp.setEnabled(false);

        txtAddSupp.setEditable(false);

        lblPhoneSupp.setText("Phone: ");
        lblPhoneSupp.setEnabled(false);

        txtPhoneSupp.setEditable(false);

        lblEmailSupp.setText("Email: ");
        lblEmailSupp.setEnabled(false);

        txtEmailSupp.setEditable(false);

        btnAddSupp.setText("Add ");
        btnAddSupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSuppActionPerformed(evt);
            }
        });

        txtSuppName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSuppNameFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPhoneSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSuppName, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(btnAddSupp)))
                        .addGap(36, 36, 36)
                        .addComponent(txtPhoneSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIDSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAddSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtAddSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtIDSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(71, 71, 71)
                        .addComponent(lblEmailSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(txtEmailSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPhoneSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhoneSupp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddSupp)
                            .addComponent(txtSuppName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIDSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDSupp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAddSupp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAddSupp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEmailSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailSupp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vehicle", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(0, 102, 204))); // NOI18N

        txtIDVehicle.setEditable(false);

        lblModel.setText("Model: ");
        lblModel.setEnabled(false);

        txtYear.setEditable(false);

        txtModel.setEditable(false);

        lblVehicleName.setText("Vehicle Name:");

        lblBrand.setText("Brand: ");
        lblBrand.setEnabled(false);

        txtVehicleName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtVehicleNameFocusLost(evt);
            }
        });

        txtBrand.setEditable(false);

        lblYear.setText("Year: ");
        lblYear.setEnabled(false);

        lblIDSupp1.setText("ID:");
        lblIDSupp1.setEnabled(false);

        btnAddVehicle.setText("Add");
        btnAddVehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddVehicleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblVehicleName, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVehicleName, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnAddVehicle))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIDSupp1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblModel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtIDVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblYear, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVehicleName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVehicleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddVehicle))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIDSupp1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblYear, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Other", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(0, 102, 204))); // NOI18N

        txtPName.setEnabled(false);

        jLabel2.setText("Staff Name:");
        jLabel2.setEnabled(false);

        jLabel4.setText("Date Time:");
        jLabel4.setEnabled(false);

        txtDSale.setEnabled(false);

        lblQuantity.setText("Quantity:");

        lblPrice.setText("Price:");

        btnSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Clear Green Button.png"))); // NOI18N
        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Yellow Ball.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Cancel Red Button.png"))); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSubmit)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPName, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDSale, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(68, 68, 68)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel)))
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(lblPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSuppNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSuppNameFocusLost
        // TODO add your handling code here:
        if (!txtSuppName.getText().equals("")) {
            EvtSuppName(txtSuppName.getText());
        }


    }//GEN-LAST:event_txtSuppNameFocusLost

    private void txtVehicleNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVehicleNameFocusLost
        // TODO add your handling code here:
        if (!txtVehicleName.getText().equals("")) {
            EvtVehicleName(txtVehicleName.getText());
        }


    }//GEN-LAST:event_txtVehicleNameFocusLost

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        ResultSet rs = null;
        ResultSet rs1 = null;
        try {
            String s = "SELECT YearCar FROM Car WHERE IDCar = " + Maxe;
            rs = DBHandleData.SelectData(s);
            if (rs.next()) {
                if (rs.getString(1) != null) {
                    this.dispose();
                } else {
                    int c = JOptionPane.showConfirmDialog(this, Phases.QUESTION_REGISTER_Car, Phases.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                    if (c == JOptionPane.OK_OPTION) {
                        try {
                            String sql = "SELECT Car.IDCar, Car.CarName, Brand.BrandName, Model.ModelName, Car.Price, "
                                    + "Car.Picture1, Car.Picture2, Car.Picture3, Car.Picture4,Car.YearCar, Car.Engine, "
                                    + "Car.EngineType, Car.Fuel, Car.Tranmission, Car.MaxCapacity, Car.MaxSpeed, Car.Length, Car.Width, "
                                    + "Car.Height, Car.Weight, Car.Numberseat, Car.Status "
                                    + "FROM  Car INNER JOIN Model ON Car.IDModel = Model.IDModel INNER JOIN "
                                    + "Brand ON Model.IDBrand = Brand.IDBrand "
                                    + "WHERE Car.IDCar = " + Maxe;
                            rs1 = DBHandleData.SelectData(sql);
                            Vector v = new Vector();
                            if (rs1.next()) {
                                for (int i = 1; i < 23; i++) {
                                    v.add(rs1.getString(i));
                                }
                            }
                            new Register(this, true, v, 1).setVisible(true);
                            parent.EvtVehicleName(txtVehicleName.getText());
                            this.dispose();
                        } catch (SQLException ex) {
                            Logger.getLogger(CreateReceiveInvoice.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            DBConnection.closeConn(null, null, rs1);
                        }
                    } else {
                        this.dispose();
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateReceiveInvoice.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConn(null, null, rs);
            this.dispose();
        }
        
    }//GEN-LAST:event_btnCancelActionPerformed
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:

        if (txtSuppName.getText().equals("") || txtVehicleName.getText().equals("")
                || txtQuantity.getText().equals("") || txtPrice.getText().equals("")) {
            JOptionPane.showMessageDialog(this, Phases.ERROR_EMPTY_FIELD, Phases.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
        } else {
            Pattern p = Pattern.compile("\\d+");
            Matcher matcher = p.matcher(txtPrice.getText());
            Matcher m1 = p.matcher(txtQuantity.getText());
            Date aDate = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = formatter.format(aDate);
            String sql = "INSERT ReceiveInvoice VALUES (?, ?, ?, '" + formattedDate + "', ?, ?, 1)";
            System.out.println(sql);
            if (!matcher.matches() || !m1.matches()) {
                JOptionPane.showMessageDialog(this, Phases.INVALID_FORMAT, Phases.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
            } else {
                String[] data = {txtIDSupp.getText(), Integer.toString(user.getMaqt()), txtIDVehicle.getText(),
                    txtQuantity.getText(), txtPrice.getText()};
                int rows = db.DBHandleData.UpdateData(sql, data);
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, Phases.ADD_SUCCESSFULL);
                    String s1 = "UPDATE Car SET Price = ?, Status = 1 WHERE IDCar = ?";
                    String[] d = {txtPrice.getText(), txtIDVehicle.getText()};
                    DBHandleData.UpdateData(s1, d);
                } else {
                    JOptionPane.showMessageDialog(this, Phases.ADD_FAILURE, Phases.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnAddSuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSuppActionPerformed
        // TODO add your handling code here:
        new AddSupplier(this, true).setVisible(true);
    }//GEN-LAST:event_btnAddSuppActionPerformed

    private void btnAddVehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddVehicleActionPerformed
        // TODO add your handling code here:
        new AddCar(this, true, 2, user).setVisible(true);
    }//GEN-LAST:event_btnAddVehicleActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddSupp;
    private javax.swing.JButton btnAddVehicle;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblAddSupp;
    private javax.swing.JLabel lblBrand;
    private javax.swing.JLabel lblEmailSupp;
    private javax.swing.JLabel lblIDSupp;
    private javax.swing.JLabel lblIDSupp1;
    private javax.swing.JLabel lblModel;
    private javax.swing.JLabel lblPhoneSupp;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblSupplierName;
    private javax.swing.JLabel lblVehicleName;
    private javax.swing.JLabel lblYear;
    private javax.swing.JTextField txtAddSupp;
    private javax.swing.JTextField txtBrand;
    private javax.swing.JTextField txtDSale;
    private javax.swing.JTextField txtEmailSupp;
    private javax.swing.JTextField txtIDSupp;
    private javax.swing.JTextField txtIDVehicle;
    private javax.swing.JTextField txtModel;
    private javax.swing.JTextField txtPName;
    private javax.swing.JTextField txtPhoneSupp;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSuppName;
    private javax.swing.JTextField txtVehicleName;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables
}
