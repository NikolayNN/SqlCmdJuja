package my.project.juja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Nikol on 4/12/2016.
 */
public class DataBase {

    private static Connection connection;

    public static void getConnection(String dbName, String login, String password) throws SQLException {
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        }

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();

        }
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/" + dbName, login,
                    password);

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }

}

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
