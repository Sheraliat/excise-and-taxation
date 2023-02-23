/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tanzeel
 */
public class DBHandleData {

    public static ResultSet SelectData(String sql) {
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cn = DBConnection.getConn();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBHandleData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return rs;

        }
    }

    public static int UpdateData(String sql, String[] data) {
        int rows = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBConnection.getConn();
            pst = cn.prepareStatement(sql);
            for (int i = 0; i < data.length; i++) {
                pst.setString(i + 1, data[i]);
            }
            rows = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandleData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return rows;

        }
    }

    public static ResultSet Connect(String sql) {
        Connection con;
        PreparedStatement stmt;
        ResultSet rs;
        rs = null;
        try {
            con = DBConnection.getConn();
            stmt = con.prepareStatement(sql);
//            for (int i = 0; i < data.length; i++) {
//                pst.setString(i + 1, data[i]);
//            }
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(sql);
            Logger.getLogger(DBHandleData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return rs;
        }
    }

    public static Boolean CheckForInsert(String sql, String Tenxe) {
        Boolean b = false;
        ResultSet rs = null;
        rs = SelectData(sql);
        try {
            while (rs.next()) {
                if (rs.getString(1).equals(Tenxe)) {
                    b = true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBHandleData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return b;
        }
    }

    public static Boolean CheckForDelete(String sql, int maxe) {
        Boolean b = false;
        ResultSet rs = null;
        rs = SelectData(sql);
        try {
            while (rs.next()) {
                if (rs.getInt(1) == maxe) {
                    b = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHandleData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return b;
        }
    }
}
