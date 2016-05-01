package my.project.juja.model;
import my.project.juja.controller.commands.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nikol on 4/21/2016.
 */
public class TempTable {
    private String tableName;
    private int tableSize;

    private List<String> columnsNameList;
    private int[] columnsToEditIdx;
    private String columnsNameWithIdx;
    private List<String> records;
    {
        records = new ArrayList<>();
    }

    public TempTable(String tableName) {
        this.tableName = tableName;
        String[] columns = DataBase.getColumnName(tableName).split("\\|");
        this.columnsNameList = new ArrayList<>(Arrays.asList(columns));
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
        String[] temp = s.split(Command.SEPARATOR);
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
        for (int i = 0; i < idx.length; i++) {
            if((idx[i] > tableSize) || (idx[i]<0)){
                return -1;
            }
        }

        Arrays.sort(idx);
        for (int i = 0; i < idx.length - 1; i++) {
            if(idx[i] == idx[i+1]){
                return -1;
            }
        }
        columnsToEditIdx = idx;
        return 1;
    }

    public void addTableLine(String line){
        System.out.println(line);
        records.add(line);
    }

    public void saveTable(){
        String columnsToEdit = "";
        for (int i = 0; i < columnsToEditIdx.length; i++) {
            columnsToEdit += columnsNameList.get(columnsToEditIdx[i]) + Command.SEPARATOR;
        }

        for (int i = 0; i < records.size(); i++) {
            DataBase.addRecord(tableName, columnsToEdit, records.get(i));
        }

    }

    public void clearTable(){
        columnsNameList = null;
        columnsToEditIdx = null;
        columnsNameWithIdx = null;
        tableSize = 0;
        records = null;
    }


}