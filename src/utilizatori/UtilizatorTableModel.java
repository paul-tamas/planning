/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilizatori;

import drepturi.Privilegii;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import login.Login;
import login.LoginMode;

/**
 *
 * @author Paul
 */
public class UtilizatorTableModel extends AbstractTableModel {
 String[] column = {"Nr.Crt.", "Utilizator", "Permisiune"};

    public UtilizatorTableModel(ArrayList<LoginMode> client, ArrayList<Privilegii>drept) {
        this.user = client;
      
    }
 ArrayList<LoginMode>user;
  ArrayList<Privilegii>drept;

    @Override
    public int getRowCount() {
        System.out.println("usrS"+user.size());
       return user.size();
    }

    @Override
    public int getColumnCount() {
        System.out.println("userColS"+column.length);
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
                return user.get(rowIndex).getUser();
            case 2:
                return drept.get(user.get(rowIndex).getPermission()).getPrivilegiu();
            
        }
        return null;

    }
    
}
