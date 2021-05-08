package views;

import exceptions.ArticleNameFieldEmptyException;
import exceptions.EmptyFieldException;
import exceptions.NotNumbersIngresedException;
import jiconfont.icons.font_awesome.FontAwesome;
import models.Article;
import models.Expense;
import models.User;
import utils.StringConstants;
import views.componentRounded.JButtonRounded;
import views.memberWindowDialogs.JDialogCalculator;
import views.memberWindowDialogs.JDialogDebt;
import views.memberWindowDialogs.*;
import views.windowPanels.JPanelGeneralSection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static views.windowUtilitary.ViewUtils.createIcon;

public class PrincipalMemberWindow extends PrincipalWindow implements ActionListener {
    private JPanelGeneralSection pnlGeneralSection;
    private JButtonRounded btnExit;

    public PrincipalMemberWindow(ActionListener listener) {
        super("Miembro Normal - AutoSaving App");
        initComponents(super.getGridBagConstraints(), listener);
        this.setVisible(false);
    }

    private void configComponents(ActionListener listener) {
        btnExit.setIcon(createIcon(FontAwesome.SIGN_OUT, 20, Color.black));
        btnExit.setActionCommand(StringConstants.ACTION_COMMAND_SIGN_OUT);
        btnExit.addActionListener(listener);
    }

    private void initComponents(GridBagConstraints gbc, ActionListener listener) {
        pnlGeneralSection = new JPanelGeneralSection(this, listener);
        btnExit = new JButtonRounded("Salir");

        dlgExpense = new JDialogExpenses(listener);
        dlgDebt = new JDialogDebt(listener);
        dlgCalculator = new JDialogCalculator(listener);
        configComponents(listener);
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


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case StringConstants.ACTION_COMMAND_OPEN_CALC_DIALOG:
                dlgCalculator.setVisible(true);
                break;
            case StringConstants.ACTION_COMMAND_OPEN_DEBT_DIALOG:
                dlgDebt.setVisible(true);
                break;
            case StringConstants.ACTION_COMMAND_OPEN_EXPENSE_DIALOG:
                dlgExpense.setVisible(true);
                break;
        }
    }

    @Override
    public Component getActiveComponent() {
        if(dlgCalculator.isActive())
            return dlgCalculator;
        if(dlgDebt.isActive())
            return dlgDebt;
        if(dlgExpense.isActive())
            return dlgExpense;
        return this;
    }

    @Override
    public void fillCalculatorData(Object calculatorData) {
        ArrayList<Article> articles = (ArrayList<Article>) calculatorData;
        dlgCalculator.fillTable(articles);
    }

    @Override
    public String getNameArticle() throws ArticleNameFieldEmptyException {
        return dlgCalculator.getNameArticle();
    }

    @Override
    public int getCostArticle() throws NotNumbersIngresedException, EmptyFieldException {
        return dlgCalculator.getPriceArticle();
    }

    @Override
    public int getQuantityArticle() throws NotNumbersIngresedException, EmptyFieldException {
        return dlgCalculator.getQuantityArticle();
    }

    @Override
    public void closeCalcWindow() {
        dlgCalculator.clearFields();
        dlgCalculator.clearTable();
        dlgCalculator.setVisible(false);
    }

    @Override
    public void setTotalCalculator(int objectFromJSON) {
        dlgCalculator.setTotal(objectFromJSON);
    }

    @Override
    public void fillExpenseData(Object objectFromJSON) {
        dlgExpense.fillTable((ArrayList<Expense>) objectFromJSON);
    }

    @Override
    public int getMount() throws NotNumbersIngresedException, EmptyFieldException {
       return dlgExpense.getMount();
    }

    @Override
    public LocalDateTime getDate() {
        return dlgExpense.getDate();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void closeAddDialog() {
        dlgExpense.closeAddDialog();
    }

    @Override
    public void setTotalExpenses(int total) {
        dlgExpense.setTotalExpense(total);
    }

    @Override
    public void setDebt(Object objectFromJSON) {
        dlgDebt.setDebt(objectFromJSON);
    }

    @Override
    public void setUsersData(Object objectFromJSON) {

    }

    //Method Only used in Administrator Window
    @Override
    public String getIdUser() {
        return null;
    }

    @Override
    public String getNameUser() {
        return null;
    }

    @Override
    public String getEmailUser() {
        return null;
    }

    @Override
    public void closeAddUserDialog() {

    }

    @Override
    public void setEditUser(User readObjectJson) {

    }

    @Override
    public String getIdToEdit() {
        return null;
    }

    @Override
    public String getIdEditUser() {
        return null;
    }

    @Override
    public String getEditNameUser() {
        return null;
    }

    @Override
    public String getEditEmailUser() {
        return null;
    }

    @Override
    public void closeEditDialog() {

    }

    @Override
    public void fillMountData(Object objectFromJSON) {

    }

    @Override
    public String getIdMount() {
        return null;
    }

    @Override
    public long getMaximumMount() {
        return 0;
    }

    @Override
    public void setMountData(String name, String i) {

    }

    @Override
    public void closeMountDialogEdit() {

    }

    @Override
    public int confirmationDelete() {
        return 0;
    }

    @Override
    public void closeDelete() {

    }
}
