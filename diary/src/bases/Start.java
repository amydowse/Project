/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bases;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;


/**
 *
 * @author amydo
 */
public class Start extends Application
{

    /**
     * @param args the command line arguments
     */
    
    public static Stage stage;
    
    public static void main(String[] args) 
    {
        Application.launch(args);
    }
    
    @Override public void start(Stage stage) throws Exception 
    {
        this.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/bases/startScreen.fxml"));
        Scene scene = new Scene(root);
        
        //scene.getStylesheets().add(getClass().getResource("tableComponent.css").toExternalForm());
        
        stage.setTitle("Safari Day Unit");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMaximized(true);
        stage.show();
    }

    
}
