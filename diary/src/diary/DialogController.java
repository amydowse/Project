/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import common.codeBank;
import diary.DiaryScreenDocumentController;
import bases.MainScreenDocumentController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
    
    private int arrayValue;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
    }
    
    public void showInformation(int arrayValue, String text, String name, String time)
    {
        this.arrayValue = arrayValue;
        txtExtraInfo.setText(text);
        lblName.setText(name);
        lblTime.setText(time);
    }
    
    public void shutdown() 
    {
        System.out.println("Stop");
        MainScreenDocumentController.DSDC.updateArray(arrayValue, txtExtraInfo.getText());
        txtExtraInfo.setText("");
    }

}
