package my.project.juja.commands;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by Nikol on 4/12/2016.
 */
public abstract class Command{
    public static final String HELP = "help";
    public static final String EXIT = "exit";
    public static final String CONNECTION = "connect";
    public static final String TABLE_LIST = "tablelist";
    public static final String TABLE_DATA = "tabledata";
    public static final String ADD_RECORD = "addrecord";
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
    public abstract void perform();
}

