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
public class multiple 
{
    String name;
    int count;
    int maximum;
    
    public multiple(String name, int count, int maximum)
    {
        this.name = name;
        this.count = count;
        this.maximum = maximum;
    }
    
    //GETTERS
    public String getName()
    {
        return name;
    }
    
    public int getCount()
    {
        return count;
    }
    
    public int getMaximum()
    {
        return maximum;
    }
    
    //SETTERS
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setCount(int count)
    {
        this.count = count;
    }
    
    public void setMaximum(int maximum)
    {
        this.maximum = maximum;
    }
}
