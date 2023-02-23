/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data.Brand;
import data.Model;
import data.Phases;
import data.Tools;
import db.DBHandleData;
import db.DBInitData;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author tanzil
 */
public class UpdateModel extends javax.swing.JDialog {

    /**
     * Creates new form UpdateBrand
     */
    Model m;
    ManageModel parent;

    public UpdateModel(java.awt.Dialog parent, boolean modal, Model m) {
        super(parent, modal);
        initComponents();
        this.m = m;
        this.parent = (ManageModel) parent;
        Tools.LookAndFeel();
        this.setLocationRelativeTo(null);
        DBInitData.initHangxe(cbbBrand);
        if (m.getIdBrand() != 0) {
            setData();
        }

    }

    public void setData() {
        txtID.setText(Integer.toString(m.getIdBrand()));
        txtName.setText(m.getModelName());
        cbbBrand.setSelectedIndex(m.getId());
    }

    public void Reset() {
        txtID.setText("");
        cbbBrand.setSelectedIndex(0);
        txtName.setText("");
    }

    public void Add() {
        Brand h = (Brand) cbbBrand.getSelectedItem();
        if (txtName.getText().equals("") || cbbBrand.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, Phases.ERROR_EMPTY_FIELD, Phases.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        String sql = "INSERT Model VALUES (?,?)";
        String[] data = {Integer.toString(h.getId()), txtName.getText()};
        if (DBHandleData.CheckForInsert("SELECT ModelName FROM Model", txtName.getText())) {
            JOptionPane.showMessageDialog(this, Phases.ADD_MODEL_EXIST, Phases.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
        } else {
            int rows = db.DBHandleData.UpdateData(sql, data);
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, Phases.UPDATE_SUCCESSFULL);
            } else {
                JOptionPane.showMessageDialog(this, Phases.UPDATE_FAILURE);
            }
        }
    }

    public void Edit() {
        Brand h = (Brand) cbbBrand.getSelectedItem();
        if (txtName.getText().equals("") || cbbBrand.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, Phases.ERROR_EMPTY_FIELD, Phases.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        String sql = "UPDATE Model SET IDBrand= ?, ModelName= ? WHERE IDModel= " + Integer.parseInt(txtID.getText());
        String[] data = {Integer.toString(h.getId()), txtName.getText()};
        if (DBHandleData.CheckForInsert("SELECT ModelName FROM Model", txtName.getText())) {
            JOptionPane.showMessageDialog(this, Phases.ADD_MODEL_EXIST, Phases.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
        } else {
            int rows = db.DBHandleData.UpdateData(sql, data);
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, Phases.UPDATE_SUCCESSFULL);
            } else {
                JOptionPane.showMessageDialog(this, Phases.UPDATE_FAILURE);
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

        jPanel1 = new javax.swing.JPanel();
        lblID = new javax.swing.JLabel();
        lblNameB = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        btnSubmit = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblBrand = new javax.swing.JLabel();
        cbbBrand = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update Brand");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Update Model", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        lblID.setText("ID:");

        lblNameB.setText("Name:");

        txtID.setEditable(false);

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

        lblBrand.setText("Brand:");

        cbbBrand.setEditable(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblBrand, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                            .addComponent(lblID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNameB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbBrand, 0, 283, Short.MAX_VALUE)
                            .addComponent(txtName)
                            .addComponent(txtID)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSubmit)
                        .addGap(39, 39, 39)
                        .addComponent(btnReset)
                        .addGap(33, 33, 33)
                        .addComponent(btnCancel)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNameB, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSubmit)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCancel)
                        .addComponent(btnReset)))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        parent.SearchModel();
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        if (m.getIdBrand() == 0) {
            Add();
        } else {
            Edit();
        }
    }//GEN-LAST:event_btnSubmitActionPerformed
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox cbbBrand;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBrand;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblNameB;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}