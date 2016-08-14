package my.project.juja.model;


import my.project.juja.model.DataBase;
import my.project.juja.model.DataSet;
import my.project.juja.model.Sensor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
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
     public void test(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:54;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:64;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:70;BAT1:25;PWR1:28|");
        Mockito.when(dataBase.getTableData()).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =   "{date=Fri Jul 22 11:15:01 MSK 2016, value=54.0}" + "\n" +
                            "{date=Fri Jul 22 11:16:41 MSK 2016, value=64.0}" + "\n" +
                            "{date=Fri Jul 22 11:18:21 MSK 2016, value=70.0}" + "\n";

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
    public void test2(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:0;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:64;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:70;BAT1:25;PWR1:28|");
        Mockito.when(dataBase.getTableData()).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =   "{date=Fri Jul 22 11:15:01 MSK 2016, value=0.0}" + "\n" +
                            "{date=Fri Jul 22 11:16:41 MSK 2016, value=64.0}" + "\n" +
                            "{date=Fri Jul 22 11:18:21 MSK 2016, value=70.0}" + "\n";

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
    public void test3(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:54;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:0;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:70;BAT1:25;PWR1:28|");
        Mockito.when(dataBase.getTableData()).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =   "{date=Fri Jul 22 11:15:01 MSK 2016, value=54.0}" + "\n" +
                            "{date=Fri Jul 22 11:16:41 MSK 2016, value=54.0}" + "\n" +
                            "{date=Fri Jul 22 11:18:21 MSK 2016, value=124.0}" + "\n";

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
    public void test4(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:54;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:64;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:0;BAT1:25;PWR1:28|");
        Mockito.when(dataBase.getTableData()).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =   "{date=Fri Jul 22 11:15:01 MSK 2016, value=54.0}" + "\n" +
                            "{date=Fri Jul 22 11:16:41 MSK 2016, value=64.0}" + "\n" +
                            "{date=Fri Jul 22 11:18:21 MSK 2016, value=64.0}" + "\n";

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
    public void test5(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175301|");
        given.add("353386062153982|1469175401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:64;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:74;BAT1:25;PWR1:28|");
        Mockito.when(dataBase.getTableData()).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =   "{date=Fri Jul 22 11:15:01 MSK 2016, value=0.0}" + "\n" +
                            "{date=Fri Jul 22 11:16:41 MSK 2016, value=64.0}" + "\n" +
                            "{date=Fri Jul 22 11:18:21 MSK 2016, value=74.0}" + "\n";

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
    public void test6(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175301|CSQ1:87");
        given.add("353386062153982|1469175401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:64;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:74;BAT1:25;PWR1:28|");
        Mockito.when(dataBase.getTableData()).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =   "{date=Fri Jul 22 11:15:01 MSK 2016, value=0.0}" + "\n" +
                "{date=Fri Jul 22 11:16:41 MSK 2016, value=64.0}" + "\n" +
                "{date=Fri Jul 22 11:18:21 MSK 2016, value=74.0}" + "\n";

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
    public void test7(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:54;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175401|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:64;CNT2:74;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175501|CSQ1:87");
        Mockito.when(dataBase.getTableData()).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =   "{date=Fri Jul 22 11:15:01 MSK 2016, value=54.0}" + "\n" +
                "{date=Fri Jul 22 11:16:41 MSK 2016, value=74.0}" + "\n" +
                "{date=Fri Jul 22 11:18:21 MSK 2016, value=74.0}" + "\n";

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
    public void test8(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:54;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175401|CSQ1:87");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:74;BAT1:25;PWR1:28|");
        Mockito.when(dataBase.getTableData()).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =   "{date=Fri Jul 22 11:15:01 MSK 2016, value=54.0}" + "\n" +
                "{date=Fri Jul 22 11:16:41 MSK 2016, value=54.0}" + "\n" +
                "{date=Fri Jul 22 11:18:21 MSK 2016, value=128.0}" + "\n";

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
    public void test9(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175301|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:54;BAT1:25;PWR1:28|");
        given.add("353386062153982|1469175401|");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:5;BAT1:25;PWR1:28|");
        Mockito.when(dataBase.getTableData()).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =   "{date=Fri Jul 22 11:15:01 MSK 2016, value=54.0}" + "\n" +
                "{date=Fri Jul 22 11:16:41 MSK 2016, value=54.0}" + "\n" +
                "{date=Fri Jul 22 11:18:21 MSK 2016, value=59.0}" + "\n";

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
    public void tes10(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175301|");
        Mockito.when(dataBase.getTableData()).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =   "{date=Fri Jul 22 11:15:01 MSK 2016, value=0.0}" + "\n";

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
    public void tes11(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175301|");
        given.add("353386062153982|1469175401|");
        Mockito.when(dataBase.getTableData()).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =   "{date=Fri Jul 22 11:15:01 MSK 2016, value=0.0}" + "\n" +
                            "{date=Fri Jul 22 11:16:41 MSK 2016, value=0.0}" + "\n";

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
    public void tes12(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175301|");
        given.add("353386062153982|1469175401|");
        given.add("353386062153982|1469175501|");
        Mockito.when(dataBase.getTableData()).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =   "{date=Fri Jul 22 11:15:01 MSK 2016, value=0.0}" + "\n" +
                            "{date=Fri Jul 22 11:16:41 MSK 2016, value=0.0}" + "\n" +
                            "{date=Fri Jul 22 11:18:21 MSK 2016, value=0.0}" + "\n";

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
    public void tes13(){
        //given
        Sensor sensor = new Sensor(353386062153982l);
        List<String> given = new ArrayList<>();
        given.add("353386062153982|1469175301|");
        given.add("353386062153982|1469175401|");
        given.add("353386062153982|1469175501|CSQ1:87;NSQ1:5;NSQ2:1;CNT1:54;CNT2:5;BAT1:25;PWR1:28|");
        Mockito.when(dataBase.getTableData()).thenReturn(given);
        sensor.setSensorData(dataBase);

        String expected =   "{date=Fri Jul 22 11:15:01 MSK 2016, value=0.0}" + "\n" +
                "{date=Fri Jul 22 11:16:41 MSK 2016, value=0.0}" + "\n" +
                "{date=Fri Jul 22 11:18:21 MSK 2016, value=5.0}" + "\n";

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