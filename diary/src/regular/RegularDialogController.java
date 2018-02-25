/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular;

import bases.TopMenuDocumentController;
import common.DatabaseConnector;
import common.codeBank;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author amydo
 */
public class RegularDialogController implements Initializable
{
    @FXML TableView tblNames;
    @FXML TableColumn tblColName;
    
    @FXML TextField txtFirstName;
    @FXML TextField txtLastName;
    @FXML TextField txtDOB;
    @FXML TextField txtNumber;
    @FXML TextField txtNHS;
    @FXML CheckBox cbOncology;
    @FXML TextArea txtExtraInfo;
        
    @FXML private RadioButton rdbRed = new RadioButton();
    @FXML private RadioButton rdbWhite = new RadioButton();
    
    @FXML Button btnSave;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ToggleGroup group = new ToggleGroup();
        rdbRed.setToggleGroup(group);
        rdbWhite.setToggleGroup(group);
        rdbWhite.setSelected(true);
    }
    
    @FXML
    public void Save()
    {
        String wristband;
        if(rdbRed.isSelected())
        {
            wristband = "Red";
        }
        else
        {
            wristband = "White";
        }
        
        int oncology = 0;
        if(cbOncology.isSelected())
        {
            oncology = 1;
        }
        if(!txtNHS.getText().equals("") && !txtFirstName.getText().equals("") && !txtLastName.getText().equals("") && !txtDOB.getText().equals("")) 
        {
            try 
            {
                Connection c = DatabaseConnector.activateConnection();
                c.setAutoCommit(true);
                Statement stmt = c.createStatement();

                String sql = "INSERT INTO regular (HospitalNumber, FirstName, LastName, DateOfBirth, Number, Wristband, ExtraInfo, Oncology) VALUES ('"
                                                        + txtNHS.getText() + "','"
                                                        + txtFirstName.getText() + "','"
                                                        + txtLastName.getText() + "','"
                                                        + txtDOB.getText() + "','"
                                                        + txtNumber.getText() + "','"
                                                        + wristband + "','"
                                                        + txtExtraInfo.getText() + "','"
                                                        + oncology + "')";

                stmt.executeUpdate(sql);
                c.close();

            } 
            catch (SQLException e) 
            {

            }

            TopMenuDocumentController.RSDC.showInformation();
            //https://stackoverflow.com/questions/13567019/close-fxml-window-by-code-javafx accessed 21/2
            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
        }
        else if(!txtNHS.getText().equals("") || !txtFirstName.getText().equals("")|| !txtLastName.getText().equals("") || !txtDOB.getText().equals("")) 
        {
            codeBank.missingError();
        }
    }
    
    public void shutdown()
    {
        
    }
    
}
