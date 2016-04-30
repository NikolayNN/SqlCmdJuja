package my.project.juja.controller.commands;

import my.project.juja.view.Console;
import my.project.juja.model.DataBase;

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
        if (DataBase.getConnection() == null){
            view.writeln("ERROR. at first connect to database");
            return;
        }

        if (parametrs.length == 0) {
            view.writeln("input table name!");
            return;
        }
        if (parametrs.length > 1) {
            view.writeln("input correct command: 'tabledata tablename'");
            return;
        }
        String tableName = parametrs[0];
        view.writeln(DataBase.getColumnName(tableName));
        view.writeln("---------------------------");
        ArrayList<String> tableData = DataBase.getTableData(tableName);
        for (String s : tableData) {
            view.writeln(s);
        }
    }
}
