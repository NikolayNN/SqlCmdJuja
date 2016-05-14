package my.project.juja.controller.commands;

import my.project.juja.model.Storeable;
import my.project.juja.model.TempTable;
import my.project.juja.view.Console;
import my.project.juja.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nikol on 4/16/2016.
 */
public class CommandAddRecord extends Command {
    public static final String name = Command.ADD_RECORD;
    private static final int EXPECTED_COUNT_PARAMETERS = 1;
    private static final String SUB_COMMAND_SAVE = "save";
    private static final String SUB_COMMAND_CANCEL = "cancel";
    private int countColumnsToEdit;

    public CommandAddRecord(Storeable dataBase,View view) {
        super(dataBase, view);
    }

    @Override
    public void perform() {
        checkCountParameters(parametrs, EXPECTED_COUNT_PARAMETERS);
        String tableName = parametrs[0];
        TempTable tempTable = new TempTable(store, tableName);
        view.writeln(tempTable.getColumnsNameWithIdx());
        view.writeln("choose neccesary index column to fill, through space. Example \"0 1 2 3\"");
        while(true) {
            try {
                tempTable.setColumnsToEditIdx(receiveColumnToEdit(view.read(), tempTable));
                break;
            } catch (RuntimeException e) {
                view.writeln(e.getMessage());
            }
        }
        view.writeln("input values for columns, available \"cancel\" and \"save\" commands");
        String command;
        while(true) {
            command = view.read();
            if(command.equalsIgnoreCase(SUB_COMMAND_SAVE)){
                tempTable.saveTable();
                view.writeln("records saved!");
                break;
            }
            if(command.equalsIgnoreCase(SUB_COMMAND_CANCEL)){
                tempTable.clearTable();
                view.writeln("canceled!");
                break;
            }
            String tableLine = command;
            try {
                checkCountParameters(command.split(" "), countColumnsToEdit);
            } catch (RuntimeException e) {
                view.writeln("check count values. Expected " + countColumnsToEdit + ", but found " + command.split(" ").length);
                tableLine = null;
            }
            tempTable.addTableLine(tableLine);
        }
    }

    private String receiveColumnToEdit(String source, TempTable tempTable) {
        //выбрать колонки для редактирования
        if(!isOnlySpaceAndNumbs(source)){
            throw new RuntimeException("ERROR. This command may consist only numbs " +
                    "throught spaces. Example \"0 1 2 3\"");
        }
        if(!isValidIndexes(source, tempTable)){
            int maxColumnIndex = tempTable.getColumnCount() - 1;
            throw new RuntimeException( "ERROR. You input incorrect column index. " +
                                        "valid index >= 0, and <= " + maxColumnIndex );
        }
        return source;
    }

    private boolean isValidIndexes(String source, TempTable tempTable){
        int[] columnsIndexes = StringToIntArray(source);
        int maxColumnIndex = tempTable.getColumnCount() - 1;
        for (int i = 0; i < columnsIndexes.length; i++) {
            if((columnsIndexes[i] < 0) || (columnsIndexes[i] > maxColumnIndex) ){
                return false;
            }
        }
        countColumnsToEdit = columnsIndexes.length;
        return true;
    }

    private int[] StringToIntArray(String source){
        String[] strArray = source.split(" ");
        int[] result = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            result[i] = Integer.parseInt(strArray[i]);
        }
        return result;
    }

    private boolean isOnlySpaceAndNumbs(String line) {
        Pattern p = Pattern.compile("^[0-9\\s-]{3,60}$");
        Matcher m = p.matcher(line);
        return m.matches();
    }

    @Override
    public String getName() {
        return name;
    }
}
