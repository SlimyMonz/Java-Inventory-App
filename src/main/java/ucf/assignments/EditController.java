/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class EditController {

	@FXML
	private TextField editValueField;
	@FXML
	private TextField editSerialField;
	@FXML
	private TextField editNameField;
	@FXML
	private ObservableList<InventoryItem> list;

	private InventoryItem newItem;


	public void clickConfirmEdit() {

		Checker check = new Checker();
		Errors err = new Errors();

		// create an if statement that uses a method to check all the values are valid or not
		if (check.allValues(list,
		                    editValueField.getText(),
		                    editSerialField.getText(),
		                    editNameField.getText())) { // then if valid:

			err.displayError("""
                    Edit accepted.

                    Close Edit Prompt to continue.""");
			// add new object with the values selected in the value containers
			this.newItem = new InventoryItem(
					editValueField.getText(),
					editSerialField.getText(),
					editNameField.getText());
		}
	}

	public InventoryItem getNewItem() {
		return this.newItem;
	}

	public Boolean nullNewItem() {
		return this.newItem == null;
	}


	public void transferObservableList(ObservableList<InventoryItem> list) {
		this.list = list;
	}

	public void transferValue(String itemValue) {
		this.editValueField.setText(itemValue);
	}

	public void transferSerial(String itemSerial) {
		this.editSerialField.setText(itemSerial);
	}

	public void transferName(String itemName) {
		this.editNameField.setText(itemName);
	}
}
