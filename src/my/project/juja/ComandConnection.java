package my.project.juja;

/**
 * Created by Nikol on 4/13/2016.
 */
public class ComandConnection extends Command {
    public ComandConnection(String source) {
        super(source);

    }

    @Override
    public String perform() {
        ConnectionToDb connectionToDb = new ConnectionToDb();
        connectionToDb.getConnection(parametrs[0], parametrs[1], parametrs[2]);
        return " ";
    }
}
