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

import diary.DatabaseConnector;
import java.sql.*;               // imports Java SQL package 

public class hold 
{
      
    public void testing() throws Exception
    {
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;

            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Vehicle"); 
            while(rs.next())
            {  }
            
            Integer warrantyInt = rs.getInt("hasWarranty");
                String reg = rs.getString("registration");
        }
        catch (SQLException e)
        {
            
        }
    }

}
