/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionScolaire;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class GestionScolaire extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/tableView/login.fxml"));
            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionScolaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   

    public static void main(String[] args) {
        launch(args);
    }
    
}
