package views.adminWindowDialogs;

import exceptions.BadEmailFormatException;
import exceptions.EmptyFieldException;
import jiconfont.icons.font_awesome.FontAwesome;
import models.User;
import utils.StringConstants;
import views.adminWindowDialogs.userDialogs.JDialogAddUser;
import views.adminWindowDialogs.userDialogs.JDialogEditUser;
import views.componentRounded.JButtonRounded;
import views.componentRounded.JTextFieldRounded;
import views.windowUtilitary.ViewUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.ArrayList;

import static utils.EncrypterString.encrypt;
import static views.windowUtilitary.ViewUtils.createIcon;

public class JDialogUsers extends JDialog implements ActionListener {
    private JLabel lblSearchUser;
    private JTextFieldRounded txtSearchUser;
    private JTable tblUsers;
    private JButtonRounded btnSearchUser;
    private JButtonRounded btnAddUser;
    private JButtonRounded btnEditUser;
    private JButtonRounded btnDeleteUser;

    private DefaultTableModel tblModel;
    private JScrollPane jspPane;

    private JDialogAddUser dlgAddUser;
    private JDialogEditUser dlgEditUser;

    public JDialogUsers(ActionListener listener) {
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
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
        dlgAddUser = new JDialogAddUser(listener);
        dlgEditUser = new JDialogEditUser(listener);
        jspPane = new JScrollPane();
        tblModel = new DefaultTableModel(new Object[]{"Id","Nombre", "Total Gastos", "Deuda", "Maximo Gastos", "Role"}, 0);
        jspPane.setViewportView(tblUsers);
        configComponents(listener);
        posicionateComponents();
    }

    private void configComponents(ActionListener listener) {
        lblSearchUser.setIcon(createIcon(FontAwesome.SEARCH, 12, Color.black));
        btnSearchUser.setIcon(createIcon(FontAwesome.SEARCH_PLUS, 12, Color.black));
        btnAddUser.setIcon(createIcon(FontAwesome.USER_PLUS, 12, Color.black));
        btnDeleteUser.setIcon(createIcon(FontAwesome.USER_TIMES, 12, Color.black));
        btnEditUser.setIcon(createIcon(FontAwesome.USER_MD, 12, Color.black));

        btnEditUser.addActionListener(this);
        btnAddUser.addActionListener(this);
        btnDeleteUser.addActionListener(this);
        btnAddUser.addActionListener(listener);
        btnEditUser.addActionListener(listener);
        btnSearchUser.addActionListener(listener);
        btnDeleteUser.addActionListener(listener);


        btnAddUser.setActionCommand(StringConstants.ACTION_COMMAND_ADD_USER);
        btnEditUser.setActionCommand(StringConstants.ACTION_COMMAND_EDIT_USER);
        btnSearchUser.setActionCommand(StringConstants.ACTION_COMMAND_SEARCH_USER_DIALOG_USER);
        btnDeleteUser.setActionCommand(StringConstants.ACTION_COMMAND_DELETE_USER);

        txtSearchUser.setEditable(false);
        txtSearchUser.setText("Not Working");

        tblUsers.setModel(tblModel);
    }

    private void posicionateComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.insets = new Insets(20, 30, 0, 30);
        add(lblSearchUser, gbc);
        gbc.gridy++;

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 30, 20, 10);
        gbc.weighty = 0.1;
        gbc.ipady = 10;
        gbc.weightx = 0.5;
        add(txtSearchUser, gbc);
        gbc.insets = new Insets(0, 10, 20, 30);
        gbc.gridx++;
        gbc.weightx = 0;

        add(btnSearchUser, gbc);

        gbc.weighty = 0.5;
        gbc.insets = new Insets(10, 30, 50, 10);
        gbc.gridy++;
        gbc.gridx--;
        gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.BOTH;
        add(jspPane, gbc);
        gbc.weighty = 0;
        gbc.gridheight = 1;
        gbc.insets = new Insets(10, 10, 10, 30);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx++;
        add(btnAddUser, gbc);
        gbc.gridy++;
        add(btnEditUser, gbc);
        gbc.gridy++;
        add(btnDeleteUser, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case StringConstants.ACTION_COMMAND_ADD_USER:
                dlgAddUser.setVisible(true);
                break;
            case StringConstants.ACTION_COMMAND_EDIT_USER:
                editUser();
                break;
        }
    }

    public  int deleteUserConfirmation() {
        return ViewUtils.showSignOutMessage(this, StringConstants.MESSAGE_DELETE_USER_CONFIRMATION);
    }

    private void editUser() {
        dlgEditUser.setVisible(true);
    }

    public void setUserData(ArrayList<User> objectFromJSON) {
        tblModel.setRowCount(0);
        for(User user : objectFromJSON){
            tblModel.addRow(new Object[]{user.getid(),user.getName(), user.getTotalExpense(), user.getDebt(), user.getMaximumMount(), user.getRole()});
        }
        tblUsers.setModel(tblModel);
    }

    public String getIdUser() throws EmptyFieldException {
        return MessageFormat.format("{0}@{1}",encrypt(dlgAddUser.getUser()), encrypt(dlgAddUser.getPassword()));
    }

    public String getNameUser() throws EmptyFieldException {
        return dlgAddUser.getNameUser();
    }

    public String getEmailUser() throws EmptyFieldException, BadEmailFormatException {
        return encrypt(dlgAddUser.getEmailUser());
    }

    public void closeAddDialog() {
        dlgAddUser.close();
    }

    public void setEditUser(User readObjectJson) {
        dlgEditUser.setData(readObjectJson);
    }

    public String getIdToEdit() {
        int rowSelected = tblUsers.getSelectedRow();
        if(rowSelected == -1)
            return "";
        return (String) tblModel.getValueAt(rowSelected,0);
    }

    public String getIdEditUser() throws EmptyFieldException {
        return MessageFormat.format("{0}@{1}",encrypt(dlgEditUser.getUser()), encrypt(dlgEditUser.getPassword()));
    }

    public String getEditNameUser() throws EmptyFieldException {
        return dlgEditUser.getEditNameUser();
    }

    public String getEditEmailUser() throws BadEmailFormatException, EmptyFieldException {
        return dlgEditUser.getEditEmailUser();
    }

    public void closeEditDialog() {
        dlgEditUser.close();
    }

}
