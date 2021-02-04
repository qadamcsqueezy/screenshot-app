package com.qada99.screenshot.service;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Screenshot {

	private Robot robot;
	private String extension = "png";
	public Screenshot() {
		super();
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public File capture(Rectangle screen, String path) throws IOException {
		File image = new File(path);
		ImageIO.write(robot.createScreenCapture(screen), extension, image);
		return image;
	}

	public BufferedImage capture(Rectangle screen) {
		return robot.createScreenCapture(screen);
	}
	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

}
