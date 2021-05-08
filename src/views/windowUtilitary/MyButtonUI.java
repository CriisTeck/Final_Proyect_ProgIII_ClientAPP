package views.windowUtilitary;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class MyButtonUI extends BasicButtonUI {
    public MyButtonUI() {
        super();
    }

    @Override
    protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {
        g.drawLine(textRect.x, textRect.y + textRect.height, textRect.x + textRect.width, textRect.y + textRect.height);
        super.paintFocus(g, b, viewRect, textRect, iconRect);
    }

}
