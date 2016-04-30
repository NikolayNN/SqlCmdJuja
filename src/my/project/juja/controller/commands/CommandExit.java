package my.project.juja.controller.commands;

import my.project.juja.model.DataBase;
import my.project.juja.view.Console;
import my.project.juja.view.View;

/**
 * Created by Nikol on 4/13/2016.
 */
public class CommandExit extends Command{

    public CommandExit(String fullString) {
        super(fullString);
    }

    @Override
    public void perform() {

        if(DataBase.getConnection() == null){
            view.writeln("Goodbye");
            return;
        }
        DataBase.closeConnection();
        view.writeln("Connection to data base was closed");
        view.writeln("Goodbye");
    }
}
