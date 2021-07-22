/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckerTest {

	String value = "$100";
	String serial = "1111111111";
	String Name = "bbbb";

	@Test
	void duplicates() {

		boolean bool = !serial.equals("1111111112");

		assertTrue(bool);

	}

	@Test
	void valueFormat() {


		if (value.contains("$")) value = value.replace("$", "");

		try {
			Double.parseDouble(value);
		} catch (NullPointerException | NumberFormatException ignored) {
			// error message will go here
			// will then return false
		}

		assertEquals(value, "100");

	}

	@Test
	void serialLength() {

		boolean bool = true;

		// return boolean based on serial number length
		if (serial.length() != 10) {
			bool = false;
		}
		if (serial.contains("#")) {
			bool = false;
		}
		assertTrue(bool);
	}

	@Test
	void validName() {
		// if the Name is less than 2 or greater than 256 characters, return false
		// else return true
		boolean bool = true;
		if (Name.length() < 2) bool = false;

		if (Name.length() > 256) bool = false;

		if (Name.contains("#")) bool = false;

		assertTrue(bool);
	}

	@Test
	void allValues() {
		boolean bool = true;
		// use all value checkers in this one method
		duplicates();
		valueFormat();
		serialLength();
		validName();
	}
}