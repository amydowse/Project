/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settings;

import common.DatabaseConnector;
import common.codeBank;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    @FXML private TextField txtExtraStart = new TextField();
    @FXML private TextField txtExtraEnd = new TextField();
    @FXML private TextField txtExtraLength = new TextField();
    @FXML private TextField txtExtraBreakStart = new TextField();
    @FXML private TextField txtExtraBreakEnd = new TextField();
    
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
    
    @FXML private Label lblNoClinic = new Label();

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
    
    
    ArrayList<String> week = new ArrayList<String>();
       
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        extraInactive();
        templateInactive();
        alterInactive();
        paneBloodDetails.setVisible(false);
        
        showTemplate();
        week.add("MONDAY");
        week.add("TUESDAY");
        week.add("WEDNESDAY");
        week.add("THURSDAY");
        week.add("FRIDAY");
        
        lblNoClinic.setVisible(false);
        
        showTemplate();
    }
    
    //>>>>>>>>>>>>> ADDING EXTRA CLINICS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private boolean initiallyBlood = false;
    @FXML
    public void selectedExtraDate()
    {
        clearAllExtra();
        
        LocalDate selected = extraListDate.getValue();
        String day = selected.getDayOfWeek().name();
        String date = codeBank.dateToString(selected);
        
        if(day.equals("MONDAY") || day.equals("TUESDAY") || day.equals("WEDNESDAY") || day.equals("THURSDAY") || day.equals("FRIDAY"))
        {
            chbSurgery.setSelected(true);
            chbSurgery.setDisable(true);
            
            chbPreop.setSelected(true);
            chbPreop.setDisable(true);
            
            chbOncology.setSelected(true); 
            chbOncology.setDisable(true);
            
            chbNonbed.setSelected(true);
            chbNonbed.setDisable(true);
        }
        else
        {
            
        }
        
        checkBlood(date, day);
    }
    
    
    
    public void checkBlood(String date, String day)
    {
        try
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);
            Statement stmt = c.createStatement();
            ResultSet rs;
            
            String sql = "SELECT * FROM template WHERE Day='" + date + "'";
            rs = stmt.executeQuery(sql);
            
            if(rs.next())
            {
                initiallyBlood = true;
                chbBlood.setSelected(true);
                paneBloodDetails.setVisible(true);
                txtExtraStart.setText(rs.getString("Start"));
                txtExtraEnd.setText(rs.getString("End"));
                txtExtraLength.setText(rs.getString("Duration"));
                txtExtraBreakStart.setText(rs.getString("BreakStart"));
                txtExtraBreakEnd.setText(rs.getString("BreakEnd"));
            }
            else
            {
                sql = "SELECT * FROM template WHERE Day='" + day + "'";
                rs = stmt.executeQuery(sql);
            
                if(rs.next())
                {
                    initiallyBlood = true;
                    chbBlood.setSelected(true);
                    paneBloodDetails.setVisible(true);
                    txtExtraStart.setText(rs.getString("Start"));
                    txtExtraEnd.setText(rs.getString("End"));
                    txtExtraLength.setText(rs.getString("Duration"));
                    txtExtraBreakStart.setText(rs.getString("BreakStart"));
                    txtExtraBreakEnd.setText(rs.getString("BreakEnd"));
                }
            }
        }
        catch(SQLException e)
        {
            
        }
    }
    
    public void clearAllExtra()
    {
        initiallyBlood = false;
        
        chbSurgery.setSelected(false);
        chbBlood.setSelected(false);
        chbPreop.setSelected(false);
        chbOncology.setSelected(false);  
        chbNonbed.setSelected(false);
        paneBloodDetails.setVisible(false);
        
        chbSurgery.setDisable(false);
        chbBlood.setDisable(false);
        chbPreop.setDisable(false);
        chbOncology.setDisable(false);  
        chbNonbed.setDisable(false);
        paneBloodDetails.setDisable(false);
        
        txtExtraStart.setText("");
        txtExtraEnd.setText("");
        txtExtraLength.setText("");
        txtExtraBreakStart.setText("");
        txtExtraBreakEnd.setText("");
    }
    
    
    public void saveExtra()
    {
        LocalDate selected = extraListDate.getValue();
        String day = selected.getDayOfWeek().name();
        String date = codeBank.dateToString(selected);
       
        if(day.equals("MONDAY") || day.equals("TUESDAY") || day.equals("WEDNESDAY") || day.equals("THURSDAY") || day.equals("FRIDAY"))
        {
            if(initiallyBlood == false && chbBlood.isSelected())
            {
                try
                {
                    Connection c = DatabaseConnector.activateConnection();
                    c.setAutoCommit(true);
                    Statement stmt = c.createStatement();

                    String sql = "REPLACE INTO template (Day, Start, End, Duration, BreakStart, BreakEnd) VALUES ('" 
                                                                                        + date + "','"
                                                                                        + txtExtraStart.getText() + "','"
                                                                                        + txtExtraEnd.getText() + "','"
                                                                                        + txtExtraLength.getText() + "','"
                                                                                        + txtExtraBreakStart.getText() + "','"
                                                                                        + txtExtraBreakEnd.getText() + "')";
                    
                    stmt.executeUpdate(sql);

                }
                catch(SQLException e)
                {
                    Logger.getLogger(SettingScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        else
        {
            int surgery = 0;
            int blood = 0;
            int preop = 0;
            int oncology = 0;
            int nonbed = 0;
            
            if(chbSurgery.isSelected())
            {
                surgery = 1;
            }
            
            if(chbBlood.isSelected())
            {
                blood = 1;
                addBlood();
            }
            
            if(chbPreop.isSelected())
            {
                preop = 1;
            }
            
            if(chbOncology.isSelected())
            {
                oncology = 1;
            }
            
            if(chbNonbed.isSelected())
            {
                nonbed = 1;
            }
            
            
            try
            {
                Connection c = DatabaseConnector.activateConnection();
                c.setAutoCommit(true);
                Statement stmt = c.createStatement();

                String sql = "REPLACE INTO extra (Date, Surgery, Blood, Preop, Oncology, Nonbed) VALUES ('" 
                                                                                        + date + "','"
                                                                                        + surgery + "','"
                                                                                        + blood + "','"
                                                                                        + preop + "','"
                                                                                        + oncology + "','"
                                                                                        + nonbed + "')";
                    
                stmt.executeUpdate(sql);
                c.close();

            }
            catch(SQLException e)
            {
                Logger.getLogger(SettingScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
            }
            
        }
        
    }
    
    public void addBlood()
    {
        LocalDate selected = extraListDate.getValue();
        String date = codeBank.dateToString(selected);
        try 
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);

            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();

            String sql = "REPLACE INTO template (Day, Start, End, Duration, BreakStart, BreakEnd) VALUES ('" 
                                                                                    + date + "','"
                                                                                    + txtExtraStart.getText() + "','"
                                                                                    + txtExtraEnd.getText() + "','"
                                                                                    + txtExtraLength.getText() + "','"
                                                                                    + txtExtraBreakStart.getText() + "','"
                                                                                    + txtExtraBreakEnd.getText() + "')";




            stmt.executeUpdate(sql);
           
            c.close();
        } 
        catch (SQLException e) 
        {
            Logger.getLogger(SettingScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    
    //>>>>>>>>>>>>> CHANGE SPECIFIC BLOOD CLINIC >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    @FXML
    public void selectedDate()
    {
        clearAllAlter();
        
        LocalDate selected = alterDate.getValue();
        
        String day = selected.getDayOfWeek().name();
        String date = codeBank.dateToString(selected);
        
        try
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);
            Statement stmt = c.createStatement();
            ResultSet rs;
            
            String sql = "SELECT * FROM template WHERE Day='" + date + "'";
            rs = stmt.executeQuery(sql);
            
            if(rs.next())
            {
                
                txtAlterStart.setText(rs.getString("Start"));
                txtAlterEnd.setText(rs.getString("End"));
                txtAlterLength.setText(rs.getString("Duration"));
                txtAlterBreakStart.setText(rs.getString("BreakStart"));
                txtAlterBreakEnd.setText(rs.getString("BreakEnd"));
            }
            else
            {
                sql = "SELECT * FROM template WHERE Day='" + day + "'";
                rs = stmt.executeQuery(sql);
            
                if(rs.next())
                {
                    txtAlterStart.setText(rs.getString("Start"));
                    txtAlterEnd.setText(rs.getString("End"));
                    txtAlterLength.setText(rs.getString("Duration"));
                    txtAlterBreakStart.setText(rs.getString("BreakStart"));
                    txtAlterBreakEnd.setText(rs.getString("BreakEnd"));
                }
                else
                {
                    lblNoClinic.setVisible(true);
                    txtAlterStart.setVisible(false);
                    txtAlterEnd.setVisible(false);
                    txtAlterLength.setVisible(false);
                    txtAlterBreakStart.setVisible(false);
                    txtAlterBreakEnd.setVisible(false);
                    
                }
            }
            c.close();
        }
        catch(SQLException e)
        {
            
        }
        
    }
    
    public void clearAllAlter()
    {
        lblNoClinic.setVisible(false);
        txtAlterStart.setVisible(true);
        txtAlterEnd.setVisible(true);
        txtAlterLength.setVisible(true);
        txtAlterBreakStart.setVisible(true);
        txtAlterBreakEnd.setVisible(true);
        txtAlterStart.setText("");
        txtAlterEnd.setText("");
        txtAlterLength.setText("");
        txtAlterBreakStart.setText("");
        txtAlterBreakEnd.setText("");
    }
    
    @FXML
    public void saveAlter()
    {
        LocalDate selected = alterDate.getValue();
        String date = codeBank.dateToString(selected);
        
        try 
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);

            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();

            String sql = "REPLACE INTO template (Day, Start, End, Duration, BreakStart, BreakEnd) VALUES ('" 
                                                                                    + date + "','"
                                                                                    + txtAlterStart.getText() + "','"
                                                                                    + txtAlterEnd.getText() + "','"
                                                                                    + txtAlterLength.getText() + "','"
                                                                                    + txtAlterBreakStart.getText() + "','"
                                                                                    + txtAlterBreakEnd.getText() + "')";




            stmt.executeUpdate(sql);
           

            c.close();
        } 
        catch (SQLException e) 
        {
            Logger.getLogger(SettingScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    
    
    
    
    
    
    //>>>>>>>>>>>>>> TEMPLATE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public void showTemplate()
    {
        try
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);
            Statement stmt = c.createStatement();
            ResultSet rs;
            
            String sql = "SELECT * FROM template";
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                
                String day = rs.getString("Day");
                String start = rs.getString("Start");
                String end = rs.getString("End");
                String duration = rs.getString("Duration");
                String breakStart = rs.getString("BreakStart");
                String breakEnd = rs.getString("BreakEnd");
                                
                for(int i=0; i<week.size(); i++)
                {
                    if(day.equals(week.get(i)))
                    {
                        tick(day);
                        txtTempStart.setText(start);
                        txtTempEnd.setText(end);
                        txtTempLength.setText(duration);
                        txtTempBreakStart.setText(breakStart);
                        txtTempBreakEnd.setText(breakEnd);
                    }
                }
            }
                
            
        }
        catch (SQLException e)
        {
            
        }
    }
    
    public void tick(String day)
    {
        switch(day)
        {
            case("MONDAY"):     chbMonday.setSelected(true);
                                break;
            case("TUESDAY"):    chbTuesday.setSelected(true);
                                break;
            case("WEDNESDAY"):  chbWednesday.setSelected(true);
                                break;
            case("THURSDAY"):   chbThursday.setSelected(true);
                                break;          
            case("FRIDAY"):     chbFriday.setSelected(true);
                                break;                      
        }
    }
    
    @FXML
    public void saveTemplate()
    {
        delete();
        
        if(chbMonday.isSelected())
        {
            save("MONDAY");
        }
        
        if(chbTuesday.isSelected())
        {
            save("TUESDAY");
        }
        
        if(chbWednesday.isSelected())
        {
            save("WEDNESDAY");
        }
        
        if(chbThursday.isSelected())
        {
            save("THURSDAY");
        }
        
        if(chbFriday.isSelected())
        {
            save("FRIDAY");
        }
    }
    
    public void save(String day)
    {
        try 
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);

            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();

            String sql = "INSERT INTO template (Day, Start, End, Duration, BreakStart, BreakEnd) VALUES ('" 
                                                                                    + day + "','"
                                                                                    + txtTempStart.getText() + "','"
                                                                                    + txtTempEnd.getText() + "','"
                                                                                    + txtTempLength.getText() + "','"
                                                                                    + txtTempBreakStart.getText() + "','"
                                                                                    + txtTempBreakEnd.getText() + "')";




            stmt.executeUpdate(sql);
           

            c.close();
        } 
        catch (SQLException e) 
        {
            Logger.getLogger(SettingScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void delete()
    {
        try 
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);

            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();

            for(int i = 0; i<5; i++)
            {
                String sql = "DELETE FROM template WHERE Day = '" + week.get(i) + "'";
                stmt.executeUpdate(sql);
            }

            c.close();
        } 
        catch (SQLException x) 
        {

        }
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