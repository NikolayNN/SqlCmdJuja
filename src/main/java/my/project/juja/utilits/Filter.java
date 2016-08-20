package my.project.juja.utilits;

import my.project.juja.model.DataSet;

import java.util.List;

/**
 * Created by Nikol on 8/17/2016.
 */
public class Filter {

    // ������� ������ �������� ������
    public static List<DataSet> execute(List<DataSet> list){
        Sorter.sort(list);
        removeZeroValueFromBegin(list);
        replaceZeroValues(list);
        for (int i = 1; i < list.size() - 1; i++) {

            if(isMistakeBig(list, i)){
                    list.remove(i);
                }
            }

//            if(currentValue < previosValue){
//                if(nextValue  >= previosValue &&
//                        (nextValue !=0 && previosValue != 0)){
//                    list.remove(i);
//                }
//            }


        return list;
    }

    private static boolean isMistakeBig(List<DataSet> list, int i){
        float previosValue = list.get(i-1).getValue();
        float currentValue = list.get(i).getValue();
        float nextValue = list.get(i+1).getValue();
        if(currentValue > nextValue){
            if(nextValue >= previosValue){
                return true;
            }
        }
        return false;
    }

    private static boolean isMistakeLittle(List<DataSet> list, int i){
        float previosValue = list.get(i-1).getValue();
        float currentValue = list.get(i).getValue();
        float nextValue = list.get(i+1).getValue();
        return currentValue < previosValue && nextValue >= previosValue;
    }



    private static void replaceZeroValues(List<DataSet> list) {
        for (int i = 1; i < list.size(); i++) {
                while ((list.get(i).getValue() == 0) && (i < list.size())){
                    DataSet dataSet = list.get(i);
                    float value = list.get(i - 1).getValue();
                    dataSet.setValue(value);
                    list.set(i, dataSet);
                    i++;
                }
            }
    }

    private static void removeZeroValueFromBegin(List<DataSet> list) {
        while(list.get(0).getValue() == 0){
            list.remove(0);
        }
    }


}
