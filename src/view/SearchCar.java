/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data.Brand;
import data.Model;
import data.Phases;
import data.Tools;
import data.UserSystem;
import db.DBInitData;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author tanzil
 */
public class SearchCar extends javax.swing.JDialog {

    /**
     * Creates new form SearchCar
     */
    UserSystem user;

    public SearchCar(java.awt.Frame parent, boolean modal, UserSystem user) {
        super(parent, modal);

        initComponents();
        Tools.LookAndFeel();
        this.user = user;
        this.setLocationRelativeTo(null);
        DBInitData.initHangxe(cbbHangsx);
        if (user.getMaqt()==1) {
            btnManagerHangxe.setEnabled(false);
            btnManagerModel.setEnabled(false);
        }
    }

    public void NoData() {
//        lblTxe.setText("");
        lblAnh1.setIcon(null);
        lblAnh2.setIcon(null);
        lblAnh3.setIcon(null);
        lblAnh4.setIcon(null);
        lblAnh1.setText("No Images");
        lblAnh2.setText("No Images");
        lblAnh3.setText("No Images");
        lblAnh4.setText("No Images");
    }

    public void HaveData(int i) {
        lblAnh1.setText("");
        lblAnh2.setText("");
        lblAnh3.setText("");
        lblAnh4.setText("");
        Tools.fitImageToLabel(lblAnh1, (String) tblCar.getValueAt(i, 7));
        Tools.fitImageToLabel(lblAnh2, (String) tblCar.getValueAt(i, 8));
        Tools.fitImageToLabel(lblAnh3, (String) tblCar.getValueAt(i, 9));
        Tools.fitImageToLabel(lblAnh4, (String) tblCar.getValueAt(i, 10));
    }

    public void Search() {
        Pattern p = Pattern.compile("\\d+");
        Matcher matcher = p.matcher(txtMaxe.getText());
        Matcher matcher1 = p.matcher(txtGiaxe1.getText());
        Matcher matcher2 = p.matcher(txtGiaxe2.getText());
        Brand hsx = (Brand) cbbHangsx.getSelectedItem();
        Model m = (Model) cbbModel.getSelectedItem();
        String sql = "SELECT Car.IDCar, Car.CarName, Brand.BrandName, Model.ModelName, Car.YearCar, "
                + "Car.Price, CASE Car.Status WHEN 1 THEN 'AVAILABE' ELSE 'NOT AVAILABE' END AS Status,  "
                + "Car.Picture1, Car.Picture2, Car.Picture3, Car.Picture4, Car.Engine, Car.EngineType, Car.Fuel, "
                + "Car.Tranmission, Car.MaxCapacity, Car.MaxSpeed, Car.Length, Car.Width, "
                + "Car.Height, Car.Weight, Car.Numberseat "
                + "FROM  Car INNER JOIN Model ON Car.IDModel = Model.IDModel INNER JOIN "
                + "Brand ON Model.IDBrand = Brand.IDBrand "
                + "WHERE 1 = 1";
        String s2 = sql;
        if (!txtMaxe.getText().equals("")) {
            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(this, Phases.INVALID_NUMBER_FORMAT, Phases.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                sql += " and Car.IDCar = " + txtMaxe.getText().trim();
            }
        }
        if (!txtTenxe.getText().equals("")) {
            sql += " and Car.CarName like '%" + txtTenxe.getText().trim() + "%'";
        }
        if (!txtGiaxe1.getText().equals("")) {
            if (!matcher1.matches()) {
                JOptionPane.showMessageDialog(this, Phases.INVALID_NUMBER_FORMAT, Phases.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                sql += " and Car.Price > " + Float.parseFloat(txtGiaxe1.getText().trim());
            }

        }
        if (!txtGiaxe2.getText().equals("")) {
            if (!matcher2.matches()) {
                JOptionPane.showMessageDialog(this, Phases.INVALID_NUMBER_FORMAT, Phases.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                sql += " and Car.Price < " + Float.parseFloat(txtGiaxe2.getText().trim());
            }

        }
        if (cbbHangsx.getSelectedIndex() != 0) {
            sql += " and Brand.BrandName like '%" + hsx.Name + "%'";
        }
        if (cbbModel.getSelectedIndex() != 0) {
            sql += " and Model.ModelName like '%" + m.ModelName + "%'";
        }
        if (cbbTrangthaixe.getSelectedIndex() != 0) {
            int i = cbbTrangthaixe.getSelectedIndex() - 1;
            sql += " and Car.Status = " + i;
        }
        DBInitData.initVehicle(tblCar, sql, s2);
        int row = tblCar.getRowCount();
        Tools.hideColumnFrom(tblCar, 7);
        if (row == 0) {
            NoData();
            JOptionPane.showMessageDialog(this, Phases.SEARCH_NOT_FOUND, Phases.INFORMATION_MESSAGE, JOptionPane.WARNING_MESSAGE);
        } else {
            String s1 = (String) tblCar.getValueAt(0, 7);
            lblTxe.setText((String) tblCar.getValueAt(0, 1));
            if (s1 == null) {
                NoData();
            } else {
                HaveData(0);
            }
        }
    }

    public void Reset() {
        txtMaxe.setText("");
        txtTenxe.setText("");
        txtGiaxe1.setText("");
        txtGiaxe2.setText("");
        cbbHangsx.setSelectedIndex(0);
        cbbModel.setSelectedIndex(0);
        cbbTrangthaixe.setSelectedIndex(0);
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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblHangsx = new javax.swing.JLabel();
        lblModel = new javax.swing.JLabel();
        lblMaxe = new javax.swing.JLabel();
        lblTenxe = new javax.swing.JLabel();
        lblTrangthaixe = new javax.swing.JLabel();
        cbbTrangthaixe = new javax.swing.JComboBox();
        txtTenxe = new javax.swing.JTextField();
        txtMaxe = new javax.swing.JTextField();
        cbbModel = new javax.swing.JComboBox();
        cbbHangsx = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        lblTu = new javax.swing.JLabel();
        txtGiaxe1 = new javax.swing.JTextField();
        lblDen = new javax.swing.JLabel();
        txtGiaxe2 = new javax.swing.JTextField();
        btnReset = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnManagerHangxe = new javax.swing.JButton();
        btnManagerModel = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblAnh2 = new javax.swing.JLabel();
        lblAnh3 = new javax.swing.JLabel();
        lblAnh4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblAnh1 = new javax.swing.JLabel();
        lblTxe = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCar = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search Vehicle");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Vehicle", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lblHangsx.setText("Brand:");

        lblModel.setText("Model: ");

        lblMaxe.setText("Vehicle's ID:");

        lblTenxe.setText("Vehicle's Name:");

        lblTrangthaixe.setText("Status:");

        cbbTrangthaixe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT STATUS", "NOT AVAILABLE", "AVAILABLE" }));
        cbbTrangthaixe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangthaixeActionPerformed(evt);
            }
        });

        cbbHangsx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbHangsxActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search with Vehicle's Price", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        lblTu.setText("From:");

        lblDen.setText("To:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTu)
                .addGap(1, 1, 1)
                .addComponent(txtGiaxe1, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtGiaxe2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTu)
                    .addComponent(txtGiaxe1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaxe2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDen))
                .addGap(28, 28, 28))
        );

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Yellow Ball.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Spotlight Blue Button.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnManagerHangxe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Menu Item.png"))); // NOI18N
        btnManagerHangxe.setText("Manage");
        btnManagerHangxe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManagerHangxeActionPerformed(evt);
            }
        });

        btnManagerModel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Menu Item.png"))); // NOI18N
        btnManagerModel.setText("Manage");
        btnManagerModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManagerModelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(btnReset)
                                .addGap(36, 36, 36)
                                .addComponent(btnSearch))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblTenxe, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblMaxe, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblModel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblHangsx, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblTrangthaixe, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaxe)
                                    .addComponent(txtTenxe)
                                    .addComponent(cbbTrangthaixe, 0, 237, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(cbbModel, javax.swing.GroupLayout.Alignment.LEADING, 0, 124, Short.MAX_VALUE)
                                            .addComponent(cbbHangsx, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnManagerHangxe)
                                            .addComponent(btnManagerModel))))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 35, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblHangsx)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnManagerHangxe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbHangsx))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblModel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnManagerModel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbModel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaxe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenxe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenxe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTrangthaixe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTrangthaixe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAnh2.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        lblAnh2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnh2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblAnh2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel6.add(lblAnh2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 136, 100));

        lblAnh3.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        lblAnh3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnh3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblAnh3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel6.add(lblAnh3, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 0, 143, 100));

        lblAnh4.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        lblAnh4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnh4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblAnh4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblAnh4.setRequestFocusEnabled(false);
        lblAnh4.setVerifyInputWhenFocusTarget(false);
        jPanel6.add(lblAnh4, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 0, 134, 100));

        jPanel4.add(jPanel6, java.awt.BorderLayout.PAGE_END);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAnh1.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        lblAnh1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnh1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblAnh1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel7.add(lblAnh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 433, 255));

        lblTxe.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lblTxe.setForeground(new java.awt.Color(255, 0, 0));
        lblTxe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel7.add(lblTxe, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 272, 209, -1));

        jPanel4.add(jPanel7, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tblCar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblCar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblCar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCar);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblCarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCarMouseClicked
        // TODO add your handling code here:
        int row = tblCar.getSelectedRow();
        String s1 = (String) tblCar.getValueAt(row, 7);
        lblTxe.setText((String) tblCar.getValueAt(row, 1));
        if (s1 == null) {
            NoData();
        } else {
            HaveData(row);
        }
    }//GEN-LAST:event_tblCarMouseClicked

    private void cbbHangsxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbHangsxActionPerformed
        // TODO add your handling code here:
        Brand hsx = (Brand) cbbHangsx.getSelectedItem();
        String sql = "SELECT * FROM Model WHERE IDBrand = " + hsx.Id;
        System.out.println(sql);
        DBInitData.initModel(cbbModel, sql);
    }//GEN-LAST:event_cbbHangsxActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        Search();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnManagerHangxeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManagerHangxeActionPerformed
        // TODO add your handling code here:
        new ManageBrand(null, true).setVisible(true);
    }//GEN-LAST:event_btnManagerHangxeActionPerformed

    private void btnManagerModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManagerModelActionPerformed
        // TODO add your handling code here:
        new ManageModel(null, true).setVisible(true);
    }//GEN-LAST:event_btnManagerModelActionPerformed

    private void cbbTrangthaixeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangthaixeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbTrangthaixeActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnManagerHangxe;
    private javax.swing.JButton btnManagerModel;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox cbbHangsx;
    private javax.swing.JComboBox cbbModel;
    private javax.swing.JComboBox cbbTrangthaixe;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnh1;
    private javax.swing.JLabel lblAnh2;
    private javax.swing.JLabel lblAnh3;
    private javax.swing.JLabel lblAnh4;
    private javax.swing.JLabel lblDen;
    private javax.swing.JLabel lblHangsx;
    private javax.swing.JLabel lblMaxe;
    private javax.swing.JLabel lblModel;
    private javax.swing.JLabel lblTenxe;
    private javax.swing.JLabel lblTrangthaixe;
    private javax.swing.JLabel lblTu;
    private javax.swing.JLabel lblTxe;
    private javax.swing.JTable tblCar;
    private javax.swing.JTextField txtGiaxe1;
    private javax.swing.JTextField txtGiaxe2;
    private javax.swing.JTextField txtMaxe;
    private javax.swing.JTextField txtTenxe;
    // End of variables declaration//GEN-END:variables
}
