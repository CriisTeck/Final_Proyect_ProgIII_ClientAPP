package views;

import controllers.ClientController;
import jiconfont.icons.font_awesome.FontAwesome;
import utils.StringConstants;
import views.adminWindowDialogs.JDialogMount;
import views.adminWindowDialogs.JDialogUsers;
import views.adminWindowPanels.JPanelAdminSection;
import views.adminWindowPanels.JPanelGeneralSection;
import views.componentRounded.JButtonRounded;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static views.windowUtilitary.ViewUtils.createIcon;

public class PrincipalAdminWindow extends PrincipalWindow implements ActionListener {
    private JPanelGeneralSection pnlGeneralSection;
    private JPanelAdminSection pnlAdminSection;
    private JButtonRounded btnExit;

    private JDialogUsers dlgUsers;
    private JDialogMount dlgMount;

    public PrincipalAdminWindow(ActionListener listener) {
        super("Administrator");
        initComponents(super.getGridBagConstraints(), listener);
        this.setVisible(true);
    }

    private void initComponents(GridBagConstraints gbc,ActionListener listener) {
        pnlGeneralSection = new JPanelGeneralSection(this);
        pnlAdminSection = new JPanelAdminSection(this);
        btnExit = new JButtonRounded("Salir");

        dlgMount = new JDialogMount(listener);
        dlgUsers = new JDialogUsers(listener);
        configComponents();
        positionateComponents(gbc);

    }

    private void configComponents() {
        btnExit.setIcon(createIcon(FontAwesome.SIGN_OUT, 20, Color.black));
    }

    private void positionateComponents(GridBagConstraints gbc) {
        gbc.gridy++;
        gbc.weighty = 0.4;
        gbc.insets = new Insets(100, 10, 50, 10);
        gbc.fill = GridBagConstraints.BOTH;
        add(pnlGeneralSection, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(50, 100, 100, 100);
        add(pnlAdminSection, gbc);

        gbc.gridy++;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.ipady = 20;
        gbc.ipadx = 15;
        gbc.insets = new Insets(0, 0, 20, 30);
        gbc.anchor = GridBagConstraints.EAST;
        add(btnExit, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case StringConstants
                    .ACTION_COMMAND_ADD_USER:
                addUser();
                break;
            case StringConstants.ACTION_COMMAND_DELETE_USER:
                deleteUser();
                break;
            case StringConstants.ACTION_COMMAND_EDIT_USER:
                editUser();
                break;
            case StringConstants.ACTION_COMMAND_SEARCH_USER_DIALOG_USER:
                searchUserUsers();
                break;
            case StringConstants.ACTION_COMMAND_OPEN_USERS_DIALOG:
                openUsersDialog();
                break;
            case StringConstants.ACTION_COMMAND_OPEN_MOUNT_DIALOG:
                openMountDialog();
        }
    }

    private void openMountDialog() {
        dlgMount.setVisible(true);
    }

    private void openUsersDialog() {
        dlgUsers.setVisible(true);
    }

    private void searchUserUsers() {
    }

    private void editUser() {
    }

    private void deleteUser() {
    }

    private void addUser() {
    }
}
