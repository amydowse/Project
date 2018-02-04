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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    @FXML private ChoiceBox cbStaff1 = new ChoiceBox();
    @FXML private ChoiceBox cbShift1 = new ChoiceBox();
    @FXML private ChoiceBox cbStaff2 = new ChoiceBox();
    @FXML private ChoiceBox cbShift2 = new ChoiceBox();
    @FXML private ChoiceBox cbStaff3 = new ChoiceBox();
    @FXML private ChoiceBox cbShift3 = new ChoiceBox();
    @FXML private ChoiceBox cbStaff4 = new ChoiceBox();
    @FXML private ChoiceBox cbShift4 = new ChoiceBox();
    @FXML private ChoiceBox cbStaff5 = new ChoiceBox();
    @FXML private ChoiceBox cbShift5 = new ChoiceBox();
    @FXML private ChoiceBox cbStaff6 = new ChoiceBox();
    @FXML private ChoiceBox cbShift6 = new ChoiceBox();
    
    @FXML private TextArea txtNotes = new TextArea();
    
    @FXML private List<TextField> attendanceList;
    @FXML private List<TextField> notesList;
    @FXML private List<TextField> extraList;
    
    private ArrayList<workingStaff> staff = new ArrayList<workingStaff>();
    
    int[] attendanceArray = new int[24];
    int[] notesArray = new int[24];
    String[] extraArray = codeBank.newStringArray();
   
      
    //METHODS -----------------------------------------------------------------------
     
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        fillStaffDropDowns();
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
    
    
    
    
    //Each method associated with the pop-up for extra information 
    @FXML public void MA1Extra(){showExtraInfo(0, txtName1MA.getText(), txtTime1MA.getText());}   
    @FXML public void MA2Extra(){showExtraInfo(1, txtName2MA.getText(), txtTime2MA.getText());}     
    @FXML public void MA3Extra(){showExtraInfo(2, txtName3MA.getText(), txtTime3MA.getText());}    
    @FXML public void MA4Extra(){showExtraInfo(3, txtName4MA.getText(), txtTime4MA.getText());}    
    @FXML public void MAEExtra(){showExtraInfo(4, txtNameEMA.getText(), txtTimeEMA.getText());}    
    
    @FXML public void LA1Extra(){showExtraInfo(5, txtName1LA.getText(), txtTime1LA.getText());}  
    @FXML public void LA2Extra(){showExtraInfo(6, txtName2LA.getText(), txtTime2LA.getText());} 
    @FXML public void LA3Extra(){showExtraInfo(7, txtName3LA.getText(), txtTime3LA.getText());} 
    @FXML public void LA4Extra(){showExtraInfo(8, txtName4LA.getText(), txtTime4LA.getText());} 
    @FXML public void LA5Extra(){showExtraInfo(9, txtName5LA.getText(), txtTime5LA.getText());} 
    @FXML public void LA6Extra(){showExtraInfo(10, txtName6LA.getText(), txtTime6LA.getText());} 
    @FXML public void LAEExtra(){showExtraInfo(11, txtNameELA.getText(), txtTimeELA.getText());} 
    
    @FXML public void MP1Extra(){showExtraInfo(12, txtName1MP.getText(), txtTime1MP.getText());}   
    @FXML public void MP2Extra(){showExtraInfo(13, txtName2MP.getText(), txtTime2MP.getText());}     
    @FXML public void MP3Extra(){showExtraInfo(14, txtName3MP.getText(), txtTime3MP.getText());}    
    @FXML public void MP4Extra(){showExtraInfo(15, txtName4MP.getText(), txtTime4MP.getText());}    
    @FXML public void MPEExtra(){showExtraInfo(16, txtNameEMP.getText(), txtTimeEMP.getText());}    
    
    @FXML public void LP1Extra(){showExtraInfo(17, txtName1LP.getText(), txtTime1LP.getText());}  
    @FXML public void LP2Extra(){showExtraInfo(18, txtName2LP.getText(), txtTime2LP.getText());} 
    @FXML public void LP3Extra(){showExtraInfo(19, txtName3LP.getText(), txtTime3LP.getText());} 
    @FXML public void LP4Extra(){showExtraInfo(20, txtName4LP.getText(), txtTime4LP.getText());} 
    @FXML public void LP5Extra(){showExtraInfo(21, txtName5LP.getText(), txtTime5LP.getText());} 
    @FXML public void LP6Extra(){showExtraInfo(22, txtName6LP.getText(), txtTime6LP.getText());} 
    @FXML public void LPEExtra(){showExtraInfo(23, txtNameELP.getText(), txtTimeELP.getText());}    
    
    private DialogController DC;
    private Pane x;
    
   
    public void showExtraInfo(int arrayValue, String name, String time)
    {      
              
        try 
        {    
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Extra Information");
            
            FXMLLoader DL = new FXMLLoader(getClass().getResource("/diary/Dialog.fxml"));           
            x = DL.load();
            DC = DL.getController();
            
            final Scene scene = new Scene(x, 400, 200);
            stage.setScene(scene);
            stage.setOnHidden(e -> DC.shutdown());
            stage.show();
                       
            DC.showInformation(arrayValue, extraArray[arrayValue], name, time);
            
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(DiaryScreenDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateArray(int arrayValue, String text)
    {
        extraArray[arrayValue] = text;
    }
    
    
    public void extraInfo(TextField txt, String info)
    {        
        if(info.equals("") || info.equals(null))
        {
            txt.setText("");
            txt.setStyle("-fx-font-weight: regular");
        }
        else
        {
            txt.setText("+");
            txt.setStyle("-fx-font-weight: bold");
        }
    }
    
    
    
    
    
    
    
    
    
    public void fillStaffDropDowns()
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
            rs = stmt.executeQuery("SELECT * FROM staff"); 
                  
            
            cbStaff1.getItems().add("");
            cbStaff2.getItems().add("");
            cbStaff3.getItems().add("");
            cbStaff4.getItems().add("");
            cbStaff5.getItems().add("");
            cbStaff6.getItems().add(""); 
            
            cbShift1.getItems().add("");
            cbShift2.getItems().add("");
            cbShift3.getItems().add("");
            cbShift4.getItems().add("");
            cbShift5.getItems().add("");
            cbShift6.getItems().add(""); 
            
            while(rs.next())
            { 
                String firstname = rs.getString("FirstName");
                int ID = rs.getInt("ID");
                
                String text = "(" + ID + ") " + firstname;
                
                cbStaff1.getItems().add(text);
                cbStaff2.getItems().add(text);
                cbStaff3.getItems().add(text);
                cbStaff4.getItems().add(text);
                cbStaff5.getItems().add(text);
                cbStaff6.getItems().add(text);  
            }
            c.close();
            
            cbShift1.getItems().addAll("E", "L", "LD");
            cbShift2.getItems().addAll("E", "L", "LD");
            cbShift3.getItems().addAll("E", "L", "LD");
            cbShift4.getItems().addAll("E", "L", "LD");
            cbShift5.getItems().addAll("E", "L", "LD");
            cbShift6.getItems().addAll("E", "L", "LD");
        }
        catch (SQLException e)
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
            rs = stmt.executeQuery("SELECT * FROM staff, working WHERE working.Date = '" + stringDate + "' AND staff.ID = working.Staff_ID"); 
                        
            while(rs.next())
            { 
                String firstname = rs.getString("FirstName");
                String lastname = rs.getString("LastName");
                int ID = rs.getInt("ID");
                String shift = rs.getString("Shift");
                                
                workingStaff instanceOfWorkingStaff = new workingStaff(firstname, lastname, ID, shift);
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
        if(staff.size() >= 1)
        {
            String value = "(" + staff.get(0).getID() + ") " + staff.get(0).getFirstName();
            cbStaff1.setValue(value);
            cbShift1.setValue(staff.get(0).getShift());
        }
        if(staff.size() >= 2)
        {
            String value = "(" + staff.get(1).getID() + ") " + staff.get(1).getFirstName();
            cbStaff2.setValue(value);
            cbShift2.setValue(staff.get(1).getShift());
        }
        if(staff.size() >= 3)
        {
            String value = "(" + staff.get(2).getID() + ") " + staff.get(2).getFirstName();
            cbStaff3.setValue(value);
            cbShift3.setValue(staff.get(2).getShift());
        }
        if(staff.size() >= 4)
        {
            String value = "(" + staff.get(3).getID() + ") " + staff.get(3).getFirstName();
            cbStaff4.setValue(value);
            cbShift4.setValue(staff.get(3).getShift());
        }
        if(staff.size() >= 5)
        {
            String value = "(" + staff.get(4).getID() + ") " + staff.get(4).getFirstName();
            cbStaff5.setValue(value);
            cbShift5.setValue(staff.get(4).getShift());
        }
        if(staff.size() == 6)
        {
            String value = "(" + staff.get(5).getID() + ") " + staff.get(5).getFirstName();
            cbStaff6.setValue(value);
            cbShift6.setValue(staff.get(5).getShift());
        }
        
        
    }
    
    
    
    public void clearTopSection()
    {
        staff.clear();
        txtNotes.setText("");
        
        cbStaff1.setValue("");
        cbStaff2.setValue("");
        cbStaff3.setValue("");
        cbStaff4.setValue("");
        cbStaff5.setValue("");
        cbStaff6.setValue("");
        
        cbShift1.setValue("");
        cbShift2.setValue("");
        cbShift3.setValue("");
        cbShift4.setValue("");
        cbShift5.setValue("");
        cbShift6.setValue("");
        
        
        //NEED TO CLEAR THE STAFF BOXES
    }
    
    //Getting the main diary information 
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
    
  
    //Showing the main diary information 
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
                    extraArray[0] = singleBooking.getExtraInfo();
                    attendanceArray[0] = singleBooking.getAttendance();
                    break;
                case "2MA":
                    txtTime2MA.setText((singleBooking.getTime()).toString());
                    txtName2MA.setText(singleBooking.getName());
                    txtAge2MA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital2MA.setText(singleBooking.getHospitalNumber());
                    txtSpeciality2MA.setText(singleBooking.getSpeciality());
                    notesArray[1]=singleBooking.getNotes();
                    extraArray[1] = singleBooking.getExtraInfo();
                    attendanceArray[1] = singleBooking.getAttendance();
                    break;
                case "3MA":
                    txtTime3MA.setText((singleBooking.getTime()).toString());
                    txtName3MA.setText(singleBooking.getName());
                    txtAge3MA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital3MA.setText(singleBooking.getHospitalNumber());
                    txtSpeciality3MA.setText(singleBooking.getSpeciality());
                    notesArray[2]=singleBooking.getNotes();
                    extraArray[2] = singleBooking.getExtraInfo();
                    attendanceArray[2] = singleBooking.getAttendance();
                    break;
                case "4MA":
                    txtTime4MA.setText((singleBooking.getTime()).toString());
                    txtName4MA.setText(singleBooking.getName());
                    txtAge4MA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital4MA.setText(singleBooking.getHospitalNumber());
                    txtSpeciality4MA.setText(singleBooking.getSpeciality());
                    notesArray[3]=singleBooking.getNotes();
                    extraArray[3] = singleBooking.getExtraInfo();
                    attendanceArray[3] = singleBooking.getAttendance();
                    break;
                case "EMA":
                    txtTimeEMA.setText((singleBooking.getTime()).toString());
                    txtNameEMA.setText(singleBooking.getName());
                    txtAgeEMA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospitalEMA.setText(singleBooking.getHospitalNumber());
                    txtSpecialityEMA.setText(singleBooking.getSpeciality());
                    notesArray[4]=singleBooking.getNotes();
                    extraArray[4] = singleBooking.getExtraInfo();
                    attendanceArray[4] = singleBooking.getAttendance();
                    break;
                case "1LA":
                    txtTime1LA.setText((singleBooking.getTime()).toString());
                    txtName1LA.setText(singleBooking.getName());
                    txtAge1LA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital1LA.setText(singleBooking.getHospitalNumber());
                    txtSpeciality1LA.setText(singleBooking.getSpeciality());
                    notesArray[5]=singleBooking.getNotes();
                    extraArray[5] = singleBooking.getExtraInfo();
                    attendanceArray[5] = singleBooking.getAttendance();
                    break;
                case "2LA":
                    txtTime2LA.setText((singleBooking.getTime()).toString());
                    txtName2LA.setText(singleBooking.getName());
                    txtAge2LA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital2LA.setText(singleBooking.getHospitalNumber());
                    txtSpeciality2LA.setText(singleBooking.getSpeciality());
                    notesArray[6]=singleBooking.getNotes();
                    extraArray[6] = singleBooking.getExtraInfo();
                    attendanceArray[6] = singleBooking.getAttendance();
                    break;
                case "3LA":
                    txtTime3LA.setText((singleBooking.getTime()).toString());
                    txtName3LA.setText(singleBooking.getName());
                    txtAge3LA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital3LA.setText(singleBooking.getHospitalNumber());
                    txtSpeciality3LA.setText(singleBooking.getSpeciality());
                    notesArray[7]=singleBooking.getNotes();
                   extraArray[7] = singleBooking.getExtraInfo();
                    attendanceArray[7] = singleBooking.getAttendance();
                    break;
                case "4LA":
                    txtTime4LA.setText((singleBooking.getTime()).toString());
                    txtName4LA.setText(singleBooking.getName());
                    txtAge4LA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital4LA.setText(singleBooking.getHospitalNumber());
                    txtSpeciality4LA.setText(singleBooking.getSpeciality());
                    notesArray[8]=singleBooking.getNotes();
                    extraArray[8] = singleBooking.getExtraInfo();
                    attendanceArray[8] = singleBooking.getAttendance();
                    break;
                case "5LA":
                    txtTime5LA.setText((singleBooking.getTime()).toString());
                    txtName5LA.setText(singleBooking.getName());
                    txtAge5LA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital5LA.setText(singleBooking.getHospitalNumber());
                    txtSpeciality5LA.setText(singleBooking.getSpeciality());
                    notesArray[9]=singleBooking.getNotes();
                    extraArray[9] = singleBooking.getExtraInfo(); 
                    attendanceArray[9] = singleBooking.getAttendance();
                    break;
                case "6LA":
                    txtTime6LA.setText((singleBooking.getTime()).toString());
                    txtName6LA.setText(singleBooking.getName());
                    txtAge6LA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital6LA.setText(singleBooking.getHospitalNumber());
                    txtSpeciality6LA.setText(singleBooking.getSpeciality());
                    notesArray[10]=singleBooking.getNotes();
                    extraArray[10] = singleBooking.getExtraInfo();
                    attendanceArray[10] = singleBooking.getAttendance();
                    break;
                case "ELA":
                    txtTimeELA.setText((singleBooking.getTime()).toString());
                    txtNameELA.setText(singleBooking.getName());
                    txtAgeELA.setText(String.valueOf(singleBooking.getAge()));
                    txtHospitalELA.setText(singleBooking.getHospitalNumber());
                    txtSpecialityELA.setText(singleBooking.getSpeciality());
                    notesArray[11]=singleBooking.getNotes();
                    extraArray[11] = singleBooking.getExtraInfo();
                    attendanceArray[11] = singleBooking.getAttendance();                    
                    break;
                case "1MP":
                    txtTime1MP.setText((singleBooking.getTime()).toString());
                    txtName1MP.setText(singleBooking.getName());
                    txtAge1MP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital1MP.setText(singleBooking.getHospitalNumber());
                    txtSpeciality1MP.setText(singleBooking.getSpeciality());
                    notesArray[12]=singleBooking.getNotes();
                    extraArray[12] = singleBooking.getExtraInfo();
                    attendanceArray[12] = singleBooking.getAttendance();                   
                    break;
                case "2MP":
                    txtTime2MP.setText((singleBooking.getTime()).toString());
                    txtName2MP.setText(singleBooking.getName());
                    txtAge2MP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital2MP.setText(singleBooking.getHospitalNumber());
                    txtSpeciality2MP.setText(singleBooking.getSpeciality());
                    notesArray[13]=singleBooking.getNotes();
                    extraArray[13] = singleBooking.getExtraInfo();
                    attendanceArray[13] = singleBooking.getAttendance();
                    break;
                case "3MP":
                    txtTime3MP.setText((singleBooking.getTime()).toString());
                    txtName3MP.setText(singleBooking.getName());
                    txtAge3MP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital3MP.setText(singleBooking.getHospitalNumber());
                    txtSpeciality3MP.setText(singleBooking.getSpeciality());
                    notesArray[14]=singleBooking.getNotes();
                    extraArray[14] = singleBooking.getExtraInfo();
                    attendanceArray[14] = singleBooking.getAttendance(); 
                    break;
                case "4MP":
                    txtTime4MP.setText((singleBooking.getTime()).toString());
                    txtName4MP.setText(singleBooking.getName());
                    txtAge4MP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital4MP.setText(singleBooking.getHospitalNumber());
                    txtSpeciality4MP.setText(singleBooking.getSpeciality());
                    notesArray[15]=singleBooking.getNotes();
                    extraArray[15] = singleBooking.getExtraInfo();
                    attendanceArray[15] = singleBooking.getAttendance();
                    break;
                case "EMP":
                    txtTimeEMP.setText((singleBooking.getTime()).toString());
                    txtNameEMP.setText(singleBooking.getName());
                    txtAgeEMP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospitalEMP.setText(singleBooking.getHospitalNumber());
                    txtSpecialityEMP.setText(singleBooking.getSpeciality());
                    notesArray[16]=singleBooking.getNotes();
                    extraArray[16] = singleBooking.getExtraInfo();
                    attendanceArray[16] = singleBooking.getAttendance(); 
                    break;
                case "1LP":
                    txtTime1LP.setText((singleBooking.getTime()).toString());
                    txtName1LP.setText(singleBooking.getName());
                    txtAge1LP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital1LP.setText(singleBooking.getHospitalNumber());
                    txtSpeciality1LP.setText(singleBooking.getSpeciality());
                    notesArray[17]=singleBooking.getNotes();
                    extraArray[17] = singleBooking.getExtraInfo();
                    attendanceArray[17] = singleBooking.getAttendance();
                    break;
                case "2LP":
                    txtTime2LP.setText((singleBooking.getTime()).toString());
                    txtName2LP.setText(singleBooking.getName());
                    txtAge2LP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital2LP.setText(singleBooking.getHospitalNumber());
                    txtSpeciality2LP.setText(singleBooking.getSpeciality());
                    notesArray[18]=singleBooking.getNotes();
                    extraArray[18] = singleBooking.getExtraInfo();
                    attendanceArray[18] = singleBooking.getAttendance(); 
                    break;
                case "3LP":
                    txtTime3LP.setText((singleBooking.getTime()).toString());
                    txtName3LP.setText(singleBooking.getName());
                    txtAge3LP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital3LP.setText(singleBooking.getHospitalNumber());
                    txtSpeciality3LP.setText(singleBooking.getSpeciality());
                    notesArray[19]=singleBooking.getNotes();
                    extraArray[19] = singleBooking.getExtraInfo();   
                    attendanceArray[19] = singleBooking.getAttendance();
                    break;
                case "4LP":
                    txtTime4LP.setText((singleBooking.getTime()).toString());
                    txtName4LP.setText(singleBooking.getName());
                    txtAge4LP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital4LP.setText(singleBooking.getHospitalNumber());
                    txtSpeciality4LP.setText(singleBooking.getSpeciality());
                    notesArray[20]=singleBooking.getNotes();
                    extraArray[20] = singleBooking.getExtraInfo();
                    attendanceArray[20] = singleBooking.getAttendance();
                    break;
                case "5LP":
                    txtTime5LP.setText((singleBooking.getTime()).toString());
                    txtName5LP.setText(singleBooking.getName());
                    txtAge5LP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital5LP.setText(singleBooking.getHospitalNumber());
                    txtSpeciality5LP.setText(singleBooking.getSpeciality());
                    notesArray[21]=singleBooking.getNotes();
                    extraArray[21] = singleBooking.getExtraInfo();
                    attendanceArray[21] = singleBooking.getAttendance(); 
                    break;
                case "6LP":
                    txtTime6LP.setText((singleBooking.getTime()).toString());
                    txtName6LP.setText(singleBooking.getName());
                    txtAge6LP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospital6LP.setText(singleBooking.getHospitalNumber());
                    txtSpeciality6LP.setText(singleBooking.getSpeciality());
                    notesArray[22]=singleBooking.getNotes();
                    extraArray[22] = singleBooking.getExtraInfo();
                    attendanceArray[22] = singleBooking.getAttendance();
                    break;
                case "ELP":
                    txtTimeELP.setText((singleBooking.getTime()).toString());
                    txtNameELP.setText(singleBooking.getName());
                    txtAgeELP.setText(String.valueOf(singleBooking.getAge()));
                    txtHospitalELP.setText(singleBooking.getHospitalNumber());
                    txtSpecialityELP.setText(singleBooking.getSpeciality());
                    notesArray[23]=singleBooking.getNotes();
                    extraArray[23] = singleBooking.getExtraInfo();
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
        
        int count3 = 0;
        for(TextField text3 : extraList)
        {
            extraInfo(text3, extraArray[count3]);
            count3++;
        }
              
    }
    
    //Resetting the main diary 
    public void clearDiary()
    {
        attendanceArray = new int[24];
        notesArray = new int[24];
        extraArray = codeBank.newStringArray();
        
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
    
    
    //Showing the correct symbol in the notes column 
    public void showNotes(TextField txt, int notes)
    {
        if(notes == 0)
        {
            txt.setText("-");
            txt.setStyle(" -fx-text-fill: #000000"); //black
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
    
    //Showing the correct colour for attendance 
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
            
            
            //implement query - SAVING EACH LINE                       
            String[] queries = new String[24];
            queries[0] = save1MA(stringDate);
            queries[1] = save2MA(stringDate);
            queries[2] = save3MA(stringDate);
            queries[3] = save4MA(stringDate);
            queries[4] = saveEMA(stringDate);
            queries[5] = save1LA(stringDate);
            queries[6] = save2LA(stringDate);
            queries[7] = save3LA(stringDate);
            queries[8] = save4LA(stringDate);
            queries[9] = save5LA(stringDate);
            queries[10] = save6LA(stringDate);
            queries[11] = saveELA(stringDate);
            queries[12] = save1MP(stringDate);
            queries[13] = save2MP(stringDate);
            queries[14] = save3MP(stringDate);
            queries[15] = save4MP(stringDate);
            queries[16] = saveEMP(stringDate);
            queries[17] = save1LP(stringDate);
            queries[18] = save2LP(stringDate);
            queries[19] = save3LP(stringDate);
            queries[20] = save4LP(stringDate);
            queries[21] = save5LP(stringDate);
            queries[22] = save6LP(stringDate);
            queries[23] = saveELP(stringDate);
            
            for(int i=0; i<queries.length; i++)
            {
               stmt.executeUpdate(queries[i]);
            }
            
            String[] staffQueries = new String[6];
            staffQueries[0] = saveStaff1(stringDate);
            staffQueries[1] = saveStaff2(stringDate);
            staffQueries[2] = saveStaff3(stringDate);
            staffQueries[3] = saveStaff4(stringDate);
            staffQueries[4] = saveStaff5(stringDate);
            staffQueries[5] = saveStaff6(stringDate);
            
            for(int i=0; i<staffQueries.length; i++)
            {
               stmt.executeUpdate(staffQueries[i]);
            }
            
            //implement query - SAVING NOTES AT TOP
            String sql = "REPLACE INTO notes (Date, Notes) VALUES (' "
                                                            + stringDate + "','"
                                                            + txtNotes.getText() + "')"   ;            
            stmt.executeUpdate(sql);        
                    
            c.close();
        }
        catch (SQLException e)
        {
            
        } 
    }
    
    
    
    
    
    
    
    
    
    //EACH SAVING METHOD FOR EACH MEMBER OF STAFF ------------------------------------------------------------------------------
    
    public String saveStaff1(String date)
    {      
        if(!cbStaff1.getValue().toString().equals(""))
        {
            String line = cbStaff1.getValue().toString();
            int first = line.indexOf("(");
            int second = line.indexOf(")");
            String ID = line.substring(first+1, second);
            String Shift = cbShift1.getValue().toString();
            
            return "REPLACE INTO working (Staff_ID, Date, Shift) VALUES (' "
                                                                + ID + "','"
                                                                + date + "','"
                                                                + Shift + "')"   ;
        }
        else
        {
            if(staff.get(0) != null)
            {
                return "DELETE FROM working WHERE Date = '" + date + "' AND Staff_ID = '" + staff.get(0).getID() + "'";
            }
            else
            {
                return "";
            }
        }
    }
     
    public String saveStaff2(String date)
    {
        if(!cbStaff2.getValue().toString().equals("") && staff.size() <=2)
        {
            String line = cbStaff2.getValue().toString();
            int first = line.indexOf("(");
            int second = line.indexOf(")");
            String ID = line.substring(first+1, second);
            String Shift = cbShift2.getValue().toString();
            
            return "REPLACE INTO working (Staff_ID, Date, Shift) VALUES (' "
                                                                + ID + "','"
                                                                + date + "','"
                                                                + Shift + "')"   ;
        }
        else
        {
            if(staff.size() >= 2)
            {
                return "DELETE FROM working WHERE Date = '" + date + "' AND Staff_ID = '" + staff.get(1).getID() + "'";
            }
            else
            {
                return "";
            }
        }
        
    }

    public String saveStaff3(String date)
    {
        if(!cbStaff3.getValue().toString().equals("") && staff.size() <=3)
        {
            String line = cbStaff3.getValue().toString();
            int first = line.indexOf("(");
            int second = line.indexOf(")");
            String ID = line.substring(first+1, second);
            String Shift = cbShift3.getValue().toString();
            
            return "REPLACE INTO working (Staff_ID, Date, Shift) VALUES (' "
                                                                + ID + "','"
                                                                + date + "','"
                                                                + Shift + "')"   ;
        }
        else
        {
            if(staff.size() >= 3)
            {
                return "DELETE FROM working WHERE Date = '" + date + "' AND Staff_ID = '" + staff.get(2).getID() + "'";
            }
            else
            {
                return "";
            }
        }
    }

    public String saveStaff4(String date)
    {
        if(!cbStaff4.getValue().toString().equals("") && staff.size() <=4)
        {
            String line = cbStaff4.getValue().toString();
            int first = line.indexOf("(");
            int second = line.indexOf(")");
            String ID = line.substring(first+1, second);
            String Shift = cbShift4.getValue().toString();
            
            return "REPLACE INTO working (Staff_ID, Date, Shift) VALUES (' "
                                                                + ID + "','"
                                                                + date + "','"
                                                                + Shift + "')"   ;
        }
        else
        {
            if(staff.size() >= 4)
            {
                return "DELETE FROM working WHERE Date = '" + date + "' AND Staff_ID = '" + staff.get(3).getID() + "'";
            }
            else
            {
                return "";
            }
        }
    }

    public String saveStaff5(String date)
    {
        if(!cbStaff5.getValue().toString().equals("") && staff.size() <=5)
        {
            String line = cbStaff5.getValue().toString();
            int first = line.indexOf("(");
            int second = line.indexOf(")");
            String ID = line.substring(first+1, second);
            String Shift = cbShift5.getValue().toString();
            
            return "REPLACE INTO working (Staff_ID, Date, Shift) VALUES (' "
                                                                + ID + "','"
                                                                + date + "','"
                                                                + Shift + "')"   ;
        }
        else
        {
            if(staff.size() >= 5)
            {
                return "DELETE FROM working WHERE Date = '" + date + "' AND Staff_ID = '" + staff.get(4).getID() + "'";
            }
            else
            {
                return "";
            }
        }
    }

    public String saveStaff6(String date)
    {
        if(!cbStaff6.getValue().toString().equals(""))
        {
            String line = cbStaff6.getValue().toString();
            int first = line.indexOf("(");
            int second = line.indexOf(")");
            String ID = line.substring(first+1, second);
            String Shift = cbShift6.getValue().toString();
            
            return "REPLACE INTO working (Staff_ID, Date, Shift) VALUES (' "
                                                                + ID + "','"
                                                                + date + "','"
                                                                + Shift + "')"   ;
        }
        else
        {
            if(staff.size() >= 6)
            {
                return "DELETE FROM working WHERE Date = '" + date + "' AND Staff_ID = '" + staff.get(5).getID() + "'";
            }
            else
            {
                return "";
            }
        }
    }    
    
    
    
    
    
    
    
    
    //EACH SAVING METHOD FOR EACH ROW --------------------------------------------------------------------------------------------
    
    public String save1MA(String date)
    {
        if(!txtTime1MA.getText().equals("") & !txtName1MA.getText().equals("") & !txtAge1MA.getText().equals("") & !txtHospital1MA.getText().equals("") & !txtSpeciality1MA.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "1MA" + "','"
                                                                                + txtTime1MA.getText() + "','"
                                                                                + txtName1MA.getText() + "','"
                                                                                + txtAge1MA.getText() + "','"
                                                                                + txtHospital1MA.getText() + "','"
                                                                                + txtSpeciality1MA.getText() + "','"
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
    
    
   
    public String save2MA(String date)
    {
        if(!txtTime2MA.getText().equals("") & !txtName2MA.getText().equals("") & !txtAge2MA.getText().equals("") & !txtHospital2MA.getText().equals("") & !txtSpeciality2MA.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "2MA" + "','"
                                                                                + txtTime2MA.getText() + "','"
                                                                                + txtName2MA.getText() + "','"
                                                                                + txtAge2MA.getText() + "','"
                                                                                + txtHospital2MA.getText() + "','"
                                                                                + txtSpeciality2MA.getText() + "','"
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
    
    
    
    public String save3MA(String date)
    {
        if(!txtTime3MA.getText().equals("") & !txtName3MA.getText().equals("") & !txtAge3MA.getText().equals("") & !txtHospital3MA.getText().equals("") & !txtSpeciality3MA.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "3MA" + "','"
                                                                                + txtTime3MA.getText() + "','"
                                                                                + txtName3MA.getText() + "','"
                                                                                + txtAge3MA.getText() + "','"
                                                                                + txtHospital3MA.getText() + "','"
                                                                                + txtSpeciality3MA.getText() + "','"
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
    
    
    
    public String save4MA(String date)
    {
        if(!txtTime4MA.getText().equals("") & !txtName4MA.getText().equals("") & !txtAge4MA.getText().equals("") & !txtHospital4MA.getText().equals("") & !txtSpeciality4MA.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "4MA" + "','"
                                                                                + txtTime4MA.getText() + "','"
                                                                                + txtName4MA.getText() + "','"
                                                                                + txtAge4MA.getText() + "','"
                                                                                + txtHospital4MA.getText() + "','"
                                                                                + txtSpeciality4MA.getText() + "','"
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
    
    
    public String saveEMA(String date)
    {
        if(!txtTimeEMA.getText().equals("") & !txtNameEMA.getText().equals("") & !txtAgeEMA.getText().equals("") & !txtHospitalEMA.getText().equals("") & !txtSpecialityEMA.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "EMA" + "','"
                                                                                + txtTimeEMA.getText() + "','"
                                                                                + txtNameEMA.getText() + "','"
                                                                                + txtAgeEMA.getText() + "','"
                                                                                + txtHospitalEMA.getText() + "','"
                                                                                + txtSpecialityEMA.getText() + "','"
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
    
    
    public String save1LA(String date)
    {
        if(!txtTime1LA.getText().equals("") & !txtName1LA.getText().equals("") & !txtAge1LA.getText().equals("") & !txtHospital1LA.getText().equals("") & !txtSpeciality1LA.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "1LA" + "','"
                                                                                + txtTime1LA.getText() + "','"
                                                                                + txtName1LA.getText() + "','"
                                                                                + txtAge1LA.getText() + "','"
                                                                                + txtHospital1LA.getText() + "','"
                                                                                + txtSpeciality1LA.getText() + "','"
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
    
    
    
    public String save2LA(String date)
    {
        if(!txtTime2LA.getText().equals("") & !txtName2LA.getText().equals("") & !txtAge2LA.getText().equals("") & !txtHospital2LA.getText().equals("") & !txtSpeciality2LA.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "2LA" + "','"
                                                                                + txtTime2LA.getText() + "','"
                                                                                + txtName2LA.getText() + "','"
                                                                                + txtAge2LA.getText() + "','"
                                                                                + txtHospital2LA.getText() + "','"
                                                                                + txtSpeciality2LA.getText() + "','"
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
    
    public String save3LA(String date)
    {
        if(!txtTime3LA.getText().equals("") & !txtName3LA.getText().equals("") & !txtAge3LA.getText().equals("") & !txtHospital3LA.getText().equals("") & !txtSpeciality3LA.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "3LA" + "','"
                                                                                + txtTime3LA.getText() + "','"
                                                                                + txtName3LA.getText() + "','"
                                                                                + txtAge3LA.getText() + "','"
                                                                                + txtHospital3LA.getText() + "','"
                                                                                + txtSpeciality3LA.getText() + "','"
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
    
    
    public String save4LA(String date)
    {
        if(!txtTime4LA.getText().equals("") & !txtName4LA.getText().equals("") & !txtAge4LA.getText().equals("") & !txtHospital4LA.getText().equals("") & !txtSpeciality4LA.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "4LA" + "','"
                                                                                + txtTime4LA.getText() + "','"
                                                                                + txtName4LA.getText() + "','"
                                                                                + txtAge4LA.getText() + "','"
                                                                                + txtHospital4LA.getText() + "','"
                                                                                + txtSpeciality4LA.getText() + "','"
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
    
    
    public String save5LA(String date)
    {
        if(!txtTime5LA.getText().equals("") & !txtName5LA.getText().equals("") & !txtAge5LA.getText().equals("") & !txtHospital5LA.getText().equals("") & !txtSpeciality5LA.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "5LA" + "','"
                                                                                + txtTime5LA.getText() + "','"
                                                                                + txtName5LA.getText() + "','"
                                                                                + txtAge5LA.getText() + "','"
                                                                                + txtHospital5LA.getText() + "','"
                                                                                + txtSpeciality5LA.getText() + "','"
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
    
    
    
    public String save6LA(String date)
    {
        if(!txtTime6LA.getText().equals("") & !txtName6LA.getText().equals("") & !txtAge6LA.getText().equals("") & !txtHospital6LA.getText().equals("") & !txtSpeciality6LA.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "6LA" + "','"
                                                                                + txtTime6LA.getText() + "','"
                                                                                + txtName6LA.getText() + "','"
                                                                                + txtAge6LA.getText() + "','"
                                                                                + txtHospital6LA.getText() + "','"
                                                                                + txtSpeciality6LA.getText() + "','"
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
    
    
    public String saveELA(String date)
    {
        if(!txtTimeELA.getText().equals("") & !txtNameELA.getText().equals("") & !txtAgeELA.getText().equals("") & !txtHospitalELA.getText().equals("") & !txtSpecialityELA.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "ELA" + "','"
                                                                                + txtTimeELA.getText() + "','"
                                                                                + txtNameELA.getText() + "','"
                                                                                + txtAgeELA.getText() + "','"
                                                                                + txtHospitalELA.getText() + "','"
                                                                                + txtSpecialityELA.getText() + "','"
                                                                                + extraArray[11] + "','"
                                                                                + notesArray[11] + "','"
                                                                                + attendanceArray[11] + "')"
                    );
        }
        else
        {
            return "";
        }
    }    
    
 //***************************************************************************************   
    
    public String save1MP(String date)
    {
        if(!txtTime1MP.getText().equals("") & !txtName1MP.getText().equals("") & !txtAge1MP.getText().equals("") & !txtHospital1MP.getText().equals("") & !txtSpeciality1MP.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "1MP" + "','"
                                                                                + txtTime1MP.getText() + "','"
                                                                                + txtName1MP.getText() + "','"
                                                                                + txtAge1MP.getText() + "','"
                                                                                + txtHospital1MP.getText() + "','"
                                                                                + txtSpeciality1MP.getText() + "','"
                                                                                + extraArray[12] + "','"
                                                                                + notesArray[12] + "','"
                                                                                + attendanceArray[12] + "')"
                    );
        }
        else
        {
            return "";
        }
    }  
    
    
   
    public String save2MP(String date)
    {
        if(!txtTime2MP.getText().equals("") & !txtName2MP.getText().equals("") & !txtAge2MP.getText().equals("") & !txtHospital2MP.getText().equals("") & !txtSpeciality2MP.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "2MP" + "','"
                                                                                + txtTime2MP.getText() + "','"
                                                                                + txtName2MP.getText() + "','"
                                                                                + txtAge2MP.getText() + "','"
                                                                                + txtHospital2MP.getText() + "','"
                                                                                + txtSpeciality2MP.getText() + "','"
                                                                                + extraArray[13] + "','"
                                                                                + notesArray[13] + "','"
                                                                                + attendanceArray[13] + "')"
                    );
        }
        else
        {
            return "";
        }
    }   
    
    
    
    public String save3MP(String date)
    {
        if(!txtTime3MP.getText().equals("") & !txtName3MP.getText().equals("") & !txtAge3MP.getText().equals("") & !txtHospital3MP.getText().equals("") & !txtSpeciality3MP.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "3MP" + "','"
                                                                                + txtTime3MP.getText() + "','"
                                                                                + txtName3MP.getText() + "','"
                                                                                + txtAge3MP.getText() + "','"
                                                                                + txtHospital3MP.getText() + "','"
                                                                                + txtSpeciality3MP.getText() + "','"
                                                                                + extraArray[14] + "','"
                                                                                + notesArray[14] + "','"
                                                                                + attendanceArray[14] + "')"
                    );
        }
        else
        {
            return "";
        }
    } 
    
    
    
    public String save4MP(String date)
    {
        if(!txtTime4MP.getText().equals("") & !txtName4MP.getText().equals("") & !txtAge4MP.getText().equals("") & !txtHospital4MP.getText().equals("") & !txtSpeciality4MP.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "4MP" + "','"
                                                                                + txtTime4MP.getText() + "','"
                                                                                + txtName4MP.getText() + "','"
                                                                                + txtAge4MP.getText() + "','"
                                                                                + txtHospital4MP.getText() + "','"
                                                                                + txtSpeciality4MP.getText() + "','"
                                                                                + extraArray[15] + "','"
                                                                                + notesArray[15] + "','"
                                                                                + attendanceArray[15] + "')"
                    );
        }
        else
        {
            return "";
        }
    }     
    
    
    public String saveEMP(String date)
    {
        if(!txtTimeEMP.getText().equals("") & !txtNameEMP.getText().equals("") & !txtAgeEMP.getText().equals("") & !txtHospitalEMP.getText().equals("") & !txtSpecialityEMP.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "EMP" + "','"
                                                                                + txtTimeEMP.getText() + "','"
                                                                                + txtNameEMP.getText() + "','"
                                                                                + txtAgeEMP.getText() + "','"
                                                                                + txtHospitalEMP.getText() + "','"
                                                                                + txtSpecialityEMP.getText() + "','"
                                                                                + extraArray[16] + "','"
                                                                                + notesArray[16] + "','"
                                                                                + attendanceArray[16] + "')"
                    );
        }
        else
        {
            return "";
        }
    }     
    
    
    public String save1LP(String date)
    {
        if(!txtTime1LP.getText().equals("") & !txtName1LP.getText().equals("") & !txtAge1LP.getText().equals("") & !txtHospital1LP.getText().equals("") & !txtSpeciality1LP.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "1LP" + "','"
                                                                                + txtTime1LP.getText() + "','"
                                                                                + txtName1LP.getText() + "','"
                                                                                + txtAge1LP.getText() + "','"
                                                                                + txtHospital1LP.getText() + "','"
                                                                                + txtSpeciality1LP.getText() + "','"
                                                                                + extraArray[17] + "','"
                                                                                + notesArray[17] + "','"
                                                                                + attendanceArray[17] + "')"
                    );
        }
        else
        {
            return "";
        }
    }     
    
    
    
    public String save2LP(String date)
    {
        if(!txtTime2LP.getText().equals("") & !txtName2LP.getText().equals("") & !txtAge2LP.getText().equals("") & !txtHospital2LP.getText().equals("") & !txtSpeciality2LP.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "2LP" + "','"
                                                                                + txtTime2LP.getText() + "','"
                                                                                + txtName2LP.getText() + "','"
                                                                                + txtAge2LP.getText() + "','"
                                                                                + txtHospital2LP.getText() + "','"
                                                                                + txtSpeciality2LP.getText() + "','"
                                                                                + extraArray[18] + "','"
                                                                                + notesArray[18] + "','"
                                                                                + attendanceArray[18] + "')"
                    );
        }
        else
        {
            return "";
        }
    }       
    
    public String save3LP(String date)
    {
        if(!txtTime3LP.getText().equals("") & !txtName3LP.getText().equals("") & !txtAge3LP.getText().equals("") & !txtHospital3LP.getText().equals("") & !txtSpeciality3LP.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "3LP" + "','"
                                                                                + txtTime3LP.getText() + "','"
                                                                                + txtName3LP.getText() + "','"
                                                                                + txtAge3LP.getText() + "','"
                                                                                + txtHospital3LP.getText() + "','"
                                                                                + txtSpeciality3LP.getText() + "','"
                                                                                + extraArray[19] + "','"
                                                                                + notesArray[19] + "','"
                                                                                + attendanceArray[19] + "')"
                    );
        }
        else
        {
            return "";
        }
    }       
    
    
    public String save4LP(String date)
    {
        if(!txtTime4LP.getText().equals("") & !txtName4LP.getText().equals("") & !txtAge4LP.getText().equals("") & !txtHospital4LP.getText().equals("") & !txtSpeciality4LP.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "4LP" + "','"
                                                                                + txtTime4LP.getText() + "','"
                                                                                + txtName4LP.getText() + "','"
                                                                                + txtAge4LP.getText() + "','"
                                                                                + txtHospital4LP.getText() + "','"
                                                                                + txtSpeciality4LP.getText() + "','"
                                                                                + extraArray[20] + "','"
                                                                                + notesArray[20] + "','"
                                                                                + attendanceArray[20] + "')"
                    );
        }
        else
        {
            return "";
        }
    }      
    
    
    public String save5LP(String date)
    {
        if(!txtTime5LP.getText().equals("") & !txtName5LP.getText().equals("") & !txtAge5LP.getText().equals("") & !txtHospital5LP.getText().equals("") & !txtSpeciality5LP.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "5LP" + "','"
                                                                                + txtTime5LP.getText() + "','"
                                                                                + txtName5LP.getText() + "','"
                                                                                + txtAge5LP.getText() + "','"
                                                                                + txtHospital5LP.getText() + "','"
                                                                                + txtSpeciality5LP.getText() + "','"
                                                                                + extraArray[21] + "','"
                                                                                + notesArray[21] + "','"
                                                                                + attendanceArray[21] + "')"
                    );
        }
        else
        {
            return "";
        }
    }   
    
    
    
    public String save6LP(String date)
    {
        if(!txtTime6LP.getText().equals("") & !txtName6LP.getText().equals("") & !txtAge6LP.getText().equals("") & !txtHospital6LP.getText().equals("") & !txtSpeciality6LP.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "6LP" + "','"
                                                                                + txtTime6LP.getText() + "','"
                                                                                + txtName6LP.getText() + "','"
                                                                                + txtAge6LP.getText() + "','"
                                                                                + txtHospital6LP.getText() + "','"
                                                                                + txtSpeciality6LP.getText() + "','"
                                                                                + extraArray[22] + "','"
                                                                                + notesArray[22] + "','"
                                                                                + attendanceArray[22] + "')"
                    );
        }
        else
        {
            return "";
        }
    }    
    
    
    public String saveELP(String date)
    {
        if(!txtTimeELP.getText().equals("") & !txtNameELP.getText().equals("") & !txtAgeELP.getText().equals("") & !txtHospitalELP.getText().equals("") & !txtSpecialityELP.getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + "ELP" + "','"
                                                                                + txtTimeELP.getText() + "','"
                                                                                + txtNameELP.getText() + "','"
                                                                                + txtAgeELP.getText() + "','"
                                                                                + txtHospitalELP.getText() + "','"
                                                                                + txtSpecialityELP.getText() + "','"
                                                                                + extraArray[23] + "','"
                                                                                + notesArray[23] + "','"
                                                                                + attendanceArray[23] + "')"
                    );
        }
        else
        {
            return "";
        }
    }        
    
}//END OF CLASS

