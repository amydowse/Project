/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedule;

import java.time.LocalTime;

/**
 *
 * @author amydo
 * 
 * Object that represents appointments that have already been made 
 * Different appointment types make use of different attributes 
 * 
 */
public class schedule 
{
    LocalTime time;
    int duration;
    String bedNumber;
    String name;
    int nurse;
    int patient;
    
    public schedule(LocalTime time, int duration)
    {
        this.time = time;
        this.duration = duration;
    }
    
    //Getters
    public LocalTime getTime()
    {
        return time;
    }
    
    public int getDuration()
    {
        return duration;
    }
    
    public String getBedNumber()
    {
        return bedNumber;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getNurse()
    {
        return nurse;
    }
    
    public int getPatient()
    {
        return patient;
    }
    
    //Setters
    public void setTime(LocalTime time)
    {
        this.time = time;
    }
    
    public void setDuration(int duration)
    {
        this.duration = duration;
    }
    
    public void setBedNumber(String bedNumber)
    {
        this.bedNumber = bedNumber;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setNurse(int nurse)
    {
        this.nurse = nurse;
    }
    
    public void setPatient(int patient)
    {
        this.patient = patient;
    }
}
