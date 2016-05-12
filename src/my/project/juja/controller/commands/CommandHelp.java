package my.project.juja.controller.commands;

import my.project.juja.view.View;

/**
 * Created by Nikol on 4/26/2016.
 */
public class CommandHelp extends Command {
    public static final String name = Command.HELP;
    public CommandHelp(View view) {
        super(view);
    }

    @Override
    public void perform() {
        view.writeln(
                Command.CONNECTION + " - Connect to database 'connect dbname login password'" + "\n" +
                Command.TABLE_LIST + " - Show exist tables in the current database 'tablelist'" + "\n" +
                Command.TABLE_DATA + " - Show table rows 'tabledata tableName'" + "\n" +
                Command.ADD_RECORD + " - Add record in the selectd table 'addrecord tableName'" + "\n" +
                Command.CLEAR_TABLE + " - clear selected table 'cleartable tableName'" + "\n" +
                Command.EXIT + "- close connection to a database and exit"
        );
    }

    @Override
    public String getName() {
        return name;
    }
}
