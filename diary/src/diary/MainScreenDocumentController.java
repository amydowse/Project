/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
/**
 *
 * @author amydo
 */
public class MainScreenDocumentController implements Initializable
{
    public static Pane ContentPane;
    @FXML Pane Content;
    
    @FXML private Button btnHome = new Button();
    @FXML private Button btnCalandar = new Button();
    @FXML private Button btnLeft = new Button();
    @FXML private Button btnRight = new Button();
    @FXML private Label lblDate = new Label();
    
    @FXML private Button btnDiary = new Button();
    @FXML private Button btnBlood = new Button();
    @FXML private Button btnPreOp = new Button();
    @FXML private Button btnOncology = new Button();
    @FXML private Button btnNonBed = new Button();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ContentPane = this.Content;
        try 
        {
            Pane newLoadedPane = FXMLLoader.load(getClass().getResource("diaryScreen.fxml"));
            changeContentPane(newLoadedPane); 
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(MainScreenDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    public void showDiary() throws IOException
    {
       Pane newLoadedPane = FXMLLoader.load(getClass().getResource("diaryScreen.fxml"));
       changeContentPane(newLoadedPane);    
    }
    
    @FXML
    public void showBlood() throws IOException
    {
       Pane newLoadedPane = FXMLLoader.load(getClass().getResource("bloodScreen.fxml"));
       changeContentPane(newLoadedPane);
        
    }
    
    @FXML
    public void showPreop() throws IOException
    {
       Pane newLoadedPane = FXMLLoader.load(getClass().getResource("preopScreen.fxml"));
       changeContentPane(newLoadedPane);
        
    }
    
    @FXML
    public void showOncology() throws IOException
    {
       Pane newLoadedPane = FXMLLoader.load(getClass().getResource("oncologyScreen.fxml"));
       changeContentPane(newLoadedPane);
        
    }
    
    @FXML
    public void showNonbed() throws IOException
    {
       Pane newLoadedPane = FXMLLoader.load(getClass().getResource("nonbedScreen.fxml"));
       changeContentPane(newLoadedPane);
        
    }
    
  
    @FXML
    public void Home() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("startScreen.fxml"));
        Scene scene = new Scene(root);
        diary.Diary.stage.setScene(scene);
        
    }
    
    
    public static void changeContentPane(Pane newContent)
    {
        ContentPane.getChildren().clear();
        ContentPane.getChildren().add(newContent);
    }

    
    
}
