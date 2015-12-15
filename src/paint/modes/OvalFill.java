package paint.modes;

import java.awt.*;

public class OvalFill extends Primitive {

    @Override
    public void draw(Graphics2D g2d) {
        g2d.fillOval(x, y, width, height);
    }
}