/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staff;

/**
 *
 * @author amydo
 */
public class staff 
{
    private int ID;
    private String firstName;
    private String lastName;
    private String position;
    private String agency;
    private String number;
    private String extraInfo;
    
    public staff(int ID, String firstName, String lastName, String position, String agency, String number, String extraInfo)
    {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.agency = agency;
        this.number = number;
        this.extraInfo = extraInfo;
    }
    
    //GETTERS
    public String getShow()
    {
        return "(" + ID + ") " + firstName + " " + lastName;
    }
    public int getID()
    {
        return ID;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public String getPosition()
    {
        return position;
    }
    
    public String getAgency()
    {
        return agency;
    }
    
    public String getNumber()
    {
        return number;
    }
    
    public String getExtraInfo()
    {
        return extraInfo;
    }
    
    
    //SETTERS
    public void setID(int ID)
    {
        this.ID = ID;
    }
    
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    public void setPosition(String position)
    {
        this.position = position;
    }
    
    public void setAgency(String agency)
    {
        this.agency = agency;
    }
    
    public void getNumber(String number)
    {
        this.number = number;
    }
    
    public void getExtraInfo(String extraInfo)
    {
        this.extraInfo = extraInfo;
    }
}
