import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplyAllButton extends JButton {
    // ATTRIBUTES
    EditGUI edit;

    // CONSTRUCTORS
    public ApplyAllButton(String text, EditGUI edit) {
        super.setText(text);
        this.edit = edit;

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // System.out.println("[STATUS]: Apply all button clicked");

                int size = edit.grid.selectedTiles.size();
                boolean val = (edit.isRoad_cb.getSelectedIndex() == 0);     // isRoad

                for(int i = 0; i < size; i++) {
                    // ISROAD
                    Tile t = edit.grid.selectedTiles.get(i);
                    t.setIsRoad(val);
                    t.displayIsRoad();

                    // ORIENTATION
                    t.setOrientation(edit.orien_cb.getSelectedIndex());
                    t.displayOrientation();

                    // OWNER
                    t.setOwner(edit.owner_cb.getSelectedIndex());
                    t.displayOwner();

                    // MESH
                    t.setMesh_idx(edit.mesh_cb.getSelectedIndex());
                    // TODO: display changes


                }

                // refresh GUI
                edit.gui.repaint();
                edit.gui.revalidate();
            }
        });
    }

    // METHODS
}
