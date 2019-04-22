import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditGUI extends JFrame {
    // ATTRIBUTES
    Grid grid;
    GUI gui;

    // CONSTRUCTORS
    public EditGUI(Grid grid, GUI gui, int width, int height) {
        super("Editor");
        this.grid = grid;
        this.gui = gui;

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
        JPanel isRoad_panel = new JPanel(new FlowLayout());
        isRoad_panel.add(new JLabel("This tile is a:"));

        String[] isRoad_list = {"ROAD", "BUILDING"};
        JComboBox isRoad_cb = new JComboBox(isRoad_list);
        isRoad_cb.setSelectedIndex(0);
        isRoad_panel.add(isRoad_cb);

        JButton apply_isRoad = new JButton("Apply");
        isRoad_panel.add(apply_isRoad);
        //code
        apply_isRoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean val = (isRoad_cb.getSelectedIndex() == 0);
                int size = grid.selectedTiles.size();
                for(int i = 0; i < size; i++) {
                    Tile t = grid.selectedTiles.get(i);
                    t.setIsRoad(val);
                    t.displayIsRoad();
                }
                // refresh GUI
                gui.repaint();
                gui.revalidate();
            }
        });
        this.add(isRoad_panel,  BorderLayout.NORTH);


        // orientation
        JPanel orientation_panel = new JPanel(new FlowLayout());
        orientation_panel.add(new JLabel("Orientation:"));

        String[] orien_list = { "North", "East", "South", "West" };
        JComboBox orien_cb = new JComboBox(orien_list);
        orien_cb.setSelectedIndex(0);
        orientation_panel.add(orien_cb);

        JButton apply_orientation = new JButton("Apply");
        orientation_panel.add(apply_orientation);
        // code
        apply_orientation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = grid.selectedTiles.size();
                for(int i = 0; i < size; i++) {
                    Tile t = grid.selectedTiles.get(i);
                    t.setOrientation(orien_cb.getSelectedIndex());
                    t.displayOrientation();
                }
                // refresh GUI
                gui.repaint();
                gui.revalidate();
            }
        });
        this.add(orientation_panel, BorderLayout.CENTER);

        // owner
        JPanel owner_panel = new JPanel(new FlowLayout());
        owner_panel.add(new JLabel("Owner:"));

        String[] owner_list = { "Default", "Player 1", "Player 2", "Special" };
        JComboBox owner_cb = new JComboBox(owner_list);
        owner_cb.setSelectedIndex(0);
        owner_panel.add(owner_cb);

        JButton apply_owner = new JButton("Apply");
        owner_panel.add(apply_owner);
        // code
        apply_owner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = grid.selectedTiles.size();
                for(int i = 0; i < size; i++) {
                    Tile t = grid.selectedTiles.get(i);
                    t.setOwner(owner_cb.getSelectedIndex());
                    t.displayOwner();
                }
                // refresh GUI
                gui.repaint();
                gui.revalidate();
            }
        });
        this.add(owner_panel, BorderLayout.SOUTH);


    }
}
