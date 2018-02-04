/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

/**
 *
 * @author amydo
 */
public class workingStaff 
{
    private String firstName;
    private String lastName;
    private int ID;
    private String shift;
    
    public workingStaff(String firstName, String lastName, int ID, String shift)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
        this.shift = shift;
    }
    
    public String getWorkName()
    {
        return firstName + " " + lastName.substring(0,1);
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public int getID()
    {
        return ID;
    }
    
    public String getShift()
    {
        return shift;
    }
}
