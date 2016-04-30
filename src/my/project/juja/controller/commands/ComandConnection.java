package my.project.juja.controller.commands;

import my.project.juja.view.Console;
import my.project.juja.model.DataBase;
import my.project.juja.view.View;

/**
 * Created by Nikol on 4/13/2016.
 */
public class ComandConnection extends Command {
    private final String commandSample = "connect dbname login password";
    private int commandSize;
    {
        commandSize = commandSample.split(Command.SEPARATOR).length-1;
    }
    public ComandConnection(String source) {
        super(source);
    }

    @Override
    public void perform() {
        View view = new Console();
        if((parametrs.length != commandSize)){
            view.writeln("command 'connect' must have 3 parameters: dbName login password");
            return;
        }
        String dbName = parametrs[0];
        String login = parametrs[1];
        String password = parametrs[2];
            DataBase.getConnection(dbName, login, password);
        view.writeln(MESSAGE_COMMAND_PERFORMED_SUCCESSFUL);
    }
}
