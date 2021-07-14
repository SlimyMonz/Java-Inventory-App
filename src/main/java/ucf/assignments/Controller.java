/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;


public class Controller {

	@FXML
	public TextField searchField;
	@FXML
	public TextField valueField;
	@FXML
	public TextField serialField;
	@FXML
	public TextField descriptionField;

	@FXML
	private TableView<InventoryItem> tableViewContainer;
	@FXML
	private ObservableList<InventoryItem> data;
	@FXML
	private ObservableList<InventoryItem> dataTemp;

	@FXML
	private TableColumn<InventoryItem, String> valueColumn;
	@FXML
	private TableColumn<InventoryItem, String> serialColumn;
	@FXML
	private TableColumn<InventoryItem, String> descriptionColumn;

	@FXML
	private FileChooser fileChooser;
	@FXML
	private ManageFile mf;


	// on app start:

	@FXML
	public void initialize() {

		fileChooser = new FileChooser();
		mf = new ManageFile();

		// make tableViewContainer editable anytime
		tableViewContainer.setEditable(true);
		tableViewContainer.setPlaceholder(new Label("Check out HELP menu above for info."));

		// make todoLists an FXCollections with an observable array list
		data = FXCollections.observableArrayList();
		dataTemp = FXCollections.observableArrayList();


		// use Columns class to set column data types
		Columns column = new Columns();

		column.setDateColumn(valueColumn);
		column.setTextColumn(serialColumn);
		column.setBoolColumn(descriptionColumn);


		// set items for listViewContainer from ObservableList
		tableViewContainer.setItems(data);

		// clear columns to make sure container is empty, then add all the columns to the container
		tableViewContainer.getColumns().clear();
		tableViewContainer.getColumns().addAll(valueColumn, serialColumn, descriptionColumn);

	}

	// on user button press:

	@FXML
	public void clickAddList(ActionEvent actionEvent) {
		// launch a new TodoList app

		try {
			new Main().start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@FXML
	public void clickNewItem(ActionEvent actionEvent) {

		// add new object with the values selected in the bottom bar containers
		data.add(new InventoryItem(
				valueField.getText(),
				serialField.getText(),
				descriptionField.getText()));

		// reset the valueField
		valueField.clear();

		// reset serialField
		serialField.clear();

		// reset descriptionField
		descriptionField.clear();
	}

	public void clickNewDefaultItem(ActionEvent actionEvent) {
		// add new object with the values selected in the bottom bar containers
		data.add(new InventoryItem());
	}

	public void clickDeleteItem(ActionEvent actionEvent) {
		// if TodoItem is selected:
		// delete selected item at tableView index
		// refresh column views
		int selectedIndex = tableViewContainer.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			tableViewContainer.getItems().remove(selectedIndex);
		}
	}

	@FXML
	public void menuLoadFile(ActionEvent actionEvent) {

		FileChooser fileChooser = new FileChooser();

		fileChooser.setInitialDirectory(new File(mf.getFilePath()));

		//if file has been chosen, load it
		File file = fileChooser.showOpenDialog(null);

		if (file != null) {
			mf.setFileName(file);
			mf.setFilePath(file);

			Object loadFile = mf.readFile(file.toPath());

			// clear all the current items
			data.clear();
			// add all the loaded items
			data.addAll((ArrayList<InventoryItem>) loadFile);
		}

	}

	@FXML
	public void menuSaveFile(ActionEvent actionEvent) {
		// Path path = ManageFile.getFilePath()
		// run ManageFile.saveFile(path)
		// use java FileChooser <----- IMPORTANT !!!!

		ArrayList<InventoryItem> listofItems = new ArrayList<>(data);

		fileChooser.setInitialFileName(mf.getFileName());

		fileChooser.setInitialDirectory(new File(mf.getFilePath()));

		File file = fileChooser.showSaveDialog(new Stage());

		// if the file isn't empty/null, run three methods to save file
		if (file != null) {
			mf.setFileName(file);
			mf.setFilePath(file);
			mf.writeFile(file, listofItems);
		}

	}

	@FXML
	public void menuQuit(ActionEvent actionEvent) {
		// prompt to save before quitting
		// Application.stop() to quit app
		Platform.exit();
		System.exit(0);
	}

	@FXML
	public void clickAbout(ActionEvent actionEvent) {
		// display About class popup
		About.displayPopup();
	}

	@FXML
	public void clickSearch(ActionEvent actionEvent) {
		dataTemp.clear();
		dataTemp.addAll(data);

		// add method for searching in here somewhere

	}
}
