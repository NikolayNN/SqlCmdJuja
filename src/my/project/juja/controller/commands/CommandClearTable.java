package my.project.juja.controller.commands;

import my.project.juja.model.DataBase;

/**
 * Created by Nikol on 4/30/2016.
 */
public class CommandClearTable extends Command {

    public CommandClearTable(String source) {
        super(source);
    }

    @Override
    public void perform() {
        if (countParametrs != 1){
            view.writeln("check your command 'cleartable tableName'");
            return;
        }
        String tableName = parametrs[0];
        DataBase.clearTable(tableName);
        view.writeln(tableName + " has been cleared" );
    }
}
