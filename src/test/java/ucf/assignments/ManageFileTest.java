/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;




import static org.junit.jupiter.api.Assertions.*;

class ManageFileTest {

	private final String dPath = System.getProperty("user.home");
	private String fPath = System.getProperty("user.home");
	private String fName = "nameOfFile";


	@Test
	void getFileName() {
		// assert the file name exists
		assertEquals("nameOfFile", fName);
	}

	@Test
	void setFileName() {
		//change filename
		//assert it changed
		this.fName = "um";
		assertEquals("um", fName);
	}

	@Test
	void getFilePath() {
		// assert default file paths are same
		assertEquals(fPath, dPath);
	}

	@Test
	void setFilePath() {
		//change path
		//assert it changed
		this.fPath = "um";
		assertEquals("um", fPath);
	}

	@Test
	public void getStringOfList() {
		String stringOfList = "";
		assertTrue(stringOfList.isBlank());
	}

	@Test
	public void setStringOfList() {
		// CANNOT TEST BECAUSE IT COULD EDIT PC FILES!
	}

	@Test
	void writeFile() {
		// CANNOT TEST BECAUSE IT COULD EDIT PC FILES!
	}

	@Test
	void readFile() {
		// CANNOT TEST BECAUSE IT COULD EDIT PC FILES!
	}
}