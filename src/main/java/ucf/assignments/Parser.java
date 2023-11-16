/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Parser {

	public String fromHTML(String html) {

		html = html.replace("<table BORDER=1 CELLSPACING=5>", "");
		html = html.replace("</table>", "");
		html = html.replace("<thead><tr>" +
				                    "<th>Value</th>" +
				                    "<th>Serial</th>" +
				                    "<th>Name</th>" +
				                    "</tr></thead>", "");
		html = html.replace("<tbody>", "");
		html = html.replace("</tbody>", "");


		while (html.contains("<tr>")) {

			html = html.replaceFirst("<tr>", "");

			for (int i = 0; i < 2; i++) {
				html = html.replaceFirst("<td>", "");
				html = html.replaceFirst("</td>", "#");
			}
			html = html.replaceFirst("<td>", "");
			html = html.replaceFirst("</td>", "");

			html = html.replaceFirst("</tr>", "\n");
		}
		return html;
	}


	public String toHTML(ObservableList<InventoryItem> array) {

		StringBuilder string = new StringBuilder();

		// begin table builder
		string.append("<table BORDER=1 CELLSPACING=5>");

		// create the head
		string.append("<thead><tr>" +
				              "<th>Value</th>" +
				              "<th>Serial</th>" +
				              "<th>Name</th>" +
				              "</tr></thead>");

		// create the body (where the list data goes)
		string.append("<tbody>");
		for (InventoryItem row : array) {
			// begin row items
			string.append("<tr>");

			string.append("<td>").append(row.getItemValue()).append("</td>");
			string.append("<td>").append(row.getItemSerial()).append("</td>");
			string.append("<td>").append(row.getItemName()).append("</td>");

			string.append("</tr>");
		}
		string.append("</tbody>");

		// end table build
		string.append("</table>");
		return string.toString();
	}

	public String toTSV(ObservableList<InventoryItem> array) {

		StringBuilder string = new StringBuilder();

		string.append("""
                Value\t\tSerial\t\tName

                """);
		for (InventoryItem row : array) {
			string.append(row.getItemValue()).append("\t\t");
			string.append(row.getItemSerial()).append("\t\t");
			string.append(row.getItemName()).append("\n");
		}
		return string.toString();
	}

	public String fromTSV(String tsv) {
		tsv = tsv.replace("Value\t\tSerial\t\tName\n\n", "");
		tsv = tsv.replace("\t\t", "#");
		return tsv;
	}

	public ObservableList<InventoryItem> stringToList(String string) {

		ObservableList<InventoryItem> newList = FXCollections.observableArrayList();
		String[] initialSplit = string.split("\n");

		for (String row : initialSplit) {
			String[] eachSplit = row.split("#");
			newList.add(new InventoryItem(
					eachSplit[0],
					eachSplit[1],
					eachSplit[2]
			));
		}
		return newList;
	}

}
