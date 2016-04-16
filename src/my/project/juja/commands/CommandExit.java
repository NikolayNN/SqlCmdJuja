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
        if(DataBase.getConnection() == null){
            Console.writeStringln("Goodbye");
            return;
        }
        DataBase.closeConnection();
        Console.writeStringln("Connection to data base was closed");
        Console.writeStringln("Goodbye");
    }
}
