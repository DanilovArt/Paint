package paint.modes;

import paint.Canvas;

import java.awt.*;

public class Pencil extends Primitive {

    @Override
    public void draw(Graphics2D g2d) {
    }

    @Override
    public void drawIt(Canvas canvas, Graphics2D g2d, int x, int y, boolean temp, int firstX, int firstY, int lastX, int lastY) {
        g2d.drawLine(lastX, lastY, x, y);
        canvas.repaint();
    }
}