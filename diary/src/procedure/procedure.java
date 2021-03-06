/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procedure;

/**
 *
 * @author amydo
 * 
 * Object that represents a procedure object 
 * 
 */
public class procedure implements Comparable<procedure>
{
    private String name;
    private int duration;
    private int nurses;
    private int patients;
    private String location;
    
    public procedure(String name, int duration, int nurses, int patients, String location)
    {
        this.name = name;
        this.duration = duration;
        this.nurses = nurses;
        this.patients = patients;
        this.location = location;
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
    
    public int getPatients()
    {
        return patients;
    }
    
    public String getLocation()
    {
        return location;
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
    
    public void setPatients(int patients)
    {
        this.patients = patients;
    }
    
    public void setLocation(String location)
    {
        this.location = location;
    }

    //Allows to put procedures objects into alphabetical order 
    @Override
    public int compareTo(procedure o) 
    {
       return getName().compareTo(o.getName());
    }
    
    
}
