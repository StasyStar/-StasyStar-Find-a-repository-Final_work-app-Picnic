import Model.impl.UserRepository;
import View.impl.UserView;
import Model.Repository;
import Controller.Controller;


public class AppRunner {
    public static void run() {
        String fileName = "/Users/stasy/Documents/Education/GB/Java/finalWork/Picnic/input.txt";
        Repository repository = new UserRepository(fileName);
        Controller controller = new Controller(repository);
        UserView userView = new UserView(controller);
        userView.run();
    }
}
