/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staff;

import common.DatabaseConnector;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author amydo
 */
public class StaffScreenDocumentController implements Initializable 
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
    @FXML Button btnAdd;
    @FXML Button btnDelete;
    
    ObservableList allStaff = FXCollections.observableArrayList();
    ObservableList<skill> allProcedures = FXCollections.observableArrayList();
    ArrayList<String> specificProcedures = new ArrayList<String>();

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       showInformation();
    }
    
    public void showInformation()
    {
        allStaff.clear();
        allProcedures.clear();
        tblNames.getItems().clear();
        tblProcedures.getItems().clear();
        
        lblID.setText("-");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtPosition.setText("");
        txtAgency.setText("");
        txtNumber.setText("");
        txtExtraInfo.setText("");
        
       
        try
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            Statement stmt = c.createStatement();    
            
            String sql = "SELECT * FROM staff";
                       
            rs = stmt.executeQuery(sql); 
                       
            while(rs.next())
            {
                Integer ID = rs.getInt("ID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String position = rs.getString("Position");
                String agency = rs.getString("Agency");
                String number = rs.getString("ContactNumber");
                String extraInfo = rs.getString("ExtraInfo");
                
                staff x = new staff(ID, firstName, lastName, position, agency, number, extraInfo );
                allStaff.add(x);
                
                
            }
                System.out.println(allStaff.size());
             
                tblColName.setCellValueFactory(new PropertyValueFactory("Show"));

                tblNames.getItems().addAll(allStaff);

                tblNames.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
                {
                     allProcedures.clear();
                    tblProcedures.getItems().clear();
                    
                    if(newSelection != null)
                    {
                        lblID.setText(""+((staff)newSelection).getID());
                        txtFirstName.setText(((staff)newSelection).getFirstName());
                        txtLastName.setText(((staff)newSelection).getLastName());
                        txtPosition.setText(((staff)newSelection).getPosition());
                        txtAgency.setText(((staff)newSelection).getAgency());
                        txtNumber.setText(((staff)newSelection).getNumber());
                        txtExtraInfo.setText(((staff)newSelection).getExtraInfo());
                    }
                    
                    showStaffProcedures(Integer.parseInt(lblID.getText()));
                    
                });

            
            
            c.close();   
        }
        catch (SQLException e)
        {
            System.out.println("ISSUE");
        }
           
    }
    
    public void showStaffProcedures(int ID)
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
                skill x = new skill(rs.getString("Name"), false);
                allProcedures.add(x);
            }
            
            sql = "SELECT Procedure_Name FROM skill WHERE Staff_ID ='" + ID + "'";

            rs = stmt.executeQuery(sql);

            while (rs.next()) 
            {
                specificProcedures.add(rs.getString("Procedure_Name"));
            }
            
            c.close();
            display();

        } 
        catch (SQLException e) 
        {

        }
    }
    
    
    public void display()
    {
        for(skill skills : allProcedures)
        {
            for(int i=0; i<specificProcedures.size(); i++)
            {
                if(skills.getProcedureName().equals(specificProcedures.get(i)))
                {
                    skills.setHasSkill(true);
                }
            }
        }
        
        tblColProcedure.setCellValueFactory(new PropertyValueFactory("ProcedureName"));
        tblColStatus.setCellValueFactory(new PropertyValueFactory("hasSkill"));
        tblProcedures.getItems().addAll(allProcedures);
    }
    
    
      
    
    @FXML
    public void Delete(ActionEvent event) throws IOException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation on Delete");
        alert.setHeaderText("You are about to delete a staff member");
        alert.setContentText("To delete, press OK");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            try
            {
                Connection c = DatabaseConnector.activateConnection();
                c.setAutoCommit( true ); 
                Statement stmt = c.createStatement();    

                String sql = "DELETE FROM staff WHERE ID = '" + lblID.getText() + "'"; 
                
                //DELETE FROM STAFF SKILLS
                
                stmt.executeUpdate(sql); 
                
                c.close();
                
                tblNames.getItems().clear();
                showInformation();    
            }
            catch (SQLException e)
            {

            }
        } 
        else 
        {
            
        }
    }
    
    
    
    @FXML
    public void Save() 
    {
        //checks that all of the info has been entered
        if (!txtFirstName.getText().equals("") && !txtLastName.equals("")) 
        {
            try 
            {
                Connection c = DatabaseConnector.activateConnection();
                c.setAutoCommit(true);
                Statement stmt = c.createStatement();

                String sql = "UPDATE staff SET          FirstName = '" + txtFirstName.getText()
                                                        + "', LastName = '" + txtLastName.getText()
                                                        + "', Position = '" + txtPosition.getText()
                                                        + "', Agency = '" + txtAgency.getText()
                                                        + "', ContactNumber = '" + txtNumber.getText()
                                                        + "', ExtraInfo = '" + txtExtraInfo.getText() + "' WHERE  ID = '" + lblID.getText() + "'";

                stmt.executeUpdate(sql);
                c.close();

                tblProcedures.getItems().clear();
                showInformation();
            } 
            catch (SQLException e) 
            {

            }
        }
    }
    
    
    private StaffDialogController DC;
    private Pane x;
    
    @FXML
    public void Add()
    {            
        try 
        {    
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Staff");
            
            FXMLLoader DL = new FXMLLoader(getClass().getResource("/staff/StaffDialog.fxml"));   
            
            x = DL.load(); //ISSUE
            DC = DL.getController();
            
            final Scene scene = new Scene(x, 1125, 930);
            stage.setScene(scene);
            stage.setOnHidden(e -> DC.shutdown());
            stage.show();
                      
        } 
        catch (IOException ex) 
        {
            //Logger.getLogger(ProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ISSUE IN MAIN");
        }
     
    }
    
}
