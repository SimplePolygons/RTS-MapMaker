import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import org.json.*;

public class JsonMaker implements ActionListener  {
    // ATTRIBUTES
    final String DEFAULT_NAME = "Map";

    String name;
    Grid grid;

    // CONSTRUCTORS
    public JsonMaker() {
        this.name = DEFAULT_NAME + ".json";
        this.grid = null;
    }
    public JsonMaker(Grid grid) {
        this.name = DEFAULT_NAME + ".json";
        this.grid = grid;
    }
    public JsonMaker(String name, Grid grid) {
        this.name = name + ".json";
        this.grid = grid;
    }

    // METHODS

    /**
     * Applies a new Grid object to this JsonMaker for parsing into a JSON file
     * @param g     Grid object
     */
    public void applyNewGrid(Grid g) {
        this.grid = g;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(grid != null) {

            // TODO: check if the current file name is good
            // set to default if not

            // prepare JSON
            JSONObject jsonObj = new JSONObject();

            // height&width
            jsonObj.put("Height", grid.h());
            jsonObj.put("Width", grid.w());

            // Grid
            // jsonObj.put("Grid", grid);   // this only writes the object pointer, don't use
            JSONArray tileArray = new JSONArray();
            for(int i = 0; i < grid.h(); i++) {
                for(int j = 0; j < grid.w(); j++) {
                    // tile information
                    JSONObject t = new JSONObject();
                    t.put("IsRoad", grid.tile[i][j].isRoad);
                    t.put("TileNum", grid.tile[i][j].tileNum);
                    t.put("Orientation", grid.tile[i][j].orientation);
                    t.put("Owner", grid.tile[i][j].owner);

                    // add to the array
                    tileArray.put(t);
                }
            }

            jsonObj.put("Grid", tileArray);

            try {
                BufferedWriter outStream = new BufferedWriter(new FileWriter(name));
                jsonObj.write(outStream);
                outStream.flush();
                System.out.println("[SYS]: File " + name + " has been written");
            } catch (Exception exc) {
                System.out.println("[ERR]: " + exc);
            }
        } else {
            System.out.println("[SYS]: Grid has not been applied");
        }
    }
}
