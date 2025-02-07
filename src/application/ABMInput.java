package application;


import java.io.File;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ABMInput{
	
	
	public void ABMWindow(Stage primaryStage) {
	try {		
		
        
        HBox LaunchInputContainer = new HBox(20);
        LaunchInputContainer.setTranslateX(40);
        LaunchInputContainer.setTranslateY(30);
        
        HBox Field = new HBox(20);
        Field.setTranslateX(40);
        Field.setTranslateY(80);
        
        
		Text launch = new Text("Geographical Coordinates of ABM Launcher");
		launch.setFont(Font.font("Bahnschrift SemiBold", FontWeight.SEMI_BOLD, 18));
		launch.setFill(Color.GREEN);
	    launch.setTranslateX(80);
	    launch.setTranslateY(10);
	    
	    TextField launchlat = new TextField();
	    launchlat.setPromptText("Latitude");
	    launchlat.setTranslateX(50);
	    launchlat.setMaxWidth(150);
	    launchlat.setFocusTraversable(false);
	    launchlat.setStyle("-fx-font-family: 'Bahnschrift SemiBold'; -fx-font-size: 17; -fx-background-color: black; -fx-border-color: green; -fx-text-fill: green;");

	    
	    TextField launchlon = new TextField();
	    launchlon.setPromptText("Longitude");
	    launchlon.setTranslateX(50);
	    launchlon.setMaxWidth(150);
	    launchlon.setFocusTraversable(false);
	    launchlon.setStyle("-fx-font-family: 'Bahnschrift SemiBold'; -fx-font-size: 17; -fx-background-color: black; -fx-border-color: green; -fx-text-fill: green;");
	    
	    
	    Text Range_Vert = new Text("Operational Range & Flight Ceiling of ABM");
		Range_Vert.setFont(Font.font("Bahnschrift SemiBold", FontWeight.SEMI_BOLD, 18));
		Range_Vert.setFill(Color.GREEN);
	    Range_Vert.setTranslateX(80);
	    Range_Vert.setTranslateY(60);
	    
	    TextField or = new TextField();
	    or.setPromptText("Operational Range");
	    or.setTranslateX(50);
	    or.setMaxWidth(180);
	    or.setFocusTraversable(false);
	    or.setStyle("-fx-font-family: 'Bahnschrift SemiBold'; -fx-font-size: 17; -fx-background-color: black; -fx-border-color: green; -fx-text-fill: green;");

	    
	    TextField fc = new TextField();
	    fc.setPromptText("Flight Ceiling(KMs)");
	    fc.setTranslateX(50);
	    fc.setMaxWidth(180);
	    fc.setFocusTraversable(false);
	    fc.setStyle("-fx-font-family: 'Bahnschrift SemiBold'; -fx-font-size: 17; -fx-background-color: black; -fx-border-color: green; -fx-text-fill: green;");
	    
	   
	    Text mass = new Text("Mass of the ABM");
		mass.setFont(Font.font("Bahnschrift SemiBold", FontWeight.SEMI_BOLD, 18));
		mass.setFill(Color.GREEN);
	    mass.setTranslateX(80);
	    mass.setTranslateY(110);
	    
	    TextField inmass = new TextField();
	    inmass.setPromptText("Mass(in Kilograms)");
	    inmass.setTranslateX(90);
	    inmass.setTranslateY(130);
	    inmass.setMaxWidth(200);
	    inmass.setFocusTraversable(false);
	    inmass.setStyle("-fx-font-family: 'Bahnschrift SemiBold'; -fx-font-size: 17; -fx-background-color: black; -fx-border-color: green; -fx-text-fill: green;");
	    
	    Text thrust = new Text("Thrust of the Rocket Engine in ABM");
		thrust.setFont(Font.font("Bahnschrift SemiBold", FontWeight.SEMI_BOLD, 18));
		thrust.setFill(Color.GREEN);
	    thrust.setTranslateX(80);
	    thrust.setTranslateY(160);
	    
	    TextField inthrust = new TextField();
	    inthrust.setPromptText("Thrust(in Newtons)");
	    inthrust.setTranslateX(90);
	    inthrust.setTranslateY(180);
	    inthrust.setMaxWidth(200);
	    inthrust.setFocusTraversable(false);
	    inthrust.setStyle("-fx-font-family: 'Bahnschrift SemiBold'; -fx-font-size: 17; -fx-background-color: black; -fx-border-color: green; -fx-text-fill: green;");
	    
	    LaunchInputContainer.getChildren().addAll(launchlat, launchlon);
	    Field.getChildren().addAll(or, fc);
	    
	    Button prepABM = new Button("PREPARE ABM FOR INTERCEPTION");
	    prepABM.setMaxSize(400, 40);
        Font f = Font.font("Bahnschrift SemiBold", 22);
        prepABM.setFont(f);
        prepABM.setStyle("-fx-background-color: black; -fx-text-fill: green;");
        
        prepABM.setTranslateX(50);
        prepABM.setTranslateY(230);
        
        prepABM.setOnMouseEntered(event -> {
            prepABM.setStyle("-fx-background-color: black; -fx-text-fill: green; -fx-border-color: green; -fx-cursor: hand;");
        });

        // Restore original style when not hovered
        prepABM.setOnMouseExited(event -> {
            prepABM.setStyle("-fx-background-color: black; -fx-text-fill: green;");
        });
        
        prepABM.setOnAction(event -> {
        	boolean check = true;
        	TextField[] textFields = {launchlat, launchlon, or, fc, inmass, inthrust};
            for (TextField textField : textFields) {
                if (textField.getText().isEmpty()) {
                    check = false;
                }
            }
            if (check) {
                // Perform the action if all text fields are filled
            	String launchLatitude = launchlat.getText();
                String launchLongitude = launchlon.getText();
                String OpRange = or.getText();
                String FCeiling = fc.getText();
                String Mass = inmass.getText();
                String Thrust = inthrust.getText();
                

               
                // Close the window
                Stage stage = (Stage) prepABM.getScene().getWindow();
                stage.close();
                
             
                 double[] ABMdata = ABMProcessInput.ABMprocessInput(launchLatitude, launchLongitude, OpRange, FCeiling, Mass, Thrust);
                 double X = ABMdata[0];
                 double Y = ABMdata[1];
                 double o_r = ABMdata[2];
                 double vr = ABMdata[3];
                 double abm_mass = ABMdata[4];
                 double abm_thrust = ABMdata[5];
                 double abm_lat = ABMdata[6];
                 double abm_lon = ABMdata[7];
                 WorldMap.ABMData(o_r, vr, abm_mass, abm_thrust, abm_lat, abm_lon);
                 WorldMap.ABM(X, Y);
               
                
                
                
                
                
            } else {
            	// Show an error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incomplete Data");
                alert.setHeaderText(null);
                alert.setContentText("Incomplete Data");
                alert.showAndWait();
            }
            
        });
        
       
	
	    
	    VBox MainContainer = new VBox(); 
       
        MainContainer.getChildren().addAll(launch, LaunchInputContainer, Range_Vert, Field, mass, inmass, thrust, inthrust, prepABM);
	    
	    Stage inputStage = new Stage();
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: black;");
		root.setTop(MainContainer);
		Scene scene = new Scene(root, 500, 580);
		
		Image image = new Image(getClass().getResourceAsStream("logo_icbm.jpg"));
		inputStage.getIcons().add(image);
		inputStage.setY(110);
		inputStage.setX(520);
		inputStage.setTitle("Anti Ballistic Missile Configuration");
        inputStage.setScene(scene);
        inputStage.show();
        } catch (Exception e) {
        e.printStackTrace();
        }
	}
}