package views.memberWindowDialogs;

import views.componentRounded.JButtonRounded;

import javax.swing.*;
import java.awt.*;

public class JDialogDebt extends JDialog {
    private JLabel lblTotalDebt;
    private JButtonRounded btnPayDebt;

    public JDialogDebt() {
        this.setSize(200,200);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        this.setVisible(true);
    }

    private void initComponents() {
        btnPayDebt = new JButtonRounded("Pagar Deuda");
        lblTotalDebt = new JLabel("Total Deuda: 0.0");
        posicionateComponents();
    }

    private void posicionateComponents() {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10,10,10,10);
        add(lblTotalDebt, gbc);
        gbc.gridy++;
        add(btnPayDebt, gbc);
    }
}