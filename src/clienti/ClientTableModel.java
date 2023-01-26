/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienti;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import login.Login;
import login.LoginMode;

/**
 *
 * @author Paul
 */
public class ClientTableModel extends AbstractTableModel {
 String[] column = {"Nr.Crt.", "Nume", "Varsta","Numar de telefon", "Adresa de email", "Descriere Client", "Introdus in sistem de:"};

    public ClientTableModel(ArrayList<Clienti> client) {
        this.client = client;
      
    }
 ArrayList<Clienti>client;
 ArrayList<LoginMode>date;
    @Override
    public int getRowCount() {
       return client.size();
    }

    @Override
    public int getColumnCount() {
    return column.length -1 ;   
    }
 @Override
 public String getColumnName(int nr) {
        return column[nr];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rowIndex+1;
            case 1:
                return client.get(rowIndex).getName();
            case 2:
                return client.get(rowIndex).getAge();
            case 3:
                return client.get(rowIndex).getPhoneNumber();
            case 4:
                return client.get(rowIndex).getMailAddress();
            case 5:
                return client.get(rowIndex).getDescription();
        }
        return null;

    }
    
}
