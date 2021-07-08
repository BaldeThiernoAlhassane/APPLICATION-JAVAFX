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
public class Professeur extends Personne {
    
    private String specialite;
    
    public Professeur(){
        super();
    }
    
    public Professeur(int id,
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
                        String specialite,
                        String situationMatrimoniale)
                        {
                
                super( id,matricule,
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
                this.specialite = specialite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }       

    @Override
    public String toString() {
        return super.toString() + "specialite=" + specialite + '}';
    }
}

