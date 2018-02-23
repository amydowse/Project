/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author amydo
 */
public class searchPatient 
{
    private LocalDate date;
    private LocalTime time;
    private String name;
    private int age;
    private String procedure;
    
    public searchPatient(LocalDate date, LocalTime time, String name, int age, String procedure)
    {
        this.date = date;
        this.time = time;
        this.name = name;
        this.age = age;
        this.procedure = procedure;
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
    
    public String getName()
    {
        return name;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public String getProcedure()
    {
        return procedure;
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
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setAge(int age)
    {
        this.age = age;
    }
    
    public void setProcedure(String procedure)
    {
        this.procedure = procedure;
    }
}
