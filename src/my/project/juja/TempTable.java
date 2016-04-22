package my.project.juja;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nikol on 4/21/2016.
 */
public class TempTable {
    private String tableName;

    private ArrayList<String> columnsNameList;
    private int[] columnsToEditIdx;
    private boolean openToEdit;
    private String columnsNameWithIdx;
    private int tableSize;
    private ArrayList<String> records;

    public TempTable(String tableName) {
        this.tableName = tableName;
        String[] columns = DataBase.getColumnName(tableName).split("\\|");
        this.columnsNameList = new ArrayList<String>(Arrays.asList(columns));
        this.tableSize = columnsNameList.size();
    }

    public String getColumnsNameWithIdx(){
        if (columnsNameWithIdx == null){
            setColumnsNameWithIdx();
        }
        return columnsNameWithIdx;
    }

    private void setColumnsNameWithIdx(){
        columnsNameWithIdx = "";
        for (int i = 0; i < columnsNameList.size(); i++) {
            columnsNameWithIdx += columnsNameList.get(i) + "(" + i + ") ";
        }
    }

    private int[] stringToArrayInt(String s){
        String[] temp = s.split(" ");
        int[] result = new int[temp.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(temp[i]);
        }
        return result;
    }

    public int setColumnsToEditIdx (String s){
        int[] idx = stringToArrayInt(s);
        if(idx.length == 0){
            return -1;
        }

        System.out.println(Arrays.toString(idx));
        for (int i = 0; i < idx.length; i++) {
            if((idx[i] > tableSize) || (idx[i]<0)){
                return -1;
            }
        }

        Arrays.sort(idx);
        for (int i = 0; i <idx.length - 1; i++) {
            if(idx[i] == idx[i+1]){
                return -1;
            }
        }
        columnsToEditIdx = idx;
return 1;
    }

    public int addRecord(String str){
        //todo
    }

//    public int setColumnsToEditIdx (String str){
//        if(str.equalsIgnoreCase("all")){
//            columnsToEditIdx = new int[tableSize];
//            for (int i = 0; i < tableSize; i++) {
//                columnsToEditIdx[i] = i;
//            }
//            return 1;
//        }
//        return -1;
//    }
}