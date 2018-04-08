/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oncology;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author amydo
 * 
 * Object that represents an oncology appointment 
 * 
 */
public class oncology implements Comparable<oncology>
{
    private int position;
    private LocalDate date;
    private LocalTime time;
    private String name;
    private String age;
    private String hospitalNumber;
    private String number;
    private String wristband;
    private String reason;
    private int notes;
    private int attendance;
    
    public oncology(LocalDate date, LocalTime time, String name, String age, String hospitalNumber, String number, String wristband, String reason, int notes, int attendance)
    {
        this.date = date;
        this.time = time;
        this.name = name;
        this.age = age;
        this.hospitalNumber = hospitalNumber;
        this.number = number;
        this.wristband = wristband;
        this.reason = reason;
        this.notes = notes;
        this.attendance = attendance;
    }
    
    //GETTERS
    public int getPosition()
    {
        return position;
    }
    
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
    
    public String getAge()
    {
        return age;
    }
    
    public String getHospitalNumber()
    {
        return hospitalNumber;
    }
    
    public String getNumber()
    {
        return number;
    }
    
    public String getWristband()
    {
        return wristband;
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
    
    public void setDate(LocalDate date)
    {
        this.date = date;
    }
    
    public void setTime(LocalTime time)
    {
        this.time = time;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setAge(String age)
    {
        this.age = age;
    }
    
    public void setHospitalNumber(String hospitalNumber)
    {
        this.hospitalNumber = hospitalNumber;
    }
    
    public void setNumber(String number)
    {
        this.number = number;
    }
    
    public void setWristband(String wristband)
    {
        this.wristband = wristband;
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
    public int compareTo(oncology o) 
    {
        return getTime().compareTo(o.getTime());
    }
    
}

