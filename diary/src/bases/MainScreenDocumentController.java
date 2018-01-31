/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bases;

import common.codeBank;
import diary.DiaryScreenDocumentController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
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
    @FXML private Button btnLeft = new Button();
    @FXML private Button btnRight = new Button();
    @FXML private Label lblDate = new Label();
    
    @FXML private Button btnDiary = new Button();
    @FXML private Button btnBlood = new Button();
    @FXML private Button btnPreOp = new Button();
    @FXML private Button btnOncology = new Button();
    @FXML private Button btnNonBed = new Button();
        
    @FXML private DatePicker dpCalandar = new DatePicker();
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ContentPane = this.Content;
        try 
        {
            updateDate();
            showDiary();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(MainScreenDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateDate()
    {
        DateTimeFormatter Stringformatter = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy");
        String stringDate = codeBank.getCurrentDate().format(Stringformatter);
        lblDate.setText(stringDate);        
    }
    
    @FXML
    public void today()
    {
        //todays date into a localDate format 
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate Today = LocalDate.now();
        codeBank.setCurrentDate(Today);
        updateDate();
        
        //showing todays information 
        //codeBank.x.showInformation(currentDate);
    
    }
    
    
    
    @FXML 
    public void plusOneDay()
    {
        codeBank.setCurrentDate(codeBank.getCurrentDate().plusDays(1));
        updateDate();
        DiaryScreenDocumentController x = new DiaryScreenDocumentController();
        x.showInformation(codeBank.getCurrentDate());
    }
    
    @FXML 
    public void minusOneDay()
    {
        codeBank.setCurrentDate(codeBank.getCurrentDate().minusDays(1));
        updateDate();
        DiaryScreenDocumentController x = new DiaryScreenDocumentController();
        x.showInformation(codeBank.getCurrentDate());
    }
    
    
    @FXML
    public void showDiary() throws IOException
    {
       Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/diary/diaryScreen.fxml"));
       changeContentPane(newLoadedPane);    
    }
    
    @FXML
    public void showBlood() throws IOException
    {
       Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/blood/bloodScreen.fxml"));
       changeContentPane(newLoadedPane);
        
    }
    
    @FXML
    public void showPreop() throws IOException
    {
       Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/preop/preopScreen.fxml"));
       changeContentPane(newLoadedPane);
        
    }
    
    @FXML
    public void showOncology() throws IOException
    {
       Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/oncology/oncologyScreen.fxml"));
       changeContentPane(newLoadedPane);
        
    }
    
    @FXML
    public void showNonbed() throws IOException
    {
       Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/nonbed/nonbedScreen.fxml"));
       changeContentPane(newLoadedPane);
        
    }
    
  
    @FXML
    public void Home() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/bases/startScreen.fxml"));
        Scene scene = new Scene(root);
        bases.Start.stage.setScene(scene);
        
    }
    
    
    public static void changeContentPane(Pane newContent)
    {
        ContentPane.getChildren().clear();
        ContentPane.getChildren().add(newContent);
    }


    
    
}
