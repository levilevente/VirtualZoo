import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    public Application() throws HeadlessException {
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.setTitle("Virtual Zoo Management System");
        app.setSize(800, 600);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
