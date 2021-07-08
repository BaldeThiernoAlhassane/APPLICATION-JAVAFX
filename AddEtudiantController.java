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
import models.Etudiant;

/**
 * FXML Controller class
 *
 * @author THIERNO A. BALDE
 */
public class AddEtudiantController implements Initializable {

    @FXML
    private TextField matriculeTxt;
    @FXML
    private TextField prenomTxt;
    @FXML
    private TextField nomTxt;
    @FXML
    private DatePicker dateNaissanceTxt;
    @FXML
    private TextField lieuNaissanceTxt;
    @FXML
    private TextField genreTxt;
    @FXML
    private TextField nationaliteTxt;
    @FXML
    private TextField adresseTxt;
    @FXML
    private TextField emailTxt;
    @FXML
    private TextField telephoneTxt;
    @FXML
    private TextField groupeSanguinTxt;
    @FXML
    private TextField situationMatrimonialeTxt;
    
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Etudiant etudiant = null;
    private boolean update;
    int etudiantId;
    

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
        
        String matricule = matriculeTxt.getText();
        String prenom = prenomTxt.getText();
        String nom = nomTxt.getText();
        String dateNaissance = String.valueOf(dateNaissanceTxt.getValue());
        String lieuNaissance = lieuNaissanceTxt.getText();
        String genre = genreTxt.getText();
        String nationalite = nationaliteTxt.getText();
        String adresse = adresseTxt.getText();
        String email = emailTxt.getText();
        String telephone = telephoneTxt.getText();
        String groupeSanguin = groupeSanguinTxt.getText();
        String situationMatrimoniale = situationMatrimonialeTxt.getText();
        
        if(matricule.isEmpty()||prenom.isEmpty()||nom.isEmpty()||dateNaissance.isEmpty()||lieuNaissance.isEmpty()||genre.isEmpty()||
            nationalite.isEmpty()||adresse.isEmpty()||email.isEmpty()||telephone.isEmpty()||groupeSanguin.isEmpty()||situationMatrimoniale.isEmpty()){
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
        matriculeTxt.setText(null);
        prenomTxt.setText(null);
        nomTxt.setText(null);
        dateNaissanceTxt.setValue(null);
        lieuNaissanceTxt.setText(null);
        genreTxt.setText(null);
        nationaliteTxt.setText(null);
        adresseTxt.setText(null);
        emailTxt.setText(null);
        telephoneTxt.setText(null);
        groupeSanguinTxt.setText(null);
        situationMatrimonialeTxt.setText(null);
        
        
    }

    private void getQuery() {
        
        if(update == false){
            query = "INSERT INTO `etudiant`(`matricule`, `prenom`, `nom`, `dateNaissance`, `lieuNaissance`, `genre`, `nationalite`, `adresse`, `email`, `telephone`, `groupeSanguin`, `situationMatrimoniale`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        }else{
            query ="UPDATE `etudiant` SET "
                    + "`matricule`=?,"
                    + "`prenom`=?,"
                    + "`nom`=?,"
                    + "`dateNaissance`=?,"
                    + "`lieuNaissance`=?,"
                    + "`genre`=?,"
                    + "`nationalite`=?,"
                    + "`adresse`=?,"
                    + "`email`=?,"
                    + "`telephone`=?,"
                    + "`groupeSanguin`=?,"
                    + "`situationMatrimoniale`=? WHERE id= '"+etudiantId+"'";
        }
    }
        

    private void insert() {
        
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, matriculeTxt.getText());
            preparedStatement.setString(2, prenomTxt.getText());
            preparedStatement.setString(3, nomTxt.getText());
            preparedStatement.setString(4, String.valueOf(dateNaissanceTxt.getValue()));
            preparedStatement.setString(5, lieuNaissanceTxt.getText());
            preparedStatement.setString(6, genreTxt.getText());
            preparedStatement.setString(7, nationaliteTxt.getText());
            preparedStatement.setString(8, adresseTxt.getText());
            preparedStatement.setString(9, emailTxt.getText());
            preparedStatement.setString(10, telephoneTxt.getText());
            preparedStatement.setString(11, groupeSanguinTxt.getText());
            preparedStatement.setString(12, situationMatrimonialeTxt.getText());
            preparedStatement.execute();
        }catch(SQLException ex){
                        Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }

    void setUpdate(boolean b) {
         this.update = b;
    }

    void setTextField(int id, String matricule, String prenom, String nom, LocalDate toLocalDate, String lieuNaissance, String genre, String nationalite, String adresse, String email, String telephone, String groupeSanguin, String situationMatrimoniale) {
        etudiantId = id;
        matriculeTxt.setText(matricule);
        prenomTxt.setText(prenom);
        nomTxt.setText(nom);
        dateNaissanceTxt.setValue(toLocalDate);
        lieuNaissanceTxt.setText(lieuNaissance);
        genreTxt.setText(genre);
        nationaliteTxt.setText(nationalite);
        adresseTxt.setText(adresse);
        emailTxt.setText(email);
        telephoneTxt.setText(telephone);
        groupeSanguinTxt.setText(groupeSanguin);
        situationMatrimonialeTxt.setText(situationMatrimoniale);
        
    }
    
}
