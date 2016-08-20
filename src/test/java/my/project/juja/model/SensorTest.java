package my.project.juja.model;


import my.project.juja.model.DataBase;
import my.project.juja.model.DataSet;
import my.project.juja.model.Sensor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nikol on 8/9/2016.
 */
public class SensorTest  {
    DataBase dataBase;


    @Before
    public void setup(){
        dataBase = Mockito.mock(DataBase.class);
    }

    @Test
    public void test01(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175101|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:1000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175201|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:2000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:3000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:4000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:5000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175601|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:4500;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175701|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:5000;BAT1:25;PWR1:28|");

        Mockito.when(dataBase.getTableData(353386062153982l)).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =   "{date=" + new Date(1469175101000l) + ", value=1000.0}" + "\n" +
                "{date=" + new Date(1469175201000l) + ", value=2000.0}" + "\n" +
                "{date=" + new Date(1469175301000l) + ", value=3000.0}" + "\n" +
                "{date=" + new Date(1469175401000l) + ", value=4000.0}" + "\n" +
                "{date=" + new Date(1469175601000l) + ", value=4500.0}" + "\n" +
                "{date=" + new Date(1469175701000l) + ", value=5000.0}" + "\n";

        //when
        List<DataSet> sensordata = sensor.getSensorData();
        //then
        String result = "";
        for (DataSet dataSet : sensordata) {
            result += dataSet.toString() + "\n";
        }
        assertEquals(expected, result);
    }

    @Test
    public void test02(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175101|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:0;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175201|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:0;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:3000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:4000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:5000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175601|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:4500;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175701|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:5000;BAT1:25;PWR1:28|");

        Mockito.when(dataBase.getTableData(353386062153982l)).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =
                "{date=" + new Date(1469175301000l) + ", value=3000.0}" + "\n" +
                "{date=" + new Date(1469175401000l) + ", value=4000.0}" + "\n" +
                "{date=" + new Date(1469175601000l) + ", value=4500.0}" + "\n" +
                "{date=" + new Date(1469175701000l) + ", value=5000.0}" + "\n";

        //when
        List<DataSet> sensordata = sensor.getSensorData();
        //then
        String result = "";
        for (DataSet dataSet : sensordata) {
            result += dataSet.toString() + "\n";
        }
        assertEquals(expected, result);
    }

    @Test
    public void test03(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175101|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:0;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175201|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:0;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:3000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:0;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:0;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175601|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:0;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175701|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:5000;BAT1:25;PWR1:28|");

        Mockito.when(dataBase.getTableData(353386062153982l)).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =
                "{date=" + new Date(1469175301000l) + ", value=3000.0}" + "\n" +
                        "{date=" + new Date(1469175401000l) + ", value=3000.0}" + "\n" +
                        "{date=" + new Date(1469175501000l) + ", value=3000.0}" + "\n" +
                        "{date=" + new Date(1469175601000l) + ", value=3000.0}" + "\n" +
                        "{date=" + new Date(1469175701000l) + ", value=5000.0}" + "\n";

        //when
        List<DataSet> sensordata = sensor.getSensorData();
        //then
        String result = "";
        for (DataSet dataSet : sensordata) {
            result += dataSet.toString() + "\n";
        }
        assertEquals(expected, result);
    }

    @Test
    public void test04(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175101|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:1000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175201|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:2000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:3000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:4000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:1000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175601|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:2000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175701|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:3000;BAT1:25;PWR1:28|");

        Mockito.when(dataBase.getTableData(353386062153982l)).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =   "{date=" + new Date(1469175101000l) + ", value=1000.0}" + "\n" +
                "{date=" + new Date(1469175201000l) + ", value=2000.0}" + "\n" +
                "{date=" + new Date(1469175301000l) + ", value=3000.0}" + "\n" +
                "{date=" + new Date(1469175401000l) + ", value=4000.0}" + "\n" +
                "{date=" + new Date(1469175501000l) + ", value=5000.0}" + "\n" +
                "{date=" + new Date(1469175601000l) + ", value=6000.0}" + "\n" +
                "{date=" + new Date(1469175701000l) + ", value=7000.0}" + "\n";

        //when
        List<DataSet> sensordata = sensor.getSensorData();
        //then
        String result = "";
        for (DataSet dataSet : sensordata) {
            result += dataSet.toString() + "\n";
        }
        assertEquals(expected, result);
    }

    @Test
    public void test05(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175101|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:1000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175201|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:2000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:3000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:4000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:1000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175601|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:2000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175701|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:3000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175801|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:1000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175901|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:2000;BAT1:25;PWR1:28|");

        Mockito.when(dataBase.getTableData(353386062153982l)).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =   "{date=" + new Date(1469175101000l) + ", value=1000.0}" + "\n" +
                "{date=" + new Date(1469175201000l) + ", value=2000.0}" + "\n" +
                "{date=" + new Date(1469175301000l) + ", value=3000.0}" + "\n" +
                "{date=" + new Date(1469175401000l) + ", value=4000.0}" + "\n" +
                "{date=" + new Date(1469175501000l) + ", value=5000.0}" + "\n" +
                "{date=" + new Date(1469175601000l) + ", value=6000.0}" + "\n" +
                "{date=" + new Date(1469175701000l) + ", value=7000.0}" + "\n" +
                "{date=" + new Date(1469175801000l) + ", value=8000.0}" + "\n" +
                "{date=" + new Date(1469175901000l) + ", value=9000.0}" + "\n";

        //when
        List<DataSet> sensordata = sensor.getSensorData();
        //then
        String result = "";
        for (DataSet dataSet : sensordata) {
            result += dataSet.toString() + "\n";
        }
        assertEquals(expected, result);
    }


    @Test
    public void test06(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175101|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:1000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175201|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:2000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:3000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:4000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175501|");
        given.add("353386062153982|1469175601|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:5000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175701|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:4500;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175801|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:5000;BAT1:25;PWR1:28|");

        Mockito.when(dataBase.getTableData(353386062153982l)).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =
                        "{date=" + new Date(1469175101000l) + ", value=1000.0}" + "\n" +
                        "{date=" + new Date(1469175201000l) + ", value=2000.0}" + "\n" +
                        "{date=" + new Date(1469175301000l) + ", value=3000.0}" + "\n" +
                        "{date=" + new Date(1469175401000l) + ", value=4000.0}" + "\n" +
                        "{date=" + new Date(1469175501000l) + ", value=4000.0}" + "\n" +
                        "{date=" + new Date(1469175701000l) + ", value=4500.0}" + "\n" +
                        "{date=" + new Date(1469175801000l) + ", value=5000.0}" + "\n";

        //when
        List<DataSet> sensordata = sensor.getSensorData();
        //then
        String result = "";
        for (DataSet dataSet : sensordata) {
            result += dataSet.toString() + "\n";
        }
        assertEquals(expected, result);
    }

    @Test
    public void test07(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175101|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:47229;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175201|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:47229;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:47230;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:47230;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:47230;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175601|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:47230;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175701|");
        given.add("353386062153982|1469175801|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:47238;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175901|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:47231;BAT1:25;PWR1:28|");


        Mockito.when(dataBase.getTableData(353386062153982l)).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =
                "{date=" + new Date(1469175101000l) + ", value=47229.0}" + "\n" +
                        "{date=" + new Date(1469175201000l) + ", value=47229.0}" + "\n" +
                        "{date=" + new Date(1469175301000l) + ", value=47230.0}" + "\n" +
                        "{date=" + new Date(1469175401000l) + ", value=47230.0}" + "\n" +
                        "{date=" + new Date(1469175501000l) + ", value=47230.0}" + "\n" +
                        "{date=" + new Date(1469175601000l) + ", value=47230.0}" + "\n" +
                        "{date=" + new Date(1469175701000l) + ", value=47230.0}" + "\n" +
                        "{date=" + new Date(1469175901000l) + ", value=47231.0}" + "\n";

        //when
        List<DataSet> sensordata = sensor.getSensorData();
        //then
        String result = "";
        for (DataSet dataSet : sensordata) {
            result += dataSet.toString() + "\n";
        }
        assertEquals(expected, result);
    }

    @Test
    public void test08(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175101|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:1000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175201|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:2000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:3000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:4000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:3000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175601|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:5000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175701|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:7000;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175801|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:8000;BAT1:25;PWR1:28|");

        Mockito.when(dataBase.getTableData(353386062153982l)).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =
                "{date=" + new Date(1469175101000l) + ", value=1000.0}" + "\n" +
                        "{date=" + new Date(1469175201000l) + ", value=2000.0}" + "\n" +
                        "{date=" + new Date(1469175301000l) + ", value=3000.0}" + "\n" +
                        "{date=" + new Date(1469175401000l) + ", value=4000.0}" + "\n" +
                        "{date=" + new Date(1469175601000l) + ", value=5000.0}" + "\n" +
                        "{date=" + new Date(1469175701000l) + ", value=7000.0}" + "\n" +
                        "{date=" + new Date(1469175801000l) + ", value=8000.0}" + "\n";

        //when
        List<DataSet> sensordata = sensor.getSensorData();
        //then
        String result = "";
        for (DataSet dataSet : sensordata) {
            result += dataSet.toString() + "\n";
        }
        assertEquals(expected, result);
    }






}