package views;

import exceptions.*;
import models.TypeAccount;
import views.componentRounded.JButtonRounded;
import views.componentRounded.JPasswordFieldRounded;
import views.componentRounded.JTextFieldRounded;
import views.loginWindowDialogs.JDialogForgottenPass;
import views.loginWindowDialogs.JDialogNewUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.text.MessageFormat;

import static utils.EncrypterString.*;

public class LoginWindow extends JFrame implements MouseListener {
    private JLabel lblUserName;
    private JTextFieldRounded txtUserName;
    private JLabel lblPassword;
    private JPasswordFieldRounded pssPassword;
    private JLabel createUser;
    private JLabel userForgotten;
    private JLabel lblShowPassword;
    private JButtonRounded btnLogin;

    private JDialogNewUser dlgNewUser;
    private JDialogForgottenPass dlgForgottenPass;


    public LoginWindow(WindowAdapter wlistener, ActionListener listener, MouseListener mListener) {
        this.setSize(300, 500);
        this.setLayout(new GridBagLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents(listener, mListener);
        this.getContentPane().setBackground(new Color(50, 50, 50));
        this.setVisible(true);
        this.addWindowListener(wlistener);
    }

    private void initComponents(ActionListener listener, MouseListener mListener) {
        lblUserName = new JLabel("Usuario:");
        lblPassword = new JLabel("Contraseña: ");

        createUser = new JLabel("Crear nuevo usuario");
        userForgotten = new JLabel("Recordar contraseña");
        txtUserName = new JTextFieldRounded(15);
        pssPassword = new JPasswordFieldRounded(15);

        lblShowPassword = new JLabel("Mostrar Contraseña");

        btnLogin = new JButtonRounded("Entrar");

        dlgForgottenPass = new JDialogForgottenPass(listener);
        dlgNewUser = new JDialogNewUser(listener);

        posicionateComponents();
        setConfigInitial(listener, mListener);
    }

    private void setConfigInitial(ActionListener listener, MouseListener mListener) {
        configureLabel(userForgotten, "lblUserForgotten");

        btnLogin.addActionListener(listener);
        btnLogin.setActionCommand("Login");
        btnLogin.setName("btnLogin");
        btnLogin.addMouseListener(this);

        lblPassword.setForeground(Color.gray);
        lblUserName.setForeground(Color.gray);
        createUser.setForeground(Color.gray);
        userForgotten.setForeground(Color.gray);

        createUser.addMouseListener(mListener);
        userForgotten.addMouseListener(mListener);
        configureLabel(createUser, "lblCreateUser");
        configureLabel(lblShowPassword, "lblShowPassword");
    }

    private void configureLabel(JLabel label, String name) {
        label.setName(name);
        label.setForeground(Color.gray);
        label.addMouseListener(this);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.setFont(new Font(label.getFont().getName(),
                label.getFont().getStyle(),
                11));
    }

    private void posicionateComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = 2;
        gbc.ipady = 15;
        gbc.ipadx = 5;
        gbc.insets = new Insets(0, 10, 0, 10);
        this.add(lblUserName, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 0, 10);
        this.add(txtUserName, gbc);

        gbc.insets = new Insets(20, 10, 0, 10);
        gbc.gridy++;
        this.add(lblPassword, gbc);

        gbc.insets = new Insets(5, 10, 0, 10);
        gbc.gridy++;
        this.add(pssPassword, gbc);

        gbc.insets = new Insets(5, 10, 0, 10);
        gbc.gridy++;
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblShowPassword, gbc);
        gbc.gridx--;

        gbc.insets = new Insets(50, 10, 0, 10);
        gbc.gridwidth = 2;
        gbc.gridy++;
        add(btnLogin, gbc);

        gbc.insets = new Insets(5, 10, 0, 10);
        gbc.gridwidth = 1;
        gbc.gridy++;
        this.add(createUser, gbc);

        gbc.gridx++;
        this.add(userForgotten, gbc);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().getName().equals(userForgotten.getName()))
            dlgForgottenPass.setVisible(true);
        if (e.getComponent().getName().equals(createUser.getName()))
            dlgNewUser.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getComponent().getName().equals(lblShowPassword.getName())) {
            pssPassword.showPassword();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getComponent().getName().equals(lblShowPassword.getName())) {
            pssPassword.hidePassword();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getComponent().getClass() == JLabel.class)
            changeForeGroundHover(e, new Color(34, 104, 227));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getComponent().getClass() == JLabel.class)
            changeForegroundUnHover(e);
    }

    private void changeForeGroundHover(MouseEvent e, Color color) {
        e.getComponent().setForeground(color);
    }

    private void changeForegroundUnHover(MouseEvent e) {
        e.getComponent().setForeground(Color.gray);
    }

    public String getPassword() throws PasswordFieldEmptyException {
        if (pssPassword.getPassword().length > 0)
            return encrypt(String.valueOf(pssPassword.getPassword()));
        throw new PasswordFieldEmptyException();
    }

    public String getUsername() throws UsernameFieldEmptyException {
        if (txtUserName.getText().length() > 0)
            return encrypt(txtUserName.getText());
        throw new UsernameFieldEmptyException();
    }

    public String getEmailToRecover() throws BadEmailFormatException {
        return encrypt(dlgForgottenPass.getEmail());
    }

    public void setAdminTrue() {
        dlgNewUser.setAdminTrue();
    }

    public void setAdminFalse() {
        dlgNewUser.setAdminFalse();
    }

    public String getId() throws PasswordFieldEmptyException, PasswordNotEqualsException, UsernameFieldEmptyException {
        return MessageFormat.format("{0}@{1}", encrypt(dlgNewUser.getUsername()), encrypt(dlgNewUser.getPassword()));
    }

    public String getUserName() throws UserFieldEmptyException {
        return dlgNewUser.getUserName();
    }

    public String getEmail() throws BadEmailFormatException, EmailFieldEmptyException {
        return encrypt(dlgNewUser.getEmail());
    }

    public TypeAccount getRole() throws NotRoleSelectedException {
        return dlgNewUser.getRole();
    }

    public Component getActiveComponent() {
        if (dlgNewUser.isActive())
            return dlgNewUser;
        if (dlgForgottenPass.isActive())
            return dlgForgottenPass;
        return this;

    }

    @Override
    public void dispose() {
        dlgNewUser.dispose();
        dlgForgottenPass.dispose();
        super.dispose();
    }

    public void disposeEmailWindow() {
        dlgForgottenPass.dispose();
    }

    public void enableBtn() {
        dlgForgottenPass.enableButton();
    }
}
