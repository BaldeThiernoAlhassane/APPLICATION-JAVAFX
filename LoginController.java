/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import helpers.DatabaseConnection;
import helpers.DbConnect;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author THIERNO A. BALDE
 */
public class LoginController implements Initializable {
    
    @FXML
    private TextField utilisateurTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Button btnConnexion;
   
    @FXML
    private Label loginMessageLabel;
  
    
     private void validateLogin() {
         DbConnect bdconn = new DbConnect();
         Connection connection = bdconn.getConnect();
       
         String verifyLogin = "SELECT * FROM `user` WHERE nomUtilisateur = '" + utilisateurTxt.getText() + "' AND password = '" + passwordTxt.getText()+ "'";
         
         try{
             Statement statement = connection.createStatement();
             ResultSet queryResult = statement.executeQuery(verifyLogin);
             
             if(queryResult.next()){
                  loadStage("/tableView/accueil.fxml");
              /*   if(queryResult.getInt(1)==1){
                     loginMessageLabel.setText("cONNECTION REUSSI");
                      //loadStage("/tableView/accueil.fxml");
                 }else{
                     loginMessageLabel.setText("Identifiant Ivalide. Svp réessayer");
                 }*/
             }
             else{
                 loginMessageLabel.setText("Identifiant Ivalide. Svp réessayer");
             }
         }catch(Exception e){
             e.printStackTrace();
             e.getCause();
         }
    }
    
     @FXML
    private void handleButtonClicks(ActionEvent mouseEvent) {
      if(mouseEvent.getSource()==btnConnexion){
           if(utilisateurTxt.getText().isEmpty()==false && passwordTxt.getText().isEmpty()==false){
            validateLogin();
        }else{
            loginMessageLabel.setText("Entrer l'utilisateur et le mot de passe ");
        }
      }
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
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
