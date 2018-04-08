/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staff;

/**
 *
 * @author amydo
 * 
 * Object representing a staff members skill - the name of the skill and if they have it or not 
 * 
 */
public class skill implements Comparable<skill>
{
    private String ProcedureName;
    private String hasSkill;
    
    public skill(String ProcedureName, String hasSkill)
    {
        this.ProcedureName = ProcedureName;
        this.hasSkill = hasSkill;
    }
    
    //GETTERS
    public String getProcedureName()
    {
        return ProcedureName;
    }
    
    public String getHasSkill()
    {
        return hasSkill;
    }
    
    
    //SETTERS
    public void setProceudreName(String ProcedureName)
    {
        this.ProcedureName = ProcedureName;
    }
    
    public void setHasSkill(String hasSkill)
    {
        this.hasSkill = hasSkill;
    }

    @Override
    public int compareTo(skill o) 
    {
        return getProcedureName().compareTo(o.getProcedureName());
    }
}
