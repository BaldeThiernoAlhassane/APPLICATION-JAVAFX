/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author THIERNO A. BALDE
 */
public class DatabaseConnection {
    
    public Connection  databaseLink;
    
    public Connection getConnection(){
        String databaseName = "gestion_scolaire";
        String databaseNomUtilisateur ="Tester";
        String databasePassword = "Passer";
        String url = "jdbc:mysql://localhost/" + databaseName;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseNomUtilisateur,databasePassword);
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        
        return databaseLink;
    }
    
}
