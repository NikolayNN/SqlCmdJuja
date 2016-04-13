package my.project.juja;

import my.project.juja.commands.Command;

/**
 * Created by Nikol on 4/12/2016.
 */
public class DataBaseManager {
    public static void main(String[] args) {
        Console console = new Console();
        CommandFactory commandFactory = new CommandFactory();
        console.writeString("Hello");
        String source = "";
        while (!source.equalsIgnoreCase(Command.EXIT)){
        console.writeString("please, input your command or 'help'");
            source = console.readString();
            Command command = commandFactory.createCommand(source);
            command.perform();
        }

    }


}

