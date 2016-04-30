package my.project.juja.model;

import my.project.juja.model.DataBase;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Nikol on 4/15/2016.
 */
public class DataBaseTest {
    Connection connection;
    @Before
    public void setup() {
        DataBase.getConnection("sqlcmd", "postgres", "root");
    }
    @Test
    public void testAddRecord(){

        DataBase.clearTable("users");

        DataBase.addRecord("users", "alex", "FadH74Gne", "123");

        ArrayList<String> recordList =DataBase.getTableData("users");
        String actual = recordList.get(0);

        assertEquals("alex|FadH74Gne|123|", actual);

    }
    @Test
    public void getColumnNameTest(){
        String actual = DataBase.getColumnName("users");
        assertEquals("name|password|Id|", actual);
    }

    @Test
    public void getTableListTest(){
        String actual = DataBase.getTableList();
        assertEquals("[users2, users]", actual);
    }

    @Test
    public void getTableData(){
        DataBase.clearTable("users");
        DataBase.addRecord("users", "login1", "pass1", "1");
        DataBase.addRecord("users", "login2", "pass2", "2");
        DataBase.addRecord("users", "login3", "pass3", "3");

        List<String> actualList = DataBase.getTableData("users");
        String actual = "";
        for (String s : actualList) {
            actual += s + "\n";
        }

        assertEquals(   "login1|pass1|1|\n" +
                        "login2|pass2|2|\n" +
                        "login3|pass3|3|\n", actual);
    }








}
