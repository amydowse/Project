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
public class skill 
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
}
