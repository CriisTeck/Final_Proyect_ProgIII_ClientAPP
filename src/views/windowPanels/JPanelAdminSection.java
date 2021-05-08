package views.windowPanels;

import jiconfont.icons.font_awesome.FontAwesome;
import utils.StringConstants;
import views.componentRounded.JButtonRounded;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static views.windowUtilitary.ViewUtils.createIcon;

public class JPanelAdminSection extends JPanel {
    private JButtonRounded btnUsers;
    private JButtonRounded btnSetMount;

    public JPanelAdminSection(ActionListener listener, ActionListener principalListener) {
        this.setLayout(new GridBagLayout());
        initComponents(listener, principalListener);
        this.setVisible(true);
    }

    private void initComponents(ActionListener listener, ActionListener principalListener) {
        btnUsers = new JButtonRounded("Usuarios");
        btnSetMount = new JButtonRounded("Montos");
        configComponents(listener, principalListener);
        positionateComponents();
    }

    private void configComponents(ActionListener listener, ActionListener principalListener) {
        btnUsers.setIcon(createIcon(FontAwesome.USERS, 20, Color.black));
        btnSetMount.setIcon(createIcon(FontAwesome.PENCIL, 20, Color.black));

        btnUsers.addActionListener(listener);
        btnSetMount.addActionListener(listener);

        btnUsers.addActionListener(principalListener);
        btnSetMount.addActionListener(principalListener);

        btnUsers.setActionCommand(StringConstants.ACTION_COMMAND_OPEN_USERS_DIALOG);
        btnSetMount.setActionCommand(StringConstants.ACTION_COMMAND_OPEN_MOUNT_DIALOG);
    }

    private void positionateComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.ipady = 80;
        gbc.ipadx = 80;

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 20, 10, 20);
        add(btnUsers, gbc);

        gbc.gridx++;
        add(btnSetMount, gbc);

    }
}
