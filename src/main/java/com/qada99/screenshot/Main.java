package com.qada99.screenshot;


import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.qada99.screenshot.controllers.Settings;
import com.qada99.screenshot.screen.ScreenSetted;
import com.qada99.screenshot.screen.ScreenSizer;

import javafx.application.Application;
import javafx.application.Platform;
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
//    	screenSizer = new ScreenSizer();
    	this.setting = new Settings(primaryStage);
        DirectoryChooser directoryChooser = new DirectoryChooser();
//        setpStage(primaryStage);
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            public void handle(ActionEvent event) {
            Main.this.setting.setScreen();
//            	 primaryStage.setOpacity(0);
//            getpStage().hide();
//             screenSizer.setScreen();
             	
//            	Robot r;
//				try {
//					r = new Robot();
//	            	BufferedImage image = r.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
//					ImageIO.write(image, "png", new File("C:\\Users\\pc\\Desktop\\test\\scren.png"));
//
//				} catch (AWTException | IOException e1 ) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
               
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
