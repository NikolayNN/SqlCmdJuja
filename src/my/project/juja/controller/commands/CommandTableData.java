package my.project.juja.controller.commands;

import my.project.juja.model.Storeable;
import my.project.juja.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikol on 4/13/2016.
 */
public class CommandTableData extends Command{
    public static final String name = Command.TABLE_DATA;
    private static final int COUNT_PARAMETERS = 1;

    public CommandTableData(Storeable store, View view) {
        super(store, view);
    }

    @Override
    public void perform() {
        List<String> columnNames;
        checkCountParameters(parametrs,COUNT_PARAMETERS);
        String tableName = parametrs[0];
        columnNames = store.getColumnName(tableName);
        for (String columnName : columnNames) {
            view.write(columnName + "|");
        }
        view.writeln("---------------------------");
        List<String> tableData = store.getTableData(tableName);
        for (String s : tableData) {
            view.writeln(s);
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
