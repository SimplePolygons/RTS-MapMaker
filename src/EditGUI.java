import javax.swing.*;

public class EditGUI extends JFrame {
    // ATTRIBUTES
    Grid grid;

    // CONSTRUCTORS
    public EditGUI(Grid grid, int width, int height) {
        this.grid = grid;

        setup();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(width, height);
        setVisible(true);
    }

    // METHODS
    /**
     * Prepares the window with all of the necessary panels, buttons, etc.
     * and assigns listeners to them
     */
    private void setup() {
        // isRoad
        // orientation
        // owner

    }
}
