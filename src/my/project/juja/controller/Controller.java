package my.project.juja.controller;

import my.project.juja.model.*;
import my.project.juja.view.Console;
import my.project.juja.view.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nikol on 5/13/2016.
 */
public class Controller {
    public void start() {
        View view = new Console();
        DataBase dataBase = new DataBase();
        dataBase.getConnection();
        List<Long> idList = new ArrayList<>();
        idList.add(353386062153982l);
        User user = new User("hotynichi", idList, dataBase);
        List<Device> devices = user.getDevices();
        Sensor sensor = devices.get(0).getSensor();
        List<DataSet> dataSets = sensor.getDataSets();
        for (DataSet dataSet : dataSets) {
            System.out.println(dataSet);
        }




    }


}
