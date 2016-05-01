package my.project.juja.controller.commands;

import my.project.juja.model.TempTable;

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
}
