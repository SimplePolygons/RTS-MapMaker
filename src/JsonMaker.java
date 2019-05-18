import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import libs.java.javax.json.*;

public class JsonMaker implements ActionListener {
    public JsonMaker(Grid g){}

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: try if the current file name is good
        // set to default if not

        // prepare JSON
        JsonObject json = Json.createObjectBuilder()
                .add("heigth", )

        // TODO: save grid to .json
    }
}
