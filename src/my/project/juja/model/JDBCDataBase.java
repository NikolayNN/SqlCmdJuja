package my.project.juja.model;
        import my.project.juja.controller.commands.Command;
        import java.sql.*;
        import java.util.ArrayList;
        import java.util.HashSet;
        import java.util.Set;

/**
 * Created by Nikol on 4/12/2016.
 */
public class JDBCDataBase implements Storeable {
    private static final String ERROR_WRONG_TABLENAME = "ERROR. check table name";
    private static final String ERROR_WRONG_COMMAND = "ERROR. check inputed command";
    private static final String ERROR_WRONG_PARAMETERS_COUNT = "ERROR. wrong paramaters count";
    private static  final String ERROR_JDBCDRIVER_NOT_FOUND = "ERROR. add jdbc driver to project";
    private static  final String ERROR_CONNECT_UNSUCCESSFUL = "ERROR. connect to database unsuccessful, check your command";
    private static  final String ERROR_CONNECTION_NOT_EXIST = "ERROR. at first connect to database";
    private Connection connection;
    private static String dbName;

    @Override
    public void getConnection(String dbName, String login, String password) {
        JDBCDataBase.dbName = dbName;
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

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void closeConnection(){
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkConnection()throws RuntimeException{
        if(connection == null){
            throw new RuntimeException(ERROR_CONNECTION_NOT_EXIST);
        }
    }

    @Override
    public void clearTable(String tableName){
    checkConnection();
        try (Statement stmt = connection.createStatement()){
            String sql = "DELETE FROM " + tableName;
            stmt.executeUpdate(sql);
        }catch (SQLException ex) {
            throw new RuntimeException(ERROR_WRONG_TABLENAME);
        }
    }

    public void addRecord(String tableName, String ... values) {
        checkConnection();
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

    @Override
    public void addRecord(String tableName, String columnNames, String columnValues) {
        checkConnection();
        columnNames = parcer(columnNames, "\"");
        columnValues = parcer(columnValues, "'");
        try (Statement stmt = connection.createStatement()) {
            String sql =    "INSERT INTO " + tableName + "(" + columnNames + ")" +
                            " VALUES (" + columnValues + ")";
            stmt.executeUpdate(sql);
            stmt.close();
        }catch (SQLException ex){
            throw new RuntimeException(ERROR_WRONG_COMMAND);
        }
    }


    private String parcer(String line, String s) {
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

    @Override
    public String getTableList(){
        checkConnection();
        String result = "no one table exist in " + dbName;
        String query =  "SELECT table_name" +
                        " FROM information_schema.tables" +
                        " WHERE table_schema='public'" +
                        " AND table_type='BASE TABLE';";
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

    @Override
    public String getColumnName(String tableName){
        String result = "";
        checkConnection();
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

    @Override
    public ArrayList<String> getTableData(String tableName){
        checkConnection();
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
