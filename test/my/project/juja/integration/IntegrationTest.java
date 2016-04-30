package my.project.juja.integration;

import my.project.juja.controller.Main;
import my.project.juja.controller.commands.Command;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nikol on 4/25/2016.
 */
public class IntegrationTest {

    private  ConfigurableInputStream in;
    private  ByteArrayOutputStream out;
    private static final String DB_NAME = "sqlcmd";
    private static final String DB_LOGIN = "postgres";
    private static final String DB_PASSWORD = "root";
    private static final String TEST_TABLE = "users";

    @Before
    public void setup(){
        in = new ConfigurableInputStream();
        out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));
    }

    public String getData() {
        try {
            String result = new String(out.toByteArray(), "UTF-8");
            out.reset();
            return result;
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }
    @Ignore
    @Test
    public void testHelp(){
        //given
        in.add(Command.HELP);
        in.add(Command.EXIT);
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Hello\r\n" +
                "please, input your command or 'help'\r\n" +
                "connect - Connect to database 'connect dbname login password'\r\n" +
                "tablelist - Show exist tables in the current database 'tablelist'\r\n" +
                "tabledata - Show table rows 'tabledata tableName'\r\n" +
                "addrecord - Add record in the selectd table 'addrecord tableName'\r\n" +
                "cleartable - clear selected table 'cleartable tableName'\r\n" +
                "exit- close connection to a database and exit\r\n" +
                "please, input your command or 'help'\r\n" +
                "Goodbye\r\n",getData());

    }
    @Test
    public void testTableListWithoutConnect(){
        //given
        in.add(Command.TABLE_LIST);
        in.add(Command.EXIT);
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Hello\r\n" +
                "please, input your command or 'help'\r\n" +
                "ERROR. at first connect to database\r\n" +
                "please, input your command or 'help'\r\n" +
                "Goodbye\r\n", getData());
    }

    @Test
    public void testTableDataWithoutConnect(){
        //given
        in.add(Command.TABLE_DATA);
        in.add(Command.EXIT);
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Hello\r\n" +
                "please, input your command or 'help'\r\n" +
                "ERROR. at first connect to database\r\n" +
                "please, input your command or 'help'\r\n" +
                "Goodbye\r\n",getData());
    }

    @Test
    public void testWrongCommand(){
        //given
        in.add("unsupportedCommand");
        in.add(Command.EXIT);
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Hello\r\n" +
                "please, input your command or 'help'\r\n" +
                "this command doesn't exist, please check your command\r\n" +
                "please, input your command or 'help'\r\n" +
                "Goodbye\r\n",getData());
    }

    @Test
    public void testConnect(){
        //given
        in.add(Command.CONNECTION + Command.SEPARATOR
                + DB_NAME + Command.SEPARATOR
                + DB_LOGIN + Command.SEPARATOR
                + DB_PASSWORD);
        in.add(Command.EXIT);
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Hello\r\n" +
                "please, input your command or 'help'\r\n" +
                "OK.\r\n" +
                "please, input your command or 'help'\r\n" +
                "Connection to data base was closed\r\n" +
                "Goodbye\r\n",getData());
    }

    @Test
    public void testConnectWithWrongDBName(){
        //given
        in.add(Command.CONNECTION + Command.SEPARATOR
                + "WrongDataBaseName" + Command.SEPARATOR
                + DB_LOGIN + Command.SEPARATOR
                + DB_PASSWORD);
        in.add(Command.EXIT);
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Hello\r\n" +
                "please, input your command or 'help'\r\n" +
                "ERROR. connect to database unsuccessful, check your command FATAL: database \"WrongDataBaseName\" does not exist\r\n" +
                "please, input your command or 'help'\r\n" +
                "Goodbye\r\n", getData());
    }

    @Test
    public void testConnectWithWrongLogin(){
        //given
        in.add(Command.CONNECTION + Command.SEPARATOR
                + DB_NAME + Command.SEPARATOR
                + "wrongLogin" + Command.SEPARATOR
                + DB_PASSWORD);
        in.add(Command.EXIT);
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Hello\r\n" +
                "please, input your command or 'help'\r\n" +
                "ERROR. connect to database unsuccessful, check your command FATAL: password authentication failed for user \"wrongLogin\"\r\n" +
                "please, input your command or 'help'\r\n" +
                "Goodbye\r\n", getData());
    }

    @Test
    public void testConnectWithWrongPassword(){
        //given
        in.add(Command.CONNECTION + Command.SEPARATOR
                + DB_NAME + Command.SEPARATOR
                + DB_LOGIN + Command.SEPARATOR
                + "wrongPassword");
        in.add(Command.EXIT);
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Hello\r\n" +
                "please, input your command or 'help'\r\n" +
                "ERROR. connect to database unsuccessful, check your command FATAL: password authentication failed for user \"postgres\"\r\n" +
                "please, input your command or 'help'\r\n" +
                "Goodbye\r\n", getData());
    }

    @Test
    public void testUnsuportedCommandAfterConnect(){
        //given
        in.add(Command.CONNECTION + Command.SEPARATOR
                + DB_NAME + Command.SEPARATOR
                + DB_LOGIN + Command.SEPARATOR
                + DB_PASSWORD);
        in.add("unsuportedcommand");
        in.add(Command.EXIT);
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Hello\r\n" +
                "please, input your command or 'help'\r\n" +
                "OK.\r\n" +
                "please, input your command or 'help'\r\n" +
                "this command doesn't exist, please check your command\r\n" +
                "please, input your command or 'help'\r\n" +
                "Connection to data base was closed\r\n" +
                "Goodbye\r\n", getData());
    }

    @Test
     public void testConnectionWithoutParametrs(){
        //given
        in.add(Command.CONNECTION);
        in.add(Command.EXIT);
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Hello\r\n" +
                "please, input your command or 'help'\r\n" +
                "command 'connect' must have 3 parameters: dbName login password\r\n" +
                "please, input your command or 'help'\r\n" +
                "Goodbye\r\n", getData());
    }

    @Test
    public void testTableListAfterConnect(){
        //given
        in.add(Command.CONNECTION + Command.SEPARATOR
                + DB_NAME + Command.SEPARATOR
                + DB_LOGIN + Command.SEPARATOR
                + DB_PASSWORD);
        in.add(Command.TABLE_LIST + Command.SEPARATOR);
        in.add(Command.EXIT);
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Hello\r\n" +
                "please, input your command or 'help'\r\n" +
                "OK.\r\n" +
                "please, input your command or 'help'\r\n" +
                "[users2, users]\r\n" +
                "please, input your command or 'help'\r\n" +
                "Connection to data base was closed\r\n" +
                "Goodbye\r\n", getData());
    }

    @Test
    public void testClearTable(){
        //given
        in.add(Command.CONNECTION + Command.SEPARATOR
                + DB_NAME + Command.SEPARATOR
                + DB_LOGIN + Command.SEPARATOR
                + DB_PASSWORD);
        in.add(Command.CLEAR + Command.SEPARATOR + TEST_TABLE);
        in.add(Command.EXIT);
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Hello\r\n" +
                "please, input your command or 'help'\r\n" +
                "OK.\r\n" +
                "please, input your command or 'help'\r\n" +
                "users has been cleared\r\n" +
                "please, input your command or 'help'\r\n" +
                "Connection to data base was closed\r\n" +
                "Goodbye\r\n", getData());
    }

    @Test
    public void testClearTableWithoutConnect(){
        //given
        in.add(Command.CLEAR + Command.SEPARATOR + TEST_TABLE);
        in.add(Command.EXIT);
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Hello\r\n" +
                "please, input your command or 'help'\r\n" +
                "ERROR. at first connect to database\r\n" +
                "please, input your command or 'help'\r\n" +
                "Goodbye\r\n", getData());
    }

    @Test
    public void testClearTableWithError(){
        //given
        in.add(Command.CONNECTION + Command.SEPARATOR
                + DB_NAME + Command.SEPARATOR
                + DB_LOGIN + Command.SEPARATOR
                + DB_PASSWORD);
        in.add(Command.CLEAR + Command.SEPARATOR + "wrongTableName");
        in.add(Command.EXIT);
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Hello\r\n" +
                "please, input your command or 'help'\r\n" +
                "OK.\r\n" +
                "please, input your command or 'help'\r\n" +
                "ERROR. check table name\r\n" +
                "please, input your command or 'help'\r\n" +
                "Connection to data base was closed\r\n" +
                "Goodbye\r\n", getData());
    }

    @Test
    public void testClearTableWithoutParameters(){
        //given
        in.add(Command.CONNECTION + Command.SEPARATOR
                + DB_NAME + Command.SEPARATOR
                + DB_LOGIN + Command.SEPARATOR
                + DB_PASSWORD);
        in.add(Command.CLEAR);
        in.add(Command.EXIT);
        //when
        Main.main(new String[0]);
        //then
        assertEquals("Hello\r\n" +
                "please, input your command or 'help'\r\n" +
                "OK.\r\n" +
                "please, input your command or 'help'\r\n" +
                "check your command 'cleartable tableName'\r\n" +
                "please, input your command or 'help'\r\n" +
                "Connection to data base was closed\r\n" +
                "Goodbye\r\n", getData());
    }



}
