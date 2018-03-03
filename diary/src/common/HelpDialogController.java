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
                lblHelpTitle.setText("Help - Diary");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/Diary.txt";
                break;
            case("Blood"):  
                lblHelpTitle.setText("Help - Blood Clinic");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/Blood.txt";
                break;
            case("Preop"):  
                lblHelpTitle.setText("Help - Pre-op");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/Preop.txt";
                break; 
            case("Oncology"):  
                lblHelpTitle.setText("Help - Oncology");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/Oncology.txt";
                break; 
            case("Nonbed"):  
                lblHelpTitle.setText("Help - Non-bed");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/Nonbed.txt";
                break;    
            case("Staff"):  
                lblHelpTitle.setText("Help - Staff");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/Staff.txt";
                break; 
            case("Regular"):  
                lblHelpTitle.setText("Help - Regular Attenders");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/Regular.txt";
                break;    
            case("Procedure"):  
                lblHelpTitle.setText("Help - Procedures");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/Procedures.txt";
                break;    
            case("PatientSearch"):  
                lblHelpTitle.setText("Help - Patient Search");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/PatientSearch.txt";
                break;    
            case("ProcedureSearch"):  
                lblHelpTitle.setText("Help - Procedure Search");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/ProcedureSearch.txt";
                break;  
            case("Settings"):  
                lblHelpTitle.setText("Help - Settings");
                fileName = "C:/Users/amydo/Documents/NetBeansProjects/Diary/diary/src/help/Settings.txt";
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
        
    }

    public void shutdown() 
    {
        
    }
    
}
