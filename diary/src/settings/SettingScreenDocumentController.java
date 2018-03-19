/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settings;

import static bases.MainScreenDocumentController.ContentPane;
import common.DatabaseConnector;
import common.HelpDialogController;
import common.codeBank;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
/**
 *
 * @author amydo
 */
public class SettingScreenDocumentController implements Initializable
{
    @FXML Hyperlink hlHlep = new Hyperlink();
    
    public static AlterPaneDocumentController APDC;
    public static ExtraPaneDocumentController EPDC;
    public static TemplatePaneDocumentController TPDC;
    
    private Pane paneAlter;
    private Pane paneExtra;
    private Pane paneTemplate;
    
    
    
    public static Pane ContentAlter;
    public static Pane ContentExtra;
    public static Pane ContentTemplate; 
    
    @FXML Pane paneAlterContainer;
    @FXML Pane paneExtraContainer;
    @FXML Pane paneTemplateContainer;
    
    
       
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        try 
        {
            FXMLLoader PAL = new FXMLLoader(getClass().getResource("/settings/alterPane.fxml"));
            paneAlter = PAL.load();
            APDC = PAL.getController();
            
            
            FXMLLoader PEL = new FXMLLoader(getClass().getResource("/settings/extraPane.fxml"));
            paneExtra = PEL.load();
            EPDC = PEL.getController();
            
            FXMLLoader PTL = new FXMLLoader(getClass().getResource("/settings/templatePane.fxml"));
            paneTemplate = PTL.load();
            TPDC = PTL.getController(); 
        } 
        catch (IOException ex) 
        {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            Logger.getLogger(SettingScreenDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ContentAlter = this.paneAlterContainer;
        ContentExtra = this.paneExtraContainer;
        ContentTemplate = this.paneTemplateContainer;
        
        paneAlterContainer.getChildren().add(paneAlter);
        paneExtraContainer.getChildren().add(paneExtra);
        paneTemplateContainer.getChildren().add(paneTemplate);
                
        extraInactive();
        templateInactive();
        alterInactive();
      
    }
    
    
    
    //Getting the screens to show when clicked on --------------------------------------------------------------------------------------
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
    
    
    
    @FXML 
    public void helpExtra()
    {
        String toShow = "SettingsExtra";
        help(toShow);
    }
    
    @FXML 
    public void helpAlter()
    {
        String toShow = "SettingsAlter";
        help(toShow);
    }
    
    @FXML 
    public void helpTemplate()
    {
        String toShow = "SettingsTemplate";
        help(toShow);
    }
    
    
    private HelpDialogController HDC;
    private Pane Hx;
    
    
    public void help(String toShow)
    {
        try 
        {    
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Help");
            
            FXMLLoader DL = new FXMLLoader(getClass().getResource("/common/HelpDialog.fxml"));   
            
            Hx = DL.load(); //ISSUE
            HDC = DL.getController();
            
            HDC.show(toShow);
            
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
    
    
    
}//end of class