/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.List;
import models.Groupe;

/**
 *
 * @author THIERNO A. BALDE
 */
public interface IGroupe {
    
     
    public void updateGroupe(Groupe groupe);
    
    public void deleteGroupe(Groupe groupe);
    
    public Groupe getGroupeByid(int id);
    
    public List<Groupe> getAllGroupe();
}
