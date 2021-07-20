/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;


import static org.junit.jupiter.api.Assertions.*;

class ManageFileTest {

	private final String dPath = System.getProperty("user.home");
	private String fPath = System.getProperty("user.home");
	private String fName = "nameOfFile";
	private final String[] data = new String[1];


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
		// assert the file path exists
		assertEquals("/Users/monz", fPath);

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
	void writeFile() {
		// write file based on value
		data[0] = "yes";
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(Path.of(fPath + "/" + fName)));
			outputStream.writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		File file = new File(String.valueOf(Path.of(fPath + "/" + fName)));
		assertTrue(file.exists());

	}

	@Test
	void readFile() {
		// create data
		data[0] = "yes";
		// read the data
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(Path.of(fPath + "/" + fName)));
			outputStream.writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		File file = new File(String.valueOf(Path.of(fPath + "/" + fName)));
		// assert the data exists

	}
}