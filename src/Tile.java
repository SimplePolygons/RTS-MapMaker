public class Tile {
    // CONSTANTS
    // orientation
    private int NORTH = 0;          // default
    private int EAST  = 1;
    private int SOUTH = 2;
    private int WEST  = 3;

    // owning player
    private int NONE       = 0;     // default
    private int PLAYER_ONE = 1;
    private int PLAYER_TWO = 2;
    private int SPECIAL    = 3;

    // ATTRIBUTES
    boolean isRoad;         // whether the Tile is a road tile or building tile
    int orientation;        // orientation of the tile (based on constants)
    int tileNum;            // number assigned to the tile
    int owner;              // owning player (or notice of a special tile)
    Grid owningGrid;        // Grid to which the Tile belongs to
    // TODO: barricades
    // TODO: mesh

    // CONSTRUCTORS

    /**
     * Basic Tile information, set to defaults
     * @param tileNum       number assigned to the tile
     * @param owningGrid    Grid to which the Tile belongs to
     */
    public Tile(int tileNum, Grid owningGrid) {
        this.isRoad = true;
        this.orientation = NORTH;
        this.tileNum = tileNum;
        this.owner = NONE;
        this.owningGrid = owningGrid;
        // TODO: barricades
        // TODO: mesh
    }

    // METHODS
}
