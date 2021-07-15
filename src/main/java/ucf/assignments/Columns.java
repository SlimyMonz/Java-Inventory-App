/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


public class Columns {

	// REALLY IMPORTANT NOTE FOR GRADER: THIS IS JAVAFX STUFF! Cannot test it properly! There will NOT be a test class for this file!

	public void setDateColumn(TableColumn<InventoryItem, String> valueColumn) {

		// set each cell to a value from InventoryItem's object
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("itemValue"));
		// set the cell as a text field for the entire column
		valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		// each time the data is edited, the InventoryItem object is updated
		valueColumn.setOnEditCommit(
				(javafx.scene.control.TableColumn.CellEditEvent<InventoryItem, String> t) ->
						(t.getTableView().getItems().get(
								t.getTablePosition().getRow())
						).setItemValue(t.getNewValue())
		);
	}

	public void setTextColumn(TableColumn<InventoryItem, String> serialColumn) {

		// set each cell to a value from InventoryItem's todoText object
		serialColumn.setCellValueFactory(new PropertyValueFactory<>("itemSerial"));
		// set the cell as a text field for the entire column
		serialColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		// each time the data is edited, the InventoryItem object is updated
		serialColumn.setOnEditCommit(
				(javafx.scene.control.TableColumn.CellEditEvent<InventoryItem, String> t) ->
						(t.getTableView().getItems().get(
								t.getTablePosition().getRow())
						).setItemSerial(t.getNewValue())
		);
	}

	public void setBoolColumn(TableColumn<InventoryItem, String> descriptionColumn) {

		// set each cell to a value from InventoryItem's bool object
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
		// set the cell as a dropdown field for the entire column
		descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		// each time the data is edited, the InventoryItem object is updated
		descriptionColumn.setOnEditCommit(
				(javafx.scene.control.TableColumn.CellEditEvent<InventoryItem, String> t) ->
						(t.getTableView().getItems().get(
								t.getTablePosition().getRow())
						).setItemDescription(t.getNewValue())
		);
	}


}
