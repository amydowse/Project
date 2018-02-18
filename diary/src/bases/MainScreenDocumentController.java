/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bases;

import blood.BloodScreenDocumentController;
import common.DatabaseConnector;
import common.codeBank;
import diary.DiaryScreenDocumentController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import nonbed.NonbedScreenDocumentController;
import oncology.OncologyScreenDocumentController;
import preop.PreopScreenDocumentController;
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
    
    public static DiaryScreenDocumentController DSDC;
    public static PreopScreenDocumentController PSDC;
    public static BloodScreenDocumentController BSDC;
    public static NonbedScreenDocumentController NSDC;
    public static OncologyScreenDocumentController OSDC;
    
    private Pane DiaryPane;
    private Pane PreopPane;
    private Pane BloodPane;
    private Pane NonbedPane;
    private Pane OncologyPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
              
        ContentPane = this.Content;
        try 
        {
            FXMLLoader DSL = new FXMLLoader(getClass().getResource("/diary/diaryScreen.fxml"));            
            DiaryPane = DSL.load();
            DSDC = DSL.getController();
            
            FXMLLoader PSL = new FXMLLoader(getClass().getResource("/preop/preopScreen.fxml"));
            PreopPane = PSL.load();
            PSDC = PSL.getController();
            
            FXMLLoader BSL = new FXMLLoader(getClass().getResource("/blood/bloodScreen.fxml"));
            BloodPane = BSL.load();
            BSDC = BSL.getController();
            
            FXMLLoader NSL = new FXMLLoader(getClass().getResource("/nonbed/nonbedScreen.fxml"));
            NonbedPane = NSL.load();
            NSDC = NSL.getController();
            
            FXMLLoader OSL = new FXMLLoader(getClass().getResource("/oncology/oncologyScreen.fxml"));
            OncologyPane = OSL.load();
            OSDC = OSL.getController();
           
            codeBank.setCurrentDate(LocalDate.now());
            
            updateDate();
            showDiary();
            updateButtons();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(MainScreenDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateButtons()
    {
        updateDiaryButton();
        updateBloodButton();
        updatePreopButton();
        updateOncologyButton();
        updateNonBedButton();
    }
    
    public void updateDiaryButton()
    {
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();          
            
            //implement query
            String date = codeBank.dateToString(codeBank.getCurrentDate());
            rs = stmt.executeQuery("SELECT count(*) AS total FROM diary WHERE Date ='" + date + "'"); 
                
            while(rs.next())
            { 
                btnDiary.setText("Diary\n\n"+rs.getInt("total") + " booked");
            }
            c.close();
        }
        catch (SQLException e)
        {
            
        } 
    }
    
    public void updateBloodButton()
    {
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();          
            
            //implement query
            String date = codeBank.dateToString(codeBank.getCurrentDate());
            rs = stmt.executeQuery("SELECT count(*) AS total FROM blood WHERE Date ='" + date + "'"); 
                
            while(rs.next())
            { 
                btnBlood.setText("Blood Clinic\n\n"+rs.getInt("total") + " booked");
            }
            c.close();
        }
        catch (SQLException e)
        {
            
        } 
    }
    
    public void updatePreopButton()
    {
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();          
            
            //implement query
            String date = codeBank.dateToString(codeBank.getCurrentDate());
            rs = stmt.executeQuery("SELECT count(*) AS total FROM preop WHERE Date ='" + date + "'"); 
                
            while(rs.next())
            { 
                btnPreOp.setText("Preop\n\n"+rs.getInt("total") + " booked");
            }
            c.close();
        }
        catch (SQLException e)
        {
            
        } 
    }
    
    public void updateOncologyButton()
    {
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();          
            
            //implement query
            String date = codeBank.dateToString(codeBank.getCurrentDate());
            rs = stmt.executeQuery("SELECT count(*) AS total FROM oncology WHERE Date ='" + date + "'"); 
                
            while(rs.next())
            { 
                btnOncology.setText("Oncology\n\n"+rs.getInt("total") + " booked");
            }
            c.close();
        }
        catch (SQLException e)
        {
            
        } 
    }
    
    public void updateNonBedButton()
    {
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();          
            
            //implement query
            String date = codeBank.dateToString(codeBank.getCurrentDate());
            rs = stmt.executeQuery("SELECT count(*) AS total FROM nonbed WHERE Date ='" + date + "'"); 
                
            while(rs.next())
            { 
                btnNonBed.setText("Non-bed\n\n"+rs.getInt("total") + " booked");
            }
            c.close();
        }
        catch (SQLException e)
        {
            
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
        DSDC.save(codeBank.getCurrentDate());
        PSDC.save(codeBank.getCurrentDate());
        BSDC.save();
        NSDC.save(codeBank.getCurrentDate());
        OSDC.save(codeBank.getCurrentDate());
        
        //todays date into a localDate format 
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate Today = LocalDate.now();
        codeBank.setCurrentDate(Today);
        
        updateDate();
        updateButtons();
        
        DSDC.showInformation(codeBank.getCurrentDate());
        DSDC.showStaff(codeBank.getCurrentDate());
        DSDC.showNotes(codeBank.getCurrentDate());
        PSDC.loadInformation();
        BSDC.showInformation();
        NSDC.showInformation(codeBank.getCurrentDate());
        OSDC.showInformation(codeBank.getCurrentDate());
    }
    
    @FXML 
    public void plusOneDay()
    {
        DSDC.save(codeBank.getCurrentDate());
        PSDC.save(codeBank.getCurrentDate());
        BSDC.save();
        NSDC.save(codeBank.getCurrentDate());
        OSDC.save(codeBank.getCurrentDate());
        
        codeBank.setCurrentDate(codeBank.getCurrentDate().plusDays(1));
        updateDate();
        updateButtons();
        
        DSDC.showInformation(codeBank.getCurrentDate());
        DSDC.showStaff(codeBank.getCurrentDate());
        DSDC.showNotes(codeBank.getCurrentDate());
        PSDC.loadInformation();
        BSDC.showInformation();
        NSDC.showInformation(codeBank.getCurrentDate());
        OSDC.showInformation(codeBank.getCurrentDate());
    }
    
    @FXML 
    public void minusOneDay()
    {
        DSDC.save(codeBank.getCurrentDate());
        PSDC.save(codeBank.getCurrentDate());
        BSDC.save();
        NSDC.save(codeBank.getCurrentDate());
        OSDC.save(codeBank.getCurrentDate());
        
        codeBank.setCurrentDate(codeBank.getCurrentDate().minusDays(1));
        updateDate();
        updateButtons();
        
        DSDC.showInformation(codeBank.getCurrentDate());
        DSDC.showStaff(codeBank.getCurrentDate());
        DSDC.showNotes(codeBank.getCurrentDate());
        PSDC.loadInformation();
        BSDC.showInformation();
        NSDC.showInformation(codeBank.getCurrentDate());
        OSDC.showInformation(codeBank.getCurrentDate());
    }
    
    @FXML
    //method for date picker 
    public void selectedDate()
    {
        DSDC.save(codeBank.getCurrentDate());
        PSDC.save(codeBank.getCurrentDate());
        BSDC.save();
        NSDC.save(codeBank.getCurrentDate());
        OSDC.save(codeBank.getCurrentDate());
        
        codeBank.setCurrentDate(dpCalandar.getValue());
        updateDate();
        updateButtons();
        
        DSDC.showInformation(codeBank.getCurrentDate());
        DSDC.showStaff(codeBank.getCurrentDate());
        DSDC.showNotes(codeBank.getCurrentDate());
        PSDC.loadInformation();
        BSDC.showInformation();
        NSDC.showInformation(codeBank.getCurrentDate());
        OSDC.showInformation(codeBank.getCurrentDate());
    }
    
    
    
    
    
    @FXML
    public void showDiary() throws IOException
    {
       //Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/diary/diaryScreen.fxml"));
        DSDC.save(codeBank.getCurrentDate());
        PSDC.save(codeBank.getCurrentDate());
        BSDC.save();
        NSDC.save(codeBank.getCurrentDate());
        OSDC.save(codeBank.getCurrentDate());
        updateButtons();
        changeContentPane(DiaryPane);    
        
    }
   
    @FXML
    public void showBlood() throws IOException
    {
       DSDC.save(codeBank.getCurrentDate());
       PSDC.save(codeBank.getCurrentDate());
       BSDC.save();
       NSDC.save(codeBank.getCurrentDate());
       OSDC.save(codeBank.getCurrentDate());
       updateButtons();
       changeContentPane(BloodPane); 
        
    }
    
    @FXML
    public void showPreop() throws IOException
    {
        DSDC.save(codeBank.getCurrentDate());
        PSDC.save(codeBank.getCurrentDate());
        BSDC.save();
        NSDC.save(codeBank.getCurrentDate());
        OSDC.save(codeBank.getCurrentDate());
        updateButtons();
        changeContentPane(PreopPane); 
        
    }
    
    @FXML
    public void showOncology() throws IOException
    {
       DSDC.save(codeBank.getCurrentDate());
       PSDC.save(codeBank.getCurrentDate());
       BSDC.save();
       NSDC.save(codeBank.getCurrentDate());
       OSDC.save(codeBank.getCurrentDate());
       updateButtons();
       changeContentPane(OncologyPane);
        
    }
    
    @FXML
    public void showNonbed() throws IOException
    {
       DSDC.save(codeBank.getCurrentDate());
       PSDC.save(codeBank.getCurrentDate());
       BSDC.save();
       NSDC.save(codeBank.getCurrentDate());
       OSDC.save(codeBank.getCurrentDate());
       updateButtons();
       changeContentPane(NonbedPane);
        
    }
    
  
    @FXML
    public void Home() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/bases/startScreen.fxml"));
        DSDC.save(codeBank.getCurrentDate());
        PSDC.save(codeBank.getCurrentDate());
        BSDC.save();
        NSDC.save(codeBank.getCurrentDate());
        OSDC.save(codeBank.getCurrentDate());
        codeBank.setCurrentDate(LocalDate.now());
        Scene scene = new Scene(root);
        bases.Start.stage.setScene(scene);
        
    }
    
    
    public static void changeContentPane(Pane newContent)
    {
        ContentPane.getChildren().clear();
        ContentPane.getChildren().add(newContent);
    }


    
    
}
