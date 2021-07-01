/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/* The purpose of this is to load the FXML file and launch the program */


public class App extends Application {



	@Override
	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("App.fxml")));
		stage.setTitle("Hocker's Todo App");
		stage.setScene(new Scene(root));
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
