package my.project.juja.controller.commands;

import my.project.juja.controller.commands.Command;
import my.project.juja.model.Storeable;
import my.project.juja.model.TempTable;
import my.project.juja.view.View;

/**
 * Created by Nikol on 4/16/2016.
 */
public class CommandAddRecord extends Command {
    public static final String name = Command.ADD_RECORD;
    private static final int EXPECTED_COUNT_PARAMETERS = 1;
    private static final String COMMAND_SAVE = "save";
    private static final String COMMAND_CANCEL = "cancel";

    public CommandAddRecord(Storeable dataBase,View view) {
        super(dataBase, view);
    }
    


    @Override
    public void perform() {
        checkCountParameters(parametrs, EXPECTED_COUNT_PARAMETERS);
        String tableName = parametrs[0];
        TempTable tempTable = new TempTable(store, tableName);

        view.writeln(tempTable.getColumnsNameWithIdx());
        view.writeln("choose neccesary index column to fill");

        String columnsToEdit = view.read();
        tempTable.setColumnsToEditIdx(columnsToEdit);

        view.writeln("input values for columns");
        String command;
        while(true) {
            command = view.read();
            if(command.equalsIgnoreCase(COMMAND_SAVE)){
                tempTable.saveTable();
                break;
            }
            if(command.equalsIgnoreCase(COMMAND_CANCEL)){
                tempTable.clearTable();
                break;
            }
            String tableLine = command;
            tempTable.addTableLine(tableLine);
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
