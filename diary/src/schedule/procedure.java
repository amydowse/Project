/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedule;

/**
 *
 * @author amydo
 */
public class procedure 
{
    String name;
    int duration;
    int nurses;
    
    public procedure(String name, int duration, int nurses)
    {
        this.name = name;
        this.duration = duration;
        this.nurses = nurses;
    }
    
    //GETTERS
    public String getName()
    {
        return name;
    }
    
    public int getDuration()
    {
        return duration;
    }
    
    public int getNurses()
    {
        return nurses;
    }
    
    
    //SETTERS
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setDuration(int duration)
    {
        this.duration = duration;
    }
    
    public void setNurses(int nurses)
    {
        this.nurses = nurses;
    }
    
    
    
    
}
