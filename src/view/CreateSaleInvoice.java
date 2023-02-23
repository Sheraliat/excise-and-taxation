/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data.Payment;
import data.Phases;
import data.Tools;
import data.UserSystem;
import db.DBConnection;
import db.DBHandleData;
import db.DBInitData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author tanzil
 */
public final class CreateSaleInvoice extends javax.swing.JDialog {

    /**
     * Creates new form CreateSaleInvoice
     */
    UserSystem user;
    private List<String> list;
    private List<String> list1;
    
    public CreateSaleInvoice(java.awt.Frame parent, boolean modal, UserSystem user) {
        super(parent, modal);
        initComponents();
        Tools.LookAndFeel();
        this.user = user;
        this.setLocationRelativeTo(null);
        DBInitData.initPay(cbbPaymethod);
        setData();
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        Tools.initlist("SELECT Fullname FROM Customer", list);
        Tools.initlist("SELECT CarName FROM Car", list1);

        Tools.autoComplete(txtCusName, list);
        Tools.autoComplete(txtVehicleName, list1);
    }

    public void setData() {
        Date aDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = formatter.format(aDate);
        txtDSale.setText(formattedDate);
        txtPName.setText(user.getTendd());
    }

    public void Reset() {
        txtCusName.setText("");
        txtVehicleName.setText("");
        txtPrice.setText("");
        txtAmount.setText("");
        txtPYear.setText("");
        txtLastDate.setText("");
        txtNextDate.setText("");
        txtAmountByMonth.setText("");
        cbbPaymethod.setSelectedIndex(0);
    }
    Float giaxe;

    public void EvtVehicleName(String Name) {
        giaxe = 0f;
        txtVehicleName.setText(Name);
        ResultSet rs = null;
        try {
            String sql = "SELECT Car.IDCar, Car.CarName, Brand.BrandName, Model.ModelName, "
                + "Car.Price + Car.Price * 0.15 AS Price ,Car.Picture1, Car.Picture2, Car.Picture3, "
                + "Car.Picture4,Car.YearCar, Car.Engine, Car.EngineType, Car.Fuel, Car.Tranmission, "
                + "Car.MaxCapacity, Car.MaxSpeed, Car.Length, Car.Width, Car.Height, Car.Weight, "
                + "Car.Numberseat, Car.Status "
                + "FROM  Car INNER JOIN Model ON Car.IDModel = Model.IDModel "
                + "INNER JOIN Brand ON Model.IDBrand = Brand.IDBrand "
                + "WHERE Car.CarName like '" + Name + "'";
            rs = DBHandleData.SelectData(sql);
            if (rs.next()) {
                if (!checkSale(Integer.parseInt(rs.getString(1)))) {
                    JOptionPane.showMessageDialog(this, Phases.SOLD_CAR, Phases.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE);
                    txtVehicleName.setText("");
                    txtVehicleName.requestFocus();
                } else {
                    if (Integer.parseInt(rs.getString(22)) == 1) {
                        txtIDCar.setText(rs.getString(1));
                        txtModel.setText(rs.getString(4));
                        txtBrand.setText(rs.getString(3));
                        txtYear.setText(rs.getString(10));
                        giaxe = Float.parseFloat(rs.getString(5));
                    } else {
                        int c = JOptionPane.showConfirmDialog(this, Phases.SELL_CAR_NOT_AVAIBLE, Phases.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                        if (c == JOptionPane.OK_OPTION) {
                            Vector v = new Vector();
                            for (int i = 1; i < 23; i++) {
                                v.add(rs.getString(i));
                            }
                            new CreateReceiveInvoice(this, true, user,(String) rs.getString(2)).setVisible(true);
                        } else {
                            txtVehicleName.setText("");
                            txtPrice.setText("");
                        }
                    }

                }
            } else {
                int choose = JOptionPane.showConfirmDialog(this, Phases.QUESTION_ADD_Car, Phases.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                if (choose == JOptionPane.OK_OPTION) {
                    new AddCar(this, true,1,user).setVisible(true);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateSaleInvoice.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConn(null, null, rs);
        }
    }

    public Boolean checkSale(int ma) {
        Boolean check = true;
        ResultSet rs = null;
        String sql = "SELECT Car.CarName,COUNT(SaleInvoice.IDCar) AS Quantity, ReceiveInvoice.Quantity "
                + "FROM Car INNER JOIN SaleInvoice ON Car.IDCar = SaleInvoice.IDCar "
                + "INNER JOIN ReceiveInvoice ON Car.IDCar = ReceiveInvoice.IDCar "
                + "WHERE Car.IDCar = " + ma + " AND Car.Status = 1 "
                + "GROUP BY Car.CarName, ReceiveInvoice.Quantity ";
        String[] data = {Integer.toString(ma)};
        rs = DBHandleData.SelectData(sql);
        try {
            if (rs.next()) {
                if (Integer.parseInt(rs.getString(2)) < Integer.parseInt(rs.getString(3))) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateSaleInvoice.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConn(null, null, rs);
            return check;
        }

    }

    public void EvtCusName(String Name) {
        txtCusName.setText(Name);
        ResultSet rs = null;
        try {
            rs = DBHandleData.SelectData("SELECT * FROM Customer WHERE Fullname like '%" + Name + "%'");
            if (rs.next()) {
                txtIDCus.setText(rs.getString(1));
                txtAddress.setText(rs.getString(4));
                txtPhone.setText(rs.getString(5));
                txtIDCard.setText(rs.getString(6));
            } else {
                int c = JOptionPane.showConfirmDialog(this, Phases.QUESTION_ADD_Customer, Phases.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                if (c == JOptionPane.OK_OPTION) {
                    new AddCustomer(this, true, user).setVisible(true);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateSaleInvoice.class.getName()).log(Level.SEVERE, null, ex);
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
        jPanel6 = new javax.swing.JPanel();
        txtIDCus = new javax.swing.JTextField();
        lblAddress = new javax.swing.JLabel();
        txtIDCard = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        lblCusName = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        txtCusName = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        lblIDCard = new javax.swing.JLabel();
        lblIDCus = new javax.swing.JLabel();
        btnAddCus = new javax.swing.JButton();
        lblVehicleName = new javax.swing.JLabel();
        btnAddVehicle = new javax.swing.JButton();
        txtVehicleName = new javax.swing.JTextField();
        txtIDCar = new javax.swing.JTextField();
        txtModel = new javax.swing.JTextField();
        txtBrand = new javax.swing.JTextField();
        txtYear = new javax.swing.JTextField();
        lblYear = new javax.swing.JLabel();
        lblBrand = new javax.swing.JLabel();
        lblModel = new javax.swing.JLabel();
        lblIDCar = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtPName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDSale = new javax.swing.JTextField();
        lblQuantity = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        lblPrice = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        btnSubmit = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblPay = new javax.swing.JLabel();
        cbbPaymethod = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPYear = new javax.swing.JTextField();
        txtLastDate = new javax.swing.JTextField();
        txtAmountByMonth = new javax.swing.JTextField();
        txtNextDate = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create Sales");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Create Sale Invoice", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel1.setToolTipText("");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Customer & Vehicle", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(0, 102, 204))); // NOI18N

        txtIDCus.setEditable(false);

        lblAddress.setText("Address: ");
        lblAddress.setEnabled(false);

        txtIDCard.setEditable(false);

        txtAddress.setEditable(false);

        lblCusName.setText("Customer Name:");

        lblPhone.setText("Phone:");
        lblPhone.setEnabled(false);

        txtCusName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCusNameFocusLost(evt);
            }
        });

        txtPhone.setEditable(false);

        lblIDCard.setText("Identity Card:");
        lblIDCard.setEnabled(false);

        lblIDCus.setText("ID:");
        lblIDCus.setEnabled(false);

        btnAddCus.setText("Add");
        btnAddCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCusActionPerformed(evt);
            }
        });

        lblVehicleName.setText("Vehicle Name:");

        btnAddVehicle.setText("Add");
        btnAddVehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddVehicleActionPerformed(evt);
            }
        });

        txtVehicleName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtVehicleNameFocusLost(evt);
            }
        });

        txtIDCar.setEditable(false);

        txtModel.setEditable(false);

        txtBrand.setEditable(false);

        txtYear.setEditable(false);

        lblYear.setText("Year: ");
        lblYear.setEnabled(false);

        lblBrand.setText("Brand: ");
        lblBrand.setEnabled(false);

        lblModel.setText("Model: ");
        lblModel.setEnabled(false);

        lblIDCar.setText("ID:");
        lblIDCar.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblCusName, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(txtCusName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddCus)
                .addGap(28, 28, 28)
                .addComponent(lblVehicleName, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtVehicleName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddVehicle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIDCard, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                    .addComponent(lblPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIDCus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDCus, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDCard, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblYear, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblIDCar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblModel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtModel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtIDCar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(76, 76, 76))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddCus)
                    .addComponent(btnAddVehicle)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCusName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCusName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVehicleName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVehicleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIDCus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(lblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIDCard, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIDCar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDCar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblModel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblYear, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Other", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(0, 102, 204))); // NOI18N

        txtPName.setEnabled(false);

        jLabel2.setText("Staff Name:");
        jLabel2.setEnabled(false);

        jLabel4.setText("Sale Date:");
        jLabel4.setEnabled(false);

        txtDSale.setEnabled(false);

        lblQuantity.setText("Price:");

        txtPrice.setEditable(false);

        lblPrice.setText("First amount:");

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnSubmit)
                        .addGap(65, 65, 65)
                        .addComponent(btnReset)
                        .addGap(58, 58, 58)
                        .addComponent(btnCancel)
                        .addGap(105, 105, 105))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDSale, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPName, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPName)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDSale)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPrice)
                            .addComponent(lblQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAmount)
                            .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblPay.setText("Pay Method:");

        cbbPaymethod.setEditable(true);
        cbbPaymethod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbPaymethodActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Year:");

        jLabel5.setText("Last Date:");

        jLabel6.setText("Amount:");

        jLabel7.setText("Next Date:");

        txtPYear.setEditable(false);

        txtLastDate.setEditable(false);

        txtAmountByMonth.setEditable(false);

        txtNextDate.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPYear, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLastDate, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNextDate, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAmountByMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPYear)
                    .addComponent(txtAmountByMonth))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLastDate)
                    .addComponent(txtNextDate))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPay, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbPaymethod, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPay, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbPaymethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        try {
            // TODO add your handling code here:
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            
            if (c == 0) {
                JOptionPane.showMessageDialog(this, Phases.ERROR_SELECT_PAY, Phases.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
                cbbPaymethod.requestFocus();
            } else if (c == 1) {
                System.out.println(c);
                String sql = "INSERT SaleInvoice(IDCus,IDCar,IDUser,IDPay,SaleDate,SellPrice,Installment,LastDate,Status) "
                        + "VALUES (?,?,?,1,'" + formatter.format(d) + "',?,?,'" + formatter.format(d) + "',1)";
                String[] data = {txtIDCus.getText(), txtIDCar.getText(), Integer.toString(user.getMaqt()),
                    txtPrice.getText(), txtAmount.getText()};
                int row = DBHandleData.UpdateData(sql, data);
                if (row > 0) {
                    JOptionPane.showMessageDialog(this, Phases.INSERT_SUCCESSFULL, Phases.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, Phases.INSERT_FAILURE, Phases.INFORMATION_MESSAGE, JOptionPane.ERROR_MESSAGE);
                }
            } else if (c == 2){
                Date last = df.parse(txtLastDate.getText());
                System.out.println(c);
                String sql = "INSERT SaleInvoice(IDCus,IDCar,IDUser,IDPay,SaleDate,SellPrice,Installment,LastDate,Status) "
                        + "VALUES (?,?,?,2,'" + formatter.format(d) + "',?,?,'" + formatter.format(last) + "',0)";
                String[] data = {txtIDCus.getText(), txtIDCar.getText(), Integer.toString(user.getMaqt()),
                    txtPrice.getText(), txtAmount.getText()};
                int row = DBHandleData.UpdateData(sql, data);
                if (row > 0) {
                    JOptionPane.showMessageDialog(this, Phases.INSERT_SUCCESSFULL, Phases.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, Phases.INSERT_FAILURE, Phases.INFORMATION_MESSAGE, JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(CreateSaleInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtCusNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCusNameFocusLost
        // TODO add your handling code here:
        if (!txtCusName.getText().equals("")) {
            EvtCusName(txtCusName.getText());
        }
//        txtVehicleName.requestFocus();

    }//GEN-LAST:event_txtCusNameFocusLost

    private void btnAddCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCusActionPerformed
        // TODO add your handling code here:
        new AddCustomer(this, true, user).setVisible(true);
    }//GEN-LAST:event_btnAddCusActionPerformed

    private void btnAddVehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddVehicleActionPerformed
        // TODO add your handling code here:
        new AddCar(this, true,1,user).setVisible(true);
    }//GEN-LAST:event_btnAddVehicleActionPerformed

    private void txtVehicleNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVehicleNameFocusLost
        // TODO add your handling code here:
        if (!txtVehicleName.getText().equals("")) {
            EvtVehicleName(txtVehicleName.getText());
            txtPrice.setText(Float.toString(giaxe));
        }


    }//GEN-LAST:event_txtVehicleNameFocusLost
    int c;
    private void cbbPaymethodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbPaymethodActionPerformed
        // TODO add your handling code here:
        Payment p = (Payment) cbbPaymethod.getSelectedItem();
        if (p.getID() == 1) {
            c = 1;
            txtPYear.setText("");
            txtLastDate.setText("");
            txtNextDate.setText("");
            txtAmountByMonth.setText("");
            txtAmount.setText(txtPrice.getText());
            txtAmount.setEditable(false);
        } else if (p.getID() == 2) {
            c = 2;
            Float amount = Float.parseFloat(txtPrice.getText()) * 0.4f;
            txtAmount.setEditable(false);
            txtAmount.setText(Float.toString(amount));
            txtPYear.setText("3");
            Calendar calender = Calendar.getInstance();
            SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
            calender.add(Calendar.MONTH, 6);
            txtNextDate.setText(sim.format(calender.getTime()));
            calender.add(Calendar.YEAR, 3);
            txtLastDate.setText(sim.format(calender.getTime()));
            Float f = (Float.parseFloat(txtPrice.getText()) - amount) / 6;
            txtAmountByMonth.setText(Float.toString(f));

        } else if (p.getID() == -1) {
            c = 0;
            txtPYear.setText("");
            txtLastDate.setText("");
            txtNextDate.setText("");
            txtAmountByMonth.setText("");
            txtAmount.setText("");
            txtAmount.setEditable(true);
        }

    }//GEN-LAST:event_cbbPaymethodActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCus;
    private javax.swing.JButton btnAddVehicle;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox cbbPaymethod;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblBrand;
    private javax.swing.JLabel lblCusName;
    private javax.swing.JLabel lblIDCar;
    private javax.swing.JLabel lblIDCard;
    private javax.swing.JLabel lblIDCus;
    private javax.swing.JLabel lblModel;
    private javax.swing.JLabel lblPay;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblVehicleName;
    private javax.swing.JLabel lblYear;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtAmountByMonth;
    private javax.swing.JTextField txtBrand;
    private javax.swing.JTextField txtCusName;
    private javax.swing.JTextField txtDSale;
    private javax.swing.JTextField txtIDCar;
    private javax.swing.JTextField txtIDCard;
    private javax.swing.JTextField txtIDCus;
    private javax.swing.JTextField txtLastDate;
    private javax.swing.JTextField txtModel;
    private javax.swing.JTextField txtNextDate;
    private javax.swing.JTextField txtPName;
    private javax.swing.JTextField txtPYear;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtVehicleName;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables
}
