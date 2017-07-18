package ganttchart.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Created by gwszymanowski on 2017-05-27.
 */
public final class FileUtil {

    private FileUtil(){

    }

    public static String convertDateToString(LocalDate date) {
        if(date == null)
            return "null";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return date.format(formatter);
    }

    public static LocalDate convertStringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }

    public static LocalDate convertStringToLocalDateUSFormat(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

    public static String concatenateString(String ... val) {
        String[] arr = val;
        StringBuilder sb = new StringBuilder();

        for(String s : arr)
            sb.append(s);

        return sb.toString();
    }

}
