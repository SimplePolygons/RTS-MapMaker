import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import org.json.*;

public class JsonMaker implements ActionListener  {
    // ATTRIBUTES
    final String DEFAULT_NAME = "Map.json";

    TextField inputLocation;
    Grid grid;

    // CONSTRUCTORS
    public JsonMaker(Grid g, TextField tf) {
        this.grid = g;
        this.inputLocation = tf;
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
        if(grid == null) {
            System.out.println("[SYS]: Grid has not been applied");
        } else {
            // prepare JSON
            JSONArray jsonArr = new JSONArray();
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
                    t.put("MeshIdx", grid.tile[i][j].mesh_idx);

                    // barricades information
                    JSONObject barricades = new JSONObject();
                    if(grid.tile[i][j].isRoad) {
                        Barricade[] tmp = grid.tile[i][j].barr;

                        barricades.put("North", tmp[grid.NORTH].type);
                        barricades.put("East", tmp[grid.EAST].type);
                        barricades.put("South", tmp[grid.SOUTH].type);
                        barricades.put("West", tmp[grid.WEST].type);
                    } else {
                        Barricade[] tmp = grid.tile[i][j].barr;

                        barricades.put("North", grid.NO_BARRICADES);
                        barricades.put("East", grid.NO_BARRICADES);
                        barricades.put("South", grid.NO_BARRICADES);
                        barricades.put("West", grid.NO_BARRICADES);
                    }
                    t.put("Barricades", barricades);

                    // add to the array
                    tileArray.put(t);
                }
            }
            jsonObj.put("Grid", tileArray);
            jsonArr.put(jsonObj);

            // Write JSON file
            String name = inputLocation.getText().trim()+".json";
            if(name.equals(".json")) name = DEFAULT_NAME;
            try {
                BufferedWriter outStream = new BufferedWriter(new FileWriter(name));
                jsonArr.write(outStream);
                outStream.flush();
                outStream.close();
                System.out.println("[SYS]: File " + name + " has been written");
            } catch (Exception exc) {
                System.out.println("[ERR]: " + exc);
                try {
                    BufferedWriter outStream = new BufferedWriter(new FileWriter(DEFAULT_NAME));
                    jsonArr.write(outStream);
                    outStream.flush();
                    outStream.close();
                    System.out.println("[SYS]: wrote instead to default file: " + DEFAULT_NAME);
                } catch (Exception exc2) {
                    System.out.println("[ERR]: While writing to default file:\n" + exc2);
                }
            }
        }
    }
}
