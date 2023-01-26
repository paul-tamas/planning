/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import dashboard.Dashboard;
import database.DbConnection;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static database.DbConnection.usr;
import javax.swing.JLabel;
/**
 *
 * @author Paul
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
       
    
                this.setTitle("Login");
        userField.setText("");
        passField.setText("");
        //statusLabel.setText("ssdas");

        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        // Move the window
        this.setLocation(x, y);

    }
  public  ArrayList<LoginMode> log = new ArrayList<LoginMode>();

    private void dbConnection(String field) throws SQLException {

        log = usr(field);
    }

    @SuppressWarnings("unchecked")
    public void setStatus(String status) {
        statusLabel.setText(status);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        loginBTN = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        statusLabel = new javax.swing.JLabel();
        passField = new javax.swing.JPasswordField();
        poza1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        userField.setText("jTextField1");

        jLabel1.setText("Username");

        jLabel2.setText("Password");

        loginBTN.setText("Login");
        loginBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBTNActionPerformed(evt);
            }
        });
        loginBTN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                loginBTNKeyPressed(evt);
            }
        });

        passField.setText("jPasswordField1");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/4_60x49.png"))); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(49, 49));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/4_49x49.png"))); // NOI18N
        jLabel8.setText("jLabel2");
        jLabel8.setPreferredSize(new java.awt.Dimension(49, 49));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3_49x49.png"))); // NOI18N
        jLabel9.setText("jLabel2");
        jLabel9.setPreferredSize(new java.awt.Dimension(49, 49));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/2_49x49.png"))); // NOI18N
        jLabel10.setText("jLabel2");
        jLabel10.setPreferredSize(new java.awt.Dimension(49, 49));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1_49x49.png"))); // NOI18N
        jLabel11.setText("jLabel2");
        jLabel11.setPreferredSize(new java.awt.Dimension(49, 49));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inteprindere_de_insertie_49x49.jpg"))); // NOI18N
        jLabel12.setText("jLabel2");
        jLabel12.setPreferredSize(new java.awt.Dimension(49, 49));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(poza1)
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loginBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(userField))))
                        .addGap(36, 36, 36))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusLabel)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(poza1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginBTN)
                .addGap(23, 23, 23)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(statusLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//public boolean validateSession(boolean val){
//if(val == true)
//    return true;
//else return false;
//}
    private void loginBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBTNActionPerformed
        // TODO add your handling code here:

        try {

            dbConnection(userField.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Cannot connect the database!");
        }
        if (log.size() != 0) {
            LoginMode l = log.get(0);
            if (l.getUser().equals(userField.getText())) {
                if (l.getPwd().equals(passField.getText())) {
                    Dashboard ds = null;
                    try {
                        ds = new Dashboard();
                    } catch (SQLException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    ds.setSession(l.getUser(), true);
                    ds.setVisible(true);
                    this.dispose();

                    statusLabel.setText("Welcome, " + userField.getText());
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Access denied!", "Dialog",
                            JOptionPane.ERROR_MESSAGE);

                    statusLabel.setText("Acces denied");
                    System.out.println(l.getPwd());
                    System.out.println(passField.getText());
                }
            } else {
                statusLabel.setText("Acces denied");
//                System.out.println(l.password);
//                System.out.println(passField.getText());
//                System.out.println("-------------------------");
//                System.out.println(l.username);
//                System.out.println(userField.getText());
                JOptionPane.showMessageDialog(new JFrame(), "Access denied!", "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else {
            statusLabel.setText("Acces denied");
            JOptionPane.showMessageDialog(new JFrame(), "Access denied!", "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        }

        //  validateSession(false);
    }//GEN-LAST:event_loginBTNActionPerformed

    private void loginBTNKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginBTNKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
             try {

            dbConnection(userField.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Cannot connect the database!");
        }
        if (log.size() != 0) {
            LoginMode l = log.get(0);
            if (l.getUser().equals(userField.getText())) {
                if (l.getPwd().equals(passField.getText())) {
                    Dashboard ds = null;
                    try {
                        ds = new Dashboard();
                    } catch (SQLException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ds.setSession(l.getUser(), true);
                    ds.setVisible(true);
                    this.dispose();

                    statusLabel.setText("Welcome, " + userField.getText());
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Access denied!", "Dialog",
                            JOptionPane.ERROR_MESSAGE);

                    statusLabel.setText("Acces denied");
//                    System.out.println(l.password);
//                    System.out.println(passField.getText());
                }
            } else {
                statusLabel.setText("Acces denied");
//                System.out.println(l.password);
//                System.out.println(passField.getText());
//                System.out.println("-------------------------");
//                System.out.println(l.username);
//                System.out.println(userField.getText());
                JOptionPane.showMessageDialog(new JFrame(), "Access denied!", "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else {
            statusLabel.setText("Acces denied");
            JOptionPane.showMessageDialog(new JFrame(), "Access denied!", "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        }
        }
    }//GEN-LAST:event_loginBTNKeyPressed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton loginBTN;
    private javax.swing.JPasswordField passField;
    private javax.swing.JLabel poza1;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTextField userField;
    // End of variables declaration//GEN-END:variables

    public boolean validateSession() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
