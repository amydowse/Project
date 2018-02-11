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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.util.Callback;

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
    @FXML TableColumn tblColAtt;
    @FXML ChoiceBox cbStaff = new ChoiceBox();
    
    ObservableList<String> workingStaff;
    ObservableList<blood> allBookings;
    LocalTime previousTime;
    ArrayList<blood> allTimes = new ArrayList<blood>();
    ArrayList<blood> specificBookings = new ArrayList<blood>();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {     
        tblClinic.setPlaceholder(new Label("There is no blood clinic scheduled for this day"));
        workingStaff = codeBank.fillStaffDropDowns();
        
        showInformation();
    }
    
    public void showInformation()
    {
        cbStaff.valueProperty().set(null);
        
        cbStaff.setItems(workingStaff);
        showStaff(codeBank.getCurrentDate());
                
        tblClinic.getItems().clear();
        
        if(allBookings != null)
        {
            allBookings.clear();
        }
        
        if(allTimes != null)
        {
            allTimes.clear();
        }
        
        if(specificBookings != null)
        {
            specificBookings.clear();
        }
        
        
        
        tblClinic.setEditable(true);
        tblColName.setEditable(true);
        tblColDOB.setEditable(true);
        tblColNHS.setEditable(true);
        tblColNumber.setEditable(true);
        tblColForm.setEditable(true);
        tblColNotes.setEditable(true);
        tblColPrevious.setEditable(true);
        tblColBooked.setEditable(true);
         
        try
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            Statement stmt = c.createStatement();    
            
            //make localdate into string
            LocalDate currentDate = codeBank.getCurrentDate();
            String day = currentDate.getDayOfWeek().name();
            
            System.out.println(currentDate);
            System.out.println(day);
            
            //implement query
            rs = stmt.executeQuery("SELECT * FROM template WHERE Day = '" + day + "'" );
            

                
            while(rs.next())
            {
                String startTimeS = rs.getString("Start");
                LocalTime startTime = LocalTime.parse(startTimeS, DateTimeFormatter.ISO_LOCAL_TIME);
                
                String endTimeS = rs.getString("End");
                LocalTime endTime = LocalTime.parse(endTimeS, DateTimeFormatter.ISO_LOCAL_TIME);
                
                String durationS = rs.getString("Duration");
                int duration = Integer.parseInt(durationS);
                
                String breakStartS = rs.getString("BreakStart");
                LocalTime breakStart = LocalTime.parse(breakStartS, DateTimeFormatter.ISO_LOCAL_TIME);
                
                String breakEndS = rs.getString("BreakEnd");
                LocalTime breakEnd = LocalTime.parse(breakEndS, DateTimeFormatter.ISO_LOCAL_TIME);
                
                blood x = new blood(null, startTime, "", "", "", "", "", "", "", "", 0); 
                previousTime = startTime;
                allTimes.add(x);
                
                while(previousTime != endTime)
                {
                    previousTime = previousTime.plusMinutes(duration);
                    
                    if(previousTime.compareTo(breakStart) < 0)
                    {
                        x = new blood(null, previousTime, "", "", "", "", "", "", "", "", 0); 
                        allTimes.add(x);
                    }
                    else if (previousTime.compareTo(breakEnd) >= 0)
                    {
                        x = new blood(null, previousTime, "", "", "", "", "", "", "", "", 0); 
                        allTimes.add(x);
                    }
                    
                }
            }
        }
        catch(SQLException e)
        {
        }
                
        
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
                String previous = rs.getString("Previous");
                String bookedBy = rs.getString("BookedBy");
                Integer attendance = rs.getInt("Attendance");
                                                 
                blood y = new blood(Date, Time, name, DOB, NHSNumber, number, form, extraInfo, previous, bookedBy, attendance);
                specificBookings.add(y);
                
            }
            
                
            for (int T = 0; T<allTimes.size(); T++)
            {
                for(int B=0; B<specificBookings.size(); B++)
                {
                    if(allTimes.get(T).getTime().compareTo(specificBookings.get(B).getTime()) == 0)
                    {
                        allTimes.set(T, specificBookings.get(B));
                    }
                }
            }    
            
            allBookings = FXCollections.observableArrayList(allTimes);
    
            
            
            
                       
            //https://docs.oracle.com/javafx/2/ui_controls/table-view.htm accessed 10/2/18
            tblColName.setCellValueFactory(new PropertyValueFactory<blood, String>("Name"));
            tblColName.setCellFactory(TextFieldTableCell.forTableColumn());
            tblColName.setOnEditCommit(
                    new EventHandler<CellEditEvent<blood, String>>() {
                @Override
                public void handle(CellEditEvent<blood, String> t) {
                    ((blood) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setName(t.getNewValue());
                }
            }
            );
            
            
            tblColDOB.setCellValueFactory(new PropertyValueFactory<blood, String>("DateOfBirth"));
            tblColDOB.setCellFactory(TextFieldTableCell.forTableColumn());        
            tblColDOB.setOnEditCommit(
                    new EventHandler<CellEditEvent<blood, String>>() {
                @Override
                public void handle(CellEditEvent<blood, String> t) {
                    ((blood) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setDOB(t.getNewValue());
                }
            }
            );
            
            
            tblColNHS.setCellValueFactory(new PropertyValueFactory<blood, String>("NHSNumber"));
            tblColNHS.setCellFactory(TextFieldTableCell.forTableColumn());
            tblColNHS.setOnEditCommit(
                    new EventHandler<CellEditEvent<blood, String>>() {
                @Override
                public void handle(CellEditEvent<blood, String> t) {
                    ((blood) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setNHS(t.getNewValue());
                }
            }
            );
            
            tblColNumber.setCellValueFactory(new PropertyValueFactory<blood, String>("Number"));
            tblColNumber.setCellFactory(TextFieldTableCell.forTableColumn());
            tblColNumber.setOnEditCommit(
                    new EventHandler<CellEditEvent<blood, String>>() {
                @Override
                public void handle(CellEditEvent<blood, String> t) {
                    ((blood) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setNumber(t.getNewValue());
                }
            }
            );
            
            tblColForm.setCellValueFactory(new PropertyValueFactory<blood, String>("Form"));
            tblColForm.setCellFactory(TextFieldTableCell.forTableColumn());
            tblColForm.setOnEditCommit(
                    new EventHandler<CellEditEvent<blood, String>>() {
                @Override
                public void handle(CellEditEvent<blood, String> t) {
                    ((blood) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setForm(t.getNewValue());
                }
            }
            );
            
            tblColNotes.setCellValueFactory(new PropertyValueFactory<blood, String>("Notes"));
            tblColNotes.setCellFactory(TextFieldTableCell.forTableColumn());
            tblColNotes.setOnEditCommit(
                    new EventHandler<CellEditEvent<blood, String>>() {
                @Override
                public void handle(CellEditEvent<blood, String> t) {
                    ((blood) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setExtra(t.getNewValue());
                }
            }
            );
            
            tblColPrevious.setCellValueFactory(new PropertyValueFactory<blood, String>("Previous"));
            tblColPrevious.setCellFactory(TextFieldTableCell.forTableColumn());
            tblColPrevious.setOnEditCommit(
                    new EventHandler<CellEditEvent<blood, String>>() {
                @Override
                public void handle(CellEditEvent<blood, String> t) {
                    ((blood) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setPrevious(t.getNewValue());
                }
            }
            );
            
            tblColBooked.setCellValueFactory(new PropertyValueFactory<blood, String>("BookedBy"));   
            tblColBooked.setCellFactory(TextFieldTableCell.forTableColumn());
            tblColBooked.setOnEditCommit(
                    new EventHandler<CellEditEvent<blood, String>>() {
                @Override
                public void handle(CellEditEvent<blood, String> t) {
                    ((blood) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setBooked(t.getNewValue());
                }
            }
            );
            
            
            
            
            
            //-----------------------------------------------------------------------------------------
            
            
            
            //https://stackoverflow.com/questions/27281370/javafx-tableview-format-one-cell-based-on-the-value-of-another-in-the-row accessed 10/2/18
            tblColAtt.setCellValueFactory(new PropertyValueFactory<blood, String>("Att"));
            tblColAtt.setCellFactory(new Callback<TableColumn<blood, LocalTime>, TableCell<blood, LocalTime>>()
            {  
                @Override
                public TableCell<blood, LocalTime> call(TableColumn<blood, LocalTime> param)
                {
                    return new TableCell<blood, LocalTime>()
                    {
                        @Override
                        protected void updateItem(LocalTime item, boolean empty)
                        {
                            if (!empty)
                            {
                                int currentIndex = indexProperty().getValue();
                                blood type = param.getTableView().getItems().get(currentIndex);
                                
                                if(type.getAttendance() == 1)
                                {
                                    setStyle("-fx-background-color: green");
                                } 
                                else if(type.getAttendance() == 2)
                                {
                                    setStyle("-fx-background-color: red");
                                }
                                else if(type.getAttendance() == 0)
                                {
                                    setStyle("-fx-background-color: white");
                                }
                            }
                        }
                    };
                }
            });
            
            
             //https://stackoverflow.com/questions/35562037/how-to-set-click-event-for-a-cell-of-a-table-column-in-a-tableview accessed 11/2/1
            tblColTime.setCellValueFactory(new PropertyValueFactory<blood, String>("Time"));   
            tblColTime.setCellFactory(tc -> {TableCell<blood, LocalTime> cell = new TableCell<blood, LocalTime>() 
            {
                @Override
                protected void updateItem(LocalTime item, boolean empty) 
                {
                    super.updateItem(item, empty) ;
                    setText(empty ? null : item.toString());
                }
            };
            cell.setOnMouseClicked(e -> {
                if (! cell.isEmpty()) 
                {
                    LocalTime userId = cell.getItem();
                    changeAttendance(userId);
                }
                
            });
            return cell ;
        });
            
            
            tblClinic.getItems().addAll(allBookings);
            
            c.close();
        }
        catch(SQLException e)
        {
                 
        }
      
        
        //LocalTime:        plusMinutes()       http://tutorials.jenkov.com/java-date-time/localtime.html
        //Time range:       .compareTo()        https://docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html#compareTo-java.time.LocalTime-
        
        //list of basicBlood that store just the times of all possible appointments
        //replace with blood objects when there is an actual booking 
        //if instance of basicBblood - show just time
        //if isntace of blood - show all informaiton
           
    }
    
    
    
    public void changeAttendance(LocalTime time)
    {
        for(blood x : allBookings)
        {
            if(x.getTime().equals(time))
            {
                if(x.getAttendance() == 0)
                {
                    x.setAttendance(1);
                }
                else if (x.getAttendance() == 1)
                {
                    x.setAttendance(2);
                }
                else
                {
                    x.setAttendance(0);
                }
            }
        }
        save();
        showInformation();
    }
    
    
    
    public void printRow(blood item)
    {
        System.out.println(item.getName());
    }
    
    
    public void save()
    {
        for (blood appointment : allBookings)
        {
            if(!appointment.getName().equals(""))
            {
                saveToDatabase(appointment);
            }
        }
    }
    
    public void saveToDatabase(blood appointment)
    {
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();
            
            String date = codeBank.dateToString(codeBank.getCurrentDate());
                        
            String sql = "REPLACE INTO blood (Date, Time, Name, DateOfBirth, NHSNumber, ContactNumber, Form, ExtraInfo, Previous, BookedBy, Attendance) VALUES('"
                                                                                                                + date + "','"
                                                                                                                + appointment.getTime() + "','"
                                                                                                                + appointment.getName() + "','"
                                                                                                                + appointment.getDateOfBirth() + "','"
                                                                                                                + appointment.getNHSNumber() + "','"
                                                                                                                + appointment.getNumber() + "','"
                                                                                                                + appointment.getForm() + "','"
                                                                                                                + appointment.getExtraInfo() + "','"
                                                                                                                + appointment.getPrevious() + "','"
                                                                                                                + appointment.getBookedBy() + "','"
                                                                                                                + appointment.getAttendance() + "')";
            
            stmt.executeUpdate(sql);                 
                    
            c.close();
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
            rs = stmt.executeQuery("SELECT * FROM staff, specificworking WHERE specificworking.Date = '" + stringDate + "' AND staff.ID = specificworking.ID AND specificworking.Place = 'Blood'"); 
                        
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




