/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {

	String string = "bababooey";
	ArrayList<String> array = new ArrayList<>();

	@Test
	void findItem() {
		if (string.contains("b")) {
			array.add(string);
		}
		assertTrue(array.contains(string));
	}
}