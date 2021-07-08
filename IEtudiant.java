/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.List;
import models.Etudiant;

/**
 *
 * @author THIERNO A. BALDE
 */
public interface IEtudiant {
    
    public void addEtudiant(Etudiant etudiant);
    
    public void updateEtudiant(Etudiant etudiant);
    
    public void deleteEtudiant(Etudiant etudiant);
    
    public Etudiant getEtudiantByid(int id);
    
    public List<Etudiant> getAllEtudiant();
    
}
