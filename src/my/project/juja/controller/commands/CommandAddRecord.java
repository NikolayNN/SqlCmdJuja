package my.project.juja.controller.commands;

import my.project.juja.view.Console;
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
        while(true) {
            view.writeln("choose neccesary index column for fill");
            if (tempTable.setColumnsToEditIdx(view.read()) == 1){
                break;
            }else{
                view.writeln("Check your input!");
            }
        }
        String command = "";
            while(  command.equalsIgnoreCase(COMMAND_SAVE) ||
                command.equalsIgnoreCase(COMMAND_CANCEL)){
                    //todo
            }
        }
}
