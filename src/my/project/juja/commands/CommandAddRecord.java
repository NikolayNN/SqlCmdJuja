package my.project.juja.commands;

import my.project.juja.Console;
import my.project.juja.DataBase;
import my.project.juja.TempTable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nikol on 4/16/2016.
 */
public class CommandAddRecord extends Command {

    private static final String COMMAND_SAVE = "save";
    private static final String COMMAND_CANCEL = "cancel";

    public CommandAddRecord(String fullString) {
        super(fullString);
    }
    @Override
    public void perform() {
        String tableName = parametrs[0];
        TempTable tempTable = new TempTable(tableName);
        Console.writeStringln(tempTable.getColumnsNameWithIdx());
        while(true) {
            Console.writeStringln("choose neccesary index column for fill");
            if (tempTable.setColumnsToEditIdx(Console.readString()) == 1){
                break;
            }else{
                Console.writeStringln("Check your input!");
            }
        }
        String command = "";
        while(  command.equalsIgnoreCase(COMMAND_SAVE) ||
                command.equalsIgnoreCase(COMMAND_CANCEL)){
            
        }
        }






//        String[] value = new String[parametrs.length - 1];
//        int j = 0;
//        System.arraycopy(parametrs,1,value,0,value.length);
//        DataBase.addRecord(tableName, value);
}
