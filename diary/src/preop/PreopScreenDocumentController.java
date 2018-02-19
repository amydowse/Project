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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
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
    @FXML TextField txtInformationA1 = new TextField();
    @FXML TextField txtNotesA1 = new TextField();
    
    //AM booking 8:30
    @FXML TextField txtTimeA2 = new TextField();
    @FXML TextField txtNameA2 = new TextField();
    @FXML TextField txtAgeA2 = new TextField();
    @FXML TextField txtHospitalA2 = new TextField();
    @FXML TextField txtSpecialityA2 = new TextField();
    @FXML TextField txtInformationA2 = new TextField();
    @FXML TextField txtNotesA2 = new TextField();
 
        //AM booking 9:00
    @FXML TextField txtTimeA3 = new TextField();
    @FXML TextField txtNameA3 = new TextField();
    @FXML TextField txtAgeA3 = new TextField();
    @FXML TextField txtHospitalA3 = new TextField();
    @FXML TextField txtSpecialityA3 = new TextField();
    @FXML TextField txtInformationA3 = new TextField();
    @FXML TextField txtNotesA3 = new TextField();
    
    //AM booking 9:30
    @FXML TextField txtTimeA4 = new TextField();
    @FXML TextField txtNameA4 = new TextField();
    @FXML TextField txtAgeA4 = new TextField();
    @FXML TextField txtHospitalA4 = new TextField();
    @FXML TextField txtSpecialityA4 = new TextField();
    @FXML TextField txtInformationA4 = new TextField();
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
    @FXML TextField txtInformationP1 = new TextField();
    @FXML TextField txtNotesP1 = new TextField();
    
    //PM booking 14:30
    @FXML TextField txtTimeP2 = new TextField();
    @FXML TextField txtNameP2 = new TextField();
    @FXML TextField txtAgeP2 = new TextField();
    @FXML TextField txtHospitalP2 = new TextField();
    @FXML TextField txtSpecialityP2 = new TextField();
    @FXML TextField txtInformationP2 = new TextField();
    @FXML TextField txtNotesP2 = new TextField();
 
    //PM booking 15:00
    @FXML TextField txtTimeP3 = new TextField();
    @FXML TextField txtNameP3 = new TextField();
    @FXML TextField txtAgeP3 = new TextField();
    @FXML TextField txtHospitalP3 = new TextField();
    @FXML TextField txtSpecialityP3 = new TextField();
    @FXML TextField txtInformationP3 = new TextField();
    @FXML TextField txtNotesP3 = new TextField();
    
    //PM booking 15:30
    @FXML TextField txtTimeP4 = new TextField();
    @FXML TextField txtNameP4 = new TextField();
    @FXML TextField txtAgeP4 = new TextField();
    @FXML TextField txtHospitalP4 = new TextField();
    @FXML TextField txtSpecialityP4 = new TextField();
    @FXML TextField txtInformationP4 = new TextField();
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
    @FXML TextField txtInformationU1 = new TextField();
    @FXML TextField txtNotesU1 = new TextField();
    
    //Unscheduled 2
    @FXML TextField txtTimeU2 = new TextField();
    @FXML TextField txtNameU2 = new TextField();
    @FXML TextField txtAgeU2 = new TextField();
    @FXML TextField txtHospitalU2 = new TextField();
    @FXML TextField txtSpecialityU2 = new TextField();
    @FXML TextField txtInformationU2 = new TextField();
    @FXML TextField txtNotesU2 = new TextField();
 
    //Uscheduled 3
    @FXML TextField txtTimeU3 = new TextField();
    @FXML TextField txtNameU3 = new TextField();
    @FXML TextField txtAgeU3 = new TextField();
    @FXML TextField txtHospitalU3 = new TextField();
    @FXML TextField txtSpecialityU3 = new TextField();
    @FXML TextField txtInformationU3 = new TextField();
    @FXML TextField txtNotesU3 = new TextField();
    
    @FXML ChoiceBox cbStaff = new ChoiceBox();
    
    private ArrayList<preop> allBookings = new ArrayList<preop>();
    int[] attendanceArray = new int[11];
    int[] notesArray = new int[11];
    String[] extraArray = codeBank.newStringArray(11);
    
    @FXML private List<TextField> attendanceList;
    @FXML private List<TextField> notesList;
    @FXML private List<TextField> extraList;
    
    
    

//-------------------------------------------------------------------------------------------------------------------------    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       clearInformation();
       loadInformation();
       showStaff(codeBank.getCurrentDate());
    }
    
        
    //Load the information for that day
    public void loadInformation()
    {
        clearInformation();
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
    
    
    public void showResults(ArrayList<preop> allBookings)
    {
        int count = 0;
        
        for(int i=0; i<allBookings.size(); i++)
        {
            preop singleBooking = allBookings.get(i);
                                
            switch(singleBooking.getTime().toString())
            {
                case "08:00":
                    txtNameA1.setText(singleBooking.getName());
                    txtAgeA1.setText(String.valueOf(singleBooking.getAge()));
                    txtHospitalA1.setText(singleBooking.getHospitalNumber());
                    txtSpecialityA1.setText(singleBooking.getSpeciality());
                    notesArray[0]=singleBooking.getNotes();
                    extraArray[0] = singleBooking.getExtraInfo();
                    attendanceArray[0] = singleBooking.getAttendance();
                    break;
                case "08:30":
                    txtNameA2.setText(singleBooking.getName());
                    txtAgeA2.setText(String.valueOf(singleBooking.getAge()));
                    txtHospitalA2.setText(singleBooking.getHospitalNumber());
                    txtSpecialityA2.setText(singleBooking.getSpeciality());
                    notesArray[1]=singleBooking.getNotes();
                    extraArray[1] = singleBooking.getExtraInfo();
                    attendanceArray[1] = singleBooking.getAttendance();
                    break;
                case "09:00":
                    txtNameA3.setText(singleBooking.getName());
                    txtAgeA3.setText(String.valueOf(singleBooking.getAge()));
                    txtHospitalA3.setText(singleBooking.getHospitalNumber());
                    txtSpecialityA3.setText(singleBooking.getSpeciality());
                    notesArray[2]=singleBooking.getNotes();
                    extraArray[2] = singleBooking.getExtraInfo();
                    attendanceArray[2] = singleBooking.getAttendance();
                    break;       
                case "09:30":
                    txtNameA4.setText(singleBooking.getName());
                    txtAgeA4.setText(String.valueOf(singleBooking.getAge()));
                    txtHospitalA4.setText(singleBooking.getHospitalNumber());
                    txtSpecialityA4.setText(singleBooking.getSpeciality());
                    notesArray[3]=singleBooking.getNotes();
                    extraArray[3] = singleBooking.getExtraInfo();
                    attendanceArray[3] = singleBooking.getAttendance();
                    break; 
                case "14:00":
                    txtNameP1.setText(singleBooking.getName());
                    txtAgeP1.setText(String.valueOf(singleBooking.getAge()));
                    txtHospitalP1.setText(singleBooking.getHospitalNumber());
                    txtSpecialityP1.setText(singleBooking.getSpeciality());
                    notesArray[4]=singleBooking.getNotes();
                    extraArray[4] = singleBooking.getExtraInfo();
                    attendanceArray[4] = singleBooking.getAttendance();
                    break;
                case "14:30":
                    txtNameP2.setText(singleBooking.getName());
                    txtAgeP2.setText(String.valueOf(singleBooking.getAge()));
                    txtHospitalP2.setText(singleBooking.getHospitalNumber());
                    txtSpecialityP2.setText(singleBooking.getSpeciality());
                    notesArray[5]=singleBooking.getNotes();
                    extraArray[5] = singleBooking.getExtraInfo();
                    attendanceArray[5] = singleBooking.getAttendance();
                    break;
                case "15:00":
                    txtNameP3.setText(singleBooking.getName());
                    txtAgeP3.setText(String.valueOf(singleBooking.getAge()));
                    txtHospitalP3.setText(singleBooking.getHospitalNumber());
                    txtSpecialityP3.setText(singleBooking.getSpeciality());
                    notesArray[6]=singleBooking.getNotes();
                    extraArray[6] = singleBooking.getExtraInfo();
                    attendanceArray[6] = singleBooking.getAttendance();
                    break;       
                case "15:30":
                    txtNameP4.setText(singleBooking.getName());
                    txtAgeP4.setText(String.valueOf(singleBooking.getAge()));
                    txtHospitalP4.setText(singleBooking.getHospitalNumber());
                    txtSpecialityP4.setText(singleBooking.getSpeciality());
                    notesArray[7]=singleBooking.getNotes();
                    extraArray[7] = singleBooking.getExtraInfo();
                    attendanceArray[7] = singleBooking.getAttendance();
                    break;
                    
                default:
                    switch(count)
                    {
                        case 0: 
                            txtTimeU1.setText(singleBooking.getTime().toString());
                            txtNameU1.setText(singleBooking.getName());
                            txtAgeU1.setText(String.valueOf(singleBooking.getAge()));
                            txtHospitalU1.setText(singleBooking.getHospitalNumber());
                            txtSpecialityU1.setText(singleBooking.getSpeciality());
                            notesArray[8]=singleBooking.getNotes();
                            extraArray[8] = singleBooking.getExtraInfo();
                            attendanceArray[8] = singleBooking.getAttendance();
                            count++;
                            break;
                        case 1: 
                            txtTimeU2.setText(singleBooking.getTime().toString());
                            txtNameU2.setText(singleBooking.getName());
                            txtAgeU2.setText(String.valueOf(singleBooking.getAge()));
                            txtHospitalU2.setText(singleBooking.getHospitalNumber());
                            txtSpecialityU2.setText(singleBooking.getSpeciality());
                            notesArray[9]=singleBooking.getNotes();
                            extraArray[9] = singleBooking.getExtraInfo();
                            attendanceArray[9] = singleBooking.getAttendance();                            
                            count++;
                            break;
                        case 2: 
                            txtTimeU3.setText(singleBooking.getTime().toString());
                            txtNameU3.setText(singleBooking.getName());
                            txtAgeU3.setText(String.valueOf(singleBooking.getAge()));
                            txtHospitalU3.setText(singleBooking.getHospitalNumber());
                            txtSpecialityU3.setText(singleBooking.getSpeciality());
                            notesArray[10]=singleBooking.getNotes();
                            extraArray[10] = singleBooking.getExtraInfo();
                            attendanceArray[10] = singleBooking.getAttendance();                            
                            break;
                        default:
                            break;
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
        
        int count3 = 0;
        for(TextField text3 : extraList)
        {
            codeBank.extraInfo(text3, extraArray[count3]);
            count3++;
        }

    }
    
    //Clear all of the textboxes
    public void clearInformation()
    {
        allBookings.clear();
        attendanceArray = new int[11];
        notesArray = new int[11];
        extraArray = codeBank.newStringArray(11);
       
        txtNameA1.setText("");
        txtAgeA1.setText("");
        txtHospitalA1.setText("");
        txtSpecialityA1.setText("");
        txtInformationA1.setText("");
        txtNotesA1.setText("");
        
        txtNameA2.setText("");
        txtAgeA2.setText("");
        txtHospitalA2.setText("");
        txtSpecialityA2.setText("");
        txtInformationA2.setText("");
        txtNotesA2.setText("");
        
        txtNameA3.setText("");
        txtAgeA3.setText("");
        txtHospitalA3.setText("");
        txtSpecialityA3.setText("");
        txtInformationA3.setText("");
        txtNotesA3.setText("");
        
        txtNameA4.setText("");
        txtAgeA4.setText("");
        txtHospitalA4.setText("");
        txtSpecialityA4.setText("");
        txtInformationA4.setText("");
        txtNotesA4.setText("");
        
        txtNameP1.setText("");
        txtAgeP1.setText("");
        txtHospitalP1.setText("");
        txtSpecialityP1.setText("");
        txtInformationP1.setText("");
        txtNotesP1.setText("");
        
        txtNameP2.setText("");
        txtAgeP2.setText("");
        txtHospitalP2.setText("");
        txtSpecialityP2.setText("");
        txtInformationP2.setText("");
        txtNotesP2.setText("");
        
        txtNameP3.setText("");
        txtAgeP3.setText("");
        txtHospitalP3.setText("");
        txtSpecialityP3.setText("");
        txtInformationP3.setText("");
        txtNotesP3.setText("");
        
        txtNameP4.setText("");
        txtAgeP4.setText("");
        txtHospitalP4.setText("");
        txtSpecialityP4.setText("");
        txtInformationP4.setText("");
        txtNotesP4.setText("");
        
        txtNameU1.setText("");
        txtAgeU1.setText("");
        txtHospitalU1.setText("");
        txtSpecialityU1.setText("");
        txtInformationU1.setText("");
        txtNotesU1.setText("");
        
        txtNameU2.setText("");
        txtAgeU2.setText("");
        txtHospitalU2.setText("");
        txtSpecialityU2.setText("");
        txtInformationU2.setText("");
        txtNotesU2.setText("");
        
        txtNameU3.setText("");
        txtAgeU3.setText("");
        txtHospitalU3.setText("");
        txtSpecialityU3.setText("");
        txtInformationU3.setText("");
        txtNotesU3.setText("");
        
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
    
    
    
    
    //Each method associated with the pop-up for extra information 
    @FXML public void A1Extra(){showExtraInfo(0, txtNameA1.getText(), txtTimeA1.getText());}   
    @FXML public void A2Extra(){showExtraInfo(1, txtNameA2.getText(), txtTimeA2.getText());}     
    @FXML public void A3Extra(){showExtraInfo(2, txtNameA3.getText(), txtTimeA3.getText());}    
    @FXML public void A4Extra(){showExtraInfo(3, txtNameA4.getText(), txtTimeA4.getText());}  
    
    @FXML public void P1Extra(){showExtraInfo(4, txtNameP1.getText(), txtTimeP1.getText());}
    @FXML public void P2Extra(){showExtraInfo(5, txtNameP2.getText(), txtTimeP2.getText());}  
    @FXML public void P3Extra(){showExtraInfo(6, txtNameP3.getText(), txtTimeP3.getText());} 
    @FXML public void P4Extra(){showExtraInfo(7, txtNameP4.getText(), txtTimeP4.getText());}
    
    @FXML public void U1Extra(){showExtraInfo(8, txtNameU1.getText(), txtTimeU1.getText());} 
    @FXML public void U2Extra(){showExtraInfo(9, txtNameU2.getText(), txtTimeU2.getText());} 
    @FXML public void U3Extra(){showExtraInfo(10, txtNameU3.getText(), txtTimeU3.getText());} 
   
    private PODialogController PDC;
    private Pane x;
    
    public void showExtraInfo(int arrayValue, String name, String time)
    {      
              
        try 
        {    
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Extra Information");
            
            FXMLLoader DL = new FXMLLoader(getClass().getResource("/preop/PODialog.fxml"));           
            x = DL.load();
            PDC = DL.getController();
            
            final Scene scene = new Scene(x, 400, 200);
            stage.setScene(scene);
            stage.setOnHidden(e -> PDC.shutdown());
            stage.show();
                       
            PDC.showInformation(arrayValue, extraArray[arrayValue], name, time);
            
        } 
        catch (IOException ex) 
        {
           
        }
    }
    
    public void updateArray(int arrayValue, String text)
    {
        extraArray[arrayValue] = text;
    }
    
    
    
    
    
    public void save(LocalDate today)
    {
        System.out.println("IN SAVE FOR PREOP");
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();
            
            String stringDate = codeBank.dateToString(today);          
            
            
            //implement query - SAVING EACH LINE                       
            String[] queries = new String[11];
            queries[0] = saveA1(stringDate);
            queries[1] = saveA2(stringDate);
            queries[2] = saveA3(stringDate);
            queries[3] = saveA4(stringDate);
            queries[4] = saveP1(stringDate);
            queries[5] = saveP2(stringDate);
            queries[6] = saveP3(stringDate);
            queries[7] = saveP4(stringDate);
            queries[8] = saveU1(stringDate);
            queries[9] = saveU2(stringDate);
            queries[10] = saveU3(stringDate);
            
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
    
    
    
    //THE SAVE METHODS FOR EACH ROW 
    
    public String saveA1(String date)
    {
        if(!txtNameA1.getText().equals("") & !txtAgeA1.getText().equals("") & !txtHospitalA1.getText().equals("") & !txtSpecialityA1.getText().equals(""))
        {
            return ("REPLACE INTO preop (Date, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "08:00" + "','"
                                                                                + txtNameA1.getText() + "','"
                                                                                + txtAgeA1.getText() + "','"
                                                                                + txtHospitalA1.getText() + "','"
                                                                                + txtSpecialityA1.getText() + "','"
                                                                                + extraArray[0] + "','"
                                                                                + notesArray[0] + "','"
                                                                                + attendanceArray[0] + "')"
                    );
        }
        else
        {
            return "";
        }
    }
    
    public String saveA2(String date)
    {
        if(!txtNameA2.getText().equals("") & !txtAgeA2.getText().equals("") & !txtHospitalA2.getText().equals("") & !txtSpecialityA2.getText().equals(""))
        {
            return ("REPLACE INTO preop (Date, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "08:30" + "','"
                                                                                + txtNameA2.getText() + "','"
                                                                                + txtAgeA2.getText() + "','"
                                                                                + txtHospitalA2.getText() + "','"
                                                                                + txtSpecialityA2.getText() + "','"
                                                                                + extraArray[1] + "','"
                                                                                + notesArray[1] + "','"
                                                                                + attendanceArray[1] + "')"
                    );
        }
        else
        {
            return "";
        }
    }    

    public String saveA3(String date)
    {
        if(!txtNameA3.getText().equals("") & !txtAgeA3.getText().equals("") & !txtHospitalA3.getText().equals("") & !txtSpecialityA3.getText().equals(""))
        {
            return ("REPLACE INTO preop (Date, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "09:00" + "','"
                                                                                + txtNameA3.getText() + "','"
                                                                                + txtAgeA3.getText() + "','"
                                                                                + txtHospitalA3.getText() + "','"
                                                                                + txtSpecialityA3.getText() + "','"
                                                                                + extraArray[2] + "','"
                                                                                + notesArray[2] + "','"
                                                                                + attendanceArray[2] + "')"
                    );
        }
        else
        {
            return "";
        }
    }        
    
    public String saveA4(String date)
    {
        if(!txtNameA4.getText().equals("") & !txtAgeA4.getText().equals("") & !txtHospitalA4.getText().equals("") & !txtSpecialityA4.getText().equals(""))
        {
            return ("REPLACE INTO preop (Date, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "09:30" + "','"
                                                                                + txtNameA4.getText() + "','"
                                                                                + txtAgeA4.getText() + "','"
                                                                                + txtHospitalA4.getText() + "','"
                                                                                + txtSpecialityA4.getText() + "','"
                                                                                + extraArray[3] + "','"
                                                                                + notesArray[3] + "','"
                                                                                + attendanceArray[3] + "')"
                    );
        }
        else
        {
            return "";
        }
    }       
    
    
    
    public String saveP1(String date)
    {
        if(!txtNameP1.getText().equals("") & !txtAgeP1.getText().equals("") & !txtHospitalP1.getText().equals("") & !txtSpecialityP1.getText().equals(""))
        {
            return ("REPLACE INTO preop (Date, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "14:00" + "','"
                                                                                + txtNameP1.getText() + "','"
                                                                                + txtAgeP1.getText() + "','"
                                                                                + txtHospitalP1.getText() + "','"
                                                                                + txtSpecialityP1.getText() + "','"
                                                                                + extraArray[4] + "','"
                                                                                + notesArray[4] + "','"
                                                                                + attendanceArray[4] + "')"
                    );
        }
        else
        {
            return "";
        }
    }
    
    public String saveP2(String date)
    {
        if(!txtNameP2.getText().equals("") & !txtAgeP2.getText().equals("") & !txtHospitalP2.getText().equals("") & !txtSpecialityP2.getText().equals(""))
        {
            return ("REPLACE INTO preop (Date, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "14:30" + "','"
                                                                                + txtNameP2.getText() + "','"
                                                                                + txtAgeP2.getText() + "','"
                                                                                + txtHospitalP2.getText() + "','"
                                                                                + txtSpecialityP2.getText() + "','"
                                                                                + extraArray[5] + "','"
                                                                                + notesArray[5] + "','"
                                                                                + attendanceArray[5] + "')"
                    );
        }
        else
        {
            return "";
        }
    }    

    public String saveP3(String date)
    {
        if(!txtNameP3.getText().equals("") & !txtAgeP3.getText().equals("") & !txtHospitalP3.getText().equals("") & !txtSpecialityP3.getText().equals(""))
        {
            return ("REPLACE INTO preop (Date, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "15:00" + "','"
                                                                                + txtNameP3.getText() + "','"
                                                                                + txtAgeP3.getText() + "','"
                                                                                + txtHospitalP3.getText() + "','"
                                                                                + txtSpecialityP3.getText() + "','"
                                                                                + extraArray[6] + "','"
                                                                                + notesArray[6] + "','"
                                                                                + attendanceArray[6] + "')"
                    );
        }
        else
        {
            return "";
        }
    }        
    
    public String saveP4(String date)
    {
        if(!txtNameP4.getText().equals("") & !txtAgeP4.getText().equals("") & !txtHospitalP4.getText().equals("") & !txtSpecialityP4.getText().equals(""))
        {
            return ("REPLACE INTO preop (Date, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "15:30" + "','"
                                                                                + txtNameP4.getText() + "','"
                                                                                + txtAgeP4.getText() + "','"
                                                                                + txtHospitalP4.getText() + "','"
                                                                                + txtSpecialityP4.getText() + "','"
                                                                                + extraArray[7] + "','"
                                                                                + notesArray[7] + "','"
                                                                                + attendanceArray[7] + "')"
                    );
        }
        else
        {
            return "";
        }
    }  
    
    
    
    
    
    public String saveU1(String date)
    {
        if(!txtTimeU1.getText().equals("") & !txtNameU1.getText().equals("") & !txtAgeU1.getText().equals("") & !txtHospitalU1.getText().equals("") & !txtSpecialityU1.getText().equals(""))
        {
            return ("REPLACE INTO preop (Date, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + txtTimeU1.getText() + "','"
                                                                                + txtNameU1.getText() + "','"
                                                                                + txtAgeU1.getText() + "','"
                                                                                + txtHospitalU1.getText() + "','"
                                                                                + txtSpecialityU1.getText() + "','"
                                                                                + extraArray[8] + "','"
                                                                                + notesArray[8] + "','"
                                                                                + attendanceArray[8] + "')"
                    );
        }
        else
        {
            return "";
        }
    }
    
    
    public String saveU2(String date)
    {
        if(!txtTimeU2.getText().equals("") & !txtNameU2.getText().equals("") & !txtAgeU2.getText().equals("") & !txtHospitalU2.getText().equals("") & !txtSpecialityU2.getText().equals(""))
        {
            return ("REPLACE INTO preop (Date, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + txtTimeU2.getText() + "','"
                                                                                + txtNameU2.getText() + "','"
                                                                                + txtAgeU2.getText() + "','"
                                                                                + txtHospitalU2.getText() + "','"
                                                                                + txtSpecialityU2.getText() + "','"
                                                                                + extraArray[9] + "','"
                                                                                + notesArray[9] + "','"
                                                                                + attendanceArray[9] + "')"
                    );
        }
        else
        {
            return "";
        }
    }
    
    
    
    public String saveU3(String date)
    {
        if(!txtTimeU3.getText().equals("") & !txtNameU3.getText().equals("") & !txtAgeU3.getText().equals("") & !txtHospitalU3.getText().equals("") & !txtSpecialityU3.getText().equals(""))
        {
            return ("REPLACE INTO preop (Date, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + txtTimeU3.getText() + "','"
                                                                                + txtNameU3.getText() + "','"
                                                                                + txtAgeU3.getText() + "','"
                                                                                + txtHospitalU3.getText() + "','"
                                                                                + txtSpecialityU3.getText() + "','"
                                                                                + extraArray[10] + "','"
                                                                                + notesArray[10] + "','"
                                                                                + attendanceArray[10] + "')"
                    );
        }
        else
        {
            return "";
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
            rs = stmt.executeQuery("SELECT * FROM staff, specificworking WHERE specificworking.Date = '" + stringDate + "' AND staff.ID = specificworking.ID AND specificworking.Place = 'Preop'"); 
                        
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
    
    
    
    
    
} //END OF CLASS
