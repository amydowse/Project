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
public class searchProcedure 
{
    private LocalDate date;
    private LocalTime time;
    private String procedure;
    
    public searchProcedure(LocalDate date, LocalTime time, String procedure)
    {
        this.date = date;
        this.time = time;
        this.procedure = procedure;
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
    
    public void setProcedure(String procedure)
    {
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
    
    public String getProcedure()
    {
        return procedure; 
    }
}
