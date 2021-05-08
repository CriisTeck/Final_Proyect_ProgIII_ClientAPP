package views.adminWindowDialogs.mountDialogs;

import utils.StringConstants;
import views.componentRounded.JButtonRounded;
import views.componentRounded.JTextFieldRounded;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JDialogEditMount extends JDialog {
    private JLabel lblName;
    private JLabel lblMount;

    private JTextFieldRounded txtName;
    private JTextFieldRounded txtMount;

    private JButtonRounded btnEditMount;

    public JDialogEditMount(ActionListener listener) {
        this.setSize(400, 300);
        this.setLayout(new GridBagLayout());
        this.setLocationRelativeTo(null);
        this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        initcomponents(listener);
        this.setTitle("Editar Montos");
        this.setVisible(false);
    }

    private void initcomponents(ActionListener listener) {
        lblName = new JLabel("Nombre");
        lblMount = new JLabel("Monto para gastar ($)");
        txtName = new JTextFieldRounded(15);
        txtMount = new JTextFieldRounded(10);
        btnEditMount = new JButtonRounded("Editar Monto");

        configComponents(listener);
        posicionateComponents();
    }

    private void posicionateComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 10;
        gbc.insets = new Insets(10,10,10,10);

        add(lblName, gbc);
        gbc.gridy++;
        add(lblMount, gbc);
        gbc.gridy = 0;
        gbc.gridx++;
        add(txtName, gbc);
        gbc.gridy++;
        add(txtMount, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(btnEditMount, gbc);
    }

    private void configComponents(ActionListener listener) {
        btnEditMount.addActionListener(listener);
        btnEditMount.setActionCommand(StringConstants.ACTION_COMMAND_EDIT_MOUNT_BUTTON);

        txtName.setEditable(false);
    }

    public long getMaximumMount() {
        if (txtMount.getText().length() <= 0){
            return 0;
        } else
            return Long.parseLong(txtMount.getText());
    }

    public void setDataEditMount(String name, String maximum) {
        txtMount.setText(maximum);
        txtName.setText(name);
    }

    public void close(){
        txtMount.setText("");
        txtName.setText("");
        this.dispose();
    }
}
