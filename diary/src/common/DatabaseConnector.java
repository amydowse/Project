/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author amydo
 * tutorial from : https://www.tutorialspoint.com/jdbc/jdbc-db-connections.htm
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
            
           // System.out.println("Connection to SQLite has been established.");
           //Class.forName("org.sqlite.JDBC");
          // c = DriverManager.getConnection("jdbc:sqlite:diaryDatabase.db");
          
           return c ; 
        }    
        catch ( Exception e ) 
        {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           return null ;     
        }
    } 
}