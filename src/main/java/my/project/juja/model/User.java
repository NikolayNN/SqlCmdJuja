package my.project.juja.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikol on 7/25/2016.
 */
public class User {
    String name;
    List<Device> devices;

    public User(String name) {
        this.name = name;
    }

    public void addDevice(long id){
        if(devices == null){
            devices = new ArrayList<>();
        }
        devices.add(new Device(id));
    }

    public String getName() {
        return name;
    }

    public List<Device> getDevices() {
        return devices;
    }
}
