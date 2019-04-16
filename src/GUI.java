import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {
    private static Grid grid;
    private static JButton[][] tiles;

    public GUI() {
        super("Map Maker");
        setLayout(new FlowLayout());
        /*
        gumb = new JButton("NEW GAME");
        gumb.setBackground(Color.WHITE);    //to je za barvo gumba, preden je kliknen
        gumb.setForeground(Color.BLUE); // to je za besedilo
        add(gumb);
        */

        // create grid
        grid = new Grid();
        int h = grid.h();
        int w = grid.w();

        tiles = new JButton[h][w];          // set up the corresponding array of Buttons

        // add action listener
        // TODO: improve
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                // JOptionPane.showMessageDialog(null, String.format("%s", event.getActionCommand()));
                // System.out.println(event.getActionCommand());

                // get the location of the pressed button
                int x = 0;
                int y = 0;

                outerloop:
                for(int i = 0; i < h; i++){
                    for(int j = 0; j < w; j++){
                        if(event.getSource() == tiles[i][j]){
                            x = i;
                            y = j;
                            break outerloop;
                        }
                    }
                }
                System.out.println("Tile pressed at: (" + x + ", " + y + ")");

                // TODO: actions
            }
        };

        displayGrid(h, w, listener);
    }

    /**
     * Displays the grid with Buttons
     * @param h             height of the grid
     * @param w             width of the grid
     * @param listener      listener for when a button is pressed
     */
    private void displayGrid(int h, int w, ActionListener listener) {
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if(grid.getTileIsRoad(i,j)){
                    tiles[i][j] = new JButton(" ");
                    // tiles[i][j].setSize(new Dimension(50,50));
                    tiles[i][j].setBackground(Color.WHITE);
                }else{
                    tiles[i][j] = new JButton(" ");
                    // tiles[i][j].setSize(new Dimension(50,50));
                    tiles[i][j].setBackground(Color.BLACK);
                }
                tiles[i][j].addActionListener(listener);
                add(tiles[i][j]);
            }
        }
    }


}
