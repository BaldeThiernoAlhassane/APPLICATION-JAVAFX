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
import models.Groupe;




public class TableView2Controller implements Initializable {

    @FXML
    private TableView<Groupe> tableGroupes;

    @FXML
    private TableColumn<Groupe, Integer> idCol;

    @FXML
    private TableColumn<Groupe, String> nomGroupeCol;

    @FXML
    private TableColumn<Groupe, String> dateCreationCol;
    
    @FXML
    private TableColumn<Groupe, String> editerCol;
    
    @FXML
    private TextField rechercheTxt;
    
    @FXML
    private Button btnQuitter;
    
      
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Groupe groupe = null;
    
    ObservableList<Groupe> ListGroupe = FXCollections.observableArrayList();

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
            Parent parent = FXMLLoader.load(getClass().getResource("/tableView/addGroupe.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TableView2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    @FXML
    private void refreshTable() {
        
        try {
            ListGroupe.clear();
            
            query = "SELECT * FROM `groupe`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                ListGroupe.add(new Groupe(
                            resultSet.getInt("id"),
                            resultSet.getString("nomGroupe"),
                            resultSet.getDate("dateCreation") ));
                tableGroupes.setItems(ListGroupe);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableView2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    @FXML
    private void print(MouseEvent event) {

    }

    private void loadDate() {
        
        connection = DbConnect.getConnect();
        refreshTable();
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomGroupeCol.setCellValueFactory(new PropertyValueFactory<>("nomGroupe"));
        dateCreationCol.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        
        
        //add cell of button edit 
         Callback<TableColumn<Groupe, String>, TableCell<Groupe, String>> cellFoctory = (TableColumn<Groupe, String> param) -> {
            // make cell containing buttons
            final TableCell<Groupe, String> cell = new TableCell<Groupe, String>() {
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
                                groupe = tableGroupes.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `groupe` WHERE id  ="+groupe.getId();
                                connection = DbConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(TableView2Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            groupe = tableGroupes.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/tableView/addGroupe.fxml"));
                            try {
                                
                                loader.load();
                                
                            } catch (IOException ex) {
                                Logger.getLogger(TableView2Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddGroupeController addGroupeController = loader.getController();
                            addGroupeController.setUpdate(true);
                            addGroupeController.setTextField(groupe.getId(), groupe.getNomGroupe(),groupe.getDateCreation().toLocalDate());
                            
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
         tableGroupes.setItems(ListGroupe);
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

      
            FilteredList<Groupe> filteredData = new FilteredList<>(ListGroupe, e->true);
          @FXML
   private void handleButtonClicks(MouseEvent mouseEvent) {
   
        rechercheTxt.textProperty().addListener((observable, oldValue, newValue)->{
            filteredData.setPredicate(groupe->{
           
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(groupe.getNomGroupe().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                    return true;
                }
                return false;    
            });
        });
         SortedList<Groupe> sortedData = new SortedList<>(filteredData);
         sortedData.comparatorProperty().bind(tableGroupes.comparatorProperty());
         tableGroupes.setItems(sortedData);
  

    
}}
    


   

    
