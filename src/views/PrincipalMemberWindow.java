package views;

import jiconfont.icons.font_awesome.FontAwesome;
import views.adminWindowPanels.JPanelGeneralSection;
import views.componentRounded.JButtonRounded;
import views.memberWindowDialogs.JDialogCalculator;
import views.memberWindowDialogs.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static views.windowUtilitary.ViewUtils.createIcon;

public class PrincipalMemberWindow extends PrincipalWindow implements ActionListener{
    private JPanelGeneralSection pnlGeneralSection;
    private JButtonRounded btnExit;

    private JDialogCalculator dlgCalculator;
    private JDialogExpenses dlgExpense;
    private JDialogDebt dlgDebt;

    public PrincipalMemberWindow(ActionListener listener) {
        super("Miembro Normal");
        initComponents(super.getGridBagConstraints(), listener);
        this.setVisible(true);
    }
    
    private void initComponents(GridBagConstraints gbc, ActionListener listener){
        pnlGeneralSection = new JPanelGeneralSection(this);
        btnExit = new JButtonRounded("Salir");
        
        configComponents();
        positionateComponents(gbc);
    }

    private void positionateComponents(GridBagConstraints gbc) {
        gbc.gridy++;
        gbc.weighty = 0.4;
        gbc.insets = new Insets(100, 10, 50, 10);
        gbc.fill = GridBagConstraints.BOTH;
        add(pnlGeneralSection, gbc);

        gbc.gridy++;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.ipady = 20;
        gbc.ipadx = 15;
        gbc.insets = new Insets(0, 0, 20, 30);
        gbc.anchor = GridBagConstraints.EAST;
        add(btnExit, gbc);
    }

    private void configComponents() {
        btnExit.setIcon(createIcon(FontAwesome.SIGN_OUT, 20, Color.black));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
