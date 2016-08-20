package my.project.juja.model;

import my.project.juja.utilits.Counter;
import my.project.juja.utilits.Filter;

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

    public void setSensorData(DataBase dataBase) {
        List<String> sourceLines = readSourceLinesFromDataBase(dataBase);

        List<DataSet> dataSets = new ArrayList<>();
        for (String sourceLine : sourceLines) {
                DataSet dataSet = createDataSet(sourceLine);
                dataSets.add(dataSet);
        }
        sensorData = Filter.execute(dataSets);
        sensorData = Counter.makeCounter(dataSets);
    }

    private List<String> readSourceLinesFromDataBase(DataBase dataBase) {
        dataBase.getConnection();
        List<String> sourceLines = dataBase.getTableData(id);
        dataBase.closeConnection();
        return sourceLines;
    }

    private DataSet createDataSet(String source) {
        String[] columns = source.split("\\|");

        long id = Long.parseLong(columns[0]);
        long ms = Long.parseLong(columns[1]) * 1000;
        Date date = new Date(ms);
        int sensorValue;
        String params ="";
        if (columns.length == 3) {
            params = columns[2];
            sensorValue = findParamValue("CNT2", params);
        } else {
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
        return 0;
    }

    private String[] splitParams(String params) {
        String[] paramsArray;
        paramsArray = params.split(";");
        return paramsArray;
    }
}



