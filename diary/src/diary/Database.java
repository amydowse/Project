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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database
{
    private static final Database INSTANCE = new Database();
    private Connection connection = null;

    private Database() 
    {
       	try 
        {
            connection = DriverManager.getConnection("jdbc:sqlite:diaryDatabase.db");
	}
	catch (SQLException ex) 
        {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
	}
    }
	
    public static Database getInstance() 
    {
	return INSTANCE;
    }
	
    public Connection getConnection()
    {
	return this.connection;
    }
}
