package views.memberWindowDialogs;

import exceptions.EmptyFieldException;
import exceptions.NotNumbersIngresedException;
import models.Expense;
import utils.StringConstants;
import views.componentRounded.JButtonRounded;
import views.componentRounded.JTextFieldRounded;
import views.memberWindowDialogs.expensesDialogs.JDialogAddExpense;
import views.memberWindowDialogs.expensesDialogs.JDialogWatchExpense;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class JDialogExpenses extends JDialog implements ActionListener {
    private JButtonRounded btnAddExpense;
    private JLabel lblTotalExpense;
    private JTextFieldRounded txtTotalExpense;
    private JTable tblArticles;
    private DefaultTableModel tblModel;
    private JScrollPane jspPane;

    private JDialogAddExpense dlgAddExpense;
    private JDialogWatchExpense dlgWatchExpense;

    public JDialogExpenses(ActionListener listener) {
        this.setSize(600,500);
        this.setLayout(new GridBagLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        this.setTitle("Gestionar Gastos");
        this.setLocationRelativeTo(null);
        initComponents(listener);
        this.setVisible(false);
    }

    private void initComponents(ActionListener listener) {
        btnAddExpense = new JButtonRounded("Agregar");
//        btnWatchExpense = new JButtonRounded("Ver Gasto");
        lblTotalExpense = new JLabel("Total");
        txtTotalExpense = new JTextFieldRounded(5);
        tblArticles = new JTable();
        jspPane = new JScrollPane();


        dlgAddExpense = new JDialogAddExpense(listener);
        dlgWatchExpense = new JDialogWatchExpense();

        tblModel = new DefaultTableModel(new Object[]{"Monto","Fecha"},0);
        jspPane.setViewportView(tblArticles);
        configComponents(listener);
        posicionateComponents();
    }

    private void configComponents(ActionListener listener) {
        txtTotalExpense.setEditable(false);
        tblArticles.setModel(tblModel);

        btnAddExpense.setActionCommand(StringConstants.ACTION_COMMAND_ADD_EXPENSE);
        btnAddExpense.addActionListener(this);
        btnAddExpense.addActionListener(listener);
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
        add(jspPane, gbc);

        gbc.insets = new Insets(40,10,50,40);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.1;
        gbc.gridheight = 1;
        gbc.weighty = 1;
        gbc.gridx++;
        add(btnAddExpense, gbc);
        gbc.insets = new Insets(50,10,50,40);
        gbc.gridy++;
        gbc.weightx = 0;
        add(lblTotalExpense, gbc);
        gbc.insets = new Insets(10,10,50,40);
        gbc.gridy++;
        add(txtTotalExpense, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case StringConstants.ACTION_COMMAND_ADD_EXPENSE:
                dlgAddExpense.setVisible(true);
                break;
            case StringConstants.ACTION_COMMAND_WATCH_EXPENSE:
                watchExpense();
                break;
        }
    }

    private void watchExpense() {
        dlgWatchExpense.setVisible(true);
    }

    public void fillTable(ArrayList<Expense> objectFromJSON) {
        tblModel.setRowCount(0);
        for (Expense expense : objectFromJSON){
            tblModel.addRow(new Object[]{expense.getMount(), expense.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE)});
        }
        tblArticles.setModel(tblModel);
    }


    public String getDescription() {
        return dlgAddExpense.getDescription();
    }

    public int getMount() throws NotNumbersIngresedException, EmptyFieldException {
        return dlgAddExpense.getMount();
    }

    public LocalDateTime getDate() {
        return dlgAddExpense.getDate();
    }

    public void closeAddDialog() {
        dlgAddExpense.close();
    }

    @Override
    public boolean isActive() {
        return dlgAddExpense.isActive() || dlgWatchExpense.isActive();
    }

    public void setTotalExpense(int total) {
        txtTotalExpense.setText(String.valueOf(total));
    }
}
