/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementation;

import Interface.IProfesseur;
import java.util.ArrayList;
import java.util.List;
import models.Professeur;

/**
 *
 * @author THIERNO A. BALDE
 */
public class ProfesseurImp implements IProfesseur{

   ArrayList<Professeur> professeurs = new ArrayList<>(); 
        
    @Override
    public void addProfesseur(Professeur professeur){
        professeurs.add(professeur);
    
    }
    
    @Override
     public void updateProfesseur(Professeur professeur){
        
         for(Professeur professeur2:professeurs){
         
             if(professeur2.getId()==professeur.getId()){
                 professeur2.setMatricule(professeur.getMatricule());
                 professeur2.setPrenom(professeur.getPrenom());
                 professeur2.setNom(professeur.getNom());
                 professeur2.setDateNaissance(professeur.getDateNaissance());
                 professeur2.setLieuNaissance(professeur.getLieuNaissance());
                 professeur2.setEmail(professeur.getEmail());
                 professeur2.setNationalite(professeur.getNationalite());
                 professeur2.setGenre(professeur.getGenre());
                 professeur2.setAdresse(professeur.getAdresse());
                 professeur2.setTelephone(professeur.getTelephone());
                 professeur2.setSituationMatrimoniale(professeur.getSituationMatrimoniale());
                 professeur2.setSpecialite(professeur.getSpecialite());
                 
                 
             }
         
         }
    
    }
    
    @Override
     public void deleteProfesseur(Professeur professeur){
         professeurs.remove(professeur);
    
    }
     
    @Override
     public Professeur getProfesseurByid(int id){
        for(Professeur professeur:professeurs){
             if(professeur.getId()==id){
                      return professeur;
             }
         }
         return null;
     }
          
    @Override
     public List<Professeur> getAllProfesseur(){
        return professeurs;
     
     }
}
