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
        TextField tf_height = new TextField();
        add(tf_height);

        JLabel label_width = new JLabel("Width: ");
        add(label_width);
        TextField tf_width  = new TextField();
        add(tf_width);

        // grid reset/resize button
        JButton gridResize = new JButton("Resize grid");
        gridResize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("[STATUS]: Pressed");

                // remove previous version of the map
                if(grid != null) removeTiles();

                // create grid
                grid = new Grid();
                int h = grid.h();
                int w = grid.w();

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
