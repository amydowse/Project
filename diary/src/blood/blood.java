/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blood;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author amydo
 */
public class blood 
{
    private LocalDate date;
    private LocalTime time;
    private String name;
    private String DateOfBirth;
    private String NHSNumber;
    private String number;
    private String form;
    private String extraInfo;
    private String previous;
    private String bookedBy;
    private int attendance;
    
    public blood(LocalDate date, LocalTime time, String name, String DOB, String NHSNumber, String number, String form, String extraInfo, String previous, String bookedBy, int attendance)
    {
        this.time = time;
        this.date = date;
        this.name = name;
        this.DateOfBirth = DOB;
        this.NHSNumber = NHSNumber;
        this.number = number;
        this.form = form;
        this.extraInfo = extraInfo;
        this.previous = previous;
        this.bookedBy = bookedBy;
        this.attendance = attendance;
     
    }    
        
         //GETTER METHODS
    public LocalDate getDate()
    {
        return date;
    }
    
    public LocalTime getTime()
    {
        return time;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getDateOfBirth()
    {
        return DateOfBirth;
    }
    
    public String getNHSNumber()
    {
        return NHSNumber;
    }
    
    public String getNumber()
    {
        return number;
    }
    
    public String getForm()
    {
        return form;
    }
    
    public String getExtraInfo()
    {
        return extraInfo;
    }

    public String getPrevious()
    {
        return previous;
    }
    
    public String getBookedBy()
    {
        return bookedBy;
    }
    
    public int getAttendance()
    {
        return attendance;
    }
    
    
    
    
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setDOB(String DOB)
    {
        this.DateOfBirth = DOB;
    }
   
    public void setNHS(String NHS)
    {
        this.NHSNumber = NHS;
    }   
    
    public void setNumber(String number)
    {
        this.number = number;
    }
    
    public void setForm(String form)
    {
        this.form = form;
    }

    public void setExtra(String extra)
    {
        this.extraInfo = extra;
    }  
    
    public void setPrevious(String previous)
    {
        this.previous = previous;
    }
    
    public void setBooked(String bookedBy)
    {
        this.bookedBy = bookedBy;
    }    
}

