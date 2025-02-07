package application;

import javafx.scene.shape.Path;

import java.io.File;
import java.text.DecimalFormat;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WorldMap extends Application {
	
	
	static StackPane root = new StackPane();
	
	Text sat_updt = new Text("SATELLITE UPDATES:\nICBM Reached Apogee(Maximum Height)");
	
	PathTransition pathTransition_final = new PathTransition();
	PathTransition pathTransition1 = new PathTransition();
	
	DecimalFormat d = new DecimalFormat("#.###");
	
	private Stage Map;
	

	 private static double ftime = 0;
	 private static double lat1 = 0;
	 private static double lon1 = 0;
	 private static double lat2 = 0;
	 private static double lon2 = 0;
	 private static double abm_lat = 0;
	 private static double abm_lon = 0;
	 private static double burntime = 0;
	 private static double H_time = 0;
	 private static double re_time = 0;
	 private static double total_time = 0;
	 private static double acln = 0;
	 private static double angle = 0;
	 private static double maxh = 0;
	 private static double o_range = 0;
	 private static double intcp_lat = 0;
	 private static double intcp_lon = 0;
	 private static double icbm_time = 0;
	 private static double abm_time = 0;
	 private static double abm_range = 0;
	 
	 private static double b_lat = 0;
	 private static double b_lon = 0;
	 
	 public static void ICBMData(double time, double Lat1, double Lon1, double Lat2, double Lon2, double Burntime, double htime, double Retime, double acclrtn, double Angle, double Maxh, double burn_lat, double burn_lon) {
		    WorldMap.ftime = time*1000;
		    total_time = ftime;
		    WorldMap.lat1 = Lat1;
		    WorldMap.lon1 = Lon1;
		    WorldMap.lat2 = Lat2;
		    WorldMap.lon2 = Lon2;
		    WorldMap.burntime = Burntime*1000;
		    WorldMap.H_time = htime*1000;
		    WorldMap.re_time = Retime*1000;
		    WorldMap.acln = acclrtn;
		    WorldMap.angle = Angle;
		    WorldMap.maxh = Maxh;
		    
		    WorldMap.b_lat = burn_lat;
		    WorldMap.b_lon = burn_lon;
		}
	 
	 
	 
	 public static void ABMData(double or, double fc, double mass, double thrust, double lat, double lon) {
		 
		 WorldMap.abm_lat = lat;
		 WorldMap.abm_lon = lon;
		 WorldMap.o_range = or;
		 
		 double[] icbm_intcp_data = ABMProcessInput.IntcpCalc(lat1, lon1, lat2, lon2, fc, acln, burntime, angle, maxh, abm_lat, abm_lon, mass, thrust);
		 
		 WorldMap.intcp_lat = icbm_intcp_data[0];
		 WorldMap.intcp_lon = icbm_intcp_data[1];
		 WorldMap.abm_time = icbm_intcp_data[2]*1000;
		 WorldMap.icbm_time = icbm_intcp_data[3]*1000;
		
		 WorldMap.abm_range = icbm_intcp_data[4];
	 }
	   

	 private StringProperty countdownText = new SimpleStringProperty("TIME TILL IMPACT");
	 
	 Stage MapStage = new Stage();
	   
	
	@Override
	 public void start(Stage primaryStage) {
		
		this.Map = primaryStage;
		
		sat_updt.setFill(Color.GREEN);
    	sat_updt.setFont(Font.font("Bahnschrift SemiBold", 17));
		
		sat_updt.setText("SATELLITE UPDATES:\nICBM Launched from "+lat1+" "+lon1+"   Estimated Target: "+lat2+" "+lon2);
		
		ImageView map = new ImageView(new Image(getClass().getResourceAsStream("WorldMap.jpg")));
        map.setTranslateY(10);
        map.setFitWidth(1350);
        map.setFitHeight(673);
        
        Text timerText = new Text();
        timerText.textProperty().bind(countdownText);
        timerText.setFill(Color.RED);
        timerText.setFont(Font.font("Bahnschrift SemiBold", FontWeight.BOLD, 24));
        timerText.setTranslateY(-360);
        
        
        Text dev = new Text();
        dev.setText("DEVELOPED BY ATHARV KUMAR TIWARI");
        dev.setFill(Color.GREEN);
        dev.setFont(Font.font("Pirulen Rg", FontWeight.SEMI_BOLD, 15));
        dev.setTranslateY(370);
      

        
        
        double[] x_y1 = ConvertTo2D(lat1, lon1);
        
        double x1 = x_y1[0];
        double y1 = x_y1[1];

        Circle lpoint = new Circle(4);
        lpoint.setFill(Color.BLUE); // Color of the point
        lpoint.setStroke(Color.BLUE); // Border color (optional)
        
        lpoint.setTranslateX(x1);
        lpoint.setTranslateY(y1);
        
        Text launch = new Text("LAUNCH");
        launch.setFont(Font.font("Bahnschrift SemiBold", FontWeight.BOLD, 12));
        launch.setFill(Color.MIDNIGHTBLUE);
        launch.setTranslateX(x1);
        launch.setTranslateY(y1+11);
        
        
        
        double[] x_y2 = ConvertTo2D(lat2, lon2);
        
        double x2 = x_y2[0];
        double y2 = x_y2[1];
        
        
        double[] b_xy = ConvertTo2D(b_lat, b_lon);
        
        double b_x = b_xy[0];
        double b_y = b_xy[1];

        Circle tpoint = new Circle(4);
        tpoint.setFill(Color.BLUE); // Color of the point
        tpoint.setStroke(Color.BLUE); // Border color (optional)
        tpoint.setTranslateX(x2);
        tpoint.setTranslateY(y2);
        
        Text target = new Text("TARGET");
        target.setFont(Font.font("Bahnschrift SemiBold", FontWeight.BOLD, 12));
        target.setFill(Color.MIDNIGHTBLUE);
        target.setTranslateX(x2);
        target.setTranslateY(y2+11);

        

        // Create a path for the line
        Path path = new Path();
        path.getElements().add(new MoveTo(x1, y1));
        path.getElements().add(new LineTo(b_x, b_y));
        
        
       
        pathTransition1.setDuration(Duration.seconds(burntime/1000.0)); // Set the animation duration
        pathTransition1.setPath(path);
        pathTransition1.setNode(new Circle(3, Color.RED)); // A new Circle node for animation
        pathTransition1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        
        //From Intcp to Target
       

       
		root.setStyle("-fx-background-color: black;");
		
		root.getChildren().add(map);
		root.getChildren().add(timerText);
		root.getChildren().add(dev);
		root.getChildren().add(lpoint);
		root.getChildren().add(tpoint);
		root.getChildren().add(launch);
		root.getChildren().add(target);
		root.getChildren().add(sat_updt);
	    root.setAlignment(sat_updt, Pos.TOP_LEFT);
	    StackPane.setMargin(sat_updt, new Insets(10, 0, 0, 20));
		root.getChildren().add(pathTransition1.getNode());
		

	      
	       
		
		Scene scene = new Scene(root, 1540, 790);
        
        Image image = new Image(getClass().getResourceAsStream("logo_icbm.jpg"));
		primaryStage.getIcons().add(image);
		primaryStage.setTitle("I.C.B.M Defense System Module");
        primaryStage.setScene(scene);
        primaryStage.show();
        ABMInput a = new ABMInput();
	    a.ABMWindow(primaryStage);
	    
	    Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(1), event -> updateCountdownText())
	    		);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        pathTransition1.play();
	}
	
	public static void ABM(double x, double y) {
		
		Circle abmpoint = new Circle(2);
        abmpoint.setFill(Color.DARKGREEN); // Color of the point
        abmpoint.setStroke(Color.DARKGREEN); // Border color (optional)
        abmpoint.setTranslateX(x);
        abmpoint.setTranslateY(y);
        
        Text def = new Text("DEFENSE SYSTEM");
        def.setFont(Font.font("Bahnschrift SemiBold", FontWeight.BOLD, 12));
        def.setFill(Color.MIDNIGHTBLUE);
        def.setTranslateX(x);
        def.setTranslateY(y-9);
        
        root.getChildren().add(abmpoint);
        root.getChildren().add(def);
		
	}
	
	public static double[] ConvertTo2D(double lat, double lon) {
		
	    double radius = 6378.1370; // Earth's semi-major axis
        double Yradius = 6356.7523; //Earth's semi-minor axis
        double scalex = 1350 / (2.00 * Math.PI * radius); // Scale factor
        double scaley = 583 / (Math.PI*Yradius);

    

        // Convert latitude and longitude to radians
        double latRad = Math.toRadians(lat);
        double lonRad = Math.toRadians(lon);

        // Calculate the Mercator projection
        double x = radius * lonRad;
        double input = lat;
        input = Math.min(Math.max(input, -89.5), 89.5);
        double earthDimensionalRateNormalized = 1.0 - Math.pow(6356.7523 / 6378.1370, 2);

        double inputOnEarthProj = Math.sqrt(earthDimensionalRateNormalized) * Math.sin( Math.toRadians(input));

        inputOnEarthProj = Math.pow(((1.0 - inputOnEarthProj) / (1.0+inputOnEarthProj)), 0.5 * Math.sqrt(earthDimensionalRateNormalized));
    
        double inputOnEarthProjNormalized = Math.tan(0.5 * ((Math.PI * 0.5) - Math.toRadians(input))) / inputOnEarthProj;
     
    
        double y = (-1) * 6378.1370 * Math.log(inputOnEarthProjNormalized);
      
        // Apply the scale factor
      
        double scaledX = x*scalex ; // Adjust for image center
        double scaledY = -y*scaley; // Invert Y and adjust for image center
    
        double[] X_Y = {scaledX, scaledY};
        
        
    
      return X_Y;
    
	}


	private void updateCountdownText() {
        double millis = ftime;
        
        if(millis==-2000000) {
        	millis = -2000000;
        	countdownText.set("ICBM INTERCEPTED");
            sat_updt.setText("SATELLITE UPDATES:\nICBM Interception Successful");
        }
        if (millis <= 0 && millis!=-2000000) {
        	
        	sat_updt.setText("SATELLITE UPDATES:\nICBM Interception Failed ");
            countdownText.set("TARGET NUKED");
            
            /*Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ICBM Simulation");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to perform another I.C.B.M simulation");
            ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            Stage again = new Stage();
            Main m = new Main();
            alert.showAndWait();*/
            
            
        } else if(millis>=0) {
            int minutes = (int) (millis / 60000);
            int seconds = (int) ((millis % 60000) / 1000);
            int remainingMillis = (int) (millis % 1000);
            countdownText.set("TIME TILL IMPACT\n       " +
                    String.format("%d:%02d:%03d", minutes, seconds, remainingMillis));
            
            ftime-= 1;
            
            //SAT UPDATES

            if(millis==(total_time-15000)) {
           	     sat_updt.setText("SATELLITE UPDATES:\nICBM in Boost Phase");	    
           }
            
            if(intcp_lat==0 && intcp_lon==0 && millis==(total_time-(int)burntime)) {
            	
            	double[] b_xy = ConvertTo2D(b_lat, b_lon);
                
                double b_x = b_xy[0];
                double b_y = b_xy[1];
                
                double[] x_y2 = ConvertTo2D(lat2, lon2);
            	  double x2 = x_y2[0];
            	  double y2 = x_y2[1];
                
                Path path = new Path();
                path.getElements().add(new MoveTo(b_x, b_y));
                path.getElements().add(new LineTo(x2, y2));
                
                
                PathTransition pathTransition_0 = new PathTransition();
                
                pathTransition_0.setDuration(Duration.seconds((total_time-burntime)/1000.0)); // Set the animation duration
                pathTransition_0.setPath(path);
                pathTransition_0.setNode(pathTransition1.getNode()); 
                pathTransition_0.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                
                pathTransition_0.play();
            }
            
            if(millis==(total_time-(int)burntime)) {
            	
           	     sat_updt.setText("SATELLITE UPDATES:\nICBM reached Burnout Stage, now in Midcourse Phase");	
           	     
           	  
              double[] b_xy = ConvertTo2D(b_lat, b_lon);
              
              double b_x = b_xy[0];
              double b_y = b_xy[1];
              
              double[] intc_xy = ConvertTo2D(intcp_lat, intcp_lon);
          	  double intc_x = intc_xy[0];
          	  double intc_y = intc_xy[1];
              
              Path path = new Path();
              path.getElements().add(new MoveTo(b_x, b_y));
              path.getElements().add(new LineTo(intc_x, intc_y));
              
              
             
              pathTransition_final.setDuration(Duration.seconds((icbm_time-burntime)/1000.0)); // Set the animation duration
              pathTransition_final.setPath(path);
              pathTransition_final.setNode(pathTransition1.getNode()); 
              pathTransition_final.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
              
              pathTransition_final.play();
               
               
              
              
           }
            
            if(abm_range>o_range && millis==(total_time-(int)icbm_time)) {
            	
            	double[] intc_xy = ConvertTo2D(intcp_lat, intcp_lon);
            	double intc_x = intc_xy[0];
            	double intc_y = intc_xy[1];
            	
            	double[] x_y = ConvertTo2D(lat2, lon2);
            	
            	double x2 = x_y[0];
            	double y2 = x_y[1];
            	
            	Path path = new Path();
                path.getElements().add(new MoveTo(intc_x, intc_y));
                path.getElements().add(new LineTo(x2, y2));
                
                PathTransition pathTransition3 = new PathTransition();
               
                pathTransition3.setDuration(Duration.seconds((total_time-icbm_time)/1000.0)); // Set the animation duration
                pathTransition3.setPath(path);
                pathTransition3.setNode(pathTransition_final.getNode());
                pathTransition3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                
                pathTransition3.play();
            }
            
            if(millis==(total_time-(int)H_time)) {
            	 sat_updt.setText("SATELLITE UPDATES:\nICBM Reached Apogee(Maximum Height), currently in Midcourse Phase");	    
            }
            
            if(millis==(total_time-(int)re_time)) {
           	     sat_updt.setText("SATELLITE UPDATES:\nICBM in Reentry Phase, heading towards "+lat2+" "+lon2);	    
           }
            
            if(millis==((total_time-(int)icbm_time+(int)abm_time)+20000))
            {
            	// Create a Media object with the video file path
                String videoPath = "C:/Users/Atharv/Downloads/ABM_Launch.mp4";
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
                    
                });
                  
                } catch(Exception e) {
                    
                	Alert alert = new Alert(Alert.AlertType.NONE);
                    alert.setTitle("A.B.M Launched");
                    alert.setHeaderText(null);
                    alert.setContentText("Anti Ballistic Missile Launched for Interception");
                   
                    
                	
                }
            }
            
            
            if(abm_range<o_range && millis==(total_time-(int)icbm_time+(int)abm_time))
            {
            	
            	
            	
            	double[] abm_x_y1 = ConvertTo2D(abm_lat, abm_lon);
                
                double x1 = abm_x_y1[0];
                double y1 = abm_x_y1[1];
                
                double[] abm_x_y2 = ConvertTo2D(intcp_lat, intcp_lon);
                
                double x2 = abm_x_y2[0];
                double y2 = abm_x_y2[1];
                
             

                // Create a path for the parabola
                Path path = new Path();
                path.getElements().add(new MoveTo(x1, y1));
                path.getElements().add(new LineTo(x2, y2));

                // Create a PathTransition for the animation
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.seconds(abm_time/1000.0)); // Set the animation duration
                pathTransition.setPath(path);
                pathTransition.setNode(new Circle(3, Color.BLACK)); // A new Circle node for animation
                pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                
                root.getChildren().add(pathTransition.getNode());
                pathTransition.play(); 
                
                pathTransition.setOnFinished(event -> {
                	
                	ftime = -2000000;
                    // Animation has finished
                	// Create a Media object with the video file path
                    String videoPath1 = "C:/Users/Atharv/Downloads/Interception.mp4";
                    Media media1 = new Media(new File(videoPath1).toURI().toString());

                    // Create a MediaPlayer to play the video
                    MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
                    

                    // Create a MediaView to display the video
                    MediaView mediaView1 = new MediaView(mediaPlayer1);
                   
                    mediaView1.setPreserveRatio(true);
                    mediaView1.setFitWidth(1600);
                    mediaView1.setFitHeight(900);
                    Stage secondaryStage1 = new Stage();
                    // Create a BorderPane to hold the MediaView
                    BorderPane root1 = new BorderPane();
                    root1.setTop(mediaView1);
                    

                    // Create a scene and set it to the secondary stage
                    Scene scene1 = new Scene(root1,1570,900);
                    secondaryStage1.setScene(scene1);
                   
                    try
                    {
                      // Show the secondary stage
                      secondaryStage1.show();
                   
                      // Play the video
                  
                      mediaPlayer1.play();
                 
                      // Add a listener to detect when the video playback has finished
                      mediaPlayer1.setOnEndOfMedia(() -> {
                        // Close the secondary stage when the video finishes
                      secondaryStage1.close();
                      
                      countdownText.set("ICBM INTERCEPTED");
                      sat_updt.setText("SATELLITE UPDATES:\nICBM Interception Successful");
                      
                      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                      alert.setTitle("ICBM Simulation");
                      alert.setHeaderText(null);
                      alert.setContentText("Do you want to perform another I.C.B.M simulation");
                      ButtonType okButton = new ButtonType("OK");
                      alert.getButtonTypes().setAll(okButton);

                      // Handle the action when the "OK" button is clicked
                      alert.setOnHidden(e -> {
                          ButtonType result = alert.getResult();
                          if (result == okButton) {
                              // OK button was pressed
                              // Perform your action, e.g., start a new stage
                              Stage s = new Stage();
                              Main m = new Main();
                              m.start(s);
                              Map.close();
                              
                              	
                          }
                      });

                      // Show the alert
                      alert.show();
                        
                    });
                      
                    } catch(Exception e) {
                        
                    	Alert alert1 = new Alert(Alert.AlertType.NONE);
                        alert1.setTitle("Interception Successful");
                        alert1.setHeaderText(null);
                        alert1.setContentText("I.C.B.M Intercepted at "+intcp_lat+" "+intcp_lon);
                        alert1.show();     
                    	
                    }
     
                });
     
            }
        }
    }
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
