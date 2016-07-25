package my.project.juja.model;
        import java.sql.*;
        import java.util.*;

/**
 * Created by Nikol on 4/12/2016.
 */
public class DataBase {
    private static final String ERROR_WRONG_TABLENAME = "ERROR. check table name";
    private static  final String ERROR_JDBCDRIVER_NOT_FOUND = "ERROR. add jdbc driver to project";
    private static  final String ERROR_CONNECT_UNSUCCESSFUL = "ERROR. connect to database unsuccessful, check your command";
    private static  final String ERROR_CONNECTION_NOT_EXIST = "ERROR. at first connect to database";
    private Connection connection;
    private static final String dbName = "gelios";
    private static final String login = "postgres";
    private static final String password = "root";
    private static final String tableName = "storage1";

    public void getConnection() {
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

    }


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



    public List<String> getTableData(){
        checkConnection();
        List<String> result = new ArrayList<>();
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
