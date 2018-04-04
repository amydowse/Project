/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
https://stackoverflow.com/questions/42569204/is-it-possible-to-reload-the-same-fxml-controller-instance
 */
package diary;

import common.DatabaseConnector;
import common.HelpDialogController;
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
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.TextFields;

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
    @FXML private List<TextField> timeList;
    @FXML private List<TextField> nameList;
    @FXML private List<TextField> ageList;
    @FXML private List<TextField> hospitalList;
    @FXML private List<TextField> specialityList;
    @FXML private List<TextField> notesList;
    @FXML private List<TextField> extraList;
    
    @FXML private Hyperlink hlHelp = new Hyperlink();
    
    @FXML private List<ChoiceBox> staffList;
    @FXML private List<ChoiceBox> shiftList;
    
    private ArrayList<workingStaff> staff = new ArrayList<workingStaff>();
    
    int[] attendanceArray = new int[24];
    int[] notesArray = new int[24];
    String[] extraArray = codeBank.newStringArray(11);
    private Boolean notes = false;
    String[] bedArray = new String[24];
    
    boolean issue = false;
   
      
    //METHODS -----------------------------------------------------------------------
     
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {    
        fillBedArray();
        showInformation(codeBank.getCurrentDate());
        fillStaffDropDowns(); 
        delete();
        
        setUpAutoComplete();
        
        //https://stackoverflow.com/questions/42943652/how-to-trigger-an-event-on-focus-out-for-a-textfield-in-javafx-using-fxml accessed 24/2
        for(int i=0; i<24; i++)
        {
            TextField selected = ageList.get(i);
            ageList.get(i).focusedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldV, Boolean newV) -> {
            if (!newV) 
            { 
                checkingAge(selected);
            }
            });
        }
        
        for(int i=0; i<24; i++)
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
    
    public void checkingAge(TextField selected)
    {
        String value = selected.getText();
        
        if(!codeBank.checkAge(value))
        {
            codeBank.ageError();
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
    
    
    
    
    public void fillBedArray()
    {
        bedArray[0] = "1MA";
        bedArray[1] = "2MA";
        bedArray[2] = "3MA";
        bedArray[3] = "4MA";
        bedArray[4] = "EMA";
        bedArray[5] = "1LA";
        bedArray[6] = "2LA";
        bedArray[7] = "3LA";
        bedArray[8] = "4LA";
        bedArray[9] = "5LA";
        bedArray[10] = "6LA";
        bedArray[11] = "ELA";
        bedArray[12] = "1MP";
        bedArray[13] = "2MP";
        bedArray[14] = "3MP";
        bedArray[15] = "4MP";
        bedArray[16] = "EMP";
        bedArray[17] = "1LP";
        bedArray[18] = "2LP";
        bedArray[19] = "3LP";
        bedArray[20] = "4LP";
        bedArray[21] = "5LP";
        bedArray[22] = "6LP";
        bedArray[23] = "ELP";
        
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
        
        codeBank.attendanceColour(attendanceList.get(arrayValue),attendanceArray[arrayValue]);
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
                
        codeBank.showNotes(notesList.get(arrayValue),notesArray[arrayValue]);
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
                  
            for(int i=0; i<6 ; i++)
            {
                staffList.get(i).getItems().add("");
                shiftList.get(i).getItems().add("");
            }
            
            
            while(rs.next())
            { 
                String firstname = rs.getString("FirstName");
                int ID = rs.getInt("ID");
                
                String text = "(" + ID + ") " + firstname;
                
                for(int i=0; i<6 ; i++)
                {
                    staffList.get(i).getItems().add(text);
                } 
            }
            c.close();
            
            for(int i=0; i<6 ; i++)
            {
                shiftList.get(i).getItems().addAll("E", "L", "LD");
            }
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
            printStaffNames();
            c.close();
        }
        catch (SQLException e)
        {
            
        } 
    }
    
    public void printStaffNames()
    {
        for(int i=0; i<staff.size(); i++)
        {
            String value = "(" + staff.get(i).getID() + ") " + staff.get(i).getFirstName();
            staffList.get(i).setValue(value);
            shiftList.get(i).setValue(staff.get(i).getShift());
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
                    notes = true;
                    txtNotes.setText("NOTES \n\n" + rs.getString("Notes")); 
            }
            c.close();
        }
        catch (SQLException e)
        {
            
        }  
    }
    
    
   
    
    
   
    
    
    //Getting the main diary information 
    public void showInformation(LocalDate SearchDate)
    {   clearAll();
        showStaff(codeBank.getCurrentDate());
        showNotes(codeBank.getCurrentDate());
        
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
    
    public void showSingle(diary singleBooking, int i)
    {
        timeList.get(i).setText((singleBooking.getTime()).toString());
        nameList.get(i).setText(singleBooking.getName());
        ageList.get(i).setText(String.valueOf(singleBooking.getAge()));
        hospitalList.get(i).setText(singleBooking.getHospitalNumber());
        specialityList.get(i).setText(singleBooking.getSpeciality());
        notesArray[i] = singleBooking.getNotes();
        extraArray[i] = singleBooking.getExtraInfo();
        attendanceArray[i] = singleBooking.getAttendance();
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
                    showSingle(singleBooking, 0);
                    break;
                case "2MA":
                    showSingle(singleBooking, 1);
                    break;
                case "3MA":
                    showSingle(singleBooking, 2);
                    break;
                case "4MA":
                    showSingle(singleBooking, 3);
                    break;
                case "EMA":
                    showSingle(singleBooking, 4);
                    break;
                case "1LA":
                    showSingle(singleBooking, 5);
                    break;
                case "2LA":
                    showSingle(singleBooking, 6);
                    break;
                case "3LA":
                    showSingle(singleBooking, 7);
                    break;
                case "4LA":
                    showSingle(singleBooking, 8);
                    break;
                case "5LA":
                    showSingle(singleBooking, 9);
                    break;
                case "6LA":
                    showSingle(singleBooking, 10);
                    break;
                case "ELA":
                    showSingle(singleBooking, 11);                 
                    break;
                case "1MP":
                   showSingle(singleBooking, 12);                  
                    break;
                case "2MP":
                    showSingle(singleBooking, 13);
                    break;
                case "3MP":
                    showSingle(singleBooking, 14);
                    break;
                case "4MP":
                    showSingle(singleBooking, 15);
                    break;
                case "EMP":
                    showSingle(singleBooking, 16);
                    break;
                case "1LP":
                    showSingle(singleBooking, 17);
                    break;
                case "2LP":
                    showSingle(singleBooking, 18);
                    break;
                case "3LP":
                    showSingle(singleBooking, 19);
                    break;
                case "4LP":
                    showSingle(singleBooking, 20);
                    break;
                case "5LP":
                    showSingle(singleBooking, 21);
                    break;
                case "6LP":
                    showSingle(singleBooking, 22);
                    break;
                case "ELP":
                    showSingle(singleBooking, 23);                  
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
        
        int count3 = 0;
        for(TextField text3 : extraList)
        {
            codeBank.extraInfo(text3, extraArray[count3]);
            count3++;
        }
              
    }
    
    
    
    
    public void clearAll()
    {
        clearTopSection();
        clearDiary();
        allBookings.clear();
    }
      
    public void clearTopSection()
    {
        notes = false;
        issue = false;
        staff.clear();
        txtNotes.setText("");
        
        for(int i = 0; i<6; i++)
        {
            staffList.get(i).setValue("");
            shiftList.get(i).setValue("");
        }
    }
    
    //Resetting the main diary 
    public void clearDiary()
    {
        attendanceArray = new int[24];
        notesArray = new int[24];
        extraArray = codeBank.newStringArray(24);
        
        for(int i=0; i< 24; i++)
        {
            clearSingle(i);
        }
    }
    
    public void clearSingle(int i)
    {
        timeList.get(i).setText("");
        nameList.get(i).setText("");
        ageList.get(i).setText("");
        hospitalList.get(i).setText("");
        specialityList.get(i).setText("");
        attendanceArray[i] = 0;
        notesArray[i] = 0;
        codeBank.attendanceColour(attendanceList.get(i), 0);
        codeBank.showNotes(notesList.get(i), 0);
        
    }
    
   
   
    
    
    
    //SAVING-----------------------------------------------------------------
    public boolean beforeSave()
    {
        for(int i=0; i<24; i++)
        {
            if(!timeList.get(i).getText().equals("") & !nameList.get(i).getText().equals("") & !ageList.get(i).getText().equals("") & !hospitalList.get(i).getText().equals("") & !specialityList.get(i).getText().equals(""))
            {
                //not data entered 
            }
            else if(!timeList.get(i).getText().equals("") || !nameList.get(i).getText().equals("") || !ageList.get(i).getText().equals("") || !hospitalList.get(i).getText().equals("") || !specialityList.get(i).getText().equals(""))
            {
                System.out.println("Diary");
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
            
            for(int i=0; i<24; i++)
            {
                String sql = SQLLine(i, stringDate);
                stmt.executeUpdate(sql);
            }
            
            stmt.executeUpdate(saveNotes(stringDate));
            
            stmt.executeUpdate(deleteStaff(stringDate));
            
            for(int i=0; i<6; i++)
            {
                String sql = saveStaff(i, stringDate);
                stmt.executeUpdate(sql);
            }
            
            c.close();
            
            clearAll();
        }
        catch (SQLException e)
        {
            
        } 
        
    }
    
    //----------------------------------------------------------------------------------------------------
    
    
    public String deleteStaff(String date)
    {
        return "DELETE FROM working WHERE Date = '" + date + "'" ;
    }
    
    public String saveStaff(int i, String date)
    {
        if(!staffList.get(i).getValue().equals(""))
        {
            String line = staffList.get(i).getValue().toString();
            int first = line.indexOf("(");
            int second = line.indexOf(")");
            String ID = line.substring(first+1, second);
            String Shift = shiftList.get(i).getValue().toString();
                        
            return "INSERT INTO working (Staff_ID, Date, Shift) VALUES (' "
                                                                + ID + "','"
                                                                + date + "','"
                                                                + Shift + "')"   ;
        }
        else
        {
            return "";
        }
    }
    
    
    
    
    //OK
    public String SQLLine(int i, String date)
    {
        if(!timeList.get(i).getText().equals("") & !nameList.get(i).getText().equals("") & !ageList.get(i).getText().equals("") & !hospitalList.get(i).getText().equals("") & !specialityList.get(i).getText().equals(""))
        {
            return ("REPLACE INTO diary (Date, BedNumber, Time, Name, Age, HospitalNumber, Speciality, ExtraInfo, Notes, Attendance) VALUES('"      
                                                                                + date + "','"
                                                                                + bedArray[i] + "','"
                                                                                + timeList.get(i).getText() + "','"
                                                                                + nameList.get(i).getText() + "','"
                                                                                + ageList.get(i).getText() + "','"
                                                                                + hospitalList.get(i).getText() + "','"
                                                                                + specialityList.get(i).getText() + "','"
                                                                                + extraArray[i] + "','"
                                                                                + notesArray[i] + "','"
                                                                                + attendanceArray[i] + "')"
                    );
        }
        else
        {
            return "";
        }        
    }   
    
   
    //OK
    public String saveNotes(String date)
    {
        if(notes)
        {
            if(txtNotes.getText().equals("") || txtNotes.getText().trim().equals("NOTES"))
            {
                return "DELETE FROM notes WHERE Date = '" + date + "'" ;
            }
            else
            {
                String lines[] = txtNotes.getText().split("\\r?\\n");
                return "REPLACE INTO notes (Date, Notes) VALUES ('" + date + "','" + lines[2] + "')" ;
            }
        }
        else
        {
            return "INSERT INTO notes (Date, Notes) VALUES ('" + date + "','" + txtNotes.getText() + "')" ;
        }
    }
    
    
    
    
    public void delete()
    {
        for(int i=0; i<24; i++)
        {
            deleteOption(i);
        }
    }
    
    
    public void deleteOption(int i)
    {
        TextField time = timeList.get(i);
        TextField name = nameList.get(i);
       
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

                    String sql = "DELETE FROM diary WHERE Date = '" + codeBank.dateToString(codeBank.getCurrentDate()) + "' AND Time = '" + time.getText() + "' AND BedNumber = '" + bedArray[i] + "'";

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
            
            HDC.show("Diary");
            
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
    
    
    
    
    
    
    
    
    
    public void setUpAutoComplete()
    {
        ArrayList<String> procedures = new ArrayList<String>();
        try
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);
            Statement stmt = c.createStatement();
            ResultSet rs;
            String sql = "SELECT * FROM procedures WHERE Location = 'Bed'";
            rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                procedures.add(rs.getString("Name"));
            }
        }
        catch (SQLException e)
        {
            
        }
        
        for(int i =0; i<specialityList.size(); i++)        
        TextFields.bindAutoCompletion(specialityList.get(i), procedures);  
    }
    
    
    
    
}//END OF CLASS

