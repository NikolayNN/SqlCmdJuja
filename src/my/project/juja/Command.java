package my.project.juja;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by Nikol on 4/12/2016.
 */
public abstract class Command{
    public static final String HELP = "help";
    public static final String EXIT = "exit";
    public static final String CONNECTION = "connection";
    public static final String COMMAND_SEPARATOR = " ";
    protected String fullString;
    protected String command;
    protected int countParametrs;
    protected String[] parametrs;

    public Command(String fullString) {
        this.fullString = fullString;
        String[] splitedFullString = fullString.split(COMMAND_SEPARATOR);
        command = splitedFullString[0];
        this.countParametrs = splitedFullString.length-1;
        parametrs = new String[countParametrs];
        if(splitedFullString.length > 1){
            System.arraycopy(splitedFullString, 1, parametrs, 0, parametrs.length);
        }
    }
    abstract void perform();
}

