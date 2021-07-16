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
			if (serial.getItemSerial().equals(string)) {
				// create error for duplicates
				error.displayError("Duplicate serial number!");
				return false;
			}
		}
		return true;
	}

	public Boolean valueFormat(String value) {

		try {
			Double.parseDouble(value);
		} catch (NullPointerException | NumberFormatException e) {
			// create error for incorrect monetary value
			error.displayError("Value must contain numbers and\n" +
			                   "be formatted as either 'integer.XX'\n" +
			                   "or any arrangement of valid integers");
			return false;
		}
		return true;
	}

	public Boolean serialLength(String serial) {
		// return boolean based on serial number length
		if (serial.length() != 10) {
			error.displayError("Needs to be a serial number of any 10 characters!");
			return false;
		}
		return true;
	}

	public Boolean validDescription(String description) {
		// if the description is less than 2 or greater than 256 characters, return false
		// else return true
		if (description.length() < 2) {
			error.displayError("Description needs to contain 2 or more characters!");
			return false;
		}

		if (description.length() > 256) {
			error.displayError("Description needs to be no more than 256 character!");
			return false;
		}

		return true;
	}

	public boolean allValues(ObservableList<InventoryItem> data, String value, String serialField, String descriptionField) {

		// use all value checkers in this one method

		if (!duplicates(data, serialField)) return false;

		if (!valueFormat(value)) return false;

		if (!serialLength(serialField)) return false;

		if (!validDescription(descriptionField)) return false;

		return true;
	}


}
