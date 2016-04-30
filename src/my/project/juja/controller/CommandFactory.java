package my.project.juja.controller;

import my.project.juja.controller.commands.*;

/**
 * Created by Nikol on 4/13/2016.
 */
public class CommandFactory{
//    private final  Command[] supportedCommands;
//    {
//        supportedCommands = new String[]{   Command.CONNECTION,
//                                            Command.EXIT,
//                                            Command.TABLE_LIST,
//                                            Command.TABLE_DATA,
//                                            Command.ADD_RECORD};
//    }

    public Command createCommand(String source){
        String command = source.split(Command.SEPARATOR)[0];


//        for (int i = 0; i < supportedCommands.length; i++) {
//            if(command.equalsIgnoreCase(supportedCommands[i])){
//                return new
//            }
//        }
        if(command.equalsIgnoreCase(Command.CONNECTION)){
            return new ComandConnection(source);
        }
        if(command.equalsIgnoreCase(Command.HELP)){
            return new CommandHelp(source);
        }
        if(command.equalsIgnoreCase(Command.EXIT)){
            return new CommandExit(source);
        }
        if (command.equalsIgnoreCase(Command.TABLE_LIST)){
            return new CommandTableList(source);
        }
        if (command.equalsIgnoreCase(Command.TABLE_DATA)){
            return new CommandTableData(source);
        }
        if (command.equalsIgnoreCase(Command.ADD_RECORD)){
            return new CommandAddRecord(source);
        }
        if (command.equalsIgnoreCase(Command.CLEAR)){
            return new CommandClearTable(source);
        }
        return  new CommandWrong(source);
    }

}
