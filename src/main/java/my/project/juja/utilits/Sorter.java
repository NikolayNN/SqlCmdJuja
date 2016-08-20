package my.project.juja.utilits;

import my.project.juja.model.DataSet;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Nikol on 8/17/2016.
 */
public class Sorter {
    public static List<DataSet> sort(List<DataSet> list) {
        list.sort(new Comparator<DataSet>() {
            @Override
            public int compare(DataSet o1, DataSet o2) {
                Date date1 = o1.getDate();
                Date date2 = o2.getDate();
                return date1.compareTo(date2);
            }
        });
        return list;
    }
}
