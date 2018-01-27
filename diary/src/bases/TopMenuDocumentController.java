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
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author amydo
 */
public class TopMenuDocumentController implements Initializable
{
    public static Pane ContentPane;
    @FXML Pane Content;
    
    @FXML Label lblHeading = new Label();
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        ContentPane = this.Content;
        Pane newLoadedPane;
        try 
        {
            String selection = bases.StartScreenDocumentController.selection;
     
            switch(selection)
            {
                case "Staff": 
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/staff/staffScreen.fxml"));
                    changeContentPaneNewLoad(newLoadedPane);
                    lblHeading.setText("STAFF");
                    break;
                case "Regular": 
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/regular/regularScreen.fxml"));
                    changeContentPaneNewLoad(newLoadedPane); 
                    lblHeading.setText("REGULAR ATTENDERS");
                    break;
                case "Procedures": 
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/procedure/procedureScreen.fxml"));
                    changeContentPaneNewLoad(newLoadedPane); 
                    lblHeading.setText("PROCEDURES");
                    break;
                case "Search": 
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/search/searchScreen.fxml"));
                    changeContentPaneNewLoad(newLoadedPane);  
                    lblHeading.setText("SEARCH");
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
