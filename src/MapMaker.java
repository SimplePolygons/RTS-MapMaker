import javax.swing.JFrame;

public class MapMaker {
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // DISPOSE_ON_CLOSE
        gui.setSize(720, 600);
        gui.setVisible(true);
    }
}
