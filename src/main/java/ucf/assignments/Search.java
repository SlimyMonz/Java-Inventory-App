/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;

import javafx.collections.ObservableList;

public class Search {

	public void findItem(ObservableList<InventoryItem> origin, ObservableList<InventoryItem> destination, String string) {
		// load in the origin
		// for each item in the origin, if it matches the serial or Name, add it to the destination

		for (InventoryItem item : origin) {
			// if serial matches, add to destination and skip to next InventoryItem
			if (string.equals(item.getItemSerial())) {
				destination.add(item);
				continue; // skips the next "if" so it's not added twice
			}
			// if Name contains anything related, adds to destination and continues loop
			if (item.getItemName().contains(string)) {
				destination.add(item);
			}

		}
	}
}
