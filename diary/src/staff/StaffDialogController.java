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
        loadProcedures();
    }
    
    
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
        tblProcedures.getItems().addAll(allProcedures);
        tblColProcedure.setCellValueFactory(new PropertyValueFactory("ProcedureName"));
        tblColStatus.setCellValueFactory(new PropertyValueFactory("hasSkill"));
        
        //Add in changing the skill set of a staff member 
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
            saveProcedures();
            TopMenuDocumentController.SSDC.showInformation();
            //https://stackoverflow.com/questions/13567019/close-fxml-window-by-code-javafx accessed 21/2
            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
            
        }
    }
    
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
                    //System.out.println(lblID.getText() + "  " + allProcedures.get(i).getProcedureName());
                    stmt.executeUpdate("INSERT INTO skill (Staff_ID, Procedure_Name) VALUES ('" + lblID.getText() + "','" + allProcedures.get(i).getProcedureName() + "')");
                }
            }
            c.close();
        }
        catch(SQLException e)
        {
            System.out.println("Saving pr");
            Logger.getLogger(StaffScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    public void shutdown()
    {
        
    }

    
}
