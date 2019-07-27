import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GUI extends JFrame {
    private Grid grid;
    private JPanel grid_panel;
    private JScrollPane scrollPane;
    private EditGUI edit;
    private GUI me = this;  // reference to self

    public JsonMaker jsonmkr;

    public GUI() {
        super("Map Maker");

        JPanel settings_panel = new JPanel();
        grid_panel = new JPanel(new GridLayout());
        settings_panel.setLayout(new FlowLayout());


        // height and width settings
        JLabel label_height = new JLabel("Height: ");
        settings_panel.add(label_height);
        TextField tf_height = new TextField(4);
        settings_panel.add(tf_height);

        JLabel label_width = new JLabel("Width: ");
        settings_panel.add(label_width);
        TextField tf_width  = new TextField(4);
        settings_panel.add(tf_width);

        // grid reset/resize button
        JButton gridResize = new JButton("Resize grid");
        gridResize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("[STATUS]: Pressed");

                if(grid != null) {
                    // remove previous version of the map
                    removeTiles();
                    remove(edit);
                } else {
                    // add the RGM button
                    addRGMButton(settings_panel);
                }

                // get values for height and width
                int h = isStringNatural(tf_height.getText());
                int w = isStringNatural(tf_width.getText());

                // create grid
                if(h < 1 || w < 1) {
                    grid = new Grid();
                    h = grid.h();
                    w = grid.w();
                } else {
                    grid = new Grid(h, w);
                }
                grid_panel.setLayout(new GridLayout(h, w));

                // display Grid
                displayGrid(grid_panel, h, w);

                // display editor panel
                add(edit = new EditGUI(grid, me, 350, 400), BorderLayout.EAST);

                // refresh GUI
                repaint();
                revalidate();

                // apply the grid to the JsonMaker
                jsonmkr.applyNewGrid(grid);
            }
        });

        settings_panel.add(gridResize);
        this.add(settings_panel, BorderLayout.NORTH);

        // Save settings
        JPanel save_panel = new JPanel(new FlowLayout());
        save_panel.add(new JLabel("File name: "));

        TextField tf_file_name = new TextField(10);
        save_panel.add(tf_file_name);

        save_panel.add(new JLabel(".json"));

        JButton save_button = new JButton("SAVE");
        save_panel.add(save_button);
        this.add(save_panel, BorderLayout.SOUTH);

        save_button.addActionListener(jsonmkr = new JsonMaker(grid, tf_file_name));
    }

    /**
     * Adds the random generated map button to the GUI.
     * The Button generates rewrites the current Grid randomly, only ignoring barricades
     * and owners (might change in the future).
     * currently, it sets: isRoad, Orientation
     * @param jp            settings JPanel, to which the
     */
    private void addRGMButton(JPanel jp) {
        JButton random_button = new JButton("Random Grid");
        random_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random generator = new Random();

                // go through all Tiles
                for(Tile[] row : grid.tile) {
                    for(Tile t : row) {

                        // isRoad
                        t.setIsRoad(generator.nextBoolean());

                        // orientation
                        t.setOrientation(generator.nextInt(4));

                        // owner
                        t.setOwner(grid.NONE);

                        // barricades
                        // t.setBarr(grid.NO_BARRICADES);
                        for(int orien = 0; orien < 4; orien++) {
                            t.barr[orien] = new Barricade(generator.nextInt(grid.BARR_TYPE.length));
                        }

                        t.displayTile();
                    }
                }
            }
        });

        jp.add(random_button);
    }

    /**
     * Evaluates whether a String is a positive integer.
     * @param s             String that will be evaluated
     * @return              positive integer or 0, if the integer is non-positive or s is invalid
     */
    private int isStringNatural(String s) {
        int res = 0;

        // try converting the string to int
        try {
            res = Integer.parseInt(s.trim());
        } catch (Exception e) {
            // in case the string is invalid, raise error
            System.out.println("[SYS]: Invalid input, should be int");
            System.out.println("[ERR]: " + e);
        }

        // handle non-positive numbers
        if(res < 1) {
            System.out.println("[SYS]: Setting to default");
            res = 0;
        }

        return res;
    }

    /**
     * Displays the grid with Buttons on the GUI
     * @param h             height of the grid
     * @param w             width of the grid
     */
    private void displayGrid(JPanel jp, int h, int w) {
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                jp.add(grid.tile[i][j]);
            }
        }

        this.add(scrollPane = new JScrollPane(jp), BorderLayout.CENTER);
        System.out.println("[STATUS]: Grid displayed");
    }

    /**
     * Removes the Tile buttons
     */
    private void removeTiles() {
        for(int i = 0; i < grid.tile.length; i++) {
            for(int j = 0; j < grid.tile[i].length; j++) {
                grid_panel.remove(grid.tile[i][j]);
            }
        }
        scrollPane.remove(grid_panel);
        this.remove(scrollPane);
        System.out.println("[STATUS]: Grid removed");
    }


}
