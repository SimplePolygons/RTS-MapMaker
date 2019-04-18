import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

public class GUI extends JFrame {
    private static Grid grid;
    private static JPanel grid_panel;
    // JScrollPane scrollPane;

    public GUI() {
        super("Map Maker");

        JPanel settings_panel = new JPanel();
        grid_panel = new JPanel(new GridLayout());

        /*
        scrollPane = new JScrollPane(grid_panel);
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setBounds(5, 5, 500, 500);
        */

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

                // remove previous version of the map
                if(grid != null) removeTiles();

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
                grid_panel.setPreferredSize(new Dimension(h*50, w*50));
                grid_panel.setMaximumSize(new Dimension(h*50, w*50));
                grid_panel.setMinimumSize(new Dimension(h*50, w*50));

                // display Grid
                displayGrid(grid_panel, h, w);

                // refresh GUI
                repaint();
                revalidate();
            }
        });

        settings_panel.add(gridResize);
        this.add(settings_panel, BorderLayout.NORTH);
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

        this.add(jp, BorderLayout.CENTER);
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
        this.remove(grid_panel);
        System.out.println("[STATUS]: Grid removed");
    }


}
