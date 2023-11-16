/*
 * Copyright Harry Hocker
 * harry_hocker@icloud.com
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
        return fileName;
    }

    public void setFileName(File file) {
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

    public void writeFile(Path path) {
        try {
            Files.writeString(path, getStringOfList());
        } catch (IOException ignored) {
        }
    }

    public String readFile(Path file) {
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
