/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;

import java.io.Serializable;

// class must be Serializable to save to file
public class InventoryItem implements Serializable {

	private final Checker check = new Checker();

	private String itemValue;
	private String itemSerial;
	private String itemDescription;


	// constructor MUST have three strings
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
		// set item value using string
		this.itemValue = itemValue;
	}

	public void setItemSerial(String itemSerial) {
		// set serial number
		this.itemSerial = itemSerial;
	}

	public void setItemDescription(String itemDescription) {
		// set item description
		this.itemDescription = itemDescription;
	}


}
