package de.thb.pizzaPronto.menu.data;

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
    private static int nextId = 1;

    private int id;
    private String title;
    private String infoText;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalTime fromTime;
    private LocalTime toTime;


    public DiscountVO(int id){
        setId(id);
    }
    public void resetId(){
        setId(nextId++);
    }

    public String toString() {
        return (infoText != null ? infoText : "") +
                (fromDate != null ? " from " + fromDate.format(DateTimeFormatter.ofLocalizedDate(java.time.format.FormatStyle.SHORT)) : "") +
                (toDate != null ? " to " + toDate.format(DateTimeFormatter.ofLocalizedDate(java.time.format.FormatStyle.SHORT)) : "") +
                (fromTime != null ? " from " + fromTime.format(DateTimeFormatter.ofLocalizedTime(java.time.format.FormatStyle.SHORT)) : "") +
                (toTime != null ? " to " + toTime.format(DateTimeFormatter.ofLocalizedTime(java.time.format.FormatStyle.SHORT)) : "");
    }

}