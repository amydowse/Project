/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bases;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
/**
 *
 * @author amydo
 */
public class StartScreenDocumentController implements Initializable
{
    public static Pane ContentPane;
    @FXML Pane Content;
    
    @FXML private Button btnDiary = new Button();
    @FXML private Button btnStaff = new Button();
    @FXML private Button btnRegular = new Button();
    @FXML private Button btnProcedure = new Button();
    @FXML private Button btnTemplates = new Button();
    @FXML private Button btnPatientSearch = new Button();
    @FXML private Button btnProcedureSearch = new Button();
    @FXML private Button btnExtra = new Button();
    @FXML private ImageView imgLogo = new ImageView();
    
    public static String selection;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ContentPane = this.Content;
       
        //http://www.java2s.com/Code/Java/JavaFX/LoadajpgimagewithImageanduseImageViewtodisplay.htm - accessed 15/1/18
        Image image1 = new Image(Start.class.getResourceAsStream("/bases/logo.jpg"));
        imgLogo.setImage(image1);

      
    }
    
    //OK
    @FXML
    public void showDiaryMain() throws IOException
    {
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/bases/mainScreen.fxml"));
        changeContentPane(newLoadedPane);
    }

    
    
    
    
    @FXML
    public void load() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/bases/topMenuScreen.fxml"));
        Scene scene = new Scene(root);
        bases.Start.stage.setScene(scene);        
    }
    
    
    
    public void showStaffMain() throws IOException
    {
        selection = "Staff";
        load();
    }
   
    
    @FXML
    public void showRegularMain() throws IOException
    {
        selection = "Regular";
        load();
    }
    
    @FXML
    public void showProcedureMain() throws IOException
    {
        selection = "Procedures";
        load();
    }


    @FXML
    public void showSearchMain() throws IOException
    {
        selection = "Search Patient";
        load();
    }
    
    @FXML
    public void showSearchProcedureMain() throws IOException
    {
        selection = "Search Procedure";
        load();
    }


    @FXML
    public void showSettingsMain() throws IOException
    {
        selection = "Settings";
        load();
    }
    
    
    public static void changeContentPane(Pane newContent)
    {
        ContentPane.getChildren().clear();
        ContentPane.getChildren().add(newContent);
    }
    
    
}
