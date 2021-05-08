package models;

import structures.stack.Stack;
import utils.EncrypterString;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private Stack<Notification> listNotifications;
    private Calculator buyCalculator;
    private List<Expense> expenseList;
    private long debt;
    private long maxAmountToExpense;
    private TypeAccount role;
    private String emailUser;
    private int totalExpense;

    public User(String id, String name, String emailUser, TypeAccount role) {
        this.id = id;
        this.name = name;
        this.listNotifications = new Stack<>(Notification::compare);
        this.buyCalculator = new Calculator();
        this.debt = 0;
        this.maxAmountToExpense = 0;
        this.role = role;
        this.emailUser = emailUser;
        expenseList = new ArrayList<>();
    }

    public String getid() {
        return id;
    }

    public String getEmail() {
        return EncrypterString.desencrypt(emailUser);
    }

    public long getDebt() {
        return debt;
    }

    public String getName() {
        return name;
    }

    public long getTotalExpense() {
        return totalExpense;
    }

    public long getMaximumMount() {
        return maxAmountToExpense;
    }

    public String getRole() {
        return role.name();
    }
}
