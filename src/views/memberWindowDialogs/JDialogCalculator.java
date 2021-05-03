package views.memberWindowDialogs;

import jiconfont.icons.font_awesome.FontAwesome;
import views.componentRounded.*;
import views.componentRounded.JTextFieldRounded;

import javax.swing.*;
import java.awt.*;

import static views.windowUtilitary.ViewUtils.createIcon;
import static views.windowUtilitary.ViewUtils.createIconWithCode;

public class JDialogCalculator extends JDialog {
    private JLabel lblNameArticle;
    private JLabel lblPriceArticle;
    private JLabel lblQuantityArticle;
    private JLabel lblTotal;

    private JTextFieldRounded txtNameArticle;
    private JTextFieldRounded txtPriceArticle;
    private JTextFieldRounded txtQuantityArticle;

    private JButtonRounded btnAddArticle;
    private JButtonRounded btnUndo;
    private JButtonRounded btnRedo;
    private JButtonRounded btnDoExpense;

    private JTable tblArticles;

    public JDialogCalculator() {
        this.setSize(850,600);
        this.setMinimumSize(new Dimension(850,600));
        this.setLayout(new GridBagLayout());
        this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        this.setTitle("Calculadora de Articulos");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        this.setVisible(true);
    }

    private void initComponents() {
        lblNameArticle = new JLabel("Nombre de Articulo");
        lblPriceArticle = new JLabel("Precio");
        lblQuantityArticle = new JLabel("Cantidad");
        lblTotal = new JLabel("Total");

        txtNameArticle = new JTextFieldRounded(15);
        txtPriceArticle = new JTextFieldRounded(15);
        txtQuantityArticle = new JTextFieldRounded(15);

        btnAddArticle = new JButtonRounded("Agregar");
        btnUndo = new JButtonRounded("Deshacer");
        btnRedo = new JButtonRounded("Rehacer");
        btnDoExpense = new JButtonRounded("Realizar Gasto");

        tblArticles = new JTable();
        configComponents();
        posicionateComponents();
    }

    private void configComponents() {
        btnAddArticle.setIcon(createIcon(FontAwesome.PLUS,20,Color.black));
        btnUndo.setIcon(createIcon(FontAwesome.UNDO,20,Color.black));
        btnRedo.setIcon(createIconWithCode('\uf01e',20,Color.black));
        btnDoExpense.setIcon(createIconWithCode('\uf155',20,Color.black));

        btnRedo.setRolloverEnabled(true);
        btnRedo.setSelected(true);
        btnRedo.setMargin(new Insets(5,5,5,5));
        btnRedo.setFocusPainted(true);
    }

    private void posicionateComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.gridx = 0;
        gbc.gridy =0;
        gbc.weighty = 0.1;
        gbc.weightx = 0.1;
        gbc.ipadx = 12;
        gbc.ipady = 12;
        gbc.insets = new Insets(10,50,10,5);
        add(lblNameArticle,gbc);

        gbc.insets = new Insets(10,5,10,5);
        gbc.gridx++;
        add(lblPriceArticle,gbc);

        gbc.insets = new Insets(10,5,10,50);
        gbc.gridx++;
        add(lblQuantityArticle, gbc);

        gbc.insets = new Insets(10,50,10,5);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridy++;
        gbc.gridx--;
        gbc.gridx--;
        add(txtNameArticle, gbc);
        gbc.insets = new Insets(10,5,10,5);

        gbc.gridx++;
        add(txtPriceArticle, gbc);

        gbc.gridx++;
        add(txtQuantityArticle, gbc);

        gbc.insets = new Insets(10,5,10,50);
        gbc.gridx++;
        add(btnAddArticle, gbc);

        gbc.insets = new Insets(10,50,10,5);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridheight = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 0.8;
        gbc.weightx = 0.8;
        gbc.ipady = 200;
        gbc.fill = GridBagConstraints.BOTH;
        add(tblArticles, gbc);
        gbc.ipady = 12;
        gbc.weighty = 0.1;

        gbc.weightx = 0.2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,50,10,50);
        gbc.gridheight = 1;
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.SOUTH;
        add(btnUndo, gbc);

        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridy++;
        add(btnRedo, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(10,50,10,10);
        add(lblTotal, gbc);
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10,50,10,50);
        gbc.gridx+=2;
        add(btnDoExpense, gbc);
    }

    public static void main(String[] args) {
        new JDialogCalculator();
    }
}