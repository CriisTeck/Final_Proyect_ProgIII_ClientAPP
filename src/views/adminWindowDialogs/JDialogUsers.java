package views.adminWindowDialogs;

import jiconfont.icons.font_awesome.FontAwesome;
import utils.StringConstants;
import views.componentRounded.JButtonRounded;
import views.componentRounded.JTextFieldRounded;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static views.windowUtilitary.ViewUtils.createIcon;

public class JDialogUsers extends JDialog {
    private JLabel lblSearchUser;
    private JTextFieldRounded txtSearchUser;
    private JTable tblUsers;
    private JButtonRounded btnSearchUser;
    private JButtonRounded btnAddUser;
    private JButtonRounded btnEditUser;
    private JButtonRounded btnDeleteUser;

    public JDialogUsers(ActionListener listener) {
        this.setSize(500,500);
        this.setLayout(new GridBagLayout());
        this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Gestionar Usuarios");
        initComponents(listener);
        this.setVisible(false);
    }

    private void initComponents(ActionListener listener) {
        lblSearchUser = new JLabel("Nombre");
        txtSearchUser = new JTextFieldRounded(20);
        tblUsers = new JTable();
        btnSearchUser = new JButtonRounded("Buscar Usuario");
        btnAddUser = new JButtonRounded("Agregar Usuario");
        btnEditUser = new JButtonRounded("Editar Usuario");
        btnDeleteUser = new JButtonRounded("Eliminar Usuario");
        configComponents(listener);
        posicionateComponents();
    }

    private void configComponents(ActionListener listener) {
        lblSearchUser.setIcon(createIcon(FontAwesome.SEARCH, 12,Color.black));
        btnSearchUser.setIcon(createIcon(FontAwesome.SEARCH_PLUS, 12,Color.black));
        btnAddUser.setIcon(createIcon(FontAwesome.USER_PLUS, 12,Color.black));
        btnDeleteUser.setIcon(createIcon(FontAwesome.USER_TIMES, 12,Color.black));
        btnEditUser.setIcon(createIcon(FontAwesome.USER_MD, 12,Color.black));

        btnAddUser.addActionListener(listener);
        btnEditUser.addActionListener(listener);
        btnSearchUser.addActionListener(listener);
        btnDeleteUser.addActionListener(listener);

        btnAddUser.setActionCommand(StringConstants.ACTION_COMMAND_ADD_USER);
        btnEditUser.setActionCommand(StringConstants.ACTION_COMMAND_EDIT_USER);
        btnSearchUser.setActionCommand(StringConstants.ACTION_COMMAND_SEARCH_USER_DIALOG_USER);
        btnDeleteUser.setActionCommand(StringConstants.ACTION_COMMAND_DELETE_USER);
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
         add(btnAddUser,gbc);
         gbc.gridy++;
         add(btnEditUser, gbc);
         gbc.gridy++;
         add(btnDeleteUser, gbc);
    }

}
