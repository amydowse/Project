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
public class nonbed implements Comparable<nonbed>
{
    int position;
    private LocalDate date;
    private LocalTime time;
    private String name;
    private int age;
    private String hospital;
    private String procedure;
    private String reason;
    private int notes;
    private int attendance;
    
    public nonbed(LocalDate date, LocalTime time, String name, int age, String hospital, String procedure, String reason, int notes, int attendance)
    {
        this.date = date;
        this.time = time;
        this.name = name;
        this.age = age;
        this.hospital = hospital;
        this.procedure = procedure;
        this.reason = reason;
        this.notes = notes;
        this.attendance = attendance;
    }
    
    
    //GETTERS
    public int getPosition()
    {
        return position;
    }
    
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
    
    public String getProcudure()
    {
        return procedure;
    }
    
    public String getReason()
    {
        return reason;
    }
   
    public int getNotes()
    {
        return notes;
    }
    
    public int getAttendance()
    {
        return attendance;
    }
    
    
    
    
    //SETTERS
    public void setPosition(int position)
    {
        this.position = position;
    }
    
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
    
    public void setProcedure(String procedure)
    {
        this.procedure = procedure;
    }
    
    public void setReason(String reason)
    {
        this.reason = reason;
    }
      
    public void setNotes(int notes)
    {
        this.notes = notes;
    }
    
    public void setAttendance(int attendance)
    {
        this.attendance = attendance;
    }

    @Override
    public int compareTo(nonbed o) 
    {
        return getTime().compareTo(o.getTime());
    }
}


        
        
        
        
        