/*
 * Copyright Harry Hocker
 * harry_hocker@icloud.com
 */

package ucf.assignments;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Errors {

	public void displayError(String string) {
		// create a new stage to display stuff
		Stage popUp = new Stage();

		// do not allow user to interact with app while stage is open
		popUp.initModality(Modality.APPLICATION_MODAL);
		popUp.setTitle("Prompt");

		// create new label that takes in String from getText() method
		Label label1 = new Label(string);
		// create new button under the label
		Button button1 = new Button("Close");
		// set button action to close stage
		button1.setOnAction(e -> popUp.close());
		// set the spacing of the text and button so it's not touching the edges
		VBox layout = new VBox(10);

		// align everything to center
		label1.setAlignment(Pos.CENTER);
		label1.setPadding(new Insets(10, 10, 10, 10));
		button1.setAlignment(Pos.BOTTOM_CENTER);

		// add label and button to stage
		layout.getChildren().addAll(label1, button1);
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(10, 10, 10, 10));

		// create a new scene
		Scene scene = new Scene(layout);

		// show the scene
		popUp.setScene(scene);
		popUp.showAndWait();

	}

}