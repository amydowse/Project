/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oncology;

import common.DatabaseConnector;
import common.codeBank;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import static java.time.LocalTime.now;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author amydo
 */
public class OncologyScreenDocumentController implements Initializable
{
    //FXML Elements
    @FXML TextField txtAttendance1 = new TextField();
    @FXML TextField txtTime1 = new TextField();
    @FXML ChoiceBox cbName1 = new ChoiceBox();
    @FXML TextField txtAge1 = new TextField();
    @FXML TextField txtHospital1 = new TextField();
    @FXML TextField txtNumber1 = new TextField();
    @FXML TextField txtWristband1 = new TextField();
    @FXML TextArea txtReason1 = new TextArea();
    @FXML TextField txtNotes1 = new TextField();
   
    @FXML TextField txtAttendance2 = new TextField();
    @FXML TextField txtTime2 = new TextField();
    @FXML ChoiceBox cbName2 = new ChoiceBox();
    @FXML TextField txtAge2 = new TextField();
    @FXML TextField txtHospital2 = new TextField();
    @FXML TextField txtNumber2 = new TextField();
    @FXML TextField txtWristband2 = new TextField();
    @FXML TextArea txtReason2 = new TextArea();
    @FXML TextField txtNotes2 = new TextField();
    
    @FXML TextField txtAttendance3 = new TextField();
    @FXML TextField txtTime3 = new TextField();
    @FXML ChoiceBox cbName3 = new ChoiceBox();
    @FXML TextField txtAge3 = new TextField();
    @FXML TextField txtHospital3 = new TextField();
    @FXML TextField txtNumber3 = new TextField();
    @FXML TextField txtWristband3 = new TextField();
    @FXML TextArea txtReason3 = new TextArea();
    @FXML TextField txtNotes3 = new TextField();
    
    @FXML TextField txtAttendance4 = new TextField();
    @FXML TextField txtTime4 = new TextField();
    @FXML ChoiceBox cbName4 = new ChoiceBox();
    @FXML TextField txtAge4 = new TextField();
    @FXML TextField txtHospital4 = new TextField();
    @FXML TextField txtNumber4 = new TextField();
    @FXML TextField txtWristband4 = new TextField();
    @FXML TextArea txtReason4 = new TextArea();
    @FXML TextField txtNotes4 = new TextField();
    
    @FXML TextField txtAttendance5 = new TextField();
    @FXML TextField txtTime5 = new TextField();
    @FXML ChoiceBox cbName5 = new ChoiceBox();
    @FXML TextField txtAge5 = new TextField();
    @FXML TextField txtHospital5 = new TextField();
    @FXML TextField txtNumber5 = new TextField();
    @FXML TextField txtWristband5 = new TextField();
    @FXML TextArea txtReason5 = new TextArea();
    @FXML TextField txtNotes5 = new TextField();
    
    @FXML TextField txtAttendance6 = new TextField();
    @FXML TextField txtTime6 = new TextField();
    @FXML ChoiceBox cbName6 = new ChoiceBox();
    @FXML TextField txtAge6 = new TextField();
    @FXML TextField txtHospital6 = new TextField();
    @FXML TextField txtNumber6 = new TextField();
    @FXML TextField txtWristband6 = new TextField();
    @FXML TextArea txtReason6 = new TextArea();
    @FXML TextField txtNotes6 = new TextField();
    
    @FXML TextField txtAttendance7 = new TextField();
    @FXML TextField txtTime7 = new TextField();
    @FXML ChoiceBox cbName7 = new ChoiceBox();
    @FXML TextField txtAge7 = new TextField();
    @FXML TextField txtHospital7 = new TextField();
    @FXML TextField txtNumber7 = new TextField();
    @FXML TextField txtWristband7 = new TextField();
    @FXML TextArea txtReason7 = new TextArea();
    @FXML TextField txtNotes7 = new TextField();
    
    @FXML TextField txtAttendance8 = new TextField();
    @FXML TextField txtTime8 = new TextField();
    @FXML ChoiceBox cbName8 = new ChoiceBox();
    @FXML TextField txtAge8 = new TextField();
    @FXML TextField txtHospital8 = new TextField();
    @FXML TextField txtNumber8 = new TextField();
    @FXML TextField txtWristband8 = new TextField();
    @FXML TextArea txtReason8 = new TextArea();
    @FXML TextField txtNotes8 = new TextField();
    
    @FXML TextField txtAttendance9 = new TextField();
    @FXML TextField txtTime9 = new TextField();
    @FXML ChoiceBox cbName9 = new ChoiceBox();
    @FXML TextField txtAge9 = new TextField();
    @FXML TextField txtHospital9 = new TextField();
    @FXML TextField txtNumber9 = new TextField();
    @FXML TextField txtWristband9 = new TextField();
    @FXML TextArea txtReason9 = new TextArea();
    @FXML TextField txtNotes9 = new TextField();
    
    @FXML TextField txtAttendance10 = new TextField();
    @FXML TextField txtTime10 = new TextField();
    @FXML ChoiceBox cbName10 = new ChoiceBox();
    @FXML TextField txtAge10 = new TextField();
    @FXML TextField txtHospital10 = new TextField();
    @FXML TextField txtNumber10 = new TextField();
    @FXML TextField txtWristband10 = new TextField();
    @FXML TextArea txtReason10 = new TextArea();
    @FXML TextField txtNotes10 = new TextField();
    
    @FXML TextField txtAttendance11 = new TextField();
    @FXML TextField txtTime11 = new TextField();
    @FXML ChoiceBox cbName11 = new ChoiceBox();
    @FXML TextField txtAge11 = new TextField();
    @FXML TextField txtHospital11 = new TextField();
    @FXML TextField txtNumber11 = new TextField();
    @FXML TextField txtWristband11 = new TextField();
    @FXML TextArea txtReason11 = new TextArea();
    @FXML TextField txtNotes11 = new TextField();
    
    @FXML TextField txtAttendance12 = new TextField();
    @FXML TextField txtTime12 = new TextField();
    @FXML ChoiceBox cbName12 = new ChoiceBox();
    @FXML TextField txtAge12 = new TextField();
    @FXML TextField txtHospital12 = new TextField();
    @FXML TextField txtNumber12 = new TextField();
    @FXML TextField txtWristband12 = new TextField();
    @FXML TextArea txtReason12 = new TextArea();
    @FXML TextField txtNotes12 = new TextField();
    
    @FXML TextField txtAttendance13 = new TextField();
    @FXML TextField txtTime13 = new TextField();
    @FXML ChoiceBox cbName13 = new ChoiceBox();
    @FXML TextField txtAge13 = new TextField();
    @FXML TextField txtHospital13 = new TextField();
    @FXML TextField txtNumber13 = new TextField();
    @FXML TextField txtWristband13 = new TextField();
    @FXML TextArea txtReason13 = new TextArea();
    @FXML TextField txtNotes13 = new TextField();
    
    @FXML TextField txtAttendance14 = new TextField();
    @FXML TextField txtTime14 = new TextField();
    @FXML ChoiceBox cbName14 = new ChoiceBox();
    @FXML TextField txtAge14 = new TextField();
    @FXML TextField txtHospital14 = new TextField();
    @FXML TextField txtNumber14 = new TextField();
    @FXML TextField txtWristband14 = new TextField();
    @FXML TextArea txtReason14 = new TextArea();
    @FXML TextField txtNotes14 = new TextField();
    
    @FXML ChoiceBox cbStaff = new ChoiceBox();
    
    @FXML private List<TextField> attendanceList;
    @FXML private List<TextField> notesList;
    @FXML private List<TextField> ageList;
    @FXML private List<TextField> hospitalList;
    @FXML private List<TextField> numberList;
    @FXML private List<TextField> wristbandList;
    @FXML private List<TextField> timeList;
    
    int[] attendanceArray = new int[14];
    int[] notesArray = new int[14];
    
    ArrayList<oncology> allBookings = new ArrayList<oncology>();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        showInformation(codeBank.getCurrentDate());
        fillDropDowns();
        delete();
        showStaff(codeBank.getCurrentDate());
        
    }
    
    
    
    public void showInformation(LocalDate date)
    {
        clearAll();
        
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();
            
            //make localdate into string
            String stringDate = codeBank.dateToString(date);
                    
            //implement query
            rs = stmt.executeQuery("SELECT * FROM regular, oncology WHERE oncology.Date = '" + stringDate + "' AND oncology.Regular_HospitalNumber = regular.HospitalNumber" );
           
            while(rs.next())
            { 
                //get String into LocalDate
                String Stringdate = rs.getString("Date"); //LocalDate
                LocalDate localDate = codeBank.stringToDate(Stringdate);
                
                //get String into LocalTime
                String time = rs.getString("Time"); //Time
                LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
              
                String name = rs.getString("Name");
                
                //https://memorynotfound.com/calculate-age-from-date-of-birth-in-java/ accessed 17/02/2018
                String dob = rs.getString("DateOfBirth");
                LocalDate dobDate = codeBank.stringToDate(dob);
                Period period = Period.between(dobDate, LocalDate.now());
                int age = period.getYears(); 
        
                String hospitalNumber = rs.getString("HospitalNumber");
                String number = rs.getString("Number");
                String wristband = rs.getString("Wristband");
                String reason = rs.getString("Reason");
                int notes = rs.getInt("Notes");
                int attendance = rs.getInt("Attendance");
                
                //creating a diary object 
                oncology booking = new oncology(localDate, localTime, name, age, hospitalNumber, number, wristband, reason, notes, attendance);
                allBookings.add(booking);             
            }
            Collections.sort(allBookings);
           
            for(int i=0; i<allBookings.size(); i++)
            {
                allBookings.get(i).setPosition(i+1);
            }
            c.close();
            showResults(allBookings);
            
        }
        catch (SQLException e)
        {
            
        }  
    }
    
    public void showResults(ArrayList<oncology> allBookings)
    {
        for(int i=0; i<allBookings.size(); i++)
        {
            oncology singleBooking = allBookings.get(i);
                     
            switch(singleBooking.getPosition())
            {
                case 1:
                    txtTime1.setText((singleBooking.getTime()).toString());
                    cbName1.setValue((singleBooking.getName()));
                    txtAge1.setText(""+singleBooking.getAge());
                    txtHospital1.setText(singleBooking.getHospitalNumber());
                    txtNumber1.setText(singleBooking.getNumber());
                    txtWristband1.setText(singleBooking.getWristband());
                    txtReason1.setText(singleBooking.getReason());
                    notesArray[0] = singleBooking.getNotes();
                    attendanceArray[0] = singleBooking.getAttendance();
                    break;
                case 2:
                    txtTime2.setText((singleBooking.getTime()).toString());
                    cbName2.setValue((singleBooking.getName()));
                    txtAge2.setText(""+singleBooking.getAge());
                    txtHospital2.setText(singleBooking.getHospitalNumber());
                    txtNumber2.setText(singleBooking.getNumber());
                    txtWristband2.setText(singleBooking.getWristband());
                    txtReason2.setText(singleBooking.getReason());
                    notesArray[1] = singleBooking.getNotes();
                    attendanceArray[1] = singleBooking.getAttendance();
                    break;
                case 3:
                    txtTime3.setText((singleBooking.getTime()).toString());
                    cbName3.setValue((singleBooking.getName()));
                    txtAge3.setText(""+singleBooking.getAge());
                    txtHospital3.setText(singleBooking.getHospitalNumber());
                    txtNumber3.setText(singleBooking.getNumber());
                    txtWristband3.setText(singleBooking.getWristband());
                    txtReason3.setText(singleBooking.getReason());
                    notesArray[2] = singleBooking.getNotes();
                    attendanceArray[2] = singleBooking.getAttendance();
                    break;
                case 4:
                    txtTime4.setText((singleBooking.getTime()).toString());
                    cbName4.setValue((singleBooking.getName()));
                    txtAge4.setText(""+singleBooking.getAge());
                    txtHospital4.setText(singleBooking.getHospitalNumber());
                    txtNumber4.setText(singleBooking.getNumber());
                    txtWristband4.setText(singleBooking.getWristband());
                    txtReason4.setText(singleBooking.getReason());
                    notesArray[3] = singleBooking.getNotes();
                    attendanceArray[3] = singleBooking.getAttendance();
                    break;
                case 5:
                    txtTime5.setText((singleBooking.getTime()).toString());
                    cbName5.setValue((singleBooking.getName()));
                    txtAge5.setText(""+singleBooking.getAge());
                    txtHospital5.setText(singleBooking.getHospitalNumber());
                    txtNumber5.setText(singleBooking.getNumber());
                    txtWristband5.setText(singleBooking.getWristband());
                    txtReason5.setText(singleBooking.getReason());
                    notesArray[4] = singleBooking.getNotes();
                    attendanceArray[4] = singleBooking.getAttendance();
                    break;
                case 6:
                    txtTime6.setText((singleBooking.getTime()).toString());
                    cbName6.setValue((singleBooking.getName()));
                    txtAge6.setText(""+singleBooking.getAge());
                    txtHospital6.setText(singleBooking.getHospitalNumber());
                    txtNumber6.setText(singleBooking.getNumber());
                    txtWristband6.setText(singleBooking.getWristband());
                    txtReason6.setText(singleBooking.getReason());
                    notesArray[5] = singleBooking.getNotes();
                    attendanceArray[5] = singleBooking.getAttendance();
                    break;
                case 7:
                    txtTime7.setText((singleBooking.getTime()).toString());
                    cbName7.setValue((singleBooking.getName()));
                    txtAge7.setText(""+singleBooking.getAge());
                    txtHospital7.setText(singleBooking.getHospitalNumber());
                    txtNumber7.setText(singleBooking.getNumber());
                    txtWristband7.setText(singleBooking.getWristband());
                    txtReason7.setText(singleBooking.getReason());
                    notesArray[6] = singleBooking.getNotes();
                    attendanceArray[6] = singleBooking.getAttendance();
                    break;
                case 8:
                    txtTime8.setText((singleBooking.getTime()).toString());
                    cbName8.setValue((singleBooking.getName()));
                    txtAge8.setText(""+singleBooking.getAge());
                    txtHospital8.setText(singleBooking.getHospitalNumber());
                    txtNumber8.setText(singleBooking.getNumber());
                    txtWristband8.setText(singleBooking.getWristband());
                    txtReason8.setText(singleBooking.getReason());
                    notesArray[7] = singleBooking.getNotes();
                    attendanceArray[7] = singleBooking.getAttendance();
                    break;
                case 9:
                    txtTime9.setText((singleBooking.getTime()).toString());
                    cbName9.setValue((singleBooking.getName()));
                    txtAge9.setText(""+singleBooking.getAge());
                    txtHospital9.setText(singleBooking.getHospitalNumber());
                    txtNumber9.setText(singleBooking.getNumber());
                    txtWristband9.setText(singleBooking.getWristband());
                    txtReason9.setText(singleBooking.getReason());
                    notesArray[8] = singleBooking.getNotes();
                    attendanceArray[8] = singleBooking.getAttendance();
                    break;
                case 10:
                    txtTime10.setText((singleBooking.getTime()).toString());
                    cbName10.setValue((singleBooking.getName()));
                    txtAge10.setText(""+singleBooking.getAge());
                    txtHospital10.setText(singleBooking.getHospitalNumber());
                    txtNumber10.setText(singleBooking.getNumber());
                    txtWristband10.setText(singleBooking.getWristband());
                    txtReason10.setText(singleBooking.getReason());
                    notesArray[9] = singleBooking.getNotes();
                    attendanceArray[9] = singleBooking.getAttendance();
                    break;
                case 11:
                    txtTime11.setText((singleBooking.getTime()).toString());
                    cbName11.setValue((singleBooking.getName()));
                    txtAge11.setText(""+singleBooking.getAge());
                    txtHospital11.setText(singleBooking.getHospitalNumber());
                    txtNumber11.setText(singleBooking.getNumber());
                    txtWristband11.setText(singleBooking.getWristband());
                    txtReason11.setText(singleBooking.getReason());
                    notesArray[10] = singleBooking.getNotes();
                    attendanceArray[10] = singleBooking.getAttendance();
                    break;    
                case 12:
                    txtTime12.setText((singleBooking.getTime()).toString());
                    cbName12.setValue((singleBooking.getName()));
                    txtAge12.setText(""+singleBooking.getAge());
                    txtHospital12.setText(singleBooking.getHospitalNumber());
                    txtNumber12.setText(singleBooking.getNumber());
                    txtWristband12.setText(singleBooking.getWristband());
                    txtReason12.setText(singleBooking.getReason());
                    notesArray[11] = singleBooking.getNotes();
                    attendanceArray[11] = singleBooking.getAttendance();
                    break;   
                case 13:
                    txtTime13.setText((singleBooking.getTime()).toString());
                    cbName13.setValue((singleBooking.getName()));
                    txtAge13.setText(""+singleBooking.getAge());
                    txtHospital13.setText(singleBooking.getHospitalNumber());
                    txtNumber13.setText(singleBooking.getNumber());
                    txtWristband13.setText(singleBooking.getWristband());
                    txtReason13.setText(singleBooking.getReason());
                    notesArray[12] = singleBooking.getNotes();
                    attendanceArray[12] = singleBooking.getAttendance();
                    break; 
                case 14:
                    txtTime14.setText((singleBooking.getTime()).toString());
                    cbName14.setValue((singleBooking.getName()));
                    txtAge14.setText(""+singleBooking.getAge());
                    txtHospital14.setText(singleBooking.getHospitalNumber());
                    txtNumber14.setText(singleBooking.getNumber());
                    txtWristband14.setText(singleBooking.getWristband());
                    txtReason14.setText(singleBooking.getReason());
                    notesArray[13] = singleBooking.getNotes();
                    attendanceArray[13] = singleBooking.getAttendance();
                    break; 
                default:
                    break;    
            }
             
        }
        
        int count = 0;
        for(TextField text : attendanceList)
        {
            codeBank.attendanceColour(text, attendanceArray[count]);
            count++;
        }
        
        int count2 = 0;
        for(TextField text2 : notesList)
        {
            codeBank.showNotes(text2, notesArray[count2]);
            count2++;
        }
                        
    }
    
    public void fillDropDowns()
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
            rs = stmt.executeQuery("SELECT Name FROM regular"); 
                
            cbName1.getItems().add("");
            cbName2.getItems().add("");
            cbName3.getItems().add("");
            cbName4.getItems().add("");
            cbName5.getItems().add("");
            cbName6.getItems().add("");
            cbName7.getItems().add("");
            cbName8.getItems().add("");
            cbName9.getItems().add("");
            cbName10.getItems().add("");
            cbName11.getItems().add("");
            cbName12.getItems().add("");
            cbName13.getItems().add("");
            cbName14.getItems().add("");
            
            
            while(rs.next())
            { 
                String name = rs.getString("Name");
               
                cbName1.getItems().add(name);
                cbName2.getItems().add(name);
                cbName3.getItems().add(name);
                cbName4.getItems().add(name);
                cbName5.getItems().add(name);
                cbName6.getItems().add(name);
                cbName7.getItems().add(name);
                cbName8.getItems().add(name);
                cbName9.getItems().add(name);
                cbName10.getItems().add(name);
                cbName11.getItems().add(name);
                cbName12.getItems().add(name);
                cbName13.getItems().add(name);
                cbName14.getItems().add(name);
                  
            }
            c.close();
            
        }
        catch (SQLException e)
        {
            
        } 
    }
    
    
    
    
    
    //Each method associated with the textfield for the bed numbers 
    @FXML public void Attendance1(){change(0);}
    @FXML public void Attendance2(){change(1);}
    @FXML public void Attendance3(){change(2);}
    @FXML public void Attendance4(){change(3);}
    @FXML public void Attendance5(){change(4);}
    @FXML public void Attendance6(){change(5);}
    @FXML public void Attendance7(){change(6);}
    @FXML public void Attendance8(){change(7);}
    @FXML public void Attendance9(){change(8);}
    @FXML public void Attendance10(){change(9);}
    @FXML public void Attendance11(){change(10);}
    @FXML public void Attendance12(){change(11);}
    @FXML public void Attendance13(){change(12);}
    @FXML public void Attendance14(){change(13);}
   
    
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
        
        codeBank.attendanceColour(attendanceList.get(arrayValue),attendanceArray[arrayValue]);
    }
    
    
    
     //Each method associated with the textfield for notes
    @FXML public void Notes1(){changeNotes(0);}
    @FXML public void Notes2(){changeNotes(1);}
    @FXML public void Notes3(){changeNotes(2);}
    @FXML public void Notes4(){changeNotes(3);}
    @FXML public void Notes5(){changeNotes(4);}
    @FXML public void Notes6(){changeNotes(5);}
    @FXML public void Notes7(){changeNotes(6);}
    @FXML public void Notes8(){changeNotes(7);}
    @FXML public void Notes9(){changeNotes(8);}
    @FXML public void Notes10(){changeNotes(9);}
    @FXML public void Notes11(){changeNotes(10);}
    @FXML public void Notes12(){changeNotes(11);}
    @FXML public void Notes13(){changeNotes(12);}
    @FXML public void Notes14(){changeNotes(13);}
  
    
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
                
        codeBank.showNotes(notesList.get(arrayValue),notesArray[arrayValue]);
    }  
    
    
    
    
    public void clearAll()
    {
        attendanceArray = new int[14];
        notesArray = new int[14];
        allBookings.clear();
     
        clear1();
        clear2();
        clear3();
        clear4();
        clear5();
        clear6();
        clear7();
        clear8();
        clear9();
        clear10();
        clear11();
        clear12();
        clear13();
        clear14();
    }
    
    public void clear1()
    {
        txtTime1.setText("");
        cbName1.setValue("");
        txtAge1.setText("");
        txtHospital1.setText("");
        txtNumber1.setText("");
        txtWristband1.setText("");
        txtReason1.setText("");
        attendanceArray[0] = 0;
        notesArray[0] = 0;
        codeBank.attendanceColour(txtAttendance1, 0);
        codeBank.showNotes(txtNotes1, 0);
        
    }
     
    public void clear2()
    {
        txtTime2.setText("");
        cbName2.setValue("");
        txtAge2.setText("");
        txtHospital2.setText("");
        txtNumber2.setText("");
        txtWristband2.setText("");
        txtReason2.setText("");
        attendanceArray[1] = 0;
        notesArray[1] = 0;
        codeBank.attendanceColour(txtAttendance2, 0);
        codeBank.showNotes(txtNotes2, 0);
    }

    public void clear3()
    {
        txtTime3.setText("");
        cbName3.setValue("");
        txtAge3.setText("");
        txtHospital3.setText("");
        txtNumber3.setText("");
        txtWristband3.setText("");
        txtReason3.setText("");
        attendanceArray[2] = 0;
        notesArray[2] = 0;
        codeBank.attendanceColour(txtAttendance3, 0);
        codeBank.showNotes(txtNotes3, 0);
    }
    
    public void clear4()
    {
        txtTime4.setText("");
        cbName4.setValue("");
        txtAge4.setText("");
        txtHospital4.setText("");
        txtNumber4.setText("");
        txtWristband4.setText("");
        txtReason4.setText("");
        attendanceArray[3] = 0;
        notesArray[3] = 0;
        codeBank.attendanceColour(txtAttendance4, 0);
        codeBank.showNotes(txtNotes4, 0);
    }

    public void clear5()
    {
        txtTime5.setText("");
        cbName5.setValue("");
        txtAge5.setText("");
        txtHospital5.setText("");
        txtNumber5.setText("");
        txtWristband5.setText("");
        txtReason5.setText("");
        attendanceArray[4] = 0;
        notesArray[4] = 0;
        codeBank.attendanceColour(txtAttendance5, 0);
        codeBank.showNotes(txtNotes5, 0);
    }

    public void clear6()
    {
        txtTime6.setText("");
        cbName6.setValue("");
        txtAge6.setText("");
        txtHospital6.setText("");
        txtNumber6.setText("");
        txtWristband6.setText("");
        txtReason6.setText("");
        attendanceArray[5] = 0;
        notesArray[5] = 0;
        codeBank.attendanceColour(txtAttendance6, 0);
        codeBank.showNotes(txtNotes6, 0);
    }

    public void clear7()
    {
        txtTime7.setText("");
        cbName7.setValue("");
        txtAge7.setText("");
        txtHospital7.setText("");
        txtNumber7.setText("");
        txtWristband7.setText("");
        txtReason7.setText("");
        attendanceArray[6] = 0;
        notesArray[6] = 0;
        codeBank.attendanceColour(txtAttendance7, 0);
        codeBank.showNotes(txtNotes7, 0);
    }

    public void clear8()
    {
        txtTime8.setText("");
        cbName8.setValue("");
        txtAge8.setText("");
        txtHospital8.setText("");
        txtNumber8.setText("");
        txtWristband8.setText("");
        txtReason8.setText("");
        attendanceArray[7] = 0;
        notesArray[7] = 0;
        codeBank.attendanceColour(txtAttendance8, 0);
        codeBank.showNotes(txtNotes8, 0);
    }

    public void clear9()
    {
        txtTime9.setText("");
        cbName9.setValue("");
        txtAge9.setText("");
        txtHospital9.setText("");
        txtNumber9.setText("");
        txtWristband9.setText("");
        txtReason9.setText("");
        attendanceArray[8] = 0;
        notesArray[8] = 0;
        codeBank.attendanceColour(txtAttendance9, 0);
        codeBank.showNotes(txtNotes9, 0);
    }
    
    public void clear10()
    {
        txtTime10.setText("");
        cbName10.setValue("");
        txtAge10.setText("");
        txtHospital10.setText("");
        txtNumber10.setText("");
        txtWristband10.setText("");
        txtReason10.setText("");
        attendanceArray[9] = 0;
        notesArray[9] = 0;
        codeBank.attendanceColour(txtAttendance10, 0);
        codeBank.showNotes(txtNotes10, 0);
    }

    public void clear11()
    {
        txtTime11.setText("");
        cbName11.setValue("");
        txtAge11.setText("");
        txtHospital11.setText("");
        txtNumber11.setText("");
        txtWristband11.setText("");
        txtReason11.setText("");
        attendanceArray[10] = 0;
        notesArray[10] = 0;
        codeBank.attendanceColour(txtAttendance11, 0);
        codeBank.showNotes(txtNotes11, 0);
    }

    public void clear12()
    {
        txtTime12.setText("");
        cbName12.setValue("");
        txtAge12.setText("");
        txtHospital12.setText("");
        txtNumber12.setText("");
        txtWristband12.setText("");
        txtReason12.setText("");
        attendanceArray[11] = 0;
        notesArray[11] = 0;
        codeBank.attendanceColour(txtAttendance12, 0);
        codeBank.showNotes(txtNotes12, 0);
    }

    public void clear13()
    {
        txtTime13.setText("");
        cbName13.setValue("");
        txtAge13.setText("");
        txtHospital13.setText("");
        txtNumber13.setText("");
        txtWristband13.setText("");
        txtReason13.setText("");
        attendanceArray[12] = 0;
        notesArray[12] = 0;
        codeBank.attendanceColour(txtAttendance13, 0);
        codeBank.showNotes(txtNotes13, 0);
    }
    
    public void clear14()
    {
        txtTime14.setText("");
        cbName14.setValue("");
        txtAge14.setText("");
        txtHospital14.setText("");
        txtNumber14.setText("");
        txtWristband14.setText("");
        txtReason14.setText("");
        attendanceArray[13] = 0;
        notesArray[13] = 0;
        codeBank.attendanceColour(txtAttendance14, 0);
        codeBank.showNotes(txtNotes14, 0);
    }
                    
        
 
    
    
    
    public void save(LocalDate today)
    {
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();
            
            String stringDate = codeBank.dateToString(today);          
            
            //implement query - SAVING EACH LINE                       
            String[] queries = new String[14];
            queries[0] = save1(stringDate);
            queries[1] = save2(stringDate);
            queries[2] = save3(stringDate);
            queries[3] = save4(stringDate);
            queries[4] = save5(stringDate);
            queries[5] = save6(stringDate);
            queries[6] = save7(stringDate);
            queries[7] = save8(stringDate);
            queries[8] = save9(stringDate);
            queries[9] = save10(stringDate);
            queries[10] = save11(stringDate);
            queries[11] = save12(stringDate);
            queries[12] = save13(stringDate);
            queries[13] = save14(stringDate);
            
            
            for(int i=0; i<queries.length; i++)
            {
               stmt.executeUpdate(queries[i]);
            }
              
                    
            c.close();
        }
        catch (SQLException e)
        {
            
        } 
    }
    
    
    
    
    
    public String save1(String date)
    {
        if(!txtTime1.getText().equals("") & !cbName1.getValue().equals("") & !txtReason1.getText().equals(""))
        {
            return ("REPLACE INTO oncology (Date, Time, Regular_HospitalNumber, Reason, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + txtTime1.getText() + "','"
                                                                                + txtHospital1.getText() + "','"
                                                                                + txtReason1.getText() + "','"
                                                                                + notesArray[0] + "','"
                                                                                + attendanceArray[0] + "')"
                    );
        }
        else
        {
            return "";
        } 
    } 
    
    public String save2(String date)
    {
        if(!txtTime2.getText().equals("") & !cbName2.getValue().equals("") & !txtReason2.getText().equals(""))
        {
            return ("REPLACE INTO oncology (Date, Time, Regular_HospitalNumber, Reason, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + txtTime2.getText() + "','"
                                                                                + txtHospital2.getText() + "','"
                                                                                + txtReason2.getText() + "','"
                                                                                + notesArray[1] + "','"
                                                                                + attendanceArray[1] + "')"
                    );
        }
        else
        {
            return "";
        } 
    } 
    
    public String save3(String date)
    {
        if(!txtTime3.getText().equals("") & !cbName3.getValue().equals("") & !txtReason3.getText().equals(""))
        {
            return ("REPLACE INTO oncology (Date, Time, Regular_HospitalNumber, Reason, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + txtTime3.getText() + "','"
                                                                                + txtHospital3.getText() + "','"
                                                                                + txtReason3.getText() + "','"
                                                                                + notesArray[2] + "','"
                                                                                + attendanceArray[2] + "')"
                    );
        }
        else
        {
            return "";
        } 
    } 
    
    public String save4(String date)
    {
        if(!txtTime4.getText().equals("") & !cbName4.getValue().equals("") & !txtReason4.getText().equals(""))
        {
            return ("REPLACE INTO oncology (Date, Time, Regular_HospitalNumber, Reason, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + txtTime4.getText() + "','"
                                                                                + txtHospital4.getText() + "','"
                                                                                + txtReason4.getText() + "','"
                                                                                + notesArray[3] + "','"
                                                                                + attendanceArray[3] + "')"
                    );
        }
        else
        {
            return "";
        } 
    } 
    
    public String save5(String date)
    {
        if(!txtTime5.getText().equals("") & !cbName5.getValue().equals("") & !txtReason5.getText().equals(""))
        {
            return ("REPLACE INTO oncology (Date, Time, Regular_HospitalNumber, Reason, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + txtTime5.getText() + "','"
                                                                                + txtHospital5.getText() + "','"
                                                                                + txtReason5.getText() + "','"
                                                                                + notesArray[4] + "','"
                                                                                + attendanceArray[4] + "')"
                    );
        }
        else
        {
            return "";
        } 
    } 
    
    public String save6(String date)
    {
        if(!txtTime6.getText().equals("") & !cbName6.getValue().equals("") & !txtReason6.getText().equals(""))
        {
            return ("REPLACE INTO oncology (Date, Time, Regular_HospitalNumber, Reason, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + txtTime6.getText() + "','"
                                                                                + txtHospital6.getText() + "','"
                                                                                + txtReason6.getText() + "','"
                                                                                + notesArray[5] + "','"
                                                                                + attendanceArray[5] + "')"
                    );
        }
        else
        {
            return "";
        } 
    } 
    
    public String save7(String date)
    {
        if(!txtTime7.getText().equals("") & !cbName7.getValue().equals("") & !txtReason7.getText().equals(""))
        {
            return ("REPLACE INTO oncology (Date, Time, Regular_HospitalNumber, Reason, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + txtTime7.getText() + "','"
                                                                                + txtHospital7.getText() + "','"
                                                                                + txtReason7.getText() + "','"
                                                                                + notesArray[6] + "','"
                                                                                + attendanceArray[6] + "')"
                    );
        }
        else
        {
            return "";
        } 
    } 
    
    public String save8(String date)
    {
        if(!txtTime8.getText().equals("") & !cbName8.getValue().equals("") & !txtReason8.getText().equals(""))
        {
            return ("REPLACE INTO oncology (Date, Time, Regular_HospitalNumber, Reason, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + txtTime8.getText() + "','"
                                                                                + txtHospital8.getText() + "','"
                                                                                + txtReason8.getText() + "','"
                                                                                + notesArray[7] + "','"
                                                                                + attendanceArray[7] + "')"
                    );
        }
        else
        {
            return "";
        } 
    } 
    
    
    public String save9(String date)
    {
        if(!txtTime9.getText().equals("") & !cbName9.getValue().equals("") & !txtReason9.getText().equals(""))
        {
            return ("REPLACE INTO oncology (Date, Time, Regular_HospitalNumber, Reason, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + txtTime9.getText() + "','"
                                                                                + txtHospital9.getText() + "','"
                                                                                + txtReason9.getText() + "','"
                                                                                + notesArray[8] + "','"
                                                                                + attendanceArray[8] + "')"
                    );
        }
        else
        {
            return "";
        } 
    } 
    
    
    public String save10(String date)
    {
        if(!txtTime10.getText().equals("") & !cbName10.getValue().equals("") & !txtReason10.getText().equals(""))
        {
            return ("REPLACE INTO oncology (Date, Time, Regular_HospitalNumber, Reason, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + txtTime10.getText() + "','"
                                                                                + txtHospital10.getText() + "','"
                                                                                + txtReason10.getText() + "','"
                                                                                + notesArray[9] + "','"
                                                                                + attendanceArray[9] + "')"
                    );
        }
        else
        {
            return "";
        } 
    } 
    
    
    public String save11(String date)
    {
        if(!txtTime11.getText().equals("") & !cbName11.getValue().equals("") & !txtReason11.getText().equals(""))
        {
            return ("REPLACE INTO oncology (Date, Time, Regular_HospitalNumber, Reason, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + txtTime11.getText() + "','"
                                                                                + txtHospital11.getText() + "','"
                                                                                + txtReason11.getText() + "','"
                                                                                + notesArray[10] + "','"
                                                                                + attendanceArray[10] + "')"
                    );
        }
        else
        {
            return "";
        } 
    } 
    
    
    public String save12(String date)
    {
        if(!txtTime12.getText().equals("") & !cbName12.getValue().equals("") & !txtReason12.getText().equals(""))
        {
            return ("REPLACE INTO oncology (Date, Time, Regular_HospitalNumber, Reason, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + txtTime12.getText() + "','"
                                                                                + txtHospital12.getText() + "','"
                                                                                + txtReason12.getText() + "','"
                                                                                + notesArray[11] + "','"
                                                                                + attendanceArray[11] + "')"
                    );
        }
        else
        {
            return "";
        } 
    } 
    
    
    public String save13(String date)
    {
        if(!txtTime13.getText().equals("") & !cbName13.getValue().equals("") & !txtReason13.getText().equals(""))
        {
            return ("REPLACE INTO oncology (Date, Time, Regular_HospitalNumber, Reason, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + txtTime13.getText() + "','"
                                                                                + txtHospital13.getText() + "','"
                                                                                + txtReason13.getText() + "','"
                                                                                + notesArray[12] + "','"
                                                                                + attendanceArray[12] + "')"
                    );
        }
        else
        {
            return "";
        } 
    } 
    
    public String save14(String date)
    {
        if(!txtTime14.getText().equals("") & !cbName14.getValue().equals("") & !txtReason14.getText().equals(""))
        {
            return ("REPLACE INTO oncology (Date, Time, Regular_HospitalNumber, Reason, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + txtTime14.getText() + "','"
                                                                                + txtHospital14.getText() + "','"
                                                                                + txtReason14.getText() + "','"
                                                                                + notesArray[13] + "','"
                                                                                + attendanceArray[13] + "')"
                    );
        }
        else
        {
            return "";
        } 
    } 
    
    
    
    @FXML public void LoadPatient1(){String patient = cbName1.getValue().toString(); Load(0, patient);}
    @FXML public void LoadPatient2(){String patient = cbName2.getValue().toString(); Load(1, patient);}
    @FXML public void LoadPatient3(){String patient = cbName3.getValue().toString(); Load(2, patient);}
    @FXML public void LoadPatient4(){String patient = cbName4.getValue().toString(); Load(3, patient);}
    @FXML public void LoadPatient5(){String patient = cbName5.getValue().toString(); Load(4, patient);}
    @FXML public void LoadPatient6(){String patient = cbName6.getValue().toString(); Load(5, patient);}
    @FXML public void LoadPatient7(){String patient = cbName7.getValue().toString(); Load(6, patient);}
    @FXML public void LoadPatient8(){String patient = cbName8.getValue().toString(); Load(7, patient);}
    @FXML public void LoadPatient9(){String patient = cbName9.getValue().toString(); Load(8, patient);}
    @FXML public void LoadPatient10(){String patient = cbName10.getValue().toString(); Load(9, patient);}
    @FXML public void LoadPatient11(){String patient = cbName11.getValue().toString(); Load(10, patient);}
    @FXML public void LoadPatient12(){String patient = cbName12.getValue().toString(); Load(11, patient);}
    @FXML public void LoadPatient13(){String patient = cbName13.getValue().toString(); Load(12, patient);}
    @FXML public void LoadPatient14(){String patient = cbName14.getValue().toString(); Load(13, patient);}
    
    
    
     @FXML
    public void Load(int position, String patient)
    {
        if(patient.equals(""))
        {
            ageList.get(position).setText(""); 
            hospitalList.get(position).setText("");
            numberList.get(position).setText("");
            wristbandList.get(position).setText("");
        }
        else
        {
            try
            {
                // open a connection
                Connection c = DatabaseConnector.activateConnection();
                c.setAutoCommit( true ); 
                ResultSet rs;
                // when creating a statement object, you MUST use a connection object to call the instance method
                Statement stmt = c.createStatement();

                rs = stmt.executeQuery("SELECT * FROM regular WHERE Name = '" + patient + "'");

                while(rs.next())
                { 
                    String dob = rs.getString("DateOfBirth");
                    LocalDate dobDate = codeBank.stringToDate(dob);
                    Period period = Period.between(dobDate, LocalDate.now());
                    ageList.get(position).setText(""+period.getYears()); 

                    hospitalList.get(position).setText(rs.getString("HospitalNumber"));
                    numberList.get(position).setText(rs.getString("Number"));
                    wristbandList.get(position).setText(rs.getString("Wristband"));
                }


                c.close();
            }
            catch (SQLException e)
            {

            } 
        }
    }
    
    
    public void delete()
    {
       deleteOption(txtTime1, txtHospital1, txtReason1, 1); 
       deleteOption(txtTime2, txtHospital2, txtReason2, 2);
       deleteOption(txtTime3, txtHospital3, txtReason3, 3);
       deleteOption(txtTime4, txtHospital4, txtReason4, 4);
       deleteOption(txtTime5, txtHospital5, txtReason5, 5);
       deleteOption(txtTime6, txtHospital6, txtReason6, 6);
       deleteOption(txtTime7, txtHospital7, txtReason7, 7);
       deleteOption(txtTime8, txtHospital8, txtReason8, 8);
       deleteOption(txtTime9, txtHospital9, txtReason9, 9);
       deleteOption(txtTime10, txtHospital10, txtReason10, 10);
       deleteOption(txtTime11, txtHospital11, txtReason11, 11);
       deleteOption(txtTime12, txtHospital12, txtReason12, 12);
       deleteOption(txtTime13, txtHospital13, txtReason13, 13);
       deleteOption(txtTime14, txtHospital14, txtReason14, 14);
    }
    
    
    
    public void deleteOption(TextField time, TextField hospital, TextArea reason, int value)
    {
        //https://stackoverflow.com/questions/32980159/javafx-append-to-right-click-menu-for-textfield accessed 18/2
        //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ContextMenu.html accessed 18/2
        ContextMenu contextMenu1 = new ContextMenu();
        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(new EventHandler<ActionEvent>() 
        {
            public void handle(ActionEvent e) 
            {
                try
                {
                    // open a connection
                    Connection c = DatabaseConnector.activateConnection();
                    c.setAutoCommit( true ); 

                    // when creating a statement object, you MUST use a connection object to call the instance method
                    Statement stmt = c.createStatement();
                    
                    String sql = "DELETE FROM oncology WHERE Date = '" + codeBank.dateToString(codeBank.getCurrentDate()) + "' AND Time = '" + time.getText() + "' AND Regular_HospitalNumber = '" + hospital.getText() +"' AND Reason ='" + reason.getText() + "'";
                    
                    stmt.executeUpdate(sql);                    
                    
                    c.close();
                }
                catch (SQLException x)
                {

                } 
                clear(value);
            }
        });
        contextMenu1.getItems().add(delete);
        time.setContextMenu(contextMenu1);
    }
    
    
    public void clear(int value)
    {
        switch(value)
        {
            case 1:     clear1(); break;
            case 2:     clear2(); break;
            case 3:     clear3(); break;
            case 4:     clear4(); break;
            case 5:     clear5(); break;
            case 6:     clear6(); break;
            case 7:     clear7(); break;
            case 8:     clear8(); break;
            case 9:     clear9(); break;
            case 10:    clear10(); break;
            case 11:    clear11(); break;
            case 12:    clear12(); break;
            case 13:    clear13(); break;
            case 14:    clear14(); break;
            default:    break;
            
        }
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
            rs = stmt.executeQuery("SELECT * FROM staff, specificworking WHERE specificworking.Date = '" + stringDate + "' AND staff.ID = specificworking.ID AND specificworking.Place = 'Oncology'"); 
                        
            while(rs.next())
            { 
                String firstname = rs.getString("FirstName");
                int ID = rs.getInt("ID");
                
                String text = "(" +ID + ") " + firstname;
                
                cbStaff.setValue(text);
                
            }
            c.close();
        }
        catch (SQLException e)
        {
            
        } 
    }
    
    
}//END OF CLASS
