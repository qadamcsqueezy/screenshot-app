package com.qada99.screenshot.service;

import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.qada99.screenshot.Main;
import com.qada99.screenshot.config.enumeration.OutputType;
import com.qada99.screenshot.config.enumeration.PageOrientation;
import com.qada99.screenshot.service.screen.ScreenSetted;
import com.qada99.screenshot.service.screen.ScreenSizer;

import javafx.stage.Stage;

public class Settings implements ScreenSetted{

	
	private ScreenSizer screenSizer;
	private Stage primaryStage;
	private String seanceName;
	private File outputFolder;
	private final String defaultOutputFolder = System.getProperty("user.home");
	private OutputConfig outputConfig;

	private List<File> images;


	public Settings(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.screenSizer = new ScreenSizer();
		this.screenSizer.addListener(this);
	}

	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Rectangle getScreen() {
    	return this.screenSizer.getScreen();
    }
	public void setScreen() {
		Settings.this.screenSizer.setScreen();
	}

	public String getSeanceName() {
		return seanceName;
	}

	public void setSeanceName(String seanceName) {
		this.seanceName = seanceName;
	}

	public File getOutputFolder() {
		if (this.outputFolder == null) {
			this.outputFolder = new File(defaultOutputFolder);
		}
		return outputFolder;
	}

	public void setOutputFolder(File outputFolder) {
		this.outputFolder = outputFolder;
	}

	@Override
	public void screenSetted() {
		Main.showStage();
		
	}


	public OutputConfig getOutputConfig() {
		if (this.outputConfig == null) {
			this.outputConfig = new OutputConfig(PageOrientation.HORIZONTAL, OutputType.PDF, 60f, false);
		}
		return outputConfig;
	}


	public void setOutputConfig(OutputConfig outputConfig) {
		this.outputConfig = outputConfig;
	}


	public List<File> getImages() {
		if (this.images == null) {
			this.images = new ArrayList<File>();
		}
		return images;
	}

	public void setImages(List<File> images) {
		this.images = images;
	}
	
	
}
