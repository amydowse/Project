/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procedure;

import bases.MainScreenDocumentController;
import bases.TopMenuDocumentController;
import common.DatabaseConnector;
import common.codeBank;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
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
    @FXML TextField txtPatients;
    @FXML ChoiceBox cbLocation;
    @FXML ChoiceBox cbLength;
    @FXML Button btnSave;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cbLocation.getItems().addAll("Bed", "Non-bed");
        cbLength.getItems().addAll("", "minutes", "hour(s)");
        
        txtDuration.focusedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldV, Boolean newV) -> {
            if (!newV) 
            { 
                checkingInteger(txtDuration);
            }
            });
        
        txtNurses.focusedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldV, Boolean newV) -> {
            if (!newV) 
            { 
                checkingInteger(txtNurses);
            }
            });
        
        txtPatients.focusedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldV, Boolean newV) -> {
            if (!newV) 
            { 
                checkingInteger(txtPatients);
            }
            });
    }
    
    public void checkingInteger(TextField selected)
    {
        String value = selected.getText();
        
        if(!codeBank.checkInteger(value))
        {
            codeBank.integerError();
        }
    }
    
    @FXML    
    public void Save() 
    {
        //checks that all of the info has been entered
        if (!txtName.getText().equals("") && !txtDuration.equals("") && !txtNurses.getText().equals("") && !txtPatients.getText().equals("") && !cbLocation.getValue().equals("")) 
        {
            if(cbLocation.getValue().equals("Non-bed"))
            {
                if(txtNurses.getText().equals("1") && txtPatients.getText().equals("1") )
                {
                    saveProcess();
                }
                else
                {
                    codeBank.nonBedError();
                }
            }
            else
            {
                if(txtNurses.getText().equals("1") || txtPatients.getText().equals("1") )
                {
                    saveProcess();
                }
                else
                {
                    codeBank.bedError();
                }
            }
            
        }
        else if (!txtName.getText().equals("") || !txtDuration.equals("") || !txtNurses.getText().equals("") || !txtPatients.getText().equals("") || !cbLocation.getValue().equals(""))
        {
            System.out.println("Procedure");
            codeBank.missingError();
        }
                
    }
    
    //the process of saving once you have done the checks 
    public void saveProcess() 
    {
        int duration = Integer.parseInt(txtDuration.getText());
        
        if (cbLength.getValue().equals("hour(s)")) 
        {
            duration = duration * 60;
        }

        try 
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);
            Statement stmt = c.createStatement();

            String sql = "UPDATE procedures SET     Duration = '" + duration
                    + "', NumberOfNurses = '" + txtNurses.getText()
                    + "', NumberOfPatients = '" + txtPatients.getText()
                    + "', Location = '" + cbLocation.getValue().toString() + "' WHERE  Name = '" + txtName.getText() + "'";

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
    
    
    
    
    
    
    
    
    
    
    
    
    public void shutdown() 
    {
                
    }
}
