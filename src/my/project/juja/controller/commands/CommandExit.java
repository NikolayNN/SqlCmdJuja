package my.project.juja.controller.commands;

import my.project.juja.model.Storeable;
import my.project.juja.view.View;

/**
 * Created by Nikol on 4/13/2016.
 */
public class CommandExit extends Command{
    public static final String name = Command.EXIT;
    public CommandExit(Storeable store, View view) {
        super(store, view);
    }

    @Override
    public void perform() {

        if(store.getConnection() == null){
            view.writeln("Goodbye");
            return;
        }
        store.closeConnection();
        view.writeln("Connection to data base was closed");
        view.writeln("Goodbye");
    }

    @Override
    public String getName() {
        return name;
    }
}
