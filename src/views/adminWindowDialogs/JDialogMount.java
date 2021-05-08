package views.adminWindowDialogs;

import jiconfont.icons.font_awesome.FontAwesome;
import models.User;
import utils.StringConstants;
import views.adminWindowDialogs.mountDialogs.JDialogEditMount;
import views.componentRounded.JButtonRounded;
import views.componentRounded.JTextFieldRounded;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static views.windowUtilitary.ViewUtils.createIcon;

public class JDialogMount extends JDialog implements ActionListener {
    private JLabel lblSearchUser;
    private JTextFieldRounded txtSearchUser;
    private JTable tblUsers;
    private JButtonRounded btnSearchUser;
    private JButtonRounded btnEditMount;

    private JDialogEditMount dlgEditMount;

    private DefaultTableModel tblModel;
    private JScrollPane jspPane;

    public JDialogMount(ActionListener listener) {
        this.setSize(500,500);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        this.setTitle("Establecer Montos");
        initComponents(listener);
        this.setVisible(false);
    }

    private void initComponents(ActionListener listener) {
        lblSearchUser = new JLabel("Nombre");
        txtSearchUser = new JTextFieldRounded(20);
        tblUsers = new JTable();
        btnSearchUser = new JButtonRounded("Buscar Usuario");
        btnEditMount = new JButtonRounded("Editar Montos");

        dlgEditMount = new JDialogEditMount(listener);
        jspPane = new JScrollPane();
        tblModel = new DefaultTableModel(new Object[]{"Id","Nombre","Monto maximo"},0);
        configComponents(listener);
        posicionateComponents();

    }

    private void configComponents(ActionListener listener) {
        lblSearchUser.setIcon(createIcon(FontAwesome.SEARCH, 12,Color.black));
        btnSearchUser.setIcon(createIcon(FontAwesome.SEARCH_PLUS, 12,Color.black));
        txtSearchUser.setEditable(false);
        txtSearchUser.setText("Not Working");
        btnEditMount.setIcon(createIcon(FontAwesome.PENCIL, 12, Color.BLACK));

        btnSearchUser.addActionListener(listener);
        btnEditMount.addActionListener(this);
        btnEditMount.addActionListener(listener);

        btnEditMount.setActionCommand(StringConstants.ACTION_COMMAND_EDIT_MOUNT);
        tblUsers.setModel(tblModel);
        jspPane.setViewportView(tblUsers);
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
        add(jspPane, gbc);
        gbc.weighty = 0;
        gbc.gridheight = 1;
        gbc.insets = new Insets(10,10,10,30);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx++;
        add(btnEditMount,gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (StringConstants.ACTION_COMMAND_EDIT_MOUNT.equals(e.getActionCommand())) {
            dlgEditMount.setVisible(true);
        }
    }

    public void fillMountData(ArrayList<User> data){
        tblModel.setRowCount(0);
        for(User user : data){
            tblModel.addRow(new Object[]{user.getid(), user.getName(), user.getMaximumMount()});
        }
        tblUsers.setModel(tblModel);
    }

    public String getIdMount(){
        int rowSelected = tblUsers.getSelectedRow();
        if(rowSelected == -1)
            return "";
        return (String) tblModel.getValueAt(rowSelected,0);
    }

    public void setDataEditMount(String name, String maximum){
        dlgEditMount.setDataEditMount(name,maximum);
    }

    public long getMaximumMount(){
        return dlgEditMount.getMaximumMount();
    }

    public void closeEditMount(){
        dlgEditMount.close();
    }
}