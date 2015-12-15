package paint.menu;

import paint.Canvas;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OpenListener implements ActionListener {
    private final Canvas canvas;
    private final JFileChooser chooser;

    public OpenListener(Canvas canvas) {
        this.canvas = canvas;
        chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        chooser.setAcceptAllFileFilterUsed(false);

        addFilter("PNG", "png");
        addFilter("BMP", "bmp");
        addFilter("JPG", "jpg");
        addFilter("JPEG", "jpeg");
        addFilter("GIF", "gif");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        if (file != null) {
            String filename = file.getPath();
            try {
                BufferedImage image = ImageIO.read(new File(filename));
                Color color = canvas.getColor();
                canvas.setImage(image);
                canvas.updateColor(color);

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void addFilter(String description, String... extensions) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(description, extensions);
        chooser.addChoosableFileFilter(filter);
    }
}