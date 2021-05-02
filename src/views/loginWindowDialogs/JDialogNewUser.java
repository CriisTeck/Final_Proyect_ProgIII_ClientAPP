package views.loginWindowDialogs;

import exceptions.*;
import models.TypeAccount;
import utils.StringConstants;
import views.componentRounded.JButtonRounded;
import views.componentRounded.JPasswordFieldRounded;
import views.componentRounded.JTextFieldRounded;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class JDialogNewUser extends JDialog {
    private JLabel lblTitle;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblConfirmPassword;
    private JLabel lblName;
    private JLabel lblRole;
    private JLabel lblEmail;

    private JTextFieldRounded txtUsername;
    private JPasswordFieldRounded pwfPassword;
    private JPasswordFieldRounded pwfConfirmPassword;
    private JTextFieldRounded txtName;
    private JTextFieldRounded txtEmail;

    private JRadioButton rdbAdmin;
    private JRadioButton rdbMember;

    private JButtonRounded btnCreate;
    private ButtonGroup btnGroupRDB;

    public JDialogNewUser(ActionListener listener) {
        this.setTitle("Crear nuevo Usuario");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500, 500);
        this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(new Color(50, 50, 50));
        this.setLocationRelativeTo(null);
        initComponents(listener);
        this.setVisible(false);
    }

    private void initComponents(ActionListener listener) {
        lblTitle = new JLabel("Nuevo Usuario");
        lblUsername = new JLabel("Usuario: ");
        lblPassword = new JLabel("Contraseña: ");
        lblConfirmPassword = new JLabel("Confirmar Contraseña: ");
        lblName = new JLabel("Nombre Completo: ");
        lblRole = new JLabel("Elija su rol: ");

        txtUsername = new JTextFieldRounded(15);
        pwfConfirmPassword = new JPasswordFieldRounded(15);
        pwfPassword = new JPasswordFieldRounded(15);
        txtName = new JTextFieldRounded(15);

        rdbAdmin = new JRadioButton("Administrador");
        rdbMember = new JRadioButton("Miembro Normal");

        btnGroupRDB = new ButtonGroup();
        btnGroupRDB.add(rdbAdmin);
        btnGroupRDB.add(rdbMember);

        lblEmail = new JLabel("Email");
        txtEmail = new JTextFieldRounded(15);

        btnCreate = new JButtonRounded("Crear Usuario");
        configComponents(listener);
        posicionateComponents();
    }

    private void configComponents(ActionListener listener) {
        Color colorForLabels = Color.gray;
        changeForegroundColor(lblName, colorForLabels);
        changeForegroundColor(lblConfirmPassword, colorForLabels);
        changeForegroundColor(lblPassword, colorForLabels);
        changeForegroundColor(lblRole, colorForLabels);
        changeForegroundColor(lblUsername, colorForLabels);
        changeForegroundColor(lblTitle, colorForLabels);
        changeForegroundColor(rdbAdmin, colorForLabels);
        changeForegroundColor(rdbMember, colorForLabels);
        rdbAdmin.setBackground(new Color(50, 50, 50));
        rdbMember.setBackground(new Color(50, 50, 50));
        btnCreate.addActionListener(listener);
        btnCreate.setActionCommand("CreateUser");
    }

    private void changeForegroundColor(JComponent component, Color colorForLabels) {
        component.setForeground(colorForLabels);
    }

    private void posicionateComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 5, 0, 5);
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipady = 10;
        add(lblTitle, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(lblName, gbc);

        gbc.gridx++;
        add(txtName, gbc);

        gbc.gridx--;
        gbc.gridy++;
        add(lblUsername, gbc);

        gbc.gridx++;
        add(txtUsername, gbc);

        gbc.gridy++;
        gbc.gridx--;
        add(lblPassword, gbc);

        gbc.gridx++;
        add(pwfPassword, gbc);

        gbc.gridy++;
        gbc.gridx--;
        add(lblConfirmPassword, gbc);

        gbc.gridx++;
        add(pwfConfirmPassword, gbc);

        gbc.gridy++;
        gbc.gridx--;
        add(lblEmail, gbc);

        gbc.gridx++;
        add(txtEmail, gbc);

        gbc.gridy++;
        gbc.gridx--;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblRole, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        add(rdbAdmin, gbc);

        gbc.gridx++;
        add(rdbMember, gbc);

        gbc.gridwidth = 2;
        gbc.gridy++;
        gbc.gridx--;
        add(btnCreate, gbc);
    }

    @Override
    public void dispose() {
        clearFields();
        super.dispose();
    }

    private void clearFields() {
        txtName.setText("");
        txtUsername.setText("");
        pwfPassword.setText("");
        pwfConfirmPassword.setText("");
        rdbMember.setSelected(false);
        rdbAdmin.setSelected(false);
    }

    public void setAdminTrue() {
        rdbAdmin.setEnabled(false);
        rdbMember.setSelected(true);
        rdbMember.setEnabled(false);
    }

    public void setAdminFalse() {
        rdbAdmin.setEnabled(true);
        rdbMember.setEnabled(true);
    }

    public String getUserName() throws UserFieldEmptyException {
        if (txtName.getText().length() > 0)
            return txtName.getText();
        throw new UserFieldEmptyException();
    }

    public String getUsername() throws UsernameFieldEmptyException {
        if (txtUsername.getText().length() > 0)
            return txtUsername.getText();
        throw new UsernameFieldEmptyException();
    }

    public String getEmail() throws BadEmailFormatException, EmailFieldEmptyException {
        if (txtEmail.getText().matches(StringConstants.EMAIL_REGEX)){
            if (txtEmail.getText().length() > 0)
                return txtEmail.getText();
            throw new EmailFieldEmptyException();
        }
        throw new BadEmailFormatException();
    }

    public String getPassword() throws PasswordFieldEmptyException, PasswordNotEqualsException {
        if(Arrays.equals(pwfPassword.getPassword(), pwfConfirmPassword.getPassword())){
            if(pwfPassword.getPassword().length > 0)
                return String.valueOf(pwfPassword.getPassword());
            throw new PasswordFieldEmptyException();
        }
        throw new PasswordNotEqualsException();
    }

    public TypeAccount getRole() throws NotRoleSelectedException {
        if(rdbMember.isSelected())
            return TypeAccount.MEMBER;
        if(rdbAdmin.isSelected())
            return TypeAccount.ADMIN;
        throw new NotRoleSelectedException();
    }
}
