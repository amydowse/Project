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
    
    public procedure(String name, int duration)
    {
        this.name = name;
        this.duration = duration;
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
    
    
    //SETTERS
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setDuration(int duration)
    {
        this.duration = duration;
    }
    
    
    
    
}
