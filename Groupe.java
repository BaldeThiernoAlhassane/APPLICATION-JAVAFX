/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author THIERNO A. BALDE
 */
public class Groupe {
    private int id;
    private String nomGroupe;
    private Date dateCreation;
   
    
    public Groupe(){
    
    }

    public Groupe(int id, String nomGroupe, Date dateCreation) {
        this.id = id;
        this.nomGroupe = nomGroupe;
        this.dateCreation = dateCreation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return "Groupe{" + "id=" + id + ", nomGroupe=" + nomGroupe + ", dateCreation=" + dateCreation + '}';
    }

}