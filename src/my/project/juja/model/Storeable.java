package my.project.juja.model;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by Nikol on 5/2/2016.
 */
public interface Storeable {
    void getConnection(String dbName, String login, String password);

    Connection getConnection();

    void closeConnection();

    void clearTable(String tableName);

    void addRecord(String tableName, String columnNames, String columnValues);

    String getTableList();

    String getColumnName(String tableName);

    ArrayList<String> getTableData(String tableName);
}
