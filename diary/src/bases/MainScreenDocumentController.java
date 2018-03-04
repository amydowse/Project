/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bases;

import blood.BloodScreenDocumentController;
import common.DatabaseConnector;
import common.codeBank;
import diary.DiaryScreenDocumentController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import nonbed.NonbedScreenDocumentController;
import oncology.OncologyScreenDocumentController;
import preop.PreopScreenDocumentController;
import procedure.ProcedureScreenDocumentController;
/**
 *
 * @author amydo
 */
public class MainScreenDocumentController implements Initializable
{
    public static Pane ContentPane;
    @FXML Pane Content;
    
    @FXML private Button btnHome = new Button();
    @FXML private Button btnLeft = new Button();
    @FXML private Button btnRight = new Button();
    @FXML private Label lblDate = new Label();
    
    @FXML private Button btnDiary = new Button();
    @FXML private Button btnBlood = new Button();
    @FXML private Button btnPreOp = new Button();
    @FXML private Button btnOncology = new Button();
    @FXML private Button btnNonBed = new Button();
        
    @FXML private DatePicker dpCalandar = new DatePicker();
    
    public static DiaryScreenDocumentController DSDC;
    public static PreopScreenDocumentController PSDC;
    public static BloodScreenDocumentController BSDC;
    public static NonbedScreenDocumentController NSDC;
    public static OncologyScreenDocumentController OSDC;
    
    private Pane DiaryPane;
    private Pane PreopPane;
    private Pane BloodPane;
    private Pane NonbedPane;
    private Pane OncologyPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        //Image from http://www.stickpng.com/img/icons-logos-emojis/home-icons/chimney-home-icon
        Image home = new Image(getClass().getResourceAsStream("/bases/home.png"));
        ImageView IVHome = new ImageView(home);
        IVHome.setFitHeight(43);
        IVHome.setFitWidth(50);
        btnHome.setGraphic(IVHome);
        
        Image left = new Image(getClass().getResourceAsStream("/bases/LeftArrow.png"));
        ImageView IVLeft = new ImageView(left);
        IVLeft.setFitHeight(30);
        IVLeft.setFitWidth(50);
        btnLeft.setGraphic(IVLeft);
        
        Image right = new Image(getClass().getResourceAsStream("/bases/RightArrow.png"));
        ImageView IVRight = new ImageView(right);
        IVRight.setFitHeight(30);
        IVRight.setFitWidth(50);
        btnRight.setGraphic(IVRight);
        
        
        
              
        ContentPane = this.Content;
        try 
        {
            FXMLLoader DSL = new FXMLLoader(getClass().getResource("/diary/diaryScreen.fxml"));            
            DiaryPane = DSL.load();
            DiaryPane.setAccessibleText("Diary");
            DSDC = DSL.getController();
            
            FXMLLoader PSL = new FXMLLoader(getClass().getResource("/preop/preopScreen.fxml"));
            PreopPane = PSL.load();
            PreopPane.setAccessibleText("Preop");
            PSDC = PSL.getController();
            
            FXMLLoader BSL = new FXMLLoader(getClass().getResource("/blood/bloodScreen.fxml"));
            BloodPane = BSL.load();
            BloodPane.setAccessibleText("Blood");
            BSDC = BSL.getController();
            
            FXMLLoader NSL = new FXMLLoader(getClass().getResource("/nonbed/nonbedScreen.fxml"));
            NonbedPane = NSL.load();
            NonbedPane.setAccessibleText("Nonbed");
            NSDC = NSL.getController();
            
            FXMLLoader OSL = new FXMLLoader(getClass().getResource("/oncology/oncologyScreen.fxml"));
            OncologyPane = OSL.load();
            OncologyPane.setAccessibleText("Oncology");
            OSDC = OSL.getController();
            
            
            codeBank.setCurrentDate(LocalDate.now());
            
            updateDate();
            changeContentPane(DiaryPane); 
            
            updateButtons();
            
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(MainScreenDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        //https://examples.javacodegeeks.com/desktop-java/javafx/datepicker-javafx/javafx-datepicker-example/#control_event accessed 24/2/18
        // Create a day cell factory
        Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>()
        {
            public DateCell call(final DatePicker datePicker)
            {
                return new DateCell()
                {
                    @Override
                    public void updateItem(LocalDate item, boolean empty)
                    {
                        // Must call super
                        super.updateItem(item, empty);

                        // Show Weekends in blue color
                        DayOfWeek day = DayOfWeek.from(item);
                        if ((day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) && !Extra(item))
                        {
                            this.setDisable ( true );
                            this.setStyle(" -fx-background-color: #ff0000; ") ;
                        }
                        
                        
                    }
                };
            }
        };


        // Set the day cell factory to the DatePicker
       dpCalandar.setDayCellFactory(dayCellFactory);

    }
    
    //if there = true
    //if not there = false
    public boolean Extra(LocalDate date)
    {
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();          
            
            //implement query
            String Stringdate = codeBank.dateToString(date);
            
            rs = stmt.executeQuery("SELECT * FROM extra WHERE Date ='" + Stringdate + "'"); 
                
            c.close();
            
            while(rs.next())
            { 
                return true;
            }
            return false;
            
        }
        catch (SQLException e)
        {
            
        } 
        return false;
    }
    
    
    
    
    
    
    
    public void updateButtons()
    {
        inUse();
        updateDiaryButton();
        updateBloodButton();
        updatePreopButton();
        updateOncologyButton();
        updateNonBedButton();
    }
    
    public void updateDiaryButton()
    {
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();          
            
            //implement query
            String date = codeBank.dateToString(codeBank.getCurrentDate());
            rs = stmt.executeQuery("SELECT count(*) AS total FROM diary WHERE Date ='" + date + "'"); 
                
            while(rs.next())
            { 
                btnDiary.setText("Diary\n\n"+rs.getInt("total") + " booked");
            }
            c.close();
        }
        catch (SQLException e)
        {
            
        } 
    }
    
    public void updateBloodButton()
    {
        try
        {
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            Statement stmt = c.createStatement(); 
            
            //make localdate into string
            LocalDate currentDate = codeBank.getCurrentDate();
            String day = currentDate.getDayOfWeek().name();
            String date = codeBank.dateToString(currentDate);
            
            rs = stmt.executeQuery("SELECT * FROM extra WHERE Date='" + date + "'");
            
            if(rs.isBeforeFirst() && rs.getInt("Blood") == 0) //in extra with a 0
            {
                btnBlood.setText("Blood Clinic\n\nNO CLINIC");
            }
            
            if((rs.isBeforeFirst() && rs.getInt("Blood")==1) || !rs.isBeforeFirst()) //if has data and blood is 1 OR no data 
            {
                //implement query
                rs = stmt.executeQuery("SELECT * FROM template WHERE Day = '" + date + "'" );

                if(rs.isBeforeFirst()) //found it by the date
                {
                    rs = stmt.executeQuery("SELECT count(*) AS total FROM blood WHERE Date ='" + date + "'"); 
                    if(rs.next())
                    { 
                        btnBlood.setText("Blood Clinic\n\n"+rs.getInt("total") + " booked"); //shows the number of appointments 
                    }
                    c.close();
                }
                else
                {
                    rs = stmt.executeQuery("SELECT * FROM template WHERE Day = '" + day + "'" ); 
                    
                    if(rs.isBeforeFirst())//found it by the day
                    {
                        while(rs.next())
                        {
                            String From = rs.getString("FromDate");
                            String To = rs.getString("ToDate");

                            if(To == null)
                            {
                                LocalDate dateFrom = codeBank.stringToDate(From);
                                if(currentDate.isAfter(dateFrom))
                                {
                                        rs = stmt.executeQuery("SELECT count(*) AS total FROM blood WHERE Date ='" + date + "'");
                                        while(rs.next())
                                        { 
                                            btnBlood.setText("Blood Clinic\n\n"+rs.getInt("total") + " booked");
                                        }
                                        c.close();
                                }
                                else
                                {
                                    btnBlood.setText("Blood Clinic\n\nNO CLINIC");
                                }
                            }
                            else
                            {
                                LocalDate dateFrom = codeBank.stringToDate(From);
                                LocalDate dateTo = codeBank.stringToDate(To);
    
                                if(currentDate.isBefore(dateTo) && (currentDate.isAfter(dateFrom)||currentDate.equals(dateFrom)))
                                {
                                    rs = stmt.executeQuery("SELECT count(*) AS total FROM blood WHERE Date ='" + date + "'");
                                    while(rs.next())
                                    { 
                                        btnBlood.setText("Blood Clinic\n\n"+rs.getInt("total") + " booked");
                                    }
                                    c.close();
                                }
                                else
                                {
                                    btnBlood.setText("Blood Clinic\n\nNO CLINIC");
                                }
                            }
                        }
                    }
                    else
                    {
                        btnBlood.setText("Blood Clinic\n\nNO CLINIC");
                    }
                }
            }
            else
            {
                btnBlood.setText("Blood Clinic\n\nNO CLINIC");
            }
            
            c.close();
        }
        catch (SQLException e)
        {
                
        }
    }
    
    
    public void updatePreopButton()
    {
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();          
            
            //implement query
            String date = codeBank.dateToString(codeBank.getCurrentDate());
            rs = stmt.executeQuery("SELECT count(*) AS total FROM preop WHERE Date ='" + date + "'"); 
                
            while(rs.next())
            { 
                btnPreOp.setText("Preop\n\n"+rs.getInt("total") + " booked");
            }
            c.close();
        }
        catch (SQLException e)
        {
            
        } 
    }
    
    public void updateOncologyButton()
    {
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();          
            
            //implement query
            String date = codeBank.dateToString(codeBank.getCurrentDate());
            rs = stmt.executeQuery("SELECT count(*) AS total FROM oncology WHERE Date ='" + date + "'"); 
                
            while(rs.next())
            { 
                btnOncology.setText("Oncology\n\n"+rs.getInt("total") + " booked");
            }
            c.close();
        }
        catch (SQLException e)
        {
            
        } 
    }
    
    public void updateNonBedButton()
    {
        try
        {
            // open a connection
            Connection c = DatabaseConnector.activateConnection();
            c.setAutoCommit( true ); 
            ResultSet rs ;
            
            // when creating a statement object, you MUST use a connection object to call the instance method
            Statement stmt = c.createStatement();          
            
            //implement query
            String date = codeBank.dateToString(codeBank.getCurrentDate());
            rs = stmt.executeQuery("SELECT count(*) AS total FROM nonbed WHERE Date ='" + date + "'"); 
                
            while(rs.next())
            { 
                btnNonBed.setText("Non-bed\n\n"+rs.getInt("total") + " booked");
            }
            c.close();
        }
        catch (SQLException e)
        {
            
        } 
    }
    
    
    public void inUse()
    {
        LocalDate selected = codeBank.getCurrentDate();
        String day = selected.getDayOfWeek().name();
        String date = codeBank.dateToString(selected);
       
        if(day.equals("MONDAY") || day.equals("TUESDAY") || day.equals("WEDNESDAY") || day.equals("THURSDAY") || day.equals("FRIDAY"))
        {
            btnDiary.setDisable(false);
            btnBlood.setDisable(false);
            btnPreOp.setDisable(false);
            btnOncology.setDisable(false);
            btnNonBed.setDisable(false);
        }
        else
        {
            try
            {
                Connection c = DatabaseConnector.activateConnection();
                c.setAutoCommit( true ); 
                ResultSet rs ;
                Statement stmt = c.createStatement();          

                rs = stmt.executeQuery("SELECT * FROM extra WHERE Date ='" + date + "'"); 

                while(rs.next())
                { 
                    if(rs.getInt("Surgery") == 0)
                    {
                        btnDiary.setDisable(true);
                    }
                    else
                    {
                        btnDiary.setDisable(false);
                    }
                    
                    if(rs.getInt("Blood") == 0)
                    {
                        btnBlood.setDisable(true);
                    }
                    else
                    {
                        btnBlood.setDisable(false);
                    }
                    
                    if(rs.getInt("Preop") == 0)
                    {
                        btnPreOp.setDisable(true);
                    }
                    else
                    {
                        btnPreOp.setDisable(false);
                    }
                    
                    if(rs.getInt("Oncology") == 0)
                    {
                        btnOncology.setDisable(true);
                    }
                    else
                    {
                        btnOncology.setDisable(false);
                    }
                    
                    if(rs.getInt("Nonbed") == 0)
                    {
                        btnNonBed.setDisable(true);
                    }
                    else
                    {
                        btnNonBed.setDisable(false);
                    }
                }
                c.close();
            }
            catch (SQLException e)
            {

            } 
        }
        
    }
    
    
    
    
    
    
    
    public void updateDate()
    {
        DateTimeFormatter Stringformatter = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy");
        String stringDate = codeBank.getCurrentDate().format(Stringformatter);
        lblDate.setText(stringDate);        
    }
    
    
    
    
    @FXML
    public void today()
    {     
        if(save())
        {
            //todays date into a localDate format 
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate Today = LocalDate.now();
            codeBank.setCurrentDate(Today);

            updateDate();
            updateButtons();

            show();
        }
    }
    
    @FXML 
    public void plusOneDay()
    {
        if(save())
        {
            LocalDate changingDate = codeBank.getCurrentDate().plusDays(1);
            DayOfWeek day = DayOfWeek.from(changingDate);

            while((day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) && !Extra(changingDate))
            {
                changingDate = changingDate.plusDays(1);
                day = DayOfWeek.from(changingDate);
            }

            codeBank.setCurrentDate(changingDate);
            updateDate();
            updateButtons();

            show();
        }
    }
    
    @FXML 
    public void minusOneDay()
    {
        if(save())
        {
            LocalDate changingDate = codeBank.getCurrentDate().minusDays(1);
            DayOfWeek day = DayOfWeek.from(changingDate);

            while((day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) && !Extra(changingDate))
            {
                changingDate = changingDate.minusDays(1);
                day = DayOfWeek.from(changingDate);
            }

            codeBank.setCurrentDate(changingDate);
            updateDate();
            updateButtons();

            show();
        }
    }
    
    @FXML
    //method for date picker 
    public void selectedDate()
    {
        if(save())
        {
            codeBank.setCurrentDate(dpCalandar.getValue());
            updateDate();
            updateButtons();
            show();
        }
    }
    
    
    
    
    
   
    
    public void show()
    {
        switch(ContentPane.getChildren().get(0).getAccessibleText())
        {
            case "Diary":   
                DSDC.showInformation(codeBank.getCurrentDate());
                break;
            case "Preop":
                PSDC.loadInformation();
                break;
            case "Blood":
                BSDC.showInformation();
                break;
            case "Nonbed":
                NSDC.showInformation(codeBank.getCurrentDate());
                break;
            case "Oncology":
                OSDC.showInformation(codeBank.getCurrentDate());
                break;
        }
    }
    
    public boolean save()
    {
        switch(ContentPane.getChildren().get(0).getAccessibleText())
        {
            case "Diary":   
                if(DSDC.beforeSave())
                {
                    DSDC.save(codeBank.getCurrentDate());
                    return true;
                }
                break;
            case "Preop":
                if(PSDC.beforeSave())
                {
                    PSDC.save(codeBank.getCurrentDate());
                    return true;
                }
                break;
            case "Blood":
                BSDC.showInformation();
                return true;
            case "Nonbed":
                if(NSDC.beforeSave())
                {
                    NSDC.save(codeBank.getCurrentDate());
                    return true;
                }
                break;
            case "Oncology":
                if(OSDC.beforeSave())
                {
                    OSDC.save(codeBank.getCurrentDate());
                    return true;
                }
                break;
        }
        return false;
    }
    
    
    
    @FXML
    public void showDiary() throws IOException
    {
        if(save())
        {        
            DSDC.showInformation(codeBank.getCurrentDate());
            updateButtons();
            changeContentPane(DiaryPane); 
        }
    }
   
    @FXML
    public void showBlood() throws IOException
    {
        if(save())
        {
            BSDC.showInformation();
            updateButtons();
            changeContentPane(BloodPane); 
        }
         
    }
    
    @FXML
    public void showPreop() throws IOException
    {
        if(save())
        {
            PSDC.loadInformation();
            updateButtons();
            changeContentPane(PreopPane);
        }
    }
    
    @FXML
    public void showOncology() throws IOException
    {
        if(save())
        {
            OSDC.showInformation(codeBank.getCurrentDate());
            updateButtons();
            changeContentPane(OncologyPane);
        }
        
    }
    
    @FXML
    public void showNonbed() throws IOException
    {
        if(save())
        {
            NSDC.showInformation(codeBank.getCurrentDate());
            updateButtons();
            changeContentPane(NonbedPane);
        }
       
    }
    
  
    @FXML
    public void Home() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/bases/startScreen.fxml"));
        if(save())
        {
            codeBank.setCurrentDate(LocalDate.now());
            Scene scene = new Scene(root);
            bases.Start.stage.setScene(scene);
        }
        
    }
    
    
    public static void changeContentPane(Pane newContent)
    {
        ContentPane.getChildren().clear();
        ContentPane.getChildren().add(newContent);
    }


    
    
}
