import javax.swing.BorderFactory;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Grid {
    // CONSTANTS
    // orientation
    protected final int NORTH = 0;          // default
    protected final int EAST  = 1;
    protected final int SOUTH = 2;
    protected final int WEST  = 3;
    // owning player
    protected final int NONE         = 0;     // default
    protected final int PLAYER_ONE   = 1;
    protected final int PLAYER_TWO   = 2;
    protected final int PLAYER_THREE = 3;
    protected final int PLAYER_FOUR  = 4;
    protected final int SPECIAL      = 5;
    // button details
    final MatteBorder DEFAULT_BORDER = BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK);
    final MatteBorder SELECTED_BORDER= BorderFactory.createMatteBorder(5, 5, 5, 5, Color.YELLOW);
    // mesh details
    protected final int NO_MESH = -1;
    // barricade types
    protected  final int NO_BARRICADES = 0;
    protected final String[] BARR_TYPE = { "None", "Light", "Medium", "Heavy" };    // first TYPE should always be NONE,
                                                     // the type constant is represented by the index of the Type String

    // GUI details
    protected boolean isPressed;
    protected boolean setTo;

    // ATTRIBUTES
    private int height = 10;        // number of rows of the grid
    private int width  = 14;        // number of the columns of the grid
    public Tile[][] tile;           // tiles on this grid
    public ArrayList<Tile> selectedTiles; // tiles that have been selected


    // CONSTRUCTORS
    /**
     * Default Grid with default size (10x14) and data
     */
    public Grid() {
        instantiate();
    }

    /**
     * Default Grid with set size
     */
    public Grid(int height, int width) {
        this.height = height;
        this.width = width;
        instantiate();
    }

    // METHODS
    /**
     * Fills in the Grid with tiles to defaults and assigns numbers
     */
    private void instantiate() {
        // instantiate the arraylist
        selectedTiles = new ArrayList<Tile>();

        // set the size of the grid
        this.tile = new Tile[height][width];

        int num = 0;
        for(int i = 0; i < tile.length; i++) {
            for(int j = 0; j < tile[i].length; j++) {
                // create the grid, assign number and owning grid
                tile[i][j] = new Tile(num++, this);
            }
        }

        isPressed = false;
        setTo = true;
    }

    /**
     * Gets the state of the til[x][y]
     * @param x         coordinate on the grid
     * @param y         coordinate on the grid
     * @return          isRoad
     */
    public boolean getTileIsRoad(int x, int y) {
        return tile[x][y].isRoad;
    }
    /**
     * Gets the height of the grid
     * @return      height
     */
    public int h() {
        return this.height;
    }
    /**
     * Gets the width of the grid
     * @return      width
     */
    public int w() {
        return this.width;
    }

    /**
     * Prints the Grid to the console
     * FORMAT: height*width grid, displaying tile numbers
     *         NOTE: every number starts with a tab
     */
    public void printGridNums() {
        for(int i = 0; i < tile.length; i++) {
            for(int j = 0; j < tile[i].length; j++) {
                System.out.print("\t" + tile[i][j].tileNum);
            }
            System.out.println();
        }
    }

    /**
     * Deselects all Tiles that are currently selected
     */
    public void deselectAllTiles() {
        while(selectedTiles.size() > 0)
            selectedTiles.get(0).setSelected(false);
    }
}
