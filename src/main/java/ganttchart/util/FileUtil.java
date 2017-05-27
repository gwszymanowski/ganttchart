package ganttchart.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by gwszymanowski on 2017-05-27.
 */
public class FileUtil {

    private FileUtil(){

    }

    public static String convertDateToString(LocalDateTime date) {
        if(date == null)
            return "null";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return date.format(formatter);
    }

    public static LocalDateTime convertStringToLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDateTime.parse(date, formatter);
    }

}
