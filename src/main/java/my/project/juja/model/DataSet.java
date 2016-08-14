package my.project.juja.model;

import java.util.Date;

/**
 * Created by Nikol on 7/25/2016.
 */
public class DataSet {
    Date date;
    float value;

    public Date getDate() {
        return date;
    }

    public DataSet(Date date, float value) {
        this.date = date;
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
                "date=" + date +
                ", value=" + value +
                '}';
    }
}
