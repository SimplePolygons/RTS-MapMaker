import javax.swing.JFrame;

public class MapMaker {
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(1000, 700);
        gui.setVisible(true);
    }
}
