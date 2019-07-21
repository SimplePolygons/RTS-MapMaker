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
            }
        });
    }

    // METHODS
}
