package views.memberWindowDialogs;

import utils.StringConstants;
import views.componentRounded.JButtonRounded;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static views.windowUtilitary.ViewUtils.createIconWithCode;

public class JDialogDebt extends JDialog {
    private JLabel lblTotalDebt;
    private JButtonRounded btnPayDebt;

    public JDialogDebt(ActionListener listener) {
        this.setSize(200,200);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
        this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Pagar Deuda");
        initComponents(listener);
        this.setVisible(false);
    }

    private void initComponents(ActionListener listener) {
        btnPayDebt = new JButtonRounded("Pagar Deuda");
        lblTotalDebt = new JLabel("Total Deuda: 0.0");
        btnPayDebt.setIcon(createIconWithCode('\uf09d',20,Color.black));
        btnPayDebt.addActionListener(listener);
        btnPayDebt.setActionCommand(StringConstants.ACTION_COMMAND_PAY_DEBT);
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


    public void setDebt(Object objectFromJSON) {
        lblTotalDebt.setText("Total Deuda: " + objectFromJSON);
    }
}