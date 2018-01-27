/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author amydo
 */
public class codeBank 
{
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
}
