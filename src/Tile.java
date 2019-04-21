import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tile extends JButton {
    // ATTRIBUTES
    // button related
    boolean isTileSelected;

    // data related
    Grid owningGrid;        // Grid to which the Tile belongs to
    boolean isRoad;         // whether the Tile is a road tile or building tile
    int orientation;        // orientation of the tile (based on constants)
    int tileNum;            // number assigned to the tile
    int owner;              // owning player (or notice of a special tile)
    // TODO: barricades
    // TODO: mesh

    // CONSTRUCTORS

    /**
     * Basic Tile information, set to defaults
     * @param tileNum       number assigned to the tile
     * @param owningGrid    Grid to which the Tile belongs to
     */
    public Tile(int tileNum, Grid owningGrid) {
        isTileSelected = false;
        setBorder(owningGrid.DEFAULT_BORDER);

        this.owningGrid = owningGrid;
        this.isRoad = true;
        this.orientation = owningGrid.NORTH;
        this.tileNum = tileNum;
        this.owner = owningGrid.NONE;
        // TODO: barricades
        // TODO: mesh

        // JButton specifications
        setPreferredSize(new Dimension(50, 50));
        setMinimumSize(new Dimension(50, 50));
        setMaximumSize(new Dimension(51, 51));

        // add actionListener
        addAL();

    }

    // METHODS

    /**
     * Adds ActionListener to the Tile, which contains code for when the
     * Tile is pressed.
     */
    public void addAL() {
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                System.out.println("[STATUS]: Pressed Tile " + tileNum);
                // TODO: onclick actions

                // System.out.println("Border: " + getBorder().getClass());
                // test button
                if(isTileSelected = !isTileSelected) {
                    setBorder(owningGrid.SELECTED_BORDER);
                }else{
                    setBorder(owningGrid.DEFAULT_BORDER);
                }
            }
        };
        addActionListener(listener);
    }
}
