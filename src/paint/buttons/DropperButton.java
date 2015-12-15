package paint.buttons;

import paint.DrawListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DropperButton extends JButton {


    public DropperButton(DrawListener listener) {
        setIcon(new ImageIcon(getClass().getResource("dropper1.png")));
        setBackground(null);
        setBorder(new EmptyBorder(0, 0, 0, 0));

        addActionListener(e -> listener.selectDropper());
    }
}