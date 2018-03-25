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
public class specificWorking 
{
    int ID;
    String location;
    
    public specificWorking(int ID, String location)
    {
        this.ID = ID;
        this.location = location;
    }
    
    //GETTERS
    public int getID()
    {
        return ID;
    }
    
    public String getLocation()
    {
        return location;
    }
    
    //Setters
    public void setID(int ID)
    {
        this.ID = ID;
    }
    
    public void setLocation(String location)
    {
        this.location = location;
    }
}
