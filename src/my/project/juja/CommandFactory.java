package my.project.juja;

import my.project.juja.commands.ComandConnection;
import my.project.juja.commands.Command;
import my.project.juja.commands.CommandExit;
import my.project.juja.commands.CommandWrong;

/**
 * Created by Nikol on 4/13/2016.
 */
public class CommandFactory{
    public Command createCommand(String source){
        String command = source.split(Command.COMMAND_SEPARATOR)[0];

        if(command.equalsIgnoreCase(Command.CONNECTION)){
            return new ComandConnection(source);
        }
        if(command.equalsIgnoreCase(Command.EXIT)){
            return new CommandExit(source);
        }
        return  new CommandWrong(source);
    }

}
