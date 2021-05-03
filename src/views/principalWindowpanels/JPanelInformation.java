package views.principalWindowpanels;

import jiconfont.icons.font_awesome.FontAwesome;
import views.componentRounded.JButtonRounded;

import javax.swing.*;
import java.awt.*;

import static views.windowUtilitary.ViewUtils.createIcon;


public class JPanelInformation extends JPanel {
    private JLabel lblName;
    private JLabel lblMount;
    private JButtonRounded btnNotifications;

    public JPanelInformation() {
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.gray));
        initComponents();
        this.setVisible(true);
    }


    private void initComponents() {
        lblName = new JLabel("name");
        lblMount = new JLabel("0.0");
        btnNotifications = new JButtonRounded("");
        configComponents();
        posicionateComponents();
    }

    private void posicionateComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.8;
        gbc.insets = new Insets(2,15,0,5);
        gbc.anchor = GridBagConstraints.WEST;
        add(lblName, gbc);

        gbc.gridx++;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblMount, gbc);

        gbc.gridx++;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.EAST;
        add(btnNotifications, gbc);
    }

    private void configComponents() {
        btnNotifications.setIcon(createIcon(FontAwesome.BELL, 20,Color.black));
    }

}
