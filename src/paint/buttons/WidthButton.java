package paint.buttons;

import paint.Canvas;

import javax.swing.*;

public class WidthButton extends JButton {

    private final Canvas canvas;
    private int width;

    /**
     * add
     */
    public WidthButton(int width, Canvas canvas, String unicode) {
        this.width = width;
        this.canvas = canvas;
        setText(unicode + " " + width + "p");
    }

    public WidthButton(Canvas canvas, String string) {
        this.canvas = canvas;
        setText(string);
    }

    public void updateStroke() {
        canvas.updateStrokeWidth(width);
    }

    public void setWidth(int width) {
        this.width = width;
    }
}