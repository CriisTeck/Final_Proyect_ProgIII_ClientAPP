package views.ComponentRounded;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class JTextFieldRounded extends JTextField {
//    private Shape shape;

    public JTextFieldRounded(int columns) {
        super(columns);
        setOpaque(false);
        this.setHorizontalAlignment(JTextField.CENTER);
    }

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
    }

//    public boolean contains(int x, int y) {
//        if (shape == null || !shape.getBounds().equals(getBounds())) {
//            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
//        }
//        return shape.contains(x, y);
//    }
}
