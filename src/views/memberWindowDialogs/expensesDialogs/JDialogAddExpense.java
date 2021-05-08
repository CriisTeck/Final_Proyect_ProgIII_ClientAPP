package views.memberWindowDialogs.expensesDialogs;

import com.toedter.calendar.JDateChooser;
import exceptions.EmptyFieldException;
import exceptions.NotNumbersIngresedException;
import utils.StringConstants;
import views.componentRounded.JButtonRounded;
import views.componentRounded.JTextFieldRounded;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class JDialogAddExpense extends JDialog {
    private JLabel lblMount;
    private JLabel lblDate;
    private JLabel lblDescription;
    private JTextFieldRounded txtMount;
    private JTextArea txaDescription;
    private JDateChooser cldDateChooser;

    private JButtonRounded btnAddExpense;

    public JDialogAddExpense(ActionListener listener) {
        this.setVisible(false);
        this.setSize(400, 650);
        this.setLayout(new GridBagLayout());
        this.setLocationRelativeTo(null);
        this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initcomponents(listener);
        this.setResizable(false);
    }

    private void initcomponents(ActionListener listener) {
        lblMount = new JLabel("Monto del gasto ($)");
        lblDate = new JLabel("Fecha del gasto");
        lblDescription = new JLabel("Descripción de gasto");

        txtMount = new JTextFieldRounded(10);
        txaDescription = new JTextArea(10, 10);

        LocalDate date = LocalDate.now();
        cldDateChooser = new JDateChooser(new Date(date.getYear() - 1900, date.getMonthValue() - 1, date.getDayOfMonth()));

        btnAddExpense = new JButtonRounded("Agregar Gasto");
        configureComponents(listener);
        posicionateComponents();
    }

    private void configureComponents(ActionListener listener) {
        txaDescription.setBorder(BorderFactory.createLineBorder(Color.black));
        btnAddExpense.addActionListener(listener);
        btnAddExpense.setActionCommand(StringConstants.ACTION_COMMAND_ADD_EXPENSE_BUTTON);
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
        add(cldDateChooser, gbc);
        gbc.gridy++;
        add(lblDescription, gbc);
        gbc.weighty = 1;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        add(txaDescription, gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy++;
        add(btnAddExpense, gbc);

    }

    public String getDescription() {
        return txaDescription.getText();
    }

    public int getMount() throws NotNumbersIngresedException, EmptyFieldException {
        if (txtMount.getText().length() > 0) {
            if (txtMount.getText().matches("^[\\d]+$")) {
                return Integer.parseInt(txtMount.getText());
            } else throw new NotNumbersIngresedException(lblMount.getText());
        } else throw new EmptyFieldException(lblMount.getText());
    }

    public LocalDateTime getDate() {
        return cldDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public void close() {
        txaDescription.setText("");
        txtMount.setText("");
        this.dispose();
    }


}
