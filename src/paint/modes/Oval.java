package paint.modes;

import java.awt.*;

public class Oval extends Primitive {

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawOval(x, y, width, height);
    }
}