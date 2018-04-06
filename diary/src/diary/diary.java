/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author amydo
 */
public class diary 
{
    private LocalDate date;
    private String bedNumber;
    private LocalTime time;
    private String name;
    private String age;
    private String hospitalNumber;
    private String speciality;
    private String extraInfo;
    private int notes;
    private int attendance;
    
    public diary(LocalDate date, String bedNumber, LocalTime time, String name, String age, String hospitalNumber, String speciality, String extraInfo, int notes, int attendance)
    {
        this.date = date;
        this.bedNumber = bedNumber;
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
    
    public String getBedNumber()
    {
        return bedNumber;
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
