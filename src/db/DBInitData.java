/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import data.Brand;
import data.Model;
import data.Payment;
import data.Role;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tanzeel
 */
public class DBInitData {

    public static void initHangxe(JComboBox cbbHangsx) {
        ResultSet rs = null;
        try {
            rs = DBHandleData.SelectData("SELECT * FROM Brand");
            cbbHangsx.addItem(new Brand(-1, "SELECT BRAND", "", "", ""));
            while (rs.next()) {
                Brand h = new Brand(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                cbbHangsx.addItem(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBInitData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConn(null, null, rs);
        }
    }

    public static void initModel(JComboBox cbbModel, String sql) {
        ResultSet rs = null;
        try {
            rs = DBHandleData.SelectData(sql);
            cbbModel.removeAllItems();
            cbbModel.addItem(new Model(-1, -1, "SELECT MODEL"));
            while (rs.next()) {
                Model c = new Model(Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(2)), rs.getString(3));
                cbbModel.addItem(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBInitData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConn(null, null, rs);
        }
    }

    public static void initRole(JComboBox cbbModel) {
        ResultSet rs = null;
        try {
            rs = DBHandleData.SelectData("SELECT * FROM Role");
            cbbModel.removeAllItems();
            cbbModel.addItem(new Role(-1, "SELECT ROLE"));
            while (rs.next()) {
                Role r = new Role(Integer.parseInt(rs.getString(1)), rs.getString(2));
                cbbModel.addItem(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBInitData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConn(null, null, rs);
        }
    }

    public static void initPay(JComboBox cbbModel) {
        ResultSet rs = null;
        try {
            rs = DBHandleData.SelectData("SELECT * FROM PayType");
            cbbModel.removeAllItems();
            cbbModel.addItem(new Payment(-1, "SELECT PAY TYPE"));
            while (rs.next()) {
                Payment p = new Payment(Integer.parseInt(rs.getString(1)), rs.getString(2));
                cbbModel.addItem(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBInitData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConn(null, null, rs);
        }
    }
    static Vector vData;
    static Vector vData1;
    static Vector vColumn;

    public static void initVehicle(JTable tblCar, String sql, String s1) {

        ResultSet rs = null;

        if (sql == null) {
            sql = s1;
        }
        rs = DBHandleData.SelectData(sql);
        vData = new Vector();
        vColumn = new Vector();
        try {
            ResultSetMetaData rsmt;
            rsmt = rs.getMetaData();
            for (int i = 1; i <= rsmt.getColumnCount(); i++) {
                vColumn.add(rsmt.getColumnName(i));
            }
            while (rs.next()) {
                Vector vRow = new Vector();
                for (int i = 1; i <= rsmt.getColumnCount(); i++) {
                    vRow.add(rs.getString(i));
                }
                vData.add(vRow);
            }
            tblCar.setModel(new DefaultTableModel(vData, vColumn));
            tblCar.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblCar.getColumnModel().getColumn(1).setPreferredWidth(235);
            tblCar.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblCar.getColumnModel().getColumn(3).setPreferredWidth(110);
            tblCar.getColumnModel().getColumn(4).setPreferredWidth(60);
            tblCar.getColumnModel().getColumn(5).setPreferredWidth(100);
            tblCar.getColumnModel().getColumn(6).setPreferredWidth(120);
            tblCar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        } catch (SQLException ex) {
            Logger.getLogger(DBInitData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConn(null, null, rs);
        }
        
    }

    public static Vector getRow(int r) {
        return (Vector) vData.get(r);
    }

    public static void initCustomer(JTable tblCustomer, String sql, String s1) {
        ResultSet rs = null;
        vColumn = new Vector();
        vData = new Vector();
        if (sql == null) {
            sql = s1;
        }
        rs = DBHandleData.SelectData(sql);
        try {
            ResultSetMetaData rsmt;
            rsmt = rs.getMetaData();
            for (int i = 1; i <= rsmt.getColumnCount(); i++) {
                vColumn.add(rsmt.getColumnName(i));
            }
            while (rs.next()) {
                Vector vRow = new Vector();
                for (int i = 1; i <= rsmt.getColumnCount(); i++) {
                    vRow.add(rs.getString(i));
                }
                vData.add(vRow);
            }
            tblCustomer.setModel(new DefaultTableModel(vData, vColumn));
            tblCustomer.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblCustomer.getColumnModel().getColumn(1).setPreferredWidth(180);
            tblCustomer.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblCustomer.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblCustomer.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblCustomer.getColumnModel().getColumn(5).setPreferredWidth(120);
            tblCustomer.getColumnModel().getColumn(6).setPreferredWidth(120);
            tblCustomer.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } catch (SQLException ex) {
            Logger.getLogger(DBInitData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConn(null, null, rs);
        }
    }

    public static void initBrandModel(JTable tblCustomer, String sql, String s1) {
        ResultSet rs = null;
        vColumn = new Vector();
        vData = new Vector();
        if (sql == null) {
            sql = s1;
        }
        rs = DBHandleData.SelectData(sql);
        try {
            ResultSetMetaData rsmt;
            rsmt = rs.getMetaData();
            for (int i = 1; i <= rsmt.getColumnCount(); i++) {
                vColumn.add(rsmt.getColumnName(i));
            }
            while (rs.next()) {
                Vector vRow = new Vector();
                for (int i = 1; i <= rsmt.getColumnCount(); i++) {
                    vRow.add(rs.getString(i));
                }
                vData.add(vRow);
            }
            tblCustomer.setModel(new DefaultTableModel(vData, vColumn));
            tblCustomer.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblCustomer.getColumnModel().getColumn(1).setPreferredWidth(250);
            tblCustomer.getColumnModel().getColumn(2).setPreferredWidth(200);
            tblCustomer.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } catch (SQLException ex) {
            Logger.getLogger(DBInitData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConn(null, null, rs);
        }
    }
}
