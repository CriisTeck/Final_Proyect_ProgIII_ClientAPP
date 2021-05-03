package views.adminWindowDialogs;

import jiconfont.icons.font_awesome.FontAwesome;
import views.componentRounded.JButtonRounded;
import views.componentRounded.JTextFieldRounded;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static views.windowUtilitary.ViewUtils.createIcon;

public class JDialogMount extends JDialog {
    private JLabel lblSearchUser;
    private JTextFieldRounded txtSearchUser;
    private JTable tblUsers;
    private JButtonRounded btnSearchUser;
    private JButtonRounded btnEditMount;

    public JDialogMount(ActionListener listener) {
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        this.setTitle("Establecer Montos");
        this.setSize(500,500);
        initComponents(listener);
        this.setVisible(false);
    }

    private void initComponents(ActionListener listener) {
        lblSearchUser = new JLabel("Nombre");
        txtSearchUser = new JTextFieldRounded(20);
        tblUsers = new JTable();
        btnSearchUser = new JButtonRounded("Buscar Usuario");
        btnEditMount = new JButtonRounded("Editar Montos");
        configComponents(listener);
        posicionateComponents();

    }

    private void configComponents(ActionListener listener) {
        lblSearchUser.setIcon(createIcon(FontAwesome.SEARCH, 12,Color.black));
        btnSearchUser.setIcon(createIcon(FontAwesome.SEARCH_PLUS, 12,Color.black));
        btnEditMount.setIcon(createIcon(FontAwesome.PENCIL, 12, Color.BLACK));

        btnSearchUser.addActionListener(listener);
        btnEditMount.addActionListener(listener);
    }

    private void posicionateComponents() {

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.insets = new Insets(20,30,0,30);
        add(lblSearchUser, gbc);
        gbc.gridy++;

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,30,20,10);
        gbc.weighty = 0.1;
        gbc.ipady = 10;
        gbc.weightx = 0.5;
        add(txtSearchUser, gbc);
        gbc.insets = new Insets(0,10,20,30);
        gbc.gridx++;
        gbc.weightx = 0;

        add(btnSearchUser, gbc);

        gbc.weighty = 0.5;
        gbc.insets = new Insets(10,30,50,10);
        gbc.gridy++;
        gbc.gridx--;
        gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.BOTH;
        add(tblUsers, gbc);
        gbc.weighty = 0;
        gbc.gridheight = 1;
        gbc.insets = new Insets(10,10,10,30);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx++;
        add(btnEditMount,gbc);
    }
}