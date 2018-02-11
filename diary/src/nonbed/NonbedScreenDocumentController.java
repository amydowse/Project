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
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
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
    @FXML TableColumn tblColAtt1;
    @FXML TableColumn tblColAtt2;
    
    @FXML Button btnSave = new Button();
    
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
                    notes = "-";
                }
                else if (Notes == 1)
                {
                    notes = "O";
                }
                else
                {
                    notes = "✔";
                }
                
                      
                nonbed x = new nonbed(codeBank.getCurrentDate(), Time, Name, Age, Hospital, Reason, Duration,  notes, Attendance); 
                allBookings.add(x);
                
            }
                allBookings.add(new nonbed(codeBank.getCurrentDate(), LocalTime.MIDNIGHT, "", -1, "", "", -1, "-", 0));
                tblClinic.getItems().addAll(allBookings);
                
                
  
                
  //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>              
                
            //https://stackoverflow.com/questions/27281370/javafx-tableview-format-one-cell-based-on-the-value-of-another-in-the-row accessed 10/2/18
            tblColAtt1.setCellValueFactory(new PropertyValueFactory<nonbed, Integer>("Att"));
            tblColAtt1.setCellFactory(tc -> {
                TableCell<nonbed, Integer> cell = new TableCell<nonbed, Integer>()  {
                    @Override
                    protected void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        //setText(empty ? null : item.toString());
                    }
                };
                cell.setOnMouseClicked(e -> {
                    if (!cell.isEmpty()) {
                        Integer userId = cell.getItem();
                        int row = cell.getIndex();
                        changeAttendance(row);
                        //System.out.println(row + " --- " + userId);
                    }

                });
                return cell;
            });
            
            tblColAtt2.setCellFactory(new Callback<TableColumn<nonbed, LocalTime>, TableCell<nonbed, LocalTime>>() {
                @Override
                public TableCell<nonbed, LocalTime> call(TableColumn<nonbed, LocalTime> param) {
                    return new TableCell<nonbed, LocalTime>() {
                        @Override
                        protected void updateItem(LocalTime item, boolean empty) {
                            if (!empty) {
                                int currentIndex = indexProperty().getValue();
                                nonbed type = param.getTableView().getItems().get(currentIndex);

                                if (type.getAttendance() == 1) 
                                {
                                    setStyle("-fx-background-color: green");
                                } 
                                else if (type.getAttendance() == 2) 
                                {
                                    setStyle("-fx-background-color: red");
                                } 
                                else if (type.getAttendance() == 0) 
                                {
                                    setStyle("-fx-background-color: white");
                                }
                            }
                        }
                    };
                }
            });
            
            
            
            
            //https://stackoverflow.com/questions/35562037/how-to-set-click-event-for-a-cell-of-a-table-column-in-a-tableview accessed 11/2/1
            tblColTime.setCellValueFactory(new PropertyValueFactory<nonbed, String>("Time"));
            tblColTime.setCellFactory(TextFieldTableCell.forTableColumn(new LocalTimeStringConverter()));
            tblColTime.setOnEditCommit(
                        new EventHandler<TableColumn.CellEditEvent<nonbed, Integer>>() 
                        {
                            @Override
                            public void handle(TableColumn.CellEditEvent<nonbed, Integer> t) 
                            {
                                ((nonbed) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAge(t.getNewValue());
                            }
                        }
                );
            

            
            
            
            
            //----------------GOOD----------------------------------------------------------------------------
            
            
            tblColNotes.setCellValueFactory(new PropertyValueFactory<nonbed, String>("Notes"));
            tblColNotes.setCellFactory(tc -> {
                TableCell<nonbed, String> cell = new TableCell<nonbed, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : item.toString());
                    }
                };
                cell.setOnMouseClicked(e -> {
                    if (!cell.isEmpty()) {
                        String userId = cell.getItem();
                        int row = cell.getIndex();
                        changeNotes(row, userId);
                        //System.out.println(row + " --- " + userId);
                    }

                });
                return cell;
            });
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------     
                
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
                
                
   
        }
        catch(SQLException e)
        {
        }
    }
    
    
    
    public void changeNotes(int index, String att)
    {
        if(att.equals("✔"))
        {
            allBookings.get(index).setNotes("-");
        }
        else if (att.equals("-"))
        {
            allBookings.get(index).setNotes("O");
        }
        else
        {
            allBookings.get(index).setNotes("✔");
        }
        save();
        showInformation();
        
    }
    
    
    
    
    public void changeAttendance(int index)
    {
        if(allBookings.get(index).getAttendance() == 0)
        {
            allBookings.get(index).setAttendance(1);
        }
        else if(allBookings.get(index).getAttendance() == 1)
        {
            allBookings.get(index).setAttendance(2);
        }
        else
        {
            allBookings.get(index).setAttendance(0);
        }
            
        save();
        showInformation();
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


@FXML
public void save()
    {
        for (nonbed appointment : allBookings)
        {
            System.out.println("SAVING A NEW NON BED THING");
            if(!appointment.getName().equals(""))
            {
                saveToDatabase(appointment);
            }
            else
            {
                deleteFromDatabase(appointment);
            }
        }
    }
    
    public void saveToDatabase(nonbed appointment)
    {
        try
        {
            int notes;
            if (appointment.getNotes() == "✔") 
            {
                notes = 2;
            } 
            else if (appointment.getNotes() == "O") 
            {
                notes = 1;
            }
            else 
            {
                notes = 0;
            }

            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();
            
            String date = codeBank.dateToString(codeBank.getCurrentDate());
                        
            String sql = "REPLACE INTO nonbed (Date, Time, Name, Age, HospitalNumber, Reason, Duration, Notes, Attendance) VALUES('"
                                                                                                                + date + "','"
                                                                                                                + appointment.getTime() + "','"
                                                                                                                + appointment.getName() + "','"
                                                                                                                + appointment.getAge() + "','"
                                                                                                                + appointment.getHospital() + "','"
                                                                                                                + appointment.getReason() + "','"
                                                                                                                + appointment.getDuration() + "','"
                                                                                                                + notes + "','"
                                                                                                                + appointment.getAttendance() + "')";
            
            stmt.executeUpdate(sql);                 
                    
            c.close();
        }
        catch (SQLException e)
        {
            
        } 
        
                
    }
        
    
    public void deleteFromDatabase(nonbed appointment)
    {
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();
            
            String date = codeBank.dateToString(codeBank.getCurrentDate());
         
            String sql = "DELETE FROM nonbed WHERE Date = '" + date + "' AND Time = '" + appointment.getTime() + "'";
            
            stmt.executeUpdate(sql);                 
                    
            c.close();
        }
        catch (SQLException e)
        {
            
        } 
        
                
    }
    
    
    
    
    
    
    
}//END OF CLASS
