/*
 * Copyright Harry Hocker
 * harry_hocker@icloud.com
 */

package ucf.assignments;

import java.io.Serializable;

// class must be Serializable to save to file
public class InventoryItem implements Serializable {

	private String itemValue;
	private String itemSerial;
	private String itemName;

	public InventoryItem(String value, String string, String Name) {
		setItemValue(value);
		setItemSerial(string);
		setItemName(Name);
	}


	public String getItemValue() {
		return itemValue;
	}

	public String getItemSerial() {
		return itemSerial;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = valueFormatting(itemValue);
	}

	public void setItemSerial(String itemSerial) {
		this.itemSerial = itemSerial;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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
