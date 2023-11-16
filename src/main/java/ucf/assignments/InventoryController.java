/*
 * Copyright Harry Hocker
 * harry_hocker@icloud.com
 */

package ucf.assignments;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.Objects;

public class InventoryController {

	@FXML
	private TextField searchField;
	@FXML
	private TextField valueField;
	@FXML
	private TextField serialField;
	@FXML
	private TextField nameField;

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
	private TableColumn<InventoryItem, String> nameColumn;

	@FXML
	private FileChooser fileChooser;
	@FXML
	private ManageFile mf;
	@FXML
	private Search sc;
	@FXML
	private Checker check;

	@FXML
	private void initialize() {
		// Initialize instances
		sc = new Search();
		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML",  "*.html"));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TSV",  "*.txt"));
		mf = new ManageFile();
		check = new Checker();

		// Set placeholder text for the tableViewContainer
		tableViewContainer.setPlaceholder(new Label("""
                Use the menu bar above to load a file.
                Check the source files for the README.md
                Use the bar below to start adding items."""));

		// Initialize ObservableLists
		data = FXCollections.observableArrayList();
		dataTemp = FXCollections.observableArrayList();

		// Set up columns
		Columns column = new Columns();
		column.setValueColumn(valueColumn);
		column.setSerialColumn(serialColumn);
		column.setNameColumn(nameColumn);

		tableViewContainer.setItems(data);
		tableViewContainer.getColumns().clear();
		//noinspection unchecked
		tableViewContainer.getColumns().addAll(valueColumn, serialColumn, nameColumn);
	}

	@FXML
	public void clickAddList() {
		// Launch a new app instance
		try {
			new Main().start(new Stage());
		} catch (Exception e) {
			//noinspection CallToPrintStackTrace
			e.printStackTrace();
		}
	}

	public void clickNewItem() {
		tableViewContainer.setItems(data);
		if (check.allValues(data, valueField.getText(), serialField.getText(), nameField.getText())) {
			data.add(new InventoryItem(valueField.getText(), serialField.getText(), nameField.getText()));
			valueField.clear();
			serialField.clear();
			nameField.clear();
		}
	}

	public void clickDeleteItem() {
		int selectedIndex = tableViewContainer.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			tableViewContainer.getItems().remove(selectedIndex);
		}
	}

	public void menuLoadFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File(mf.getFilePath()));
		File file = fileChooser.showOpenDialog(null);

		if (file != null) {
			mf.setFileName(file);
			mf.setFilePath(file);
			String fileString = mf.readFile(file.toPath());
			Parser parse = new Parser();
			data.clear();
			data = parse.stringToList(fileString);
			tableViewContainer.setItems(data);
		}
	}

	public void menuSaveFile() {
		fileChooser.setInitialFileName(mf.getFileName());
		fileChooser.setInitialDirectory(new File(mf.getFilePath()));
		File file = fileChooser.showSaveDialog(new Stage());

		if (file != null) {
			mf.setFileName(file);
			mf.setFilePath(file);
			mf.setStringOfList(this.data);
			mf.writeFile(file.toPath());
		}
	}

	public void menuQuit() {
		Platform.exit();
		System.exit(0);
	}

	public void clickSearch() {
		dataTemp.clear();
		sc.findItem(data, dataTemp, searchField.getText());
		tableViewContainer.setItems(dataTemp);
	}

	public void clickReset() {
		dataTemp.clear();
		tableViewContainer.setItems(data);
		searchField.clear();
	}

	public void clickEditItem() throws IOException {
		int index = tableViewContainer.getSelectionModel().getSelectedIndex();
		dataTemp.clear();
		dataTemp.addAll(data);

		if (index >= 0) {
			FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("EditWindow.fxml")));
			Parent editRoot = loader.load();
			EditController popup = loader.getController();
			setEditPopupValues(popup, index);
			showEditWindow(editRoot);

			if (!popup.nullNewItem()) {
				data.clear();
				data.addAll(dataTemp);
				data.add(popup.getNewItem());
			}
			tableViewContainer.setItems(data);
		}
	}

	private void showEditWindow(Parent editRoot) {
		Stage edit = new Stage();
		edit.setTitle("Edit Item");
		edit.setScene(new Scene(editRoot));
		edit.showAndWait();
	}

	private void setEditPopupValues(EditController popup, Integer index) {
		popup.transferObservableList(dataTemp);
		popup.transferValue(valueColumn.getCellData(index));
		popup.transferSerial(serialColumn.getCellData(index));
		popup.transferName(nameColumn.getCellData(index));
		dataTemp.remove(index.intValue());
	}
}
