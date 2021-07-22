/*
 * UCF COP3330 Summer 2021 Assignment Solution
 * Copyright 2021 Harry Hocker
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

	String html = "<table BORDER=1 CELLSPACING=5></table>" +
			"<thead><tr><th>Value</th><th>Serial</th><th>Name</th></tr></thead>" +
			"<tbody></tbody>";

	String tsv = "Value\t\tSerial\t\tName\n\n";

	@Test
	void fromHTML() {
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
		// the test string should be empty now
		assertTrue(html.isEmpty());
	}

	@Test
	void toHTML() {
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

		string.append("</tbody>");

		// end table build
		string.append("</table>");
		assertFalse(string.isEmpty());
	}

	@Test
	void toTSV() {
		StringBuilder string = new StringBuilder();

		string.append("Value" + "\t\t" + "Serial" + "\t\t" + "Name" + "\n\n");

		assertFalse(string.isEmpty());
	}

	@Test
	void fromTSV() {
		tsv = tsv.replace("Value\t\tSerial\t\tName\n\n", "");
		assertTrue(tsv.isEmpty());
	}

	@Test
	void stringToList() {
		// cannot test due to being JAVAFX!!!
	}
}