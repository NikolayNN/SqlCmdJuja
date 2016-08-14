package my.project.juja.model;

import java.util.*;

/**
 * Created by Nikol on 8/14/2016.
 */
public class Handler {

    List<DataSet> sourceData;
    int divider;
    List<DataSet> processedData;
    List<List<DataSet>> splitedDataByDay;


    public List<DataSet> getProcessedData() {
        return processedData;
    }
    public Handler(List<DataSet> sourceData, int divider) {
        this.sourceData = sourceData;
        this.divider = divider;
    }

    public void process (){
        setSplitedDataByDay();
        processedData = new ArrayList<>();
        List<DataSet> dailyValues;
        for (int i = 0; i < splitedDataByDay.size(); i++) {
            dailyValues = splitedDataByDay.get(i);
            Date day = dailyValues.get(0).getDate();
            float valueStart = dailyValues.get(0).getValue();
            float valueFinish = dailyValues.get(dailyValues.size() - 1).getValue();
            float value = (valueFinish - valueStart)/divider;
            processedData.add(new DataSet(day, value));
        }
    }

    private void setSplitedDataByDay(){
        splitedDataByDay = new ArrayList<>();
        for (int i = 0; i < sourceData.size(); i++) {
            int currentDay = sourceData.get(i).getDate().getDay();
            List<DataSet> dailyValues = new ArrayList<>();
            while(i < sourceData.size()){
                if(currentDay == sourceData.get(i).getDate().getDay()) {
                    dailyValues.add(sourceData.get(i));
                    i++;
                }else{
                    break;
                }
            }
            splitedDataByDay.add(dailyValues);
        }
    }




}
