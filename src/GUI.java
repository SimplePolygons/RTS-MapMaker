import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {
    private static int[][] grid;
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
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                JOptionPane.showMessageDialog(null, String.format("%s", event.getActionCommand()));
                // funkcija turn
                // System.out.println(event.getActionCommand());
                for(int i = 0; i < 5; i++){
                    for(int j = 0; j < 5; j++){
                        if(event.getSource() == tiles[i][j]){
                            System.out.println("Tile pressed at: (" + i + ", " + j+ ")");
                            break;
                        }
                    }
                }
            }
        };

        // create grid
        grid = new int[5][5];
        tiles = new JButton[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(grid[i][j] == 0){
                    tiles[i][j] = new JButton(" ");
                    // tiles[i][j].setSize(new Dimension(50,50));
                    tiles[i][j].setBackground(Color.BLACK);
                }else{
                    tiles[i][j] = new JButton(" ");
                    // tiles[i][j].setSize(new Dimension(50,50));
                    tiles[i][j].setBackground(Color.WHITE);
                }
                tiles[i][j].addActionListener(listener);
                add(tiles[i][j]);
            }
        }
    }


}
