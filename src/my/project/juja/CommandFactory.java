package my.project.juja;

/**
 * Created by Nikol on 4/13/2016.
 */
public class CommandFactory{
    public Command createCommand(String source){
        String command = source.split(Command.COMMAND_SEPARATOR)[0];

        if(command.equalsIgnoreCase(Command.CONNECTION)){
            return new ComandConnection(source);
        }
        return  new CommandWrong(source);
    }

}
