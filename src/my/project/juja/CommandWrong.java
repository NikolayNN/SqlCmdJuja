package my.project.juja;

/**
 * Created by Nikol on 4/13/2016.
 */
public class CommandWrong extends Command{
    public CommandWrong(String source) {
        super(source);
    }

    @Override
    String perform() {
        return null;
    }
    //todo
}
