package views.memberWindowDialogs;

import views.componentRounded.JButtonRounded;
import views.componentRounded.JTextFieldRounded;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JDialogExpenses extends JDialog {
    private JButtonRounded btnAddExpense;
    private JButtonRounded btnWatchExpense;
    private JLabel lblTotalExpense;
    private JTextFieldRounded txtTotalExpense;
    private JTable tblArticles;

    public JDialogExpenses(ActionListener listener) {
        this.setSize(600,500);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        this.setTitle("Gestionar Gastos");
        this.setLocationRelativeTo(null);
        initComponents(listener);
        this.setVisible(true);
    }

    private void initComponents(ActionListener listener) {
        btnAddExpense = new JButtonRounded("Agregar");
        btnWatchExpense = new JButtonRounded("Ver Gasto");
        lblTotalExpense = new JLabel("Total");
        txtTotalExpense = new JTextFieldRounded(5);
        tblArticles = new JTable();

        configComponents();
        posicionateComponents();
    }

    private void configComponents() {
        txtTotalExpense.setEditable(false);
    }

    private void posicionateComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy= 0;
        gbc.gridheight = 4;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.ipady = 12;
        gbc.ipadx = 12;
        gbc.insets = new Insets(40,40,50,10);
        gbc.fill = GridBagConstraints.BOTH;
        add(tblArticles, gbc);

        gbc.insets = new Insets(40,10,50,40);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.1;
        gbc.gridheight = 1;
        gbc.weighty = 0.1;
        gbc.gridx++;
        add(btnAddExpense, gbc);
        gbc.insets = new Insets(10,10,50,40);
        gbc.gridy++;
        add(btnWatchExpense, gbc);
        gbc.gridy++;
        gbc.weightx = 0;
        add(lblTotalExpense, gbc);
        gbc.gridy++;
        add(txtTotalExpense, gbc);
    }
}
