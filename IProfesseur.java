/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.List;
import models.Professeur;

/**
 *
 * @author THIERNO A. BALDE
 */
public interface IProfesseur {
    
     public void addProfesseur(Professeur professeur);
    
    public void updateProfesseur(Professeur professeur);
    
    public void deleteProfesseur(Professeur professeur);
    
    public Professeur getProfesseurByid(int id);
    
    public List<Professeur> getAllProfesseur();
      
    
}
