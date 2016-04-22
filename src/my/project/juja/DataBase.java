package my.project.juja;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nikol on 4/12/2016.
 */
public class DataBase {

    private static Connection connection;
    private static String dbName;


    public static void getConnection(String dbName, String login, String password) {
        DataBase.dbName = dbName;
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
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/" + dbName, login,
                    password);
        } catch (SQLException e) {
            Console.writeStringln("You should connect to database");
        }

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

    public static void clearTable(String tableName){
        try (Statement stmt = connection.createStatement()){
//            stmt = connection.createStatement();
            String sql = "DELETE FROM " + tableName;
            stmt.executeUpdate(sql);
            Console.writeStringln("table " + tableName + " was cleared");
        }catch (SQLException ex) {
            Console.writeStringln("check table name");
        }
    }

    public static void addRecord(String tableName, String ... values) {
        if(connection == null){
            Console.writeStringln("ERROR. connect to data base!");
            return;
        }

        try (Statement stmt = connection.createStatement()) {
        String valuesQuery = "";
        for (int i = 0; i < values.length; i++) {
            valuesQuery += "'" + values[i] + "'";
            if(i == values.length - 1){
                continue;
            }else valuesQuery += " , ";
        }

            String sql = "INSERT INTO " + tableName +
                    " VALUES (" + valuesQuery + ")";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch (SQLException ex){
            Console.writeStringln("ERROR. wrong query!");
            ex.printStackTrace();
        }
    }

    public static String getTableList (){
        if(connection == null){
            Console.writeStringln("ERROR. Connect to data base.");
            return "";
        }
        String result = "no one table exist in " + dbName;
        String query = "SELECT table_name" +
                "        FROM information_schema.tables" +
                "        WHERE table_schema='public'" +
                "        AND table_type='BASE TABLE';";
        try(Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query) )  {
            Set<String> tables = new HashSet<>();
            while (rs.next()) {
                String name = rs.getString("table_name");
                tables.add(name);
            }
            result = tables.toString();
            rs.close();
            stmt.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }



    public static String getColumnName(String tableName){
        String result = "";
        if(connection == null){
            Console.writeStringln("Error. Connect to database");
            return "";
        }
        String query = "SELECT * FROM " + tableName;
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                result += rsmd.getColumnName(i) + "|";
            }
        }catch (SQLException ex){
            Console.writeStringln("ERROR. TempTable " + tableName + " is not exist");
        }
        return result;
    }

    public static ArrayList<String> getTableData(String tableName){
        ArrayList<String> result = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                String record = "";
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                     record += (rs.getString(i).trim() + "|");
                }
                result.add(record);
            }
        }catch (SQLException ex){
            Console.writeStringln("ERROR. TempTable " + tableName + " is not exist");
        }
        return result;
    }
}
