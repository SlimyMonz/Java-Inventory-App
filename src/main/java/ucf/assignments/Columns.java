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

		// set each cell to a value from InventoryItem's object value string
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("itemValue"));
		// set the cell as a text field for the entire column
		valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());

	}

	public void setTextColumn(TableColumn<InventoryItem, String> serialColumn) {

		// set each cell to a value from InventoryItem's serial object string
		serialColumn.setCellValueFactory(new PropertyValueFactory<>("itemSerial"));
		// set the cell as a text field for the entire column
		serialColumn.setCellFactory(TextFieldTableCell.forTableColumn());

	}

	public void setBoolColumn(TableColumn<InventoryItem, String> descriptionColumn) {

		// set column to a text description from string of object
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
		// set the entire column as a text field
		descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

	}


}
