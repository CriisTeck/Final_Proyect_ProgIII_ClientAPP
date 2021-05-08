package views.memberWindowDialogs;

import exceptions.ArticleNameFieldEmptyException;
import exceptions.EmptyFieldException;
import exceptions.NotNumbersIngresedException;
import jiconfont.icons.font_awesome.FontAwesome;
import models.Article;
import utils.StringConstants;
import views.componentRounded.JButtonRounded;
import views.componentRounded.JTextFieldRounded;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private DefaultTableModel tblModel;

    private JScrollPane scpTableScroll;

    public JDialogCalculator(ActionListener listener) {
        this.setSize(850, 600);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(850, 600));
        this.setLayout(new GridBagLayout());
        this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        this.setTitle("Calculadora de Articulos");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents(listener);
        this.setVisible(false);
    }

    private void initComponents(ActionListener listener) {
        lblNameArticle = new JLabel("Nombre de Articulo");
        lblPriceArticle = new JLabel("Precio");
        lblQuantityArticle = new JLabel("Cantidad");
        lblTotal = new JLabel("Total");

        txtNameArticle = new JTextFieldRounded(0);
        txtPriceArticle = new JTextFieldRounded(0);
        txtQuantityArticle = new JTextFieldRounded(0);

        btnAddArticle = new JButtonRounded("Agregar");
        btnUndo = new JButtonRounded("Deshacer");
        btnRedo = new JButtonRounded("Rehacer");
        btnDoExpense = new JButtonRounded("Realizar Gasto");

        tblArticles = new JTable();
        tblModel = new DefaultTableModel(new Object[]{"Articulo", "Costo", "Cantidad"}, 0);
        scpTableScroll = new JScrollPane();
        configComponents(listener);
        posicionateComponents();
    }

    private void configComponents(ActionListener listener) {
        btnAddArticle.setIcon(createIcon(FontAwesome.PLUS, 20, Color.black));
        btnUndo.setIcon(createIcon(FontAwesome.UNDO, 20, Color.black));
        btnRedo.setIcon(createIconWithCode('\uf01e', 20, Color.black));
        btnDoExpense.setIcon(createIconWithCode('\uf155', 20, Color.black));

        btnRedo.setRolloverEnabled(true);
        btnRedo.setSelected(true);
        btnRedo.setMargin(new Insets(5, 5, 5, 5));
        btnRedo.setFocusPainted(true);
        tblArticles.setModel(tblModel);
        scpTableScroll.setViewportView(tblArticles);

        btnAddArticle.setActionCommand(StringConstants.ACTION_COMMAND_ADD_ARTICLE);
        btnRedo.setActionCommand(StringConstants.ACTION_COMMAND_REDO_ARTICLE);
        btnUndo.setActionCommand(StringConstants.ACTION_COMMAND_UNDO_ARTICLE);
        btnDoExpense.setActionCommand(StringConstants.ACTION_COMMAND_DO_EXPENSE);

        btnAddArticle.addActionListener(listener);
        btnRedo.addActionListener(listener);
        btnUndo.addActionListener(listener);
        btnDoExpense.addActionListener(listener);

    }

    private void posicionateComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.1;
        gbc.weightx = 0.1;
        gbc.ipadx = 12;
        gbc.ipady = 12;
        gbc.insets = new Insets(10, 50, 10, 5);
        add(lblNameArticle, gbc);

        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.gridx++;
        add(lblPriceArticle, gbc);

        gbc.insets = new Insets(10, 5, 10, 50);
        gbc.gridx++;
        add(lblQuantityArticle, gbc);

        gbc.insets = new Insets(10, 50, 10, 5);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridy++;
        gbc.gridx--;
        gbc.gridx--;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtNameArticle, gbc);
        gbc.insets = new Insets(10, 5, 10, 5);

        gbc.gridx++;
        add(txtPriceArticle, gbc);

        gbc.gridx++;
        add(txtQuantityArticle, gbc);
        gbc.fill = GridBagConstraints.NONE;

        gbc.insets = new Insets(10, 5, 10, 50);
        gbc.gridx++;
        add(btnAddArticle, gbc);

        gbc.insets = new Insets(10, 50, 10, 5);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridheight = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 0.8;
        gbc.weightx = 0.8;
        gbc.ipady = 200;
        gbc.fill = GridBagConstraints.BOTH;
        add(scpTableScroll, gbc);
        gbc.ipady = 12;
        gbc.weighty = 0.1;

        gbc.weightx = 0.2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);
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
        gbc.insets = new Insets(10, 50, 10, 10);
        add(lblTotal, gbc);
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 50, 10, 50);
        gbc.gridx += 2;
        add(btnDoExpense, gbc);
    }

    public void fillTable(ArrayList<Article> articles) {
        clearTable();
        for (Article article : articles) {
            tblModel.addRow(article.toArray());
        }
        tblArticles.setModel(tblModel);
        clearFields();
    }

    public void clearTable() {
        tblModel.setRowCount(0);
    }

    public void clearFields() {
        txtQuantityArticle.setText("");
        txtPriceArticle.setText("");
        txtNameArticle.setText("");
    }

    public String getNameArticle() throws ArticleNameFieldEmptyException {
        if (txtNameArticle.getText().length() > 0)
            return txtNameArticle.getText();
        throw new ArticleNameFieldEmptyException();
    }

    public int getPriceArticle() throws NotNumbersIngresedException, EmptyFieldException {
        if (txtPriceArticle.getText().length() > 0)
            if (txtPriceArticle.getText().matches("^[\\d]+$"))
                return Integer.parseInt(txtPriceArticle.getText());
            else throw new NotNumbersIngresedException(lblPriceArticle.getText());
        else throw new EmptyFieldException(lblPriceArticle.getText());
    }

    public int getQuantityArticle() throws NotNumbersIngresedException, EmptyFieldException {
        if (txtQuantityArticle.getText().length() > 0)
            if (txtQuantityArticle.getText().matches("^[\\d]+$"))
                return Integer.parseInt(txtQuantityArticle.getText());
            else throw new NotNumbersIngresedException(lblQuantityArticle.getText());
        else throw new EmptyFieldException(lblQuantityArticle.getText());
    }

    public void setTotal(int objectFromJSON) {
        lblTotal.setText("Total:\t\t\n  "+objectFromJSON);
    }
}