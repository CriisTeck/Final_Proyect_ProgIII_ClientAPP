package views.ComponentRounded;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JButtonRounded extends JButton implements MouseListener {
    private Shape shape;
    private boolean isHover;
    private Color colorButton;
    private boolean isPressed;

    public JButtonRounded(String name) {
        super(name);
        super.setUI(new BasicButtonUI());
        super.setBorderPainted(false);
        setOpaque(false);
        isHover = false;
        this.setForeground(Color.black);
        this.addMouseListener(this);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(!isHover)
            g.setColor(new Color(255,255,255));
        else if (isPressed)
            g.setColor(colorButton);
        else
            g.setColor(new Color(93, 136, 182));

        g.fillRoundRect(0,0,getWidth()-1,getHeight()-1,25,25);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.black);
        g.drawRoundRect(0,0,getWidth()-1,getHeight()-1,25,25);
        super.paintBorder(g);
    }

    public void updateHover(boolean state) {
        isHover = state;
        paintComponent(getGraphics());
    }

    public void pressButton(){
        isPressed = true;
        colorButton = new Color(93, 161, 182);
        paintComponent(getGraphics());
    }

    public void unpressButton(){
        isPressed = false;
        updateHover(true);
        paintComponent(getGraphics());

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        unpressButton();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        updateHover(true);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        updateHover(false);
    }
}
