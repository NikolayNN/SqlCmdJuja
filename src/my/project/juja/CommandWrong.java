package my.project.juja;

/**
 * Created by Nikol on 4/13/2016.
 */
public class CommandWrong extends Command{
    public CommandWrong(String source) {
        super(source);
    }

    @Override
    public void perform() {
        Console console = new Console();
        console.writeString("Wrong command, please check your command or input 'help'");
    }
    //todo
}
