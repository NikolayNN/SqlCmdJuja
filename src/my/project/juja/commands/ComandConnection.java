package my.project.juja.commands;

import my.project.juja.Console;
import my.project.juja.DataBase;

import java.sql.SQLException;

/**
 * Created by Nikol on 4/13/2016.
 */
public class ComandConnection extends Command {
    public ComandConnection(String source) {
        super(source);
    }

    @Override
    public void perform() {
        Console console = new Console();

        if((parametrs.length < 3) || (parametrs.length >= 4)){
            Console.writeStringln("check your command 'connect dbName login password'");
            return;
        }

            DataBase.getConnection(parametrs[0], parametrs[1], parametrs[2]);

    }
}
