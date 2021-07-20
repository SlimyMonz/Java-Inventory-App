/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

/* The purpose of this is to load the FXML file and launch the program */


public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InventoryWindow.fxml")));
		stage.setTitle("Inventory");
		stage.setScene(new Scene(root));
		stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("icon.jpeg"))));
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
