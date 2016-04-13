package my.project.juja;

import my.project.juja.commands.*;

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
        if (command.equalsIgnoreCase(Command.TABLE_LIST)){
            return new CommandTableList(source);
        }
        return  new CommandWrong(source);
    }

}
