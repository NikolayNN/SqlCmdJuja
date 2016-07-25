package my.project.juja.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nikol on 7/25/2016.
 */
public class Sensor {
    long id;
    List<DataSet> dataSets;

    public long getId() {
        return id;
    }

    public List<DataSet> getDataSets() {
        return dataSets;
    }

    public Sensor(long id, DataBase dataBase) {
        this.id = id;
        this.dataSets = createDataSetList(dataBase);
    }

    private List<DataSet> createDataSetList(DataBase dataBase){
        dataBase.getConnection();
        List<String> sourceLines = dataBase.getTableData();
        dataBase.closeConnection();
        List<DataSet> dataSets = new ArrayList<>();
        for (String sourceLine : sourceLines) {
            DataSet dataSet = createDataSet(sourceLine);
            dataSets.add(dataSet);
        }
        return check(dataSets);
    }

    private DataSet createDataSet(String source) {
        String[] columns = source.split("\\|");
        long id = Long.parseLong(columns[0]);
        long ms = Long.parseLong(columns[1]) * 1000;
        Date date = new Date(ms);
        String params = columns[2];
        int sensorValue = findParamValue("CNT2", params);
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
        return -1;
    }

    private String[] splitParams(String params) {
        String[] paramsArray;
        paramsArray = params.split(";");
        return paramsArray;
    }

    private List<DataSet> check(List<DataSet> dataSets) {
        DataSet previos = dataSets.get(0);
        DataSet current;
        int offset = 0;
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



}
