/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bases;

import static bases.MainScreenDocumentController.changeContentPane;
import static bases.StartScreenDocumentController.ContentPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import procedure.ProcedureScreenDocumentController;
import regular.RegularScreenDocumentController;
import staff.StaffScreenDocumentController;

/**
 *
 * @author amydo
 */
public class TopMenuDocumentController implements Initializable
{
    public static Pane ContentPane;
    @FXML Pane Content;
    
    @FXML Label lblHeading = new Label();
    
    public static ProcedureScreenDocumentController PrSDC;
    public static RegularScreenDocumentController RSDC;
    public static StaffScreenDocumentController SSDC;
    
    private Pane ProcedurePane;
    private Pane RegularPane;
    private Pane StaffPane;
    
    
    @FXML Button btnHome = new Button();
        
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        //Image from http://www.stickpng.com/img/icons-logos-emojis/home-icons/chimney-home-icon
        Image image = new Image(getClass().getResourceAsStream("/bases/home.png"));
        ImageView IV = new ImageView(image);
        IV.setFitHeight(53);
        IV.setFitWidth(60);
        btnHome.setGraphic(IV);
       
        
        
        ContentPane = this.Content;
        Pane newLoadedPane;
        try 
        {
            FXMLLoader PrSL = new FXMLLoader(getClass().getResource("/procedure/procedureScreen.fxml"));            
            ProcedurePane = PrSL.load();
            PrSDC = PrSL.getController();
            
            FXMLLoader RSL = new FXMLLoader(getClass().getResource("/regular/regularScreen.fxml"));            
            RegularPane = RSL.load();
            RSDC = RSL.getController();
            
            FXMLLoader SSL = new FXMLLoader(getClass().getResource("/staff/staffScreen.fxml"));            
            StaffPane = SSL.load();
            SSDC = SSL.getController();
            
            String selection = bases.StartScreenDocumentController.selection;
     
            switch(selection)
            {
                case "Staff": 
                    changeContentPaneNewLoad(StaffPane);
                    lblHeading.setText("STAFF");
                    break;
                case "Regular": 
                    changeContentPaneNewLoad(RegularPane); 
                    lblHeading.setText("REGULAR ATTENDERS");
                    break;
                case "Procedures": 
                    changeContentPaneNewLoad(ProcedurePane);
                    lblHeading.setText("PROCEDURES");
                    break;
                case "Search Patient": 
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/search/searchScreen.fxml"));
                    changeContentPaneNewLoad(newLoadedPane);  
                    lblHeading.setText("SEARCH PATIENT");
                    break;
                case "Search Procedure": 
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/search/searchProcedureScreen.fxml"));
                    changeContentPaneNewLoad(newLoadedPane);  
                    lblHeading.setText("SEARCH PROCEDURE");
                    break;
                case "Settings": 
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/settings/settingScreen.fxml"));
                    changeContentPaneNewLoad(newLoadedPane);   
                    lblHeading.setText("SETTINGS");
                    break;
            }
            
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(TopMenuDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    @FXML
    public void Home() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("startScreen.fxml"));
        Scene scene = new Scene(root);
        bases.Start.stage.setScene(scene);
        
    }
    
    public static void changeContentPaneNewLoad(Pane newContent)
    {
        
        ContentPane.getChildren().add(newContent);
    }
 

}
