/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package window.java;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Rohan
 */
public class Window extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        //setting full screen 
        //primaryStage.setFullScreen(true); 
        
        //Setting gridPane as layout for the page
        GridPane page_1 = new GridPane();
        
        //making gridlines visible
        //page_1.setGridLinesVisible(true);
        
        //css effects for page_1 i.e. gridpane
        page_1.setId("page");
       
        //setting gaps between rows and columns of grid 
        page_1.setHgap(10);
        page_1.setVgap(10);
        
        //padding of gridpane in scene
        page_1.setPadding(new Insets(50,50,50,50));
        
        //Logo Image
        Image logo = new Image(Window.class.getResourceAsStream("air-logo.png")); 
        ImageView logo_view = new ImageView(logo);
        
        //adding logo image to gridpane
        page_1.add(logo_view,0,0);
        
        //setting size of image logo
        logo_view.setFitHeight(150);
        logo_view.setFitWidth(200);
        
        
        //scenetitle
        Label scenetitle = new Label("Welcome To AeroSwing Flights...");
       
        //adding css effects to label welcome-label
        scenetitle.setId("welcome-label");
        page_1.add(scenetitle, 1,0,4,2);
        
        //Radio Buttons 
        RadioButton one_trip_btn = new RadioButton("One Way Trip");
        RadioButton round_trip_btn = new RadioButton("Round Trip");
        
        //ToggleGroup to select any one option for RadioButtons
        ToggleGroup group = new ToggleGroup();
        one_trip_btn.setToggleGroup(group);
        round_trip_btn.setToggleGroup(group);
        
        //Setting Title for radioButtons
        Text select_trip_text = new Text("Select Your Trip");
        select_trip_text.setId("text");
        
        //adding trip text and radio buttons to page
        page_1.add(select_trip_text, 0,3,2,1);
        page_1.add(one_trip_btn,0,4);
        page_1.add(round_trip_btn,0,5);
        
        //Journeydetails-text
        Text jour_detail_text = new Text("Enter Your Journey Details");
        jour_detail_text.setId("text");
        page_1.add(jour_detail_text,0,7,2,1);
        
        //Text for From:
        Text from_state_text = new Text("Journey From :");
        page_1.add(from_state_text,0,9);
        
        //ComboBox for Source States
        ComboBox from_city_list = new ComboBox();
        from_city_list.getItems().addAll("Mumbai","New Delhi","Bengaluru","Chennai");
        from_city_list.setPromptText("---------select city---------");
        from_city_list.setMaxSize(300,50);
        page_1.add(from_city_list,1,9);
        
        //css effects for combobox from_state_list
        from_city_list.setId("combobox");
        
        //departure date
        DatePicker depart_date = new DatePicker();
        depart_date.setPromptText("Departure Date");        
        depart_date.setEditable(false); 
        page_1.add(depart_date,2,9);
        
        //return date
        DatePicker return_date = new DatePicker();
        return_date.setPromptText("Return Date");
        return_date.setEditable(false);
        page_1.add(return_date,2,11);
        
        //disabling dates before toady's date and dates after 1 year  
        depart_date.setDayCellFactory(picker -> new DateCell(){
            @Override
            public void updateItem(LocalDate date, boolean empty){
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                if(return_date.getValue()==null)
                    setDisable(empty || date.compareTo(today)<0 || date.isAfter(today.plusYears(1)));   
                else if(return_date.getValue()!=null)
                    setDisable(empty || date.compareTo(return_date.getValue())>0 || date.compareTo(today)<0 || date.isAfter(return_date.getValue().plusYears(1)));
            }
        });
    
        //text for To:
        Text to_state_text = new Text("To Destination:");
        page_1.add(to_state_text,0,11);
 
        //ComboBox for Destination Cities
        ComboBox to_city_list = new ComboBox();
        to_city_list.getItems().addAll("Mumbai","New Delhi","Bengaluru","Chennai");
        to_city_list.setPromptText("---------select city---------");
        to_city_list.setMaxSize(300,50);
        page_1.add(to_city_list,1,11);
        
        //css effects for comobox to_state_list
        to_city_list.setId("combobox");

        
        //disabling dates before departure date and dates after 1 year  
        return_date.setDayCellFactory(picker -> new DateCell(){
            @Override
            public void updateItem(LocalDate date, boolean empty){
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                if(depart_date.getValue()!=null)
                    setDisable(empty || date.compareTo(depart_date.getValue())<0 || date.isAfter(depart_date.getValue().plusYears(1)));
                else if(depart_date.getValue()==null)
                    setDisable(empty || date.compareTo(today)<0 || date.isAfter(today.plusYears(1)));
            }
        });
        
        //enabling return date if only round trip is selected
        return_date.setDisable(true); 
 
        //if one way trip selected then disabling return date
        one_trip_btn.setOnAction(e -> {
            return_date.setDisable(true); 
        });
        
        //if round trip selected then enabling return date
        round_trip_btn.setOnAction(e -> {
            
            return_date.setDisable(false); 
        });   
        
        //Text for Class
        Text select_class = new Text("Select Class :");
        page_1.add(select_class,0,14);
        
        //ComboBox for selecting Class
        ComboBox class_list = new ComboBox();
        class_list.getItems().addAll("Economy","Business","First");
        class_list.setMaxSize(300,50);
        page_1.add(class_list,1,14,1,1);
        
        //css effects for comobox class_list
        class_list.setId("combobox");
        
        class_list.setPromptText("--------select class--------"); 
//        class_list.setMinSize(to_city_list.getWidth(),to_city_list.getHeight());
        
        //Text for travellers
        Text travellers_txt = new Text("Traveller(s) :");
        travellers_txt.setId("text");
        page_1.add(travellers_txt,0,15,2,1);
        
        //css effects for comobox travellers
        //travellers_txt.setId("combobox");
        
        //ComboBox for selecting Class
        Text adult_txt = new Text("Adult(above 15 yrs) :");
        page_1.add(adult_txt,0,16);
        
        final Spinner adult_no = new Spinner();
        //value_factory for adult i.e. setting range of values for no. of adults
        SpinnerValueFactory adult_range = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,4,0);
        adult_no.setValueFactory(adult_range); 
        page_1.add(adult_no, 1, 16);
        
        Text child_txt = new Text("Child(2-15 yrs) :");
        page_1.add(child_txt,0,17);
        
        final Spinner child_no = new Spinner();
        //value_factory for child i.e. setting range of values for no. of child
        SpinnerValueFactory child_range = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,4,0);
        child_no.setValueFactory(child_range);
        page_1.add(child_no, 1, 17);
        
        Text infant_txt = new Text("Infant(Under 2 yrs) :");
        page_1.add(infant_txt,0,18);
        
        final Spinner infant_no = new Spinner();
        //value_factory for infant i.e. setting range of values for no. of infants
        SpinnerValueFactory infant_range = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,4,0);
        infant_no.setValueFactory(infant_range);
        page_1.add(infant_no, 1, 18);
        
        
        //Text for account details 
        Text account_text = new Text("Enter Your Account Details");
        account_text.setId("text");
        page_1.add(account_text,0,20,3,1);
        
        //
        Text username_text = new Text("Username :");
        page_1.add(username_text,0,21);
        
        //
        TextField username_tf = new TextField();
        username_tf.setPromptText("Enter Username");  
        page_1.add(username_tf,1,21,1,1);
        
        //T
        Text passwd_text = new Text("Password :");
        page_1.add(passwd_text,0,22);      
        
        //
        PasswordField passwd_pf = new PasswordField();
        passwd_pf.setPromptText("Enter Password");
        page_1.add(passwd_pf,1,22);
        
        //forgot_password?
        Hyperlink forgot_pw = new Hyperlink("(forgot password ?)");
        page_1.add(forgot_pw,0,23);
        forgot_pw.setId("forgot-pw");
        
        //
        forgot_pw.setOnAction(new EventHandler<ActionEvent>(){
 
            @Override
            public void handle(ActionEvent e)
            {       
                new Forgot_Password();
            }      
        }); 
        
        //
        Button sign_in_btn = new Button("Sign in");
        page_1.add(sign_in_btn, 0, 26);
        
        //
        Button sign_up_btn = new Button("Sign up");
        page_1.add(sign_up_btn, 1, 26);
        
        sign_up_btn.setOnAction(e->{
           new Sign_Up_Window();
        });
    
        
        
        
        ScrollPane rootPane = new ScrollPane();
        rootPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        rootPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); 
        rootPane.setFitToHeight(true);
        rootPane.setFitToWidth(true); 
        //rootPane.setVmax(2);
        //rootPane.setHmax(2);
        //rootPane.setVvalue(20);
        rootPane.setContent(page_1);   
        
        
        
        //instantiating scene
        Scene scene = new Scene(rootPane, 1000, 600);
        
        scene.getStylesheets().add(Window.class.getResource("Window.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("AeroSwing National Flights");
        //primaryStage.setResizable(false);
        
        //setting primaryStage to the size of screen of pc
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
          //MinX and MinY are upper left corner of primaryStage
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
          //setting width of stage to width of screen
        primaryStage.setWidth(primaryScreenBounds.getWidth());
          //setting height of stage to height of screen
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        
        //show Stage
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /*private static boolean allField(TextField... textFields)
    {
        for(TextField textField : textFields)
        {
            if(textField.getText().trim().isEmpty())
            {
                return false;
            }
        }
        return true;
    }*/
  
}
