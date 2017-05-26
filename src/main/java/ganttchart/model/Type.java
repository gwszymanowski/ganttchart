package ganttchart.model;


/**
 * Created by gwszymanowski on 2017-05-13.
 */

public enum Type {
    NORMAL("Normal"), IMPORTANT("Important"), URGENT("Urgent");

    private final String desc;

    Type(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return desc;
    }


}
