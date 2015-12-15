package paint;

import paint.modes.Primitive;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel {
    public final static Color BKGD_COLOR = Color.WHITE;
    private BufferedImage image; // rastr graphic
    private Graphics2D imageGraphics; //vector graphic
    private Color color;
    private Stroke stroke;
    private Primitive tempPrimitive;


    /**
     * construct Canvas
     * (Canvas is a paint area)
     */
    public Canvas(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        imageGraphics = (Graphics2D) image.getGraphics();

        // clear screen and start it at white instead of black
        updateColor(BKGD_COLOR);
        imageGraphics.fillRect(0, 0, width, height);
        updateColor(Color.BLACK);
        updateStrokeWidth(2);
    }

    /**
     * paint all graphic primitives (from imageGraphics and temp figure)
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(color);
        g2d.setStroke(stroke);

        g2d.drawImage(image, 0, 0, null);

        if (tempPrimitive != null) {
            tempPrimitive.draw(g2d);
        }
    }

    /**
     * get current color
     */
    public Color getColor() {
        return color;
    }

    /**
     * change current color
     */
    // method called by JColorChooser ChangeListener
    public void updateColor(Color color) {
        this.color = color;
        imageGraphics.setColor(color);
    }

    /**
     * change size of our brush (or pencil)
     */
    // method called by WidthButton ActionListener
    public void updateStrokeWidth(int width) {
        stroke = new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        imageGraphics.setStroke(stroke);
    }

    /**
     * return our graphic primitives as single image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * change our Canvas graphic to graphic from image
     */
    // on Open, set image to chosen image
    public void setImage(BufferedImage image) {
        this.image = image;
        imageGraphics = (Graphics2D) image.getGraphics();
        repaint();
    }

    /**
     * return our graphic primitives
     */
    public Graphics2D getImageGraphics() {
        return imageGraphics;
    }

    /**
     * set if current painting primitive is temp
     */
    // when repaint, paint tempPrimitive if one exists
    public void setTempPrimitive(Primitive primitive) {
        tempPrimitive = primitive;
    }

    /**
     * set color and stroke after eraser
     */
    public void resetGraphics() {
        imageGraphics.setColor(color);
        imageGraphics.setStroke(stroke);
    }
}