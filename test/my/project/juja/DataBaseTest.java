package my.project.juja;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

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

        DataBase.addRecord("users", "alex", "FadH74Gne");

//        assertEquals("[name, password, id]", Arrays.toString(user.getNames()));

    }
}
