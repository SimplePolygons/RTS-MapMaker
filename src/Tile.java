import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile extends JButton {
    // ATTRIBUTES
    // button related
    boolean isTileSelected;
    private Tile me = this; // refference to self

    // data related
    Grid owningGrid;        // Grid to which the Tile belongs to
    boolean isRoad;         // whether the Tile is a road tile or building tile
    int orientation;        // orientation of the tile (based on constants)
    int tileNum;            // number assigned to the tile
    int owner;              // owning player (or notice of a special tile)
    int mesh_idx;           // index of the mesh that will be used for this Tile in the game

    Barricade[] barr;       // array of barricades, size 4, which should
                            // be the same as the number of orientation directions

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
        this.mesh_idx = owningGrid.NO_MESH;
        this.barr = new Barricade[4];
        setBarr(owningGrid.NO_BARRICADES);

        // JButton specifications
        setPreferredSize(new Dimension(50, 50));
        setMinimumSize(new Dimension(50, 50));
        setMaximumSize(new Dimension(50, 50));

        // add actionListener
        // addAL();

        // add mouseListener
        addML();
    }

    // METHODS

    /**
     * Sets the isRoad value of the Tile.
     * @param   isRoad      the new value of isRoad
     */
    public void setIsRoad(boolean isRoad) {
        this.isRoad = isRoad;
    }
    /**
     * Sets orientation of the Tile. The orientation must correspond to
     * a constant in the Grid class
     * @param   orientation         the new orientation of the tile
     */
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
    /**
     * Sets the owner value of the Tile.
     * @param   owner      the new owner of the tile
     */
    public void setOwner(int owner) {
        this.owner = owner;
    }
    /**
     * Sets the mesh index of the Tile
     * @param mesh_idx      index to be set
     */
    public void setMesh_idx(int mesh_idx) {
        this.mesh_idx = mesh_idx;
    }

    /**
     * Sets the Barr value of the Tile by applying the type
     * to all Barricades. Used to set the value to default
     * @param type      Type of the Barricades
     */
    public void setBarr(int type) {
        for(int i = 0; i < barr.length; i++) {
            barr[i] = new Barricade(type);
        }
    }
    /**
     * Sets the Barr value of the Tile by applying the type to all
     * Barricades checked as true in the Checkbox[] cb.
     * @param type      Type of the Barricades
     * @param cb        Array of checkboxes which corresponds to the indexes of the
     *                  Barricades which should be changed
     */
    public void setBarr(int type, Checkbox[] cb) {
        for(int i = 0; i < barr.length; i++) {
            if(cb[i].getState())
                barr[i] = new Barricade(type);
        }
    }

    public void setSelected(boolean s) {
        if(s) {
            if(!isTileSelected) {
                System.out.println("[STATUS]: Selected Tile " + tileNum);
                setBorder(owningGrid.SELECTED_BORDER);
                owningGrid.selectedTiles.add(me);
                isTileSelected = true;
            }
        }else{
            if(isTileSelected) {
                System.out.println("[STATUS]: Deselected Tile " + tileNum);
                setBorder(owningGrid.DEFAULT_BORDER);
                owningGrid.selectedTiles.remove(me);
                isTileSelected = false;
            }
        }
    }

    /**
     * Adds ActionListener to the Tile, which contains code for when the
     * Tile is pressed.
     */
    public void addAL() {
        ActionListener listener = new ActionListener(){
            /**
             * Toggles tile selected and adds/removes it to the list of selected tiles
             * @param event
             */
            @Override
            public void actionPerformed(ActionEvent event){
                setSelected(!isTileSelected);
                /*
                if(isTileSelected = !isTileSelected) {
                    System.out.println("[STATUS]: Selected Tile " + tileNum);
                    setBorder(owningGrid.SELECTED_BORDER);
                    owningGrid.selectedTiles.add(me);
                }else{
                    System.out.println("[STATUS]: Deselected Tile " + tileNum);
                    setBorder(owningGrid.DEFAULT_BORDER);
                    owningGrid.selectedTiles.remove(me);
                }
                */
            }
        };
        addActionListener(listener);
    }

    /**
     * Adds MouseListeners to this Tile, which contain the code for when mouse drags
     * over or leaves the Tile
     */
    public void addML() {
        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // System.out.println("[SYS]: mouse CLICKED over: " + tileNum);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("[SYS]: mouse PRESSED over: " + tileNum);
                owningGrid.isPressed = true;

                setSelected(!isTileSelected);

                owningGrid.setTo = isTileSelected;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("[SYS]: mouse RELEASED over: " + tileNum);
                owningGrid.isPressed = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // System.out.println("[SYS]: mouse ENTERED over: " + tileNum);
                if(owningGrid.isPressed) {
                    System.out.println(tileNum);
                    setSelected(owningGrid.setTo);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // System.out.println("[SYS]: mouse EXITED over: " + tileNum);
            }
        };
        addMouseListener(mouseListener);
    }

    /**
     * Changes the Tile background color based on the state of isRoad attribute
     */
    public void displayIsRoad() {
        if(this.isRoad) {
            setBackground(Color.WHITE);
        } else {
            setBackground(Color.BLACK);
        }
    }

    /**
     * Displays the Icon, which indicates the direction of the tile
     */
    public void displayOrientation() {
        if(this.orientation == this.owningGrid.NORTH) {
            setIcon(new ImageIcon("src/Icons/Arrow_North.png"));
        } else if(this.orientation == this.owningGrid.EAST) {
            setIcon(new ImageIcon("src/Icons/Arrow_East.png"));
        } else if(this.orientation == this.owningGrid.SOUTH) {
            setIcon(new ImageIcon("src/Icons/Arrow_South.png"));
        } else if(this.orientation == this.owningGrid.WEST) {
            setIcon(new ImageIcon("src/Icons/Arrow_West.png"));
        } else {
            System.out.println("[ERROR]: unknown orientation");
        }
    }

    /**
     * Displays the text, indicating the owner of the tile
     */
    public void displayOwner() {
        if(this.owner == this.owningGrid.PLAYER_ONE) {
            setText("P1");
        } else if(this.owner == this.owningGrid.PLAYER_TWO) {
            setText("P2");
        } else if(this.owner == this.owningGrid.SPECIAL) {
            setText("S");
        } else {
            setText("D");
        }
    }
}
