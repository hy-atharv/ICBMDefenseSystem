package application;
import java.io.File; 	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;  
import javafx.scene.media.MediaPlayer;  
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            String path = "C:/Users/Atharv/Downloads/ICBMFirstUi.mp4";
            
            // Instantiating Media class
            Media media = new Media(new File(path).toURI().toString());

            // Instantiating MediaPlayer class
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            // Set the video to loop forever
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

            // Instantiating MediaView class
            MediaView mediaView = new MediaView(mediaPlayer);

            // By setting this property to true, the video will be played
            mediaPlayer.setAutoPlay(true);

            // Setting the size of the MediaView
            mediaView.setFitWidth(1024);
            mediaView.setFitHeight(576);
            mediaView.setX(258);
            mediaView.setY(80);
          
            Text text = new Text(140,70,"Intercontinental Ballistic Missile Defense System");
            text.setFont(Font.font("Pirulen Rg", 32)); // Replace with the actual font file path
            text.setFill(Color.GREEN);
            // Configure the Pane and set a black background
            BorderPane root = new BorderPane();
          
            root.setStyle("-fx-background-color: black;");
            //((BorderPane) root).setCenter(hBox);
            root.getChildren().add(mediaView);
            root.getChildren().add(text);  
            Button button = new Button("Simulate an ICBM Launch");
            button.setMaxSize(400, 40);
            Font f = Font.font("Pirulen Rg", 18);
            button.setFont(f);
            button.setStyle("-fx-background-color: black; -fx-text-fill: green;");
            
           
            button.setTranslateY(320);
            button.setOnMouseEntered(event -> {
                button.setStyle("-fx-background-color: black; -fx-text-fill: green; -fx-border-color: green; -fx-cursor: hand;");
            });

            // Restore original style when not hovered
            button.setOnMouseExited(event -> {
                button.setStyle("-fx-background-color: black; -fx-text-fill: green;");
            });
            
            button.setOnAction(event -> {
            	InputWindow inputwindow = new InputWindow();
            	inputwindow.inputwindow(primaryStage);
            });
            root.setCenter(button);
            
            Text dev = new Text();
            dev.setText("DEVELOPED BY ATHARV KUMAR TIWARI");
            dev.setFill(Color.GREEN);
            dev.setFont(Font.font("Pirulen Rg", FontWeight.SEMI_BOLD, 14));
            dev.setTranslateX(20);
            dev.setTranslateY(-10);
            root.setBottom(dev);
            
            
;            // Create the scene
            Scene scene = new Scene(root, 1540, 790);
            
            Image image = new Image(getClass().getResourceAsStream("logo_icbm.jpg"));
			primaryStage.getIcons().add(image);
			ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("ICBMImage.png")));
            imageView.setX(1310);
            imageView.setY(90);
            imageView.setFitWidth(93);
            imageView.setFitHeight(620);
            
            ImageView sarmat = new ImageView(new Image(getClass().getResourceAsStream("RS-28.png")));
            sarmat.setX(150);
            sarmat.setY(110);
            sarmat.setFitWidth(108);
            sarmat.setFitHeight(591);
            
            root.getChildren().add(imageView);
            root.getChildren().add(sarmat);
            primaryStage.setTitle("I.C.B.M Defense System Module");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
