/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryItemTest {

	String itemValue = "$100";
	String itemSerial = "1111111111";
	String itemName = "bingo";

	@Test
	void getItemValue() {
		assertEquals("$100", itemValue);
	}

	@Test
	void getItemSerial() {
		assertEquals("1111111111", itemSerial);
	}

	@Test
	void getItemName() {
		assertEquals("bingo", itemName);
	}

	@Test
	void setItemValue() {
		itemValue = "$1";
		assertEquals("$1", itemValue);
	}

	@Test
	void setItemSerial() {
		itemSerial = "2222222222";
		assertEquals("2222222222", itemSerial);
	}

	@Test
	void setItemName() {
		itemName = "not bingo";
		assertEquals("not bingo", itemName);
	}
}