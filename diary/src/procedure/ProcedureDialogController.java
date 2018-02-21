/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procedure;

import bases.MainScreenDocumentController;
import bases.TopMenuDocumentController;
import common.DatabaseConnector;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author amydo
 */
public class ProcedureDialogController implements Initializable
{
    @FXML TextField txtName;
    @FXML TextField txtDuration;
    @FXML TextField txtNurses;
    @FXML ChoiceBox cbLocation;
    
    @FXML Button btnSave;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cbLocation.getItems().addAll("Bed", "Non-bed");
    }
    
    @FXML
    public void Save()
    {
        if (!txtName.getText().equals("") && !txtDuration.equals("") && !txtNurses.getText().equals("")) 
        {
            try 
            {
                Connection c = DatabaseConnector.activateConnection();
                c.setAutoCommit(true);
                Statement stmt = c.createStatement();

                String sql = "INSERT INTO procedures(Name, Duration, NumberOfNurses, Location) VALUES('" 
                                                                + txtName.getText() + "','"
                                                                + txtDuration.getText() + "','"
                                                                + txtNurses.getText() + "','"
                                                                + cbLocation.getValue().toString() + "')";

                stmt.executeUpdate(sql);
                c.close();
                
            } 
            catch (SQLException e) 
            {
                    
            }
            TopMenuDocumentController.PrSDC.showInformation();
            //https://stackoverflow.com/questions/13567019/close-fxml-window-by-code-javafx accessed 21/2
            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
            
        }
    }
   
    
    public void shutdown() 
    {
                
    }
}
