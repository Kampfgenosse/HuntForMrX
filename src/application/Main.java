package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Sample.fxml"));
			// Scene scene = new Scene(root,400,400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// primaryStage.setScene(scene);
			// primaryStage.show();
			
			// load the image
			Image image = new Image("map.jpg");
			
			// resizes the image to have width of 1000 while preserving the ratio and using
			// higher quality filtering method; this ImageView is also cached to
			// improve performance
			ImageView iv = new ImageView();
			iv.setImage(image);
			iv.setFitWidth(1000);
			iv.setPreserveRatio(true);
			iv.setSmooth(true);
			iv.setCache(true);
			
			Group root = new Group();
			Scene scene = new Scene(root);
			scene.setFill(Color.BLACK);
			HBox box = new HBox();
			box.getChildren().add(iv);
			root.getChildren().add(box);
			
			primaryStage.setTitle("Hunt for MisterX");
			primaryStage.setWidth(1000);
			primaryStage.setHeight(850);
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
