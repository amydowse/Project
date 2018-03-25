package schedule;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
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
    @FXML TableColumn tblColBook = new TableColumn();
    
    @FXML Hyperlink hlHlep = new Hyperlink();
    
    ObservableList searchResult = FXCollections.observableArrayList();
    
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
    int foodchallangeCount = 0;
    
    ObservableList results = FXCollections.observableArrayList();
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        fillDropDown();
        ToggleGroup group = new ToggleGroup();
        rb1W.setToggleGroup(group);
        rb2W.setToggleGroup(group);
        rb3W.setToggleGroup(group);
        rb4W.setToggleGroup(group);
    }
    
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
            
            cmbSearchProcedure.getItems().addAll(procedures);
            
        }
        catch(SQLException e)
        {
            
        }
    }
    
    @FXML
    public void search()
    {
        findWhatIsOn(codeBank.stringToDate("23/03/2018"));
        System.out.println("STEP 1");
        
        for(int i=0; i<scheduleSTAFF.length; i++)
        {
            for(int j=0; j<145; j++)
            {
                System.out.print("(" + j  +")" + scheduleSTAFF[i][j] + "-");
            }
            System.out.println("");
        }
        
        suggest(cmbSearchProcedure.getValue().toString(), LocalDate.now());
        System.out.println("STEP 2");
        displayResult();
        System.out.println("STEP 3");
    }
    
    
    
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
                    System.out.println("1");
                    if(rs.getInt("Blood") == 0)
                    {
                        fill(Date, 1, 0, 1, 1, 1);
                    }
                }
                else
                {
                    rs = stmt.executeQuery("SELECT * FROM template WHERE Day ='" + date + "'"); //find blood clinic by date 
                    if(rs.isBeforeFirst())
                    {
                        System.out.println("2");
                        fill(Date, 1, 1, 1, 1, 1);
                    }
                    else
                    {
                        rs = stmt.executeQuery("SELECT * FROM template WHERE Day ='" + day + "' AND ToDate IS NULL");//find blood clinic by day
                        if(rs.isBeforeFirst())
                        {
                            System.out.println("3");
                            fill(Date, 1, 1, 1, 1, 1);
                        }
                        else
                        {
                            System.out.println("4");
                            fill(Date, 1, 0, 1, 1, 1);
                        }
                    }
                }

            }
            else
            {
                rs = stmt.executeQuery("SELECT * FROM extra WHERE Date ='" + date + "'"); //get all the staff working that day and their shifts 
                while(rs.next())
                {
                    System.out.println("5");
                    fill(Date, rs.getInt("Diary"), rs.getInt("Blood"), rs.getInt("Preop"), rs.getInt("Oncology"), rs.getInt("Nonbed"));
                }
            }
        }
        catch(SQLException e)
        {
            Logger.getLogger(SearchProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    
    
    public void fill(LocalDate Date, int Diary, int Blood, int Preop, int Oncology, int Nonbed)
    {
        //----------------------------
        //TRUE = BUSY
        //FALSE = FREE
        //----------------------------
        
        //Data structures to use 
        date = codeBank.dateToString(Date);
        workingStaff.clear();
        shift.clear();
        specificStaff.clear();
        unassignedStaff.clear();
        bedProcedures.clear();
        nonbedProcedures.clear();
        scheduleLOCATION = new boolean[16][145];
        foodchallangeCount = 0;
        
        results.clear();
        tblSearchResult.getItems().clear();
        
        locations = new String[] {"1M", "2M", "3M", "4M", "EM", "1L", "2L", "3L", "4L", "5L", "6L", "EL"};
        
        //Getting information about staff working, shifts and their specific locations 
        try
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            Statement stmt = c.createStatement();
            
            rs = stmt.executeQuery("SELECT * FROM working WHERE Date ='" + date + "'"); //get all the staff working that day and their shifts 
            while(rs.next())
            { 
                workingStaff.add(rs.getInt("Staff_ID"));
                shift.add(rs.getString("Shift"));
            }
            
            rs = stmt.executeQuery("SELECT * FROM specificworking WHERE Date ='" + date + "'"); //get the staff who have a specific location
            while(rs.next())
            { 
                int ID = rs.getInt("ID");
                String location = rs.getString("Place");
                specificStaff.add(new specificWorking(ID, location));
            }
            
            rs = stmt.executeQuery("SELECT Name, Duration FROM procedures WHERE Location ='Bed'"); //get all the bed proceudres 
            while(rs.next())
            { 
                procedure x = new procedure(rs.getString("Name"), rs.getInt("Duration"));
                bedProcedures.add(x);
            }
            
            rs = stmt.executeQuery("SELECT Name, Duration FROM procedures WHERE Location ='Non-bed'"); //get all the bed proceudres 
            while(rs.next())
            { 
                procedure x = new procedure(rs.getString("Name"), rs.getInt("Duration"));
                nonbedProcedures.add(x);
            }
            c.close();           
        }
        catch (SQLException e)
        {
            Logger.getLogger(SearchProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
        
        System.out.println("ARRAY SIZES-------------");
        System.out.println(workingStaff.size());
        System.out.println(shift.size());
        System.out.println(specificStaff.size());
        System.out.println(bedProcedures.size());
        System.out.println(nonbedProcedures.size());
        
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
        
        System.out.println("UN: " + unassignedStaff.size());
        
        //Setting up the schedule for the staff working that day
        scheduleSTAFF = new boolean[workingStaff.size()][145]; //the number of 5 min slots in 7:00-7:00 (0-144)
        
        //setting the times to match the shift - no LD option as they work 7-7
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
        
        //marking off breaks
        breaks();
               
        if(Diary == 1)
        {
            diary();
        }
        else
        {
            remove(-1);
        }
        
        if(Blood == 1 && inSpecificWorking("Blood"))
        {
            blood();
        }
        else
        {
            remove(12);
        }
        
        if(Preop == 1 && inSpecificWorking("Preop"))
        {
            preop();
        }
        else
        {
            remove(13);
        }
        
        if(Oncology == 1 && inSpecificWorking("Oncology"))
        {
            oncology();
        }
        else
        {
            remove(14);
        }
        
        if(Nonbed == 1 && inSpecificWorking("Nonbed"))
        {
            nonbed();
        }
        else
        {
            remove(15);
        }
    }
    
    //if that area isnt on offer, set it to 'true' so it seems fully booked so not scheduling is done 
    public void remove(int index)
    {
        if(index == -1)
        {
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
        System.out.println(">>>>>>>>>BREAKS");
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
    
    
    
    
    
    
    
    
    
    //SPLIT ACROSS AM AND PM
    //block out the times that there are pre-ops scheduled for the specific person working in that area 
    public void preop()
    {
        ArrayList<schedule> appointments = new ArrayList<schedule>();
        int AMStaff = -1;
        int PMStaff = -1;
        
        for(int i=0; i<specificStaff.size(); i++)
        {
            if(specificStaff.get(i).getLocation().equals("PreopAM"))
            {
                System.out.println("AM PREOP HIT");
                AMStaff = findIndex(specificStaff.get(i).getID());
            }        
            if(specificStaff.get(i).getLocation().equals("PreopPM") )
            {
                System.out.println("PM PREOP HIT");
                PMStaff = findIndex(specificStaff.get(i).getID());
            }
            
            try 
            {
                Connection c = DatabaseConnector.activateConnection();
                c.setAutoCommit(true);
                ResultSet rs;
                Statement stmt = c.createStatement();

                rs = stmt.executeQuery("SELECT * FROM preop WHERE Date ='" + date + "'"); //get all the pre-op appoinments for that day  
                while (rs.next()) 
                {
                    String time = rs.getString("Time");
                    LocalTime Time = LocalTime.parse(time);

                    appointments.add(new schedule(Time, 30));
                }

                int index;
                for (int j = 0; j < appointments.size(); j++) 
                {
                    if(appointments.get(j).getTime().isBefore(LocalTime.parse("12:00")))
                    {
                        index = AMStaff;
                    }
                    else
                    {
                        index = PMStaff;
                    }

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
            catch (SQLException e) 
            {
                Logger.getLogger(SearchProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        
    }


    
 
    
    
    
    
    
    
    
    
    
    
    
    //blocks out the booked appointments 
    public void blood()
    {
        System.out.println("INSIDE BLOOD");
        
        ArrayList<schedule> appointments = new ArrayList<schedule>();
        
        int duration = 0;
        String breakStartTime = "";
        String breakEndTime = "";
        
        String day = (codeBank.stringToDate(date)).getDayOfWeek().name();
        
        for(int i=0; i<specificStaff.size(); i++)
        {
            if(specificStaff.get(i).getLocation().equals("Blood"))
            {
                int index = findIndex(specificStaff.get(i).getID());
                
                try
                {
                    Connection c = DatabaseConnector.activateConnection();
                    c.setAutoCommit( true ); 
                    ResultSet rs ;
                    Statement stmt = c.createStatement();

                    rs = stmt.executeQuery("SELECT * FROM template WHERE Day ='" + date + "'"); //get all the pre-op appoinments for that day  
                    if(rs.isBeforeFirst())
                    { 
                        duration = rs.getInt("Duration");
                        breakStartTime = rs.getString("BreakStart");
                        breakEndTime = rs.getString("BreakEnd");
                    }
                    else
                    {
                        rs = stmt.executeQuery("SELECT * FROM template WHERE Day ='" + day + "' AND ToDate = 'null'"); //get all the pre-op appoinments for that day  
                        if(rs.isBeforeFirst())
                        { 
                            duration = rs.getInt("Duration");
                            breakStartTime = rs.getString("BreakStart");
                            breakEndTime = rs.getString("BreakEnd");
                        }
                    }
                    
                    updateBloodDuration(duration);
                    
                    rs = stmt.executeQuery("SELECT * FROM blood WHERE Date ='" + date + "'"); //get all the blood appoinments for that day  
                    while(rs.next())
                    {
                        LocalTime time = LocalTime.parse(rs.getString("Time"));
                        appointments.add(new schedule(time, duration));
                    }
                    
                    System.out.println("APPPP " + appointments.size());
                    
                    LocalTime dayStart = LocalTime.parse("07:00");
                    int durationBoxes = duration/5;
                    
                    //block out break
                    LocalTime breakStart = LocalTime.parse(breakStartTime);
                    LocalTime breakEnd = LocalTime.parse(breakEndTime);
                    
                    long minutesToStart = ChronoUnit.MINUTES.between(dayStart, breakStart); 
                    int arrayStart = (int) minutesToStart/5; 
                    
                    long minutesToEnd = ChronoUnit.MINUTES.between(dayStart, breakEnd); 
                    int arrayEnd = (int) minutesToEnd/5; 
                    
                    for(int j=arrayStart; j<arrayEnd-1; j++)
                    {
                        System.out.print("BLOODB ");
                        scheduleSTAFF[index][j] = true;
                    }
                    
                    //block out appointments
                    for(int j=0; j<appointments.size(); j++)
                    {                        
                        long minutesBetween = ChronoUnit.MINUTES.between(dayStart, appointments.get(j).getTime()); 
                        int arraySpace = (int) minutesBetween/5; //the start of appointment (the index in the array)
                                                
                        for(int k=arraySpace; k<(arraySpace+durationBoxes); k++) 
                        {
                            System.out.print("BLOOD APPOINTMENT BLOCKED");
                            scheduleSTAFF[index][k] = true;
                            scheduleLOCATION[15][k] = true;
                        }
                    }
                    
                    
                }
                catch (SQLException e)
                {
                    Logger.getLogger(SearchProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
    
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
   
    
    
    
    
    
    
    
    
    
    
    
    //block out the times that there are oncology appointments scheduled for the specific person working in that area 
    public void oncology()
    {
        System.out.println("INSIDE ONCOLOGU");
        ArrayList<schedule> appointments = new ArrayList<schedule>();
        
        for(int i=0; i<specificStaff.size(); i++)
        {
            if(specificStaff.get(i).getLocation().equals("Oncology"))
            {
                int index = findIndex(specificStaff.get(i).getID());
                
                try
                {
                    Connection c = DatabaseConnector.activateConnection();
                    c.setAutoCommit( true ); 
                    ResultSet rs ;
                    Statement stmt = c.createStatement();

                    rs = stmt.executeQuery("SELECT * FROM oncology WHERE Date ='" + date + "'"); //get all the oncology appoinments for that day  
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
                            System.out.print("ONC ");
                            scheduleSTAFF[index][k] = true;
                            scheduleLOCATION[14][k] = true;
                        }
                    }
                }
                catch (SQLException e)
                {
                    Logger.getLogger(SearchProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
    
  
    
    
    
    
    
    
    
    
    
    
    
     //block out the times that there are non-bed appointments scheduled for the specific person working in that area 
    public void nonbed()
    {
        ArrayList<schedule> appointments = new ArrayList<schedule>();
        
        for(int i=0; i<specificStaff.size(); i++)
        {
            if(specificStaff.get(i).getLocation().equals("Nonbed"))
            {
                int index = findIndex(specificStaff.get(i).getID());
                
                try
                {
                    Connection c = DatabaseConnector.activateConnection();
                    c.setAutoCommit( true ); 
                    ResultSet rs ;
                    Statement stmt = c.createStatement();
                    
                    ResultSet rs2 ;
                    Statement stmt2 = c.createStatement();

                    rs = stmt.executeQuery("SELECT * FROM nonbed WHERE Date ='" + date + "'"); //get all the non-bed appoinments for that day  
                    while(rs.next())
                    { 
                        String name = rs.getString("Procedure");
                        String time = rs.getString("Time");
                        LocalTime Time = LocalTime.parse(time);
                        int duration = 0;
                        
                        rs2 = stmt2.executeQuery("SELECT Duration FROM procedures WHERE Name = '" + name + "'"); //getting the duraiton of the appointment 
                        if(rs2.next())
                        {
                            duration = rs2.getInt("Duration");
                        }
                        
                        appointments.add(new schedule(Time, duration));   
                    }
                    
                    for(int j=0; j<appointments.size(); j++)
                    {
                        LocalTime dayStart = LocalTime.parse("07:00");
                        long minutesBetween = ChronoUnit.MINUTES.between(dayStart, appointments.get(j).getTime()); 
                        int arraySpace = (int) minutesBetween/5;
                        
                        int endAdd = appointments.get(j).getDuration()/5;   //the number of spaces to add for that duration 
                        
                        for(int k=arraySpace; k< arraySpace+endAdd ; k++) 
                        {
                            System.out.print("NON ");
                            scheduleSTAFF[index][k] = true;
                            scheduleLOCATION[15][k] = true;
                        }
                    }
                }
                catch (SQLException e)
                {
                    Logger.getLogger(SearchProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void diary()
    {
        ArrayList<schedule> appointments = new ArrayList<schedule>();
       
        try 
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);
            ResultSet rs;
            Statement stmt = c.createStatement();

            rs = stmt.executeQuery("SELECT * FROM diary WHERE Date ='" + date + "'"); //get all the diary/bed appoinments for that day  
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

            for (int j = 0; j < appointments.size(); j++) 
            {
                LocalTime dayStart = LocalTime.parse("07:00");
                long minutesBetween = ChronoUnit.MINUTES.between(dayStart, appointments.get(j).getTime());
                int arraySpace = (int) minutesBetween / 5;
                
                int index = getArrayIndex(appointments.get(j).getBedNumber());

                int endAdd = appointments.get(j).getDuration() / 5;   //the number of spaces to add for that duration 

                for (int k = arraySpace; k < arraySpace + endAdd; k++) 
                {
                    System.out.print("DIARY ");
                    scheduleLOCATION[index][k] = true;
                }
            }
            
            int unassignedStaffCount = 0;
            int foodchallangeCount = 0;
            boolean assigned = false;
            boolean fits = true;
            
            for(int i=0 ;i<appointments.size(); i++)
            {
                if(appointments.get(i).getName().equals("Food Challenge"))
                {
                    foodchallangeCount++;
                }
                
                while(!assigned && unassignedStaffCount < unassignedStaff.size())
                {
                    fits = true;
                    int staffID = findIndex(unassignedStaff.get(unassignedStaffCount));
                    
                    LocalTime dayStart = LocalTime.parse("07:00");
                    LocalTime appointmentTime = appointments.get(i).getTime();
                    
                    long minutesToStart = ChronoUnit.MINUTES.between(dayStart, appointmentTime); 
                    int arrayStart = (int) minutesToStart/5; 
                    
                    int arrayDuration = appointments.get(i).getDuration() / 5;
                    
                    for(int j=arrayStart; j<arrayStart+arrayDuration-1; j++)
                    {
                        if(scheduleSTAFF[staffID][j])
                        {
                            fits = false;
                            
                        }
                    }
                    
                    if(fits)
                    {
                        for(int j=arrayStart; j<arrayStart+arrayDuration-1; j++)
                        {
                            System.out.print("DIARYS ");
                            scheduleSTAFF[staffID][j] = true;
                        }
                        
                        assigned = true;
                        unassignedStaffCount++;
                    }
                }
            }
            
            
            
        } 
        catch (SQLException e) 
        {
            Logger.getLogger(SearchProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }    
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
        return 360;    //if not in list, must be a surgery so staff for 6 hours with is 360 mins
    }
    
    
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //Helper method - finds the index of a specific member of staff 
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
    
    
    
    
    
    public void suggest(String procedure, LocalDate date) 
    {
        for (int i = 0; i < bedProcedures.size(); i++) 
        {
            if (bedProcedures.get(i).getName().equals(procedure)) 
            {  
                if(procedure.equals("Food Challange"))
                {
                    makeSuggestions(-1, bedProcedures.get(i).getDuration(), date);
                }
                else if(procedure.equals("Oncology"))
                {
                    makeSuggestions(14, bedProcedures.get(i).getDuration(), date);
                }
                else
                {
                    makeSuggestions(-2, bedProcedures.get(i).getDuration(), date);
                }
            }
        }

        for (int i = 0; i < nonbedProcedures.size(); i++) 
        {
            if (nonbedProcedures.get(i).getName().equals(procedure)) 
            {
                int index = findLocationIndex(procedure);                
                makeSuggestions(index, nonbedProcedures.get(i).getDuration(), date);
            }
        }
    }
    
    
    public void makeSuggestions(int index, int duration, LocalDate date)
    {
        if(index == 12) //blood
        {
           findEmptyAppointments(date);
        }
        else if(index ==13) //preop
        {
            for(int j=0; j<workingStaff.size(); j++)
            {
                for(int i=0; i<145; i=i+6) //move forward 1/2 hour
                {
                    if(checkIfSpace(j, i, 30))
                    {
                        LocalTime time = LocalTime.parse("07:00").plusMinutes(i*5);
                        results.add(new result(date, time));
                    }
                }
            }
        }
        else if(index == 14) //oncology 
        {
            System.out.println("Making oncology suggestion");
            for(int i=0; i<145; i=i+6) //move forward 1/2 hour
            {
                if(checkIfSpace(index, i, 60))
                {
                    LocalTime time = LocalTime.parse("07:00").plusMinutes(i*5);
                    results.add(new result(date, time));
                    i = i + 6; //moving forward 1 hour 
                }
            }
        }
        else if(index == 15) //nonbed
        {
            System.out.println("DUR " + duration);
            for(int i=0; i<145; i=i+2) //looks at 10 minute intervals 
            {
                if(checkIfSpace(index, i, duration))
                {
                    LocalTime time = LocalTime.parse("07:00").plusMinutes(i*5);
                    results.add(new result(date, time));
                    i = ((i + (duration/5)) - 2); //moves forward by that duration 
                }
            }
            System.out.println("SIZE " + results.size());
        }
        else if(index == -2 || index == -1 && foodchallangeCount < 3) //food challange with already booked <3 or any other bed procedure 
        {
            if(foodchallangeCount < 3)
            {
                for(int i=0; i<145; i++)
                {
                    for(int j=0; j<12; j++)
                    {
                        if(checkIfSpace(i, j, duration))
                        {
                            LocalTime time = LocalTime.parse("07:00").plusMinutes(i*5);
                            results.add(new result(date, time));
                            i = i + duration/5 - 1;
                        }
                    }
                }
            }
        }
    }
    
    
    
    public boolean checkIfSpace(int row, int startPosition, int duration)
    {
        System.out.println("CHECK IF SPACE");
        int numberOfBoxes = duration / 5;
          
        for(int i=startPosition; i<(startPosition+numberOfBoxes) && i<145; i++)
        {
            if(scheduleLOCATION[row][i])
            {
                return false;
            }
        }
        return true;
    }
    
    
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
    
    
    
    
    //method used to find the blood clinic appointments that have not been booked 
    public void findEmptyAppointments(LocalDate date)
    {
        System.out.println("EMPTY");
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

            rs = stmt.executeQuery("SELECT * FROM template WHERE Day ='" + Date + "'"); //get all the pre-op appoinments for that day  
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
                rs = stmt.executeQuery("SELECT * FROM template WHERE Day ='" + Day + "' AND ToDate = 'null'"); //get all the pre-op appoinments for that day  
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
            
            
            rs = stmt.executeQuery("SELECT * FROM blood WHERE Date ='" + Date + "'"); //get all the blood appoinments for that day  
            while(rs.next()) 
            {
                String time = rs.getString("Time");
                LocalTime Time = LocalTime.parse(time);
                x = new result(date, Time);
                allAppointments.add(x);
            }
            
            System.out.println("TIMES : " + allTimes.size());
            System.out.println("APP : " + allAppointments.size());
            
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
            System.out.println("SHOW : " + results.size());
        }
        catch(SQLException e)
        {
             Logger.getLogger(SearchProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    
    
    
    public void displayResult()
    {
        tblSearchResult.getItems().addAll(results);
        tblColDate.setCellValueFactory(new PropertyValueFactory("DateString"));
        tblColTime.setCellValueFactory(new PropertyValueFactory("Time"));

                
    }
    
    
    
  
    
    
    
    
    
    
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
            System.out.println("ISSUE IN MAIN");
        }
    }
    
}
