package my.project.juja.controller;

import my.project.juja.model.*;
import my.project.juja.view.Console;
import my.project.juja.view.GoogleSheet;
import my.project.juja.view.View;

import java.io.IOException;
import java.util.List;

/**
 * Created by Nikol on 5/13/2016.
 */
public class Controller {
    public void start() {
        View view = new Console();
        DataBase dataBase = new DataBase();
        User user = new User("testUser");
        user.addDevice(353386062153982l);
        List<Device> devices = user.getDevices();
        for (Device device : devices) {
            Sensor sensor = device.getSensor();
            sensor.setSensorData(dataBase);
        }

        for (Device device : devices){
            Sensor sensor = device.getSensor();
            List<DataSet> sensorData = sensor.getSensorData();

            GoogleSheet googleSheet = new GoogleSheet();
            int row = 0;
            for (DataSet dataSet : sensorData) {
                try {
                    googleSheet.writeDouble(dataSet.getValue(), 0, row);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                row++;

            }
        }


    }


}
