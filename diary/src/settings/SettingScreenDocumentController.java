/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settings;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
/**
 *
 * @author amydo
 */
public class SettingScreenDocumentController implements Initializable
{
    //EXTRA LIST
    @FXML private Pane paneExtra = new Pane();
    @FXML private Pane paneExtraContainer = new Pane();
    @FXML private Label lblExtra = new Label();
    
    @FXML private Label lblExtraDate = new Label();
    @FXML private DatePicker extraListDate = new DatePicker();
    @FXML private Label lblExtraInstructions = new Label();
    @FXML private CheckBox chbSurgery = new CheckBox();
    @FXML private CheckBox chbBlood = new CheckBox();
    @FXML private CheckBox chbPreop = new CheckBox();
    @FXML private CheckBox chbOncology = new CheckBox();   
    @FXML private CheckBox chbNonbed = new CheckBox();
    @FXML private Pane paneBloodDetails = new Pane();
    @FXML private Button btnSaveExtra = new Button();
    
    //BLOOD CLINIC TEMPLATE
    @FXML private Pane paneTemplate = new Pane();
    @FXML private Label lblTemplate = new Label();   
    
    @FXML private CheckBox chbMonday = new CheckBox();
    @FXML private CheckBox chbTuesday = new CheckBox();       
    @FXML private CheckBox chbWednesday = new CheckBox();
    @FXML private CheckBox chbThursday = new CheckBox();
    @FXML private CheckBox chbFriday = new CheckBox();

    @FXML private Label lblTempStart = new Label();
    @FXML private Label lblTempEnd = new Label();
    @FXML private Label lblTempLength = new Label();
    @FXML private Label lblTempBreakStart = new Label();
    @FXML private Label lblTempBreakEnd = new Label();
    
    @FXML private TextField txtTempStart = new TextField();
    @FXML private TextField txtTempEnd = new TextField();
    @FXML private TextField txtTempLength = new TextField();
    @FXML private TextField txtTempBreakStart = new TextField();
    @FXML private TextField txtTempBreakEnd = new TextField();
    
    @FXML private Button btnSaveTemplate = new Button();
    
    //ALTER EXISTING BLOOD CLINIC
    @FXML private Pane paneAlter = new Pane();
    @FXML private Label lblAlter = new Label();
    
    @FXML private Label lblAlterInstructions = new Label();
    @FXML private DatePicker alterDate = new DatePicker();   

    @FXML private Label lblAlterStart = new Label();
    @FXML private Label lblAlterEnd = new Label();
    @FXML private Label lblAlterLength = new Label();
    @FXML private Label lblAlterBreakStart = new Label();
    @FXML private Label lblAlterBreakEnd = new Label();
    
    @FXML private TextField txtAlterStart = new TextField();
    @FXML private TextField txtAlterEnd = new TextField();
    @FXML private TextField txtAlterLength = new TextField();
    @FXML private TextField txtAlterBreakStart = new TextField();
    @FXML private TextField txtAlterBreakEnd = new TextField();
    
    @FXML private Button btnSaveAlter = new Button();
    
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        extraInactive();
        templateInactive();
        alterInactive();
        paneBloodDetails.setVisible(false);
    }
    
    public void extraClicked()
    {
        extraActive();
        templateInactive();
        alterInactive();
    }
    
    public void templateClicked()
    {
        extraInactive();
        templateActive();
        alterInactive();
    }
        
    public void alterClicked()
    {
        extraInactive();
        templateInactive();
        alterActive();
    }
    
    //https://o7planning.org/en/11101/javafx-label-tutorial#a3712123 accessed 18/1/18
    public void extraActive()
    {
        paneExtra.setDisable(false);
    }
    
    public void extraInactive()
    {
        paneExtra.setDisable(true);
                
    }
    
    public void templateActive()
    {
        paneTemplate.setDisable(false);
    }
    
    public void templateInactive()
    {
        paneTemplate.setDisable(true);
    }
    
    public void alterActive()
    {
        paneAlter.setDisable(false);
    }
    
    public void alterInactive()
    {
        paneAlter.setDisable(true);
    }
    
    public void showBlood()
    {
        if(chbBlood.isSelected())
        {
            paneBloodDetails.setVisible(true);
        }
        else
        {
            paneBloodDetails.setVisible(false);
        }
    }
}