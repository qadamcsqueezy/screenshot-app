package com.qada99.screenshot;


import java.awt.Rectangle;

import com.qada99.screenshot.view.MainController;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class Main extends Application{
    
	private static double xOffset = 0;
	private static double yOffset = 0;

	public static Stage primaryStage;
     private static Rectangle screen;

//     private Settings setting;
    @Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(MainController.class.getResource("mainScene.fxml"));
		Main.primaryStage = stage;
    	 Scene scene = new Scene(root);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setAlwaysOnTop(true);

		scene.setFill(Color.TRANSPARENT);
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				xOffset = primaryStage.getX() - event.getScreenX();
				yOffset = primaryStage.getY() - event.getScreenY();
			}

		});
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				primaryStage.setX(event.getScreenX() + xOffset);
				primaryStage.setY(event.getScreenY() + yOffset);
			}
		});
		primaryStage.setScene(scene);



		// this.setting = new Settings(primaryStage);
//        DirectoryChooser directoryChooser = new DirectoryChooser();
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
// 
//            public void handle(ActionEvent event) {
//            Main.this.setting.setScreen();
//
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//
// Scene scene = new Scene(root, 300, 250);
////        primaryStage.setAlwaysOnTop(true);
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
        primaryStage.show();
    }
 public static void main(String[] args) {
        launch(args);
    }
 
	public static void hideStage() {
		primaryStage.setOpacity(0f);
//		primaryStage.toBack();
 }

	public static Rectangle getScreen() {
	return screen;
}
public static void setScreen(Rectangle screen) {
	Main.screen = screen;
}

public static void showStage() {
	primaryStage.setOpacity(1f);

	// primaryStage.toFront();
//	primaryStage.setAlwaysOnTop(true);
}


}
