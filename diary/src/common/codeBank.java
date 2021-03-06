/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import diary.DiaryScreenDocumentController;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author amydo
 * 
 * This contains sections of code that is used a lot in the system so are written here once to save repetition 
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
    
    //chaning the + in the extra info of the diary to show when information is added 
    public static void extraInfo(TextField txt, String info)
    {        
        if(info.equals("") || info.equals(null))
        {
            txt.setText("");
        }
        else
        {
            txt.setText("+");
            txt.setStyle("-fx-font-weight: bold");
        }
    }
    
    
    
    //Fills the drop down menu with the staff working on that day 
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

                String text = "(" + ID + ") " + firstname + " - " + rs.getString("Shift");

                workingStaff.add(text);
            }
            

        } catch (SQLException e) 
        {

        }
        return workingStaff;
    }
    
    
    //Checks if an input is a valid date
    public static boolean checkDate(String input)
    {
        if(input.equals(""))
        {
            return true;
        }
        if(input.matches("\\d\\d/\\d\\d/\\d\\d\\d\\d"))
        {
            try 
            {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                df.setLenient(false);
                df.parse(input);
                System.out.println("DATE OK");
                return true;
            } 
            catch (ParseException e) 
            {
                return false;
            }
        }
        else
        {
            return false;
        }
   
    }
    
    //Checks that an input is a number
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
    
    //Checks that an input is either an integer or ??/12 or ??/52 for ages 
    public static boolean checkAge(String input)
    {
        try 
        { 
            if(input.equals(""))
            {
                return true;
            }
            else
            {
                int locationOfSlash = input.indexOf("/");
                if(input.substring(locationOfSlash+1, input.length()).equals("52"))
                {
                    return true;
                }
                if(input.substring(locationOfSlash+1, input.length()).equals("12"))
                {
                    return true;
                }
            }
            Integer.parseInt(input);
            return true;
        } 
        catch(NumberFormatException e)  
        { 
            return false; 
        } 
    }
    
    //Checks that an input is a valid time 
    public static boolean checkTime(String input)
    {
        try
        {
            if(input.equals(""))
            {
                return true;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime.parse(input, formatter);
            return true;
        }
        catch(DateTimeParseException e)
        {
              return false;
        }
    }
    
    
    
    //Error shown when an age is not in format expected
    public static void ageError()
    {
        System.out.println("Alert");
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Error in Data");
        alert.setHeaderText("An age entered is not a number");
        alert.setContentText("Please check data");
        alert.showAndWait();
    }
    
    //Error shown when an input is not a number when it should be 
    public static void integerError()
    {
        System.out.println("Alert");
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Error in Data");
        alert.setHeaderText("An value entered is not a number");
        alert.setContentText("Please check data");
        alert.showAndWait();
    }
    
    //Error shown when an input is not a valid date 
    public static void dateError()
    {
        System.out.println("Alert");
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Error in Date");
        alert.setHeaderText("A date is not entered in the correct format");
        alert.setContentText("Please put in the following format: dd/MM/YYYY");
        alert.showAndWait();
    }
    
    //Error shown when an input is not a valid time 
    public static void timeError()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Error in Time");
        alert.setHeaderText("A time is not entered in the correct format");
        alert.setContentText("Please a date in the 24-hour clocked: HH:mm");
        alert.showAndWait();
    }
    
    //Error shown when the order of times is not correct - used when setting up blood clinic 
    public static void timeOrderError()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Error in Times");
        alert.setHeaderText("The entered times do not form a clinic");
        alert.setContentText("Please check that:\nEnd is after Start\nBreak end is after break start\nThe break times are within the clinic times");
        alert.showAndWait();
    }
    
    //Error shown when there is missing data when booking an appointment 
    public static void missingError()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Missing data");
        alert.setHeaderText("Not all the required data has been entered");
        alert.setContentText("These enteries have not been saved");
        alert.showAndWait();
    }
    
    //Error shown when you are setting up a proceudre that is non-bed and it is not 1-1 relationship 
    public static void nonBedError()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Error");
        alert.setHeaderText("You can only have 1 nurse to 1 patient for non-bed procedures");
        alert.setContentText("Please try again");
        alert.showAndWait();
    }
    
    //Error shown when you are setting up a proceudre that is bed and it is not 1-x or x-1 relationship 
    public static void bedError()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Error");
        alert.setHeaderText("You can only have 1 nurse to many patients or many nurses to 1 patient");
        alert.setContentText("Please try again");
        alert.showAndWait();
    }
    
    
}

