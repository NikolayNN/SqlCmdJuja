package my.project.juja.controller.command;

import my.project.juja.controller.commands.Command;
import my.project.juja.controller.commands.CommandTableList;
import my.project.juja.model.Storeable;
import my.project.juja.view.View;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

/**
 * Created by Nikol on 5/11/2016.
 */
public class CommandTableListMockito {
    private Storeable store;
    private View view;

    @Before
    public void setup(){
        store = Mockito.mock(Storeable.class);
        view = Mockito.mock(View.class);
    }

    @Test
    public void test(){
        //given
        Command command = new CommandTableList(store,view);
        List<String> tableList = new ArrayList<>();
        tableList.add("TestTable1");
        tableList.add("TestTable2");
        Mockito.when(store.getTableList()).thenReturn(tableList);

        //when
        command.perform();

        //then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view).writeln(captor.capture());
        assertEquals("[[TestTable1, TestTable2]]", captor.getAllValues().toString());
    }
}
