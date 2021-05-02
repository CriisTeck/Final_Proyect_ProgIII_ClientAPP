package views.loginWindowDialogs;

import exceptions.BadEmailFormatException;
import utils.StringConstants;
import views.componentRounded.JButtonRounded;
import views.componentRounded.JTextFieldRounded;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JDialogForgottenPass extends JDialog {
    private JLabel lblTitle;
    private JLabel lblEmail;
    private JButtonRounded btnSendEmail;
    private JTextFieldRounded txtEmail;

    public JDialogForgottenPass(ActionListener listener) {
        this.setTitle("Recuperar Contraseña");
        this.setSize(400, 300);
        this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(new Color(50, 50, 50));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initComponents(listener);
        this.setVisible(false);
    }

    private void initComponents(ActionListener listener) {
        lblTitle = new JLabel("Recuperar Contraseña");
        lblEmail = new JLabel("Ingrese su correo electronico: ");
        btnSendEmail = new JButtonRounded("Enviar correo de recuperación");
        txtEmail = new JTextFieldRounded(15);
        configComponents(listener);
        posicionateComponents();
    }

    private void configComponents(ActionListener listener) {
        changeForegroundColor(lblEmail);
        changeForegroundColor(lblTitle);

        lblTitle.setFont(new Font("Lucida Console", Font.PLAIN, 15));

        btnSendEmail.addActionListener(listener);
        btnSendEmail.setActionCommand("SendEmail");
    }

    private void changeForegroundColor(JComponent component) {
        component.setForeground(Color.gray);
    }

    private void posicionateComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.ipady = 15;
        gbc.insets = new Insets(20, 5, 25, 5);

        add(lblTitle, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        add(lblEmail, gbc);

        gbc.gridx++;
        add(txtEmail, gbc);

        gbc.gridx--;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(btnSendEmail, gbc);
    }

    public String getEmail() throws BadEmailFormatException {
        if(txtEmail.getText().matches(StringConstants.EMAIL_REGEX)){
            btnSendEmail.setEnabled(false);
            return txtEmail.getText();
        }
        throw new BadEmailFormatException();
    }

    private void clearFields(){
        txtEmail.setText("");
    }

    @Override
    public void dispose() {
        clearFields();
        super.dispose();
    }

    public void enableButton(){
        btnSendEmail.setEnabled(true);
    }
}
