package my.project.juja.model;

import java.util.List;

/**
 * Created by Nikol on 7/25/2016.
 */
public class Device {
    Sensor sensor;
    long id;

    public Device(long id) {
        this.id = id;
        sensor = new Sensor(id);
    }
    
    public Sensor getSensor() {
        return sensor;
    }

    public long getId() {
        return id;
    }
}
