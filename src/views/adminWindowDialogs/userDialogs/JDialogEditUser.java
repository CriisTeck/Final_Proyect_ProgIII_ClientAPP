package views.adminWindowDialogs.userDialogs;

import exceptions.BadEmailFormatException;
import exceptions.EmptyFieldException;
import models.User;
import utils.StringConstants;
import views.componentRounded.JButtonRounded;
import views.componentRounded.JTextFieldRounded;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static utils.EncrypterString.desencrypt;
import static utils.EncrypterString.encrypt;

public class JDialogEditUser extends JDialog {
    private JLabel lblUser;
    private JLabel lblPassword;
    private JLabel lblName;
    private JLabel lblEmail;

    private JTextFieldRounded txtUser;
    private JTextFieldRounded txtPassword;
    private JTextFieldRounded txtName;
    private JTextFieldRounded txtEmail;

    private JButtonRounded btnEditUser;

    public JDialogEditUser(ActionListener listener) {
        this.setSize(400, 650);
        this.setLayout(new GridBagLayout());
        this.setLocationRelativeTo(null);
        this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        initcomponents(listener);
        this.setTitle("Editar Usuario");
        this.setVisible(false);
    }

    private void initcomponents(ActionListener listener) {
        txtUser = new JTextFieldRounded(15);
        txtPassword = new JTextFieldRounded(15);
        txtName = new JTextFieldRounded(15);
        txtEmail = new JTextFieldRounded(15);

        lblEmail = new JLabel("Correo electronico");
        lblPassword = new JLabel("Contraseña");
        lblName = new JLabel("Nombre");
        lblUser = new JLabel("Usuario");

        btnEditUser = new JButtonRounded("Editar Usuario");
        configComponents(listener);
        posicionateComponents();
    }

    private void posicionateComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.ipady = 10;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblName, gbc);
        gbc.gridy++;
        add(lblUser, gbc);
        gbc.gridy++;
        add(lblPassword, gbc);
        gbc.gridy++;
        add(lblEmail, gbc);
        gbc.gridy = 0;
        gbc.gridx++;
        add(txtName, gbc);
        gbc.gridy++;
        add(txtUser, gbc);
        gbc.gridy++;
        add(txtPassword, gbc);
        gbc.gridy++;
        add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(btnEditUser, gbc);
    }

    private void configComponents(ActionListener listener) {
        btnEditUser.addActionListener(listener);
        btnEditUser.setActionCommand(StringConstants.ACTION_COMMAND_EDIT_USER_BUTTON);
    }

    public void setData(User readObjectJson) {
        try {
            txtUser.setText(desencrypt(readObjectJson.getid().split("@")[0]));
            txtName.setText(readObjectJson.getName());
            txtEmail.setText((readObjectJson.getEmail()));
            txtPassword.setText(desencrypt(readObjectJson.getid().split("@")[1]));
        } catch (NullPointerException e) {
            throw new NullPointerException("No hay usuario seleccionado");
        }
    }

    public String getUser() throws EmptyFieldException {
        if (txtUser.getText().length() > 0) {
            return txtUser.getText();
        }
        throw new EmptyFieldException(lblUser.getText());
    }

    public String getPassword() throws EmptyFieldException {
        if (txtPassword.getText().length() > 0) {
            return txtPassword.getText();
        }
        throw new EmptyFieldException(lblPassword.getText());
    }

    public String getEditEmailUser() throws EmptyFieldException, BadEmailFormatException {
        if (txtEmail.getText().length() > 0)
            if (txtEmail.getText().matches(StringConstants.EMAIL_REGEX))
                return encrypt(txtEmail.getText());
            else throw new BadEmailFormatException();
        throw new EmptyFieldException(lblEmail.getText());
    }

    public String getEditNameUser() throws EmptyFieldException {
        if (txtName.getText().length() > 0) {
            return txtName.getText();
        }
        throw new EmptyFieldException(lblName.getText());
    }

    public void close() {
        txtName.setText("");
        txtUser.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        this.dispose();
    }
}
