/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import common.DatabaseConnector;
import common.codeBank;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author amydo
 */
public class DiaryScreenDocumentController  implements Initializable
{
    //The FXML elements 
    @FXML private TextField txtOne = new TextField();
    @FXML private TextField txtTime1MA = new TextField();
    @FXML private TextField txtName1MA = new TextField();
    @FXML private TextField txtAge1MA = new TextField();
    @FXML private TextField txtHospital1MA = new TextField();
    @FXML private TextField txtSpeciality1MA = new TextField();
    @FXML private TextField txtNotes1MA = new TextField();
    @FXML private TextField txtExtra1MA = new TextField();
    
    
    
       
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        //todays date into a localDate format 
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate Today = LocalDate.now();
        
        //showing todays information 
        showInformation(Today);
        
    }
    
    public void showInformation(LocalDate SearchDate)
    {
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
                
                
                //showing results in textboxes 
                txtTime1MA.setText((instanceOfDiary.getTime()).toString());
                txtName1MA.setText(instanceOfDiary.getName());
                txtAge1MA.setText(String.valueOf(instanceOfDiary.getAge()));
                txtHospital1MA.setText(instanceOfDiary.getHospitalNumber());
                txtSpeciality1MA.setText(instanceOfDiary.getSpeciality());
                txtNotes1MA.setText(String.valueOf(instanceOfDiary.getNotes()));
                txtExtra1MA.setText(String.valueOf(instanceOfDiary.getExtraInfo()));
            }
        }
        catch (SQLException e)
        {
            
        }
        
        
        
        
        
    }
}
