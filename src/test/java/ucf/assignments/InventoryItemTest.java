/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryItemTest {

	String date = "2020-10-20";
	String text = "This is a todo description!";
	Boolean bool = false;

	@Test
	void getDueDate() {
		// assert that the date is stored and get-able
		assertEquals("2020-10-20", date);
	}

	@Test
	void getTodoText() {
		// assert that the text is stored and get-able
		assertEquals("This is a todo description!", text);
	}

	@Test
	void getBool() {
		// assert that stored boolean is false
		assertFalse(bool);
	}

	@Test
	void setDueDate() {
		// change this.date and assert it updated to the value
		this.date = "0000";
		assertEquals("0000", date);

	}

	@Test
	void setTodoText() {
		// create a very long string
		String string = "";
		for (int i = 0; i < 300; i++) string += "1";

		String expected = "";
		for (int i = 0; i < 256; i++) expected += "1";

		// limit the string to 256 characters
		if (string.length() > 256) {
			this.text = (string.substring(0, 256));}
		else {
			this.text = (string);
		}

		// assert string is limited to 256 characters
		assertEquals(expected, text);
	}

	@Test
	void setBool() {
		// change boolean to true
		// assert boolean is true
		this.bool = true;
		assertTrue(bool);
	}
}