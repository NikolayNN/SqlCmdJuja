package my.project.juja.utilits;

import my.project.juja.model.DataSet;

import java.util.List;

/**
 * Created by Nikol on 8/17/2016.
 */
public class Counter {

    //берет
    public static List<DataSet> makeCounter (List<DataSet> dataSets) {
        float offset = 0;
        for (int i = 1; i < dataSets.size(); i++) {
            float previosValue = dataSets.get(i - 1).getValue();
            float currentValue = dataSets.get(i).getValue();
            if(currentValue + offset < previosValue){
                offset = previosValue;
            }
            DataSet dataSet = addOffset(dataSets.get(i), offset);
            dataSets.set(i, dataSet);
        }





//        if(dataSets.size() == 0){
//            return dataSets;
//        }
//        DataSet previos = dataSets.get(0);
//        DataSet current;
//        float offset = 0;
//        for (int i = 1; i < dataSets.size(); i++) {
//            current = dataSets.get(i);
//                if(previos.getValue() == current.getValue()){
//                    offset = current.getValue();
//                    previos = current;
//                    continue;
//                }
//                    if ((previos.getValue() - offset >= current.getValue())) {
//                        offset = previos.getValue() + current.getValue();
//                    }
//                    current.setValue(offset + current.getValue());
//                    previos = current;
//
//        }
        return dataSets;
    }

    private static DataSet addOffset(DataSet dataSet, float offset) {
        float value = dataSet.getValue() + offset;
        dataSet.setValue(value);
        return dataSet;
    }

}
