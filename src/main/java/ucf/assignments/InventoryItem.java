/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;

import java.io.Serializable;
import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

// class must be Serializable to save to file
public class InventoryItem implements Serializable {


	private BigDecimal itemValue;
	private String itemSerial;
	private String itemDescription;

	// blank and parameter constructors
	public InventoryItem() {
		// set Date to blank
		// set default string to blank ""
		// set default Boolean to false
		this.itemValue = valueOf(0.00);
		this.itemSerial = ("");
		this.itemDescription = "";

	}

	public InventoryItem(String value, String string, String description) {
		// set Date to datepicker date from parameter
		// set string to parameter from GUI
		setItemValue(value);
		setItemSerial(string);
		setItemDescription(description);
	}

	// collection of Getters
	public String getItemValue() {
		// return Date value of object
		return itemValue.toString();
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
	public void setItemValue(String value) {
		// set item value using string
		this.itemValue = valueOf(Long.parseLong(value));
	}

	public void setItemSerial(String string) {

		// limit the string to 10 characters

		if (string.length() == 10) {
			this.itemSerial = string;
		}
		else if (string.length() > 10) {
			this.itemSerial = (string.substring(0, 10));
		}

	}

	public void setItemDescription(String description) {
		if (description.length() >= 2) this.itemDescription = description;
	}


}
