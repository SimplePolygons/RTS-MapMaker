import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditGUI extends JPanel {
    // ATTRIBUTES
    Grid grid;
    GUI gui;
    // components
    protected JComboBox isRoad_cb;
    protected JComboBox orien_cb;
    protected JComboBox owner_cb;
    protected JComboBox mesh_cb;
    protected JComboBox barr_cb;
    protected Checkbox[] cb_barr;

    // CONSTRUCTORS
    public EditGUI(Grid grid, GUI gui, int width, int height) {
        // super("Editor");
        this.grid = grid;
        this.gui = gui;

        setup();

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
        JPanel mainPanel = new JPanel(new GridLayout(6,1)); // NOTE: when adding new edit options, increment the rows parameter!
        this.add(mainPanel);

        // ============================================================= //
        // ========================== IS ROAD ========================== //
        // ============================================================= //
        JPanel isRoad_panel = new JPanel(new FlowLayout());
        isRoad_panel.add(new JLabel("This tile is a:"));

        String[] isRoad_list = {"ROAD", "BUILDING"};
        isRoad_cb = new JComboBox(isRoad_list);
        isRoad_cb.setSelectedIndex(0);
        isRoad_panel.add(isRoad_cb);
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
        orien_cb = new JComboBox(orien_list);
        orien_cb.setSelectedIndex(0);
        orientation_panel.add(orien_cb);
        mainPanel.add(orientation_panel);
        // ============================================================= //
        // ====================== END ORIENTATION ====================== //
        // ============================================================= //



        // ============================================================= //
        // =========================== OWNER =========================== //
        // ============================================================= //
        JPanel owner_panel = new JPanel(new FlowLayout());
        owner_panel.add(new JLabel("Owner:"));

        String[] owner_list = { "Default", "Player 1", "Player 2", "Player 3", "Player 4", "Special" };
        owner_cb = new JComboBox(owner_list);
        owner_cb.setSelectedIndex(0);
        owner_panel.add(owner_cb);
        mainPanel.add(owner_panel);
        // ============================================================= //
        // ========================= END OWNER ========================= //
        // ============================================================= //



        // ============================================================= //
        // =========================== MESH ============================ //
        // ============================================================= //
        JPanel mesh_panel = new JPanel(new FlowLayout());
        mesh_panel.add(new JLabel("Mesh:"));

        // TODO: real time list setting
        // Get the list of mesh names from a directory (like DEMO)
        // String[] mesh_list_DEMO = {"BT_Example_001", "BT_Example_002", "RT_Example_001", "RT_Example_002"};
        // then separate the list into two: roads and buildings.
        // data should be searched each time the isRoad_cb changes but only applied to tiles of the same type
        // String[] mesh_roads_list = { "RT_Example_001", "RT_Example_002" };
        // String[] mesh_buildings_list = { "BT_Example_001", "BT_Example_002" };

        String[] mesh_list_DEMO = {"BT_Example_001", "BT_Example_002", "RT_Example_001", "RT_Example_002"};
        mesh_cb = new JComboBox(mesh_list_DEMO);
        mesh_cb.setSelectedIndex(0);
        mesh_panel.add(mesh_cb);
        mainPanel.add(mesh_panel);
        // ============================================================= //
        // ========================= END MESH ========================== //
        // ============================================================= //



        // ============================================================= //
        // ========================= BARRICADES ======================== //
        // ============================================================= //
        JPanel barricades_panel = new JPanel(new GridLayout(1, 4));
        barricades_panel.add(new JLabel("Barricade:"));

        // barricade locations
        cb_barr = new Checkbox[4];
        JPanel barr_cb_panel = new JPanel(new GridLayout(4,1));

        // display the new checkboxes
        barr_cb_panel.add(cb_barr[grid.NORTH] = new Checkbox("North", false));
        barr_cb_panel.add(cb_barr[grid.EAST] = new Checkbox("East", false));
        barr_cb_panel.add(cb_barr[grid.SOUTH] = new Checkbox("South", false));
        barr_cb_panel.add(cb_barr[grid.WEST] = new Checkbox("West", false));
        barricades_panel.add(barr_cb_panel);

        // barricade types
        barr_cb = new JComboBox(grid.BARR_TYPE);
        barr_cb.setSelectedIndex(0);
        barricades_panel.add(barr_cb);

        mainPanel.add(barricades_panel);
        // ============================================================= //
        // ======================= END BARRICADES ====================== //
        // ============================================================= //


        // ============================================================= //
        // ======================= APPLY ALL BUTTON ==================== //
        // ============================================================= //

        ApplyAllButton apply_all = new ApplyAllButton("Apply", this);
        mainPanel.add(apply_all);

        // ============================================================= //
        // ===================== END APPLY ALL BUTTON ================== //
        // ============================================================= //
    }
}
