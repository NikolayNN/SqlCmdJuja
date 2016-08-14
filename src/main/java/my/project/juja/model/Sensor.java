package my.project.juja.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Nikol on 7/25/2016.
 */
public class Sensor {
    long id;
    List<DataSet> sensorData;

    public Sensor(long id) {
        this.id = id;
    }

    public List<DataSet> getSensorData() {
        return sensorData;
    }

    public void setSensorData(DataBase dataBase){
        List<String> sourceLines = readSourceLinesFromDataBase(dataBase);

        List<DataSet> dataSets = new ArrayList<>();
        for (String sourceLine : sourceLines) {
            DataSet dataSet = createDataSet(sourceLine);
            dataSets.add(dataSet);
        }
        sort(dataSets);
        sensorData = check(dataSets);
    }

    private List<String> readSourceLinesFromDataBase(DataBase dataBase){
        dataBase.getConnection();
        List<String> sourceLines = dataBase.getTableData();
        dataBase.closeConnection();
        return sourceLines;
    }

    private DataSet createDataSet(String source) {
        String[] columns = source.split("\\|");

        long id = Long.parseLong(columns[0]);
        long ms = Long.parseLong(columns[1]) * 1000;
        Date date = new Date(ms);
        int sensorValue;
        if(columns.length == 3) {
            String params = columns[2];
            sensorValue = findParamValue("CNT2", params);
        }else{
            sensorValue = 0;
        }
        return new DataSet(date, sensorValue);
    }

    private int findParamValue(String paramKey, String params) {
        String[] paramsArray = splitParams(params);
        for (int i = 0; i < paramsArray.length; i++) {
            if (paramsArray[i].startsWith(paramKey)) {
                int paramValue;
                paramValue = Integer.parseInt(paramsArray[i].substring(5, paramsArray[i].length()));
                return paramValue;
            }
        }
        return 0; // if can't found paramkey return 0
    }

    private String[] splitParams(String params) {
        String[] paramsArray;
        paramsArray = params.split(";");
        return paramsArray;
    }

    private List<DataSet> check(List<DataSet> dataSets) {
        DataSet previos = dataSets.get(0);
        DataSet current;
        float offset = 0;
        for (int i = 0; i < dataSets.size(); i++) {
            current = dataSets.get(i);
            if (previos.getValue() > current.getValue()) {
                offset = previos.getValue();
            }
            current.setValue(offset + current.getValue());
            previos = current;
        }
        return dataSets;
    }

    private void sort(List<DataSet> list) {
        list.sort(new Comparator<DataSet>() {
            @Override
            public int compare(DataSet o1, DataSet o2) {
                Date date1 = o1.getDate();
                Date date2 = o2.getDate();
                return date1.compareTo(date2);
            }
        });
    }



}
