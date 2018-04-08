/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 *
 * @author amydo
 * 
 * Takes control of showing the help dialog and correct file when clicked on a screen
 * Depending on the screen that it was clicked from, a different file is loaded 
 * 
 */
public class HelpDialogController implements Initializable
{
    @FXML private Label lblHelpTitle = new Label();
    @FXML private TextArea txtHelpArea = new TextArea();

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        txtHelpArea.setWrapText(true);
        txtHelpArea.setEditable(false);
    }
    
    public void show(String screen)
    {
        String fileName = "";
        
        switch(screen)
        {
            case("Diary"):  
                lblHelpTitle.setText("HELP : DIARY");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/Diary.txt";
                break;
            case("Blood"):  
                lblHelpTitle.setText("HELP : BLOOD CLINIC");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/Blood.txt";
                break;
            case("Preop"):  
                lblHelpTitle.setText("HELP : PRE-OP");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/Preop.txt";
                break; 
            case("Oncology"):  
                lblHelpTitle.setText("HELP - ONCOLOGYY");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/Oncology.txt";
                break; 
            case("Nonbed"):  
                lblHelpTitle.setText("HELP : NON-BED");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/Nonbed.txt";
                break;    
            case("Staff"):  
                lblHelpTitle.setText("HELP : STAFF");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/Staff.txt";
                break; 
            case("Regular"):  
                lblHelpTitle.setText("HELP : REGULAR ATTENDERS");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/Regular.txt";
                break;    
            case("Procedure"):  
                lblHelpTitle.setText("HELP : PROCEDURES");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/Procedures.txt";
                break;    
            case("PatientSearch"):  
                lblHelpTitle.setText("HELP : PATIENT SEARCH");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/PatientSearch.txt";
                break;    
            case("ProcedureSearch"):  
                lblHelpTitle.setText("HELP : PROCEDURE SEARCH");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/ProcedureSearch.txt";
                break;  
            case("SettingsExtra"):  
                lblHelpTitle.setText("HELP : SETTINGS - EXTRA CLINIC");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/SettingsExtra.txt";
                break;    
            case("SettingsAlter"):  
                lblHelpTitle.setText("HELP : SETTINGS - ALTER CLINIC");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/SettingsAlter.txt";
                break;    
            case("SettingsTemplate"):  
                lblHelpTitle.setText("HELP : SETTINGS - TEMPLATE");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/SettingsTemplate.txt";
                break;       
        }
        
        
        //https://stackoverflow.com/questions/16265693/how-to-use-buffered-reader-in-java accessed 3/3
        BufferedReader reader = null;

        try 
        {
            File file = new File(fileName);
            reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) 
            {
                txtHelpArea.appendText(line + "\n");
            }

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } finally 
        {
            try 
            {
                reader.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        //https://stackoverflow.com/questions/39098633/how-to-control-the-javafx-textarea-auto-scroll accessed 4/3
        txtHelpArea.positionCaret(0);
        
    }

    public void shutdown() 
    {
        
    }
    
}
