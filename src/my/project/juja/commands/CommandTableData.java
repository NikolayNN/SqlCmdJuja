package my.project.juja.commands;

import my.project.juja.Console;
import my.project.juja.DataBase;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Nikol on 4/13/2016.
 */
public class CommandTableData extends Command{

    public CommandTableData(String fullString) {
        super(fullString);
    }

    @Override
    public void perform() {
        if (parametrs.length == 0) {
            Console.writeStringln("input table name!");
            return;
        }
        if (parametrs.length > 1) {
            Console.writeStringln("input correct command 'tabledata tablename'");
            return;
        }
        String tableName = parametrs[0];
        Console.writeStringln(DataBase.getColumnName(tableName));
        Console.writeStringln("---------------------------");
        ArrayList<String> tableData = DataBase.getTableData(tableName);
        for (String s : tableData) {
            Console.writeStringln(s);
        }
    }
}
