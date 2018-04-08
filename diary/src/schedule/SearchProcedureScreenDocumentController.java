package schedule;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sun.javafx.scene.control.skin.DatePickerContent;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import schedule.specificWorking;
import common.DatabaseConnector;
import common.HelpDialogController;
import common.codeBank;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author amydo
 */
public class SearchProcedureScreenDocumentController implements Initializable
{
    @FXML RadioButton rb1W = new RadioButton();
    @FXML RadioButton rb2W = new RadioButton();
    @FXML RadioButton rb3W = new RadioButton();
    @FXML RadioButton rb4W = new RadioButton();
    
    @FXML ChoiceBox cmbSearchProcedure = new ChoiceBox();
    @FXML Button btnSearch = new Button();
    @FXML Label lblSearchCriteria = new Label();
    
    @FXML TableView tblSearchResult = new TableView();
    @FXML TableColumn tblColDate = new TableColumn();
    @FXML TableColumn tblColTime = new TableColumn();
    
    @FXML Hyperlink hlHlep = new Hyperlink();
        
    //Data structures to use 
    String date;
    ArrayList<Integer> workingStaff = new ArrayList<>();
    ArrayList<String> shift = new ArrayList<>();
    ArrayList<specificWorking> specificStaff = new ArrayList<>();
    ArrayList<Integer> unassignedStaff = new ArrayList<>();
    ArrayList<procedure> bedProcedures = new ArrayList<>();
    ArrayList<procedure> nonbedProcedures = new ArrayList<>();
    boolean[][] scheduleSTAFF;
    boolean[][] scheduleLOCATION;
    String[] locations;
    
    ArrayList<multiple> multiplePatientsAM = new ArrayList<multiple>();
    ArrayList<multiple> multiplePatientsPM = new ArrayList<multiple>();
    
    ArrayList<schedule> bedAppointments = new ArrayList<schedule>();
    
    ObservableList<result> results = FXCollections.observableArrayList();
    
    ArrayList<LocalDate> days = new ArrayList<LocalDate>();
    
    @FXML StackPane paneCal = new StackPane();
    
    DatePicker dp = new DatePicker();
    

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        fillDropDown();
        ToggleGroup group = new ToggleGroup();
        rb1W.setToggleGroup(group);
        rb2W.setToggleGroup(group);
        rb3W.setToggleGroup(group);
        rb4W.setToggleGroup(group);
        rb1W.setSelected(true);
        tblSearchResult.setPlaceholder(new Label("There are no available appointments within that range"));
        
        //Displaying the calander and setting the selected date to be today 
        //https://stackoverflow.com/questions/44010082/how-to-scale-javafx-datepicker-and-transform-to-a-calendar accessed 6/4/18
        dp.setShowWeekNumbers(false);
        DatePickerSkin pickerSkin = new DatePickerSkin(dp);
        DatePickerContent calendar = (DatePickerContent) pickerSkin.getPopupContent();
        paneCal.getChildren().add(calendar);
        
        dp.setValue(LocalDate.now());
    }
    
    //Fill the drop down with all of the procedures the ward does 
    public void fillDropDown()
    {
        ArrayList<String> procedures = new ArrayList<String>();
        
        try
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            Statement stmt = c.createStatement();     
            
            rs = stmt.executeQuery("SELECT * FROM procedures");
            while(rs.next())
            {
                procedures.add(rs.getString("Name"));
            }
            
            //Sort procedures into alphabetical order 
            Collections.sort(procedures);
            cmbSearchProcedure.getItems().addAll(procedures);
            
        }
        catch(SQLException e)
        {
            
        }
    }
    
    @FXML
    public void search()
    {
        lblSearchCriteria.setText(cmbSearchProcedure.getValue().toString());
         
        //Clearing all old results when you press search again 
        results.clear();
        tblSearchResult.getItems().clear();
               
        workingStaff.clear();
        shift.clear();
        specificStaff.clear();
        unassignedStaff.clear();
        bedProcedures.clear();
        nonbedProcedures.clear();
        scheduleLOCATION = new boolean[16][145];
        
        multiplePatientsAM.clear();
        multiplePatientsPM.clear();
        bedAppointments.clear();
        
        days.clear();
        
        calculateDays();
        
        //Find suggestions for each day in the range
        for(int i=0; i<days.size(); i++)
        {
            findWhatIsOn(days.get(i));
            suggest(cmbSearchProcedure.getValue().toString(), days.get(i));
        }
        //display all results for that time range 
        displayResult();
    }

    //Getting the days that you will need to consider 
    public void calculateDays()
    {
        int count = 0;
        
        if(rb1W.isSelected())
            count = 7;
        if(rb2W.isSelected())
            count = 14;
        if(rb3W.isSelected())
            count = 21;
        if(rb4W.isSelected())
            count = 28;
        
        LocalDate considering = dp.getValue();
        
        for(int i=0; i<count; i++)
        {
            if(considering.getDayOfWeek().toString().equals("SATURDAY") || considering.getDayOfWeek().toString().equals("SUNDAY") )
            {
                //check database if its a weekend to see if anything is going on 
                try
                {
                    Connection c = DatabaseConnector.activateConnection();
                    c.setAutoCommit( true ); 
                    ResultSet rs ;
                    Statement stmt = c.createStatement();
                    
                    String date = codeBank.dateToString(considering);
                    
                    rs = stmt.executeQuery("SELECT * FROM extra WHERE Date ='" + date + "'"); 
                    if(rs.isBeforeFirst())
                    {
                        days.add(considering);
                    }
                    
                    c.close();
                }
                catch(SQLException e)
                {
                    //Logger.getLogger(SearchProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            else //week day, need to consider
            {
                days.add(considering);
            }
            considering = considering.plusDays(1);
        }
    }
    
    //Determining what clinics are running for each day you are considering 
    public void findWhatIsOn(LocalDate Date)
    {
        try
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            Statement stmt = c.createStatement();     
            
            String date = codeBank.dateToString(Date);
            String day = Date.getDayOfWeek().name();

            if(day.equals("MONDAY") || day.equals("TUESDAY") || day.equals("WEDNESDAY") || day.equals("THURSDAY") || day.equals("FRIDAY"))
            {
                rs = stmt.executeQuery("SELECT * FROM extra WHERE Date ='" + date + "'"); //either no data or there is not blood clinic 
                if(rs.isBeforeFirst())
                {
                    if(rs.getInt("Blood") == 0)
                    {
                        //There is no blood clinic running 
                        fill(Date, 1, 0, 1, 1, 1);
                    }
                }
                else
                {
                    rs = stmt.executeQuery("SELECT * FROM template WHERE Day ='" + date + "'"); //find blood clinic by date 
                    if(rs.isBeforeFirst())
                    {
                        //There is a blood clinic found by date 
                        fill(Date, 1, 1, 1, 1, 1);
                    }
                    else
                    {
                        rs = stmt.executeQuery("SELECT * FROM template WHERE Day ='" + day + "' AND ToDate IS NULL");//find blood clinic by day
                        if(rs.isBeforeFirst())
                        {
                            //There is a blood clinic found by day that is still active 
                            fill(Date, 1, 1, 1, 1, 1);
                        }
                        else
                        {
                            //There is no blood clinic running 
                            fill(Date, 1, 0, 1, 1, 1);
                        }
                    }
                }

            }
            else
            {
                //Weekend so need to consider the entry in extra
                rs = stmt.executeQuery("SELECT * FROM extra WHERE Date ='" + date + "'"); 
                while(rs.next())
                {
                    fill(Date, rs.getInt("Surgery"), rs.getInt("Blood"), rs.getInt("Preop"), rs.getInt("Oncology"), rs.getInt("Nonbed"));
                }
            }
        }
        catch(SQLException e)
        {
            //Logger.getLogger(SearchProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    
    //Fills the staff and location arrays to show when they are busy/in use
    //True means busy
    //False means free
    public void fill(LocalDate Date, int Diary, int Blood, int Preop, int Oncology, int Nonbed)
    {
        //Data structures to use 
        date = codeBank.dateToString(Date);
        workingStaff.clear();
        shift.clear();
        specificStaff.clear();
        unassignedStaff.clear();
        bedProcedures.clear();
        nonbedProcedures.clear();
        scheduleLOCATION = new boolean[16][145];
        
        multiplePatientsAM.clear();
        multiplePatientsPM.clear();
        bedAppointments.clear();
        
        locations = new String[] {"1M", "2M", "3M", "4M", "EM", "1L", "2L", "3L", "4L", "5L", "6L", "EL"};
        
        //Getting information about staff working, shifts and their specific locations 
        try
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            Statement stmt = c.createStatement();
            
            //Get all the staff working that day and their shifts
            rs = stmt.executeQuery("SELECT * FROM working WHERE Date ='" + date + "'");  
            while(rs.next())
            { 
                workingStaff.add(rs.getInt("Staff_ID"));
                shift.add(rs.getString("Shift"));
            }
            
            //Get the staff who have a specific location
            rs = stmt.executeQuery("SELECT * FROM specificworking WHERE Date ='" + date + "'"); 
            while(rs.next())
            { 
                int ID = rs.getInt("ID");
                String location = rs.getString("Place");
                specificStaff.add(new specificWorking(ID, location));
            }
            
            //Get all the bed procedures the ward does 
            rs = stmt.executeQuery("SELECT Name, Duration, NumberOfNurses FROM procedures WHERE Location ='Bed'"); 
            while(rs.next())
            { 
                procedure x = new procedure(rs.getString("Name"), rs.getInt("Duration"), rs.getInt("NumberOfNurses"));
                bedProcedures.add(x);
            }
            
            //Get all of the non-bed procedures the ward does
            rs = stmt.executeQuery("SELECT Name, Duration, NumberOfNurses FROM procedures WHERE Location ='Non-bed'"); 
            while(rs.next())
            { 
                procedure x = new procedure(rs.getString("Name"), rs.getInt("Duration"), rs.getInt("NumberOfNurses"));
                nonbedProcedures.add(x);
            }
            c.close();           
        }
        catch (SQLException e)
        {
            //Logger.getLogger(SearchProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
        

        
        //Gets a list of unassigned staff for that day 
        boolean match;
        for(int i=0; i<workingStaff.size(); i++)
        {
            match = false;
            for(int j=0; j<specificStaff.size(); j++)
            {
                if(workingStaff.get(i) == specificStaff.get(j).getID())
                {
                    match = true;
                }
            }
            if(!match)
            {
                unassignedStaff.add(workingStaff.get(i));
            }
        }
        
        
        
        //Setting up the schedule for the staff working that day
        scheduleSTAFF = new boolean[workingStaff.size()][145]; //the number of 5 min slots in 7:00-7:00 (0-144)
        
        //Blocking off the staffSCHEDULE to match shift they are working - no LD option as they work 7-7
        for(int i=0; i<shift.size(); i++)
        {
            switch(shift.get(i))
            {
                case "L":
                    workingL(i);
                    break;
                case "E":
                    workingE(i);
                    break;
                default:
                    break;
            }
        }
        
        //Marking off breaks
        breaks();
          
        //Blocking off staff and location for differnt types of appointment
        //If that clinic is not scheduled, set the location to show busy all the time so not scheduling done 
        if(Blood == 1 && inSpecificWorking("Blood"))
        {
            blood();
        }
        else
        {
            remove(12); //index of blood room in location array 
        }
        
        if(Preop == 1 && (inSpecificWorking("PreopAM") || inSpecificWorking("PreopPM")))
        {
            preop();
        }
        else
        {
            remove(13); //index of pre-op room in location array
        }
        
        if(Oncology == 1 && inSpecificWorking("Oncology"))
        {
            oncology();
        }
        else
        {
            remove(14); //index of oncology room in location array
        }
        
        if(Nonbed == 1 && inSpecificWorking("Nonbed"))
        {
            nonbed();
        }
        else
        {
            remove(15); // index of non-bed room in location array 
        }
        if(Diary == 1)
        {
            diary();
        }
        else
        {
            remove(-1);
        }
    }
    
    //if that area isnt on offer, set it to 'true' so it seems fully booked so not scheduling is done 
    public void remove(int index)
    {
        if(index == -1)
        {
            //This is the beds so need to set all of the beds to be busy so loop through all 11 - not treating as AM and PM
            for(int j = 0; j<12; j++)
            {
                for(int i=0; i<145; i++)
                {
                    scheduleLOCATION[index][i] = true;
                }
            }
        }
        else
        {
            for(int i=0; i<145; i++)
            {
                scheduleLOCATION[index][i] = true;
            }
        }
    }
    
    //Method to determine if a staff member is assiged to work in a specific area 
    public boolean inSpecificWorking(String location)
    {
        for(int i=0; i<specificStaff.size(); i++)
        {
            if(specificStaff.get(i).getLocation().equals(location))
            {
                return true;
            }
        }
        return false;
    }
    
    
    
    //Blocking of the sections when the person isnt on shift 
    public void workingL(int ID)
    {
        //Shows that soemone working a late shift if not there before 11:30
        for(int i=0; i<54; i++)
        {
            scheduleSTAFF[ID][i] = true;
        }
    }
    
    public void workingE(int ID)
    {
        //Showing that soemone working an early shift is not there after 14:30
        for(int i=91; i<145; i++)
        {
            scheduleSTAFF[ID][i] = true;
        }
    }
    
    
    
    //Puts in busy for breaks for each staff member depending on their shift
    //This is not set in stone - user can overbook these break times but does scheudle in for a break
    public void breaks()
    {
        int LDCount = 0;
        int ECount = 0;
        int LCount = 0;
        int stagger = 6;    //number of array boxes that represent 30 mins 
        
        //For every 3 people doing the same shift, the breaks a staggered by 30 mins 
        for(int i=0; i<shift.size(); i++)
        {
            switch(shift.get(i))
            {
                case "LD":
                    for(int j= (72 + (LDCount*stagger)); j<(78 + (LDCount*stagger)); j++)   //13:00 - 13:30 breaks 
                    {
                        scheduleSTAFF[i][j] = true;
                    }
                    for(int j= (96 + (LDCount*stagger)); j<(102 + (LDCount*stagger)); j++)   //15:00 - 15:30 breaks 
                    {
                        scheduleSTAFF[i][j] = true;
                    }                    
                    LDCount++;
                    if(LDCount == 3)
                        LDCount = 1;
                    break;
                case "L":
                    for(int j= (108 + (LCount*stagger)); j<(114 + (LCount*stagger)); j++)   //16:00 - 16:30 breaks 
                    {
                        scheduleSTAFF[i][j] = true;
                    } 
                    LCount++;
                    if(LCount == 3)
                        LCount = 1;
                    break;
                case "E":
                    for(int j= (48 + (ECount*stagger)); j<(54 + (ECount*stagger)); j++)   //11:00 - 15:30 breaks 
                    {
                        scheduleSTAFF[i][j] = true;
                    } 
                    ECount++;
                    if(ECount == 3)
                        ECount = 1;
                    break;
                default:
                    break;
            }
        }
    }
    
    
    //Block out the times that there are pre-ops scheduled for the specific person working in that area 
    //Split across AM and PM
    public void preop()
    {
        ArrayList<schedule> appointments = new ArrayList<schedule>();
        int AMStaff = -1;
        int PMStaff = -1;
        
        for(int i=0; i<specificStaff.size(); i++)
        {
            if(specificStaff.get(i).getLocation().equals("PreopAM"))
            {
                //Get index of staff member doing AM preop - index in scheduleSTAFF
                AMStaff = findIndex(specificStaff.get(i).getID());
            } 
            
            if(specificStaff.get(i).getLocation().equals("PreopPM") )
            {
                //Get index of staff member doing PM preop - index in scheduleSTAFF
                PMStaff = findIndex(specificStaff.get(i).getID());
            }
            
            try 
            {
                Connection c = DatabaseConnector.activateConnection();
                c.setAutoCommit(true);
                ResultSet rs;
                Statement stmt = c.createStatement();

                //Get all pre-op appointments for that day 
                rs = stmt.executeQuery("SELECT * FROM preop WHERE Date ='" + date + "'"); 
                while (rs.next()) 
                {
                    String time = rs.getString("Time");
                    LocalTime Time = LocalTime.parse(time);

                    appointments.add(new schedule(Time, 30));
                }

                int index;
                for (int j = 0; j < appointments.size(); j++) 
                {
                    //For each appointment, blocking off the correct staff member and the location 
                    if(appointments.get(j).getTime().isBefore(LocalTime.parse("12:00")))
                    {
                        index = AMStaff;
                    }
                    else
                    {
                        index = PMStaff;
                    }

                    if(index != -1)
                    {
                        LocalTime dayStart = LocalTime.parse("07:00");
                        long minutesBetween = ChronoUnit.MINUTES.between(dayStart, appointments.get(j).getTime());
                        int arraySpace = (int) minutesBetween / 5;
                        for (int k = arraySpace; k < arraySpace + 6; k++) 
                        {
                            scheduleSTAFF[index][k] = true;
                            scheduleLOCATION[13][k] = true;
                        }
                    }
                }
            } 
            catch (SQLException e) 
            {
                //Logger.getLogger(SearchProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    
    
    //Blocks out the booked appointments 
    public void blood()
    {       
        ArrayList<schedule> appointments = new ArrayList<schedule>();
        
        int duration = 0;
        String breakStartTime = "";
        String breakEndTime = "";
        
        String day = (codeBank.stringToDate(date)).getDayOfWeek().name();
        
        for(int i=0; i<specificStaff.size(); i++)
        {
            if(specificStaff.get(i).getLocation().equals("Blood"))
            {
                //Get the index of the staff member who is assigned to work in blood clinic 
                int index = findIndex(specificStaff.get(i).getID());
                
                try
                {
                    Connection c = DatabaseConnector.activateConnection();
                    c.setAutoCommit( true ); 
                    ResultSet rs ;
                    Statement stmt = c.createStatement();

                    //Find template by date 
                    rs = stmt.executeQuery("SELECT * FROM template WHERE Day ='" + date + "'"); 
                    if(rs.isBeforeFirst())
                    { 
                        duration = rs.getInt("Duration");
                        breakStartTime = rs.getString("BreakStart");
                        breakEndTime = rs.getString("BreakEnd");
                    }
                    else
                    {
                        //Find template by day - it is active 
                        rs = stmt.executeQuery("SELECT * FROM template WHERE Day ='" + day + "' AND ToDate IS NULL");  
                        if(rs.isBeforeFirst())
                        { 
                            duration = rs.getInt("Duration");
                            breakStartTime = rs.getString("BreakStart");
                            breakEndTime = rs.getString("BreakEnd");
                        }
                    }
                    
                    updateBloodDuration(duration);
                    
                    //Get all of the blood appointments booked for that day 
                    rs = stmt.executeQuery("SELECT * FROM blood WHERE Date ='" + date + "'");   
                    while(rs.next())
                    {
                        LocalTime time = LocalTime.parse(rs.getString("Time"));
                        appointments.add(new schedule(time, duration));
                    }
                    
                    LocalTime dayStart = LocalTime.parse("07:00");
                    int durationBoxes = duration/5; //number of boxes needed to show the duration of an appointment 
                    
                    //Block out the blood clinic break for that staff member 
                    LocalTime breakStart = LocalTime.parse(breakStartTime);
                    LocalTime breakEnd = LocalTime.parse(breakEndTime);
                    
                    long minutesToStart = ChronoUnit.MINUTES.between(dayStart, breakStart); 
                    int arrayStart = (int) minutesToStart/5; 
                    
                    long minutesToEnd = ChronoUnit.MINUTES.between(dayStart, breakEnd); 
                    int arrayEnd = (int) minutesToEnd/5; 
                    
                    for(int j=arrayStart; j<arrayEnd-1; j++)
                    {
                        scheduleSTAFF[index][j] = true;
                    }
                    
                    //Block out appointments - both staff member and location 
                    for(int j=0; j<appointments.size(); j++)
                    {                        
                        long minutesBetween = ChronoUnit.MINUTES.between(dayStart, appointments.get(j).getTime()); 
                        int arraySpace = (int) minutesBetween/5; //the start of appointment (the index in the array)
                                                
                        for(int k=arraySpace; k<(arraySpace+durationBoxes); k++) 
                        {
                            scheduleSTAFF[index][k] = true;
                            scheduleLOCATION[15][k] = true;
                        }
                    }
                }
                catch (SQLException e)
                {
                    //Logger.getLogger(SearchProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
    
    //Update the duration of the blood appointments to be the one specific to that day - had used the general 
    public void updateBloodDuration(int newDuration)
    {
        for(int i=0; i<nonbedProcedures.size(); i++)
        {
            if(nonbedProcedures.get(i).getName().equals("Blood"))
            {
                nonbedProcedures.get(i).setDuration(newDuration);
            }
        }
    }
   
    
    //Block out the times that there are oncology appointments scheduled for the specific person working in that area 
    public void oncology()
    {
        ArrayList<schedule> appointments = new ArrayList<schedule>();
        
        for(int i=0; i<specificStaff.size(); i++)
        {
            if(specificStaff.get(i).getLocation().equals("Oncology"))
            {
                //Get index of staff member working in oncology for that day 
                int index = findIndex(specificStaff.get(i).getID());
                
                try
                {
                    Connection c = DatabaseConnector.activateConnection();
                    c.setAutoCommit( true ); 
                    ResultSet rs ;
                    Statement stmt = c.createStatement();

                    //Get all the oncology appointments booked for that day 
                    rs = stmt.executeQuery("SELECT * FROM oncology WHERE Date ='" + date + "'");
                    while(rs.next())
                    { 
                        String time = rs.getString("Time");
                        LocalTime Time = LocalTime.parse(time);
                        
                        appointments.add(new schedule(Time, 60));   //all set to last 1 hour
                    }
                    
                    for(int j=0; j<appointments.size(); j++)
                    {
                        LocalTime dayStart = LocalTime.parse("07:00");
                        long minutesBetween = ChronoUnit.MINUTES.between(dayStart, appointments.get(j).getTime()); 
                        int arraySpace = (int) minutesBetween/5;
                        for(int k=arraySpace; k<arraySpace+12; k++) //12 spaces make up an hour 
                        {
                            scheduleSTAFF[index][k] = true;
                            scheduleLOCATION[14][k] = true;
                        }
                    }
                    c.close();
                }
                catch (SQLException e)
                {
                    //Logger.getLogger(SearchProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
    
    
    
     //Block out the times that there are non-bed appointments scheduled for the specific person working in that area 
    public void nonbed()
    {
        ArrayList<schedule> appointments = new ArrayList<schedule>();
        
        for(int i=0; i<specificStaff.size(); i++)
        {
            if(specificStaff.get(i).getLocation().equals("Nonbed"))
            {
                //Get index of staff member working in non-bed for that day 
                int index = findIndex(specificStaff.get(i).getID());
                
                try
                {
                    Connection c = DatabaseConnector.activateConnection();
                    c.setAutoCommit( true ); 
                    ResultSet rs ;
                    Statement stmt = c.createStatement();
                    
                    ResultSet rs2 ;
                    Statement stmt2 = c.createStatement();

                    //Get all non-bed appointments for that day 
                    rs = stmt.executeQuery("SELECT * FROM nonbed WHERE Date ='" + date + "'");  
                    while(rs.next())
                    { 
                        String name = rs.getString("Procedure");
                        String time = rs.getString("Time");
                        LocalTime Time = LocalTime.parse(time);
                        int duration = 0;
                        
                        //Get the duration for that appointment 
                        rs2 = stmt2.executeQuery("SELECT Duration FROM procedures WHERE Name = '" + name + "'"); 
                        if(rs2.next())
                        {
                            duration = rs2.getInt("Duration");
                        }
                        
                        appointments.add(new schedule(Time, duration));   
                    }
                    
                    //Block out for staff and location 
                    for(int j=0; j<appointments.size(); j++)
                    {
                        LocalTime dayStart = LocalTime.parse("07:00");
                        long minutesBetween = ChronoUnit.MINUTES.between(dayStart, appointments.get(j).getTime()); 
                        int arraySpace = (int) minutesBetween/5;
                        
                        int endAdd = appointments.get(j).getDuration()/5;   //the number of spaces to add for that duration 
                        
                        for(int k=arraySpace; k< arraySpace+endAdd ; k++) 
                        {
                            scheduleSTAFF[index][k] = true;
                            scheduleLOCATION[15][k] = true;
                        }
                    }
                }
                catch (SQLException e)
                {
                    //Logger.getLogger(SearchProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
   
    
    //Blocking out staff and locations for the diary appointments - ones that require a bed 
    public void diary()
    {
        ArrayList<schedule> appointments = new ArrayList<schedule>();
       
        try 
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);
            ResultSet rs;
            Statement stmt = c.createStatement();

            //Gettng all of the appointments from the database
            rs = stmt.executeQuery("SELECT * FROM diary WHERE Date ='" + date + "'"); 
            while (rs.next()) 
            {
                String time = rs.getString("Time");
                LocalTime Time = LocalTime.parse(time);
                
                String procedure = rs.getString("Speciality");
                int duration = findDuration(procedure);
                
                schedule x = new schedule(Time, duration);
                x.setBedNumber(rs.getString("BedNumber"));
                x.setName(procedure);
        
                appointments.add(x);
            }
            
            bedAppointments = appointments;

            //Blocking out the bed space for these appointments 
            for (int j = 0; j < appointments.size(); j++) 
            {
                LocalTime dayStart = LocalTime.parse("07:00");
                long minutesBetween = ChronoUnit.MINUTES.between(dayStart, appointments.get(j).getTime());
                int arraySpace = (int) minutesBetween / 5;
                
                int index = getArrayIndex(appointments.get(j).getBedNumber());

                int endAdd = appointments.get(j).getDuration() / 5;   //the number of spaces to add for that duration 

                for (int k = arraySpace; k < arraySpace + endAdd && k<145; k++) 
                {
                    scheduleLOCATION[index][k] = true;
                }
            }
            
            blockOutStaff(appointments);
            
        } 
        catch (SQLException e) 
        {
            //Logger.getLogger(SearchProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }    
    }
    
    
    //Blocking out staff for the diary appointments 
    public void blockOutStaff(ArrayList<schedule> appointments)
    {
        //if there is at least 1 surgery in a room, assigns 1 nurse and then stops looking at that room 
        blockOutStaffSurgery(appointments, "MA", "07:30");
        blockOutStaffSurgery(appointments, "LA", "07:30");
        blockOutStaffSurgery(appointments, "MP", "13:30");
        blockOutStaffSurgery(appointments, "LP", "13:30");
        
        multiples();
        blockOutStaffOther(appointments);
    }
    
    //Sssigning a nurse to a surgery room  
    public void blockOutStaffSurgery(ArrayList<schedule> appointments, String search, String time)
    {
        int x = 0;
        boolean assigned = false;
        while(x < appointments.size() && !assigned)
        {
            String compare = (appointments.get(x).getBedNumber()).substring(1); //Getting MA/LA/MP/LP from appoitment room
            if(compare.equals(search) && !inProcedures(appointments.get(x).getName())) //if the appointment room matches the search one and it is NOT in the list of proecures 
            {
                assignStaff(LocalTime.parse(time), appointments.get(x).getDuration()); //assign a staff member 
                assigned = true;
            }
            x++;
        }
    }
    
    //Dealing with having 1 nurse to multiple patients - setting up the arrays 
    public void multiples()
    {
        try
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);
            ResultSet rs;
            Statement stmt = c.createStatement();
            
            int nurses=0;
            int patients=0;
            
            rs = stmt.executeQuery("SELECT * FROM procedures"); 
            while (rs.next())
            {
                nurses = rs.getInt("NumberOfNurses");
                patients = rs.getInt("NumberOfPatients");
                
                //If there are multiple patients allowed, records this
                if(patients > 1)
                {
                    multiplePatientsAM.add(new multiple(rs.getString("Name"), 0, patients));
                    multiplePatientsPM.add(new multiple(rs.getString("Name"), 0, patients));
                }
                
            }
            
        }
        catch(SQLException e)
        {
            
        }
    }
    
    //Blocking out staff members for bed appointments that are not surgery 
    public void blockOutStaffOther(ArrayList<schedule> appointments)
    {
        try 
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);
            ResultSet rs;
            Statement stmt = c.createStatement();

            int nurses=0;
            int patients=0;
            
            for(int i=0; i<appointments.size(); i++)
            {
                if(inProcedures(appointments.get(i).getName()))
                {
                    rs = stmt.executeQuery("SELECT * FROM procedures WHERE Name ='" + appointments.get(i).getName() + "'"); 
                    while (rs.next())
                    {
                        nurses = rs.getInt("NumberOfNurses");
                        patients = rs.getInt("NumberOfPatients");
                    }
                    
                    appointments.get(i).setNurse(nurses);
                    appointments.get(i).setPatient(patients);
                    
                    //If there is multiple staff to an appointment, blocks off that many 
                    for(int j=0; j<nurses; j++)
                    {
                        assignStaff(appointments.get(i).getTime(), appointments.get(i).getDuration());
                    }
                    
                        //If there are multiple patients allowed, records this
                        if(appointments.get(i).getTime().isBefore(LocalTime.parse("12:00")))
                        {
                            int index = inList(appointments.get(i).getName(), "AM");
                            if(index != -1)
                            {
                                multiplePatientsAM.get(index).setCount(multiplePatientsAM.get(index).getCount()+1);
                            }
                        }
                        else
                        {
                            int index = inList(appointments.get(i).getName(), "PM");
                            if(index != -1)
                            {
                                multiplePatientsPM.get(index).setCount(multiplePatientsPM.get(index).getCount()+1);
                            }
                        }
                    
                }
            }
        }
        catch(SQLException e)
        {
            
        }
    }
    
    
    //finds if a procedure is in the list - if it is not, then it is a surgery appointment 
    public boolean inProcedures(String name)
    {
        for(int i=0; i<bedProcedures.size(); i++)
        {
            if(bedProcedures.get(i).getName().equals(name))
            {
                return true;
            }
        }
        return false;
    }
    
    
    //checks to see if a proceuder that allows for multiple patients is already in the this - gives its index if it is 
    public int inList(String name, String time)
    {
        if(time.equals("AM"))
        {
            for(int i=0; i<multiplePatientsAM.size(); i++)
            {
                if(multiplePatientsAM.get(i).getName().equals(name))
                {
                    return i;
                }
            }
            return -1;
        }
        else
        {
            for(int i=0; i<multiplePatientsPM.size(); i++)
            {
                if(multiplePatientsPM.get(i).getName().equals(name))
                {
                    return i;
                }
            }
            return -1;
        }
        
    }
    
    
    //block off staff - first looking at unassigned, and then checking for spaces in assigned 
    public void assignStaff(LocalTime startTime, int duration)
    {
        boolean assigned = false;
        int staffID;
        
        //getting the array points set 
        LocalTime dayStart = LocalTime.parse("07:00");
        long minutesToStart = ChronoUnit.MINUTES.between(dayStart, startTime); 
        int arrayStart = (int) minutesToStart/5; 
        int arrayDuration = duration / 5;

        //go through all the unassignedStaff
        for (int j = 0; j < unassignedStaff.size(); j++) 
        {
            if (!assigned) // as long as it hasnt already been assigned to staff member
            {
                staffID = findIndex(unassignedStaff.get(j));//get the staff ID index
                
                //if an unassiged staff member has space, block that off and say it has been assigned 
                if (fits(staffID, arrayStart, arrayDuration)) 
                {
                    //If they have space, block it out
                    for (int x = arrayStart; x < arrayStart + arrayDuration; x++) 
                    {
                        scheduleSTAFF[staffID][x] = true;
                        assigned = true;
                    }
                }
            }
        }
       
        if(!assigned) // as long as it hasnt already been assigned to staff member
        {
            for (int j = 0; j < specificStaff.size(); j++) 
            {
                if (!assigned) // as long as it hasnt already been assigned to staff member
                {
                    staffID = findIndex(specificStaff.get(j).getID());//get the staff ID index 

                    //if they have space, block it out 
                    if(fits(staffID, arrayStart, arrayDuration))
                    {
                        for(int x=arrayStart; x<arrayStart+arrayDuration; x++)
                        {
                            scheduleSTAFF[staffID][x] = true;
                            assigned = true;
                        }
                    }//end of assigned fit
                }
            }
        }
        
        if (!assigned) //still not assigned as noone has full clear space - assign to most clear
        {
            int mostEmpty = findMostFreeStaff(arrayStart, arrayDuration);

            for (int k = arrayStart; k < (arrayStart + arrayDuration) && k<145; k++) 
            {
                scheduleSTAFF[mostEmpty][k] = true;
            }
        }
    }//end of method

    
    
    //finds the member of staff who has the most free spaces in that time period 
    public int findMostFreeStaff(int arrayStart, int arrayDuration)
    {
        int highestCount = 0;
        int highestIndex = 0;
        
        for(int i=0; i<workingStaff.size(); i++)
        {
            int count = 0;
            for(int j=arrayStart; j<arrayStart+arrayDuration && j<145; j++)
            {
                if(!scheduleSTAFF[i][j])
                {
                    count++;
                }
            }
            if(count > highestCount)
            {
                highestCount = count;
                highestIndex = i;
            }
        }
        
        return highestIndex;
    }
    
    //determins if a staff member is fully free for that peroid of time 
    public boolean fits(int staffID, int arrayStart, int arrayDuration)
    {
        for(int k=arrayStart; k<arrayStart+arrayDuration; k++)
        {
            if(scheduleSTAFF[staffID][k])
            {
                return false;

            }
        }
        return true;
    }
    
    //find the duration of a bed procedure 
    public int findDuration(String procedure)
    {
        for(int i=0; i<bedProcedures.size(); i++)
        {
            if(procedure.equals(bedProcedures.get(i).getName()))
            {
                return bedProcedures.get(i).getDuration();
            }
        }
        //if not in list, must be a surgery so staff for 6 hours with is 360 mins
        return 360;   
    }
    
    
    //given a bed number, gives the index of that bed in the schedule array
    public int getArrayIndex(String bedNumber)
    {
        String search = bedNumber.substring(0, 2);
        
        for(int i=0; i<locations.length; i++)
        {
            if(search.equals(locations[i]))
            {
                return i;
            }
        }
        return -1;
    }
    
    
    //Finds the index of a specific member of staff given their ID
    public int findIndex(int ID)
    {
        for(int i=0; i<workingStaff.size(); i++)
        {
           if(workingStaff.get(i) == ID)
           {
               return i;
           }
        }
        return -1;
    }
    
    
    
    //-----------------------------------------------------------------------------------------------------------------------------
    
    
    
    //determines if non-bed or bed procedure and then calls the method to make that suggestion 
    public void suggest(String procedure, LocalDate date) 
    {
        //You are wanting to make a suggestion for a non-bed procedure
        for (int i = 0; i < nonbedProcedures.size(); i++) 
        {
            if (nonbedProcedures.get(i).getName().equals(procedure)) 
            {
                //Find index of location for that non-bed proceudre 
                int index = findLocationIndex(procedure);                
                makeSuggestions(index, nonbedProcedures.get(i).getDuration(), date, procedure, nonbedProcedures.get(i).getNurses());
            }
        }
        
        //You are wanting to make a suggestion for a bed proceudre 
        for (int i = 0; i < bedProcedures.size(); i++) 
        {
            if (bedProcedures.get(i).getName().equals(procedure)) 
            {  
                //Oncology is bed but has it own location index 
                if(procedure.equals("Oncology"))
                {
                    makeSuggestions(14, bedProcedures.get(i).getDuration(), date, procedure, bedProcedures.get(i).getNurses());
                }
                else
                {
                    makeSuggestions(-1, bedProcedures.get(i).getDuration(), date, procedure, bedProcedures.get(i).getNurses());
                }
            }
        }

        
    }
    
    //makes the actual suggestions
    public void makeSuggestions(int index, int duration, LocalDate date, String name, int nurses)
    {
        if(index == 12) //BLOOD
        {
           findEmptyAppointments(date);
        }
        else if(index ==13) //PREOP
        {
            for(int j=0; j<workingStaff.size(); j++)
            {
                for(int i=0; i<145; i=i+6) //look at 30 minute intervals 
                {
                    //Check when any staff member has 30 minutes free to do a pre-op 
                    if(checkIfSpace(j, i, 30))
                    {
                        LocalTime time = LocalTime.parse("07:00").plusMinutes(i*5);
                        results.add(new result(date, time));
                    }
                }
            }
        }
        else if(index == 14) //ONCOLOGY 
        {
            for(int i=0; i<145; i=i+6) //look at 30 minute intervals 
            {
                //Check when room is free for 1 hour - staff member working in that specific area will cover it 
                if(checkIfSpace(index, i, 60))
                {
                    LocalTime time = LocalTime.parse("07:00").plusMinutes(i*5);
                    results.add(new result(date, time));
                    i = i + 6; //moving forward 1 hour 
                }
            }
        }
        else if(index == 15) //NON-BED
        {
            //Check when room is free duration - staff member working in that specific area will cover it 
            for(int i=0; i<145; i=i+2) //looks at 10 minute intervals 
            {
                if(checkIfSpace(index, i, duration))
                {
                    LocalTime time = LocalTime.parse("07:00").plusMinutes(i*5);
                    results.add(new result(date, time));
                    i = ((i + (duration/5)) - 2); //moves forward by that duration 
                }
            }
        }
        else if(index == -1) //ANY OTHER BED PROCEDURE 
        {
            //Determines if the searched for procedure allows for multiple patients to one nurse - gives index of that procedure in multiples list 
            int MPIndexAM = inList(name, "AM");
            int MPIndexPM = inList(name, "PM");
            
            //The procedure does allow for multiples 
            if(MPIndexAM != -1 || MPIndexPM != -1)
            {
                //AM MULTPLE
                if(MPIndexAM != -1)
                {
                    boolean done = false;
                    LocalTime startTime;
                    
                    //If there are no appointments already booked check for staff avilability  
                    if(multiplePatientsAM.get(MPIndexAM).getCount() == 0)
                    {
                        for(int j=0; j<scheduleSTAFF.length; j++)
                        {
                            for(int k = 24; k<145; k++)
                            {
                                if(!done)
                                {
                                    int boxesBooked = timeAlreadyBooked(j, k, duration);
                                    if(boxesBooked <= ((duration/5)/5)) //allow staff member to be booked for 1/5 of the duration to still be assigned it  
                                    {
                                        if(hasSkill(workingStaff.get(j)) && freeBed(k, duration))  //check the staff member has the skill and there is a free bed 
                                        {  
                                            startTime = LocalTime.parse("07:00").plusMinutes(k*5);
                                            for(int i = 0; i<multiplePatientsAM.get(MPIndexAM).getMaximum(); i++)
                                            {
                                                results.add(new result(date, startTime));
                                                startTime = startTime.plusMinutes(15);
                                            }
                                            done = true; 
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else
                    {   
                        //Some appointments are already booked - find 15mins after the latest currently booked appointments 
                        startTime = calculateTime(name, "AM");
                        long minutesBetween = ChronoUnit.MINUTES.between(LocalTime.parse("07:00"), startTime);
                        int startPlace = (int) minutesBetween / 5;
                        startTime = startTime.plusMinutes(15);
                        
                        //Make suggestions every 15 mins until you reach the maximum 
                        for(int i = multiplePatientsAM.get(MPIndexAM).getCount(); i<multiplePatientsAM.get(MPIndexAM).getMaximum(); i++)
                        {
                            //check the bed is free 
                            if(freeBed(startPlace, duration))
                            {
                                results.add(new result(date, startTime));
                                startTime = startTime.plusMinutes(i * 15);
                            }
                        }
                    }
                }

                //PM MULTIPLE - same structure as AM 
                if(MPIndexPM != -1)
                {
                    boolean done = false;
                    LocalTime startTime;
                    
                    //If there are no appointments already booked check for staff avilability  
                    if(multiplePatientsPM.get(MPIndexPM).getCount() == 0)
                    {
                        for(int j=0; j<scheduleSTAFF.length; j++)
                        {
                            for(int k = 78; k<145; k++)
                            {
                                if(!done)
                                {
                                    int boxesBooked = timeAlreadyBooked(j, k, duration);
                                    if(boxesBooked <= ((duration/5)/5)) //allow staff member to be booked for 1/5 of the duration to still be assigned it  
                                    {
                                        if(hasSkill(workingStaff.get(j)) && freeBed(k, duration))  //check the staff member has the skill and there is a free bed 
                                        {  
                                            startTime = LocalTime.parse("07:00").plusMinutes(k*5);
                                            for(int i = 0; i<multiplePatientsPM.get(MPIndexPM).getMaximum(); i++)
                                            {
                                                results.add(new result(date, startTime));
                                                startTime = startTime.plusMinutes(15);
                                            }
                                            done = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        //Some appointments are already booked - find 15mins after the latest currently booked appointments
                        startTime = calculateTime(name, "PM");
                        long minutesBetween = ChronoUnit.MINUTES.between(LocalTime.parse("07:00"), startTime);
                        int startPlace = (int) minutesBetween / 5;
                        startTime = startTime.plusMinutes(15);
                        
                        //Make suggestions every 15 mins until you reach the maximum 
                        for(int i = multiplePatientsPM.get(MPIndexPM).getCount(); i<multiplePatientsPM.get(MPIndexPM).getMaximum(); i++)
                        {
                            //Check if bed is free
                            if(freeBed(startPlace, duration))
                            {
                                results.add(new result(date, startTime));
                                startTime = startTime.plusMinutes(i * 15);
                            }
                        }
                    }
                    
                }
            }
            else if(nurses == 1) //When there is only 1 nurse to 1 patient 
            {
                //Find if staff free - check at 10 minute intervals 
                for(int i=0; i<145; i=i+2) 
                {
                    for(int j=0; j<scheduleSTAFF.length; j++)
                    {
                        if(checkIfStaff(j, i, duration))  //Free
                        {
                            if(hasSkill(workingStaff.get(j))) //Skill
                            {
                                if(freeBed(i, duration)) //Free bed 
                                {
                                    LocalTime time = LocalTime.parse("07:00").plusMinutes(i*5);
                                    results.add(new result(date, time));
                                    i = (i+duration)-2; //moving forward by the duration
                                }
                            }
                        }
                    }
                }   
            }
            else
            {
                //SCHEDULING FOR MANY NURSE - TO - 1 PATIENT RELATIONSHIP 
                for(int i=0; i<145; i=i+2) //look at 10 minute intervals 
                {
                    int count = 0;
                    //Count how many staff are free that have the needed skill 
                    for(int j=0; j<scheduleSTAFF.length; j++)
                    {
                        if(checkIfStaff(j, i, duration) && hasSkill(workingStaff.get(j)))  
                        {
                            count++;
                        }
                    }
                    //If you have enough free nurses, make the suggestion 
                    if(count >= nurses)
                    {
                        for(int j=0; j<nurses; j++)
                        {
                            //Check if bed is free 
                            if (freeBed(i, duration)) 
                            {
                                LocalTime time = LocalTime.parse("07:00").plusMinutes(i * 5);
                                results.add(new result(date, time));
                                i = (i + duration) - 2; //moving forward by the duration
                            }
                        }
                    }
                }      
            }
        }//end of bed scheduling 
    }
    
    
    //Calculate how long the staff member is already booked for within a duration - returns number of array boxes set to true 
    public int timeAlreadyBooked(int row, int startPosition, int duration)
    {
        int count = 0;
        int numberOfBoxes = duration / 5;
          
        for(int i=startPosition; i<(startPosition+numberOfBoxes) && i<145; i++)
        {
            if(scheduleSTAFF[row][i])
            {
                count++;
            }
        }
        return count;
    }
    
    //Find the latest appointment time already for a procedure that allows for multiple patients 
    public LocalTime calculateTime(String name, String time)
    {
        LocalTime latest;
        
        if(time.equals("AM"))
        {
            latest = LocalTime.parse("09:00");
            for(int i=0; i<bedAppointments.size(); i++)
            {
                if(bedAppointments.get(i).getName().equals(name))
                {
                    if(bedAppointments.get(i).getTime().isAfter(latest) && bedAppointments.get(i).getTime().isBefore(LocalTime.parse("12:00")))
                    {
                        latest = bedAppointments.get(i).getTime();
                    }
                }
            }
        }
        else
        {
            latest = LocalTime.parse("13:00");
            
            for(int i=0; i<bedAppointments.size(); i++)
            {
                if(bedAppointments.get(i).getName().equals(name))
                {
                    if(bedAppointments.get(i).getTime().isAfter(latest))
                    {
                        latest = bedAppointments.get(i).getTime();
                    }
                }
            }
        }
        
        return latest;
    }
    
    
    
    
    //Method to check if a bed has space 
    public boolean checkIfSpace(int row, int startPosition, int duration)
    {
        int numberOfBoxes = duration / 5;
          
        for(int i=startPosition; i<(startPosition+numberOfBoxes); i++)
        {
            //Ensure you don't go outside the array 
            if(i >= 145)
            {
                return false;
            }
            else
            {
                if(scheduleLOCATION[row][i])
                {   
                    return false;
                }
            }
        }
        return true;
    }
    
    //Method to check if the staff memeber is free 
    public boolean checkIfStaff(int row, int startPosition, int duration)
    {
        int numberOfBoxes = duration / 5;
          
        for(int i=startPosition; i<(startPosition+numberOfBoxes) && i<145; i++)
        {
            if(scheduleSTAFF[row][i])
            {
                return false;
            }
        }
        return true;
    }
    
    
    //Tells if the staff member has the skill you are trying to schedule for 
    public boolean hasSkill(int staffID)
    {
        try
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            Statement stmt = c.createStatement();
            
            rs = stmt.executeQuery("SELECT * FROM skill WHERE Staff_ID ='" + staffID + "'"); //get all the skills of a specific staff member 
            while(rs.next())
            { 
                if(rs.getString("Procedure_Name").equals(cmbSearchProcedure.getValue().toString()))
                {
                    c.close();
                    return true;
                }
            }
            c.close();
            
        }
        catch(SQLException e)
        {
            //Logger.getLogger(SearchProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
    
    
    //Tells you if you have a bed free to be able to suggest an appointment
    public boolean freeBed(int start, int duration)
    {
        for(int i=0; i<11; i++) //not looking at the extra beds so not 4 or 12
        {
            if(i!=4)
            {
                if(checkIfSpace(i, start, duration))
                {
                    return true;
                }
            }
        }
        return false;
    }
                            
    //Gives you the index of the non-bed procedure location
    public int findLocationIndex(String procedure)
    {
        switch(procedure)
        {
            case "Blood": return 12;
            case "Pre-op": return 13;
            case "Oncology": return 14;
        }
        return 15;  
    }
    
    
    
    
    //Method used to find the blood clinic appointments that have not been booked 
    public void findEmptyAppointments(LocalDate date)
    {
        ArrayList<result> allTimes = new ArrayList<result>();
        ArrayList<result> allAppointments = new ArrayList<result>();
        
        String startTimeS="";
        String endTimeS="";
        int duration = 0;
        String breakStartTimeS="";
        String breakEndTimeS="";
        
        String Date = codeBank.dateToString(date);
        String Day = date.getDayOfWeek().name();
         
        try 
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);
            ResultSet rs;
            Statement stmt = c.createStatement();
            
            //If there is no data from extra, or extra is set to 1 for blod 
            rs = stmt.executeQuery("SELECT * FROM extra WHERE Date = '" + Date + "'");
            if(!rs.isBeforeFirst() || rs.getInt("Blood") == 1)
            {
                //Get template by date 
                rs = stmt.executeQuery("SELECT * FROM template WHERE Day ='" + Date + "'");   
                if (rs.next()) 
                {
                    startTimeS = rs.getString("Start");
                    endTimeS = rs.getString("End");
                    duration = rs.getInt("Duration");
                    breakStartTimeS = rs.getString("BreakStart");
                    breakEndTimeS = rs.getString("BreakEnd");
                } 
                else 
                {
                    //Get template by day that is active 
                    rs = stmt.executeQuery("SELECT * FROM template WHERE Day ='" + Day + "' AND ToDate IS NULL");  
                    if (rs.next()) 
                    {
                        startTimeS = rs.getString("Start");
                        endTimeS = rs.getString("End");
                        duration = rs.getInt("Duration");
                        breakStartTimeS = rs.getString("BreakStart");
                        breakEndTimeS = rs.getString("BreakEnd");
                    }
                    else
                    {
                        return;//there is not blood clinic found 
                    }
                }


                LocalTime startTime = LocalTime.parse(startTimeS, DateTimeFormatter.ISO_LOCAL_TIME);
                LocalTime endTime = LocalTime.parse(endTimeS, DateTimeFormatter.ISO_LOCAL_TIME);
                LocalTime breakStart = LocalTime.parse(breakStartTimeS, DateTimeFormatter.ISO_LOCAL_TIME);
                LocalTime breakEnd = LocalTime.parse(breakEndTimeS, DateTimeFormatter.ISO_LOCAL_TIME);

                result x = new result(date, startTime);
                LocalTime previousTime = startTime;
                allTimes.add(x);

                //Get an arraylist with object with all of the possible times for the blood clinic 
                while (previousTime != endTime && previousTime.compareTo(endTime)!= 1) 
                {
                    previousTime = previousTime.plusMinutes(duration);

                    if (previousTime.compareTo(breakStart) < 0) 
                    {
                        x = new result(date, previousTime);
                        allTimes.add(x);
                    } 
                    else if (previousTime.compareTo(breakEnd) >= 0) 
                    {
                        x = new result(date, previousTime);
                        allTimes.add(x);
                    }
                }

                //Get all the blood appointments for that day 
                rs = stmt.executeQuery("SELECT * FROM blood WHERE Date ='" + Date + "'");   
                while(rs.next()) 
                {
                    String time = rs.getString("Time");
                    LocalTime Time = LocalTime.parse(time);
                    x = new result(date, Time);
                    allAppointments.add(x);
                }

                //Create a list of all of the free appointments - all times that dont match with appointments 
                boolean match = false;
                for(int i=0; i<allTimes.size(); i++)
                {
                    match = false;
                    for(int j=0; j<allAppointments.size(); j++)
                    {
                        if(allTimes.get(i).getTime().compareTo(allAppointments.get(j).getTime())==0)
                        {
                            match = true;
                        }
                    }
                    if(!match)
                    {
                        results.add(allTimes.get(i));
                    }
                }
            }
            c.close();
        }
        catch(SQLException e)
        {
            //Logger.getLogger(SearchProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    //Shows the suggested appointments in the table
    public void displayResult()
    {
        tblSearchResult.getItems().addAll(results);
        tblColDate.setCellValueFactory(new PropertyValueFactory("DateString"));
        tblColTime.setCellValueFactory(new PropertyValueFactory("Time"));
     
    }
    
    
    //Ensures that the correct help file is shown when you press ?
    private HelpDialogController HDC;
    private Pane Hx;
    
    @FXML
    public void help()
    {
        try 
        {    
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Help");
            
            FXMLLoader DL = new FXMLLoader(getClass().getResource("/common/HelpDialog.fxml"));   
            
            Hx = DL.load(); //ISSUE
            HDC = DL.getController();
            
            HDC.show("ProcedureSearch");
            
            final Scene scene = new Scene(Hx, 795, 876);
            stage.setScene(scene);
            stage.setOnHidden(e -> HDC.shutdown());
            stage.show();
                      
        } 
        catch (IOException ex) 
        {
            //Logger.getLogger(ProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
   
}
