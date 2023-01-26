/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Programari;

import clienti.AdaugaClient;
import clienti.Clienti;
import java.awt.Color;
import dashboard.Dashboard;
import static database.DbConnection.cl;
import static database.DbConnection.prg;
import static database.DbConnection.usr;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import login.DatabaseConnection;
import login.Login;
import login.LoginMode;

/**
 *
 * @author Paul
 */
public class AdaugaProgramare extends javax.swing.JFrame {

    /**
     * Creates new form AdaugaProgramare
     */
    public AdaugaProgramare() {
        initComponents();
        this.setTitle("Adauga programare");
        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        // Move the window
        this.setLocation(x, y);
        addButton.setBackground(Color.green);
        addButton.setLabel("Salveaza");
        cancelBtn.setBackground(Color.red);
        cancelBtn.setLabel("Renunta");
        String sessionUser;
        sessionUser = Dashboard.getConnectedUser();
        userField.setText(sessionUser);
        //user Field not editable
        userField.enableInputMethods(false);
        userField.setEditable(false);
        // set format for hour field
        timeFieldFormat();
       descriptionArea.setLineWrap(true);
        //read from DataBase data\
        try {

            dbConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AdaugaProgramare.class.getName()).log(Level.SEVERE, null, ex);
        }
        //fill client list
        fillClientChoice();
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
checkURL();
    }
//private void formatFilter(){
//       hourFieldFormat.addKeyListener(new KeyAdapter() {
//        public void keyPressed(KeyEvent e) {
//
//            int key = e.getKeyCode();
//
//            /* Restrict input to only integers */
//            if (key < 96 && key > 105) 
//                e.setKeyChar("");
//        };
//    });
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

//}
    ArrayList<Clienti> client = new ArrayList<Clienti>();
    ArrayList<Programari> programari = new ArrayList<Programari>();

    ArrayList<LoginMode> users = new ArrayList<LoginMode>();

    public void dbConnection() throws SQLException {

        programari = prg();
        client = cl();
        users = usr(userField.getText().toString());
    }

    // in that method i get data from db table client and display it in GUI
    private void fillClientChoice() {
        for (int i = 0; i < client.size(); i++) {
            clientChoice.addItem(client.get(i).getName());
        }
    }

    private AdaugaProgramare(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void timeFieldFormat() {
        DateFormat dateFormat = null;
        DateFormatter dateFormatter = new DateFormatter(dateFormat);

//  hourFieldFormat.setFormatterFactory(new DefaultFormatterFactory(dateFormatter));
//  hourFieldFormat = new JFormattedTextField(new SimpleDateFormat("HH:mm"));
//            hourFieldFormat.setValue(new Date().getTime());
//           hourFieldFormat.setBounds(140,384,145,31);
//            //add(hourFieldFormat);
//            System.out.println("time format");
    }
    //in that method i count how many charactes user typed 

    private void charCounter() {
        String txtArea = descriptionArea.getText();
        int charCount;
        charCount = 399 - txtArea.length();
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        clientChoice = new java.awt.Choice();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionArea = new javax.swing.JTextArea();
        userField = new javax.swing.JTextField();
        characterCounter = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        addButton = new java.awt.Button();
        cancelBtn = new java.awt.Button();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        hourField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        minutesField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        mounthField = new javax.swing.JTextField();
        yearField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        dayField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Ora programarii:");

        jLabel2.setText("Data programarii:");

        jLabel3.setText("Descrierea programarii: (maxim 400 caractere):");

        jLabel4.setText("Clientul programat:");

        jLabel5.setText("Utilizatorul care a adaugat programarea:");

        descriptionArea.setColumns(20);
        descriptionArea.setRows(5);
        descriptionArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descriptionAreaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(descriptionArea);

        userField.setText("jTextField3");

        characterCounter.setText("0");

        jLabel7.setText("/400");

        addButton.setLabel("button1");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        cancelBtn.setLabel("button1");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        jLabel6.setText("HH");

        jLabel8.setText("AAAA");

        jLabel9.setText(":");

        jLabel10.setText("MM");

        jLabel11.setText("/");

        jLabel12.setText("/");

        jLabel13.setText("MM");

        jLabel14.setText("ZZ");

        jButton1.setText("Adauga client nou ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(characterCounter)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)
                        .addGap(249, 249, 249))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(hourField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(minutesField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dayField)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mounthField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yearField, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(163, 163, 163))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel13)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel8)
                                .addGap(188, 188, 188))))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clientChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(hourField)
                    .addComponent(jLabel9)
                    .addComponent(minutesField)
                    .addComponent(mounthField)
                    .addComponent(yearField)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(dayField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4)
                        .addComponent(clientChoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(characterCounter)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void descriptionAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descriptionAreaKeyTyped
        // TODO add your handling code here:
        charCounter();
        System.out.println("pressss");
    }//GEN-LAST:event_descriptionAreaKeyTyped

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        this.dispose();       // TODO add your handling code here:
    }//GEN-LAST:event_cancelBtnActionPerformed
    private int getConnectedUserId() {
        System.out.println("user data" + users.get(0) + "user id " + users.get(0).getID());
        return users.get(0).getID();

    }
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        String oraS = hourField.getText() + ":" + minutesField.getText()+":00";
        String dataS = yearField.getText() + "-" + mounthField.getText() + "-" + dayField.getText();
        String dateTime = dataS+ " " + oraS;
        Timestamp ts= Timestamp.valueOf(dateTime);
        System.out.println(clientChoice.getSelectedIndex() + "idcliebt");
        int idClient = client.get(clientChoice.getSelectedIndex()).getID();
        int idUser = 0;
        System.out.println(clientChoice.getItemCount());
        String descriere = descriptionArea.getText();
        if (Dashboard.getConnectedUser().isEmpty()) {
            JOptionPane.showMessageDialog(new JFrame(), "Completati toate campurile ", "Dialog",
                    JOptionPane.ERROR_MESSAGE);

        } else {
            idUser = getConnectedUserId();
        }
        
        System.out.println("dat1e" + dataS);
        System.out.println("or1a" + oraS);
        //   Time ora = oraS;
//        LocalDate date = LocalDate.parse(dataS);
        // LocalDate time = LocalDate.parse(oraS);

        try (Connection connection = DriverManager.getConnection(url, Dbuser, password)) {

        } catch (SQLException e) {
            // throw new IllegalStateException("Cannot connect the database!", e);
            String message = "Cannot connect the database!";

            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);

        }
        // ResultSet rs = null;
        Statement stmt = null;
        System.out.println("Connecting database...");
        System.out.println(hourField.getText().isEmpty() + " " + minutesField.getText().isEmpty() + " " + dayField.getText().isEmpty() + " " + mounthField.getText().isEmpty()
                + " " + yearField.getText().isEmpty() + " " + descriptionArea.getText().isEmpty());
//      stmt = conn.createStatement();
        System.out.println(dataS.isEmpty());
        System.out.println(descriere.isEmpty());
        System.out.println("Loading driver...");
        if (minutesField.getText().isEmpty() && hourField.getText().isEmpty()
                && dayField.getText().isEmpty() && mounthField.getText().isEmpty()
                && yearField.getText().isEmpty() && descriptionArea.getText().isEmpty()
                && idClient > 0 && idUser > 0) {
//            JOptionPane.showMessageDialog(new JFrame(), "Completati toate campurile ", "Dialog",
//                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                try (Connection connection = DriverManager.getConnection(url, Dbuser, password)) {

                    stmt = connection.createStatement();
          //            sql = "insert into users ('u_fname', 'u_lname', 'u_uname', 'u_pass', 'u_age', 'u_adderess')"
//                + "values('" + s.getFirstname() + "','" + s.getLastname()
//                + "','" + s.getUsername() + "','" + s.getPassword() + "','" + s.getAge() + "','" + s.getAdderss() + "')";
//        
                     stmt.executeUpdate("INSERT INTO programari (ora,data,descriereProgramare,idClient,idUser)" 
                             + "VALUES ('"+oraS+"','"+ ts+"','"+descriptionArea.getText()+"','"+idClient+"','"+idUser+"')");
//INSERT INTO programari (`ora`,`data`,`descriereProgramare`,`idClient`,`idUser`) VALUES ("14:00","2022-01-22","Terapie ca nu mai il vrea doamna","1","1")
                    System.out.println("am adaugat programare pentru: " + oraS + ", " + dataS + ", " + descriere + ", " + idClient + ", " + idUser);
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
            //statement.executeUpdate("INSERT INTO Customers " + "VALUES (1004, 'Cramden', 'Mr.', 'New York', 2001)");
            //INSERT INTO programari (`ora`,`data`,`descriereProgramare`,`idClient`,`idUser`) VALUES ("14:00","2022-01-22","Terapie ca nu mai il vrea doamna","1","1")
//        String string = "INSERT INTO programari " + "VALUES (oraS, dataS, descriere, 'New York', 2001)"
            this.dispose();
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new AdaugaClient().show(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AdaugaProgramare.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdaugaProgramare.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdaugaProgramare.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdaugaProgramare.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdaugaProgramare().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button addButton;
    private java.awt.Button cancelBtn;
    private javax.swing.JLabel characterCounter;
    private java.awt.Choice clientChoice;
    private javax.swing.JTextField dayField;
    private javax.swing.JTextArea descriptionArea;
    private javax.swing.JTextField hourField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField minutesField;
    private javax.swing.JTextField mounthField;
    private javax.swing.JTextField userField;
    private javax.swing.JTextField yearField;
    // End of variables declaration//GEN-END:variables
}
