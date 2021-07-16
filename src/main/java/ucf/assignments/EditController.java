/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class EditController {

	@FXML
	private TextField editValueField;
	@FXML
	private TextField editSerialField;
	@FXML
	private TextField editDescriptionField;
	@FXML
	private ObservableList<InventoryItem> data;

	public InventoryItem clickConfirmEdit(ActionEvent actionEvent) {

		Checker check = new Checker();

		// create an if statement that uses a method to check all the values are valid or not
		if (check.allValues(data,
		                    editValueField.getText(),
		                    editSerialField.getText(),
		                    editDescriptionField.getText())) { // then if valid:
			// add new object with the values selected in the bottom bar containers
			return new InventoryItem(
					editValueField.getText(),
					editSerialField.getText(),
					editDescriptionField.getText());
		}
		return null;
	}

public void transferObservableList(ObservableList<InventoryItem> data) {
		this.data = data;
}

	public void transferValue(String itemValue) {
		this.editValueField.setText(itemValue);
	}

	public void transferSerial(String itemSerial) {
		this.editSerialField.setText(itemSerial);
	}

	public void transferDescription(String itemDescription) {
		this.editDescriptionField.setText(itemDescription);
	}
}
