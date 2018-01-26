/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author amydo
 */
public class RegularScreenDocumentController implements Initializable
{
    @FXML private RadioButton rdbRed = new RadioButton();
    @FXML private RadioButton rdbWhite = new RadioButton();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ToggleGroup group = new ToggleGroup();
        rdbRed.setToggleGroup(group);
        rdbWhite.setToggleGroup(group);
    }
    
}
