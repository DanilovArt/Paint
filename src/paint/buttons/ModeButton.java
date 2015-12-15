package paint.buttons;

import paint.modes.Primitive;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ModeButton extends JButton {

    private final Primitive shape;

    public ModeButton(Primitive shape, String unicode) {
        this.shape = shape;
        setText(unicode);
    }

    public ModeButton(Primitive shape, ImageIcon image) {
        this.shape = shape;
        setIcon(image);
        setBackground(null);
        setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    public Primitive getShape() {
        return shape;
    }
}