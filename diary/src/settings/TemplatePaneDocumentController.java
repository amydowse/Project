/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settings;

import common.DatabaseConnector;
import common.codeBank;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author amydo
 * 
 * Controller for the template section of the settings
 * This is where you set up a recurring template for blood clinics that is valid from that day forward 
 * 
 */
public class TemplatePaneDocumentController implements Initializable
{
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
    @FXML private Label lblSaveTemplate = new Label();
    
    ArrayList<String> week = new ArrayList<String>();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        lblSaveTemplate.setVisible(false);
        week.add("MONDAY");
        week.add("TUESDAY");
        week.add("WEDNESDAY");
        week.add("THURSDAY");
        week.add("FRIDAY");
        showTemplate();
    }
    
    
    //Showing the currenlty active template - showing for all the days that are active (have not finish date) 
    public void showTemplate()
    {
        try
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);
            Statement stmt = c.createStatement();
            ResultSet rs;
            
            String sql = "SELECT * FROM template WHERE ToDate IS NULL";
            
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
    
    //Getting the box ticked that corresponds to the day 
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
    
    //Saving the new template - check length is a number, times are valid and temporal order is ok 
    @FXML
    public void saveTemplateCheck()
    {
        if(codeBank.checkInteger(txtTempLength.getText()))
        {
            if(codeBank.checkTime(txtTempStart.getText()) && codeBank.checkTime(txtTempEnd.getText()) && codeBank.checkTime(txtTempBreakStart.getText()) && codeBank.checkTime(txtTempBreakEnd.getText()))
            {   
                if(allOkTemp())
                {
                    //If save is successful show a message for a few seconds that then disspears 
                    saveTemplate();
                    lblSaveTemplate.setVisible(true);
                    Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(1000),
                    ae -> templateSaveSuccessful()));
                    timeline.play();

                }
                else
                {
                    codeBank.timeOrderError();
                }
            }
            else
            {
                codeBank.timeError();
            }
        }
        else
        {
            codeBank.integerError();
        }
    }
    
    public void templateSaveSuccessful()
    {
        lblSaveTemplate.setVisible(false);
    }
    
    //Checks the temporal order of the times of a new template 
    public boolean allOkTemp()
    {
        LocalTime start = LocalTime.parse(txtTempStart.getText());
        LocalTime end = LocalTime.parse(txtTempEnd.getText());
        LocalTime breakStart = LocalTime.parse(txtTempBreakStart.getText());
        LocalTime breakEnd = LocalTime.parse(txtTempBreakEnd.getText());

        if (start.isBefore(end)) 
        {
            if (breakStart.isBefore(breakEnd) || breakStart.equals(breakEnd)) 
            {
                if ((breakStart.isAfter(start) && breakEnd.isBefore(end)) || (breakStart.equals(end) && breakEnd.equals(end)))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    //Saving the template for each day that is selected 
    public void saveTemplate()
    {
        setToNull();
        
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
    
    //Saving a specific days template 
    public void save(String day)
    {
        try 
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);
            Statement stmt = c.createStatement();
            
            String date = codeBank.dateToString(codeBank.getCurrentDate());

            String sql = "INSERT INTO template (Day, FromDate, Start, End, Duration, BreakStart, BreakEnd) VALUES ('" 
                                                                                    + day + "','"
                                                                                    + date + "','"
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
            //Logger.getLogger(SettingScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    //Setting a finish date to the currently active template 
    public void setToNull()
    {
        try 
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);

            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();

            String sql = "UPDATE template SET ToDate ='" + codeBank.dateToString(codeBank.getCurrentDate()) + "' WHERE ToDate IS NULL AND FromDate IS NOT NULL";
            stmt.executeUpdate(sql);

            c.close();
        } 
        catch (SQLException x) 
        {

        }
    }
    
    
    
}
