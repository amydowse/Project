/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blood;

/**
 *
 * @author amydo
 */

import java.time.LocalDate;
import java.time.LocalTime;

public class basicBlood 
{
    LocalTime time;
    
    public basicBlood(LocalTime time)
    {
        this.time = time;
    }
    
    public LocalTime getTime()
    {
        return time;
    }
}
