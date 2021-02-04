package com.qada99.screenshot.view;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import com.qada99.screenshot.Main;
import com.qada99.screenshot.config.enumeration.OutputType;
import com.qada99.screenshot.config.enumeration.PageOrientation;
import com.qada99.screenshot.service.Screenshot;
import com.qada99.screenshot.service.Settings;
import com.qada99.screenshot.util.ImageUtil;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
public class MainController {

	private Settings setting;
	private Timer timer;
	private Screenshot screenshot;
	private Media sound;
	@FXML
	private AnchorPane settingPane, outputPane, shutdownPane;

	@FXML
	private JFXComboBox<OutputType> typesComboBox;
	@FXML
	private AnchorPane mainPane;
	@FXML
	private Label folderName;

	@FXML
	private ImageView changedImage, rotatedImage;
	@FXML
	private JFXToggleButton keepImageToggle;

	@FXML
	private JFXCheckBox verticalCheckBox, horizontalCheckBox;

	public MainController() {
		super();
		this.screenshot = new Screenshot();
	}

	@FXML
	void sreenShot(MouseEvent event) {
		MediaPlayer mediaplayer = new MediaPlayer(sound);
		mediaplayer.play();
	}
	@FXML
	private void initialize() {
		this.settingPane.setVisible(false);
		this.outputPane.setVisible(false);
		this.shutdownPane.setVisible(false);
		this.setting = new Settings(Main.primaryStage);
		this.changedImage.maxHeight(57);
		this.changedImage.maxWidth(100);
		this.timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				changeImage();
			}
		}, 0, 500);
		this.verticalCheckBox.setSelected(true);
		this.typesComboBox.getItems().add(OutputType.PDF);
		this.typesComboBox.setValue(OutputType.PDF);
	    this.typesComboBox.getItems().add(OutputType.PPX);	
		sound = new Media(
				Paths.get("src/main/resources/audio/screenshot-sound.mp3").toUri().toString());

	}

	@FXML
	void outputClicked(MouseEvent event) {
		outputPane.toFront();
		outputPane.setVisible(!outputPane.isVisible());
		settingPane.setVisible(false);
	}

	private void changeImage() {
		if (this.settingPane.isVisible()) {
			try {
				Image img = ImageUtil.convertToFxImage(screenshot.capture(setting.getScreen()));
				this.changedImage.setImage(img);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@FXML
	void setScreen(MouseEvent event) {
		this.setting.setScreen();
	}
	@FXML
	void chooseFolder(MouseEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setInitialDirectory(setting.getOutputFolder());

		setting.setOutputFolder(directoryChooser.showDialog(getSetting().getPrimaryStage()));
		folderName.setText(setting.getOutputFolder().getName());
	}

	@FXML
	void checkBoxAction(ActionEvent event) {

		if (event.getSource() == verticalCheckBox) {
			this.horizontalCheckBox.setSelected(!verticalCheckBox.isSelected());
		} else if (event.getSource() == horizontalCheckBox) {
			this.verticalCheckBox.setSelected(!horizontalCheckBox.isSelected());
		}
		if (verticalCheckBox.isSelected()) {
			this.getSetting().getOutputConfig().setPageOrientation(PageOrientation.VERTICAL);
			rotatedImage.setRotate(rotatedImage.getRotate() + 90);
		} else {
			this.getSetting().getOutputConfig().setPageOrientation(PageOrientation.HORIZONTAL);
			rotatedImage.setRotate(-90f);
		}
	}
	@FXML
	void changeType(ActionEvent event) {
		this.setting.getOutputConfig().setType(this.typesComboBox.getValue());
	}

	@FXML
	void toggleAction(ActionEvent event) {
		if (event.getSource() == keepImageToggle) {
			this.setting.getOutputConfig().setKeepImage(keepImageToggle.isSelected());
		}
	}

	@FXML
	void shutdown(MouseEvent event) {
		this.timer.cancel();
		Platform.exit();

	}
	@FXML
	void settingsClicked(MouseEvent event) {

		settingPane.toFront();
		settingPane.setVisible(!settingPane.isVisible());
		outputPane.setVisible(false);
	}


	public Settings getSetting() {
		return setting;
	}

	public void setSetting(Settings setting) {
		this.setting = setting;
	}
}
