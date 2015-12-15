package paint.modes;

import paint.Canvas;

import java.awt.*;

public class Eraser extends Primitive {

    private final static int ERASER_HEIGHT = 30;
    private final Stroke eraserStroke = new BasicStroke(ERASER_HEIGHT, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

    /**
     * draw eraser
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.BLACK);

        // adjust so pointer is in middle of eraser circle
        int adjust = ERASER_HEIGHT / 2;

        // use width twice to ensure it is a circle
        g2d.drawOval(x - adjust, y - adjust, width, width);
    }

    /**
     * calculate eraser cords
     */
    @Override
    public void drawIt(Canvas canvas, Graphics2D g2d, int x, int y, boolean temp, int firstX, int firstY, int lastX, int lastY) {
        g2d.setColor(Canvas.BKGD_COLOR);
        g2d.setStroke(eraserStroke);
        g2d.drawLine(lastX, lastY, x, y);
        set(x, y, ERASER_HEIGHT, ERASER_HEIGHT);
        canvas.setTempPrimitive(this);
        canvas.resetGraphics();

        canvas.repaint();
    }

    /**
     * finish erase when mouse released
     */
    @Override
    public void release(Canvas canvas, Graphics2D g2d, int x, int y, boolean b, int firstX, int firstY, int lastX, int lastY) {
        canvas.setTempPrimitive(null);
        canvas.repaint();
    }
}