/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



/**
 * FXML Controller class
 *
 * @author THIERNO A. BALDE
 */
public class HomeController implements Initializable {
    
     @FXML
    private Button btnAccueil;

    @FXML
    private Button btnEtudiant;

    @FXML
    private Button btnProfesseur;

    @FXML
    private Button btnGroupe;
    
    @FXML
    private Button btnGroupeEtudiant;

    @FXML
    private Button btnDeconnexion;
    
     @FXML
    private void handleButtonClicks(ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == btnAccueil) {
            loadStage("/tableView/tableau de bord.fxml");
        } else if (mouseEvent.getSource() == btnEtudiant) {
            loadStage("/tableView/tableView.fxml");
        }else if (mouseEvent.getSource() == btnProfesseur) {
            loadStage("/tableView/tableView1.fxml");
        }else if (mouseEvent.getSource() == btnGroupe) {
            loadStage("/tableView/tableView2.fxml");
        }else if (mouseEvent.getSource() == btnGroupeEtudiant) {
            loadStage("/tableView/getGroupeView.fxml");
        }else if (mouseEvent.getSource() == btnDeconnexion) {
            loadStage("/tableView/login.fxml");
        }
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

   
