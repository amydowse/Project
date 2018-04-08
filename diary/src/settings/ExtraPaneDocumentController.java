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
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import static settings.SettingScreenDocumentController.APDC;

/**
 *
 * @author amydo
 * 
 * Controller for the extra pane of the settings screen
 * Extra is where you can add in extra clinics / remove clinics 
 * 
 */
public class ExtraPaneDocumentController implements Initializable
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
    @FXML private Label lblSaveExtra = new Label();
    
    @FXML private TextField txtExtraStart = new TextField();
    @FXML private TextField txtExtraEnd = new TextField();
    @FXML private TextField txtExtraLength = new TextField();
    @FXML private TextField txtExtraBreakStart = new TextField();
    @FXML private TextField txtExtraBreakEnd = new TextField();
    
    ArrayList<String> toDelete = new ArrayList<String>();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        paneBloodDetails.setVisible(false);
        lblSaveExtra.setVisible(false);
        extraListDate.setShowWeekNumbers(false);
    }
    
    private boolean initiallyBlood = false;
    
    
    
    //SHOWING--------------------------------------------------------------------------------------
    @FXML
    public void selectedExtraDate()
    {
        clearAllExtra();
        
        LocalDate selected = extraListDate.getValue();
        String day = selected.getDayOfWeek().name();
        String date = codeBank.dateToString(selected);
        
        //For weekdays, have surgery, preop, oncology and nonbed selected and not allowed to deselect 
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
            
            checkBlood(date, day);
        }
        else
        {
            showWeekend(date);
        }
        
        
    }
    
    //Method to determine what is shown for a weekend 
    public void showWeekend(String date)
    {
        try
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);
            Statement stmt = c.createStatement();
            ResultSet rs;
            
            String sql = "SELECT * FROM extra WHERE Date='" + date + "'";
            rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                int surgery = rs.getInt("Surgery");
                int blood = rs.getInt("Blood");
                int preop = rs.getInt("Preop");
                int oncology = rs.getInt("Oncology");
                int nonbed = rs.getInt("Nonbed");
            

                if(surgery==1)
                {
                    chbSurgery.setSelected(true);
                }

                //If there is a blood clinic scheduled at the weekend, show the times for it too 
                if(blood==1)
                {
                    chbBlood.setSelected(true);
                    paneBloodDetails.setVisible(true);
                    
                    sql = "SELECT * FROM template WHERE Day='" + date + "'";
                    rs = stmt.executeQuery(sql);
                    
                    if(rs.next())
                    {
                        txtExtraStart.setText(rs.getString("Start"));
                        txtExtraEnd.setText(rs.getString("End"));
                        txtExtraLength.setText(rs.getString("Duration"));
                        txtExtraBreakStart.setText(rs.getString("BreakStart"));
                        txtExtraBreakEnd.setText(rs.getString("BreakEnd"));
                        
                        txtExtraStart.setEditable(false);
                        txtExtraEnd.setEditable(false);
                        txtExtraLength.setEditable(false);
                        txtExtraBreakStart.setEditable(false);
                        txtExtraBreakEnd.setEditable(false);
                    }
                    
                }

                if(preop==1)
                {
                    chbPreop.setSelected(true);
                }

                if(oncology==1)
                {
                    chbOncology.setSelected(true);
                }

                if(nonbed==1)
                {
                    chbNonbed.setSelected(true);
                }
            }
        
            c.close();
            
        }
        catch(SQLException e)
        {
            Logger.getLogger(SettingScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    //Check to see if there is a blood clinic schedueld for a weekday 
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
               
                txtExtraStart.setEditable(false);
                txtExtraEnd.setEditable(false);
                txtExtraLength.setEditable(false);
                txtExtraBreakStart.setEditable(false);
                txtExtraBreakEnd.setEditable(false);
               
            }
            else
            {
                rs = stmt.executeQuery("SELECT * FROM extra WHERE Date='" + date + "'");
            
                if(rs.isBeforeFirst() && rs.getInt("Blood") == 0)
                {
                    //no clinic
                }

                if((rs.isBeforeFirst() && rs.getInt("Blood")==1) || !rs.isBeforeFirst()) //if has data and blood is 1 OR no data 
                {
                    sql = "SELECT * FROM template WHERE Day='" + day + "'"; //find by day
                    rs = stmt.executeQuery(sql);
                    
                    LocalDate currentDate = codeBank.stringToDate(date);

                    while(rs.next())
                    {

                        String From = rs.getString("FromDate");
                        String To = rs.getString("ToDate");

                        //Finding the active template 
                        if (To == null) 
                        {
                            LocalDate dateFrom = codeBank.stringToDate(From);
                            if (currentDate.isAfter(dateFrom)) 
                            {
                                initiallyBlood = true;
                                chbBlood.setSelected(true);
                                paneBloodDetails.setVisible(true);
                                txtExtraStart.setText(rs.getString("Start"));
                                txtExtraEnd.setText(rs.getString("End"));
                                txtExtraLength.setText(rs.getString("Duration"));
                                txtExtraBreakStart.setText(rs.getString("BreakStart"));
                                txtExtraBreakEnd.setText(rs.getString("BreakEnd"));
                               
                                txtExtraStart.setEditable(false);
                                txtExtraEnd.setEditable(false);
                                txtExtraLength.setEditable(false);
                                txtExtraBreakStart.setEditable(false);
                                txtExtraBreakEnd.setEditable(false);
                                
                            }
                        } 
                        else 
                        {
                            LocalDate dateFrom = codeBank.stringToDate(From);
                            LocalDate dateTo = codeBank.stringToDate(To);

                            if (currentDate.isBefore(dateTo) && (currentDate.isAfter(dateFrom) || currentDate.equals(dateFrom))) 
                            {
                                initiallyBlood = true;
                                chbBlood.setSelected(true);
                                paneBloodDetails.setVisible(true);
                                txtExtraStart.setText(rs.getString("Start"));
                                txtExtraEnd.setText(rs.getString("End"));
                                txtExtraLength.setText(rs.getString("Duration"));
                                txtExtraBreakStart.setText(rs.getString("BreakStart"));
                                txtExtraBreakEnd.setText(rs.getString("BreakEnd"));
                                
                                txtExtraStart.setEditable(false);
                                txtExtraEnd.setEditable(false);
                                txtExtraLength.setEditable(false);
                                txtExtraBreakStart.setEditable(false);
                                txtExtraBreakEnd.setEditable(false);
                            }
                        }
                    }
                       
                }
            }
            c.close();
        }
        catch(SQLException e)
        {
            
        }
    }
    
    
    
    
    
    
    
    
    //SAVING--------------------------------------------------------------------------------------
    
    //Checking that the times entered for a new blood clinic are in temporal order 
    public boolean allOk()
    {
        LocalTime start = LocalTime.parse(txtExtraStart.getText());
        LocalTime end = LocalTime.parse(txtExtraEnd.getText());
        LocalTime breakStart = LocalTime.parse(txtExtraBreakStart.getText());
        LocalTime breakEnd = LocalTime.parse(txtExtraBreakEnd.getText());

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
    
    //Checking that duration is a number, that blood is selected, the times are valid and are in a temporal order 
    @FXML
    public void saveExtraCheck()
    {
        if(codeBank.checkInteger(txtExtraLength.getText()))
        {
            if(chbBlood.isSelected())
            {
                if(codeBank.checkTime(txtExtraStart.getText()) && codeBank.checkTime(txtExtraEnd.getText()) && codeBank.checkTime(txtExtraBreakStart.getText()) && codeBank.checkTime(txtExtraBreakEnd.getText()))
                {   
                    if(allOk())
                    {
                        //When saved successfully, message shows up for a few seconds 
                        saveExtra();
                        lblSaveExtra.setVisible(true);
                        Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(1000),
                        ae -> extraSaveSuccessful()));
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
                saveExtra();
                lblSaveExtra.setVisible(true);
                Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> extraSaveSuccessful()));
                timeline.play();
            }
        }
        else
        {
            codeBank.integerError();
        }
    }
    
    //When save is successful clear all of the data and update the alter blood clinic section to show a blood clinic has been added 
    public void extraSaveSuccessful()
    {
        lblSaveExtra.setVisible(false);
        clearAllExtra();
        extraListDate.getEditor().clear();
        APDC.alterDateShowingDays();

    }
    
    //Overall method to save the extra clinics added 
    public void saveExtra()
    {
        LocalDate selected = extraListDate.getValue();
        String day = selected.getDayOfWeek().name();
        String date = codeBank.dateToString(selected);
        
        if(day.equals("MONDAY") || day.equals("TUESDAY") || day.equals("WEDNESDAY") || day.equals("THURSDAY") || day.equals("FRIDAY"))
        {
            //Weekday, initally no blood clinic and then one is createed 
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
                    
                    //Say that there is not a blood clinic that has been added 
                    sql = "REPLACE INTO extra (Date, Surgery, Blood, Preop, Oncology, Nonbed) VALUES ('" + date + "', 1, 1, 1, 1, 1)";
                    stmt.executeUpdate(sql);
                    
                    c.close();

                }
                catch(SQLException e)
                {
                    ///Logger.getLogger(SettingScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            
            //Weekday, initally a blood clinic and now there isnt 
            if(initiallyBlood == true && !chbBlood.isSelected())
            {
                try
                {
                    Connection c = DatabaseConnector.activateConnection();
                    c.setAutoCommit(true);
                    Statement stmt = c.createStatement();
                    
                    //If blood is a date template, delete it 
                    String sql = "DELETE FROM template WHERE Day ='" + date + "'";
                    int result = stmt.executeUpdate(sql);
                                        
                    //if day is a template day say that it does not follow template  
                    if(templateDay(day))
                    {
                        sql = "REPLACE INTO extra (Date, Surgery, Blood, Preop, Oncology, Nonbed) VALUES ('" + date + "', '1', '0', '1', '1', '1')";
                        stmt.executeUpdate(sql);
                    }
                    else
                    {
                        //if it not a template day, remove it from the extra list 
                        sql = "DELETE FROM extra WHERE Date = '" + date + "'";
                        stmt.executeUpdate(sql);
                    }
                    
                    //Delete any appointments that were booked 
                    sql = "DELETE FROM blood WHERE Date ='" + date + "'";
                    stmt.executeUpdate(sql);
                    
                    c.close();

                }
                catch(SQLException e)
                {
                    Logger.getLogger(SettingScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            
        }
        else  //weekend 
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
            else
            {
                //Adding to an array all of the appointments that need to be deleted because that clinic has been removed 
                toDelete.add("DELETE FROM diary WHERE Date ='" + date + "'");
            }
                        
            if(chbBlood.isSelected())
            {
                blood = 1;
                addBlood();
            }
            else
            {
                deleteBlood();
            }
            
            if(chbPreop.isSelected())
            {
                preop = 1;
            }
            else
            {
                toDelete.add("DELETE FROM preop WHERE Date ='" + date + "'");
            }
                
            
            if(chbOncology.isSelected())
            {
                oncology = 1;
            }
            else
            {
                toDelete.add("DELETE FROM oncology WHERE Date ='" + date + "'");
            }
            
            if(chbNonbed.isSelected())
            {
                nonbed = 1;
            }
            else
            {
                toDelete.add("DELETE FROM nonbed WHERE Date ='" + date + "'");

            }
                        
            try
            {
                Connection c = DatabaseConnector.activateConnection();
                c.setAutoCommit(true);
                Statement stmt = c.createStatement();

                //If all clinics are cancelled, delete that date from extra 
                if(surgery==0 & blood==0 & preop==0 & oncology==0 & nonbed==0)
                {
                    String sql = "DELETE FROM extra WHERE Date='" + date + "'";
                    stmt.executeUpdate(sql);
                }
                else
                {
                    //Save into extra the clinics that are taking place 
                    String sql = "REPLACE INTO extra (Date, Surgery, Blood, Preop, Oncology, Nonbed) VALUES ('" 
                                                                                        + date + "','"
                                                                                        + surgery + "','"
                                                                                        + blood + "','"
                                                                                        + preop + "','"
                                                                                        + oncology + "','"
                                                                                        + nonbed + "')";
                    
                    stmt.executeUpdate(sql);
                }
                
                //Delete those that need to be 
                for(int i=0; i<toDelete.size(); i++)
                {
                    stmt.executeUpdate(toDelete.get(i));
                }
                
                c.close();

            }
            catch(SQLException e)
            {
               //Logger.getLogger(SettingScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
            }
            
        }
        
    }
    
    //Tells you if a day is an active template 
    public boolean templateDay(String day)
    {
        boolean answer = false;
        try 
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);
            Statement stmt = c.createStatement();
            ResultSet rs;

            String sql = "SELECT Day FROM template WHERE ToDate IS NULL";
            rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                if(rs.getString("Day").equals(day))
                {
                    answer = true;
                }
            }
            c.close();
        } 
        catch (SQLException e) 
        {
            //Logger.getLogger(SettingScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
        return answer;
    }
    
    
    
    
    //Method to add in a new blood clinic 
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
            //Logger.getLogger(SettingScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    
    //Method to delete a blood clinic when it has been unticked 
    public void deleteBlood()
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

            String sql = "DELETE FROM template WHERE Day='" + date + "'";
            stmt.executeUpdate(sql);
           
            c.close();
        } 
        catch (SQLException e) 
        {
            //Logger.getLogger(SettingScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    
    
    
    public void clearAllExtra()
    {
        initiallyBlood = false;
        
        toDelete.clear();
        
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
        
        txtExtraStart.setEditable(true);
        txtExtraEnd.setEditable(true);
        txtExtraLength.setEditable(true);
        txtExtraBreakStart.setEditable(true);
        txtExtraBreakEnd.setEditable(true);
    }
    
    //If there is a blood clinic, show the textfields about blood clinic times 
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
