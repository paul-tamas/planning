/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienti;


import dashboard.Dashboard;
import static database.DbConnection.cl;
import static database.DbConnection.prg;
import static database.DbConnection.usr;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
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

/**
 *
 * @author Paul
 */
public class AdaugaClient extends javax.swing.JFrame {

    /**
     * Creates new form adaugaClienti
     */
    public AdaugaClient() {
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

        username = sessionUser = Dashboard.getConnectedUser();

        utilizator.setText(sessionUser);
        utilizator.setEditable(false);
        System.out.println(utilizator.getText().toString() + " userfield");
        descriere.setLineWrap(true);
        try {
            dbConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AdaugaClient.class.getName()).log(Level.SEVERE, null, ex);
         }
        checkURL();
    }
    ArrayList<LoginMode> users = new ArrayList<LoginMode>();
    String username;
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

        // System.out.println("logged user "+ utilizator.getText().toString());
        System.out.println("logged user " + username);
        users = usr(utilizator.getText().toString());
        System.out.println(" citit");
        //System.out.println(users.get(0));
    }

    private void charCounter() {
        String txtArea = descriere.getText();
        int charCount;
        charCount = 299 - txtArea.length();
        if (charCount <= 1) {
            JOptionPane.showMessageDialog(null, "Ati atins numarul maxim de caractere, salvarea descrierii nu se poate realiza");

        }
//  txtArea=(String) charCount;
        characterCounter.setText(String.valueOf(charCount));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        descriere = new javax.swing.JTextArea();
        nume = new javax.swing.JTextField();
        varsta = new javax.swing.JTextField();
        telefon = new javax.swing.JTextField();
        mail = new javax.swing.JTextField();
        utilizator = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        cancelBTN = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        characterCounter = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        descriere.setColumns(20);
        descriere.setRows(5);
        descriere.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                descriereKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(descriere);

        jLabel1.setText("Nume");

        jLabel2.setText("Varsta");

        jLabel3.setText("Telefon");

        jLabel4.setText("Mail");

        jLabel5.setText("Descriere");

        jLabel6.setText("Adaugat de ");

        addBtn.setText("Adauga");
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

        jLabel7.setText("(numarul maxim de caractere admis este ");

        characterCounter.setText("0");

        jLabel9.setText("/300)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addBtn)
                .addGap(34, 34, 34)
                .addComponent(cancelBTN)
                .addGap(157, 157, 157))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(utilizator)
                            .addComponent(mail)
                            .addComponent(telefon)
                            .addComponent(varsta)
                            .addComponent(nume))
                        .addGap(264, 264, 264))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(characterCounter, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)))
                        .addContainerGap(142, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nume)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(varsta)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefon)
                    .addComponent(jLabel3))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mail)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(characterCounter)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(utilizator, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn)
                    .addComponent(cancelBTN)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBTNActionPerformed
        this.dispose();     // TODO add your handling code here:
    }//GEN-LAST:event_cancelBTNActionPerformed

    private void descriereKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descriereKeyPressed
        // TODO add your handling code here:
        charCounter();
    }//GEN-LAST:event_descriereKeyPressed
    private int getConnectedUserId() {
        System.out.println("user data" + users.get(0) + "user id " + users.get(0).getID());
        return users.get(0).getID();

    }
    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed

        int idUser = 0;
        if (Dashboard.getConnectedUser().isEmpty()) {
            JOptionPane.showMessageDialog(new JFrame(), "Completati toate campurile ", "Dialog",
                    JOptionPane.ERROR_MESSAGE);

        } else {
            idUser = getConnectedUserId();
        }

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
                //            sql = "insert into users ('u_fname', 'u_lname', 'u_uname', 'u_pass', 'u_age', 'u_adderess')"
//                + "values('" + s.getFirstname() + "','" + s.getLastname()
//                + "','" + s.getUsername() + "','" + s.getPassword() + "','" + s.getAge() + "','" + s.getAdderss() + "')";
//        
                stmt.executeUpdate("INSERT INTO date_clienti (nume,varsta,telefon,mail,descriere,idUser)"
                        + "VALUES ('" + nume.getText() + "','" + Integer.parseInt(varsta.getText()) + "','"
                        + telefon.getText() + "','" + mail.getText() + "','"
                        + descriere.getText() + "','" + idUser + "')");
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
        }        // TODO add your handling code here:
    }//GEN-LAST:event_addBtnActionPerformed

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
            java.util.logging.Logger.getLogger(AdaugaClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdaugaClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdaugaClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdaugaClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdaugaClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton cancelBTN;
    private javax.swing.JLabel characterCounter;
    private javax.swing.JTextArea descriere;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField mail;
    private javax.swing.JTextField nume;
    private javax.swing.JTextField telefon;
    private javax.swing.JTextField utilizator;
    private javax.swing.JTextField varsta;
    // End of variables declaration//GEN-END:variables
}