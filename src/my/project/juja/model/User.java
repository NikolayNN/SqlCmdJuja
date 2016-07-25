package my.project.juja.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikol on 7/25/2016.
 */
public class User {
    String name;
    List<Device> devices;
    DataBase dataBase;

    public User(String name, List<Long> idList, DataBase dataBase) {
        this.dataBase = dataBase;
        this.name = name;
        devices = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            devices.add(new Device(idList.get(i), dataBase));
        }
    }

    public String getName() {
        return name;
    }

    public List<Device> getDevices() {
        return devices;
    }
}
