package com.qada99.screenshot.view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.qada99.screenshot.Main;
import com.qada99.screenshot.config.enumeration.OutputType;
import com.qada99.screenshot.config.enumeration.PageOrientation;
import com.qada99.screenshot.service.FileGenerator;
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
	private FileGenerator fileGenerator;
	private Media sound;
	private int counter = 0;
	@FXML
	private AnchorPane settingPane, outputPane;
	@FXML
	private JFXTextField seanceName, padding;
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
		this.fileGenerator = new FileGenerator();
	}

	@FXML
	void sreenShot(MouseEvent event) {
		this.setting.setSeanceName(seanceName.getText());
		String folderPath = this.setting.getOutputFolder().toString() + File.separator + this.setting.getSeanceName();
		if (counter == 0) {
			try {
				Files.createDirectory(Paths.get(folderPath));
				// you can't now change output folder
				this.seanceName.setEditable(false);

			} catch (IOException e) {
				System.err.println("Failed to create directory! ");
			}

		}
		try {
			this.setting.getImages().add(this.screenshot.capture(this.setting.getScreen(),
					folderPath + File.separator + "screen" + (counter + 1) + ".png"));
			// if all goes good
			counter++;
			MediaPlayer mediaplayer = new MediaPlayer(sound);
			mediaplayer.play();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void generate(MouseEvent event) {
		if (counter == 0)
			return;
		this.setting.getOutputConfig().setType(typesComboBox.getValue());
		if (verticalCheckBox.isSelected())
			this.setting.getOutputConfig().setPageOrientation(PageOrientation.VERTICAL);
		else
			this.setting.getOutputConfig().setPageOrientation(PageOrientation.HORIZONTAL);

		this.setting.getOutputConfig().setKeepImage(keepImageToggle.isSelected());
		try {
			this.setting.getOutputConfig().setPadding(Float.parseFloat(padding.getText()));

		} catch (NumberFormatException e) {
			this.setting.getOutputConfig().setPadding(60f);
		}
		String filePath = this.setting.getOutputFolder().toString() + File.separator + this.setting.getSeanceName()
				+ File.separator + this.setting.getSeanceName();

		try {
			this.fileGenerator.generate(setting, filePath);
			// kolchi howa hadak
			if (!this.setting.getOutputConfig().isKeepImage()) {
				for (File image : this.setting.getImages()) {
					image.delete();
				}
				this.setting.getImages().clear();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@FXML
	private void initialize() {
		this.settingPane.setVisible(false);
		this.outputPane.setVisible(false);
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
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-hh-mm");

		this.seanceName.setText("seance-" + dateFormat.format(new Date()));
		this.setting.setSeanceName(this.seanceName.getText());
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
				Image img = ImageUtil.convertToFxImage(screenshot.capture(setting.getScreen()));
				this.changedImage.setImage(img);


		}

	}

	@FXML
	void setScreen(MouseEvent event) {
		this.setting.setScreen();
	}
	@FXML
	void chooseFolder(MouseEvent event) {
		if (counter > 0)
			return;
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
