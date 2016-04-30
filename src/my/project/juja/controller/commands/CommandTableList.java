package my.project.juja.controller.commands;

import my.project.juja.view.Console;
import my.project.juja.model.DataBase;

/**
 * Created by Nikol on 4/13/2016.
 */
public class CommandTableList extends Command {
    public CommandTableList(String fullString) {
        super(fullString);
    }

    @Override
    public void perform() {
        String tableList = DataBase.getTableList();
        view.writeln(tableList);
    }

}
