package views;

import views.principalWindowpanels.JPanelInformation;

import javax.swing.*;
import java.awt.*;

public class PrincipalWindow extends JFrame {
    private JPanelInformation pnlInformation;
    private GridBagConstraints gbc;

    public PrincipalWindow(String title){
        super(title);
        this.setSize(800,800);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setLocationRelativeTo(null);
        initComponents();
        this.setVisible(true);
    }

    private void initComponents() {
        pnlInformation = new JPanelInformation();
        posicionateComponents();
    }

    private void posicionateComponents() {
        gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.weighty = 0;
        gbc.weightx = 1;
        add(pnlInformation, gbc);
    }

    public Component getActiveComponent() {
        return this;
    }

    protected GridBagConstraints getGridBagConstraints() {
        return gbc;
    }
}
