package my.project.juja.model;

import java.util.List;

/**
 * Created by Nikol on 7/25/2016.
 */
public class Device {
    Sensor sensor;
    long id;
    DataBase dataBase;

    public Device(long id, DataBase dataBase) {
        this.id = id;
        this.sensor = new Sensor(id, dataBase);
    }

    public Sensor getSensor() {
        return sensor;
    }

    public long getId() {
        return id;
    }
}
