package paint.modes;

import paint.Canvas;

import java.awt.*;

public abstract class Primitive {
    int x;
    int y;
    int width;
    int height;

    /**
     * add  primitive to graphics
     */
    public abstract void draw(Graphics2D g2d);

    /**
     * calculate primitive parameters
     */
    public void drawIt(Canvas canvas, Graphics2D g2d, int x, int y, boolean temp, int firstX, int firstY, int lastX, int lastY) {
        int width = x - firstX;
        int height = y - firstY;
        x = width > 0 ? firstX : lastX;
        y = height > 0 ? firstY : lastY;

        set(x, y, Math.abs(width), Math.abs(height));

        if (temp) {
            canvas.setTempPrimitive(this);
        } else {
            canvas.setTempPrimitive(null);
            draw(g2d);
        }

        canvas.repaint();
    }

    void set(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * add primitives when mouse released
     */
    public void release(Canvas canvas, Graphics2D g2d, int x, int y, boolean b, int firstX, int firstY, int lastX, int lastY) {
        drawIt(canvas, g2d, x, y, false, firstX, firstY, lastX, lastY);
    }
}