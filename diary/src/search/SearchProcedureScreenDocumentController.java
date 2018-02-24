/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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
    
}
