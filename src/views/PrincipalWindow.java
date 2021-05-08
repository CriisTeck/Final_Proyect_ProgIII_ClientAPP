package views;

import exceptions.ArticleNameFieldEmptyException;
import exceptions.BadEmailFormatException;
import exceptions.EmptyFieldException;
import exceptions.NotNumbersIngresedException;
import models.Notification;
import models.User;
import views.adminWindowDialogs.JDialogMount;
import views.adminWindowDialogs.JDialogUsers;
import views.memberWindowDialogs.JDialogCalculator;
import views.memberWindowDialogs.JDialogDebt;
import views.memberWindowDialogs.JDialogExpenses;
import views.principalWindowpanels.JPanelInformation;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class PrincipalWindow extends JFrame {
    private JPanelInformation pnlInformation;
    private GridBagConstraints gbc;


    protected JDialogUsers dlgUsers;
    protected JDialogMount dlgMount;

    protected JDialogCalculator dlgCalculator;
    protected JDialogExpenses dlgExpense;
    protected JDialogDebt dlgDebt;

    public PrincipalWindow(String title){
        super(title);
        this.setSize(800,800);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setLocationRelativeTo(null);
        initComponents();
        this.setVisible(false);
    }

    private void initComponents() {
        pnlInformation = new JPanelInformation();
        posicionateComponents();
    }

    private void posicionateComponents() {
        gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.weighty = 0;
        gbc.weightx = 1;
        add(pnlInformation, gbc);
    }

    public abstract Component getActiveComponent();

    protected GridBagConstraints getGridBagConstraints() {
        return gbc;
    }

    public void fillInitalData(Object[] data) {
        pnlInformation.setNameText((String)data[0]);
        pnlInformation.setMount((double)data[1],(double)data[2]);
    }

    public abstract void fillCalculatorData(Object objectFromJSON);

    public abstract String getNameArticle() throws ArticleNameFieldEmptyException;

    public abstract int getCostArticle() throws NotNumbersIngresedException, EmptyFieldException;

    public abstract int getQuantityArticle() throws NotNumbersIngresedException, EmptyFieldException;

    public abstract void closeCalcWindow();

    public abstract void setTotalCalculator(int objectFromJSON);

    public abstract void fillExpenseData(Object objectFromJSON);

    public abstract int getMount() throws NotNumbersIngresedException, EmptyFieldException;

    public abstract LocalDateTime getDate();

    public abstract String getDescription();

    public abstract void closeAddDialog();

    public abstract void setTotalExpenses(int total);

    public abstract void setDebt(Object objectFromJSON);

    public abstract void setUsersData(Object objectFromJSON);

    public abstract String getIdUser() throws EmptyFieldException;

    public abstract String getNameUser() throws EmptyFieldException;

    public abstract String getEmailUser() throws EmptyFieldException, BadEmailFormatException;

    public abstract void closeAddUserDialog();

    public abstract void setEditUser(User readObjectJson);

    public abstract String getIdToEdit();

    public abstract String getIdEditUser() throws EmptyFieldException;

    public abstract String getEditNameUser() throws EmptyFieldException;

    public abstract String getEditEmailUser() throws BadEmailFormatException, EmptyFieldException;

    public abstract void closeEditDialog();

    public abstract void fillMountData(Object objectFromJSON);

    public abstract String getIdMount();

    public abstract long getMaximumMount();

    public abstract void setMountData(String name, String i);

    public abstract void closeMountDialogEdit();

    public abstract int confirmationDelete();

    public abstract void closeDelete();
}
