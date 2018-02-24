/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preop;

import common.DatabaseConnector;
import common.codeBank;
import diary.DialogController;
import diary.DiaryScreenDocumentController;
import diary.diary;
import java.io.IOException;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
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
public class PreopScreenDocumentController implements Initializable 
{

    //The textfileds from the FXML file
    
    @FXML TextField txtAM = new TextField();
    //AM Headings
    @FXML TextField txtTimeAM = new TextField();
    @FXML TextField txtNameAM = new TextField();
    @FXML TextField txtAgeAM = new TextField();
    @FXML TextField txtHospitalAM = new TextField();
    @FXML TextField txtSpecialityAM = new TextField();
    @FXML TextField txtInformationAM = new TextField();
    @FXML TextField txtNotesAM = new TextField();
    
    //AM booking 8:00
    @FXML TextField txtTimeA1 = new TextField();
    @FXML TextField txtNameA1 = new TextField();
    @FXML TextField txtAgeA1 = new TextField();
    @FXML TextField txtHospitalA1 = new TextField();
    @FXML TextField txtSpecialityA1 = new TextField();
    @FXML TextArea txtInformationA1 = new TextArea();
    @FXML TextField txtNotesA1 = new TextField();
    
    //AM booking 8:30
    @FXML TextField txtTimeA2 = new TextField();
    @FXML TextField txtNameA2 = new TextField();
    @FXML TextField txtAgeA2 = new TextField();
    @FXML TextField txtHospitalA2 = new TextField();
    @FXML TextField txtSpecialityA2 = new TextField();
    @FXML TextArea txtInformationA2 = new TextArea();
    @FXML TextField txtNotesA2 = new TextField();
 
        //AM booking 9:00
    @FXML TextField txtTimeA3 = new TextField();
    @FXML TextField txtNameA3 = new TextField();
    @FXML TextField txtAgeA3 = new TextField();
    @FXML TextField txtHospitalA3 = new TextField();
    @FXML TextField txtSpecialityA3 = new TextField();
    @FXML TextArea txtInformationA3 = new TextArea();
    @FXML TextField txtNotesA3 = new TextField();
    
    //AM booking 9:30
    @FXML TextField txtTimeA4 = new TextField();
    @FXML TextField txtNameA4 = new TextField();
    @FXML TextField txtAgeA4 = new TextField();
    @FXML TextField txtHospitalA4 = new TextField();
    @FXML TextField txtSpecialityA4 = new TextField();
    @FXML TextArea txtInformationA4 = new TextArea();
    @FXML TextField txtNotesA4 = new TextField();
    
    //----------
    
    @FXML TextField txtPM = new TextField();
    //PM Headings
    @FXML TextField txtTimePM = new TextField();
    @FXML TextField txtNamePM = new TextField();
    @FXML TextField txtAgePM = new TextField();
    @FXML TextField txtHospitalPM = new TextField();
    @FXML TextField txtSpecialityPM = new TextField();
    @FXML TextField txtInformationPM = new TextField();
    @FXML TextField txtNotesPM = new TextField();
    
    //PM booking 14:00
    @FXML TextField txtTimeP1 = new TextField();
    @FXML TextField txtNameP1 = new TextField();
    @FXML TextField txtAgeP1 = new TextField();
    @FXML TextField txtHospitalP1 = new TextField();
    @FXML TextField txtSpecialityP1 = new TextField();
    @FXML TextArea txtInformationP1 = new TextArea();
    @FXML TextField txtNotesP1 = new TextField();
    
    //PM booking 14:30
    @FXML TextField txtTimeP2 = new TextField();
    @FXML TextField txtNameP2 = new TextField();
    @FXML TextField txtAgeP2 = new TextField();
    @FXML TextField txtHospitalP2 = new TextField();
    @FXML TextField txtSpecialityP2 = new TextField();
    @FXML TextArea txtInformationP2 = new TextArea();
    @FXML TextField txtNotesP2 = new TextField();
 
    //PM booking 15:00
    @FXML TextField txtTimeP3 = new TextField();
    @FXML TextField txtNameP3 = new TextField();
    @FXML TextField txtAgeP3 = new TextField();
    @FXML TextField txtHospitalP3 = new TextField();
    @FXML TextField txtSpecialityP3 = new TextField();
    @FXML TextArea txtInformationP3 = new TextArea();
    @FXML TextField txtNotesP3 = new TextField();
    
    //PM booking 15:30
    @FXML TextField txtTimeP4 = new TextField();
    @FXML TextField txtNameP4 = new TextField();
    @FXML TextField txtAgeP4 = new TextField();
    @FXML TextField txtHospitalP4 = new TextField();
    @FXML TextField txtSpecialityP4 = new TextField();
    @FXML TextArea txtInformationP4 = new TextArea();
    @FXML TextField txtNotesP4 = new TextField();
    
    //----------
    
    @FXML TextField txtUnscheduled = new TextField();
    //Unsheduled  Headings
    @FXML TextField txtTimeU = new TextField();
    @FXML TextField txtNameU = new TextField();
    @FXML TextField txtAgeU = new TextField();
    @FXML TextField txtHospitalU = new TextField();
    @FXML TextField txtSpecialityU = new TextField();
    @FXML TextField txtInformationU = new TextField();
    @FXML TextField txtNotesU = new TextField();
    
    //Uncheduled 1
    @FXML TextField txtTimeU1 = new TextField();
    @FXML TextField txtNameU1 = new TextField();
    @FXML TextField txtAgeU1 = new TextField();
    @FXML TextField txtHospitalU1 = new TextField();
    @FXML TextField txtSpecialityU1 = new TextField();
    @FXML TextArea txtInformationU1 = new TextArea();
    @FXML TextField txtNotesU1 = new TextField();
    
    //Unscheduled 2
    @FXML TextField txtTimeU2 = new TextField();
    @FXML TextField txtNameU2 = new TextField();
    @FXML TextField txtAgeU2 = new TextField();
    @FXML TextField txtHospitalU2 = new TextField();
    @FXML TextField txtSpecialityU2 = new TextField();
    @FXML TextArea txtInformationU2 = new TextArea();
    @FXML TextField txtNotesU2 = new TextField();
 
    //Uscheduled 3
    @FXML TextField txtTimeU3 = new TextField();
    @FXML TextField txtNameU3 = new TextField();
    @FXML TextField txtAgeU3 = new TextField();
    @FXML TextField txtHospitalU3 = new TextField();
    @FXML TextField txtSpecialityU3 = new TextField();
    @FXML TextArea txtInformationU3 = new TextArea();
    @FXML TextField txtNotesU3 = new TextField();
    
    @FXML ChoiceBox cbAMNurse = new ChoiceBox();
    @FXML ChoiceBox cbPMNurse = new ChoiceBox();
    
    private ArrayList<preop> allBookings = new ArrayList<preop>();
    int[] attendanceArray = new int[11];
    int[] notesArray = new int[11];
    ObservableList<String> workingStaff;
    
    @FXML private List<TextField> attendanceList;
    @FXML private List<TextField> timeList;
    @FXML private List<TextField> nameList;
    @FXML private List<TextField> ageList;
    @FXML private List<TextField> hospitalList;
    @FXML private List<TextField> specialityList;
    @FXML private List<TextField> notesList;
    @FXML private List<TextArea> informationList;
    
    boolean OneUnscheduled = false;
    boolean TwoUnscheduled = false;
    boolean ThreeUnscheduled = false;
    
    
    

//-------------------------------------------------------------------------------------------------------------------------    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       clearInformation();
       loadInformation();
       delete();
      
    }
    
        
    //Load the information for that day
    public void loadInformation()
    {
        clearInformation();
        workingStaff = codeBank.fillStaffDropDowns();       
        cbAMNurse.getItems().addAll(workingStaff);
        cbPMNurse.getItems().addAll(workingStaff);
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
            String stringDate = codeBank.dateToString(codeBank.getCurrentDate());
            
            //implement query
            rs = stmt.executeQuery("SELECT * FROM preop WHERE Date = '" + stringDate + "'" );
            
            while(rs.next())
            { 
                //get String into LocalDate
                String date = rs.getString("Date"); //LocalDate
                LocalDate localDate = codeBank.stringToDate(date);
                
                //get String into LocalTime
                String time = rs.getString("Time"); //Time
                LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
              
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                String hospitalNumber = rs.getString("HospitalNumber");
                String speciality = rs.getString("Speciality");
                String extraInfo = rs.getString("ExtraInfo");
                int notes = rs.getInt("Notes");
                int attendance = rs.getInt("Attendance");
                
                //creating a diary object 
                preop instanceOfPreop = new preop(localDate, localTime, name, age, hospitalNumber, speciality, extraInfo, notes, attendance);
                allBookings.add(instanceOfPreop);             
            }
           showResults(allBookings);
           c.close();
        }
        catch (SQLException e)
        {
            
        }  
    }
    
    public void showSingle(preop singleBooking, int i)
    {
        timeList.get(i).setText((singleBooking.getTime()).toString());
        nameList.get(i).setText(singleBooking.getName());
        ageList.get(i).setText(String.valueOf(singleBooking.getAge()));
        hospitalList.get(i).setText(singleBooking.getHospitalNumber());
        specialityList.get(i).setText(singleBooking.getSpeciality());
        informationList.get(i).setText(singleBooking.getExtraInfo());
        notesArray[i] = singleBooking.getNotes();     
        attendanceArray[i] = singleBooking.getAttendance();
    }
    
    
    public void showResults(ArrayList<preop> allBookings)
    {
        int count = 0;
        
        for(int i=0; i<allBookings.size(); i++)
        {
            preop singleBooking = allBookings.get(i);
                                
            switch(singleBooking.getTime().toString())
            {
                case "08:00":
                    showSingle(singleBooking, 0);
                    break;
                case "08:30":
                    showSingle(singleBooking, 1);
                    break;
                case "09:00":
                    showSingle(singleBooking, 2);
                    break;       
                case "09:30":
                    showSingle(singleBooking, 3);
                    break; 
                case "14:00":
                    showSingle(singleBooking, 4);
                    break;
                case "14:30":
                    showSingle(singleBooking, 5);
                    break;
                case "15:00":
                    showSingle(singleBooking, 6);
                    break;       
                case "15:30":
                    showSingle(singleBooking, 7);
                    break;
                default:
                    if(!OneUnscheduled)
                    {
                        OneUnscheduled = true;
                        showSingle(singleBooking, 8);
                    }
                    if(!TwoUnscheduled)
                    {
                        TwoUnscheduled = true;
                        showSingle(singleBooking, 9);
                    }
                    if(!ThreeUnscheduled)
                    {
                        ThreeUnscheduled = true;
                        showSingle(singleBooking, 10);
                    }
                    
            }
        }//end of for
        
        
        int count1 = 0;
        for(TextField text : attendanceList)
        {
            codeBank.attendanceColour(text, attendanceArray[count1]);
            count1++;
        }
        
        int count2 = 0;
        for(TextField text2 : notesList)
        {
            codeBank.showNotes(text2, notesArray[count2]);
            count2++;
        }
        
        

    }
    
    public void clearSingle(int i)
    {
        nameList.get(i).setText("");
        ageList.get(i).setText("");
        hospitalList.get(i).setText("");
        specialityList.get(i).setText("");
        informationList.get(i).setText("");
        attendanceArray[i] = 0;
        notesArray[i] = 0;
        codeBank.attendanceColour(attendanceList.get(i), 0);
        codeBank.showNotes(notesList.get(i), 0);
    }
    
    
    
    //Clear all of the textboxes
    public void clearInformation()
    {       
        cbAMNurse.getItems().clear();
        cbPMNurse.getItems().clear();
        allBookings.clear();
        attendanceArray = new int[11];
        notesArray = new int[11];
        OneUnscheduled = false;
        TwoUnscheduled = false;
        ThreeUnscheduled = false;
       
        for(int i=0; i<11; i++)
        {
            clearSingle(i);
        }
        
        timeList.get(8).setText("");
        timeList.get(9).setText("");
        timeList.get(10).setText("");
        
    }
    
    
    
    
    
    //Each method associated with the textfield for the bed numbers 
    @FXML public void A1Attendance(){change(0);}
    @FXML public void A2Attendance(){change(1);}
    @FXML public void A3Attendance(){change(2);}
    @FXML public void A4Attendance(){change(3);}
    @FXML public void P1Attendance(){change(4);}
    @FXML public void P2Attendance(){change(5);}
    @FXML public void P3Attendance(){change(6);}
    @FXML public void P4Attendance(){change(7);}
    @FXML public void U1Attendance(){change(8);}
    @FXML public void U2Attendance(){change(9);}
    @FXML public void U3Attendance(){change(10);}

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
    @FXML public void A1Notes(){changeNotes(0);}
    @FXML public void A2Notes(){changeNotes(1);}
    @FXML public void A3Notes(){changeNotes(2);}
    @FXML public void A4Notes(){changeNotes(3);}
    @FXML public void P1Notes(){changeNotes(4);}
    @FXML public void P2Notes(){changeNotes(5);}
    @FXML public void P3Notes(){changeNotes(6);}
    @FXML public void P4Notes(){changeNotes(7);}
    @FXML public void U1Notes(){changeNotes(8);}
    @FXML public void U2Notes(){changeNotes(9);}
    @FXML public void U3Notes(){changeNotes(10);}

    
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
    
    
   
    //Saving the lines and the staff 
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
            
            for(int i=0; i<11; i++)
            {
                String sql = SQLLine(i, stringDate);
                stmt.executeUpdate(sql);
            }
            c.close();
            saveStaff(today);
        }
        catch (SQLException e)
        {
            
        } 
    }
    
    //Saving each preop line
    public String SQLLine(int i, String date)
    {
        if(!timeList.get(i).getText().equals("") & !nameList.get(i).getText().equals("") & !ageList.get(i).getText().equals("") & !hospitalList.get(i).getText().equals("") & !specialityList.get(i).getText().equals(""))
        {
            return ("REPLACE INTO preop (Date, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + timeList.get(i).getText() + "','"
                                                                                + nameList.get(i).getText() + "','"
                                                                                + ageList.get(i).getText() + "','"
                                                                                + hospitalList.get(i).getText() + "','"
                                                                                + specialityList.get(i).getText() + "','"
                                                                                + informationList.get(i).getText() + "','"
                                                                                + notesArray[i] + "','"
                                                                                + attendanceArray[i] + "')"
                    );
        }
        else
        {
            return "";
        }
    
    }
    
    //Saving the selected staff
    public void saveStaff(LocalDate today)
    {
        ArrayList<String> queries = new ArrayList<String>();
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();
                        
            String stringDate = codeBank.dateToString(today);          
            
            if(cbAMNurse.getValue() != null)
            {
                if(cbAMNurse.getValue().equals(""))
                {
                    queries.add("DELETE FROM specificworking WHERE Date = '" + stringDate + "', Place = 'PreopAM'");
                }

                if(!cbAMNurse.getValue().equals(""))
                {
                    String staff = cbAMNurse.getValue().toString();
                    staff = staff.substring(staff.indexOf("(") + 1);
                    staff = staff.substring(0, staff.indexOf(")"));
                    queries.add("REPLACE INTO specificworking (Date, Place, ID) VALUES ('" + stringDate + "', 'PreopAM', '" + staff + "')");
                }
            }
            
            if(cbPMNurse.getValue() != null)
            {
                if(cbPMNurse.getValue().equals(""))
                {
                    queries.add("DELETE FROM specificworking WHERE Date = '" + stringDate + "', Place = 'PreopPM'");
                }

                if(!cbPMNurse.getValue().equals(""))
                {
                    String staff = cbPMNurse.getValue().toString();
                    staff = staff.substring(staff.indexOf("(") + 1);
                    staff = staff.substring(0, staff.indexOf(")"));
                    queries.add("REPLACE INTO specificworking (Date, Place, ID) VALUES ('" + stringDate + "', 'PreopPM', '" + staff + "')");
                }
            }
            
            
            for(int i=0; i<queries.size(); i++)
            {
                stmt.executeUpdate(queries.get(i));
            }
            
            c.close();
        }
        catch (SQLException e)
        {
            Logger.getLogger(PreopScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        } 
    }
        
        
    
    
    //Show the specific staff that are working - selecting them from the list 
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
            rs = stmt.executeQuery("SELECT * FROM staff, specificworking WHERE specificworking.Date = '" + stringDate + "' AND staff.ID = specificworking.ID AND specificworking.Place = 'PreopAM'"); 
                        
            while(rs.next())
            { 
                String firstname = rs.getString("FirstName");
                int ID = rs.getInt("ID");
                
                String text = "(" +ID + ") " + firstname;
                
                cbAMNurse.setValue(text);
                
            }
            
            rs = stmt.executeQuery("SELECT * FROM staff, specificworking WHERE specificworking.Date = '" + stringDate + "' AND staff.ID = specificworking.ID AND specificworking.Place = 'PreopPM'"); 
                        
            while(rs.next())
            { 
                String firstname = rs.getString("FirstName");
                int ID = rs.getInt("ID");
                
                String text = "(" +ID + ") " + firstname;
                
                cbPMNurse.setValue(text);
                
            }
            c.close();
        }
        catch (SQLException e)
        {
            
        } 
    }
    
    
    //Setting up the delete function
    public void delete()
    {
        for(int i=0; i<11; i++)
        {
            deleteOption(i);
        }
    }
    
    //Deleting the right clicked on row 
    public void deleteOption(int i)
    {
        TextField time = timeList.get(i);
       
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

                    String sql = "DELETE FROM preop WHERE Date = '" + codeBank.dateToString(codeBank.getCurrentDate()) + "' AND Time = '" + time.getText() + "'";

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
    
    
    
    
} //END OF CLASS
