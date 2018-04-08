/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staff;

import bases.TopMenuDocumentController;
import common.DatabaseConnector;
import common.codeBank;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author amydo
 * 
 * Controller for the pop-up when you are adding a new staff member 
 * 
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
    
    ObservableList<skill> allProcedures = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try 
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);
            Statement stmt = c.createStatement();
            ResultSet rs;

            //Finding the next ID for a staff member - adding 1 to the highest one already in use 
            String sql = "SELECT MAX(ID) FROM staff";

            rs = stmt.executeQuery(sql);

            if (rs.next()) 
            {
                ID = rs.getInt(1) + 1;
            }

            c.close();

        } 
        catch (SQLException e) 
        {

        }
        lblID.setText(""+ID);
        loadProcedures();
    }
    
    //Getting all of the proceudres the ward offers - automatically saying that the new staff member cannot do any of them (showing a cross)
    public void loadProcedures()
    {
        try 
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);
            Statement stmt = c.createStatement();
            ResultSet rs;

            String sql = "SELECT Name FROM procedures";

            rs = stmt.executeQuery(sql);

            while (rs.next()) 
            {
                skill x = new skill(rs.getString("Name"), "✘");
                allProcedures.add(x);
            }            
        }
        catch (SQLException e)
        {
        }
        display();
    }
    
    
    public void display()
    {
        //Sort proceudres into alphabetical order and display 
        Collections.sort(allProcedures);
        tblProcedures.getItems().addAll(allProcedures);
        tblColProcedure.setCellValueFactory(new PropertyValueFactory("ProcedureName"));
        tblColStatus.setCellValueFactory(new PropertyValueFactory("hasSkill"));
        
        //Add in changing the skill set of a staff member - can click on the procedure in the list so say they can/cannot do it 
        //https://stackoverflow.com/questions/27281370/javafx-tableview-format-one-cell-based-on-the-value-of-another-in-the-row accessed 10/2/18
            tblColStatus.setCellValueFactory(new PropertyValueFactory<skill, String>("hasSkill"));
            tblColStatus.setCellFactory(tc -> {
                TableCell<skill, String> cell = new TableCell<skill, String>()  {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : item.toString());
                    }
                };
                cell.setOnMouseClicked(e -> {
                    if (!cell.isEmpty()) {
                        int row = cell.getIndex();
                        changeStatus(row, ID);
                    }

                });
                return cell;
            });
        
    }
    
    //Chaning the symbol showed in the procedure list when it is clicked 
    public void changeStatus(int index, int ID)
    {
        if(allProcedures.get(index).getHasSkill().equals("✔"))
        {
            allProcedures.get(index).setHasSkill("✘");
        }
        else
        {
            allProcedures.get(index).setHasSkill("✔");
        }
        
        tblProcedures.getItems().clear();
        tblProcedures.getItems().addAll(allProcedures);     
         
    }
    
  
    //Saving the new staff member and their skills 
    @FXML
    public void Save()
    {
        //Checking that all of the data is entered 
        if (!txtFirstName.getText().equals("") && !txtLastName.getText().equals("")) 
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
            saveProcedures();
            
            //Reloads the staff screen to show the new staff member 
            TopMenuDocumentController.SSDC.showInformation();
            
            //Close the pop-up when it is saved 
            //https://stackoverflow.com/questions/13567019/close-fxml-window-by-code-javafx accessed 21/2
            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
            
        }
        else if (!txtFirstName.getText().equals("") || !txtLastName.getText().equals("")) 
        {
            codeBank.missingError();
        }
    }
    
    //Saving the procedures of the newly added staff memeber 
    public void saveProcedures()
    {
        try 
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit(true);
            Statement stmt = c.createStatement();
            
            for (int i = 0; i < allProcedures.size(); i++) 
            {
                if (allProcedures.get(i).getHasSkill().equals("✔")) 
                {
                    stmt.executeUpdate("INSERT INTO skill (Staff_ID, Procedure_Name) VALUES ('" + lblID.getText() + "','" + allProcedures.get(i).getProcedureName() + "')");
                }
            }
            c.close();
        }
        catch(SQLException e)
        {
            //Logger.getLogger(StaffScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    public void shutdown()
    {
        
    }

    
}
