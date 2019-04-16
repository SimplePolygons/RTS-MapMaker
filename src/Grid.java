public class Grid {
    // ATTRIBUTES
    private int height = 10;        // number of rows of the grid
    private int width  = 14;        // number of the columns of the grid
    private Tile[][] tile;          // tiles on this grid

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
        // set the size of the grid
        this.tile = new Tile[height][width];

        int num = 0;
        for(int i = 0; i < tile.length; i++) {
            for(int j = 0; j < tile[i].length; j++) {
                // create the grid, assign number and owning grid
                tile[i][j] = new Tile(num++, this);
            }
        }
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

}
