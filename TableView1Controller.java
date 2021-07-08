package Controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import helpers.DbConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.Etudiant;
import models.Professeur;

public class TableView1Controller implements Initializable {

    @FXML
    private TableView<Professeur> tableProfesseurs;

    @FXML
    private TableColumn<Professeur, Integer> idCol;

    @FXML
    private TableColumn<Professeur, String> matriculeCol;

    @FXML
    private TableColumn<Professeur, String> prenomCol;

    @FXML
    private TableColumn<Professeur, String> nomCol;

    @FXML
    private TableColumn<Professeur, String> date_NaissCol;

    @FXML
    private TableColumn<Professeur, String> lieu_NaissCol;

    @FXML
    private TableColumn<Professeur, String> genreCol;

    @FXML
    private TableColumn<Professeur, String> nationaliteCol;

    @FXML
    private TableColumn<Professeur, String> adresseCol;

    @FXML
    private TableColumn<Professeur, String> emailCol;

    @FXML
    private TableColumn<Professeur, String> telephoneCol;

    @FXML
    private TableColumn<Professeur, String> specialiteCol;

    @FXML
    private TableColumn<Professeur, String> situa_MatrimCol;

    @FXML
    private TableColumn<Professeur, String> editerCol;
    
    @FXML
    private TextField rechercheTxt;
    
    @FXML
    private Button btnQuitter;
    
      
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Professeur professeur = null;
    
    ObservableList<Professeur> ListProfesseur = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDate();
        
    }
    
    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    @FXML
    private void getAddView(MouseEvent event) {
        
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/tableView/addProfesseur.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TableView1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    @FXML
    private void refreshTable() {
        
        try {
            ListProfesseur.clear();
            
            query = "SELECT * FROM `professeur`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                ListProfesseur.add(new Professeur(
                            resultSet.getInt("id"),
                            resultSet.getString("matricule"),
                            resultSet.getString("prenom"),
                            resultSet.getString("nom"),
                            resultSet.getDate("dateNaissance"),
                            resultSet.getString("lieuNaissance"),
                            resultSet.getString("genre"),
                            resultSet.getString("nationalite"),
                            resultSet.getString("adresse"),
                            resultSet.getString("email"),
                            resultSet.getString("telephone"),
                            resultSet.getString("specialite"),
                            resultSet.getString("situationMatrimoniale") ));
                tableProfesseurs.setItems(ListProfesseur);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableView1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    @FXML
    private void print(MouseEvent event) {

    }

    private void loadDate() {
        
        connection = DbConnect.getConnect();
        refreshTable();
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        matriculeCol.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        date_NaissCol.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        lieu_NaissCol.setCellValueFactory(new PropertyValueFactory<>("lieuNaissance"));
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        nationaliteCol.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        telephoneCol.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        specialiteCol.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        situa_MatrimCol.setCellValueFactory(new PropertyValueFactory<>("situationMatrimoniale"));
        
        
        //add cell of button edit 
         Callback<TableColumn<Professeur, String>, TableCell<Professeur, String>> cellFoctory = (TableColumn<Professeur, String> param) -> {
            // make cell containing buttons
            final TableCell<Professeur, String> cell = new TableCell<Professeur, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                professeur = tableProfesseurs.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `professeur` WHERE id  ="+professeur.getId();
                                connection = DbConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(TableView1Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            professeur = tableProfesseurs.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/tableView/addProfesseur.fxml"));
                            try {
                                
                                loader.load();
                                
                            } catch (IOException ex) {
                                Logger.getLogger(TableView1Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddProfesseurController addProfesseurController = loader.getController();
                            addProfesseurController.setUpdate(true);
                            addProfesseurController.setTextField(professeur.getId(), professeur.getMatricule(),professeur.getPrenom(), 
                                    professeur.getNom(),professeur.getDateNaissance().toLocalDate(),professeur.getLieuNaissance(), 
                                    professeur.getGenre(),professeur.getNationalite(),professeur.getAdresse(),professeur.getEmail(),
                                    professeur.getTelephone(),professeur.getSpecialite(),professeur.getSituationMatrimoniale());
                            
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            

                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }


            };

            return cell;
        };
         editerCol.setCellFactory(cellFoctory);
         tableProfesseurs.setItems(ListProfesseur);
    }

    
     @FXML
    private void handleButtonClicks(ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == btnQuitter) {
            loadStage("/tableView/accueil.fxml");}
    }

   
    
      private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
      
      
       
           FilteredList<Professeur> filteredData = new FilteredList<>(ListProfesseur, e->true);
          @FXML
   private void handleButtonClicks(MouseEvent mouseEvent) {
   
        rechercheTxt.textProperty().addListener((observable, oldValue, newValue)->{
            filteredData.setPredicate(professeur->{
           
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(professeur.getEmail().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                    return true;
                }
                return false;    
            });
        });
         SortedList<Professeur> sortedData = new SortedList<>(filteredData);
         sortedData.comparatorProperty().bind(tableProfesseurs.comparatorProperty());
         tableProfesseurs.setItems(sortedData);
  

    
}}

   

    
