/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import drepturi.Privilegii;
import Programari.Programari;
import clienti.Clienti;
import java.io.File;
import java.io.FileNotFoundException;
import login.LoginMode;
import java.sql.Connection;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import login.DatabaseConnection;
import login.Login;

/**
 *
 * @author my
 */
public class DbConnection {

    static ArrayList<LoginMode> date = new ArrayList<LoginMode>();
    static ArrayList<Clienti> clienti = new ArrayList<Clienti>();
    static ArrayList<Programari> programari = new ArrayList<Programari>();
    static ArrayList<Privilegii> privilegii = new ArrayList<Privilegii>();
//    static String DBserver = "89.42.218.232";
//    static String database = "r97687cent_CRM";
//    static String Dbuser = "r97687cent_CRM";
//    static String password = "xhSvB3qq13K?-(";
//    static String url = "jdbc:mysql://89.42.218.232:3306/" + database;
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
    
    
    static public ArrayList<LoginMode> usr(String field)  {
        date.clear();
        Login lg = new Login();
        checkURL();
        System.out.println("Connecting database...");
        lg.setStatus("Connecting database...");

//        try (Connection conn = DriverManager.getConnection(url, Dbuser, password)) {
 try (Connection conn = DriverManager.getConnection(url,Dbuser,password)) {
            System.out.println("Database connected!");
            String message = "Database connected!";
// show a dialog message
            JOptionPane.showMessageDialog(null, message);
        } catch (SQLException e) {
            // throw new IllegalStateException("Cannot connect the database!", e);
            String message = "Cannot connect the database!";

            JOptionPane.showMessageDialog(new JFrame(), e, "Dialog",
                    JOptionPane.ERROR_MESSAGE);

        }
        ResultSet rs = null;
        Statement stmt = null;
        System.out.println("Connecting database...");

        System.out.println("Loading driver...");
      
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, Dbuser, password)) {
                System.out.println("Driver loaded!");
                System.out.println("con+"+field);
              
                stmt = conn.createStatement();
              //  rs = stmt.executeQuery("select * from users");
                rs = stmt.executeQuery("select * from users where username1 =" + "'" + field + "'");
                if(rs.getRow() == 0){
                    System.out.println("empty");
                }else
                  System.out.println("pana aici o ajuns"+ rs.getInt("id"));
 int i=0;
                while (rs.next()) 
                {
                    System.out.println("test "+rs.getInt("id") + " " + rs.getString("username1") + " " + rs.getString("password") + " " + rs.getInt("id_drept"));
                    date.add(new LoginMode(rs.getInt("id"), rs.getString("username1"), rs.getString("password"), rs.getInt("id_drept")));
//                    conn.close();
System.out.println(date.get(i++));
                }
                //conn.close();
            }
        } catch (ClassNotFoundException e) {
            //throw new IllegalStateException("Cannot find the driver in the classpath!", e);
            System.out.println("Cannot find the driver in the classpath!" + e);
        } catch (SQLException e) {
//    throw new IllegalStateException("Cannot connect the database!", e);
            System.out.println("Cannot connect the database!" + " " + e);
            JOptionPane.showMessageDialog(null, "Cannot connect the database!" + " " + e);
        }

        return date;
    }

    static public ArrayList<Clienti> cl() throws SQLException {
checkURL();
        clienti.clear();

        try (Connection connection = DriverManager.getConnection(url, Dbuser, password)) {

        } catch (SQLException e) {
            // throw new IllegalStateException("Cannot connect the database!", e);
            String message = "Cannot connect the database!";

            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);

        }
        ResultSet rs = null;
        Statement stmt = null;
        System.out.println("Connecting database...");

        System.out.println("Loading driver...");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, Dbuser, password)) {

                stmt = connection.createStatement();
                rs = stmt.executeQuery("select * from date_clienti");

                while (rs.next()) {
                    // System.out.println(rs.getInt("id") + " " + rs.getString("username") + " " + rs.getString("password") + " " + rs.getInt("id_drept"));
                    clienti.add(new Clienti(rs.getInt("id"), rs.getString("nume"), rs.getInt("varsta"),rs.getString("telefon"), rs.getString("mail"), rs.getString("descriere"), rs.getInt("iduser")));
//                    usr.close();
                }
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
        return clienti;
    }

    static public ArrayList<Programari> prg() throws SQLException {
        programari.clear();
        System.out.println("programari");
checkURL();
        try (Connection connection = DriverManager.getConnection(url, Dbuser, password)) {

        } catch (SQLException e) {
            // throw new IllegalStateException("Cannot connect the database!", e);
            String message = "Cannot connect the database!";

            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);

        }
        ResultSet rs = null;
        Statement stmt = null;
        System.out.println("Connecting database...");

        System.out.println("Loading driver...");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, Dbuser, password)) {

                stmt = connection.createStatement();
                System.out.println("testtt");
                rs = stmt.executeQuery("select * from programari");

                while (rs.next()) {
                    try {
                        // System.out.println(rs.getInt("id"), rs.getTime("ora"),rs.getDate("data"), rs.getString("descriereProgramare"),rs.getInt("idClient"), rs.getInt("idUser") +" test");
                      //  System.out.println("ora:" + rs.getTime("ora"));
                       System.out.println("data:" + rs.getDate("data"));
                        programari.add(new Programari(rs.getInt("id"), rs.getString("ora"), rs.getTimestamp("data"), rs.getString("descriereProgramare"), rs.getInt("idClient"), rs.getInt("idUser")));
                    } catch (ParseException ex) {
                        Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
//                usr.close();
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
        return programari;
    }

    static public ArrayList<Privilegii> drpt() throws SQLException {
checkURL();
        try (Connection connection = DriverManager.getConnection(url, Dbuser, password)) {

        } catch (SQLException e) {
            // throw new IllegalStateException("Cannot connect the database!", e);
            String message = "Cannot connect the database!";

            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);

        }
        ResultSet rs = null;
        Statement stmt = null;
        System.out.println("Connecting database...");

        System.out.println("Loading driver...");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, Dbuser, password)) {

                stmt = connection.createStatement();
                rs = stmt.executeQuery("select * from drepturi");

                while (rs.next()) {
                    // System.out.println(rs.getInt("id") + " " + rs.getString("username") + " " + rs.getString("password") + " " + rs.getInt("id_drept"));
                    privilegii.add(new Privilegii(rs.getInt("id"), rs.getString("drept")));

                }
//                usr.close();
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
        return privilegii;
    }
}
