/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staff;

import bases.TopMenuDocumentController;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author amydo
 */
public class StaffDialogController implements Initializable
{
    //Staff detials 
    @FXML TableView tblNames;
    @FXML TableColumn tblColName;
    
    @FXML Label lblID;
    @FXML TextField txtFirstName;
    @FXML TextField txtLastName;
    @FXML TextField txtPosition;
    @FXML TextField txtAgency;
    @FXML TextField txtNumber;
    @FXML TextArea txtExtraInfo;
    
    //Procedures details - what the staff can do 
    @FXML TableView tblProcedures;
    @FXML TableColumn tblColProcedure;
    @FXML TableColumn tblColStatus;
    
    @FXML Button btnSave;
    int ID = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try 
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);
            Statement stmt = c.createStatement();
            ResultSet rs;

            String sql = "SELECT MAX(ID) FROM staff";

            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                ID = rs.getInt(1) + 1;
            }

            c.close();

        } 
        catch (SQLException e) 
        {

        }
        lblID.setText(""+ID);
    }
    
    
  
        
    @FXML
    public void Save()
    {
        if (!txtFirstName.getText().equals("") && !txtLastName.equals("")) 
        {
            try 
            {
                Connection c = DatabaseConnector.activateConnection();
                c.setAutoCommit(true);
                Statement stmt = c.createStatement();

                String sql = "INSERT INTO staff (ID, FirstName, LastName, Position, Agency, ContactNumber, ExtraInfo) VALUES('" 
                                                                + lblID.getText() + "','"
                                                                + txtFirstName.getText() + "','"
                                                                + txtLastName.getText() + "','"
                                                                + txtPosition.getText() + "','"
                                                                + txtAgency.getText() + "','"
                                                                + txtNumber.getText() + "','"
                                                                + txtExtraInfo.getText() + "')";

                stmt.executeUpdate(sql);
                c.close();
                
            } 
            catch (SQLException e) 
            {
                    
            }
            TopMenuDocumentController.SSDC.showInformation();
            //https://stackoverflow.com/questions/13567019/close-fxml-window-by-code-javafx accessed 21/2
            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
            
        }
    }
    
    
    public void shutdown()
    {
        
    }

    
}
