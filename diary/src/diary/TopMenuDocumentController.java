/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import static diary.MainScreenDocumentController.changeContentPane;
import static diary.StartScreenDocumentController.ContentPane;
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
            String selection = diary.StartScreenDocumentController.selection;
     
            switch(selection)
            {
                case "Staff": 
                    newLoadedPane = FXMLLoader.load(getClass().getResource("staffScreen.fxml"));
                    changeContentPaneNewLoad(newLoadedPane);
                    lblHeading.setText("STAFF");
                    break;
                case "Regular": 
                    newLoadedPane = FXMLLoader.load(getClass().getResource("regularScreen.fxml"));
                    changeContentPaneNewLoad(newLoadedPane); 
                    lblHeading.setText("REGULAR ATTENDERS");
                    break;
                case "Procedures": 
                    newLoadedPane = FXMLLoader.load(getClass().getResource("procedureScreen.fxml"));
                    changeContentPaneNewLoad(newLoadedPane); 
                    lblHeading.setText("PROCEDURES");
                    break;
                case "Search": 
                    newLoadedPane = FXMLLoader.load(getClass().getResource("searchScreen.fxml"));
                    changeContentPaneNewLoad(newLoadedPane);  
                    lblHeading.setText("SEARCH");
                    break;
                case "Settings": 
                    newLoadedPane = FXMLLoader.load(getClass().getResource("settingScreen.fxml"));
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
        diary.Diary.stage.setScene(scene);
        
    }
    
    public static void changeContentPaneNewLoad(Pane newContent)
    {
        
        ContentPane.getChildren().add(newContent);
    }
 

}
