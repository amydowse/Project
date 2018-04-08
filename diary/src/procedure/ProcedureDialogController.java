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
 * 
 * Controller for the pop-up when you are adding a new procedure 
 * 
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
        
        //Adding listener to the duration to check its a number when you click off of it 
        txtDuration.focusedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldV, Boolean newV) -> {
            if (!newV) 
            { 
                checkingInteger(txtDuration);
            }
            });
        
        //Adding listener to the number of nurses to check its a number when you click off of it 
        txtNurses.focusedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldV, Boolean newV) -> {
            if (!newV) 
            { 
                checkingInteger(txtNurses);
            }
            });
        
        //Adding listener to the number of patients to check its a number when you click off of it 
        txtPatients.focusedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldV, Boolean newV) -> {
            if (!newV) 
            { 
                checkingInteger(txtPatients);
            }
            });
    }
    
    //Method to check that an input is a number 
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
            //Check the ratio of nurse to patinet 
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
                //check the ratio of nurse to patient 
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

            String sql = "INSERT INTO procedures (Name, Duration, NumberOfNurses, NumberOfPatients, Location) VALUES('" 
                                                                + txtName.getText() + "','"
                                                                + duration + "','"
                                                                + txtNurses.getText() + "','"
                                                                + txtPatients.getText() + "','"
                                                                + cbLocation.getValue().toString() + "')";
                    
            stmt.executeUpdate(sql);
            c.close();

        } 
        catch (SQLException e) 
        {
        }
        
        //Refreshing the proceudre screen so that when the pop-up closes, the new procedure appears in the list 
        TopMenuDocumentController.PrSDC.showInformation();
        
        //https://stackoverflow.com/questions/13567019/close-fxml-window-by-code-javafx accessed 21/2
        //Closing the window when you have saved 
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }
    
    
    public void shutdown() 
    {
                
    }
}
