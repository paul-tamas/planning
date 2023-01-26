/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilizatori;

import Programari.ProgramariTableModel;
import static database.DbConnection.cl;
import static database.DbConnection.prg;
import drepturi.Privilegii;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import login.DatabaseConnection;
import login.LoginMode;
import static utilizatori.AdaugaUtilizatori.url;

/**
 *
 * @author Paul
 */
public class UtilizatoriDashboard extends javax.swing.JFrame {

    /**
     * Creates new form ClientDashboard
     */
    public UtilizatoriDashboard() {
        initComponents();
           this.setTitle("Tablou de bord - modul utilizatori");
        // Get the size of the screen
        closeBTN.setBackground(Color.red);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;
        this.setLocation(x, y);
        checkURL();
        try {
            dbConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UtilizatoriDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        System.out.println("prg");
        UtilizatorTableModel clMod = new UtilizatorTableModel(users,drept);
        clientTable.setModel(clMod);
         this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

       
    }
     //public ArrayList<Clienti> client = new ArrayList<Clienti>();
    ArrayList<LoginMode> users = new ArrayList<LoginMode>();

       ArrayList<Privilegii> drept = new ArrayList<Privilegii>();
 static String DBserver = null;
    static String database = null;
    static String Dbuser = null;
    static String password = null;
    static String url = "";
    static String certificates =  "?verifyServerCertificate=false"
                    + "&useSSL=true"
                    + "&requireSSL=false";
  static  File configFile;
    static void checkURL(){
    if(url.isEmpty())
          configFile = new File("config.ini");
      Scanner myReader = null;
        try {
            myReader = new Scanner(configFile);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            new DatabaseConnection().setVisible(true);
        }
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
         String parts[] = data.split("=");
         
          System.out.println(parts[0] +"|||||| " +parts[1]);
      
        System.out.println(data);
        if(parts[0].equals("STRING") || parts[0].equals("string")){
          String[] sp = data.split(";");
          url = parts[1]+"=false";
          Dbuser=sp[1];
          password=sp[2];
                  
            System.out.println("url+"+url);
        }
        if(parts[0].equals("BAZADEDATE")){
            database = parts[1];
            System.out.println("database"+database);
        }
      }
      myReader.close();
     // url+=certificates;
        System.out.println(" url:"+ url);
        System.out.println(" user:"+ Dbuser);
        System.out.println(" parola:"+ password);
   //  url = url+database;
    }
    
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
               int i = 0;
                while (ut.next()) 
                {
                    System.out.println("test "+ut.getInt("id") + " " + ut.getString("username1") + " " + ut.getString("password") + " " + ut.getInt("id_drept"));
                    users.add(new LoginMode(ut.getInt("id"), ut.getString("username1"), ut.getString("password"), ut.getInt("id_drept")));
                 //   System.out.println(users.get(i).getUser());
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
static int rowSelectedForModify =0;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        clientTable = new javax.swing.JTable();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jScrollPane2 = new javax.swing.JScrollPane();
        clientTXT = new javax.swing.JEditorPane();
        dateBTN = new javax.swing.JButton();
        addClientBTN = new javax.swing.JButton();
        closeBTN = new javax.swing.JButton();
        refreshBTN = new javax.swing.JButton();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1000, 700));

        clientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        clientTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(clientTable);

        jScrollPane2.setViewportView(clientTXT);

        dateBTN.setText("Modifica date utilizatori");
        dateBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateBTNActionPerformed(evt);
            }
        });

        addClientBTN.setText("Adauga utilizator nou");
        addClientBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClientBTNActionPerformed(evt);
            }
        });

        closeBTN.setText("Inchidere");
        closeBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBTNActionPerformed(evt);
            }
        });

        refreshBTN.setText("Actulizare tabela");
        refreshBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addClientBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(closeBTN))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(refreshBTN)
                                .addGap(0, 498, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addClientBTN)
                    .addComponent(refreshBTN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateBTN)
                    .addComponent(closeBTN))
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBTNActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_closeBTNActionPerformed

    private void clientTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientTableMouseClicked
        // TODO add your handling code here: String paneTxt = null;
      
        int row = clientTable.getSelectedRow();
         rowSelectedForModify = row;
         System.out.println("row "+ row);

    }//GEN-LAST:event_clientTableMouseClicked

    private void addClientBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClientBTNActionPerformed
        // TODO add your handling code here:
       new AdaugaUtilizatori().setVisible(true);
    }//GEN-LAST:event_addClientBTNActionPerformed
public static int getSelectedForModify(){
    return rowSelectedForModify;
}
    private void dateBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateBTNActionPerformed
        System.out.println(getSelectedForModify());
        new ModificaUtilizatori().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_dateBTNActionPerformed

    private void refreshBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBTNActionPerformed
        // TODO add your handling code here:
        clientTable.removeAll();
        users.clear();
        try {
            dbConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UtilizatoriDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        UtilizatorTableModel clMod = new UtilizatorTableModel(users,drept);
        clientTable.setModel(clMod);
        
        
    }//GEN-LAST:event_refreshBTNActionPerformed

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
            java.util.logging.Logger.getLogger(UtilizatoriDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UtilizatoriDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UtilizatoriDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UtilizatoriDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UtilizatoriDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addClientBTN;
    private javax.swing.JEditorPane clientTXT;
    private javax.swing.JTable clientTable;
    private javax.swing.JButton closeBTN;
    private javax.swing.JButton dateBTN;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton refreshBTN;
    // End of variables declaration//GEN-END:variables
}
