/*
 * Copyright Harry Hocker
 * harry_hocker@icloud.com
 */

package ucf.assignments;

import javafx.collections.ObservableList;

import java.util.HashMap;

public class Checker {

	private final Errors error = new Errors();

	public Boolean duplicateSerial(ObservableList<InventoryItem> list, String serialString) {

		HashMap<String, Integer> serialMap = new HashMap<>();

		for (InventoryItem item : list) {
			serialMap.put(item.getItemSerial(), 0);
		}
        return !serialMap.containsKey(serialString);
    }

	public Boolean valueFormat(String dollarValue) {
		String modifiedDollarValue = dollarValue.replace("$", "");

		if (dollarValue.contains("-")) {
			error.displayError("Cannot be a negative value!");
			return false;
		}
		try {
			Double.parseDouble(modifiedDollarValue);
		} catch (NullPointerException | NumberFormatException e) {
			error.displayError("""
                Value must contain numbers and
                be formatted as either 'integer.XX'
                or any arrangement of valid integers""");
			return false;
		}
		return true;
	}


	public Boolean serialLength(String serial) {
		if (serial.length() != 10) {
			error.displayError("Needs to be a serial number of exactly 10 characters!");
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
			error.displayError("Name canoot be more than 256 characters!");
			return false;
		}

		if (Name.contains("#")) {
			error.displayError("Cannot contain '#'!");
			return false;
		}
		return true;
	}

	public Boolean allValues(ObservableList<InventoryItem> inventoryItems, String value, String serialField, String NameField) {

		// use all value checkers in this one method
		if (!duplicateSerial(inventoryItems, serialField)) return false;
		if (!valueFormat(value)) return false;
		if (!serialLength(serialField)) return false;
        return validName(NameField);
		// if all of those pass, then finally return true
    }


}
