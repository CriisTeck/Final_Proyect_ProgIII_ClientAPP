package views.principalWindowpanels;

import utils.UtilsString;

import javax.swing.*;
import java.awt.*;


public class JPanelInformation extends JPanel {
    private JLabel lblName;
    private JLabel lblMount;

    public JPanelInformation() {
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.gray));
        initComponents();
        this.setVisible(true);
    }


    private void initComponents() {
        lblName = new JLabel("name");
        lblMount = new JLabel("0.0");
        posicionateComponents();
    }

    private void posicionateComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.8;
        gbc.ipady = 10;
        gbc.insets = new Insets(2, 15, 0, 5);
        gbc.anchor = GridBagConstraints.WEST;
        add(lblName, gbc);

        gbc.gridx++;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblMount, gbc);

    }


    public void setNameText(String name) {
        lblName.setText(UtilsString.capitalize(name));
    }

    public void setMount(double actualMount, double maximum) {
        lblMount.setText(actualMount + "/" + maximum);
    }
}
