/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienti;

/**
 *
 * @author Paul
 */
public class Clienti {
  private  int id;
 private  String nume;
private    int varsta;
   private String telefon;
   private String mail;
   private String descriere; 
 private  int idUser;
  public Clienti(int id, String nume, int varsta,String telefon, String mail, String descriere, int idUser) {
        this.id = id;
        this.nume = nume;
        this.varsta = varsta;
        this.telefon = telefon;
        this.mail = mail;
        this.descriere = descriere;
        this.idUser = idUser;
    }
  public int getID(){
      return id;
  }
  public String getName(){
      return nume;
  }
  
  public int getAge(){
      return varsta;
  }
  public String getPhoneNumber(){
      return telefon;
  }
  
  public String getMailAddress(){
      return mail;
  }
  public String getDescription(){
      return descriere;
  }
  public int getUserId(){
      return idUser;
  }
  
  
  
  
}
