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
                System.out.println("[STATUS]: Apply all button clicked");

                // ISROAD
                boolean val = (edit.isRoad_cb.getSelectedIndex() == 0);
                int size = edit.grid.selectedTiles.size();
                for(int i = 0; i < size; i++) {
                    Tile t = edit.grid.selectedTiles.get(i);
                    t.setIsRoad(val);
                    t.displayIsRoad();
                }

                //


                // refresh GUI
                edit.gui.repaint();
                edit.gui.revalidate();
            }
        });
    }

    // METHODS
}
