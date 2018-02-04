/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import diary.DiaryScreenDocumentController;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author amydo
 * 
 * https://stackoverflow.com/questions/42569204/is-it-possible-to-reload-the-same-fxml-controller-instance
 */
public class codeBank 
{    
    private static LocalDate currentDate = LocalDate.now();
    
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
    
    public static String[] newStringArray()
    {
        String[] array = new String[24];
        for(int i=0; i<array.length; i++)
        {
            array[i] = "";
        }
        return array;
    }
    
}

