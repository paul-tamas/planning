/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exportToExcel;

/**
 *
 * @author paul_
 * 
 */
import java.io.File;
import static java.io.FileDescriptor.out;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import static java.lang.System.out;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import static sun.misc.MessageUtils.out;
import clienti.*;
import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import login.DatabaseConnection;
import org.apache.poi.ss.usermodel.Row;
public class ExportToExcel  {
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
    public void export(String query, String fileName) throws FileNotFoundException, IOException {
       checkURL();
        String excelFilePath = "C:/Export_centrulASCO/"+fileName+"-export.xlsx";
 
        try (Connection connection = DriverManager.getConnection(url, Dbuser, password)) {
//            String sql = "CREATE OR REPLACE VIEW date_client  "
//                       + "AS SELECT date_clienti.id, date_clienti.nume,date_clienti.varsta," +
//                         "date_clienti.telefon,date_clienti.mail, date_clienti.descriere, users.username1" +
//                         "FROM date_clienti, users" +
//                         "WHERE date_clienti.iduser = users.id";
//            String sql1 = "Select * from date_client";
//            Statement statement = connection.createStatement();
//            statement.executeUpdate(sql);
           Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            XSSFWorkbook workbook = new XSSFWorkbook();
             System.out.println("workbook done ");
            XSSFSheet sheet = workbook.createSheet("Date Clienti");
  System.out.println("worksheet done ");
            writeHeaderLine(sheet);
            System.out.println("header done ");
 
            writeDataLines(result, workbook, sheet);
  
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
 
            statement.close();
            openFile(excelFilePath);
 JOptionPane.showMessageDialog(null, "Date exportate cu succes in C:/Export_centrulASCO");
        } catch (SQLException e) {
            System.out.println("Datababse error:");
            
            JOptionPane.showMessageDialog(new JFrame(), e+"\n Database error", "Dialog",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
             JOptionPane.showMessageDialog(new JFrame(), e+"\n File IO error", "Dialog",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
 
    private void writeHeaderLine(XSSFSheet sheet) {
 
        Row headerRow = sheet.createRow(0);
 
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Nr.Crt.");
 
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Nume");
 
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Varsta");
 
        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Telefon");
 
        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("Mail");
        
        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("Descriere Client");
        
//        headerCell = headerRow.createCell(6);
//        headerCell.setCellValue("Personana care l-a adagat");
//        
    }
 
    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
            XSSFSheet sheet) throws SQLException {
        int rowCount = 1;
 
        while (result.next()) {
            int id=result.getInt("id");
            String nume = result.getString("nume");
            int varsta = result.getInt("varsta");
            String telefon = result.getString("telefon");
            String mail = result.getString("mail");
            String descriere = result.getString("descriere");
//            String personal = result.getString("username1");
            
 
            Row row = sheet.createRow(rowCount++);
 
            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(id);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(nume);
 
            cell = row.createCell(columnCount++);
 
//            CellStyle cellStyle = workbook.createCellStyle();
//            CreationHelper creationHelper = workbook.getCreationHelper();
//            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
//            cell.setCellStyle(cellStyle);
// 
            cell.setCellValue(varsta);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(telefon);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(mail);
            
            cell = row.createCell(columnCount++);
            cell.setCellValue(descriere);
            
         //   cell = row.createCell(columnCount++);
           // cell.setCellValue(personal);
 
        }
    } 
    private void openFile(String path) throws IOException{
        File file = new File(path);
        
        //first check if Desktop is supported by Platform or not
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
        
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);
    }
    
}
