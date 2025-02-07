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

public class InputWindow{
	
	
	public void inputwindow(Stage primaryStage) {
	try {		
		
        
        HBox LaunchInputContainer = new HBox(20);
        LaunchInputContainer.setTranslateX(40);
        LaunchInputContainer.setTranslateY(30);
        
        HBox TargetInputContainer = new HBox(20);
        TargetInputContainer.setTranslateX(40);
        TargetInputContainer.setTranslateY(80);
        
		Text launch = new Text("Geographical Coordinates of Launch Place");
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
	    
	    Text target = new Text("Geographical Coordinates of Target");
		target.setFont(Font.font("Bahnschrift SemiBold", FontWeight.SEMI_BOLD, 18));
		target.setFill(Color.GREEN);
	    target.setTranslateX(80);
	    target.setTranslateY(60);
	    
	    TextField targetlat = new TextField();
	    targetlat.setPromptText("Latitude");
	    targetlat.setTranslateX(50);
	    targetlat.setMaxWidth(150);
	    targetlat.setFocusTraversable(false);
	    targetlat.setStyle("-fx-font-family: 'Bahnschrift SemiBold'; -fx-font-size: 17; -fx-background-color: black; -fx-border-color: green; -fx-text-fill: green;");

	    
	    TextField targetlon = new TextField();
	    targetlon.setPromptText("Longitude");
	    targetlon.setTranslateX(50);
	    targetlon.setMaxWidth(150);
	    targetlon.setFocusTraversable(false);
	    targetlon.setStyle("-fx-font-family: 'Bahnschrift SemiBold'; -fx-font-size: 17; -fx-background-color: black; -fx-border-color: green; -fx-text-fill: green;");
	   
	    Text mass = new Text("Mass of the ICBM");
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
	    
	    Text thrust = new Text("Thrust of the Rocket Engine in ICBM");
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
	    
	    Text angle = new Text("Launch Angle");
		angle.setFont(Font.font("Bahnschrift SemiBold", FontWeight.SEMI_BOLD, 18));
		angle.setFill(Color.GREEN);
	    angle.setTranslateX(80);
	    angle.setTranslateY(210);
	    
	    TextField inangle = new TextField();
	    inangle.setPromptText("Angle(in Degrees)");
	    inangle.setTranslateX(90);
	    inangle.setTranslateY(230);
	    inangle.setMaxWidth(200);
	    inangle.setFocusTraversable(false);
	    inangle.setStyle("-fx-font-family: 'Bahnschrift SemiBold'; -fx-font-size: 17; -fx-background-color: black; -fx-border-color: green; -fx-text-fill: green;");
	    LaunchInputContainer.getChildren().addAll(launchlat, launchlon);
	    TargetInputContainer.getChildren().addAll(targetlat, targetlon);
	    
	    Button launchicbm = new Button("LAUNCH ICBM");
	    launchicbm.setMaxSize(200, 40);
        Font f = Font.font("Bahnschrift SemiBold", 24);
        launchicbm.setFont(f);
        launchicbm.setStyle("-fx-background-color: black; -fx-text-fill: green;");
        
        launchicbm.setTranslateX(150);
        launchicbm.setTranslateY(270);
        
        launchicbm.setOnMouseEntered(event -> {
            launchicbm.setStyle("-fx-background-color: black; -fx-text-fill: green; -fx-border-color: red; -fx-cursor: hand;");
        });

        // Restore original style when not hovered
        launchicbm.setOnMouseExited(event -> {
            launchicbm.setStyle("-fx-background-color: black; -fx-text-fill: green;");
        });
        
        launchicbm.setOnAction(event -> {
        	boolean check = true;
        	TextField[] textFields = {launchlat, launchlon, targetlat, targetlon, inmass, inthrust, inangle};
            for (TextField textField : textFields) {
                if (textField.getText().isEmpty()) {
                    check = false;
                }
            }
            if (check) {
                // Perform the action if all text fields are filled
            	String launchLatitude = launchlat.getText();
                String launchLongitude = launchlon.getText();
                String targetLatitude = targetlat.getText();
                String targetLongitude = targetlon.getText();
                String Mass = inmass.getText();
                String Thrust = inthrust.getText();
                String Angle = inangle.getText();

               
                // Close the window
                Stage stage = (Stage) launchicbm.getScene().getWindow();
                stage.close();
                primaryStage.close();
                 
                

                // Set the window size
                

                // Create a Media object with the video file path
                String videoPath = "C:/Users/Atharv/Downloads/ICBMLaunch.mp4";
                Media media = new Media(new File(videoPath).toURI().toString());

                // Create a MediaPlayer to play the video
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setRate(1);

                // Create a MediaView to display the video
                MediaView mediaView = new MediaView(mediaPlayer);
               
                mediaView.setPreserveRatio(true);
                mediaView.setFitWidth(1600);
                mediaView.setFitHeight(900);
                Stage secondaryStage = new Stage();
                // Create a BorderPane to hold the MediaView
                BorderPane root = new BorderPane();
                root.setTop(mediaView);
                

                // Create a scene and set it to the secondary stage
                Scene scene = new Scene(root,1570,900);
                secondaryStage.setScene(scene);
               
                try
                {
                  // Show the secondary stage
                  secondaryStage.show();
               
                  // Play the video
              
                  mediaPlayer.play();
             
                  // Add a listener to detect when the video playback has finished
                  mediaPlayer.setOnEndOfMedia(() -> {
                    // Close the secondary stage when the video finishes
                    secondaryStage.close();
                    Stage t = new Stage();
                    double[] data = ProcessInput.processInput(launchLatitude, launchLongitude, targetLatitude, targetLongitude, Mass, Thrust, Angle);
                    double time = data[0];
                    double lat1 = data[1];
                    double lon1 = data[2];
                    double lat2 = data[3];
                    double lon2 = data[4];
                    double burntime = data[5];
                    double maxh_time = data[6];
                    double reentry_time = data[7];
                    double acl = data[8];
                    double ang = data[9];
                    double maxh = data[10];
                    
                    double Burnout_data[] = ICBMTrajectoryCalc.BurnoutPoint(burntime, acl, ang, lat1, lon1, lat2, lon2);
                    double burn_lat = Burnout_data[0];
                    double burn_lon = Burnout_data[1];
                    
                    WorldMap.ICBMData(time, lat1, lon1, lat2, lon2, burntime, maxh_time, reentry_time, acl, ang, maxh, burn_lat, burn_lon);
                    WorldMap w = new WorldMap();
                    
                    w.start(t);
                    
                });
                  
                } catch(Exception e) {
                	
                	double[] data = ProcessInput.processInput(launchLatitude, launchLongitude, targetLatitude, targetLongitude, Mass, Thrust, Angle);
                    double time = data[0];
                    double lat1 = data[1];
                    double lon1 = data[2];
                    double lat2 = data[3];
                    double lon2 = data[4];
                    double burntime = data[5];
                    double maxh_time = data[6];
                    double reentry_time = data[7];
                    double acl = data[8];
                    double ang = data[9];
                    double maxh = data[10];
                    
                    double Burnout_data[] = ICBMTrajectoryCalc.BurnoutPoint(burntime, acl, ang, lat1, lon1, lat2, lon2);
                    double burn_lat = Burnout_data[0];
                    double burn_lon = Burnout_data[1];
                    
                    Stage t = new Stage();
                    
                	Alert alert = new Alert(Alert.AlertType.NONE);
                    alert.setTitle("I.C.B.M Launched");
                    alert.setHeaderText(null);
                    alert.setContentText("I.C.B.M Launched from "+lat1+" "+lon1+"\nTarget: "+lat2+" "+lon2);
                    alert.showAndWait();
                    
                    
                    
                    WorldMap.ICBMData(time, lat1, lon1, lat2, lon2, burntime, maxh_time, reentry_time, acl, ang, maxh, burn_lat, burn_lon);
                    WorldMap w = new WorldMap();
                    
                    w.start(t);
                	
                }
                
                
                
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
       
        MainContainer.getChildren().addAll(launch, LaunchInputContainer, target, TargetInputContainer, mass, inmass, thrust, inthrust, angle, inangle, launchicbm);
	    
	    Stage inputStage = new Stage();
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: black;");
		root.setTop(MainContainer);
		Scene scene = new Scene(root, 500, 650);
		
		Image image = new Image(getClass().getResourceAsStream("logo_icbm.jpg"));
		inputStage.getIcons().add(image);
		inputStage.setY(110);
		inputStage.setX(520);
		inputStage.setTitle("I.C.B.M Launch Configuration");
        inputStage.setScene(scene);
        inputStage.show();
        } catch (Exception e) {
        e.printStackTrace();
        }
	}
}

	

	