package views;

import exceptions.ArticleNameFieldEmptyException;
import exceptions.BadEmailFormatException;
import exceptions.EmptyFieldException;
import exceptions.NotNumbersIngresedException;
import jiconfont.icons.font_awesome.FontAwesome;
import models.Article;
import models.Expense;
import models.User;
import utils.StringConstants;
import views.adminWindowDialogs.JDialogMount;
import views.adminWindowDialogs.JDialogUsers;
import views.componentRounded.JButtonRounded;
import views.memberWindowDialogs.JDialogCalculator;
import views.memberWindowDialogs.JDialogDebt;
import views.memberWindowDialogs.JDialogExpenses;
import views.windowPanels.JPanelAdminSection;
import views.windowPanels.JPanelGeneralSection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static views.windowUtilitary.ViewUtils.createIcon;

public class PrincipalAdminWindow extends PrincipalWindow implements ActionListener {
    private JPanelGeneralSection pnlGeneralSection;
    private JPanelAdminSection pnlAdminSection;
    private JButtonRounded btnExit;

    public PrincipalAdminWindow(ActionListener listener) {
        super("Administrator - AutoSaving App");
        this.setVisible(false);
        initComponents(super.getGridBagConstraints(), listener);
    }

    private void initComponents(GridBagConstraints gbc, ActionListener listener) {
        pnlGeneralSection = new JPanelGeneralSection(this, listener);
        pnlAdminSection = new JPanelAdminSection(this, listener);
        btnExit = new JButtonRounded("Salir");

        dlgMount = new JDialogMount(listener);
        dlgUsers = new JDialogUsers(listener);

        dlgCalculator = new JDialogCalculator(listener);
        dlgDebt = new JDialogDebt(listener);
        dlgExpense = new JDialogExpenses(listener);

        configComponents(listener);
        positionateComponents(gbc);

    }

    private void configComponents(ActionListener listener) {
        btnExit.setIcon(createIcon(FontAwesome.SIGN_OUT, 20, Color.black));
        btnExit.setActionCommand(StringConstants.ACTION_COMMAND_SIGN_OUT);
        btnExit.addActionListener(listener);
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
                break;
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

    @Override
    public Component getActiveComponent() {
        if(dlgCalculator.isActive())
            return dlgCalculator;
        if(dlgDebt.isActive())
            return dlgDebt;
        if(dlgExpense.isActive())
            return dlgExpense;
        if(dlgMount.isActive())
            return dlgMount;
        if(dlgUsers.isActive())
            return dlgUsers;
        return this;
    }

    @Override
    public void fillCalculatorData(Object objectFromJSON) {
        dlgCalculator.fillTable((ArrayList<Article>) objectFromJSON);
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
        dlgCalculator.dispose();
    }

    @Override
    public void setTotalCalculator(int objectFromJSON) {
        dlgCalculator.setTotal(objectFromJSON);
    }

    @Override
    public void fillExpenseData(Object objectFromJSON) {
        dlgExpense.fillTable((ArrayList<Expense>)objectFromJSON);
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
        return dlgExpense.getDescription();
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
        dlgUsers.setUserData((ArrayList<User>) objectFromJSON);
    }

    @Override
    public String getIdUser() throws EmptyFieldException {
        return dlgUsers.getIdUser();
    }

    @Override
    public String getNameUser() throws EmptyFieldException {
        return dlgUsers.getNameUser();
    }

    @Override
    public String getEmailUser() throws EmptyFieldException, BadEmailFormatException {
        return dlgUsers.getEmailUser();
    }

    @Override
    public void closeAddUserDialog() {
        dlgUsers.closeAddDialog();
    }

    @Override
    public void setEditUser(User readObjectJson) {
        dlgUsers.setEditUser(readObjectJson);
    }

    @Override
    public String getIdToEdit() {
        return dlgUsers.getIdToEdit();
    }

    @Override
    public String getIdEditUser() throws EmptyFieldException {
        return dlgUsers.getIdEditUser();
    }

    @Override
    public String getEditNameUser() throws EmptyFieldException {
        return dlgUsers.getEditNameUser();
    }

    @Override
    public String getEditEmailUser() throws BadEmailFormatException, EmptyFieldException {
        return dlgUsers.getEditEmailUser();
    }

    @Override
    public void closeEditDialog() {
        dlgUsers.closeEditDialog();
    }

    @Override
    public void fillMountData(Object objectFromJSON) {
        dlgMount.fillMountData((ArrayList<User>)objectFromJSON);
    }

    @Override
    public String getIdMount() {
        return dlgMount.getIdMount();
    }

    @Override
    public long getMaximumMount() {
        return dlgMount.getMaximumMount();
    }

    @Override
    public void setMountData(String name, String i) {
        dlgMount.setDataEditMount(name,i);
    }

    @Override
    public void closeMountDialogEdit() {
        dlgMount.closeEditMount();
    }

    @Override
    public int confirmationDelete() {
        return dlgUsers.deleteUserConfirmation();
    }

    @Override
    public void closeDelete() {

    }
}
