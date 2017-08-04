package ganttchart.serialization.adapter;

import ganttchart.util.FileUtil;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * Created by gwszymanowski on 2017-08-04.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    private LocalDate dateContext;

    public LocalDateAdapter() {
    }

    public LocalDateAdapter(LocalDate dateContext) {
        this.dateContext = dateContext;
    }

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return FileUtil.convertStringToLocalDate(v);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return FileUtil.convertDateToString(v);
    }
}
