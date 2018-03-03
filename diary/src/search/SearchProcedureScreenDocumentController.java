/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import common.HelpDialogController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author amydo
 */
public class SearchProcedureScreenDocumentController implements Initializable
{
    @FXML RadioButton rb1W = new RadioButton();
    @FXML RadioButton rb2W = new RadioButton();
    @FXML RadioButton rb3W = new RadioButton();
    @FXML RadioButton rb4W = new RadioButton();
    
    @FXML ChoiceBox cmbSearchProcedure = new ChoiceBox();
    @FXML Button btnSearch = new Button();
    @FXML Label lblSearchCriteria = new Label();
    
    @FXML TableView tblSearchResult = new TableView();
    @FXML TableColumn tblColDate = new TableColumn();
    @FXML TableColumn tblColTime = new TableColumn();
    @FXML TableColumn tblColBook = new TableColumn();
    
    @FXML Hyperlink hlHlep = new Hyperlink();
    
    ObservableList searchResult = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ToggleGroup group = new ToggleGroup();
        rb1W.setToggleGroup(group);
        rb2W.setToggleGroup(group);
        rb3W.setToggleGroup(group);
        rb4W.setToggleGroup(group);
    }
    
    private HelpDialogController HDC;
    private Pane Hx;
    
    @FXML
    public void help()
    {
        try 
        {    
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Help");
            
            FXMLLoader DL = new FXMLLoader(getClass().getResource("/common/HelpDialog.fxml"));   
            
            Hx = DL.load(); //ISSUE
            HDC = DL.getController();
            
            HDC.show("ProcedureSearch");
            
            final Scene scene = new Scene(Hx, 795, 876);
            stage.setScene(scene);
            stage.setOnHidden(e -> HDC.shutdown());
            stage.show();
                      
        } 
        catch (IOException ex) 
        {
            //Logger.getLogger(ProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ISSUE IN MAIN");
        }
    }
    
}
