package my.project.juja.controller.commands;

import my.project.juja.model.Storeable;
import my.project.juja.view.Console;
import my.project.juja.view.View;

/**
 * Created by Nikol on 4/12/2016.
 */
public abstract class Command{
    public static final String HELP = "help";
    public static final String EXIT = "exit";
    public static final String CONNECTION = "connect";
    public static final String TABLE_LIST = "table-list";
    public static final String TABLE_DATA = "table-data";
    public static final String ADD_RECORD = "add-record";
    public static final String CLEAR_TABLE = "clear-table";
    public static final String SET_COL = "col";
    public static final String SEPARATOR = " ";
    public static final String MESSAGE_COMMAND_PERFORMED_SUCCESSFUL = "OK.";

    protected String source;
    protected String command;
    protected int countParametrs;
    protected String[] parametrs;
    protected View view;
    protected Storeable store;

    public Command(Storeable store, View view) {
        this.store = store;
        this.view = view;
    }

    public Command(View view) {
        this.view = view;
    }

    public void setup(String source){
        this.source = source;
        String[] splitedFullString = source.split(SEPARATOR);
        command = splitedFullString[0];
        this.countParametrs = splitedFullString.length-1;
        parametrs = new String[countParametrs];
        if(splitedFullString.length > 1){
            System.arraycopy(splitedFullString, 1, parametrs, 0, parametrs.length);
        }
    }

    public void checkCountParameters(String parameters[] , int expectedCount){
        if(parameters.length != expectedCount){
            throw new RuntimeException("Wrong count parameters expected " + expectedCount + ", but found " + parameters.length);
        }
    }
    public abstract void perform();
    public abstract String getName();
}

