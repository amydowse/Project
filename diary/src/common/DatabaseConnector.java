/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author amydo
 * Tutorial from : https://www.tutorialspoint.com/jdbc/jdbc-db-connections.htm
 * 
 * Connection to the database
 */
 
import java.sql.* ;  // for standard JDBC programs


public class DatabaseConnector
{

    //Return a standard connection
    public static Connection activateConnection() // return connection
    {
        Connection c = null ;
        try 
        {
            String url = "jdbc:sqlite:diaryDatabase.db";
            // create a connection to the database
            c = DriverManager.getConnection(url);
           
           return c ; 
        }    
        catch ( Exception e ) 
        {
           return null ;     
        }
    } 
}