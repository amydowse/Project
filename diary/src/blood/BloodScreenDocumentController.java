/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blood;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author amydo
 */
public class BloodScreenDocumentController implements Initializable
{
    @FXML TableView tblClinic;
    @FXML TableColumn tblColTime;
    @FXML TableColumn tblColName;
    @FXML TableColumn tblColDOB;
    @FXML TableColumn tblColNHS;
    @FXML TableColumn tblColNumber;    
    @FXML TableColumn tblColForm;
    @FXML TableColumn tblColNotes;
    @FXML TableColumn tblColPrevious;
    @FXML TableColumn tblColBooked;    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ObservableList allBookings = FXCollections.observableArrayList();
        
        
        //LocalTime:        plusMinutes()       http://tutorials.jenkov.com/java-date-time/localtime.html
        //Time range:       .compareTo()        https://docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html#compareTo-java.time.LocalTime-
        
        //list of ParentBlood that store just the times of all possible appointments
        //replace with blood objects when there is an actual booking 
        //if instance of PArentblood - show just time
        //if isntace of blood - show all informaiton 
        
        
        
        try
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            Statement stmt = c.createStatement();    
            
            //make localdate into string
            String stringDate = codeBank.dateToString(codeBank.getCurrentDate());
            
            //implement query
            rs = stmt.executeQuery("SELECT * FROM blood WHERE Date = '" + stringDate + "'" );
                
            while(rs.next())
            {
                String date = rs.getString("Date");
                LocalDate Date = codeBank.stringToDate(date);
                
                String time = rs.getString("Time");
                LocalTime Time = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
                
                String name = rs.getString("Name");
                
                String DOB = rs.getString("DateOfBirth");
                String NHSNumber = rs.getString("NHSNumber");
                String number = rs.getString("ContactNumber");
                String form = rs.getString("Form");
                String extraInfo = rs.getString("ExtraInfo");
                Integer previous = rs.getInt("Previous");
                String bookedBy = rs.getString("BookedBy");
                Integer attendance = rs.getInt("Attendance");
                                 
                blood x = new blood(Date, Time, name, DOB, NHSNumber, number, form, extraInfo, previous, bookedBy, attendance);
                allBookings.add(x);
                
            }
                        
            tblColTime.setCellValueFactory(new PropertyValueFactory("Time"));
            tblColName.setCellValueFactory(new PropertyValueFactory("Name"));
            tblColDOB.setCellValueFactory(new PropertyValueFactory("DateOfBirth"));
            tblColNHS.setCellValueFactory(new PropertyValueFactory("NHSNumber"));
            tblColNumber.setCellValueFactory(new PropertyValueFactory("Number"));
            tblColForm.setCellValueFactory(new PropertyValueFactory("Form"));
            tblColNotes.setCellValueFactory(new PropertyValueFactory("Notes"));
            tblColPrevious.setCellValueFactory(new PropertyValueFactory("Previous"));
            tblColBooked.setCellValueFactory(new PropertyValueFactory("BookedBy"));

            tblClinic.getItems().addAll(allBookings);

            /*
                tableReg.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
                {
                    
                });
             */
            c.close();
        }
        catch (SQLException e)
        {
            
        }
           
    }
    
}
