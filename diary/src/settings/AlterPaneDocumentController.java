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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 *
 * @author amydo
 * 
 * Controller for the alter section of the setting screen
 * This is where you can alter the times of a specific blood clinic that has already been set up 
 * 
 */
public class AlterPaneDocumentController implements Initializable
{
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
    @FXML private Label lblSaveAlter = new Label();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        lblSaveAlter.setVisible(false);
        lblNoClinic.setVisible(false);
        alterDate.setShowWeekNumbers(false);
        
        alterDateShowingDays();
    }
    
    
    public void alterDateShowingDays()
    {
        // Create a day cell factory - colouring the dates that have a blood clinic scheduled for 
        Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>()
        {
            public DateCell call(final DatePicker datePicker)
            {
                return new DateCell()
                {
                    @Override
                    public void updateItem(LocalDate item, boolean empty)
                    {
                        // Must call super
                        super.updateItem(item, empty);

                        if(hasBlood(item))
                        {
                            this.setStyle(" -fx-background-color: #80bdff; ") ;
                        }
                        
                        
                    }
                };
            }
        };

        // Set the day cell factory to the DatePicker
       alterDate.setDayCellFactory(dayCellFactory);
    }
    
    //Determine which days have a blood clinic running 
    public boolean hasBlood(LocalDate date)
    {
        try
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            Statement stmt = c.createStatement(); 
            
            //make localdate into string
            String day = date.getDayOfWeek().name();
            String stringDate = codeBank.dateToString(date);
            
            rs = stmt.executeQuery("SELECT * FROM extra WHERE Date='" + stringDate + "'");
            if(rs.isBeforeFirst())
            {
                if(rs.getInt("Blood")==1)
                {
                    c.close();
                    return true; //it is set in extra to be there 
                }
                else
                {
                    c.close();
                    return false;
                }
            }
            
            rs = stmt.executeQuery("SELECT * FROM template WHERE Day ='" + stringDate + "'");
            if(rs.isBeforeFirst())
            {
                c.close();
                return true; //found template by date 
            }
            
            rs = stmt.executeQuery("SELECT * FROM template WHERE Day ='" + day + "'");
            if (rs.isBeforeFirst())//found it by the day
            {
                while (rs.next()) 
                {
                    String From = rs.getString("FromDate");
                    String To = rs.getString("ToDate");

                    //If the most current template in use 
                    if (To == null) 
                    {
                        LocalDate dateFrom = codeBank.stringToDate(From);
                        if (date.isAfter(dateFrom)) 
                        {
                            c.close();
                            return true;
                        }
                    } 
                    else 
                    {
                        LocalDate dateFrom = codeBank.stringToDate(From);
                        LocalDate dateTo = codeBank.stringToDate(To);

                        //Falls into the range of the template 
                        if (date.isBefore(dateTo) && (date.isAfter(dateFrom) || date.equals(dateFrom))) 
                        {
                            c.close();
                            return true;
                        }
                    }
                }
            } 
            else 
            {
               c.close();
               return false;
            }          
        }
        catch(SQLException e)
        {
            
        }
        return false;
    }
    
    
    
    
    
    
    //Showing the details of the blood clinic when you click on a date     
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
            
            //Get the templates by date
            String sql = "SELECT * FROM template WHERE Day='" + date + "'";
            rs = stmt.executeQuery(sql);
            
            if(rs.next()) //found the date so show it
            {
                
                txtAlterStart.setText(rs.getString("Start"));
                txtAlterEnd.setText(rs.getString("End"));
                txtAlterLength.setText(rs.getString("Duration"));
                txtAlterBreakStart.setText(rs.getString("BreakStart"));
                txtAlterBreakEnd.setText(rs.getString("BreakEnd"));
            }
            else
            {
                rs = stmt.executeQuery("SELECT * FROM extra WHERE Date='" + date + "'"); //get the date from template 
            
                if(rs.isBeforeFirst() && rs.getInt("Blood") == 0) //If blood is 0 in extra, hide everything (data in extra, blood = 0)
                {
                        lblNoClinic.setVisible(true);
                        txtAlterStart.setVisible(false);
                        txtAlterEnd.setVisible(false);
                        txtAlterLength.setVisible(false);
                        txtAlterBreakStart.setVisible(false);
                        txtAlterBreakEnd.setVisible(false);
                }

                if((rs.isBeforeFirst() && rs.getInt("Blood")==1) || !rs.isBeforeFirst()) //if data in extra and blood = 1 OR no data in extra
                {
                    sql = "SELECT * FROM template WHERE Day='" + day + "'"; //get the day from template 
                    rs = stmt.executeQuery(sql);
                    
                    LocalDate currentDate = codeBank.stringToDate(date);

                    if(!rs.isBeforeFirst()) //If no data for that day 
                    {
                        lblNoClinic.setVisible(true);
                        txtAlterStart.setVisible(false);
                        txtAlterEnd.setVisible(false);
                        txtAlterLength.setVisible(false);
                        txtAlterBreakStart.setVisible(false);
                        txtAlterBreakEnd.setVisible(false);
                    }
                    
                    while(rs.next()) //If there are multiple templates for that day, look at dates 
                    {
                        String From = rs.getString("FromDate");
                        String To = rs.getString("ToDate");

                        if (To == null) //if no To date it is the current, active template 
                        {
                            LocalDate dateFrom = codeBank.stringToDate(From);
                            if (currentDate.isAfter(dateFrom)) //Checking the date is after the From date - make sure in rnage 
                            {
                                txtAlterStart.setText(rs.getString("Start"));
                                txtAlterEnd.setText(rs.getString("End"));
                                txtAlterLength.setText(rs.getString("Duration"));
                                txtAlterBreakStart.setText(rs.getString("BreakStart"));
                                txtAlterBreakEnd.setText(rs.getString("BreakEnd"));
                            }
                        } 
                        else //If there is a To date, checks the range 
                        {
                            LocalDate dateFrom = codeBank.stringToDate(From);
                            LocalDate dateTo = codeBank.stringToDate(To);

                            //Current BEFORE TO
                            //Current AFTER FROM   OR   Current == FROM
                            if (currentDate.isBefore(dateTo) && (currentDate.isAfter(dateFrom) || currentDate.equals(dateFrom))) 
                            {
                                lblNoClinic.setVisible(false);
                                txtAlterStart.setVisible(true);
                                txtAlterEnd.setVisible(true);
                                txtAlterLength.setVisible(true);
                                txtAlterBreakStart.setVisible(true);
                                txtAlterBreakEnd.setVisible(true);
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
    public void saveAlterCheck()
    {
        //Check that duration is a number and that the times are valid 
        if(codeBank.checkInteger(txtAlterLength.getText()))
        {
            if(codeBank.checkTime(txtAlterStart.getText()) && codeBank.checkTime(txtAlterEnd.getText()) && codeBank.checkTime(txtAlterBreakStart.getText()) && codeBank.checkTime(txtAlterBreakEnd.getText()))
            {
                if(allOkAlter())
                {
                    saveAlter();
                    //'Save successful' shows for a few seconds and then disappears to show save was ok 
                    //http://tomasmikula.github.io/blog/2014/06/04/timers-in-javafx-and-reactfx.html accessed 17/3
                    lblSaveAlter.setVisible(true);
                    Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(1000),
                    ae -> alterSaveSuccessful()));
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
    
    //Clearing the textboxes when you have saved new details 
    public void alterSaveSuccessful()
    {
        lblSaveAlter.setVisible(false);
        clearAllAlter();
        alterDate.getEditor().clear();
    }
    
    //Checking that all the data is ok before saving - things are in the correct order 
     public boolean allOkAlter()
    {
        LocalTime start = LocalTime.parse(txtAlterStart.getText());
        LocalTime end = LocalTime.parse(txtAlterEnd.getText());
        LocalTime breakStart = LocalTime.parse(txtAlterBreakStart.getText());
        LocalTime breakEnd = LocalTime.parse(txtAlterBreakEnd.getText());

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
    
    
    //The actual saving of the new information for the blood clinic - saves it by the date 
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
            
            String sql = "DELETE FROM template WHERE Day='" + date + "'";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO template (Day, Start, End, Duration, BreakStart, BreakEnd) VALUES ('" 
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
            //Logger.getLogger(SettingScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    
}
