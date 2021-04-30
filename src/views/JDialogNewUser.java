package views;

import views.ComponentRounded.JButtonRounded;
import views.ComponentRounded.JPasswordFieldRounded;
import views.ComponentRounded.JTextFieldRounded;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JDialogNewUser extends JDialog {
    private JLabel lblTitle;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblConfirmPassword;
    private JLabel lblName;
    private JLabel lblRole;

    private JTextFieldRounded txtUsername;
    private JPasswordFieldRounded pwfPassword;
    private JPasswordFieldRounded pwfConfirmPassword;
    private JTextFieldRounded txtName;

    private JRadioButton rdbAdmin;
    private JRadioButton rdbMember;

    private JButtonRounded btnCreate;

    public JDialogNewUser(ActionListener listener) {
        this.setTitle("Crear nuevo Usuario");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500,500);
        this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(new Color(50,50,50));
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
        rdbAdmin.setBackground(new Color(50,50,50));
        rdbMember.setBackground(new Color(50,50,50));
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
        gbc.insets = new Insets(20,5,0,5);
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
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblRole, gbc);

        gbc.gridwidth =1;
        gbc.gridy++;
        add(rdbAdmin, gbc);

        gbc.gridx++;
        add(rdbMember, gbc);

        gbc.gridwidth = 2;
        gbc.gridy++;
        gbc.gridx--;
        add(btnCreate, gbc);
    }

//    @Override
//    public void dispose() {
//        clearFields();
//        this.setVisible(false);
//    }

    private void clearFields() {
        txtName.setText("");
        txtUsername.setText("");
        pwfPassword.setText("");
        pwfConfirmPassword.setText("");
        rdbMember.setSelected(false);
        rdbAdmin.setSelected(false);
    }

}
