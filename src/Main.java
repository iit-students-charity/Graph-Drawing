import controller.Controller;
import model.LinearFunction;
import view.MainFrame;


public class Main {

    public static void main(String[] args) {
        MainFrame window = new MainFrame();
        Controller controller = new Controller(window);
        LinearFunction calc = new LinearFunction(controller);
        window.buildFrame().setVisible(true);
    }
}
