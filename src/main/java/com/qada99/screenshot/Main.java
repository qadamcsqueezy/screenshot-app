package com.qada99.screenshot;


import java.awt.Rectangle;

import com.qada99.screenshot.service.Settings;
import com.qada99.screenshot.service.screen.ScreenSizer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Main extends Application{
    
     private ScreenSizer screenSizer;
     private static Stage pStage;
     private static Rectangle screen;
     private Settings setting;
    @Override
    public void start(Stage primaryStage) {
    	this.setting = new Settings(primaryStage);
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            public void handle(ActionEvent event) {
            Main.this.setting.setScreen();

            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);

 Scene scene = new Scene(root, 300, 250);
//        primaryStage.setAlwaysOnTop(true);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 public static void main(String[] args) {
        launch(args);
    }
 
public static Stage getpStage() {
	return pStage;
}
public static void setpStage(Stage p) {
	pStage = p;
}
public static Rectangle getScreen() {
	return screen;
}
public static void setScreen(Rectangle screen) {
	Main.screen = screen;
}


}
