/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data.Brand;
import data.Phases;
import data.Tools;
import db.DBHandleData;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author tanzil
 */
public class UpdateBrand extends javax.swing.JDialog {

    /**
     * Creates new form UpdateBrand
     */
    Brand h;
    ManageBrand parent;

    public UpdateBrand(java.awt.Dialog parent, boolean modal, Brand h) {
        super(parent, modal);
        initComponents();
        this.h = h;
        this.parent = (ManageBrand) parent;
        Tools.LookAndFeel();
        this.setLocationRelativeTo(null);
        if (h.getId() != 0) {
            setData();
        }
        System.out.println(h.getId());
    }

    public void setData() {
        txtID.setText(Integer.toString(h.getId()));
        txtEmail.setText(h.getEmail());
        txtAddress.setText(h.getPhone());
        txtName.setText(h.getName());
        txtPhone.setText(h.getAddress());
    }

    public void Reset() {
        txtID.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        txtName.setText("");
        txtPhone.setText("");
    }

    public void Add() {
        Pattern p = Pattern.compile("0[0-9]{9,10}");
        Matcher matcher = p.matcher(txtPhone.getText());
        Pattern pp = Pattern.compile("[a-zA-Z_]+[0-9]*@[a-zA-Z]{3,15}.[a-zA-Z]{3,15}[.[a-zA-Z]{3,15}]*");
        Matcher m = pp.matcher(txtEmail.getText());
        if (txtName.getText().equals("") || txtAddress.getText().equals("") || txtPhone.getText().equals("") || txtEmail.getText().equals("")) {
            JOptionPane.showMessageDialog(this, Phases.ERROR_EMPTY_FIELD, Phases.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(this, Phases.INVALID_NUMBER_FORMAT, Phases.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!m.matches()) {
            JOptionPane.showMessageDialog(this, Phases.INVALID_EMAIL_FORMAT, Phases.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        String sql = "INSERT Brand VALUES (?,?,?,?)";
        String[] data = {txtName.getText(), txtAddress.getText(), txtPhone.getText(), txtEmail.getText()};
        if (DBHandleData.CheckForInsert("SELECT BrandName FROM Brand", txtName.getText())) {
            JOptionPane.showMessageDialog(this, Phases.ADD_BRAND_EXIST, Phases.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
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
        Pattern p = Pattern.compile("0[0-9]{9,10}");
        Matcher matcher = p.matcher(txtPhone.getText());
        Pattern pp = Pattern.compile("[a-zA-Z_]+[0-9]*@[a-zA-Z]{3,15}.[a-zA-Z]{3,15}[.[a-zA-Z]{3,15}]*");
        Matcher m = pp.matcher(txtEmail.getText());
        if (txtName.getText().equals("") || txtAddress.getText().equals("") || txtPhone.getText().equals("") || txtEmail.getText().equals("")) {
            JOptionPane.showMessageDialog(this, Phases.ERROR_EMPTY_FIELD, Phases.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(this, Phases.INVALID_NUMBER_FORMAT, Phases.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!m.matches()) {
            JOptionPane.showMessageDialog(this, Phases.INVALID_EMAIL_FORMAT, Phases.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        String sql = "UPDATE Brand SET BrandName= ?, Address= ?, Phone= ?, Email=? WHERE IDBrand= " + Integer.parseInt(txtID.getText());
        String[] data = {txtName.getText(), txtAddress.getText(), txtPhone.getText(), txtEmail.getText()};

        int rows = db.DBHandleData.UpdateData(sql, data);
        if (rows > 0) {
            JOptionPane.showMessageDialog(this, Phases.UPDATE_SUCCESSFULL);
        } else {
            JOptionPane.showMessageDialog(this, Phases.UPDATE_FAILURE);
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
        lblPhone = new javax.swing.JLabel();
        lblEmailB = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        btnSubmit = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update Brand");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Update Brand", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        lblID.setText("ID:");

        lblNameB.setText("Name:");

        txtID.setEditable(false);

        lblPhone.setText("Phone:");

        lblEmailB.setText("Address:");

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

        lblEmail.setText("Email:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblEmailB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                            .addComponent(lblPhone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNameB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtName)
                            .addComponent(txtPhone)
                            .addComponent(txtAddress)
                            .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                            .addComponent(txtEmail)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnSubmit)
                        .addGap(18, 18, 18)
                        .addComponent(btnReset)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNameB, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmailB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit)
                    .addComponent(btnReset)
                    .addComponent(btnCancel))
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
        parent.Search();
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        if (h.getId() != 0) {
            Edit();
        } else {
            Add();
        }
    }//GEN-LAST:event_btnSubmitActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmailB;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblNameB;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
