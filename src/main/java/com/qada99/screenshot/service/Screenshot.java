package com.qada99.screenshot.service;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

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

	public Path capture(Rectangle screen, String outPutFile) throws IOException, URISyntaxException {
		ImageIO.write(robot.createScreenCapture(screen), extension, new File(outPutFile));
		return Paths.get(new URI(outPutFile));
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

}
