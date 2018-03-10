/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oncology;

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
import static java.time.LocalTime.now;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    @FXML private List<TextField> timeList;
    @FXML private List<ChoiceBox> nameList;
    @FXML private List<TextField> ageList;
    @FXML private List<TextField> hospitalList;
    @FXML private List<TextField> numberList;
    @FXML private List<TextField> wristbandList;
    @FXML private List<TextArea> reasonList;
    @FXML private List<TextField> notesList;
    
    @FXML private Hyperlink hlHelp = new Hyperlink();
      
    int[] attendanceArray = new int[14];
    int[] notesArray = new int[14];
    
    ArrayList<Boolean> deleted = new ArrayList<Boolean>();   
    ArrayList<String> patients = new ArrayList<String>();
    ArrayList<oncology> allBookings = new ArrayList<oncology>();
    ObservableList<String> workingStaff;
    
    boolean issue = false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        showInformation(codeBank.getCurrentDate());
        fillDropDowns();
        delete();
               
        for(int i=0; i<14; i++)
        {
            TextField selected = timeList.get(i);
            timeList.get(i).focusedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldV, Boolean newV) -> {
            if (!newV) 
            { 
                checkingTime(selected);
            }
            });
        }
        
       
    
    }
    
    public void checkingTime(TextField selected)
    {
        String value = selected.getText();
        
        if(!codeBank.checkTime(value))
        {
            codeBank.timeError();
        }
    }
    
    
    
    
    public void showInformation(LocalDate date)
    {
        clearAll();
        fillDropDowns();
        
        for(int i=0; i<14; i++)
        {
            deleted.add(i, false);
        }
        
        workingStaff = codeBank.fillStaffDropDowns();
        cbStaff.getItems().addAll(workingStaff);
        showStaff(codeBank.getCurrentDate());
        
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
                    
            String sql = "SELECT * FROM oncology WHERE oncology.Date = '" + stringDate + "'";
            rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                //get String into LocalDate
                String Stringdate = rs.getString("Date"); //LocalDate
                LocalDate localDate = codeBank.stringToDate(Stringdate);
                
                //get String into LocalTime
                String time = rs.getString("Time"); //Time
                LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);

                String hospitalNumber = rs.getString("Regular_HospitalNumber");
                String reason = rs.getString("Reason");
                int notes = rs.getInt("Notes");
                int attendance = rs.getInt("Attendance");
                
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                String number = rs.getString("ContactNumber");
                String wristband = rs.getString("Wristband");
                
                oncology booking = new oncology(localDate, localTime, name, age, hospitalNumber, number, wristband, reason, notes, attendance);
                allBookings.add(booking);  
                
            }   
            c.close();
        }
        catch(SQLException e)
        {
            
        }
            for(int i=0; i<allBookings.size(); i++)
            {
                if(allBookings.get(i).getName() == null)
                {
                    try
                    {
                        // open a connection
                        Connection c2 = DatabaseConnector.activateConnection();
                        c2.setAutoCommit( true ); 
                        ResultSet rs2 ;

                        // when creating a statement object, you MUST use a connection object to call the instance method
                        Statement stmt2 = c2.createStatement();

                        //make localdate into string
                        String sql = "SELECT * FROM regular WHERE regular.HospitalNumber ='" + allBookings.get(i).getHospitalNumber() + "'";
                        rs2 = stmt2.executeQuery(sql);

                        while(rs2.next())
                        {

                            String firstName = rs2.getString("FirstName");
                            String lastName = rs2.getString("LastName");
                            allBookings.get(i).setName(firstName + " " + lastName);
                                                        
                            String dob = rs2.getString("DateOfBirth");
                            LocalDate dobDate = codeBank.stringToDate(dob);
                            Period period = Period.between(dobDate, LocalDate.now());
                            allBookings.get(i).setAge(period.getYears()); 
                            
                            allBookings.get(i).setNumber(rs2.getString("Number"));
                            allBookings.get(i).setWristband(rs2.getString("Wristband"));

                            
                        }   
                        c2.close();
                    }
                    catch (SQLException ex)
                    {
                        
                    }
                }
                deleted.set(i, true);
            }
            
            Collections.sort(allBookings);
           
            for(int i=0; i<allBookings.size(); i++)
            {
                allBookings.get(i).setPosition(i+1);
            }
            showResults(allBookings);
            
         
    }
    
    public void showResults(ArrayList<oncology> allBookings)
    {
        for(int i=0; i<allBookings.size(); i++)
        {
            oncology singleBooking = allBookings.get(i);
        
            timeList.get(i).setText((singleBooking.getTime()).toString());
            
            if(!inList(singleBooking.getName()))
            {
                nameList.get(i).getItems().add(singleBooking.getName());
            }
            nameList.get(i).setValue(singleBooking.getName());
            
            ageList.get(i).setText("" + singleBooking.getAge());
            hospitalList.get(i).setText(singleBooking.getHospitalNumber());
            numberList.get(i).setText(singleBooking.getNumber());
            wristbandList.get(i).setText(singleBooking.getWristband());
            reasonList.get(i).setText(singleBooking.getReason());
            notesArray[i] = singleBooking.getNotes();
            attendanceArray[i] = singleBooking.getAttendance();

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
    
    public boolean inList(String name)
    {
        for(int i=0; i<patients.size(); i++)
        {
            if(patients.get(i).equals(name))
            {
                return true;
            }
        }
        return false;
    }
    
    
                        
    
    //Fill the name drop downs with the names of regular attenders 
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
            rs = stmt.executeQuery("SELECT FirstName, LastName FROM regular WHERE Oncology='" + 1 + "'"); 
            
            patients.add(" ");
            
            while(rs.next())
            { 
                String FirstName = rs.getString("FirstName");
                String LastName = rs.getString("LastName");
                
                patients.add(FirstName + " " + LastName);
            }
            
            for(int i=0; i<14; i++)
            {
                nameList.get(i).getItems().addAll(patients);
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
    
    
    
    
    public boolean beforeSave()
    {
        for(int i=0; i<14; i++)
        {
            if(!timeList.get(i).getText().equals("") & nameList.get(i).getValue() != null & !reasonList.get(i).getText().equals(""))
            {
                
            }
            else if(!timeList.get(i).getText().equals("") || nameList.get(i).getValue() != null || !reasonList.get(i).getText().equals(""))
            {
                codeBank.missingError();
                return false;
            } 
        }
        return true;
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
            
            for(int i=0; i<14; i++)
            {
                String sql;
                if(deleted.get(i) == false)
                {
                    sql = SQLLine(i, stringDate);
                }
                else
                {
                    sql = SQLLineDeleted(i, stringDate);
                }
                
                stmt.executeUpdate(sql);
            }
            c.close();
            
            saveStaff();
            
            clearAll();
        }
        catch (SQLException e)
        {
            Logger.getLogger(OncologyScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        } 
        
    }
    
    public String SQLLine(int i, String date)
    {
        if(nameList.get(i).getValue()==null)
        {
            return "";
        }
        else
        {
            if(!timeList.get(i).getText().equals("") & !nameList.get(i).getValue().equals("") & !reasonList.get(i).getText().equals(""))
            {
                return ("REPLACE INTO oncology (Date, Time, Regular_HospitalNumber, Reason, Notes, Attendance) VALUES('"      
                                                                                    + date + "','"
                                                                                    + timeList.get(i).getText() + "','"
                                                                                    + hospitalList.get(i).getText() + "','"
                                                                                    + reasonList.get(i).getText() + "','"
                                                                                    + notesArray[i] + "','"
                                                                                    + attendanceArray[i] + "')"
                        );
            }
            else
            {
                return "";
            }
        }
        
    }   
    
    
    public String SQLLineDeleted(int i, String date)
    {
        if(nameList.get(i).getValue()==null)
        {
            return "";
        }
        else
        {
            if(!timeList.get(i).getText().equals("") & !nameList.get(i).getValue().equals("") & !reasonList.get(i).getText().equals(""))
            {
                return ("REPLACE INTO oncology (Date, Time, Regular_HospitalNumber, Reason, Notes, Attendance, Name, Age, ContactNumber, Wristband) VALUES('"      
                                                                                    + date + "','"
                                                                                    + timeList.get(i).getText() + "','"
                                                                                    + hospitalList.get(i).getText() + "','"
                                                                                    + reasonList.get(i).getText() + "','"
                                                                                    + notesArray[i] + "','"
                                                                                    + attendanceArray[i] + "','"
                                                                                    + nameList.get(i).getValue().toString() + "','"
                                                                                    + ageList.get(i).getText() + "','"
                                                                                    + numberList.get(i).getText() + "','"
                                                                                    + wristbandList.get(i).getText() + "')"
                        );
            }
            else
            {
                return "";
            }
        }
        
    }   
    
    
  
    public void saveStaff()
    {
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();
            
            String date = codeBank.dateToString(codeBank.getCurrentDate());
            
            String staff = cbStaff.getValue().toString();
            
            staff = staff.substring(staff.indexOf("(") + 1);
            staff = staff.substring(0, staff.indexOf(")"));
            
            String sql = "REPLACE INTO specificworking (Date, Place, ID) VALUES('"
                                                            + date + "','Oncology','"                                           
                                                            + staff + "')";
            
            stmt.executeUpdate(sql);                 
                    
            c.close();
        }
        catch (SQLException e)
        {
            
        } 
        catch(NullPointerException n)
        {
            
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
   
    
    

    
    
    
    
    
    
    
    
    
    
    
    //Loading a patients information when you select them from the drop down 
    
    @FXML public void LoadPatient1()
    {
        if(cbName1.getValue() != null)
        {
            String patient = cbName1.getValue().toString(); 
            Load(0, patient);
        }
    }
    
    @FXML public void LoadPatient2()
    {
        if(cbName2.getValue() != null)
        {
            String patient = cbName2.getValue().toString(); 
            Load(1, patient);
        }
    }
    
    @FXML public void LoadPatient3()
    {
        if(cbName3.getValue() != null)
        {
            String patient = cbName3.getValue().toString(); 
            Load(2, patient);
        }
    }
    
    @FXML public void LoadPatient4()
    {
        if(cbName4.getValue() != null)
        {
            String patient = cbName4.getValue().toString(); 
            Load(3, patient);
        }
    }
    
    @FXML public void LoadPatient5()
    {
        if(cbName5.getValue() != null)
        {
            String patient = cbName5.getValue().toString(); 
            Load(4, patient);
        }
    }
    
    @FXML public void LoadPatient6()
    {
        if(cbName6.getValue() != null)
        {
            String patient = cbName6.getValue().toString(); 
            Load(5, patient);
        }
    }
    
    @FXML public void LoadPatient7()
    {
        if(cbName7.getValue() != null)
        {
            String patient = cbName7.getValue().toString(); 
            Load(6, patient);
        }
    }
    
    @FXML public void LoadPatient8()
    {
        if(cbName8.getValue() != null)
        {
            String patient = cbName8.getValue().toString(); 
            Load(7, patient);
        }
    }
    
    @FXML public void LoadPatient9()
    {
        if(cbName9.getValue() != null)
        {
            String patient = cbName9.getValue().toString(); 
            Load(8, patient);
        }
    }
    
    @FXML public void LoadPatient10()
    {
        if(cbName10.getValue() != null)
        {
            String patient = cbName10.getValue().toString(); 
            Load(9, patient);
        }
    }
    
    @FXML public void LoadPatient11()
    {
        if(cbName11.getValue() != null)
        {
            String patient = cbName11.getValue().toString(); 
            Load(10, patient);
        }
    }
    
    @FXML public void LoadPatient12()
    {
        if(cbName12.getValue() != null)
        {
            String patient = cbName12.getValue().toString(); 
            Load(11, patient);
        }
    }
    
    @FXML public void LoadPatient13()
    {
        if(cbName13.getValue() != null)
        {
            String patient = cbName13.getValue().toString(); 
            Load(12, patient);
        }
    }
    
    @FXML public void LoadPatient14()
    {
        if(cbName14.getValue() != null)
        {
            String patient = cbName14.getValue().toString(); 
            Load(13, patient);
        }
    }
    
    
    
    
    
    @FXML
    public void Load(int position, String patient)
    {
        if(patient == null || patient.equals(""))
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
                String[] name = patient.split(" ");
                
                // open a connection
                Connection c = DatabaseConnector.activateConnection();
                c.setAutoCommit( true ); 
                ResultSet rs;
                // when creating a statement object, you MUST use a connection object to call the instance method
                Statement stmt = c.createStatement();

                rs = stmt.executeQuery("SELECT * FROM regular WHERE FirstName = '" + name[0] + "' AND LastName ='" + name[1] + "'");

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
                    Logger.getLogger(OncologyScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
            } 
        }
        
    }
    
    
   public void clearAll()
    {
        cbStaff.getItems().clear();
        
        if(workingStaff != null)
        {
            workingStaff.clear();
        }
        
        issue = false;
        patients.clear();
        attendanceArray = new int[14];
        notesArray = new int[14];
        allBookings.clear();
                
        for(int i=0; i< 14; i++)
        {
            clearSingle(i);
        }
    }
    
    public void clearSingle(int i)
    {
        nameList.get(i).getItems().clear();
        
        timeList.get(i).setText("");       
        ageList.get(i).setText("");
        hospitalList.get(i).setText("");
        numberList.get(i).setText("");
        wristbandList.get(i).setText("");
        reasonList.get(i).setText("");
        attendanceArray[i] = 0;
        notesArray[i] = 0;
        codeBank.attendanceColour(attendanceList.get(i), 0);
        codeBank.showNotes(notesList.get(i), 0);
         
    }
    
    public void delete()
    {
        for(int i=0; i<14; i++)
        {
            deleteOption(i);
        }
    }
    
    
    public void deleteOption(int i)
    {
        TextField time = timeList.get(i);
        ChoiceBox name = nameList.get(i);
        TextField hospital = hospitalList.get(i);

        //https://stackoverflow.com/questions/32980159/javafx-append-to-right-click-menu-for-textfield accessed 18/2
        //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ContextMenu.html accessed 18/2
        ContextMenu contextMenu1 = new ContextMenu();
        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    // open a connection
                    Connection c = DatabaseConnector.activateConnection();
                    c.setAutoCommit(true);

                    // when creating a statement object, you MUST use a connection object to call the instance method
                    Statement stmt = c.createStatement();

                    String sql = "DELETE FROM oncology WHERE Date = '" + codeBank.dateToString(codeBank.getCurrentDate()) + "' AND Time = '" + time.getText() + "'AND Regular_HospitalNumber ='" + hospital.getText() + "'";
                    stmt.executeUpdate(sql);
                    

                    c.close();
                } catch (SQLException x) {

                }
                clearSingle(i);
            }
        });
        contextMenu1.getItems().add(delete);
        timeList.get(i).setContextMenu(contextMenu1);

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
            
            HDC.show("Oncology");
            
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
    
    
    
    
    
    
    
    
    
    
    
    

    
    
}//END OF CLASS
