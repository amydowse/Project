/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedule;

import common.codeBank;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author amydo
 * 
 * Object that represents a possible appointment date and time 
 * 
 */
public class result implements Comparable<result>
{
    LocalDate date;
    LocalTime time;
    
    public result(LocalDate date, LocalTime time)
    {
        this.date = date;
        this.time = time;
    }
    
    //GETTERS
    public LocalDate getDate()
    {
        return date;
    }
    
    public LocalTime getTime()
    {
        return time;
    }
    
    public String getDateString()
    {
        return codeBank.dateToString(date);
    }
    
    //SETTERS
    public void setDate(LocalDate date)
    {
        this.date = date;
    }
    
    public void setTime(LocalTime time)
    {
        this.time = time;
    }

    @Override
    public int compareTo(result o) 
    {
        if(getDate().compareTo(o.getDate()) == 0)
        {
            return getTime().compareTo(o.getTime());
        }
        else
        {
            return getDate().compareTo(o.getDate());
        }
    }
}
