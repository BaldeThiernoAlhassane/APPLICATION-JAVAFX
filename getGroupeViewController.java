/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import helpers.DbConnect;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Etudiant;

/**
 * FXML Controller class
 *
 * @author THIERNO A. BALDE
 */
public class getGroupeViewController implements Initializable {

    @FXML
    private TextField nomGroupeTxt;
    
    
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void valider(MouseEvent event) {
        
        connection = DbConnect.getConnect();
        
        String nomGroupe = nomGroupeTxt.getText();
        
        
        if(nomGroupe.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Donner un groupe");
            alert.showAndWait();
            
        }else{
            
            //getQuery();
            select();
            clean();
            
        }
    }
    
    

    @FXML
    private void clean() {
        nomGroupeTxt.setText(null);
        
        
    }

//    private void getQuery() {
//        
//        if(update == false){
//            query = "INSERT INTO `etudiant`(`matricule`, `prenom`, `nom`, `dateNaissance`, `lieuNaissance`, `genre`, `nationalite`, `adresse`, `email`, `telephone`, `groupeSanguin`, `situationMatrimoniale`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
//        }else{
//            query ="UPDATE `etudiant` SET "
//                    + "`matricule`=?,"
//                    + "`prenom`=?,"
//                    + "`nom`=?,"
//                    + "`dateNaissance`=?,"
//                    + "`lieuNaissance`=?,"
//                    + "`genre`=?,"
//                    + "`nationalite`=?,"
//                    + "`adresse`=?,"
//                    + "`email`=?,"
//                    + "`telephone`=?,"
//                    + "`groupeSanguin`=?,"
//                    + "`situationMatrimoniale`=? WHERE id= '"+etudiantId+"'";
//        }
//    }
        

    private void select() {
        
        try{
           preparedStatement = connection.prepareStatement("SELECT * FROM `gestion_scolaire` WHERE nomGroupe= '"+nomGroupeTxt.getText()+"'");
           resultSet = preparedStatement.executeQuery();
           if(resultSet != null){
               loadStage("tableView/tableViewGroupe.fxml");
           }
        }catch(SQLException ex){
                        Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }

         private void loadStage(String fxml) {
         
         
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UTILITY);
//            stage.getIcons().add(new Image("/home/icons/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
