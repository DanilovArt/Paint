package paint.modes;

import java.awt.*;

public class Rect extends Primitive {

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawRect(x, y, width, height);
    }
}