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
        try {
            Connection connection = DataBase.getConnection();
            Console console = new Console();
            if(connection == null){
                console.writeString("ERROR. Connect to data base.");
                return;
            }
            Statement stmt = connection.createStatement();
            String query;
            ResultSet rs;
            Set<String> tables = new HashSet<>();
            query = "SELECT table_name" +
                    "        FROM information_schema.tables" +
                    "        WHERE table_schema='public'" +
                    "        AND table_type='BASE TABLE';";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("table_name");
                tables.add(name);
            }
            console.writeString(tables.toString());
        }catch (SQLException ex){
           
            ex.printStackTrace();
        }
    }
}
