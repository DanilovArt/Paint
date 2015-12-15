package paint.modes;

import java.awt.*;

public class RectFill extends Primitive {

    @Override
    public void draw(Graphics2D g2d) {
        g2d.fillRect(x, y, width, height);
    }
}