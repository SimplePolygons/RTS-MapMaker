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
        // main panel
        JPanel mainPanel = new JPanel(new GridLayout(4,1)); // NOTE: when adding new edit options, increment the rows parameter!
        this.add(mainPanel);

        // ============================================================= //
        // ========================== IS ROAD ========================== //
        // ============================================================= //
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
        // this.add(isRoad_panel,  BorderLayout.NORTH);
        mainPanel.add(isRoad_panel);
        // ============================================================= //
        // ======================== END IS ROAD ======================== //
        // ============================================================= //



        // ============================================================= //
        // ======================== ORIENTATION ======================== //
        // ============================================================= //
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
        // this.add(orientation_panel, BorderLayout.CENTER);
        mainPanel.add(orientation_panel);
        // ============================================================= //
        // ====================== END ORIENTATION ====================== //
        // ============================================================= //



        // ============================================================= //
        // =========================== OWNER =========================== //
        // ============================================================= //
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
        // this.add(owner_panel, BorderLayout.SOUTH);
        mainPanel.add(owner_panel);
        // ============================================================= //
        // ========================= END OWNER ========================= //
        // ============================================================= //



        // ============================================================= //
        // ========================= BARRICADES ======================== //
        // ============================================================= //
        JPanel barricades_panel = new JPanel(new GridLayout(1, 4));
        barricades_panel.add(new JLabel("Barricade:"));

        // barricade locations
        Checkbox[] cb_barr = new Checkbox[4];
        JPanel barr_cb_panel = new JPanel(new GridLayout(4,1));

        // display the new checkboxes
        barr_cb_panel.add(cb_barr[grid.NORTH] = new Checkbox("North", false));
        barr_cb_panel.add(cb_barr[grid.EAST] = new Checkbox("East", false));
        barr_cb_panel.add(cb_barr[grid.SOUTH] = new Checkbox("South", false));
        barr_cb_panel.add(cb_barr[grid.WEST] = new Checkbox("West", false));
        barricades_panel.add(barr_cb_panel);

        // barricade types
        JComboBox barr_cb = new JComboBox(grid.BARR_TYPE);
        barr_cb.setSelectedIndex(0);
        barricades_panel.add(barr_cb);

        // apply barricades button
        JButton apply_barricades = new JButton("Apply");
        barricades_panel.add(apply_barricades);
        // code
        apply_barricades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get the type of the Barricades
                int selected_type = barr_cb.getSelectedIndex();

                // go through all of the selected Tiles
                int size = grid.selectedTiles.size();
                for(int i = 0; i < size; i++) {
                    Tile t = grid.selectedTiles.get(i);
                    t.setBarr(selected_type, cb_barr);

                    // TODO: display changes && refresh GUI
                }
            }
        });

        mainPanel.add(barricades_panel);
        // ============================================================= //
        // ======================= END BARRICADES ====================== //
        // ============================================================= //



    }
}
