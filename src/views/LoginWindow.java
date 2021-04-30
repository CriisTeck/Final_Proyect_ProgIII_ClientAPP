package views;

import controllers.ClientController;
import exceptions.BadEmailFormatException;
import exceptions.PasswordFieldEmptyException;
import exceptions.UsernameFieldEmpty_onLoginException;
import utils.EncrypterString;
import views.ComponentRounded.JButtonRounded;
import views.ComponentRounded.JPasswordFieldRounded;
import views.ComponentRounded.JTextFieldRounded;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;

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


    public LoginWindow(WindowAdapter wlistener, ActionListener listener) {
        this.setSize(300, 500);
        this.setLayout(new GridBagLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents(listener);
        this.getContentPane().setBackground(new Color(50, 50, 50));
        this.setVisible(true);
        this.addWindowListener(wlistener);
    }

    private void initComponents(ActionListener listener) {
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
        setConfigInitial(listener);
    }

    private void setConfigInitial(ActionListener listener) {
        configureLabel(userForgotten, "lblUserForgotten");
        configureLabel(createUser, "lblCreateUser");
        configureLabel(lblShowPassword, "lblShowPassword");

        btnLogin.addActionListener(listener);
        btnLogin.setActionCommand("Login");
        btnLogin.setName("btnLogin");
        btnLogin.addMouseListener(this);

        lblPassword.setForeground(Color.gray);
        lblUserName.setForeground(Color.gray);
        createUser.setForeground(Color.gray);
        userForgotten.setForeground(Color.gray);
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
        if(e.getComponent().getName().equals(userForgotten.getName()))
            dlgForgottenPass.setVisible(true);
        if(e.getComponent().getName().equals(createUser.getName()))
            dlgNewUser.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getComponent().getName().equals(lblShowPassword.getName())){
            pssPassword.showPassword();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getComponent().getName().equals(lblShowPassword.getName())){
            pssPassword.hidePassword();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getComponent().getClass() == JLabel.class)
            changeForeGroundHover(e, new Color(34,104,227));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getComponent().getClass() == JLabel.class)
            changeForegroundUnHover(e);
    }

    private void changeForeGroundHover(MouseEvent e, Color color){
        e.getComponent().setForeground(color);
    }

    private void changeForegroundUnHover(MouseEvent e){
        e.getComponent().setForeground(Color.gray);
    }

    public String getPassword() throws PasswordFieldEmptyException {
        if(pssPassword.getPassword().length > 0)
            return EncrypterString.encrypt(String.valueOf(pssPassword.getPassword()));
        throw new PasswordFieldEmptyException();
    }

    public String getUsername() throws UsernameFieldEmpty_onLoginException {
        if(txtUserName.getText().length() > 0)
            return EncrypterString.encrypt(txtUserName.getText());
        throw new UsernameFieldEmpty_onLoginException();
    }

    public String getEmailToRecover() throws BadEmailFormatException {
        return dlgForgottenPass.getEmail();
    }
}
