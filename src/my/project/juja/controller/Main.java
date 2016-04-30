package my.project.juja.controller;

import my.project.juja.controller.commands.Command;
import my.project.juja.view.Console;
import my.project.juja.view.View;

/**
 * Created by Nikol on 4/12/2016.
 */
public class Main {
    public static void main(String[] args) {
        View view = new Console();
        CommandFactory commandFactory = new CommandFactory();
        view.writeln("Hello");
        String source = "";
        while (!source.equalsIgnoreCase(Command.EXIT)){
            view.writeln("please, input your command or 'help'");
            try {
                source = view.read();
                Command command = commandFactory.createCommand(source);
                command.perform();
            } catch (Exception e) {
                view.writeln(e.getMessage());
            }
        }

    }


}

