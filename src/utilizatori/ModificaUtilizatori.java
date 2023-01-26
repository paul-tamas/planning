/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilizatori;


import dashboard.Dashboard;
import static database.DbConnection.cl;
import static database.DbConnection.prg;
import static database.DbConnection.usr;
import drepturi.Privilegii;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import login.LoginMode;
import static utilizatori.UtilizatoriDashboard.Dbuser;
import static utilizatori.UtilizatoriDashboard.password;
import static utilizatori.UtilizatoriDashboard.url;

/**
 *
 * @author Paul
 */
public class ModificaUtilizatori extends javax.swing.JFrame {

    /**
     * Creates new form adaugaClienti
     */
    public ModificaUtilizatori() {
        initComponents();
        this.setTitle("Adauga Clienti");

        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;
        this.setLocation(x, y);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addBtn.setBackground(Color.green);
        cancelBTN.setBackground(Color.red);
        String sessionUser;
        try {
            dbConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ModificaUtilizatori.class.getName()).log(Level.SEVERE, null, ex);
        }
        sessionUser = Dashboard.getConnectedUser();
        int selectedForModify;
        System.out.println("modificare");
        selectedForModify = UtilizatoriDashboard.getSelectedForModify();
        selectedForModi = selectedForModify;
        System.out.println(selectedForModify);
        utilizator.setText(users.get(selectedForModify).getUser());
        parola.setText(users.get(selectedForModify).getPwd());
        permissionChoicer.addItem(drept.get(users.get(selectedForModify).getPermission()).getPrivilegiu());
       
       
       
    }
    ArrayList<LoginMode> users = new ArrayList<LoginMode>();
    ArrayList<Privilegii> drept = new ArrayList<Privilegii>();
    int selectedForModi;

     public void dbConnection() throws SQLException {
usersRead();
dreptRead();
    }
    void usersRead(){
    try (Connection conn = DriverManager.getConnection(url, Dbuser, password)) {
            System.out.println("Database connected!");
            String message = "Database connected!";
// show a dialog message
           // JOptionPane.showMessageDialog(null, message);
        } catch (SQLException e) {
            // throw new IllegalStateException("Cannot connect the database!", e);
            String message = "Cannot connect the database!";

            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);

        }
        ResultSet ut = null;
    
        Statement stmt = null;
        System.out.println("Connecting database...");

        System.out.println("Loading driver...");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, Dbuser, password)) {
                System.out.println("Driver loaded!");
             
                stmt = conn.createStatement();
                ut = stmt.executeQuery("select * from users");
               
                while (ut.next()) 
                {
                    System.out.println("test "+ut.getInt("id") + " " + ut.getString("username1") + " " + ut.getString("password") + " " + ut.getInt("id_drept"));
                    users.add(new LoginMode(ut.getInt("id"), ut.getString("username1"), ut.getString("password"), ut.getInt("id_drept")));
//                   
                }
              
             
            }
        } catch (ClassNotFoundException e) {
            //throw new IllegalStateException("Cannot find the driver in the classpath!", e);
            System.out.println("Cannot find the driver in the classpath!" + e);
        } catch (SQLException e) {
//    throw new IllegalStateException("Cannot connect the database!", e);
            System.out.println("Cannot connect the database!" + " " + e);
            JOptionPane.showMessageDialog(null, "Cannot connect the database!" + " " + e);
        }
     
    
    
    }
    void dreptRead(){
        try (Connection conn = DriverManager.getConnection(url, Dbuser, password)) {
            System.out.println("Database connected!");
            String message = "Database connected!";
// show a dialog message
           // JOptionPane.showMessageDialog(null, message);
        } catch (SQLException e) {
            // throw new IllegalStateException("Cannot connect the database!", e);
            String message = "Cannot connect the database!";

            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);

        }
       
        ResultSet drpt = null;
        Statement stmt = null;
        System.out.println("Connecting database...");

        System.out.println("Loading driver...");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, Dbuser, password)) {
                System.out.println("Driver loaded!");
             
                stmt = conn.createStatement();
             
                drpt = stmt.executeQuery("select * from drepturi");
                
                while (drpt.next()){
                 drept.add(new Privilegii(drpt.getInt("id"),drpt.getString("drept")));
                }
             
            }
        } catch (ClassNotFoundException e) {
            //throw new IllegalStateException("Cannot find the driver in the classpath!", e);
            System.out.println("Cannot find the driver in the classpath!" + e);
        } catch (SQLException e) {
//    throw new IllegalStateException("Cannot connect the database!", e);
            System.out.println("Cannot connect the database!" + " " + e);
            JOptionPane.showMessageDialog(null, "Cannot connect the database!" + " " + e);
        }
     
    }
    private void charCounter() {
    
        }
//  txtArea=(String) charCount;
        
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        utilizator = new javax.swing.JTextField();
        parola = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        cancelBTN = new javax.swing.JButton();
        permissionChoicer = new java.awt.Choice();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Utilizator");

        jLabel2.setText("Parola");

        jLabel6.setText("Modifica permisiune in aplicatie");

        addBtn.setText("Modifica");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        cancelBTN.setText("Renunta");
        cancelBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBTNActionPerformed(evt);
            }
        });

        permissionChoicer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                permissionChoicerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(addBtn))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cancelBTN, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(parola, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                    .addComponent(utilizator)))
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(permissionChoicer, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(utilizator)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(parola)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(permissionChoicer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn)
                    .addComponent(cancelBTN))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBTNActionPerformed
        this.dispose();     // TODO add your handling code here:
    }//GEN-LAST:event_cancelBTNActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
        try (Connection connection = DriverManager.getConnection(url, Dbuser, password)) {

        } catch (SQLException e) {
            // throw new IllegalStateException("Cannot connect the database!", e);
            String message = "Cannot connect the database!";

            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);

        }
        // ResultSet rs = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, Dbuser, password)) {

                stmt = connection.createStatement();
                //           sql = "insert into users ('u_fname', 'u_lname', 'u_uname', 'u_pass', 'u_age', 'u_adderess')"
//                + "values('" + s.getFirstname() + "','" + s.getLastname()
//                + "','" + s.getUsername() + "','" + s.getPassword() + "','" + s.getAge() + "','" + s.getAdderss() + "')";
//        
                 stmt.executeUpdate("update users set username1 = " + "'" + utilizator.getText() + "',password= '"
                        + parola.getText() + "',id_drept = '" +
                         drept.get(permissionChoicer.getSelectedIndex()).getID() + "' where id = '" + users.get(selectedForModi).getID() + "'");
                this.dispose();
                //  System.out.println("am adaugat programare pentru:);
            }
        } catch (ClassNotFoundException e) {
            //throw new IllegalStateException("Cannot find the driver in the classpath!", e);
            System.out.println("Cannot find the driver in the classpath!" + e);
            String message = "Cannot find the driver in the classpath!" + e;

            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
//    throw new IllegalStateException("Cannot connect the database!", e);
            System.out.println("Cannot connect the database!" + " " + e);
            String message = "Cannot connect the database!" + " " + e;

            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);
//JOptionPane.showMessageDialog(null, "Cannot connect the database!" + " " + e);
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void permissionChoicerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_permissionChoicerMouseClicked
        // TODO add your handling code here:
         for(int i=0;i<drept.size();i++)
       permissionChoicer.add(drept.get(i).getPrivilegiu());
    }//GEN-LAST:event_permissionChoicerMouseClicked

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
            java.util.logging.Logger.getLogger(ModificaUtilizatori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificaUtilizatori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificaUtilizatori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificaUtilizatori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModificaUtilizatori().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton cancelBTN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField parola;
    private java.awt.Choice permissionChoicer;
    private javax.swing.JTextField utilizator;
    // End of variables declaration//GEN-END:variables
}
