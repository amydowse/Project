/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import diary.DiaryScreenDocumentController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author amydo
 * 
 * https://stackoverflow.com/questions/42569204/is-it-possible-to-reload-the-same-fxml-controller-instance
 */
public class codeBank 
{    
    private static LocalDate currentDate;
    
    public static void setCurrentDate(LocalDate newDate)
    {
        currentDate = newDate;
    }
    
    public static LocalDate getCurrentDate()
    {
        return currentDate;
    }
    
    public static String dateToString(LocalDate localDate)
    {
        DateTimeFormatter Stringformatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String stringDate = localDate.format(Stringformatter);
        return stringDate;
    }
    
    public static LocalDate stringToDate(String date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
    
    public static String[] newStringArray(int size)
    {
        String[] array = new String[size];
        for(int i=0; i<array.length; i++)
        {
            array[i] = "";
        }
        return array;
    }
    
    
        //Showing the correct symbol in the notes column 
    public static void showNotes(TextField txt, int notes)
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
    public static void attendanceColour(TextField txt, int attendance)
    {
        if(attendance == 0)
        {
            txt.setStyle(" -fx-control-inner-background: #FFFFFF"); //white
        }
        else if (attendance == 1)
        {
            txt.setStyle(" -fx-control-inner-background: #00FF31"); //green
        }
        else
        {
            txt.setStyle(" -fx-control-inner-background: #FF0000"); //red
        }
    }
    
    public static void extraInfo(TextField txt, String info)
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
    
    
    
    
    public static ObservableList<String> fillStaffDropDowns() 
    {
        ObservableList<String> workingStaff = FXCollections.observableArrayList();
        workingStaff.add("");
        try 
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);
            ResultSet rs;

            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();
            String stringDate = codeBank.dateToString(codeBank.getCurrentDate());

            //implement query
            rs = stmt.executeQuery("SELECT * FROM staff, working WHERE working.Date = '" + stringDate + "' AND staff.ID = working.Staff_ID");

            while (rs.next()) 
            {
                String firstname = rs.getString("FirstName");
                int ID = rs.getInt("ID");

                String text = "(" + ID + ") " + firstname;

                workingStaff.add(text);
            }
            

        } catch (SQLException e) 
        {

        }
        return workingStaff;
    }
    
    
    
    public static boolean checkDate(String input)
    {
        return true;
    }
    
    public static boolean checkInteger(String input)
    {
        try 
        { 
            if(input.equals(""))
            {
                return true;
            }
            Integer.parseInt(input);
            return true;
        } 
        catch(NumberFormatException e)  
        { 
            return false; 
        } 
    }
    
    public static void dataError()
    {
        System.out.println("Alert");
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Error in Data");
        alert.setHeaderText("An age entered is not a number");
        alert.setContentText("Please check data");
        alert.showAndWait();
    }
    
    
    
}

