package views.componentRounded;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class MyButtonUI extends BasicButtonUI {
    public MyButtonUI() {
        super();
    }

    @Override
    protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(93, 136, 182));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(2, 2, b.getWidth()-4,b.getHeight()-4,25,25);
        super.paintFocus(g2d, b, viewRect, textRect, iconRect);
    }

}
