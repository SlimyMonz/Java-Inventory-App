/*
 * Copyright Harry Hocker
 * harry_hocker@icloud.com
 */

package ucf.assignments;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class Columns {

    public void setValueColumn(TableColumn<InventoryItem, String> valueColumn) {
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("itemValue"));
        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public void setSerialColumn(TableColumn<InventoryItem, String> serialColumn) {
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("itemSerial"));
        serialColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public void setNameColumn(TableColumn<InventoryItem, String> NameColumn) {
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        NameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }
}
