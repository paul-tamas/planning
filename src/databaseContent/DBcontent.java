/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseContent;
/// de verificat de ce nu se conecteaza laptopul la baza de date

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import login.DatabaseConnection;
import login.LoginMode;

/**
 *
 * @author paul_
 */
public class DBcontent {

//    static String DBserver = null;
//    static String database = null;
//    static String Dbuser = null;
//    static String password = null;
//    static String url = "";
//    static String certificates = "?verifyServerCertificate=false"
//            + "&useSSL=true"
//            + "&requireSSL=false";
//    static File configFile;
//static String  checkURL(){
//    if(url.isEmpty())
//          configFile = new File("config.ini");
//      Scanner myReader = null;
//        try {
//            myReader = new Scanner(configFile);
//        } catch (FileNotFoundException ex) {
//            System.out.println("File not found");
//            new DatabaseConnection().setVisible(true);
//        }
//      while (myReader.hasNextLine()) {
//        String data = myReader.nextLine();
//         String parts[] = data.split("=");
//          System.out.println(parts[0] +"|||||| " +parts[1]);
//      
//        System.out.println(data);
//        if(parts[0].equals("STRING") || parts[0].equals("string")){
//          String[] sp = parts[1].split(";");
//          url = sp[0];
//          Dbuser=sp[1];
//          password=sp[2];
//                  
//            System.out.println("url+"+url);
//        }
//        if(parts[0].equals("BAZADEDATE")){
//            database = parts[1];
//            System.out.println("database"+database);
//        }
//      }
//      myReader.close();
//      url+=certificates;
//        System.out.println(" url:"+ url);
//        System.out.println(" url:"+ Dbuser);
//        System.out.println(" url:"+ password);
//   //  url = url+database;
//   return url +"`"+Dbuser+"`"+password;
//    }
    public void databaseCreation(String databaseName, String serverAddress, String port, String Dbuser, String Dbpass) {
        try {
            String url = "jdbc:mysql://"+serverAddress+":"+port+"/mysql?zeroDateTimeBehavior=convertToNull";
            Connection connection = DriverManager.getConnection(url, Dbuser, Dbpass);

            String sql = "CREATE DATABASE " + databaseName;

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            JOptionPane.showMessageDialog(null, databaseName + " Database has been created successfully", "System Message", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
//            e.printStackTrace();
System.out.println("exception "+ e );
           
        }
    }

    public void databaseInsertion(String url, String Dbuser, String password) {
//        checkURL();
        Statement stmt = null;
        System.out.println("Connecting database...");

        System.out.println("Loading driver...");

        try {
            System.out.println("load sql tables");
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, Dbuser, password)) {
                System.out.println("Driver loaded!");

//               
                stmt = conn.createStatement();
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `drepturi` (\n"
                        + "  `id` int(11) NOT NULL AUTO_INCREMENT,\n"
                        + "  `drept` varchar(50) DEFAULT NULL,\n"
                        + "  PRIMARY KEY (`id`)\n"
                        + ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1");
                System.out.println("table drepturi load");
                stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO `drepturi` (`id`, `drept`) VALUES\n"
                        + "	(1, 'Administrator'),\n"
                        + "	(2, 'utilizator')");
                stmt = conn.createStatement();
                System.out.println("data inserted in drepturi");

                stmt = conn.createStatement();
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `users` (\n"
                        + "  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,\n"
                        + "  `username1` varchar(50) NOT NULL,\n"
                        + "  `password` varchar(50) NOT NULL,\n"
                        + "  `id_drept` int(11) NOT NULL,\n"
                        + "  PRIMARY KEY (`id`),\n"
                        + "  UNIQUE KEY `username` (`username1`) USING BTREE,\n"
                        + "  KEY `fk drept` (`id_drept`),\n"
                        + "  CONSTRAINT `fk drept` FOREIGN KEY (`id_drept`) REFERENCES `drepturi` (`id`) ON DELETE CASCADE ON UPDATE CASCADE\n"
                        + ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1");
                System.out.println("table users loaded...... \n insert admin user");
                stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO `users` (`id`, `username1`, `password`, `id_drept`) VALUES\n"
                        + "	(1, 'admin', 'admin', 1)");
                System.out.println("data inserted in users");
                System.out.println("load table date_clienti");
                stmt = conn.createStatement();
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `date_clienti` (\n"
                        + "  `id` int(11) NOT NULL AUTO_INCREMENT,\n"
                        + "  `nume` varchar(150) NOT NULL,\n"
                        + "  `varsta` int(11) NOT NULL,\n"
                        + "  `telefon` varchar(15) DEFAULT NULL,\n"
                        + "  `mail` varchar(150) NOT NULL,\n"
                        + "  `descriere` varchar(300) NOT NULL,\n"
                        + "  `iduser` int(10) unsigned NOT NULL,\n"
                        + "  PRIMARY KEY (`id`),\n"
                        + "  KEY `fk` (`iduser`),\n"
                        + "  CONSTRAINT `fk` FOREIGN KEY (`iduser`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE\n"
                        + ") ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1");
                System.out.println(" table date_clienti loaded");
                stmt = conn.createStatement();
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `programari` (\n"
                        + "  `id` int(11) NOT NULL AUTO_INCREMENT,\n"
                        + "  `ora` varchar(50) NOT NULL,\n"
                        + "  `data` timestamp NOT NULL,\n"
                        + "  `descriereProgramare` varchar(400) NOT NULL,\n"
                        + "  `idClient` int(11) NOT NULL,\n"
                        + "  `idUser` int(11) unsigned NOT NULL,\n"
                        + "  PRIMARY KEY (`id`),\n"
                        + "  KEY `foreign key` (`idClient`),\n"
                        + "  KEY `foreign key user` (`idUser`),\n"
                        + "  CONSTRAINT `foreign key` FOREIGN KEY (`idClient`) REFERENCES `date_clienti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,\n"
                        + "  CONSTRAINT `foreign key user` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE\n"
                        + ") ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1");
                System.out.println("table programari loaded");
                System.out.println("export view creation");
                stmt = conn.createStatement();
                /** trebuie sa vad cum se creaza usor un view 
                 
                
                stmt.executeUpdate("CREATE OR REPLACE VIEW date_client  "
                        + "AS SELECT date_clienti.id, date_clienti.nume,date_clienti.varsta,"
                        + "date_clienti.telefon,date_clienti.mail, date_clienti.descriere, users.username1"
                        + "FROM date_clienti, users"
                        + "WHERE date_clienti.iduser = users.id");
                System.out.println("export view created");*/
//                stmt = conn.createStatement();
//                stmt.executeUpdate("");
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
}
