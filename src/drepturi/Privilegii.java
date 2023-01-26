/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drepturi;

/**
 *
 * @author Paul
 */
public class Privilegii {
    int id;
    String privilegiu;

    public Privilegii(int id, String privilegiu) {
        this.id = id;
        this.privilegiu = privilegiu;
    }
    public int getID(){
        return id;
    }
    public String getPrivilegiu(){
        return privilegiu;
    }
}
