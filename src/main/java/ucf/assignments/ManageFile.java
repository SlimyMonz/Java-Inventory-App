/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;


import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ManageFile {

	private final String defaultPath = System.getProperty("user.home");
	private String filePath;
	private String fileName;


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


	public void writeFile(File file, ArrayList<InventoryItem> data) {
		// get file and object data from parameter
		// write data to a new file based on file path
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(file.toPath()));
			outputStream.writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
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
