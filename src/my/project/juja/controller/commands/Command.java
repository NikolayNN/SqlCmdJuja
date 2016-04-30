package my.project.juja.controller.commands;

import my.project.juja.view.Console;
import my.project.juja.view.View;

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
    public static final String CLEAR = "cleartable";
    public static final String SET_COL = "setcol";
    public static final String SEPARATOR = " ";
    public static final String MESSAGE_COMMAND_PERFORMED_SUCCESSFUL = "OK.";
    protected String source;
    protected String command;
    protected int countParametrs;
    protected String[] parametrs;
    protected View view;
    {
        view = new Console();
    }
    public Command(String source) {
        this.source = source;
        String[] splitedFullString = source.split(SEPARATOR);
        command = splitedFullString[0];
        this.countParametrs = splitedFullString.length-1;
        parametrs = new String[countParametrs];
        if(splitedFullString.length > 1){
            System.arraycopy(splitedFullString, 1, parametrs, 0, parametrs.length);
        }
    }
    public abstract void perform();
}

