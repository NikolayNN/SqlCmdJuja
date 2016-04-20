package my.project.juja.commands;

import my.project.juja.DataBase;

import java.util.Arrays;

/**
 * Created by Nikol on 4/16/2016.
 */
public class CommandAddRecord extends Command {

    public CommandAddRecord(String fullString) {
        super(fullString);
    }

    @Override
    public void perform() {
        String tableName = parametrs[0];
        String[] value = new String[parametrs.length - 1];
        int j = 0;

        System.arraycopy(parametrs,1,value,0,value.length);
        DataBase.addRecord(tableName, value);
    }
}
