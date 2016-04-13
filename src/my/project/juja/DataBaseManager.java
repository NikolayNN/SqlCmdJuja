package my.project.juja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Nikol on 4/12/2016.
 */
public class DataBaseManager {
    public static void main(String[] args) {
        Console console = new Console();
        CommandFactory commandFactory = new CommandFactory();
        console.writeString("Hello");
        console.writeString("please, input your command or 'help'");
        Command command = commandFactory.createCommand(console.readString());
        command.perform();



//        Connection connection = connectionToDb.getConnection();

    }


}

