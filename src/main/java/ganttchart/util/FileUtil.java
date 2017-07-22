package ganttchart.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Created by gwszymanowski on 2017-05-27.
 */
public final class FileUtil {

    private static final String[] formats = {
            "dd-MM-yyyy",   "yyyy-MM-dd"
    };

    private FileUtil(){}

    public static String convertDateToString(LocalDate date) {
        if(date == null)
            return "null";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return date.format(formatter);
    }

    public static LocalDate convertStringToLocalDate(String date) {
        LocalDate newDate = null;

        if (date != null)
            for (String parse : formats) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(parse);
                try {
                    newDate = LocalDate.parse(date, formatter);
                } catch (DateTimeParseException e) {
                }
            }

        return newDate;
    }



    public static String concatenateString(String ... val) {
        StringBuilder sb = new StringBuilder();

        for(String s : val)
            sb.append(s);

        return sb.toString();
    }

}
