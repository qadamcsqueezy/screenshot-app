package com.qada99.screenshot.controllers;

import java.awt.Rectangle;

import com.qada99.screenshot.screen.ScreenSetted;
import com.qada99.screenshot.screen.ScreenSizer;

import javafx.stage.Stage;

public class Settings implements ScreenSetted{

	
	private ScreenSizer screenSizer;
	
	private Stage primaryStage;
	

	public Settings(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.screenSizer = new ScreenSizer();
	}

	
    public Rectangle getScreen() {
    	return this.screenSizer.getScreen();
    }
	public void setScreen() {
		this.primaryStage.hide();
		this.screenSizer.setScreen();
		
		
	}
	@Override
	public void screenSetted() {
//		 Platform.runLater(new Runnable() {
//				@Override
//				public void run() {	
		this.primaryStage.show();
	}
	
	
	
}
