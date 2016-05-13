package my.project.juja.controller.commands;

import my.project.juja.model.Storeable;
import my.project.juja.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikol on 4/13/2016.
 */
public class CommandTableList extends Command {
    public static final String name = Command.TABLE_LIST;

    public CommandTableList(Storeable store, View view) {
        super(store, view);
    }

    @Override
    public void perform() {
        List<String> tableList = store.getTableList();
        view.writeln(tableList.toString());
    }

    @Override
    public String getName() {
        return name;
    }

}
