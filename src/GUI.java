import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class GUI extends JFrame {
    private static Grid grid;

    public GUI() {
        super("Map Maker");
        setLayout(new FlowLayout());

        // height and width settings
        JLabel label_height = new JLabel("Height: ");
        add(label_height);
        TextField tf_height = new TextField(4);
        add(tf_height);

        JLabel label_width = new JLabel("Width: ");
        add(label_width);
        TextField tf_width  = new TextField(4);
        add(tf_width);

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

                // display Grid
                displayGrid(h, w);

                // refresh GUI
                repaint();
                revalidate();
            }
        });
        add(gridResize);

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
     * Displays the grid with Buttons
     * @param h             height of the grid
     * @param w             width of the grid
     */
    private void displayGrid(int h, int w) {
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                add(grid.tile[i][j]);
            }
        }
        System.out.println("[STATUS]: Grid displayed");
    }

    /**
     * Removes the Tile buttons
     */
    private void removeTiles() {
        for(int i = 0; i < grid.tile.length; i++) {
            for(int j = 0; j < grid.tile[i].length; j++) {
                remove(grid.tile[i][j]);
            }
        }
        System.out.println("[STATUS]: Grid removed");
    }


}
