/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;


import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


public class ManageFile {

	private final String defaultPath = System.getProperty("user.home");
	private String filePath;
	private String fileName;
	private String listToString;

	public ManageFile() {
		// initializes the file with a default name
		this.fileName = "default";
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
		// if filePath.isEmpty()
		// return defaultPath
		// else return filePath
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

	public void setListToString(ObservableList<InventoryItem> array) {
		if (this.fileName.contains(".txt")) toTSV(array);
		if (this.fileName.contains(".html")) toHTML(array);

	}

	private void toTSV(ObservableList<InventoryItem> array) {
		StringBuilder string = new StringBuilder();
		string.append("Value" + "\t" + "Serial" + "\t" + "Description" + "\n");
		for (InventoryItem row : array) {
			string.append(row.getItemValue()).append("\t");
			string.append(row.getItemSerial()).append("\t");
			string.append(row.getItemDescription()).append("\n");
		}
		this.listToString = string.toString();
	}

	// this whole thing is a hardcoded mess of HTML
	private void toHTML(ObservableList<InventoryItem> array) {

		StringBuilder string = new StringBuilder();

		// begin table builder
		string.append("<table BORDER=1 CELLSPACING=5>");

		// set caption
		string.append("<caption>").append(this.fileName).append("</caption>");

		// create the head
		string.append("<thead>").append("<tr>");
		string.append("<th>").append("Value").append("</th>");
		string.append("<th>").append("Serial").append("</th>");
		string.append("<th>").append("Description").append("</th>");
		string.append("</thead>").append("</tr>");

		// create the body (where the list data goes)
		string.append("<tbody>");
		for (InventoryItem row : array) {
			// begin row items
			string.append("<tr>");

			string.append("<td>").append(row.getItemValue()).append("</td>");
			string.append("<td>").append(row.getItemSerial()).append("</td>");
			string.append("<td>").append(row.getItemDescription()).append("</td>");

			string.append("</tr>");
		}
		string.append("</tbody>");

		// end table build
		string.append("</table>");
		this.listToString = string.toString();
	}

	// turn the list of objets into a string and use a string builder or whatever, then write the string to files instead
	// IE: HTML file will use <h>string content</h> and such
	// replace ArrayList<InventoryItem> with String and use a different file writer that simply writes strings
public void writeFile(File file) {
	try(FileOutputStream fos = new FileOutputStream(file);
	    BufferedOutputStream output = new BufferedOutputStream(fos)) {
		//convert string to bytes
		byte[] bytes = this.listToString.getBytes();
		//write bytes to file
		output.write(bytes);
	} catch (IOException ignored) {
		// ignore exceptions because they shouldn't get this far lmao
	}
}

	public Object readFile(Path file) {
		// read the data
		// return data as object
		try {
			ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(file));
			return inputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
