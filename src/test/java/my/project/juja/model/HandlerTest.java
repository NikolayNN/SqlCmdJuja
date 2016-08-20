package my.project.juja.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nikol on 8/14/2016.
 */
public class HandlerTest  {
    DataBase dataBase;
    @Before
    public void setup(){
        dataBase = Mockito.mock(DataBase.class);
    }

    @Test
    public void test(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175101|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:0;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175201|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:1000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:2000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:3000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:4000;BAT1:25;PWR1:28|");
        Mockito.when(dataBase.getTableData(353386062153982l)).thenReturn(given);
        sensor.setSensorData(dataBase);
        Handler handler = new Handler(sensor.getSensorData(), 200);

        String expected = "[{date=" + new Date(1469175101000l).toString() + ", value=20.0}]";
        //when
        handler.process();
        //then
        String result = handler.getProcessedData().toString();
        assertEquals(expected, result);
    }

    @Test
    public void test1(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175101|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:0;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175201|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:1000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:2000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:3000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:4000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1474359101|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:4000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1474359201|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:0;BAT1:25;PWR1:28|");
        given.add("353386062153982|1474359301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:1000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1474359401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:2000;BAT1:25;PWR1:28|");
        Mockito.when(dataBase.getTableData(353386062153982l)).thenReturn(given);
        sensor.setSensorData(dataBase);
        Handler handler = new Handler(sensor.getSensorData(), 200);

        String expected = "{date=" + new Date(1469175101000l).toString() + ", value=20.0}" + "\n" +
                          "{date=" + new Date(1474359201000l).toString() + ", value=10.0}" + "\n";
        //when
        handler.process();
        //then
        String result = "";
        for (DataSet dataSet : handler.getProcessedData()) {
            result += dataSet.toString() + "\n";
        }

        assertEquals(expected, result);
    }

    @Test
    //тест при не правильном порядке сообщений
    public void test2(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1474359101|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:4000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1474359201|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:0;BAT1:25;PWR1:28|");
        given.add("353386062153982|1474359301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:1000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1474359401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:2000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175101|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:0;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175201|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:1000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:2000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:3000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:4000;BAT1:25;PWR1:28|");
        Mockito.when(dataBase.getTableData(353386062153982l)).thenReturn(given);
        sensor.setSensorData(dataBase);
        Handler handler = new Handler(sensor.getSensorData(), 200);

        String expected = "{date=" + new Date(1469175101000l).toString() + ", value=20.0}" + "\n" +
                "{date=" + new Date(1474359201000l).toString() + ", value=10.0}" + "\n";
        //when
        handler.process();
        //then
        String result = "";
        for (DataSet dataSet : handler.getProcessedData()) {
            result += dataSet.toString() + "\n";
        }

        assertEquals(expected, result);
    }

    @Test
    //тест при не правильном порядке сообщений
    public void test3(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1474359101|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:4000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175101|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:0;BAT1:25;PWR1:28|");
        given.add("353386062153982|1474359201|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:0;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175201|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:1000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1474359301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:1000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:2000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1474359401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:2000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:3000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:4000;BAT1:25;PWR1:28|");
        Mockito.when(dataBase.getTableData(353386062153982l)).thenReturn(given);
        sensor.setSensorData(dataBase);
        Handler handler = new Handler(sensor.getSensorData(), 200);

        String expected = "{date=" + new Date(1469175101000l).toString() + ", value=20.0}" + "\n" +
                "{date=" + new Date(1474359201000l).toString() + ", value=10.0}" + "\n";
        //when
        handler.process();
        //then
        String result = "";
        for (DataSet dataSet : handler.getProcessedData()) {
            result += dataSet.toString() + "\n";
        }
        assertEquals(expected, result);
    }

    @Test
    //пустые сообщения
    public void test4(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175101|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:0;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175201|");
        given.add("353386062153982|1469175301|DIN2:0");
        given.add("353386062153982|1469175401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:3000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:4000;BAT1:25;PWR1:28|");
        Mockito.when(dataBase.getTableData(353386062153982l)).thenReturn(given);
        sensor.setSensorData(dataBase);
        Handler handler = new Handler(sensor.getSensorData(), 200);

        String expected = "[{date=" + new Date(1469175101000l).toString() + ", value=20.0}]";
        //when
        handler.process();
        //then
        String result = handler.getProcessedData().toString();
        assertEquals(expected, result);
    }
}