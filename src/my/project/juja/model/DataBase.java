package my.project.juja.model;

import my.project.juja.controller.commands.Command;
import my.project.juja.view.Console;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nikol on 4/12/2016.
 */
public class DataBase {

    private static final String ERROR_WRONG_TABLENAME = "ERROR. check table name";
    private static final String ERROR_WRONG_COMMAND = "ERROR. check inputed command";
    private static final String ERROR_WRONG_PARAMETERS_COUNT = "ERROR. wrong paramaters count";
    private static Connection connection;
    private static String dbName;
    private static  final String ERROR_JDBCDRIVER_NOT_FOUND = "ERROR. add jdbc driver to project";
    private static  final String ERROR_CONNECT_UNSUCCESSFUL = "ERROR. connect to database unsuccessful, check your command";
    private static  final String ERROR_CONNECTION_NOT_EXIST = "ERROR. at first connect to database";

    public static void getConnection(String dbName, String login, String password) {
        DataBase.dbName = dbName;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
          throw new RuntimeException(ERROR_JDBCDRIVER_NOT_FOUND);
        }
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/" + dbName, login,
                    password);
        } catch (SQLException ex) {
            throw new RuntimeException(ERROR_CONNECT_UNSUCCESSFUL + " " + ex.getMessage());
        }
        if (connection == null) {
           throw new RuntimeException(ERROR_CONNECT_UNSUCCESSFUL);
        }
}

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection(){
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clearTable(String tableName){
        if(connection == null){
            throw new RuntimeException(ERROR_CONNECTION_NOT_EXIST);
        }
        try (Statement stmt = connection.createStatement()){
//            stmt = connection.createStatement();
            String sql = "DELETE FROM " + tableName;
            stmt.executeUpdate(sql);
        }catch (SQLException ex) {
            throw new RuntimeException(ERROR_WRONG_TABLENAME);
        }
    }

    public static void addRecord(String tableName, String ... values) {
        if(connection == null){
            throw new RuntimeException(ERROR_CONNECTION_NOT_EXIST);
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
            throw new RuntimeException(ERROR_WRONG_COMMAND);
        }
    }

    public static void addRecord(String tableName,String columnNames, String columnValues) {
        if(connection == null){
            throw new RuntimeException(ERROR_CONNECTION_NOT_EXIST);
        }
//        if(columnNames.length != columnValues.length){
//            throw new RuntimeException(ERROR_WRONG_PARAMETERS_COUNT);
//        }

        columnNames = parcer(columnNames, "\"");
        columnValues = parcer(columnValues, "'");

        try (Statement stmt = connection.createStatement()) {
            String sql = "INSERT INTO " + tableName + "(" + columnNames + ")" +
                    " VALUES (" + columnValues + ")";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch (SQLException ex){
            throw new RuntimeException(ERROR_WRONG_COMMAND);
        }
    }


    private static String parcer(String line, String s) {
        String[] word = line.split(Command.SEPARATOR);
        String result = "";
        for (int i = 0; i < word.length; i++) {

            result += s + word[i] + s;
            if (!(i == word.length-1)){
                result += ",";
            }else {
                break;
            }

        }
        return result;
    }

    public static String getTableList (){
        if(connection == null){
            throw new RuntimeException(ERROR_CONNECTION_NOT_EXIST);
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
            throw new RuntimeException(ERROR_WRONG_COMMAND);
        }
        return result;
    }



    public static String getColumnName(String tableName){
        String result = "";
        if(connection == null){
           throw new RuntimeException(ERROR_CONNECTION_NOT_EXIST);
        }
        String query = "SELECT * FROM " + tableName;
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                result += rsmd.getColumnName(i) + "|";
            }
        }catch (SQLException ex){
            throw new RuntimeException(ERROR_WRONG_TABLENAME);
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
            throw new RuntimeException(ERROR_WRONG_TABLENAME);
        }
        return result;
    }
}
