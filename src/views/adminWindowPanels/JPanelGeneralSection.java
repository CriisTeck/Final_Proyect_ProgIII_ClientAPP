package views.adminWindowPanels;

import jiconfont.icons.font_awesome.FontAwesome;
import views.componentRounded.JButtonRounded;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static views.windowUtilitary.ViewUtils.createIcon;

public class JPanelGeneralSection extends JPanel {
    private JButtonRounded btnCalculator;
    private JButtonRounded btnExpense;
    private JButtonRounded btnDebt;

    public JPanelGeneralSection(ActionListener listener) {
        this.setLayout(new GridBagLayout());
//        this.setBorder(BorderFactory.createLineBorder(Color.gray));
        initComponents(listener);
        this.setVisible(true);
    }

    private void initComponents(ActionListener listener) {
        btnCalculator = new JButtonRounded("Calculadora");
        btnExpense = new JButtonRounded("Gastos");
        btnDebt = new JButtonRounded("Deudas");
        configComponents(listener);
        posicionateComponents();
    }

    private void posicionateComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.ipady = 80;
        gbc.ipadx = 80;

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10,20,10,20);

        add(btnCalculator, gbc);

        gbc.gridx++;
        add(btnExpense, gbc);

        gbc.gridx++;
        add(btnDebt, gbc);
    }

    private void configComponents(ActionListener listener) {
        btnDebt.setIcon(createIcon(FontAwesome.VIACOIN,30,Color.black));
        btnExpense.setIcon(createIcon(FontAwesome.MONEY, 30,Color.BLACK));
        btnCalculator.setIcon(createIcon(FontAwesome.CALCULATOR, 30,Color.BLACK));

    }


}
