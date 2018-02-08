/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preop;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author amydo
 */
public class preop 
{
    private LocalDate date;
    private LocalTime time;
    private String name;
    private int age;
    private String hospitalNumber;
    private String speciality;
    private String extraInfo;
    private int notes;
    private int attendance;
    
    public preop(LocalDate date, LocalTime time, String name, int age, String hospitalNumber, String speciality, String extraInfo, int notes, int attendance)
    {
        this.date = date;
        this.time = time;
        this.name = name;
        this.age = age;
        this.hospitalNumber = hospitalNumber;
        this.speciality = speciality;
        this.extraInfo = extraInfo;
        this.notes = notes;
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
    
    public int getAge()
    {
        return age;
    }
    
    public String getHospitalNumber()
    {
        return hospitalNumber;
    }
    
    public String getSpeciality()
    {
        return speciality;
    }
    
    public String getExtraInfo()
    {
        return extraInfo;
    }
    
    public int getNotes()
    {
        return notes;
    }
    
    public int getAttendance()
    {
        return attendance;
    }
}
