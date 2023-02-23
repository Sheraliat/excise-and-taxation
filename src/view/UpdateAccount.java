/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data.Phases;
import data.Role;
import data.Tools;
import data.UserSystem;
import db.DBHandleData;
import db.DBInitData;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author tanzil
 */
public class UpdateAccount extends javax.swing.JDialog {

    /**
     * Creates new form UpdateAccount
     */
    UserSystem user;

    public UpdateAccount(java.awt.Frame parent, boolean modal, UserSystem user) {
        super(parent, modal);
        initComponents();
        Tools.LookAndFeel();
        this.setLocationRelativeTo(null);
        DBInitData.initRole(cbbRole);
        if (user.getMaqt()==2) {
            btnDelete.setEnabled(false);
        }
    }
    
    public void Reset() {
        txtEmail.setText("");
        txtFullname.setText("");
        txtID.setText("");
    }
    String s;
    
    public void Search() {
        Pattern p = Pattern.compile("\\d+");
        Matcher matcher = p.matcher(txtID.getText());
        Role r = (Role) cbbRole.getSelectedItem();
        String sql = "SELECT UserSystem.IDUser, Role.RoleName, UserSystem.Username, UserSystem.Password, UserSystem.Fullname, "
                + "CASE UserSystem.Gender WHEN 0 THEN 'Female' ELSE 'Male' END  AS Gender, UserSystem.Address, "
                + "UserSystem.Phone, UserSystem.Email, UserSystem.IDRole "
                + "FROM  UserSystem INNER JOIN Role ON UserSystem.IDRole = Role.IDRole "
                + "WHERE 1 = 1";
        s = sql;
        if (!txtID.getText().equals("")) {
            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(this, Phases.INVALID_NUMBER_FORMAT, Phases.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                sql += " and UserSystem.IDUser = " + txtID.getText().trim();
            }
        }
        if (cbbRole.getSelectedIndex() != 0) {
            sql += " and UserSystem.IDRole = " + r.getId_vai_tro();
        }
        System.out.println(r.getId_vai_tro());
        if (!txtFullname.getText().equals("")) {
            sql += " and UserSystem.Fullname like '%" + txtFullname.getText().trim() + "%'";
        }
        if (!txtEmail.getText().equals("")) {
            sql += " and UserSystem.Email like '%" + txtEmail.getText().trim() + "%'";
        }
        DBInitData.initCustomer(tblAccount, sql, "SELECT * FROM UserSystem ");
        Tools.hideColumnFrom(tblAccount, 9);
        int row = tblAccount.getRowCount();
        if (row == 0) {
            JOptionPane.showMessageDialog(this, Phases.SEARCH_NOT_FOUND, Phases.INFORMATION_MESSAGE, JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    public void Edit() {
        int row = tblAccount.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, Phases.ERROR_SELECT_TABLE, Phases.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE);
            return;
        }
        int i = 0;
        if (((String) tblAccount.getValueAt(row, 5)).equals("Female")) {
            i = 0;
        } else {
            i = 1;
        }
        UserSystem user = new UserSystem(Integer.parseInt((String) tblAccount.getValueAt(row, 0)),
                Integer.parseInt((String) tblAccount.getValueAt(row, 9)),
                (String) tblAccount.getValueAt(row, 2), (String) tblAccount.getValueAt(row, 3), (String) tblAccount.getValueAt(row, 4),
                i, (String) tblAccount.getValueAt(row, 6),
                (String) tblAccount.getValueAt(row, 7), (String) tblAccount.getValueAt(row, 8));
        new EditAccount(this, true, user).setVisible(true);
    }
    
    public void Delete() {
        int row = tblAccount.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, Phases.ERROR_SELECT_TABLE, Phases.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE);
            return;
        }
        String sql = "DELETE FROM UserSystem WHERE IDUser = " + Integer.parseInt((String) tblAccount.getValueAt(row, 0));
        String[] data = {};
        String s1 = "SELECT UserSystem.IDUser "
                + "FROM UserSystem INNER JOIN SaleInvoice ON UserSystem.IDUser = SaleInvoice.IDUser "
                + "WHERE SaleInvoice.Status = 0";
        if (DBHandleData.CheckForDelete(s1, Integer.parseInt((String) tblAccount.getValueAt(row, 0)))) {
            JOptionPane.showMessageDialog(this, Phases.DELETE_ACCOUNT_ERROR_EXIST, Phases.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
        } else {
            int c = JOptionPane.showConfirmDialog(this, Phases.QUESTION_DELETE, Phases.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
            if (c == JOptionPane.OK_OPTION) {
                DBHandleData.UpdateData(sql, data);
                JOptionPane.showMessageDialog(this, Phases.DELETE_SUCCESSFULL, Phases.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
                DBInitData.initCustomer(tblAccount, s, "SELECT * FROM UserSystem ");
                Tools.hideColumnFrom(tblAccount, 9);
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAccount = new javax.swing.JTable();
        lblID = new javax.swing.JLabel();
        lblFullName = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtFullname = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        cbbRole = new javax.swing.JComboBox();
        btnReset = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update Account");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Update Account", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(51, 102, 255))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Account", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        tblAccount.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblAccount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblAccount.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tblAccount.setDropMode(javax.swing.DropMode.ON);
        jScrollPane1.setViewportView(tblAccount);

        lblID.setText("ID:");

        lblFullName.setText("Full Name:");

        lblEmail.setText("Email:");

        lblRole.setText("Role:");

        cbbRole.setEditable(true);

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

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Spotlight Blue Button.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Write Document.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Trash Empty.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                            .addComponent(txtFullname))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancel, btnDelete, btnEdit, btnReset});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtID)
                        .addComponent(lblRole, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSearch)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancel, btnDelete, btnEdit, btnReset, btnSearch});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btnResetActionPerformed
    
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed
    
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        Search();
    }//GEN-LAST:event_btnSearchActionPerformed
    
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        Edit();
    }//GEN-LAST:event_btnEditActionPerformed
    
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        Delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox cbbRole;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFullName;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblRole;
    private javax.swing.JTable tblAccount;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFullname;
    private javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables
}
