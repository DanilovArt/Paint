package paint.modes;

import paint.Canvas;

import java.awt.*;

public class Line extends Primitive {

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawLine(x, y, width, height);
    }

    @Override
    public void drawIt(Canvas canvas, Graphics2D g2d, int x, int y, boolean temp, int firstX, int firstY, int lastX, int lastY) {
        set(lastX, lastY, firstX, firstY);

        if (temp) {
            canvas.setTempPrimitive(this);
        } else {
            canvas.setTempPrimitive(null);
            draw(g2d);
        }

        canvas.repaint();
    }
}