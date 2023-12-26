package de.thb.pizzaPronto.menu.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DiscountVO {
	private String title;
	private String infoText;
	private LocalDate fromDate;
	private LocalDate toDate;
	private LocalTime fromTime;
	private LocalTime toTime;

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append( title != null ? title + "\n": "");
		sb.append(infoText != null ? infoText : "");
		sb.append(fromDate != null ? " from " + fromDate.format(DateTimeFormatter.ofLocalizedDate(java.time.format.FormatStyle.SHORT)) : "");
		sb.append(toDate != null ? " to " + toDate.format(DateTimeFormatter.ofLocalizedDate(java.time.format.FormatStyle.SHORT)) : "");
		sb.append(fromTime != null ? " from " + fromTime.format(DateTimeFormatter.ofLocalizedTime(java.time.format.FormatStyle.SHORT)) : "");
		sb.append(toTime != null ? " to " + toTime.format(DateTimeFormatter.ofLocalizedTime(java.time.format.FormatStyle.SHORT)) : "");
		return sb.toString();
	}

}
