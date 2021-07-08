/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementation;

import Interface.IGroupe;
import java.util.ArrayList;
import java.util.List;
import models.Groupe;

/**
 *
 * @author THIERNO A. BALDE
 */
public class GroupeImp implements IGroupe {
    
    ArrayList<Groupe> groupes = new ArrayList<>();

//    @Override
    public void addGroupe(Groupe groupe) {
        groupes.add(groupe);
    }

 @Override
    public void updateGroupe(Groupe groupe) {
        for(Groupe groupe2:groupes){
            if(groupe2.getId()==groupe.getId()){
                 groupe2.setNomGroupe(groupe.getNomGroupe());
                 groupe2.setDateCreation(groupe.getDateCreation());
                 }
         }
    }

    @Override
    public void deleteGroupe(Groupe groupe) { 
        groupes.remove(groupe);
    }

    @Override
    public Groupe getGroupeByid(int id) {
        for(Groupe groupe:groupes){
             if(groupe.getId()==id){
                      return groupe;
             }
         }
         return null;
    }

    @Override
    public List<Groupe> getAllGroupe() {
        return groupes;
    }
    
    

}