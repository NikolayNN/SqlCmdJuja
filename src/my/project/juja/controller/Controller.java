package my.project.juja.controller;

import my.project.juja.model.DataBase;
import my.project.juja.view.Console;
import my.project.juja.view.View;

/**
 * Created by Nikol on 5/13/2016.
 */
public class Controller {
    public void start(){
        View view = new Console();
        DataBase db = new DataBase();
        db.getConnection();
        

    }
}
