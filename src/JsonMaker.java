import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.json.*;

public class JsonMaker implements ActionListener  {
    // ATTRIBUTES
    final String DEFAULT_NAME = "Map";

    String name;
    Grid grid;

    // CONSTRUCTORS
    public JsonMaker(String name, Grid grid) {
        this.name = name;
        this.grid = grid;
    }

    // METHODS
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: try if the current file name is good
        // set to default if not

        // prepare JSON
        JSONObject jsonObj = new JSONObject();

        // TODO: save grid to .json
    }
}
