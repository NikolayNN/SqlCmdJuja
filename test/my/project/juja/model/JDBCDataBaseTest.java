package my.project.juja.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Nikol on 4/15/2016.
 */
public class JDBCDataBaseTest {
    JDBCDataBase JDBCDataBase;
    @Before
    public void setup() {
        JDBCDataBase = new JDBCDataBase();
        JDBCDataBase.getConnection("sqlcmd", "postgres", "root");
    }
    @Test
    public void testAddRecord(){

        JDBCDataBase.clearTable("users");

        JDBCDataBase.addRecord("users","name password Id", "alex FadH74Gne 123");

        List<String> recordList = JDBCDataBase.getTableData("users");
        String actual = recordList.get(0);

        assertEquals("alex|FadH74Gne|123|", actual);

    }
    @Test
    public void getColumnNameTest(){
        List<String> actual = JDBCDataBase.getColumnName("users");

        assertEquals("[name, password, Id]", actual.toString());
    }

    @Test
    public void getTableListTest(){
        List<String> actual = JDBCDataBase.getTableList();

        assertEquals("[users, users2]", actual.toString());
    }

    @Test
    public void getTableData(){
        JDBCDataBase.clearTable("users");
        JDBCDataBase.addRecord("users","name password Id", "login1 pass1 1");
        JDBCDataBase.addRecord("users","name password Id", "login2 pass2 2");
        JDBCDataBase.addRecord("users","name password Id", "login3 pass3 3");

        List<String> actualList = JDBCDataBase.getTableData("users");
        String actual = "";
        for (String s : actualList) {
            actual += s + "\n";
        }

        assertEquals(   "login1|pass1|1|\n" +
                        "login2|pass2|2|\n" +
                        "login3|pass3|3|\n", actual);
    }








}
