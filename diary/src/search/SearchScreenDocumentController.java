/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author amydo
 */
public class SearchScreenDocumentController implements Initializable
{
    @FXML RadioButton rbFuture = new RadioButton();
    @FXML RadioButton rbPast = new RadioButton();
    @FXML RadioButton rbBoth = new RadioButton();
    
    @FXML TextField txtSearchName = new TextField();
    @FXML Button btnSearch = new Button();
    @FXML Label lblSearchCriteria = new Label();
    
    @FXML TableView tblSearchResult = new TableView();
    @FXML TableColumn tblColDate = new TableColumn();
    @FXML TableColumn tblColTime = new TableColumn();
    @FXML TableColumn tblColName = new TableColumn();
    @FXML TableColumn tblColAge = new TableColumn();
    @FXML TableColumn tblColProcedure = new TableColumn();
    
    ObservableList searchResult = FXCollections.observableArrayList();
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ToggleGroup group = new ToggleGroup();
        rbFuture.setToggleGroup(group);
        rbPast.setToggleGroup(group);
        rbBoth.setToggleGroup(group);
        
        rbFuture.setSelected(true);
                
        tblSearchResult.setPlaceholder(new Label("There are not results for your search criteria"));
    }
    
    
    public void search() 
    { 
        searchResult.clear();
        tblSearchResult.getItems().clear();
        
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();
            
            //Search Diary
            //diary - Date, Time, Name, Age, Speciality
            rs = stmt.executeQuery("SELECT * FROM diary WHERE Name LIKE '%" + txtSearchName.getText() + "%'"); 
            while(rs.next())
            {  
                String Date = rs.getString("Date");
                LocalDate date = codeBank.stringToDate(Date);
                
                String Time = rs.getString("Time");
                LocalTime time = LocalTime.parse(Time);
                
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                String reason = rs.getString("Speciality");
                
                searchPatient x = new searchPatient(date, time, name, age, reason);
                
                if(rbFuture.isSelected())
                {
                    if(date.isAfter(LocalDate.now()))
                    {
                        searchResult.add(x);
                    }
                }
                else if (rbPast.isSelected())
                {
                    if(date.isBefore(LocalDate.now()))
                    {
                        searchResult.add(x);
                    }
                }
                else
                {
                    searchResult.add(x);
                }
                       
            }
            
            //Search blood
            //blood - Date, Time, Name, DateOfBirth
            rs = stmt.executeQuery("SELECT * FROM blood WHERE Name LIKE '%" + txtSearchName.getText() + "%'"); 
            while(rs.next())
            {  
                String Date = rs.getString("Date");
                LocalDate date = codeBank.stringToDate(Date);
                
                String Time = rs.getString("Time");
                LocalTime time = LocalTime.parse(Time);
                
                String name = rs.getString("Name");
                
                String dob = rs.getString("DateOfBirth");
                LocalDate dobDate = codeBank.stringToDate(dob);
                Period period = Period.between(dobDate, LocalDate.now());
                int age = period.getYears(); 
                
                searchPatient x = new searchPatient(date, time, name, age, "Blood");
                
                if(rbFuture.isSelected())
                {
                    if(date.isAfter(LocalDate.now()))
                    {
                        searchResult.add(x);
                    }
                }
                else if (rbPast.isSelected())
                {
                    if(date.isBefore(LocalDate.now()))
                    {
                        searchResult.add(x);
                    }
                }
                else
                {
                    searchResult.add(x);
                }
                       
            }
            
                     
            //Search nonbed
            //nonbed - Date, Time, Name, Age, Procedure
            rs = stmt.executeQuery("SELECT * FROM nonbed WHERE Name LIKE '%" + txtSearchName.getText() + "%'"); 
            while(rs.next())
            {  
                String Date = rs.getString("Date");
                LocalDate date = codeBank.stringToDate(Date);
                
                String Time = rs.getString("Time");
                LocalTime time = LocalTime.parse(Time);
                
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                String reason = rs.getString("Procedure");
                
                searchPatient x = new searchPatient(date, time, name, age, reason);
                
                if(rbFuture.isSelected())
                {
                    if(date.isAfter(LocalDate.now()))
                    {
                        searchResult.add(x);
                    }
                }
                else if (rbPast.isSelected())
                {
                    if(date.isBefore(LocalDate.now()))
                    {
                        searchResult.add(x);
                    }
                }
                else
                {
                    searchResult.add(x);
                }
                       
            }
            
            //Search preop
            //preop - Date, Time, Name, Age
            rs = stmt.executeQuery("SELECT * FROM blood WHERE Name LIKE '%" + txtSearchName.getText() + "%'"); 
            while(rs.next())
            {  
                String Date = rs.getString("Date");
                LocalDate date = codeBank.stringToDate(Date);
                
                String Time = rs.getString("Time");
                LocalTime time = LocalTime.parse(Time);
                
                String name = rs.getString("Name");
                
                String dob = rs.getString("DateOfBirth");
                LocalDate dobDate = codeBank.stringToDate(dob);
                Period period = Period.between(dobDate, LocalDate.now());
                int age = period.getYears(); 
                
                searchPatient x = new searchPatient(date, time, name, age, "Pre op");
                
                if(rbFuture.isSelected())
                {
                    if(date.isAfter(LocalDate.now()))
                    {
                        searchResult.add(x);
                    }
                }
                else if (rbPast.isSelected())
                {
                    if(date.isBefore(LocalDate.now()))
                    {
                        searchResult.add(x);
                    }
                }
                else
                {
                    searchResult.add(x);
                }
                      
            }
            
            
            //search oncology
            rs = stmt.executeQuery("SELECT * FROM regular, oncology WHERE (regular.FirstName LIKE '%" + txtSearchName.getText() + "%' OR regular.LastName LIKE '%" + txtSearchName.getText() + "%') AND oncology.Regular_HospitalNumber = regular.HospitalNumber");
            
            while(rs.next())
            {  
                System.out.println("RESULT???????????");
                String Date = rs.getString("Date");
                LocalDate date = codeBank.stringToDate(Date);
                
                String Time = rs.getString("Time");
                LocalTime time = LocalTime.parse(Time);
                
                String Firstname = rs.getString("FirstName");
                String Lastname = rs.getString("LastName");
                String name = Firstname + " " + Lastname;
                
                String dob = rs.getString("DateOfBirth");
                LocalDate dobDate = codeBank.stringToDate(dob);
                Period period = Period.between(dobDate, LocalDate.now());
                int age = period.getYears(); 
                
                String reason = rs.getString("Reason");
                
                searchPatient x = new searchPatient(date, time, name, age , "Oncology - " + reason);
                
                if(rbFuture.isSelected())
                {
                    if(date.isAfter(LocalDate.now()))
                    {
                        System.out.println("ADDING F");
                        searchResult.add(x);
                            
                    }
                }
                else if (rbPast.isSelected())
                {
                    if(date.isBefore(LocalDate.now()))
                    {
                        System.out.println("ADDING P");
                        searchResult.add(x);
                    }
                }
                else
                {
                    System.out.println("ADDING B");
                    searchResult.add(x);
                }
                      
            }
            
            
            
            
        }
        catch (SQLException e)
        {
            
        }
        display();
    }
    
    
    public void display()
    {
        tblSearchResult.getItems().addAll(searchResult);
        tblColDate.setCellValueFactory(new PropertyValueFactory("Date"));
        tblColTime.setCellValueFactory(new PropertyValueFactory("Time"));
        tblColName.setCellValueFactory(new PropertyValueFactory("Name"));
        tblColAge.setCellValueFactory(new PropertyValueFactory("Age"));
        tblColProcedure.setCellValueFactory(new PropertyValueFactory("Procedure"));

    }
    
    
    
}//END OF CLASS
