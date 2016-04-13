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
        try {
            DataBase.getConnection(parametrs[0], parametrs[1], parametrs[2]);
        } catch (SQLException e) {
            console.writeString("Please, check data base name, login, password");
        }
    }
}
