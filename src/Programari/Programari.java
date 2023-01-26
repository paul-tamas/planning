/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Programari;

//
//import java.sql.Date;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Paul
 */
public class Programari {
    int id;
    String ora;
    Timestamp data;
    String descriereProgramare;
    int idClient;
    int idUser;
 SimpleDateFormat DateFor = new SimpleDateFormat("YYYY-MM-DD");

    public Programari(int id, String ora, Timestamp data, String descriereProgramare, int idClient, int idUser) throws ParseException {
//        this.data
        this.id = id;
        this.ora = ora;
        this.data = data;
        this.descriereProgramare = descriereProgramare;
        this.idClient = idClient;
        this.idUser = idUser;
    }
    
    public String getData() {
          Date timeD = new Date(data.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy");
        return sdf.format(timeD);
  }
    public String getHour() {
        String ora = data.getHours()+":"+data.getMinutes()+":"+data.getSeconds();
        System.out.println(ora);
    return ora;
  }
    public String descriereProgramare(){
    return descriereProgramare;
    }
  public String afisare(){
      return (this.id+","+this.ora+","+this.data+","+this.descriereProgramare+this.idClient);
  }
      public int idClient(){
          return idClient;
      }
      public int getID(){
          return id;
      }
}
