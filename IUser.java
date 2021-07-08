/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import models.User;

/**
 *
 * @author THIERNO A. BALDE
 */
public interface IUser {
    
    public User getConnection (String utilisateur, String password);
    
}
