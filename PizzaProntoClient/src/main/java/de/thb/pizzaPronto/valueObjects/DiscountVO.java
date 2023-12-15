package de.thb.pizzaPronto.valueObjects;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DiscountVO {
	private String title;
	private String infoText;
	private LocalDate fromDate;
	private LocalDate toDate;
	private LocalTime fromTime;
	private LocalTime toTime;
	
	public DiscountVO(String title, String infoText, LocalDate fromDate, LocalDate toDate, LocalTime fromTime,
			LocalTime toTime) {
		super();
		this.title = title;
		this.infoText = infoText;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.fromTime = fromTime;
		this.toTime = toTime;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInfoText() {
		return infoText;
	}
	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	public LocalTime getFromTime() {
		return fromTime;
	}
	public void setFromTime(LocalTime fromTime) {
		this.fromTime = fromTime;
	}
	public LocalTime getToTime() {
		return toTime;
	}
	public void setToTime(LocalTime toTime) {
		this.toTime = toTime;
	}
	
	public String getDiscountInfo() {
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
