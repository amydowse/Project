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
    private int previous;
    private String bookedBy;
    private int attendance;
    
    public blood(LocalDate date, LocalTime time, String name, String DOB, String NHSNumber, String number, String form, String extraInfo, int previous, String bookedBy, int attendance)
    {
        this.date = date;
        this.time = time;
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

    public int getPervious()
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
    
}
