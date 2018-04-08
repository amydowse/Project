/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular;

import java.time.LocalDate;

/**
 *
 * @author amydo
 * 
 * Object representing a regular attender 
 * 
 */
public class regular implements Comparable<regular>
{
    String hospitalNumber;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    String number;
    String wristband;
    String extraInformation;
    int oncology;
    
    public regular(String hospitalNumber, String firstName, String lastName, LocalDate dateOfBirth, String number, String wristband, String extraInformation, int oncology)
    {
        this.hospitalNumber = hospitalNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.number = number;
        this.wristband = wristband;
        this.extraInformation = extraInformation;
        this.oncology = oncology; 
    }
    
    //GETTERS
    public String getHospitalNumber()
    {
        return hospitalNumber;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public String getName()
    {
        return firstName + " " + lastName;
    }
    
    public LocalDate getDOB()
    {
        return dateOfBirth;
    }
    
    public String getNumber()
    {
        return number;
    }
    
    public String getExtraInformation()
    {
        return extraInformation;
    }
    
    public String getWristband()
    {
        return wristband;
    }
    
    public int getOncology()
    {
        return oncology;
    }
    
    
    //SETTERS
    public void setHospitalNumber(String hospitalNumber)
    {
        this.hospitalNumber = hospitalNumber;
    }
    
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    public void setDOB(LocalDate dob)
    {
        this.dateOfBirth = dob;
    }
    
    public void setNumber(String number)
    {
        this.number = number;
    }
    
    public void setExtraInformation(String extraInformation)
    {
        this.extraInformation = extraInformation;
    }
    
    public void setWristband(String wristband)
    {
        this.wristband = wristband;
    }
    
    public void setOncology(int oncology)
    {
        this.oncology = oncology;
    }

    @Override
    public int compareTo(regular o) 
    {
        return getName().compareTo(o.getName());
    }
   
    
}
