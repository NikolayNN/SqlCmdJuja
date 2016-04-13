package my.project.juja.commands;

import my.project.juja.DataBase;
import my.project.juja.Console;

/**
 * Created by Nikol on 4/13/2016.
 */
public class CommandExit extends Command{

    public CommandExit(String fullString) {
        super(fullString);
    }

    @Override
    public void perform() {
        Console console = new Console();
        if(DataBase.connectToDataBase() == null){
            console.writeString("Goodbye");
            return;
        }
        DataBase.closeConnection();
        console.writeString("Connection to data base was closed");
        console.writeString("Goodbye");
    }
}
