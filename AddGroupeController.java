/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import helpers.DbConnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Groupe;

/**
 * FXML Controller class
 *
 * @author THIERNO A. BALDE
 */
public class AddGroupeController implements Initializable {

    @FXML
    private TextField nomGroupeTxt;
    
    @FXML
    private DatePicker dateCreationTxt;
  
    
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Groupe groupe = null;
    private boolean update;
    int groupeId;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void save(MouseEvent event) {
        
        connection = DbConnect.getConnect();
        
        String nomGroupe = nomGroupeTxt.getText();
        String dateCreation = String.valueOf(dateCreationTxt.getValue());
        
        if(nomGroupe.isEmpty()||dateCreation.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Svp veuillez remplir tous les champs");
            alert.showAndWait();
            
        }else{
            
            getQuery();
            insert();
            clean();
            
        }
    }
    
    

    @FXML
    private void clean() {
        nomGroupeTxt.setText(null);
        dateCreationTxt.setValue(null);
        
        
    }

    private void getQuery() {
        
        if(update == false){
            query = "INSERT INTO `groupe`(`nomGroupe`, `dateCreation`) VALUES (?,?)";
        }else{
            query ="UPDATE `groupe` SET "
                    + "`nomGroupe`=?,"
                    + "`dateCreation`=? WHERE id= '"+groupeId+"'";
        }
    }
        

    private void insert() {
        
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nomGroupeTxt.getText());
            preparedStatement.setString(2, String.valueOf(dateCreationTxt.getValue()));
            preparedStatement.execute();
        }catch(SQLException ex){
                        Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }

    void setUpdate(boolean b) {
         this.update = b;
    }

    void setTextField(int id, String nomGroupe, LocalDate toLocalDate) {
        groupeId = id;
        nomGroupeTxt.setText(nomGroupe);
        dateCreationTxt.setValue(toLocalDate);
        
    }
    
}
