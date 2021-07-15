/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;

import javafx.collections.ObservableList;

public class Checker {

	private final Errors error = new Errors();

	public Boolean duplicates(ObservableList<InventoryItem> list, String string) {

		for (InventoryItem serial : list) {
			if (serial.getItemSerial().equals(string)) return false;
		}
		return true;
	}

	public Boolean valueFormat(String value) {

		try {
			Double number = Double.parseDouble(value);
		} catch (NullPointerException | NumberFormatException e){
			return false;
		}

		return true;
	}

	public Boolean serialLength(String serial) {
		// return boolean based on serial number length
		return serial.length() == 10;
	}

	public Boolean validDescription(String description) {
		// return boolean based on description length
		return description.length() >= 2;
	}

	public boolean allValues(ObservableList<InventoryItem> data, String value, String serialField, String descriptionField) {

		if (!duplicates(data, serialField)) {
			// create error for duplicates
			error.displayError("Duplicate serial number!");
			return false;
		}

		if (!valueFormat(value)) {
			// create error for incorrect monetary value
			error.displayError("Value must contain numbers and\n" +
					                   "be formatted as either XXXX.XX or XXXX");
			return false;
		}

		if(!serialLength(serialField)) {
			error.displayError("Needs to be a serial number of any 10 characters!");
			return false;
		}

		if (!validDescription(descriptionField)) {
			error.displayError("Description needs to be two or more characters!");
			return false;
		}

		return true;
	}


}
