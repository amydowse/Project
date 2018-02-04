/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import common.codeBank;
import diary.DiaryScreenDocumentController;
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

public class DialogController implements Initializable
{
    
    @FXML private TextArea txtExtraInfo = new TextArea();
    @FXML private Label lblName = new Label();
    @FXML private Label lblTime = new Label();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        System.out.println("In here");
    }
    
    public void showInformation(String text, String name, String time)
    {
        txtExtraInfo.setText(text);
        lblName.setText(name);
        lblTime.setText(time);
    }

}
