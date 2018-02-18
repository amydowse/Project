/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nonbed;

import common.DatabaseConnector;
import common.codeBank;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import oncology.oncology;

/**
 *
 * @author amydo
 */
public class NonbedScreenDocumentController implements Initializable
{
    //FXML Elements
    @FXML TextField txtAttendance1 = new TextField();
    @FXML TextField txtTime1 = new TextField();
    @FXML TextField txtName1 = new TextField();
    @FXML TextField txtAge1 = new TextField();
    @FXML TextField txtHospital1 = new TextField();
    @FXML ChoiceBox cbProcedure1 = new ChoiceBox();
    @FXML TextArea txtReason1 = new TextArea();
    @FXML TextField txtNotes1 = new TextField();
    
    @FXML TextField txtAttendance2 = new TextField();
    @FXML TextField txtTime2 = new TextField();
    @FXML TextField txtName2 = new TextField();
    @FXML TextField txtAge2 = new TextField();
    @FXML TextField txtHospital2 = new TextField();
    @FXML ChoiceBox cbProcedure2 = new ChoiceBox();
    @FXML TextArea txtReason2 = new TextArea();
    @FXML TextField txtNotes2 = new TextField();
    
    @FXML TextField txtAttendance3 = new TextField();
    @FXML TextField txtTime3 = new TextField();
    @FXML TextField txtName3 = new TextField();
    @FXML TextField txtAge3 = new TextField();
    @FXML TextField txtHospital3 = new TextField();
    @FXML ChoiceBox cbProcedure3 = new ChoiceBox();
    @FXML TextArea txtReason3 = new TextArea();
    @FXML TextField txtNotes3 = new TextField();
    
    @FXML TextField txtAttendance4 = new TextField();
    @FXML TextField txtTime4 = new TextField();
    @FXML TextField txtName4 = new TextField();
    @FXML TextField txtAge4 = new TextField();
    @FXML TextField txtHospital4 = new TextField();
    @FXML ChoiceBox cbProcedure4 = new ChoiceBox();
    @FXML TextArea txtReason4 = new TextArea();
    @FXML TextField txtNotes4 = new TextField();
    
    @FXML TextField txtAttendance5 = new TextField();
    @FXML TextField txtTime5 = new TextField();
    @FXML TextField txtName5 = new TextField();
    @FXML TextField txtAge5 = new TextField();
    @FXML TextField txtHospital5 = new TextField();
    @FXML ChoiceBox cbProcedure5 = new ChoiceBox();
    @FXML TextArea txtReason5 = new TextArea();
    @FXML TextField txtNotes5 = new TextField();
    
    @FXML TextField txtAttendance6 = new TextField();
    @FXML TextField txtTime6 = new TextField();
    @FXML TextField txtName6 = new TextField();
    @FXML TextField txtAge6 = new TextField();
    @FXML TextField txtHospital6 = new TextField();
    @FXML ChoiceBox cbProcedure6 = new ChoiceBox();
    @FXML TextArea txtReason6 = new TextArea();
    @FXML TextField txtNotes6 = new TextField();
    
    @FXML TextField txtAttendance7 = new TextField();
    @FXML TextField txtTime7 = new TextField();
    @FXML TextField txtName7 = new TextField();
    @FXML TextField txtAge7 = new TextField();
    @FXML TextField txtHospital7 = new TextField();
    @FXML ChoiceBox cbProcedure7 = new ChoiceBox();
    @FXML TextArea txtReason7 = new TextArea();
    @FXML TextField txtNotes7 = new TextField();
    
    @FXML TextField txtAttendance8 = new TextField();
    @FXML TextField txtTime8 = new TextField();
    @FXML TextField txtName8 = new TextField();
    @FXML TextField txtAge8 = new TextField();
    @FXML TextField txtHospital8 = new TextField();
    @FXML ChoiceBox cbProcedure8 = new ChoiceBox();
    @FXML TextArea txtReason8 = new TextArea();
    @FXML TextField txtNotes8 = new TextField();
    
    @FXML TextField txtAttendance9 = new TextField();
    @FXML TextField txtTime9 = new TextField();
    @FXML TextField txtName9 = new TextField();
    @FXML TextField txtAge9 = new TextField();
    @FXML TextField txtHospital9 = new TextField();
    @FXML ChoiceBox cbProcedure9 = new ChoiceBox();
    @FXML TextArea txtReason9 = new TextArea();
    @FXML TextField txtNotes9 = new TextField();
    
    @FXML TextField txtAttendance10 = new TextField();
    @FXML TextField txtTime10 = new TextField();
    @FXML TextField txtName10 = new TextField();
    @FXML TextField txtAge10 = new TextField();
    @FXML TextField txtHospital10 = new TextField();
    @FXML ChoiceBox cbProcedure10 = new ChoiceBox();
    @FXML TextArea txtReason10 = new TextArea();
    @FXML TextField txtNotes10 = new TextField();
    
    @FXML TextField txtAttendance11 = new TextField();
    @FXML TextField txtTime11 = new TextField();
    @FXML TextField txtName11 = new TextField();
    @FXML TextField txtAge11 = new TextField();
    @FXML TextField txtHospital11 = new TextField();
    @FXML ChoiceBox cbProcedure11 = new ChoiceBox();
    @FXML TextArea txtReason11 = new TextArea();
    @FXML TextField txtNotes11 = new TextField();
    
    @FXML TextField txtAttendance12 = new TextField();
    @FXML TextField txtTime12 = new TextField();
    @FXML TextField txtName12 = new TextField();
    @FXML TextField txtAge12 = new TextField();
    @FXML TextField txtHospital12 = new TextField();
    @FXML ChoiceBox cbProcedure12 = new ChoiceBox();
    @FXML TextArea txtReason12 = new TextArea();
    @FXML TextField txtNotes12 = new TextField();
    
    @FXML TextField txtAttendance13 = new TextField();
    @FXML TextField txtTime13 = new TextField();
    @FXML TextField txtName13 = new TextField();
    @FXML TextField txtAge13 = new TextField();
    @FXML TextField txtHospital13 = new TextField();
    @FXML ChoiceBox cbProcedure13 = new ChoiceBox();
    @FXML TextArea txtReason13 = new TextArea();
    @FXML TextField txtNotes13 = new TextField();
    
    @FXML TextField txtAttendance14 = new TextField();
    @FXML TextField txtTime14 = new TextField();
    @FXML TextField txtName14 = new TextField();
    @FXML TextField txtAge14 = new TextField();
    @FXML TextField txtHospital14 = new TextField();
    @FXML ChoiceBox cbProcedure14 = new ChoiceBox();
    @FXML TextArea txtReason14 = new TextArea();
    @FXML TextField txtNotes14 = new TextField();
   
    
    
    @FXML private List<TextField> attendanceList;
    @FXML private List<TextField> timeList;
    @FXML private List<TextField> nameList;
    @FXML private List<TextField> ageList;
    @FXML private List<TextField> hospitalList;
    @FXML private List<ChoiceBox> procedureList;
    @FXML private List<TextArea> reasonList;
    @FXML private List<TextField> notesList;
   
    int[] attendanceArray = new int[14];
    int[] notesArray = new int[14];
    
    ArrayList<nonbed> allBookings = new ArrayList<nonbed>();

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        showInformation(codeBank.getCurrentDate());
        fillDropDowns();
        delete();
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
            rs = stmt.executeQuery("SELECT * FROM nonbed WHERE Date = '" + stringDate + "'" );
           
            while(rs.next())
            { 
                //get String into LocalDate
                String Stringdate = rs.getString("Date"); //LocalDate
                LocalDate localDate = codeBank.stringToDate(Stringdate);
                
                //get String into LocalTime
                String time = rs.getString("Time"); //Time
                LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
              
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                String hospitalNumber = rs.getString("HospitalNumber");
                String procedure = rs.getString("Procedure");
                String reason = rs.getString("Reason");
                int notes = rs.getInt("Notes");
                int attendance = rs.getInt("Attendance");
                
                //creating a diary object 
                nonbed booking = new nonbed(localDate, localTime, name, age, hospitalNumber, procedure, reason, notes, attendance);
                allBookings.add(booking);             
            }
            System.out.println(">>>>>> " + allBookings.size());
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
    
    public void showResults(ArrayList<nonbed> allBookings)
    {
        for(int i=0; i<allBookings.size(); i++)
        {
            nonbed singleBooking = allBookings.get(i);
               
            attendanceArray[i] = singleBooking.getAttendance();
            timeList.get(i).setText(singleBooking.getTime().toString());
            nameList.get(i).setText(singleBooking.getName());
            ageList.get(i).setText(""+singleBooking.getAge());
            hospitalList.get(i).setText(singleBooking.getHospital());
            procedureList.get(i).setValue(singleBooking.getProcudure());
            reasonList.get(i).setText(singleBooking.getReason());
            notesArray[i] = singleBooking.getNotes();
            
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
            rs = stmt.executeQuery("SELECT Name FROM procedures"); 
            
            for(int i=0; i< procedureList.size(); i++)
            {
                procedureList.get(i).getItems().add("");
            }
           
            while(rs.next())
            { 
                String name = rs.getString("Name");
               
                for(int i=0; i< procedureList.size(); i++)
                {
                    procedureList.get(i).getItems().add(name);
                }
                  
            }
            c.close();
            
        }
        catch (SQLException e)
        {
            
        } 
    }
    
    public void clearAll()
    {
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
        timeList.get(i).setText("");
        nameList.get(i).setText("");
        ageList.get(i).setText("");
        txtHospital1.setText("");
        procedureList.get(i).setValue("");
        reasonList.get(i).setText("");
        txtReason1.setText("");
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
        TextField hospital = hospitalList.get(i);
        ChoiceBox procedure = procedureList.get(i);

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

                    String sql = "DELETE FROM nonbed WHERE Date = '" + codeBank.dateToString(codeBank.getCurrentDate()) + "' AND Time = '" + time.getText() + "' AND HospitalNumber = '" + hospital.getText() + "' AND Reason ='" + procedure.getValue() + "'";

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
                String sql = SQLLine(i, stringDate);
                stmt.executeUpdate(sql);
            }
            c.close();
        }
        catch (SQLException e)
        {
            
        } 
    }
    
    public String SQLLine(int i, String date)
    {
        if(!timeList.get(i).getText().equals("") & !nameList.get(i).getText().equals("") & !procedureList.get(i).getValue().equals(""))
        {
            return  ("REPLACE INTO nonbed (Date, Time, Name, Age, HospitalNumber, Procedure, Reason, Notes, Attendance) VALUES('"      
                                                                                        + date + "','"
                                                                                        + timeList.get(i).getText() + "','"
                                                                                        + nameList.get(i).getText() + "','"
                                                                                        + ageList.get(i).getText() + "','"
                                                                                        + hospitalList.get(i).getText() + "','"
                                                                                        + procedureList.get(i).getValue() + "','"
                                                                                        + txtReason1.getText() + "','"
                                                                                        + notesArray[i] + "','"
                                                                                        + attendanceArray[i] + "')"
                    );
        }
        else
        {
            return "";
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
    
    
}//END CLASS
