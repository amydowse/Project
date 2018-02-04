/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
https://stackoverflow.com/questions/42569204/is-it-possible-to-reload-the-same-fxml-controller-instance
 */
package diary;

import common.DatabaseConnector;
import common.codeBank;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author amydo
 */
public class DiaryScreenDocumentController  implements Initializable
{
    //The FXML elements 
    
    //Mokey House, AM, Bed 1
    @FXML private  TextField txtOneMA = new TextField();
    @FXML private  TextField txtTime1MA = new TextField();
    @FXML private  TextField txtName1MA = new TextField();
    @FXML private  TextField txtAge1MA = new TextField();
    @FXML private  TextField txtHospital1MA = new TextField();
    @FXML private  TextField txtSpeciality1MA = new TextField();
    @FXML private  TextField txtNotes1MA = new TextField();
    @FXML private  TextField txtExtra1MA = new TextField();
    
    //Monkey House, AM, Bed 2
    @FXML private  TextField txtTwoMA = new TextField();
    @FXML private  TextField txtTime2MA = new TextField();
    @FXML private  TextField txtName2MA = new TextField();
    @FXML private  TextField txtAge2MA = new TextField();
    @FXML private  TextField txtHospital2MA = new TextField();
    @FXML private  TextField txtSpeciality2MA = new TextField();
    @FXML private  TextField txtNotes2MA = new TextField();
    @FXML private  TextField txtExtra2MA = new TextField();
    
    //Monkey House, AM, Bed 3
    @FXML private  TextField txtThreeMA = new TextField();
    @FXML private  TextField txtTime3MA = new TextField();
    @FXML private  TextField txtName3MA = new TextField();
    @FXML private  TextField txtAge3MA = new TextField();
    @FXML private  TextField txtHospital3MA = new TextField();
    @FXML private  TextField txtSpeciality3MA = new TextField();
    @FXML private  TextField txtNotes3MA = new TextField();
    @FXML private  TextField txtExtra3MA = new TextField();
    
    //Monkey House, AM, Bed 4
    @FXML private  TextField txtFourMA = new TextField();
    @FXML private  TextField txtTime4MA = new TextField();
    @FXML private  TextField txtName4MA = new TextField();
    @FXML private  TextField txtAge4MA = new TextField();
    @FXML private  TextField txtHospital4MA = new TextField();
    @FXML private  TextField txtSpeciality4MA = new TextField();
    @FXML private  TextField txtNotes4MA = new TextField();
    @FXML private  TextField txtExtra4MA = new TextField();   
    
    //Monkey House, AM, Bed Extra Line
    @FXML private  TextField txtSpareMA = new TextField();
    @FXML private  TextField txtTimeEMA = new TextField();
    @FXML private  TextField txtNameEMA = new TextField();
    @FXML private  TextField txtAgeEMA = new TextField();
    @FXML private  TextField txtHospitalEMA = new TextField();
    @FXML private  TextField txtSpecialityEMA = new TextField();
    @FXML private  TextField txtNotesEMA = new TextField();
    @FXML private  TextField txtExtraEMA = new TextField();   
    
    //----------------------------------------------------------------------

    //Leopards Den, AM, Bed 1
    @FXML private  TextField txtOneLA = new TextField();
    @FXML private  TextField txtTime1LA = new TextField();
    @FXML private  TextField txtName1LA = new TextField();
    @FXML private  TextField txtAge1LA = new TextField();
    @FXML private  TextField txtHospital1LA = new TextField();
    @FXML private  TextField txtSpeciality1LA = new TextField();
    @FXML private  TextField txtNotes1LA = new TextField();
    @FXML private  TextField txtExtra1LA = new TextField();
    
    //Leopards Den, AM, Bed 2
    @FXML private  TextField txtTwoLA = new TextField();
    @FXML private  TextField txtTime2LA = new TextField();
    @FXML private  TextField txtName2LA = new TextField();
    @FXML private  TextField txtAge2LA = new TextField();
    @FXML private  TextField txtHospital2LA = new TextField();
    @FXML private  TextField txtSpeciality2LA = new TextField();
    @FXML private  TextField txtNotes2LA = new TextField();
    @FXML private  TextField txtExtra2LA = new TextField();
    
    //Leopards Den, AM, Bed 3
    @FXML private  TextField txtThreeLA = new TextField();
    @FXML private  TextField txtTime3LA = new TextField();
    @FXML private  TextField txtName3LA = new TextField();
    @FXML private  TextField txtAge3LA = new TextField();
    @FXML private  TextField txtHospital3LA = new TextField();
    @FXML private  TextField txtSpeciality3LA = new TextField();
    @FXML private  TextField txtNotes3LA = new TextField();
    @FXML private  TextField txtExtra3LA = new TextField();
    
    //Leopards Den, AM, Bed 4
    @FXML private  TextField txtFourLA = new TextField();
    @FXML private  TextField txtTime4LA = new TextField();
    @FXML private  TextField txtName4LA = new TextField();
    @FXML private  TextField txtAge4LA = new TextField();
    @FXML private  TextField txtHospital4LA = new TextField();
    @FXML private  TextField txtSpeciality4LA = new TextField();
    @FXML private  TextField txtNotes4LA = new TextField();
    @FXML private  TextField txtExtra4LA = new TextField(); 
    
    //Leopards Den, AM, Bed 5
    @FXML private  TextField txtFiveLA = new TextField();
    @FXML private  TextField txtTime5LA = new TextField();
    @FXML private  TextField txtName5LA = new TextField();
    @FXML private  TextField txtAge5LA = new TextField();
    @FXML private  TextField txtHospital5LA = new TextField();
    @FXML private  TextField txtSpeciality5LA = new TextField();
    @FXML private  TextField txtNotes5LA = new TextField();
    @FXML private  TextField txtExtra5LA = new TextField(); 
    
    //Leopards Den, AM, Bed 6
    @FXML private  TextField txtSixLA = new TextField();
    @FXML private  TextField txtTime6LA = new TextField();
    @FXML private  TextField txtName6LA = new TextField();
    @FXML private  TextField txtAge6LA = new TextField();
    @FXML private  TextField txtHospital6LA = new TextField();
    @FXML private  TextField txtSpeciality6LA = new TextField();
    @FXML private  TextField txtNotes6LA = new TextField();
    @FXML private  TextField txtExtra6LA = new TextField(); 
    
    //Leopards Den, AM, Bed Extra Line
    @FXML private  TextField txtSpareLA = new TextField();
    @FXML private  TextField txtTimeELA = new TextField();
    @FXML private  TextField txtNameELA = new TextField();
    @FXML private  TextField txtAgeELA = new TextField();
    @FXML private  TextField txtHospitalELA = new TextField();
    @FXML private  TextField txtSpecialityELA = new TextField();
    @FXML private  TextField txtNotesELA = new TextField();
    @FXML private  TextField txtExtraELA = new TextField();       
    
    //----------------------------------------------------------------------    
    
    //Mokey House, PM, Bed 1
    @FXML private  TextField txtOneMP = new TextField();
    @FXML private  TextField txtTime1MP = new TextField();
    @FXML private  TextField txtName1MP = new TextField();
    @FXML private  TextField txtAge1MP = new TextField();
    @FXML private  TextField txtHospital1MP = new TextField();
    @FXML private  TextField txtSpeciality1MP = new TextField();
    @FXML private  TextField txtNotes1MP = new TextField();
    @FXML private  TextField txtExtra1MP = new TextField();
    
    //Monkey House, PM, Bed 2
    @FXML private  TextField txtTwoMP = new TextField();
    @FXML private  TextField txtTime2MP = new TextField();
    @FXML private  TextField txtName2MP = new TextField();
    @FXML private  TextField txtAge2MP = new TextField();
    @FXML private  TextField txtHospital2MP = new TextField();
    @FXML private  TextField txtSpeciality2MP = new TextField();
    @FXML private  TextField txtNotes2MP = new TextField();
    @FXML private  TextField txtExtra2MP = new TextField();
    
    //Monkey House, PM, Bed 3
    @FXML private  TextField txtThreeMP = new TextField();
    @FXML private  TextField txtTime3MP = new TextField();
    @FXML private  TextField txtName3MP = new TextField();
    @FXML private  TextField txtAge3MP = new TextField();
    @FXML private  TextField txtHospital3MP = new TextField();
    @FXML private  TextField txtSpeciality3MP = new TextField();
    @FXML private  TextField txtNotes3MP = new TextField();
    @FXML private  TextField txtExtra3MP = new TextField();
    
    //Monkey House, PM, Bed 4
    @FXML private  TextField txtFourMP = new TextField();
    @FXML private  TextField txtTime4MP = new TextField();
    @FXML private  TextField txtName4MP = new TextField();
    @FXML private  TextField txtAge4MP = new TextField();
    @FXML private  TextField txtHospital4MP = new TextField();
    @FXML private  TextField txtSpeciality4MP = new TextField();
    @FXML private  TextField txtNotes4MP = new TextField();
    @FXML private  TextField txtExtra4MP = new TextField();   
    
    //Monkey House, PM, Bed Extra Line
    @FXML private  TextField txtSpareMP = new TextField();
    @FXML private  TextField txtTimeEMP = new TextField();
    @FXML private  TextField txtNameEMP = new TextField();
    @FXML private  TextField txtAgeEMP = new TextField();
    @FXML private  TextField txtHospitalEMP = new TextField();
    @FXML private  TextField txtSpecialityEMP = new TextField();
    @FXML private  TextField txtNotesEMP = new TextField();
    @FXML private  TextField txtExtraEMP = new TextField();   
    
    //----------------------------------------------------------------------

    //Leopards Den, PM, Bed 1
    @FXML private  TextField txtOneLP = new TextField();
    @FXML private  TextField txtTime1LP = new TextField();
    @FXML private  TextField txtName1LP = new TextField();
    @FXML private  TextField txtAge1LP = new TextField();
    @FXML private  TextField txtHospital1LP = new TextField();
    @FXML private  TextField txtSpeciality1LP = new TextField();
    @FXML private  TextField txtNotes1LP = new TextField();
    @FXML private  TextField txtExtra1LP = new TextField();
    
    //Leopards Den, PM, Bed 2
    @FXML private  TextField txtTwoLP = new TextField();
    @FXML private  TextField txtTime2LP = new TextField();
    @FXML private  TextField txtName2LP = new TextField();
    @FXML private  TextField txtAge2LP = new TextField();
    @FXML private  TextField txtHospital2LP = new TextField();
    @FXML private  TextField txtSpeciality2LP = new TextField();
    @FXML private  TextField txtNotes2LP = new TextField();
    @FXML private  TextField txtExtra2LP = new TextField();
    
    //Leopards Den, PM, Bed 3
    @FXML private  TextField txtThreeLP = new TextField();
    @FXML private  TextField txtTime3LP = new TextField();
    @FXML private  TextField txtName3LP = new TextField();
    @FXML private  TextField txtAge3LP = new TextField();
    @FXML private  TextField txtHospital3LP = new TextField();
    @FXML private  TextField txtSpeciality3LP = new TextField();
    @FXML private  TextField txtNotes3LP = new TextField();
    @FXML private  TextField txtExtra3LP = new TextField();
    
    //Leopards Den, PM, Bed 4
    @FXML private  TextField txtFourLP = new TextField();
    @FXML private  TextField txtTime4LP = new TextField();
    @FXML private  TextField txtName4LP = new TextField();
    @FXML private  TextField txtAge4LP = new TextField();
    @FXML private  TextField txtHospital4LP = new TextField();
    @FXML private  TextField txtSpeciality4LP = new TextField();
    @FXML private  TextField txtNotes4LP = new TextField();
    @FXML private  TextField txtExtra4LP = new TextField(); 
    
    //Leopards Den, PM, Bed 5
    @FXML private  TextField txtFiveLP = new TextField();
    @FXML private  TextField txtTime5LP = new TextField();
    @FXML private  TextField txtName5LP = new TextField();
    @FXML private  TextField txtAge5LP = new TextField();
    @FXML private  TextField txtHospital5LP = new TextField();
    @FXML private  TextField txtSpeciality5LP = new TextField();
    @FXML private  TextField txtNotes5LP = new TextField();
    @FXML private  TextField txtExtra5LP = new TextField(); 
    
    //Leopards Den, PM, Bed 6
    @FXML private  TextField txtSixLP = new TextField();
    @FXML private  TextField txtTime6LP = new TextField();
    @FXML private  TextField txtName6LP = new TextField();
    @FXML private  TextField txtAge6LP = new TextField();
    @FXML private  TextField txtHospital6LP = new TextField();
    @FXML private  TextField txtSpeciality6LP = new TextField();
    @FXML private  TextField txtNotes6LP = new TextField();
    @FXML private  TextField txtExtra6LP = new TextField(); 
    
    //Leopards Den, PM, Bed Extra Line
    @FXML private  TextField txtSpareLP = new TextField();
    @FXML private  TextField txtTimeELP = new TextField();
    @FXML private  TextField txtNameELP = new TextField();
    @FXML private  TextField txtAgeELP = new TextField();
    @FXML private  TextField txtHospitalELP = new TextField();
    @FXML private  TextField txtSpecialityELP = new TextField();
    @FXML private  TextField txtNotesELP = new TextField();
    @FXML private  TextField txtExtraELP = new TextField();        
    
    private ArrayList<diary> allBookings = new ArrayList<diary>();
    
    
    //Top section
    //put in fxml of staff and notes 
    @FXML private TextField txtStaff1 = new TextField();
    @FXML private TextField txtStaff2 = new TextField();
    @FXML private TextField txtStaff3 = new TextField();
    @FXML private TextField txtStaff4 = new TextField();
    @FXML private TextField txtStaff5 = new TextField();
    @FXML private TextField txtStaff6 = new TextField();
    
    @FXML private TextArea txtNotes = new TextArea();
    
    @FXML private List<TextField> attendanceList;
    @FXML private List<TextField> notesList;
    
    private ArrayList<workingStaff> staff = new ArrayList<workingStaff>();
    
    int[] attendanceArray = new int[24];
    int[] notesArray = new int[24];
      
    //METHODS -----------------------------------------------------------------------
     
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        showInformation(codeBank.getCurrentDate());
        showStaff(codeBank.getCurrentDate());
       
    }
    
   
    
    //Each method associated with the textfield for the bed numbers 
    @FXML public void MA1Attendance(){change(0);}
    @FXML public void MA2Attendance(){change(1);}
    @FXML public void MA3Attendance(){change(2);}
    @FXML public void MA4Attendance(){change(3);}
    @FXML public void MAEAttendance(){change(4);}
    @FXML public void LA1Attendance(){change(5);}
    @FXML public void LA2Attendance(){change(6);}
    @FXML public void LA3Attendance(){change(7);}
    @FXML public void LA4Attendance(){change(8);}
    @FXML public void LA5Attendance(){change(9);}
    @FXML public void LA6Attendance(){change(10);}
    @FXML public void LAEAttendance(){change(11);}
    @FXML public void MP1Attendance(){change(12);}
    @FXML public void MP2Attendance(){change(13);}
    @FXML public void MP3Attendance(){change(14);}
    @FXML public void MP4Attendance(){change(15);}
    @FXML public void MPEAttendance(){change(16);}
    @FXML public void LP1Attendance(){change(17);}
    @FXML public void LP2Attendance(){change(18);}
    @FXML public void LP3Attendance(){change(19);}
    @FXML public void LP4Attendance(){change(20);}
    @FXML public void LP5Attendance(){change(21);}
    @FXML public void LP6Attendance(){change(22);}
    @FXML public void LPEAttendance(){change(23);}
    
    
    //Change the attendance colour when you click on it 
    public void change(int arrayValue)
    {
        if(attendanceArray[arrayValue] == 0)
        {
            attendanceArray[arrayValue] = 1;
        }
        else if(attendanceArray[arrayValue] == 1)
        {
            attendanceArray[arrayValue] = 2;
        }
        else if(attendanceArray[arrayValue] == 2)
        {
            attendanceArray[arrayValue] = 0;
        }
        
        attendanceColour(attendanceList.get(arrayValue),attendanceArray[arrayValue]);
    }
    
    
    //Each method associated with the textfield for notes
    @FXML public void MA1Notes(){changeNotes(0);}
    @FXML public void MA2Notes(){changeNotes(1);}
    @FXML public void MA3Notes(){changeNotes(2);}
    @FXML public void MA4Notes(){changeNotes(3);}
    @FXML public void MAENotes(){changeNotes(4);}
    @FXML public void LA1Notes(){changeNotes(5);}
    @FXML public void LA2Notes(){changeNotes(6);}
    @FXML public void LA3Notes(){changeNotes(7);}
    @FXML public void LA4Notes(){changeNotes(8);}
    @FXML public void LA5Notes(){changeNotes(9);}
    @FXML public void LA6Notes(){changeNotes(10);}
    @FXML public void LAENotes(){changeNotes(11);}
    @FXML public void MP1Notes(){changeNotes(12);}
    @FXML public void MP2Notes(){changeNotes(13);}
    @FXML public void MP3Notes(){changeNotes(14);}
    @FXML public void MP4Notes(){changeNotes(15);}
    @FXML public void MPENotes(){changeNotes(16);}
    @FXML public void LP1Notes(){changeNotes(17);}
    @FXML public void LP2Notes(){changeNotes(18);}
    @FXML public void LP3Notes(){changeNotes(19);}
    @FXML public void LP4Notes(){changeNotes(20);}
    @FXML public void LP5Notes(){changeNotes(21);}
    @FXML public void LP6Notes(){changeNotes(22);}
    @FXML public void LPENotes(){changeNotes(23);}
    
  //Change the attendance colour when you click on it 
    public void changeNotes(int arrayValue)
    {
        if(notesArray[arrayValue] == 0)
        {
            notesArray[arrayValue] = 1;
        }
        else if(notesArray[arrayValue] == 1)
        {
            notesArray[arrayValue] = 2;
        }
        else if(notesArray[arrayValue] == 2)
        {
            notesArray[arrayValue] = 0;
        }
                
        showNotes(notesList.get(arrayValue),notesArray[arrayValue]);
    }  
    
    
    
    
    
    
    
    
    
    public void showStaff(LocalDate SearchDate)
    {
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();
            String stringDate = codeBank.dateToString(SearchDate);          
            
            //implement query
            rs = stmt.executeQuery("SELECT * FROM staff, working WHERE working.Date = '" + stringDate + "' AND staff.ID = working.Staff_ID"); 
                        
            while(rs.next())
            { 
                String firstname = rs.getString("FirstName");
                String lastname = rs.getString("LastName");
                
                workingStaff instanceOfWorkingStaff = new workingStaff(firstname, lastname);
                staff.add(instanceOfWorkingStaff);
                
            }
            printStaffNames(staff);
            c.close();
        }
        catch (SQLException e)
        {
            
        } 
    }
    
    public void showNotes(LocalDate SearchDate)
    {
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();
            
            String stringDate = codeBank.dateToString(SearchDate);
            
            
            //implement query
            rs = stmt.executeQuery("SELECT * FROM notes WHERE Date = '" + stringDate + "'" );
            
            while(rs.next())
            { 
                    txtNotes.setText("NOTES \n\n " + rs.getString("Notes")); 
            }
            c.close();
        }
        catch (SQLException e)
        {
            
        }  
    }
    
    
    public void printStaffNames(ArrayList<workingStaff> staff)
    {
        if(staff.size()==1)
        {
            txtStaff1.setText(staff.get(0).getWorkName());
        }
        else if (staff.size() == 2)
        {
            txtStaff1.setText(staff.get(0).getWorkName());
            txtStaff2.setText(staff.get(1).getWorkName());
        }
        else if (staff.size() == 3)
        {
            txtStaff1.setText(staff.get(0).getWorkName());
            txtStaff2.setText(staff.get(1).getWorkName());
            txtStaff3.setText(staff.get(2).getWorkName());
        }
        else if (staff.size() == 4)
        {
            txtStaff1.setText(staff.get(0).getWorkName());
            txtStaff2.setText(staff.get(1).getWorkName());
            txtStaff3.setText(staff.get(2).getWorkName());
            txtStaff4.setText(staff.get(3).getWorkName());
        }
        else if (staff.size() == 5)
        {
            txtStaff1.setText(staff.get(0).getWorkName());
            txtStaff2.setText(staff.get(1).getWorkName());
            txtStaff3.setText(staff.get(2).getWorkName());
            txtStaff4.setText(staff.get(3).getWorkName());
            txtStaff5.setText(staff.get(4).getWorkName());
        }
        else if (staff.size() == 6)
        {
            txtStaff1.setText(staff.get(0).getWorkName());
            txtStaff2.setText(staff.get(1).getWorkName());
            txtStaff3.setText(staff.get(2).getWorkName());
            txtStaff4.setText(staff.get(3).getWorkName());
            txtStaff5.setText(staff.get(4).getWorkName());
            txtStaff6.setText(staff.get(5).getWorkName());
        }
    }
    
    
    
    public void clearTopSection()
    {
        staff.clear();
        txtNotes.setText("");
        txtStaff1.setText("");
        txtStaff2.setText("");
        txtStaff3.setText("");
        txtStaff4.setText("");
        txtStaff5.setText("");
        txtStaff6.setText("");
    }
    
    public void showInformation(LocalDate SearchDate)
    {   
        allBookings.clear();
        clearDiary();
        clearTopSection();
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();
            
            //make localdate into string
            String stringDate = codeBank.dateToString(SearchDate);
            
            System.out.println(stringDate);
            
            //implement query
            rs = stmt.executeQuery("SELECT * FROM diary WHERE Date = '" + stringDate + "'" );
            
            while(rs.next())
            { 
                //get String into LocalDate
                String date = rs.getString("Date"); //LocalDate
                LocalDate localDate = codeBank.stringToDate(date);
                
                //get String into LocalTime
                String time = rs.getString("Time"); //Time
                LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
                
                String bedNumber = rs.getString("BedNumber");
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                String hospitalNumber = rs.getString("HospitalNumber");
                String speciality = rs.getString("Speciality");
                String extraInfo = rs.getString("ExtraInfo");
                int notes = rs.getInt("Notes");
                int attendance = rs.getInt("Attendance");
                
                //creating a diary object 
                diary instanceOfDiary = new diary(localDate, bedNumber, localTime, name, age, hospitalNumber, speciality, extraInfo, notes, attendance);
                allBookings.add(instanceOfDiary);             
            }
           showResults(allBookings);
           c.close();
        }
        catch (SQLException e)
        {
            
        }  
    }
    
  
    
    public void showResults(ArrayList<diary> allBookings)
    {
        for(int i=0; i<allBookings.size(); i++)
        {
            diary singleBooking = allBookings.get(i);
                       
            switch(singleBooking.getBedNumber())
            {
                case "1MA":
                    txtTime1MA.setText((singleBooking.getTime()).toString());
                    txtName1MA.setText(singleBooking.getName());
                    txtAge1MA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital1MA.setText(singleBooking.getHospitalNumber());
                    txtSpeciality1MA.setText(singleBooking.getSpeciality());
                    notesArray[0]=singleBooking.getNotes();
                    txtExtra1MA.setText(String.valueOf(singleBooking.getExtraInfo()));
                    attendanceArray[0] = singleBooking.getAttendance();
                    break;
                case "2MA":
                    txtTime2MA.setText((singleBooking.getTime()).toString());
                    txtName2MA.setText(singleBooking.getName());
                    txtAge2MA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital2MA.setText(singleBooking.getHospitalNumber());
                    txtSpeciality2MA.setText(singleBooking.getSpeciality());
                    notesArray[1]=singleBooking.getNotes();
                    txtExtra2MA.setText(String.valueOf(singleBooking.getExtraInfo()));
                    attendanceArray[1] = singleBooking.getAttendance();
                    break;
                case "3MA":
                    txtTime3MA.setText((singleBooking.getTime()).toString());
                    txtName3MA.setText(singleBooking.getName());
                    txtAge3MA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital3MA.setText(singleBooking.getHospitalNumber());
                    txtSpeciality3MA.setText(singleBooking.getSpeciality());
                    notesArray[2]=singleBooking.getNotes();
                    txtExtra3MA.setText(String.valueOf(singleBooking.getExtraInfo()));  
                    attendanceArray[2] = singleBooking.getAttendance();
                    break;
                case "4MA":
                    txtTime4MA.setText((singleBooking.getTime()).toString());
                    txtName4MA.setText(singleBooking.getName());
                    txtAge4MA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital4MA.setText(singleBooking.getHospitalNumber());
                    txtSpeciality4MA.setText(singleBooking.getSpeciality());
                    notesArray[3]=singleBooking.getNotes();
                    txtExtra4MA.setText(String.valueOf(singleBooking.getExtraInfo()));
                    attendanceArray[3] = singleBooking.getAttendance();
                    break;
                case "EMA":
                    txtTimeEMA.setText((singleBooking.getTime()).toString());
                    txtNameEMA.setText(singleBooking.getName());
                    txtAgeEMA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospitalEMA.setText(singleBooking.getHospitalNumber());
                    txtSpecialityEMA.setText(singleBooking.getSpeciality());
                    notesArray[4]=singleBooking.getNotes();
                    txtExtraEMA.setText(String.valueOf(singleBooking.getExtraInfo()));
                    attendanceArray[4] = singleBooking.getAttendance();
                    break;
                case "1LA":
                    txtTime1LA.setText((singleBooking.getTime()).toString());
                    txtName1LA.setText(singleBooking.getName());
                    txtAge1LA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital1LA.setText(singleBooking.getHospitalNumber());
                    txtSpeciality1LA.setText(singleBooking.getSpeciality());
                    notesArray[5]=singleBooking.getNotes();
                    txtExtra1LA.setText(String.valueOf(singleBooking.getExtraInfo()));    
                    attendanceArray[5] = singleBooking.getAttendance();
                    break;
                case "2LA":
                    txtTime2LA.setText((singleBooking.getTime()).toString());
                    txtName2LA.setText(singleBooking.getName());
                    txtAge2LA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital2LA.setText(singleBooking.getHospitalNumber());
                    txtSpeciality2LA.setText(singleBooking.getSpeciality());
                    notesArray[6]=singleBooking.getNotes();
                    txtExtra2LA.setText(String.valueOf(singleBooking.getExtraInfo())); 
                    attendanceArray[6] = singleBooking.getAttendance();
                    break;
                case "3LA":
                    txtTime3LA.setText((singleBooking.getTime()).toString());
                    txtName3LA.setText(singleBooking.getName());
                    txtAge3LA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital3LA.setText(singleBooking.getHospitalNumber());
                    txtSpeciality3LA.setText(singleBooking.getSpeciality());
                    notesArray[7]=singleBooking.getNotes();
                    txtExtra3LA.setText(String.valueOf(singleBooking.getExtraInfo()));  
                    attendanceArray[7] = singleBooking.getAttendance();
                    break;
                case "4LA":
                    txtTime4LA.setText((singleBooking.getTime()).toString());
                    txtName4LA.setText(singleBooking.getName());
                    txtAge4LA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital4LA.setText(singleBooking.getHospitalNumber());
                    txtSpeciality4LA.setText(singleBooking.getSpeciality());
                    notesArray[8]=singleBooking.getNotes();
                    txtExtra4LA.setText(String.valueOf(singleBooking.getExtraInfo()));  
                    attendanceArray[8] = singleBooking.getAttendance();
                    break;
                case "5LA":
                    txtTime5LA.setText((singleBooking.getTime()).toString());
                    txtName5LA.setText(singleBooking.getName());
                    txtAge5LA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital5LA.setText(singleBooking.getHospitalNumber());
                    txtSpeciality5LA.setText(singleBooking.getSpeciality());
                    notesArray[9]=singleBooking.getNotes();
                    txtExtra5LA.setText(String.valueOf(singleBooking.getExtraInfo())); 
                    attendanceArray[9] = singleBooking.getAttendance();
                    break;
                case "6LA":
                    txtTime6LA.setText((singleBooking.getTime()).toString());
                    txtName6LA.setText(singleBooking.getName());
                    txtAge6LA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital6LA.setText(singleBooking.getHospitalNumber());
                    txtSpeciality6LA.setText(singleBooking.getSpeciality());
                    notesArray[10]=singleBooking.getNotes();
                    txtExtra6LA.setText(String.valueOf(singleBooking.getExtraInfo()));  
                    attendanceArray[10] = singleBooking.getAttendance();
                    break;
                case "ELA":
                    txtTimeELA.setText((singleBooking.getTime()).toString());
                    txtNameELA.setText(singleBooking.getName());
                    txtAgeELA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospitalELA.setText(singleBooking.getHospitalNumber());
                    txtSpecialityELA.setText(singleBooking.getSpeciality());
                    notesArray[11]=singleBooking.getNotes();
                    txtExtraELA.setText(String.valueOf(singleBooking.getExtraInfo()));
                    attendanceArray[11] = singleBooking.getAttendance();                    
                    break;
                case "1MP":
                    txtTime1MP.setText((singleBooking.getTime()).toString());
                    txtName1MP.setText(singleBooking.getName());
                    txtAge1MP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital1MP.setText(singleBooking.getHospitalNumber());
                    txtSpeciality1MP.setText(singleBooking.getSpeciality());
                    notesArray[12]=singleBooking.getNotes();
                    txtExtra1MP.setText(String.valueOf(singleBooking.getExtraInfo()));
                    attendanceArray[12] = singleBooking.getAttendance();                   
                    break;
                case "2MP":
                    txtTime2MP.setText((singleBooking.getTime()).toString());
                    txtName2MP.setText(singleBooking.getName());
                    txtAge2MP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital2MP.setText(singleBooking.getHospitalNumber());
                    txtSpeciality2MP.setText(singleBooking.getSpeciality());
                    notesArray[13]=singleBooking.getNotes();
                    txtExtra2MP.setText(String.valueOf(singleBooking.getExtraInfo()));
                    attendanceArray[13] = singleBooking.getAttendance();
                    break;
                case "3MP":
                    txtTime3MP.setText((singleBooking.getTime()).toString());
                    txtName3MP.setText(singleBooking.getName());
                    txtAge3MP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital3MP.setText(singleBooking.getHospitalNumber());
                    txtSpeciality3MP.setText(singleBooking.getSpeciality());
                    notesArray[14]=singleBooking.getNotes();
                    txtExtra3MP.setText(String.valueOf(singleBooking.getExtraInfo()));    
                    attendanceArray[14] = singleBooking.getAttendance(); 
                    break;
                case "4MP":
                    txtTime4MP.setText((singleBooking.getTime()).toString());
                    txtName4MP.setText(singleBooking.getName());
                    txtAge4MP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital4MP.setText(singleBooking.getHospitalNumber());
                    txtSpeciality4MP.setText(singleBooking.getSpeciality());
                    notesArray[15]=singleBooking.getNotes();
                    txtExtra4MP.setText(String.valueOf(singleBooking.getExtraInfo()));
                    attendanceArray[15] = singleBooking.getAttendance();
                    break;
                case "EMP":
                    txtTimeEMP.setText((singleBooking.getTime()).toString());
                    txtNameEMP.setText(singleBooking.getName());
                    txtAgeEMP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospitalEMP.setText(singleBooking.getHospitalNumber());
                    txtSpecialityEMP.setText(singleBooking.getSpeciality());
                    notesArray[16]=singleBooking.getNotes();
                    txtExtraEMP.setText(String.valueOf(singleBooking.getExtraInfo())); 
                    attendanceArray[16] = singleBooking.getAttendance(); 
                    break;
                case "1LP":
                    txtTime1LP.setText((singleBooking.getTime()).toString());
                    txtName1LP.setText(singleBooking.getName());
                    txtAge1LP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital1LP.setText(singleBooking.getHospitalNumber());
                    txtSpeciality1LP.setText(singleBooking.getSpeciality());
                    notesArray[17]=singleBooking.getNotes();
                    txtExtra1LP.setText(String.valueOf(singleBooking.getExtraInfo()));    
                    attendanceArray[17] = singleBooking.getAttendance();
                    break;
                case "2LP":
                    txtTime2LP.setText((singleBooking.getTime()).toString());
                    txtName2LP.setText(singleBooking.getName());
                    txtAge2LP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital2LP.setText(singleBooking.getHospitalNumber());
                    txtSpeciality2LP.setText(singleBooking.getSpeciality());
                    notesArray[18]=singleBooking.getNotes();
                    txtExtra2LP.setText(String.valueOf(singleBooking.getExtraInfo())); 
                    attendanceArray[18] = singleBooking.getAttendance(); 
                    break;
                case "3LP":
                    txtTime3LP.setText((singleBooking.getTime()).toString());
                    txtName3LP.setText(singleBooking.getName());
                    txtAge3LP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital3LP.setText(singleBooking.getHospitalNumber());
                    txtSpeciality3LP.setText(singleBooking.getSpeciality());
                    notesArray[19]=singleBooking.getNotes();
                    txtExtra3LP.setText(String.valueOf(singleBooking.getExtraInfo()));        
                    attendanceArray[19] = singleBooking.getAttendance();
                    break;
                case "4LP":
                    txtTime4LP.setText((singleBooking.getTime()).toString());
                    txtName4LP.setText(singleBooking.getName());
                    txtAge4LP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital4LP.setText(singleBooking.getHospitalNumber());
                    txtSpeciality4LP.setText(singleBooking.getSpeciality());
                    notesArray[20]=singleBooking.getNotes();
                    txtExtra4LP.setText(String.valueOf(singleBooking.getExtraInfo()));  
                    attendanceArray[20] = singleBooking.getAttendance();
                    break;
                case "5LP":
                    txtTime5LP.setText((singleBooking.getTime()).toString());
                    txtName5LP.setText(singleBooking.getName());
                    txtAge5LP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital5LP.setText(singleBooking.getHospitalNumber());
                    txtSpeciality5LP.setText(singleBooking.getSpeciality());
                    notesArray[21]=singleBooking.getNotes();
                    txtExtra5LP.setText(String.valueOf(singleBooking.getExtraInfo())); 
                    attendanceArray[21] = singleBooking.getAttendance(); 
                    break;
                case "6LP":
                    txtTime6LP.setText((singleBooking.getTime()).toString());
                    txtName6LP.setText(singleBooking.getName());
                    txtAge6LP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital6LP.setText(singleBooking.getHospitalNumber());
                    txtSpeciality6LP.setText(singleBooking.getSpeciality());
                    notesArray[22]=singleBooking.getNotes();
                    txtExtra6LP.setText(String.valueOf(singleBooking.getExtraInfo())); 
                    attendanceArray[22] = singleBooking.getAttendance();
                    break;
                case "ELP":
                    txtTimeELP.setText((singleBooking.getTime()).toString());
                    txtNameELP.setText(singleBooking.getName());
                    txtAgeELP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospitalELP.setText(singleBooking.getHospitalNumber());
                    txtSpecialityELP.setText(singleBooking.getSpeciality());
                    notesArray[23]=singleBooking.getNotes();
                    txtExtraELP.setText(String.valueOf(singleBooking.getExtraInfo()));
                    attendanceArray[23] = singleBooking.getAttendance();                    
                    break;
                default:
                    break;
                    
            }
        }
        
        int count = 0;
        for(TextField text : attendanceList)
        {
            attendanceColour(text, attendanceArray[count]);
            count++;
        }
        
        int count2 = 0;
        for(TextField text2 : notesList)
        {
            showNotes(text2, notesArray[count2]);
            count2++;
        }
              
    }
    
    
    public void clearDiary()
    {
        attendanceArray = new int[24];
        
        txtTime1MA.setText("");
        txtName1MA.setText("");
        txtAge1MA.setText("");
        txtHospital1MA.setText("");
        txtSpeciality1MA.setText("");
        txtNotes1MA.setText("");
        txtExtra1MA.setText("");
                 
        txtTime2MA.setText("");
        txtName2MA.setText("");
        txtAge2MA.setText("");
        txtHospital2MA.setText("");
        txtSpeciality2MA.setText("");
        txtNotes2MA.setText("");
        txtExtra2MA.setText("");
      
        txtTime3MA.setText("");
        txtName3MA.setText("");
        txtAge3MA.setText("");
        txtHospital3MA.setText("");
        txtSpeciality3MA.setText("");
        txtNotes3MA.setText("");
        txtExtra3MA.setText(""); 
                 
        txtTime4MA.setText("");
        txtName4MA.setText("");
        txtAge4MA.setText("");
        txtHospital4MA.setText("");
        txtSpeciality4MA.setText("");
        txtNotes4MA.setText("");
        txtExtra4MA.setText("");
                    
        txtTimeEMA.setText("");
        txtNameEMA.setText("");
        txtAgeEMA.setText("");
        txtHospitalEMA.setText("");
        txtSpecialityEMA.setText("");
        txtNotesEMA.setText("");
        txtExtraEMA.setText("");
                  
        txtTime1LA.setText("");
        txtName1LA.setText("");
        txtAge1LA.setText("");
        txtHospital1LA.setText("");
        txtSpeciality1LA.setText("");
        txtNotes1LA.setText("");
        txtExtra1LA.setText("");   
                  
        txtTime2LA.setText("");   
        txtName2LA.setText("");   
        txtAge2LA.setText("");   
        txtHospital2LA.setText("");   
        txtSpeciality2LA.setText("");   
        txtNotes2LA.setText("");   
        txtExtra2LA.setText("");   
                   
        txtTime3LA.setText("");   
        txtName3LA.setText("");   
        txtAge3LA.setText("");   
        txtHospital3LA.setText("");   
        txtSpeciality3LA.setText("");   
        txtNotes3LA.setText("");   
        txtExtra3LA.setText("");     
                  
        txtTime4LA.setText("");   
        txtName4LA.setText("");   
        txtAge4LA.setText("");   
        txtHospital4LA.setText("");   
        txtSpeciality4LA.setText("");   
        txtNotes4LA.setText("");   
        txtExtra4LA.setText("");   
                   
        txtTime5LA.setText("");   
        txtName5LA.setText("");   
        txtAge5LA.setText("");   
        txtHospital5LA.setText("");   
        txtSpeciality5LA.setText("");   
        txtNotes5LA.setText("");   
        txtExtra5LA.setText("");   
                    
        txtTime6LA.setText("");   
        txtName6LA.setText("");   
        txtAge6LA.setText("");   
        txtHospital6LA.setText("");   
        txtSpeciality6LA.setText("");   
        txtNotes6LA.setText("");   
        txtExtra6LA.setText("");    
                   
        txtTimeELA.setText("");   
        txtNameELA.setText("");   
        txtAgeELA.setText("");   
        txtHospitalELA.setText("");   
        txtSpecialityELA.setText("");   
        txtNotesELA.setText("");   
        txtExtraELA.setText("");                       
                    
        txtTime1MP.setText("");   
        txtName1MP.setText("");   
        txtAge1MP.setText("");   
        txtHospital1MP.setText("");   
        txtSpeciality1MP.setText("");   
        txtNotes1MP.setText("");   
        txtExtra1MP.setText("");                       
                   
        txtTime2MP.setText("");   
        txtName2MP.setText("");   
        txtAge2MP.setText("");   
        txtHospital2MP.setText("");   
        txtSpeciality2MP.setText("");   
        txtNotes2MP.setText("");   
        txtExtra2MP.setText("");   
                  
        txtTime3MP.setText("");   
        txtName3MP.setText("");   
        txtAge3MP.setText("");   
        txtHospital3MP.setText("");   
        txtSpeciality3MP.setText("");   
        txtNotes3MP.setText("");   
        txtExtra3MP.setText("");        
                  
        txtTime4MP.setText("");   
        txtName4MP.setText("");   
        txtAge4MP.setText("");   
        txtHospital4MP.setText("");   
        txtSpeciality4MP.setText("");   
        txtNotes4MP.setText("");   
        txtExtra4MP.setText("");    
                   
        txtTimeEMP.setText("");   
        txtNameEMP.setText("");   
        txtAgeEMP.setText("");   
        txtHospitalEMP.setText("");   
        txtSpecialityEMP.setText("");   
        txtNotesEMP.setText("");   
        txtExtraEMP.setText("");     
                   
        txtTime1LP.setText("");   
        txtName1LP.setText("");   
        txtAge1LP.setText("");   
        txtHospital1LP.setText("");   
        txtSpeciality1LP.setText("");   
        txtNotes1LP.setText("");   
        txtExtra1LP.setText("");      
                   
        txtTime2LP.setText("");   
        txtName2LP.setText("");   
        txtAge2LP.setText("");   
        txtHospital2LP.setText("");   
        txtSpeciality2LP.setText("");   
        txtNotes2LP.setText("");   
        txtExtra2LP.setText("");   
                   
        txtTime3LP.setText("");   
        txtName3LP.setText("");   
        txtAge3LP.setText("");   
        txtHospital3LP.setText("");   
        txtSpeciality3LP.setText("");   
        txtNotes3LP.setText("");   
        txtExtra3LP.setText("");   

        txtTime4LP.setText("");   
        txtName4LP.setText("");   
        txtAge4LP.setText("");   
        txtHospital4LP.setText("");   
        txtSpeciality4LP.setText("");   
        txtNotes4LP.setText("");   
        txtExtra4LP.setText("");   

        txtTime5LP.setText("");   
        txtName5LP.setText("");   
        txtAge5LP.setText("");   
        txtHospital5LP.setText("");   
        txtSpeciality5LP.setText("");   
        txtNotes5LP.setText("");   
        txtExtra5LP.setText("");   

        txtTime6LP.setText("");   
        txtName6LP.setText("");   
        txtAge6LP.setText("");   
        txtHospital6LP.setText("");   
        txtSpeciality6LP.setText("");   
        txtNotes6LP.setText("");   
        txtExtra6LP.setText("");   

        txtTimeELP.setText("");   
        txtNameELP.setText("");   
        txtAgeELP.setText("");   
        txtHospitalELP.setText("");   
        txtSpecialityELP.setText("");   
        txtNotesELP.setText("");   
        txtExtraELP.setText("");                        
        
    }
    
    
    
    public void showNotes(TextField txt, int notes)
    {
        if(notes == 0)
        {
            txt.setText("-");
        }
        else if (notes == 1)
        {
            txt.setText("O");
            txt.setStyle(" -fx-text-fill: #FFD800"); //orange
        }
        else
        {   
            txt.setText("✓");
            txt.setStyle(" -fx-text-fill: #00FF31"); //green
        }
    }
    
    public void attendanceColour(TextField txt, int attendance)
    {
        if(attendance == 0)
        {
            txt.setStyle(" -fx-background-color: #FFFFFF"); //white
        }
        else if (attendance == 1)
        {
            txt.setStyle(" -fx-background-color: #00FF31"); //green
        }
        else
        {
            txt.setStyle(" -fx-background-color: #FF0000"); //red
        }
    }
    
  
    
    
    
    
    
    
    
    //SAVING-----------------------------------------------------------------
    
    
    public void save(LocalDate today)
    {
        System.out.println("IN SAVE");
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();
            String stringDate = codeBank.dateToString(today);          
            
            //implement query
            String sql = save1MA(stringDate);
            
            System.out.println(sql);
            
            stmt.executeUpdate(sql);
                         
            c.close();
        }
        catch (SQLException e)
        {
            
        } 
    }
    
    public String save1MA(String date)
    {
        System.out.println("IN 1MA SAVE");
        return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                            + date + "','"
                                                                            + "1MA" + "','"
                                                                            + txtTime1MA.getText() + "','"
                                                                            + txtName1MA.getText() + "','"
                                                                            + txtAge1MA.getText() + "','"
                                                                            + txtHospital1MA.getText() + "','"
                                                                            + txtSpeciality1MA.getText() + "','"
                                                                            + txtExtra1MA.getText() + "','"
                                                                            + txtNotes1MA.getText() + "','"
                                                                            + attendanceArray[0] + "')"
                );
    }   
    
}

