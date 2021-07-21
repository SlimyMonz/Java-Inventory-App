/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;


import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class ManageFile {

	private final String defaultPath = System.getProperty("user.home");
	private String filePath;
	private String fileName;
	private String stringOfList;

	public ManageFile() {
		// initializes the file with a default name and string
		this.fileName = "default";
		this.stringOfList = "";
	}


	public String getFileName() {
		// return string of file name
		return fileName;
	}

	public void setFileName(File file) {
		// set file name
		this.fileName = file.getName();
	}


	public String getFilePath() {
		if (filePath == null) {
			return defaultPath;
		} else {
			return filePath;
		}
	}

	public void setFilePath(File file) {
		// return the folder that the file is stored in
		this.filePath = file.getParent();
	}

	public String getStringOfList() {
		return stringOfList;
	}

	public void setStringOfList(ObservableList<InventoryItem> array) {
		Parser parse = new Parser();
		if (this.fileName.contains(".txt")) this.stringOfList = parse.toTSV(array);
		if (this.fileName.contains(".html")) this.stringOfList = parse.toHTML(array);

	}


	// turn the list of objets into a string and use a string builder or whatever, then write the string to files instead
	// IE: HTML file will use <h>string content</h> and such
	// replace ArrayList<InventoryItem> with String and use a different file writer that simply writes strings
	public void writeFile(Path path) {
		try {
			Files.writeString(path, getStringOfList());
		} catch (IOException ignored) {
		}
	}


	public String readFile(Path file) {
		// read the data
		// read as String to be parsed from TSV or HTML
		// return as either format
		Parser parse = new Parser();
		try {
			String temp = Files.readString(file);
			if (file.toString().contains(".txt")) {
				return parse.fromTSV(temp);
			}
			if (file.toString().contains(".html")) {
				return parse.fromHTML(temp);
			}
		} catch (IOException ignored) {
		}
		return null;
	}



}
