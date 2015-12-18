package paint;

import paint.buttons.DropperButton;
import paint.buttons.ModeButton;
import paint.buttons.WidthButton;
import paint.menu.OpenListener;
import paint.menu.SaveListener;
import paint.modes.*;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

class PaintFrame extends JFrame {
    private final Canvas canvas;
    private final DrawListener listener;
    private final JColorChooser chooser;
    private final JPanel rightPanel;
    private final JPanel bottomPanel;
    /**
     * react on width buttons
     */
    private final ActionListener widthListen = e -> {
        WidthButton button = (WidthButton) e.getSource();
        button.updateStroke();
    };
    /**
     * react on wight custom button
     */
    private final ActionListener otherListen = e -> {
        WidthButton button = (WidthButton) e.getSource();
        String widthS = JOptionPane.showInputDialog("Enter the stroke width:");
        if (widthS != null) {
            button.setWidth(Integer.valueOf(widthS));
            button.updateStroke();
        }
    };
    /**
     * react on shape button
     */
    private final ActionListener shapeListen = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ModeButton button = (ModeButton) e.getSource();
            listener.updateShape(button.getShape());
        }
    };

    public PaintFrame() {
        int width = 800;
        int height = 640;
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Paint");
        setResizable(false);
        setLayout(new BorderLayout());

        canvas = new Canvas(width - 100, height - 230);
        add(canvas, BorderLayout.CENTER);

        // create color chooser with initial color set to black
        chooser = new JColorChooser(Color.BLACK);

        JLabel location = new JLabel("0,0");
        listener = new DrawListener(canvas, chooser, location);
        addMouseListener(listener);
        addMouseMotionListener(listener);

        addMenu();

        rightPanel = new JPanel();

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        addShapes();
        rightPanel.add(Box.createVerticalStrut(35));


        // add line widths
        addWidth(2, "\u2500");
        addWidth(5, "\u2500");
        addWidth(7, "\u2500");
        addWidth(9, "\u2500");
        addWidth("custom");

        // add the panel of shape and size options to the main frame
        add(rightPanel, BorderLayout.EAST);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        addColorChooser();
        bottomPanel.add(location, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * add Menu
     */
    private void addMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem save = new JMenuItem("Save", KeyEvent.VK_S);
        save.addActionListener(new SaveListener(canvas));
        file.add(save);

        JMenuItem open = new JMenuItem("Open", KeyEvent.VK_O);
        open.addActionListener(new OpenListener(canvas));
        file.add(open);

        menu.add(file);
        setJMenuBar(menu);
    }

    /**
     * add primitives buttons
     */
    private void addShapes() {
        addPrimitive(new Pencil(), "\u270E");
        addPrimitive(new Diamond(), "\u2666");
        //addPrimitive(new Line(), new ImageIcon(getClass().getResource("pics/line.gif")));
        addPrimitive(new Line(), "\u2572");
        addPrimitive(new Rect(), "\u25A2");
        addPrimitive(new RectFill(), "\u25FC");
        addPrimitive(new Oval(), "\u25EF");
        addPrimitive(new OvalFill(), "\u2B24");
        addPrimitive(new Eraser(), new ImageIcon(getClass().getResource("pics/eraser.png")));
        rightPanel.add(new DropperButton(listener));
    }

    /**
     * add primitive button
     */
    private void addPrimitive(Primitive shape, String unicode) {
        ModeButton mode = new ModeButton(shape, unicode);
        mode.addActionListener(shapeListen);
        rightPanel.add(mode);
    }

    /**
     * add primitive button
     */
    private void addPrimitive(Primitive shape, ImageIcon image) {
        ModeButton mode = new ModeButton(shape, image);
        mode.addActionListener(shapeListen);
        rightPanel.add(mode);
    }

    /**
     * add width button
     */
    private void addWidth(int width, String unicode) {
        WidthButton button = new WidthButton(width, canvas, unicode);
        button.addActionListener(widthListen);
        rightPanel.add(button);
    }

    /**
     * add width button(custom)
     */
    private void addWidth(String unicode) {
        WidthButton button = new WidthButton(canvas, unicode);
        button.addActionListener(otherListen);
        rightPanel.add(button);
    }

    /**
     * add color panel
     */
    private void addColorChooser() {
        // remove unwanted, extra color chooser features like other color
        // options and preview
        AbstractColorChooserPanel[] panels = chooser.getChooserPanels();
        AbstractColorChooserPanel[] newPanels = {panels[0]};
        chooser.setChooserPanels(newPanels);

        // customize preview panel
        JLabel preview = new JLabel("\u25FC", JLabel.CENTER);
        preview.setFont(new Font("Serif", Font.BOLD, 40));
        // preview.setSize(50,20);
        chooser.setPreviewPanel(preview);

        chooser.getSelectionModel().addChangeListener(e -> canvas.updateColor(chooser.getColor()));

        bottomPanel.add(chooser, BorderLayout.WEST);
    }
}