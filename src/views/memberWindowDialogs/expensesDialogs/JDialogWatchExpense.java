package views.memberWindowDialogs.expensesDialogs;

import views.componentRounded.JTextFieldRounded;

import javax.swing.*;
import java.awt.*;

public class JDialogWatchExpense extends JDialog {
    private JLabel lblMount;
    private JLabel lblDate;
    private JLabel lblType;
    private JLabel lblDescription;
    private JTextFieldRounded txtMount;
    private JTextArea txaDescription;
    private JTextFieldRounded txtDate;
    private JTextFieldRounded txtType;

    public JDialogWatchExpense() {
        this.setSize(400, 650);
        this.setLayout(new GridBagLayout());
        this.setLocationRelativeTo(null);
        this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initcomponents();
        this.setTitle("Descripcion de gasto");
        this.setVisible(false);
        this.setResizable(false);
    }

    private void initcomponents() {
        lblMount = new JLabel("Monto del gasto ($)");
        lblDate = new JLabel("Fecha del gasto");
        lblType = new JLabel("Tipo de gasto");
        lblDescription = new JLabel("Descripción de gasto");

        txtMount = new JTextFieldRounded(10);
        txaDescription = new JTextArea(10, 10);

        txtDate =  new JTextFieldRounded(10);
        txtType = new JTextFieldRounded(10);

        configureComponents();
        posicionateComponents();
    }

    private void posicionateComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.ipadx = 10;
        gbc.insets = new Insets(10, 30, 10, 30);
        gbc.ipady = 10;
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(lblMount, gbc);
        gbc.gridy++;
        add(txtMount, gbc);
        gbc.gridy++;
        add(lblDate, gbc);
        gbc.gridy++;
        add(txtDate, gbc);
        gbc.gridy++;
        add(lblType, gbc);
        gbc.gridy++;
        add(txtType, gbc);
        gbc.gridy++;
        add(lblDescription, gbc);
        gbc.weighty = 1;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 30, 30, 30);
        add(txaDescription, gbc);
    }

    private void configureComponents() {
        txaDescription.setBorder(BorderFactory.createLineBorder(Color.black));
        txtMount.setEditable(false);
        txaDescription.setEditable(false);
        txtType.setEditable(false);
        txtDate.setEditable(false);
    }

}
