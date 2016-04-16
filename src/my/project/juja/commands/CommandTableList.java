package my.project.juja.commands;

import my.project.juja.Console;
import my.project.juja.DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nikol on 4/13/2016.
 */
public class CommandTableList extends Command {
    public CommandTableList(String fullString) {
        super(fullString);
    }

    @Override
    public void perform() {
        String tableList = DataBase.getTableList();
        Console.writeStringln(tableList);
    }

}
