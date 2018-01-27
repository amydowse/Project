/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import common.DatabaseConnector;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author amydo
 */
public class SearchScreenDocumentController implements Initializable
{
    @FXML private RadioButton rdbPatient = new RadioButton();
    @FXML private RadioButton rdbProcedure = new RadioButton();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ToggleGroup group = new ToggleGroup();
        rdbPatient.setToggleGroup(group);
        rdbProcedure.setToggleGroup(group);
        
        Load();
    }
    
    
    public void Load() 
    {
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT * FROM procedures"); 
            while(rs.next())
            {  
                System.out.println(rs.getInt("ID"));
                System.out.println(rs.getString("Name"));
                System.out.println(rs.getInt("Duration"));
                System.out.println(rs.getInt("NumberOfNurses"));
                System.out.println(rs.getString("Location"));
            }
            
        }
        catch (SQLException e)
        {
            
        }
    }
    
    
}
