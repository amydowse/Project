/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular;

import common.DatabaseConnector;
import common.HelpDialogController;
import common.codeBank;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author amydo
 */
public class RegularScreenDocumentController implements Initializable
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
    @FXML Button btnAdd;
    @FXML Button btnDelete;
    
    @FXML Hyperlink hlHlep = new Hyperlink();
    
    ObservableList allPatients = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ToggleGroup group = new ToggleGroup();
        rdbRed.setToggleGroup(group);
        rdbWhite.setToggleGroup(group);
        
        showInformation();
        
        txtDOB.focusedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldV, Boolean newV) -> {
            if (!newV) {
                checkingDate(txtDOB.getText());
            }
        });
    }
    
    public void checkingDate(String value)
    {
        if(!codeBank.checkDate(value))
        {
            codeBank.dateError();
        }
    }
    
    
    public void showInformation()
    {
        allPatients.clear();
        tblNames.getItems().clear();
        txtFirstName.setText("");
        txtLastName.setText("");
        txtDOB.setText("");
        txtNumber.setText("");
        txtNHS.setText("");
        txtExtraInfo.setText("");
        
        rdbRed.setSelected(false);
        rdbWhite.setSelected(false);
        cbOncology.setSelected(false);
        
        
        try
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            Statement stmt = c.createStatement();    
            
            String sql = "SELECT * FROM regular";
                       
            rs = stmt.executeQuery(sql); 
                       
            while(rs.next())
            {
                String FirstName = rs.getString("FirstName");
                String LastName = rs.getString("LastName");
                
                String dob = rs.getString("DateOfBirth");
                LocalDate DOB = codeBank.stringToDate(dob);
                
                String Number = rs.getString("Number");
                String HospitalNumber = rs.getString("HospitalNumber");
                String Wristband = rs.getString("Wristband");
                String ExtraInfo = rs.getString("ExtraInfo");
                Integer Oncology = rs.getInt("Oncology");
             
                regular x = new regular(HospitalNumber, FirstName, LastName, DOB, Number, Wristband, ExtraInfo, Oncology);
                allPatients.add(x);
                
            }
             
            
                tblColName.setCellValueFactory(new PropertyValueFactory("name"));

                tblNames.getItems().addAll(allPatients);

                tblNames.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
                {
                    if(newSelection != null)
                    {
                        txtFirstName.setText(((regular)newSelection).getFirstName());
                        txtLastName.setText(((regular)newSelection).getLastName());
                        txtDOB.setText(codeBank.dateToString(((regular)newSelection).getDOB()));
                        txtNumber.setText(((regular)newSelection).getNumber());
                        txtNHS.setText(((regular)newSelection).getHospitalNumber());
                        txtExtraInfo.setText(((regular)newSelection).getExtraInformation());
                        
                        if(((regular)newSelection).getWristband().equals("Red"))
                        {
                            rdbRed.setSelected(true);
                        }
                        else
                        {
                            rdbWhite.setSelected(true);
                        }
                        
                        if(((regular)newSelection).getOncology() == 1)
                        {
                            cbOncology.setSelected(true);
                        }
                        else
                        {
                            cbOncology.setSelected(false);
                        }
                       
        
                      
                    }
                });

            
            
            c.close();   
        }
        catch (SQLException e)
        {
            
        }
           
    }
    
    
    
    
    
    @FXML
    public void Delete(ActionEvent event) throws IOException
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation on Delete");
        alert.setHeaderText("You are about to delete a regular attender");
        alert.setContentText("To delete, press OK");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            try
            {
                Connection c = DatabaseConnector.activateConnection();
                c.setAutoCommit( true ); 
                Statement stmt = c.createStatement();  
                ResultSet rs;
                
                String sql = "SELECT * FROM oncology WHERE Regular_HospitalNumber ='" + txtNHS.getText() + "'";
                rs = stmt.executeQuery(sql);
                
                //They have appointments
                if(rs.next())
                {
                    //Get the information of the regular attender you are deleting 
                    sql = "SELECT FirstName, LastName, DateOfBirth, Number, Wristband FROM regular WHERE HospitalNumber='" + txtNHS.getText() + "'";
                    rs = stmt.executeQuery(sql);
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    String name = firstName + " " + lastName;
                    
                    String dob = rs.getString("DateOfBirth");
                    LocalDate dobDate = codeBank.stringToDate(dob);
                    Period period = Period.between(dobDate, LocalDate.now());
                    int age = period.getYears(); 
                   
                    String number = rs.getString("Number");
                    String wristband = rs.getString("Wristband");

                    //Set the details of the oncology patients  (keeps them in the diary for past appointments)
                    sql = "UPDATE oncology SET Name = '" + name + "', Age = '" + age + "', ContactNumber ='" + number + "', Wristband = '" + wristband + "' WHERE Regular_HospitalNumber ='" + txtNHS.getText() + "'"; 
                    stmt.executeUpdate(sql);

                    //Delete the regualar attender 
                    sql = "DELETE FROM regular WHERE HospitalNumber='" + txtNHS.getText() + "'";
                    stmt.executeUpdate(sql); 
                }
                else //they dont have any appointents 
                {
                    sql = "DELETE FROM oncology WHERE Regular_HospitalNumber ='" + txtNHS.getText() + "'";
                    stmt.executeUpdate(sql);
                }

                c.close();
                
                tblNames.getItems().clear();
                showInformation();    
            }
            catch (SQLException e)
            {
                Logger.getLogger(RegularScreenDocumentController.class.getName()).log(Level.SEVERE, null, e);
            }
        } 
        else 
        {
            
        }
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
        
        //checks that all of the info has been entered
        if (!txtNHS.getText().equals("") && !txtFirstName.getText().equals("") && !txtLastName.getText().equals("") && !txtDOB.getText().equals("")) 
        {
            try 
            {
                Connection c = DatabaseConnector.activateConnection();
                c.setAutoCommit(true);
                Statement stmt = c.createStatement();

                String sql = "UPDATE regular SET        FirstName = '" + txtFirstName.getText()
                                                        + "', LastName = '" + txtLastName.getText()
                                                        + "', DateOfBirth = '" + txtDOB.getText()
                                                        + "', Number = '" + txtNumber.getText()
                                                        + "', Wristband = '" + wristband
                                                        + "', Oncology = '" + oncology
                                                        + "', ExtraInfo = '" + txtExtraInfo.getText() + "' WHERE  HospitalNumber = '" + txtNHS.getText() + "'";

                stmt.executeUpdate(sql);
                c.close();

                tblNames.getItems().clear();
                showInformation();
            } 
            catch (SQLException e) 
            {

            }
        }
        else if (!txtNHS.getText().equals("") || !txtFirstName.getText().equals("") || !txtLastName.getText().equals("") || !txtDOB.getText().equals("")) 
        {
            codeBank.missingError();
        }
    }
    
    private RegularDialogController DC;
    private Pane x;
    
    @FXML
    public void Add()
    {      
        System.out.println("xxxx");      
        try 
        {    
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Regular Attender");
            
            FXMLLoader Y = new FXMLLoader(getClass().getResource("/regular/RegularDialog.fxml"));   
            
            x = Y.load(); //ISSUE
            DC = Y.getController();
            
            final Scene scene = new Scene(x, 609, 914);
            stage.setScene(scene);
            stage.setOnHidden(e -> DC.shutdown());
            stage.show();
                      
        } 
        catch (IOException ex) 
        {
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
            
            HDC.show("Regular");
            
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

    
    
    
    
}//END OF CLASS
