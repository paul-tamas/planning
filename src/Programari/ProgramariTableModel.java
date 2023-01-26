/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Programari;

import clienti.Clienti;
import static database.DbConnection.cl;
import static database.DbConnection.prg;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import login.LoginMode;
import dashboard.Dashboard.*;
import static database.DbConnection.usr;
/**
 *
 * @author Paul
 */
public class ProgramariTableModel extends AbstractTableModel {

    String[] column = {"Nr.Crt.", "Client", "Ora", "Data", "Descriere Programare", "Descriere Client"};
ArrayList<Programari> programari;
ArrayList<Clienti>client;
int index=1;
    public ProgramariTableModel(ArrayList<Programari> programari, ArrayList<Clienti> client) {
        this.programari = programari;
        this.client = client;
    }

  

    @Override
    public int getRowCount() {
        
        return programari.size();
    }

    @Override
    public int getColumnCount() {
        return column.length - 1;
    }

    @Override
    public String getColumnName(int nr) {
        return column[nr];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Programari p = programari.get(rowIndex);
//        System.out.println("p   "+ p.idClient);

      Clienti c = null;
      boolean gasit=true;
        for (int i=0;i<client.size() && gasit;i++){
        c= client.get(i);
        if(c.getID() == p.idClient)
            gasit = false;
        }
        switch (columnIndex) {
            case 0:
                return rowIndex+1;
            case 1:
                return c.getName();
            case 2:
                return p.getHour();
            case 3:
                return p.getData();
            case 4:
                return p.descriereProgramare;
            case 5:
            //c.descriere;
        }

        return null;
    }
}
