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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class InventoryController {

	@FXML
	private TextField searchField;
	@FXML
	private TextField valueField;
	@FXML
	private TextField serialField;
	@FXML
	private TextField descriptionField;

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
	@FXML
	private Search sc;
	@FXML
	private Checker check;


	// on app start:

	@FXML
	private void initialize() {

		sc = new Search();
		fileChooser = new FileChooser();
		mf = new ManageFile();
		check = new Checker();

		// change the window text to push user to learn how to use the program
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
		// launch a new app instance
		try {
			new Main().start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void clickNewItem(ActionEvent actionEvent) {

		// makes sure that after using Search that things don't get weird
		tableViewContainer.setItems(data);

		// create an if statement that uses a method to check all the values are valid or not
		if (check.allValues(data,
		                    valueField.getText(),
		                    serialField.getText(),
		                    descriptionField.getText())) {
			// then if valid:
			// add new object with the values selected in the bottom bar containers
			data.add(new InventoryItem(
					valueField.getText(),
					serialField.getText(),
					descriptionField.getText()));

			// reset the fields
			valueField.clear();
			serialField.clear();
			descriptionField.clear();
		}

		// else if it's not valid, do nothing
	}


	public void clickDeleteItem(ActionEvent actionEvent) {
		// if row is selected:
		// delete selected row at tableView index
		int selectedIndex = tableViewContainer.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			tableViewContainer.getItems().remove(selectedIndex);
		}
	}


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


	public void menuSaveFile(ActionEvent actionEvent) {
		// Path path = ManageFile.getFilePath()
		// run ManageFile.saveFile(path)
		// use java FileChooser <----- IMPORTANT !!!!

		ArrayList<InventoryItem> listOfItems = new ArrayList<>(data);

		fileChooser.setInitialFileName(mf.getFileName());

		fileChooser.setInitialDirectory(new File(mf.getFilePath()));

		File file = fileChooser.showSaveDialog(new Stage());

		// if the file isn't empty/null, run three methods to save file
		if (file != null) {
			mf.setFileName(file);
			mf.setFilePath(file);
			mf.writeFile(file, listOfItems);
		}

	}


	public void menuQuit(ActionEvent actionEvent) {
		// prompt to save before quitting
		// Application.stop() to quit app
		Platform.exit();
		System.exit(0);
	}


	public void clickAbout(ActionEvent actionEvent) {
		// display About class popup
		About popup = new About();
		popup.displayPopup();
	}


	public void clickSearch(ActionEvent actionEvent) {
		// make sure dataTemp is clear
		dataTemp.clear();

		// add method for searching in here somewhere
		sc.findItem(data, dataTemp, searchField.getText());

		tableViewContainer.setItems(dataTemp);
	}


	public void clickReset(ActionEvent actionEvent) {
		dataTemp.clear();
		tableViewContainer.setItems(data);
		searchField.clear();
	}

	public void clickEditItem(ActionEvent actionEvent) throws IOException {

		// find index of selected item
		int index = tableViewContainer.getSelectionModel().getSelectedIndex();

		if (index >= 0) {

			// create new edit window
			FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("EditWindow.fxml")));
			Parent editRoot = loader.load();

			// get controller of popup
			EditController popup = loader.getController();

			// transfer data to popup based on index
			setEditPopupValues(popup, index);

			// display the popup
			Stage edit = new Stage();
			edit.setTitle("Edit Item");
			edit.setScene(new Scene(editRoot));
			edit.showAndWait();



			// removes the previous item to add the edited one
			//tableViewContainer.getItems().remove(index);

		}

	}

	private void setEditPopupValues(EditController popup, Integer index) {
		popup.transferObservableList(data);
		popup.transferValue(valueColumn.getCellData(index));
		popup.transferSerial(serialColumn.getCellData(index));
		popup.transferDescription(descriptionColumn.getCellData(index));
	}

}
