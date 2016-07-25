package my.project.juja.model;

import java.util.Date;

/**
 * Created by Nikol on 7/25/2016.
 */
public class DataSet {
    Date date;
    int value;

    public DataSet(Date date, int value) {
        this.date = date;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DataSet{" +
                "date=" + date +
                ", value=" + value +
                '}';
    }
}
