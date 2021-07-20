/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;

import java.io.Serializable;

// class must be Serializable to save to file
public class InventoryItem implements Serializable {

	private String itemValue;
	private String itemSerial;
	private String itemDescription;



	// constructor MUST have three strings, never void nor empty!
	public InventoryItem(String value, String string, String description) {
		// set Date to datepicker date from parameter
		// set string to parameter from GUI
		setItemValue(value);
		setItemSerial(string);
		setItemDescription(description);
	}

	// collection of Getters
	public String getItemValue() {
		// return dollar value of object
		return itemValue;
	}

	public String getItemSerial() {
		// return item serial
		return itemSerial;
	}

	public String getItemDescription() {
		// return item description
		return itemDescription;
	}

	// collection of Setters
	public void setItemValue(String itemValue) {
		// set value based on formatter
		this.itemValue = valueFormatting(itemValue);
	}

	public void setItemSerial(String itemSerial) {
		// set serial number
		this.itemSerial = itemSerial;
	}

	public void setItemDescription(String itemDescription) {
		// set item description
		this.itemDescription = itemDescription;
	}

	// formatter for the value
	private String valueFormatting(String itemValue) {

		if (itemValue.contains(".")) {
			String[] split = itemValue.split("\\.");
			String dollars = split[0];
			String cents = split[1];
			while (cents.length() > 2) {
				cents = cents.substring(0, cents.length()-1);
			}
			itemValue = dollars + "." + cents;
		}

		if (!itemValue.contains("$")) itemValue = "$" + itemValue;
		if (!itemValue.contains(".")) itemValue = itemValue + ".00";

		return itemValue;
	}

}
