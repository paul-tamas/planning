/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBaseModification;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import login.DatabaseConnection;

/**
 *
 * @author paul_
 */
public class ModifytablesStructure {
      static String DBserver = null;
    static String database = null;
    static String Dbuser = null;
    static String password = null;
    static String url = "";
    static String certificates =  "?verifyServerCertificate=false"
                    + "&useSSL=true"
                    + "&requireSSL=false";
  static  File configFile;
    static String  checkURL(){
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
          String[] sp = parts[1].split(";");
          url = sp[0];
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
      url+=certificates;
        System.out.println(" url:"+ url);
        System.out.println(" url:"+ Dbuser);
        System.out.println(" url:"+ password);
   //  url = url+database;
   return url +"`"+Dbuser+"`"+password;
    }
    public void modifyTable() throws SQLException{
        checkURL();
         try (Connection connection = DriverManager.getConnection(url, Dbuser, password)) {
            
            String query = "Alter table programari modify column data1 timestamp ";
            Statement statement = connection.createStatement();
//            statement.executeQuery(sql);
           statement.executeUpdate(query);
           statement.close();
        JOptionPane.showMessageDialog(null,"Actualizat cu succes");
           //JOptionPane.showMessageDialog(null,"\n Actualizat cu succes", "Dialog",);
    }catch(SQLException e){
             JOptionPane.showMessageDialog(new JFrame(), e+"\n Database error", "Dialog",
                    JOptionPane.ERROR_MESSAGE);
             System.out.println(e);
            e.printStackTrace();
        
    }}
            
            
            
            
            
            
            
            
            
}
