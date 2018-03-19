/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settings;

import common.DatabaseConnector;
import common.HelpDialogController;
import common.codeBank;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
/**
 *
 * @author amydo
 */
public class Testing implements Initializable
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
    @FXML private Label lblSaveAlter = new Label();
    
    @FXML Hyperlink hlHlep = new Hyperlink();
    
    
    ArrayList<String> week = new ArrayList<String>();
    ArrayList<String> toDelete = new ArrayList<String>();
       
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        extraInactive();
        templateInactive();
        alterInactive();
        
        paneBloodDetails.setVisible(false);
        lblSaveAlter.setVisible(false);
        lblSaveTemplate.setVisible(false);
        lblSaveExtra.setVisible(false);
        
        showTemplate();
        week.add("MONDAY");
        week.add("TUESDAY");
        week.add("WEDNESDAY");
        week.add("THURSDAY");
        week.add("FRIDAY");
        
        lblNoClinic.setVisible(false);
        
        showTemplate();
        
        alterDate.setShowWeekNumbers(false);
        extraListDate.setShowWeekNumbers(false);
        
        
        alterDateShowingDays();
    }
    
    
    public void alterDateShowingDays()
    {
        // Create a day cell factory
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
    
    
    
    
    
    
    
    
    
    
    //>>>>>>>>>>>>> ADDING EXTRA CLINICS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private boolean initiallyBlood = false;
    
    
    
    //SHOWING--------------------------------------------------------------------------------------
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
            
            checkBlood(date, day);
        }
        else
        {
            showWeekend(date);
        }
        
        
    }
    
    
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
                    sql = "SELECT * FROM template WHERE Day='" + day + "'";
                    rs = stmt.executeQuery(sql);
                    
                    LocalDate currentDate = codeBank.stringToDate(date);

                    while(rs.next())
                    {

                        String From = rs.getString("FromDate");
                        String To = rs.getString("ToDate");

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
    
    public void extraSaveSuccessful()
    {
        lblSaveExtra.setVisible(false);
        clearAllExtra();
        extraListDate.getEditor().clear();
        alterDateShowingDays();

    }
    
    
    public void saveExtra()
    {
        LocalDate selected = extraListDate.getValue();
        String day = selected.getDayOfWeek().name();
        String date = codeBank.dateToString(selected);
        
        System.out.println(day);
       
        if(day.equals("MONDAY") || day.equals("TUESDAY") || day.equals("WEDNESDAY") || day.equals("THURSDAY") || day.equals("FRIDAY"))
        {
            //Creating a new blood clinic
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
                    
                    sql = "DELETE FROM extra WHERE Date='" + date + "'";
                    stmt.executeUpdate(sql);
                    
                    c.close();

                }
                catch(SQLException e)
                {
                    Logger.getLogger(SettingScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            
            if(initiallyBlood == true && !chbBlood.isSelected())
            {
                try
                {
                    Connection c = DatabaseConnector.activateConnection();
                    c.setAutoCommit(true);
                    Statement stmt = c.createStatement();
                    
                    
                    String sql = "DELETE FROM template WHERE Day ='" + date + "'";
                    int result = stmt.executeUpdate(sql);
                                        
                    //if day is a template day 
                    if(templateDay(day))
                    {
                        sql = "INSERT INTO extra VALUES ('" + date + "', '1', '0', '1', '1', '1')";
                        stmt.executeUpdate(sql);
                    }
                    
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
            
            System.out.println(surgery);
            System.out.println(blood);
            System.out.println(preop);
            System.out.println(oncology);
            System.out.println(nonbed);
            
            try
            {
                Connection c = DatabaseConnector.activateConnection();
                c.setAutoCommit(true);
                Statement stmt = c.createStatement();

                if(surgery==0 & blood==0 & preop==0 & oncology==0 & nonbed==0)
                {
                    String sql = "DELETE FROM extra WHERE Date='" + date + "'";
                    stmt.executeUpdate(sql);
                }
                else
                {
                    String sql = "REPLACE INTO extra (Date, Surgery, Blood, Preop, Oncology, Nonbed) VALUES ('" 
                                                                                        + date + "','"
                                                                                        + surgery + "','"
                                                                                        + blood + "','"
                                                                                        + preop + "','"
                                                                                        + oncology + "','"
                                                                                        + nonbed + "')";
                    
                    stmt.executeUpdate(sql);
                }
                
                
                for(int i=0; i<toDelete.size(); i++)
                {
                    stmt.executeUpdate(toDelete.get(i));
                }
                
                c.close();

            }
            catch(SQLException e)
            {
                Logger.getLogger(SettingScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
            }
            
        }
        
    }
    
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
            Logger.getLogger(SettingScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
        return answer;
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
            Logger.getLogger(SettingScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
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
        if(codeBank.checkInteger(txtAlterLength.getText()))
        {
            if(codeBank.checkTime(txtAlterStart.getText()) && codeBank.checkTime(txtAlterEnd.getText()) && codeBank.checkTime(txtAlterBreakStart.getText()) && codeBank.checkTime(txtAlterBreakEnd.getText()))
            {
                if(allOkAlter())
                {
                    saveAlter();
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
    
    public void alterSaveSuccessful()
    {
        lblSaveAlter.setVisible(false);
        clearAllAlter();
        alterDate.getEditor().clear();
    }
    
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
    public void saveTemplateCheck()
    {
        if(codeBank.checkInteger(txtTempLength.getText()))
        {
            if(codeBank.checkTime(txtTempStart.getText()) && codeBank.checkTime(txtTempEnd.getText()) && codeBank.checkTime(txtTempBreakStart.getText()) && codeBank.checkTime(txtTempBreakEnd.getText()))
            {   
                if(allOkTemp())
                {
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
            Logger.getLogger(SettingScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
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
    
    @FXML 
    public void helpExtra()
    {
        String toShow = "SettingsExtra";
        help(toShow);
    }
    
    @FXML 
    public void helpAlter()
    {
        String toShow = "SettingsAlter";
        help(toShow);
    }
    
    @FXML 
    public void helpTemplate()
    {
        String toShow = "SettingsTemplate";
        help(toShow);
    }
    
    
    private HelpDialogController HDC;
    private Pane Hx;
    
    
    public void help(String toShow)
    {
        try 
        {    
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Help");
            
            FXMLLoader DL = new FXMLLoader(getClass().getResource("/common/HelpDialog.fxml"));   
            
            Hx = DL.load(); //ISSUE
            HDC = DL.getController();
            
            HDC.show(toShow);
            
            final Scene scene = new Scene(Hx, 795, 876);
            stage.setScene(scene);
            stage.setOnHidden(e -> HDC.shutdown());
            stage.show();
                      
        } 
        catch (IOException ex) 
        {
            //Logger.getLogger(ProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ISSUE IN MAIN");
        }
    }
    
    
    
}//end of class