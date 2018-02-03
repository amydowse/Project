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
    
    public workingStaff(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public String getWorkName()
    {
        return firstName + " " + lastName.substring(0,1);
    }
}
