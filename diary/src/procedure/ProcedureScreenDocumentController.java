/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procedure;

import common.DatabaseConnector;
import common.HelpDialogController;
import common.codeBank;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author amydo
 */
public class ProcedureScreenDocumentController implements Initializable
{
    @FXML TableView tblProcedures;
    @FXML TableColumn tblColName;
    
    @FXML TextField txtName;
    @FXML TextField txtDuration;
    @FXML TextField txtNurses;
    @FXML TextField txtPatients;
    @FXML ChoiceBox cbLocation;
    @FXML ChoiceBox cbLength;
    
    @FXML Button btnSave;
    @FXML Button btnAdd;
    @FXML Button btnDelete;
    
    @FXML Hyperlink hlHelp = new Hyperlink();
    
     ObservableList allProcedures = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cbLocation.getItems().addAll("", "Bed", "Non-bed");
        cbLength.getItems().addAll("", "minutes", "hour(s)");
        showInformation();
    }
    
        
    public void showInformation()
    {
        allProcedures.clear();
        tblProcedures.getItems().clear();
        txtName.setText("");
        txtDuration.setText("");
        txtNurses.setText("");
        txtPatients.setText("");
        cbLocation.setValue("");
        cbLength.setValue("");
        
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
        
        
        try
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            Statement stmt = c.createStatement();    
            
            String sql = "SELECT * FROM procedures";
                       
            rs = stmt.executeQuery(sql); 
                       
            while(rs.next())
            {
                String name = rs.getString("Name");
                Integer duration = rs.getInt("Duration");
                Integer nurses = rs.getInt("NumberOfNurses");
                Integer patients = rs.getInt("NumberOfPatients");
                String location = rs.getString("Location");
                
                procedure x = new procedure(name, duration, nurses, patients, location);
                allProcedures.add(x);
                
            }
             
            
                tblColName.setCellValueFactory(new PropertyValueFactory("Name"));

                tblProcedures.getItems().addAll(allProcedures);

                tblProcedures.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
                {
                    if(newSelection != null)
                    {
                        txtName.setText(((procedure)newSelection).getName());
                        txtDuration.setText(""+((procedure)newSelection).getDuration());
                        
                        int duration = ((procedure)newSelection).getDuration();
                        if(duration < 60)
                        {
                            txtDuration.setText(""+duration);
                            cbLength.setValue("minutes");
                        }
                        else
                        {
                            txtDuration.setText(""+(duration/60));
                            cbLength.setValue("hour(s)");
                        }
                        
                        txtNurses.setText(""+((procedure)newSelection).getNurses());
                        txtPatients.setText(""+((procedure)newSelection).getPatients());
                        cbLocation.setValue(((procedure)newSelection).getLocation());
                    }
                });

            
            
            c.close();   
        }
        catch (SQLException e)
        {
            
        }
           
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
    public void Delete(ActionEvent event) throws IOException
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation on Delete");
        alert.setHeaderText("You are about to delete a procedure");
        alert.setContentText("To delete, press OK");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            try
            {
                Connection c = DatabaseConnector.activateConnection();
                c.setAutoCommit( true ); 
                Statement stmt = c.createStatement();    

                String sql = "DELETE FROM procedures WHERE Name = '" + txtName.getText() + "'"; 
                
                //DELETE FROM STAFF SKILLS
                
                stmt.executeUpdate(sql); 
                
                c.close();
                
                tblProcedures.getItems().clear();
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

            tblProcedures.getItems().clear();
            showInformation();
        } 
        catch (SQLException e) 
        {

        }
    }
    
    private ProcedureDialogController DC;
    private Pane x;
    
    @FXML
    public void Add()
    {         
        try 
        {    
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Procedure");
            
            FXMLLoader Y = new FXMLLoader(getClass().getResource("/procedure/ProcedureDialog.fxml"));   
            
            x = Y.load(); //ISSUE
            DC = Y.getController();
            
            final Scene scene = new Scene(x, 600, 520);
            stage.setScene(scene);
            stage.setOnHidden(e -> DC.shutdown());
            stage.show();
                      
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(ProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ISSUE IN MAIN");
        }
     
    }

    
    
    private HelpDialogController HDC;
    private Pane Hx;
    
    @FXML
    public void help()
    {
        try 
        {    
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Help");
            
            FXMLLoader DL = new FXMLLoader(getClass().getResource("/common/HelpDialog.fxml"));   
            
            Hx = DL.load(); //ISSUE
            HDC = DL.getController();
            
            HDC.show("Procedure");
            
            final Scene scene = new Scene(Hx, 795, 876);
            stage.setScene(scene);
            stage.setOnHidden(e -> HDC.shutdown());
            stage.show();
                      
        } 
        catch (IOException ex) 
        {
            //Logger.getLogger(ProcedureScreenDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ISSUE IN MAIN");
        }
    }
    
    
    
    
}//end of class
