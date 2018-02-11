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
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalTimeStringConverter;

/**
 *
 * @author amydo
 */
public class NonbedScreenDocumentController implements Initializable
{
    @FXML TableView tblClinic;
    @FXML TableColumn tblColName;
    @FXML TableColumn tblColTime;
    @FXML TableColumn tblColAge;
    @FXML TableColumn tblColHospital;
    @FXML TableColumn tblColNumber;    
    @FXML TableColumn tblColReason;
    @FXML TableColumn tblColDuration;
    @FXML TableColumn tblColNotes;
    
    @FXML ChoiceBox cbStaff = new ChoiceBox();
    
    ObservableList<String> workingStaff; 
    ObservableList<nonbed> allBookings = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        showInformation();
    }
    
    public void showInformation()
    {
        cbStaff.valueProperty().set(null);
        
        cbStaff.setItems(codeBank.fillStaffDropDowns());
        showStaff(codeBank.getCurrentDate());
                
        tblClinic.getItems().clear();
        
        if(allBookings != null)
        {
            allBookings.clear();
        }
        
        
        tblClinic.setEditable(true);
        tblColTime.setEditable(true);
        tblColName.setEditable(true);
        tblColAge.setEditable(true);        
        tblColHospital.setEditable(true);
        tblColReason.setEditable(true);
        tblColDuration.setEditable(true);
              
        try
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            Statement stmt = c.createStatement();    
            
            //make localdate into string
            String date = codeBank.dateToString(codeBank.getCurrentDate());
            
            //implement query
            rs = stmt.executeQuery("SELECT * FROM nonbed WHERE Date = '" + date + "'" );
                            
            while(rs.next())
            {
                String time = rs.getString("Time");
                LocalTime Time = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
                
                String Name = rs.getString("Name");
                int Age = rs.getInt("Age");
                String Hospital = rs.getString("HospitalNumber");
                String Reason = rs.getString("Reason");
                int Duration = rs.getInt("Duration");
                int Notes = rs.getInt("Notes");
                int Attendance = rs.getInt("Attendance");
                
                String notes;
                if(Notes==0)
                {
                    notes = "✔";
                }
                else if (Attendance == 1)
                {
                    notes = "O";
                }
                else
                {
                    notes = "-";
                }
                
                      
                nonbed x = new nonbed(codeBank.getCurrentDate(), Time, Name, Age, Hospital, Reason, Duration,  notes, Attendance); 
                allBookings.add(x);
                
            }
                allBookings.add(new nonbed(codeBank.getCurrentDate(), LocalTime.MIDNIGHT, "", -1, "", "", -1, "-", 0));
                tblClinic.getItems().addAll(allBookings);
                
                
  
                tblColTime.setCellValueFactory(new PropertyValueFactory<nonbed, LocalTime>("Time"));
                tblColTime.setCellFactory(TextFieldTableCell.forTableColumn(new LocalTimeStringConverter()));
                tblColTime.setOnEditCommit(
                        new EventHandler<TableColumn.CellEditEvent<nonbed, LocalTime>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<nonbed, LocalTime> t) {
                        ((nonbed) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setTime(t.getNewValue());          
                    }
                }
                );
                
                
            tblColTime.setCellFactory(new Callback<TableColumn<nonbed, LocalTime>, 
            TableCell<nonbed, LocalTime>>()
            {
                @Override
                public TableCell<nonbed, LocalTime> call(
                        TableColumn<nonbed, LocalTime> param)
                {
                    return new TableCell<nonbed, LocalTime>()
                    {
                        @Override
                        protected void updateItem(LocalTime item, boolean empty)
                        {
                            if (!empty)
                            {
                                int currentIndex = indexProperty().getValue();
                                nonbed type = param.getTableView().getItems().get(currentIndex);
                                
                                if(type.getAttendance() == 1)
                                {
                                    setText(type.getTime().toString());
                                    setStyle("-fx-background-color: green");
                                } 
                                else if(type.getAttendance() == 2)
                                {
                                    setText(type.getTime().toString());
                                    setStyle("-fx-background-color: red");
                                }
                                else if(type.getAttendance() == 0)
                                {
                                    setText(type.getTime().toString());
                                    setStyle("-fx-background-color: white");
                                }
                            }
                        }
                    };
                }
            });
            
            tblClinic.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
            {
                if(((nonbed)newSelection).getNotes() == "✔")
                {
                    ((nonbed)newSelection).setNotes("O");
                }
                else if (((nonbed)newSelection).getNotes() == "O")
                {
                    ((nonbed)newSelection).setNotes("-");
                }
                else if(((nonbed)newSelection).getNotes() == "-")
                {
                    ((nonbed)newSelection).setNotes("✔");
                }
                
                
            });
            
            tblClinic.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
            {
                if(((nonbed)newSelection).getAttendance() == 1)
                {
                    ((nonbed)newSelection).setAttendance(2);
                }
                else if (((nonbed)newSelection).getAttendance() == 2)
                {
                    ((nonbed)newSelection).setAttendance(0);
                }
                else if(((nonbed)newSelection).getAttendance() == 0)
                {
                    ((nonbed)newSelection).setAttendance(1);
                }
                
                
            });
            
                
                tblColName.setCellValueFactory(new PropertyValueFactory<nonbed, String>("Name"));
                tblColName.setCellFactory(TextFieldTableCell.forTableColumn());
                tblColName.setOnEditCommit(
                        new EventHandler<TableColumn.CellEditEvent<nonbed, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<nonbed, String> t) {
                        ((nonbed) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setName(t.getNewValue());
                    }
                }
                );
                
                
                //https://stackoverflow.com/questions/32353843/editing-numbers-in-javafx-tableview-cells accessed 11/02/18
                tblColAge.setCellValueFactory(new PropertyValueFactory<nonbed, Integer>("Age"));
                tblColAge.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                tblColAge.setOnEditCommit(
                        new EventHandler<TableColumn.CellEditEvent<nonbed, Integer>>() 
                        {
                            @Override
                            public void handle(TableColumn.CellEditEvent<nonbed, Integer> t) 
                            {
                                ((nonbed) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAge(t.getNewValue());
                            }
                        }
                );
                
                tblColHospital.setCellValueFactory(new PropertyValueFactory<nonbed, String>("Hospital"));
                tblColHospital.setCellFactory(TextFieldTableCell.forTableColumn());
                tblColHospital.setOnEditCommit(
                        new EventHandler<TableColumn.CellEditEvent<nonbed, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<nonbed, String> t) {
                        ((nonbed) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setHospital(t.getNewValue());
                    }
                }
                );
                
                tblColReason.setCellValueFactory(new PropertyValueFactory<nonbed, String>("Reason"));
                tblColReason.setCellFactory(TextFieldTableCell.forTableColumn());
                tblColReason.setOnEditCommit(
                        new EventHandler<TableColumn.CellEditEvent<nonbed, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<nonbed, String> t) {
                        ((nonbed) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setReason(t.getNewValue());
                    }
                }
                );
                
                tblColDuration.setCellValueFactory(new PropertyValueFactory<nonbed, Integer>("Duration"));
                tblColDuration.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                tblColDuration.setOnEditCommit(
                        new EventHandler<TableColumn.CellEditEvent<nonbed, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<nonbed, Integer> t) {
                        ((nonbed) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setDuration(t.getNewValue());
                    }
                }
                );
                
                tblColNotes.setCellValueFactory(new PropertyValueFactory<nonbed, Integer>("Notes"));
//                tblColNotes.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//                tblColNotes.setOnEditCommit(
//                        new EventHandler<TableColumn.CellEditEvent<nonbed, Integer>>() {
//                    @Override
//                    public void handle(TableColumn.CellEditEvent<nonbed, Integer> t) {
//                        ((nonbed) t.getTableView().getItems().get(
//                                t.getTablePosition().getRow())).setNotes(t.getNewValue());
//                    }
//                }
//                );
                
                
                
                
            
        }
        catch(SQLException e)
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
            rs = stmt.executeQuery("SELECT * FROM staff, specificworking WHERE specificworking.Date = '" + stringDate + "' AND staff.ID = specificworking.ID AND specificworking.Place = 'Nonbed'"); 
                        
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
