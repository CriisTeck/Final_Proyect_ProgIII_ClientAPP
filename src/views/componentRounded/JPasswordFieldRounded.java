package views.componentRounded;

import javax.swing.*;
import java.awt.*;

public class JPasswordFieldRounded extends JPasswordField {

    public JPasswordFieldRounded(int columns) {
        super(columns);
        setEchoChar('•');
        setOpaque(false);
        this.setHorizontalAlignment(JPasswordField.CENTER);
    }

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(Color.red);
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
    }

    public void showPassword() {
        this.setEchoChar((char) 0);
    }

    public void hidePassword() {
        this.setEchoChar('•'/*'⬤'*/);
    }
}
