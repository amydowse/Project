/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nonbed;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author amydo
 */
public class nonbed 
{
    private LocalDate date;
    private LocalTime time;
    private String name;
    private int age;
    private String hospital;
    private String reason;
    private int duration;
    private String notes;
    private int attendance;
    
    public nonbed(LocalDate date, LocalTime time, String name, int age, String hospital, String reason, int duration, String notes, int attendance)
    {
        this.date = date;
        this.time = time;
        this.name = name;
        this.age = age;
        this.hospital = hospital;
        this.reason = reason;
        this.duration = duration;
        this.notes = notes;
        this.attendance = attendance;
    }
    
    
    //GETTERS
    public LocalTime getTime()
    {
        return time;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public String getHospital()
    {
        return hospital;
    }
    
    public String getReason()
    {
        return reason;
    }
    
    public int getDuration()
    {
        return duration;
    }
    
    public String getNotes()
    {
        return notes;
    }
    
    public int getAttendance()
    {
        return attendance;
    }
    
    
    
    
     //SETTERS
    public void setTime(LocalTime time)
    {
        this.time = time;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setAge(int age)
    {
        this.age = age;
    }
    
    public void setHospital(String hospital)
    {
        this.hospital = hospital;
    }
    
    public void setReason(String reason)
    {
        this.reason = reason;
    }
    
    public void setDuration(int duration)
    {
        this.duration = duration;
    }
    
    public void setNotes(String notes)
    {
        this.notes = notes;
    }
    
    public void setAttendance(int attendance)
    {
        this.attendance = attendance;
    }
}


        
        
        
        
        