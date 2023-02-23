/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import com.toedter.calendar.JDateChooser;
import db.DBConnection;
import db.DBHandleData;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import view.EditSale;
import view.Login;

/**
 *
 * @author tanzil
 */
public class Tools {

    public static void fitImageToLabel(JLabel lblImages, String imgPath) {
        try {
            File f = new File(imgPath);
            System.out.println(imgPath);
            if (!f.exists()) {
            } else {
                BufferedImage bimg = ImageIO.read(f);
                Image image = bimg.getScaledInstance(lblImages.getWidth(), lblImages.getHeight(), Image.SCALE_AREA_AVERAGING);
                ImageIcon imgIcon = new ImageIcon(image);
                lblImages.setIcon(imgIcon);
            }
        } catch (IOException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void hideColumnFrom(JTable table, int fromIndex) {
        for (int i = fromIndex; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setMaxWidth(0);
            table.getColumnModel().getColumn(i).setMinWidth(0);
            table.getColumnModel().getColumn(i).setPreferredWidth(0);
        }
    }

    public static String Img(String s, int i) {
        String name = s.substring(s.lastIndexOf("\\") + 1, s.lastIndexOf("."));
        String ext = s.substring(s.lastIndexOf("."), s.length());
        name = name + i + ext;
        String dir = s.substring(0, s.lastIndexOf("\\") + 1);
        String path = dir + name;
        return path;
    }

    
    /* 
    * Copy file Function
    * Check Folder if exist
    *               else create folder
    * 
    */
    public static Boolean CopyFileTo(String source, String destination) {
        System.out.println(source);
        File fsource = new File(source);
        if (!fsource.exists()) {
            return false;
        }
        File fdestination = new File(destination);
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            fos = new FileOutputStream(destination + "/" + fsource.getName());
            fis = new FileInputStream(fsource);
            //
            System.out.println(fos);
            
            byte[] b = new byte[4096];
            int n = 0;
            while (fis.available() > 0) {
                n = fis.read(b);
                fos.write(b, 0, n);
            }
            fos.flush();
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;

    }

    public static void check() {
        String regexForDate = "[0-9]{2}/[0-9]{2}/[0-9]{4}";
        Pattern pDate = Pattern.compile(regexForDate);

        String[] date = {"15/12/2010", "25/12/2012", "2012/05/25"};
        for (String d : date) {
            Matcher m = pDate.matcher(d);
            if (m.matches()) {
                System.out.println(d);
            }
        }

        String regexForName = "[a-zA-Z ]{3,30}";
        String regexForPhone = "[0-9]{10,11}";
        String regexForEmail = "[a-zA-Z_]+[0-9]*@[a-zA-Z]{3,15}.[a-zA-Z]{3,15}[.[a-zA-Z]{3,15}]*";
    }

    public static void sleep(int timeOut) {
        try {
            Thread.sleep(timeOut * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void LookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void initlist(String sql, List list) {
        ResultSet rs = null;
        try {
            rs = DBHandleData.SelectData(sql);
            while (rs.next()) {
                list.add(rs.getString(1));
                System.out.println(list);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConn(null, null, rs);
        }
        Collections.sort(list);
    }

    public static void autoComplete(final JTextField text, final List list) {
        text.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent arg0) {
            }

            @Override
            public void insertUpdate(DocumentEvent ev) {
                String completion;
                int pos = ev.getOffset();
                String content = null;
                try {
                    content = text.getText(0, pos + 1);
                } catch (BadLocationException ex) {
                    System.out.println("Error" + ex.getMessage());
                }

                int w;
                for (w = pos; w >= 0; w--) {
                }
                if (pos - w < 2) {
                    return;
                }

                String prefix = content.substring(w + 1);
                int n = Collections.binarySearch(list, prefix);
                if (n < 0 && -n <= list.size()) {
                    String match = (String) list.get(-n - 1);
                    if (match.startsWith(prefix)) {
                        completion = match.substring(pos - w);
                        SwingUtilities.invokeLater(new Tools.CompletionTask(
                                completion, pos + 1, text));
                    }
                }

            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
            }
        });

    }


    public static class CompletionTask implements Runnable {

        String completion;
        int position;
        JTextField txt;

        CompletionTask(String completion, int position, JTextField txt) {
            this.completion = completion;
            this.position = position;
            this.txt = txt;
        }

        @Override
        public void run() {
            txt.setText(txt.getText() + completion);
            txt.setCaretPosition(position + completion.length());
            txt.moveCaretPosition(position);
        }
    }

    public static void checkOrder(Vector v, int i, JTextField txt, JDateChooser dc) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String first = (String) v.get(i);
        if (first != null) {
            String date = (String) v.get(i + 1);
            try {
                Date d = sdf.parse(date);
                txt.setText(first);
                dc.setDate(d);
                txt.setEditable(false);
                dc.setEnabled(false);
            } catch (ParseException ex) {
                Logger.getLogger(EditSale.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void checkFormatNumber(JDialog d, JTextField txt) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(txt.getText());
        if (!m.matches()) {
            JOptionPane.showMessageDialog(d, "Invalid Format !!!", "Error", JOptionPane.ERROR_MESSAGE);
            txt.setText("");
            txt.requestFocus();
        }

    }


}
