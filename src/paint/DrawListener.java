package paint;

import paint.modes.Pencil;
import paint.modes.Primitive;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class DrawListener implements MouseListener, MouseMotionListener {
    private final Canvas canvas;
    private final JColorChooser chooser;
    private final JLabel location;
    /**
     * use offset to start drawing at tip of mouse pointer to counteract size of title and menu bars
     */

    private final int SIZE_OF_MENU_BAR = 23;
    private final int OFFSET = SIZE_OF_MENU_BAR + 25;
    private Graphics2D imageGraphics;
    private Primitive primitive;
    private boolean dropperSelected;
    private int lastX;
    private int lastY;
    private int firstX;
    private int firstY;

    /**
     * construct mouse event listener
     */
    public DrawListener(Canvas canvas, JColorChooser chooser, JLabel location) {
        this.canvas = canvas;
        this.chooser = chooser;
        this.location = location;
        primitive = new Pencil();
    }

    /**
     * change current primitive
     */
    public void updateShape(Primitive shape) {
        this.primitive = shape;
    }

    /**
     * select Dropper
     */
    public void selectDropper() {
        dropperSelected = true;
    }

    /**
     * Pick color from image
     */
    private void updateDropperColor(int x, int y) {
        // get color of pixel in image that clicked on
        BufferedImage image = canvas.getImage();
        int clr = image.getRGB(x, y);
        int red = (clr & 0x00ff0000) >> 16;
        int green = (clr & 0x0000ff00) >> 8;
        int blue = clr & 0x000000ff;
        Color color = new Color(red, green, blue);
        canvas.updateColor(color);
        chooser.setColor(color);
        chooser.getPreviewPanel().setBackground(color);
        dropperSelected = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY() - OFFSET;

        if (dropperSelected) {
            updateDropperColor(lastX, lastY);
        }

        firstX = lastX;
        firstY = lastY;


        imageGraphics = canvas.getImageGraphics();

        primitive.drawIt(canvas, imageGraphics, lastX, lastY, false, firstX, firstY, lastX, lastY);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY() - OFFSET;

        primitive.drawIt(canvas, imageGraphics, x, y, true, firstX, firstY, lastX, lastY);

        lastX = x;
        lastY = y;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        primitive.release(canvas, imageGraphics, e.getX(), e.getY() - OFFSET, false, firstX, firstY, lastX, lastY);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        location.setText(e.getX() + ", " + (e.getY() - SIZE_OF_MENU_BAR));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}