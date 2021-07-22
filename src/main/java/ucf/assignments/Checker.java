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

		if (value.contains("$")) value = value.replace("$", "");
		if(value.contains("-")) {
			error.displayError("Cannot be a negative value!");
			return false;
		}

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
		if (serial.contains("#")) {
			error.displayError("Cannot contain '#' in any circumstance!");
			return false;
		}
		return true;
	}

	public Boolean validName(String Name) {
		// if the Name is less than 2 or greater than 256 characters, return false
		// else return true
		if (Name.length() < 2) {
			error.displayError("Name needs to contain more than 2 characters!");
			return false;
		}

		if (Name.length() > 256) {
			error.displayError("Name needs to be no more than 256 characters!");
			return false;
		}

		if (Name.contains("#")) {
			error.displayError("Cannot contain '#'!");
			return false;
		}
		return true;
	}

	public Boolean allValues(ObservableList<InventoryItem> data, String value, String serialField, String NameField) {

		// use all value checkers in this one method
		if (!duplicates(data, serialField)) return false;
		if (!valueFormat(value)) return false;
		if (!serialLength(serialField)) return false;
		if (!validName(NameField)) return false;
		// if all of those pass, then finally return true
		return true;
	}


}
