package my.project.juja.controller.commands;

import my.project.juja.view.Console;

/**
 * Created by Nikol on 4/13/2016.
 */
public class CommandWrong extends Command {
    public CommandWrong(String source) {
        super(source);
    }

    @Override
    public void perform() {
        view.writeln("this command doesn't exist, please check your command");
    }

}
