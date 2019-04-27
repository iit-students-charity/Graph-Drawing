import controller.Controller;
import model.LinearFunction;
import view.MainFrame;


public class Main {

    public static void main(String[] args) {
        MainFrame window = new MainFrame();
        Controller controller = new Controller(window, window.getLock());
        LinearFunction calc = new LinearFunction(window.getLock());
        window.buildFrame().setVisible(true);
    }
}
