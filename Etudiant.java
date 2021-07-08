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
public class Etudiant extends Personne {
    private String groupeSanguin;
    
    public Etudiant(){
        super();
    }
    
    public Etudiant(int id,
                        String matricule,
                        String prenom,
                        String nom,
                        Date dateNaissance,
                        String lieuNaissance,
                        String genre,
                        String nationalite,
                        String adresse,
                        String email,
                        String telephone,
                        String groupeSanguin,
                        String situationMatrimoniale)
                        {
        super(id,
    matricule,
    prenom,
    nom,
    dateNaissance,
    lieuNaissance,
    genre,
    nationalite,
    adresse,
    email,
    telephone,
    situationMatrimoniale);
        this.groupeSanguin = groupeSanguin;
}

    

   
    public String getGroupeSanguin() {
        return groupeSanguin;
    }

    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }
    
    public String afficherPrenom(){
        return super.getPrenom();
    }

    @Override
    public String toString() {
        return super.toString()+ "groupeSanguin=" + groupeSanguin + '}';
    }
    
}


