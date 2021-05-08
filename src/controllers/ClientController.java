package controllers;

import com.google.gson.reflect.TypeToken;
import exceptions.*;
import models.Article;
import models.Expense;
import models.TypeAccount;
import models.User;
import net.Connection;
import utils.CodeRequest;
import utils.JSONUtils;
import utils.MessageRequest;
import utils.StringConstants;
import views.LoginWindow;
import views.PrincipalAdminWindow;
import views.PrincipalMemberWindow;
import views.PrincipalWindow;
import views.windowUtilitary.ViewUtils;

import java.awt.event.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ClientController extends WindowAdapter implements IObserver, ActionListener, MouseListener {
    private PrincipalWindow view;
    private LoginWindow login;
    private Connection conn;

    private boolean state;

    public ClientController() {
        startApp();
    }

    private void startApp() {
        try {
            conn = new Connection(StringConstants.HOST);
            login = new LoginWindow(this, this, this);
            if (conn.isRunning()) {
                state = true;
                init();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void init() throws InterruptedException {
        while (state) {
            try {
                if (conn.inputAvalaible()) {
                    String[] code = conn.readData();
                    switch (code[0]) {
                        case CodeRequest.EXCEPTION:
                            showError(code[1]);
                            break;
                        case CodeRequest.ADVICE:
                            showAdvice(code[1]);
                            break;
                        case CodeRequest.REQUEST:
                            processRequest(code[1]);
                            break;
                    }
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void processRequest(String s) throws IOException {
        if (MessageRequest.REQUEST_SEND_INITIAL_DATA.equals(s)) {
            fillInitialData();
        }
    }

    private void fillInitialData() throws IOException {
        Object[] data = (Object[]) JSONUtils.objectFromJSON(conn.readObjectJson(), Object[].class);
        view.fillInitalData(data);
        view.setVisible(true);
    }

    private void showAdvice(String message) throws Exception {
        switch (message) {
            case MessageRequest.ADVICE_CLIENT_LAUNCHED:
                startClient();
                break;
            case MessageRequest.ADVICE_EMAIL_SENDED:
                ViewUtils.showAdvice(StringConstants.EMAIL_SENDED, login.getActiveComponent());
                login.disposeEmailWindow();
                break;
            case MessageRequest.ADVICE_CALCULATOR_SUCCESFULL:
                requestCalcData();
                break;
            case MessageRequest.ADVICE_CALCULATOR_DO_EXPENSE_SUCCESFULL:
                doExpenseSuccesfull();
                break;
            case MessageRequest.ADVICE_EXPENSE_ADD_SUCCESFULL:
                addExpenseSuccesfull();
                break;
            case MessageRequest.ADVICE_USER_ADD_SUCCESFULL:
                addUserSuccessful();
                break;
            case MessageRequest.ADVICE_USER_EDIT_SUCCESFULL:
                editUserSuccessful();
                break;
            case MessageRequest.ADVICE_USER_MOUNT_SUCCESFULL:
                userMountSuccesfull();
                break;
            case MessageRequest.ADVICE_USER_DELETED_SUCCESFULL:
                userDeletedSuccesfull();
                break;
            default:
                if (login.isVisible())
                    ViewUtils.showAdvice(message, login.getActiveComponent());
                else if (view.isVisible())
                    ViewUtils.showAdvice(message, view.getActiveComponent());
        }
    }

    private void userDeletedSuccesfull() throws IOException {
        ViewUtils.showAdvice(StringConstants.MESSAGE_SUCCESFULL_OPERATION, view.getActiveComponent());
        requestDataUsers();
    }

    private void requestInitialData() throws IOException {
        conn.sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_SEND_INITIAL_DATA);
    }

    private void userMountSuccesfull() throws IOException {
        ViewUtils.showAdvice(StringConstants.MESSAGE_SUCCESFULL_OPERATION, view.getActiveComponent());
        view.closeMountDialogEdit();
        mountOpen();
        requestInitialData();
    }

    private void addUserSuccessful() throws IOException {
        ViewUtils.showAdvice(StringConstants.MESSAGE_SUCCESFULL_OPERATION, view.getActiveComponent());
        requestDataUsers();
        view.closeAddUserDialog();
        requestInitialData();
    }

    private void addExpenseSuccesfull() throws IOException {
        ViewUtils.showAdvice(StringConstants.MESSAGE_SUCCESFULL_OPERATION, view.getActiveComponent());
        requestTotalExpenses();
        requestDataExpenses();
        view.closeAddDialog();
        requestInitialData();
    }

    private void requestTotalExpenses() throws IOException {
        conn.sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_TOTAL_EXPENSES);
        view.setTotalExpenses((Integer) JSONUtils.objectFromJSON(conn.readObjectJson(), int.class));
    }

    private void doExpenseSuccesfull() throws IOException {
        ViewUtils.showAdvice(StringConstants.MESSAGE_SUCCESFULL_OPERATION, view.getActiveComponent());
        view.closeCalcWindow();
        requestInitialData();
    }

    private void startClient() throws IOException {
        String[] code = conn.readData();
        if (code[1].equals("MEMBER_TYPE"))
            view = new PrincipalMemberWindow(this);
        else if (code[1].equals("ADMIN_TYPE"))
            view = new PrincipalAdminWindow(this);
        login.dispose();
    }

    private void showError(String message) throws IOException {
        if (login.isVisible())
            ViewUtils.showError(message, login.getActiveComponent());
        else if (view.isVisible())
            ViewUtils.showError(message, view.getActiveComponent());
        if (message.equals(new EmailAlreadyRegisteredException().getMessage()) || message.equals(new UserAlreadyExistsException().getMessage()))
            conn.sendCreateUserRequest();
        if (message.equals(new EmailNotRegisteredException().getMessage()))
            login.enableBtn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (e.getActionCommand()) {
                case StringConstants.ACTION_COMMAND_LOGIN:
                    conn.sendLoginData(login.getUsername(), login.getPassword());
                    break;
                case StringConstants.ACTION_COMMAND_SEND_EMAIL:
                    conn.sendRecoverEmail(login.getEmailToRecover());
                    break;
                case StringConstants.ACTION_COMMAND_CREATE_USER:
                    conn.sendUser(login.getId(), login.getUserName(), login.getEmail(), login.getRole());
                    break;
                case StringConstants.ACTION_COMMAND_SIGN_OUT:
                    signOut();
                    break;
                case StringConstants.ACTION_COMMAND_OPEN_CALC_DIALOG:
                    requestCalcData();
                    break;
                case StringConstants.ACTION_COMMAND_ADD_ARTICLE:
                    requestAddArticle();
                    break;
                case StringConstants.ACTION_COMMAND_UNDO_ARTICLE:
                    requestUndoArticle();
                    break;
                case StringConstants.ACTION_COMMAND_REDO_ARTICLE:
                    requestRedorticle();
                    break;
                case StringConstants.ACTION_COMMAND_DO_EXPENSE:
                    requestDoExpense();
                    break;
                case StringConstants.ACTION_COMMAND_OPEN_EXPENSE_DIALOG:
                    requestDataExpenses();
                    break;
                case StringConstants.ACTION_COMMAND_ADD_EXPENSE_BUTTON:
                    requestAddExpense();
                    break;
                case StringConstants.ACTION_COMMAND_OPEN_DEBT_DIALOG:
                    requestTotalDebt();
                    break;
                case StringConstants.ACTION_COMMAND_PAY_DEBT:
                    payDebt();
                    break;
                case StringConstants.ACTION_COMMAND_OPEN_USERS_DIALOG:
                    requestDataUsers();
                    break;
                case StringConstants.ACTION_COMMAND_ADD_USER_BUTTON:
                    addUser();
                    break;
                case StringConstants.ACTION_COMMAND_EDIT_USER:
                    editUserOpen();
                    break;
                case StringConstants.ACTION_COMMAND_EDIT_USER_BUTTON:
                    editUser();
                    break;
                case StringConstants.ACTION_COMMAND_OPEN_MOUNT_DIALOG:
                    mountOpen();
                    break;
                case StringConstants.ACTION_COMMAND_EDIT_MOUNT:
                    editMountOpen();
                    break;
                case StringConstants.ACTION_COMMAND_DELETE_USER:
                    deleteUser();
                case StringConstants.ACTION_COMMAND_EDIT_MOUNT_BUTTON:
                    editMount();
                    break;
            }
        } catch (Exception ex) {
            try {
                ex.printStackTrace();
                showError(ex.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void deleteUser() throws IOException {
        conn.sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_DELETE_USER);
        if (view.confirmationDelete() == 0)
            conn.sendDataUser(view.getIdToEdit());
        else
            conn.sendDataUser("");

    }

    private void editMountOpen() throws IOException {
        conn.sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_EDIT_MOUNT_MOUNT);
        conn.sendArray(view.getIdMount(), 0);
        String[] data = (String[]) JSONUtils.objectFromJSON(conn.readObjectJson(), String[].class);
        view.setMountData(data[0], data[1]);
    }

    private void editMount() throws IOException {
        conn.sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_EDIT_MOUNT_USER);
        conn.sendArray(view.getIdMount(), view.getMaximumMount());
    }

    private void mountOpen() throws IOException {
        conn.sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_EDIT_MOUNT);
        Type type = new TypeToken<ArrayList<User>>() {
        }.getType();
        view.fillMountData(JSONUtils.userListFromJSON(conn.readObjectJson(), type));
    }

    private void editUserSuccessful() throws IOException {
        ViewUtils.showAdvice(StringConstants.MESSAGE_SUCCESFULL_OPERATION, view.getActiveComponent());
        requestDataUsers();
        view.closeEditDialog();
        requestInitialData();
    }

    private void editUser() throws IOException {
        try {
            conn.sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_EDIT_USER);
            conn.sendDataUser(view.getIdToEdit());
            conn.sendUser(view.getIdEditUser(), view.getEditNameUser(), view.getEditEmailUser(), TypeAccount.MEMBER);
        } catch (EmptyFieldException | BadEmailFormatException e) {
            conn.sendUser("", "", "", null);
        }
    }

    private void editUserOpen() throws IOException {
        conn.sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_DATA_EDIT_USER);
        conn.sendDataUser(view.getIdToEdit());
        view.setEditUser((User) JSONUtils.userListFromJSON(conn.readObjectJson(), User.class));
    }

    private void addUser() throws IOException {
        try {
            conn.sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_ADD_USER);
            String email = view.getEmailUser();
            String id = view.getIdUser();
            String name = view.getNameUser();
            conn.sendUser(id, name, email, TypeAccount.MEMBER);
        } catch (EmptyFieldException | BadEmailFormatException e) {
            conn.sendUser("", "", "", TypeAccount.MEMBER);
        }
    }

    private void requestDataUsers() throws IOException {
        conn.sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_DATA_USERS);
        Type type = new TypeToken<ArrayList<User>>() {
        }.getType();
        view.setUsersData(JSONUtils.userListFromJSON(conn.readObjectJson(), type));
    }

    private void requestTotalDebt() throws IOException {
        conn.sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_TOTAL_DEBT);
        view.setDebt(JSONUtils.objectFromJSON(conn.readObjectJson(), int.class));
    }

    private void payDebt() throws IOException {
        conn.sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_PAY_DEBT);
        view.setDebt(JSONUtils.objectFromJSON(conn.readObjectJson(), int.class));
        requestInitialData();
    }

    private void requestAddExpense() throws IOException, NotNumbersIngresedException, EmptyFieldException {
        conn.sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_ADD_EXPENSE);
        conn.sendExpense(view.getMount(), view.getDate(), view.getDescription());
    }

    private void requestDataExpenses() throws IOException {
        conn.sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_DATA_EXPENSES);
        Type listType = new TypeToken<ArrayList<Expense>>() {
        }.getType();
        String s = conn.readObjectJson();
        view.fillExpenseData(JSONUtils.objectFromJSON(s, listType));
    }

    private void requestUndoArticle() throws IOException {
        conn.sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_UNDO_ARTICLE);
    }

    private void requestRedorticle() throws IOException {
        conn.sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_REDO_ARTICLE);
    }

    private void requestDoExpense() throws IOException {
        conn.sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_DO_EXPENSE);
    }

    private void requestAddArticle() throws IOException, ArticleNameFieldEmptyException, NotNumbersIngresedException, EmptyFieldException {
        conn.sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_ADD_ARTICLE);
        conn.sendArticle(view.getNameArticle(), view.getCostArticle(), view.getQuantityArticle());
    }

    private void requestCalcData() throws IOException {
        conn.sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_DATA_CALCULATOR);
        String text = conn.readObjectJson();
        Type listType = new TypeToken<ArrayList<Article>>() {
        }.getType();
        view.fillCalculatorData(JSONUtils.objectFromJSON(text, listType));
        requestTotalCalc();
    }

    private void requestTotalCalc() throws IOException {
        conn.sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_TOTAL_CALCULATOR);
        view.setTotalCalculator((Integer) JSONUtils.objectFromJSON(conn.readObjectJson(), int.class));
    }

    private void signOut() throws IOException {
        if (ViewUtils.showSignOutMessage(view, StringConstants.MESSAGE_SIGN_OUT) == 1)
            return;
        state = false;
        view.setVisible(false);
        conn.sendSignOutSignal();
        conn.finishConexion();
        System.exit(0);
    }


    @Override
    public void windowClosing(WindowEvent e) {
        try {
            conn.closeSocket();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().getName().equals("lblCreateUser")) {
            try {
                createNewUser();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void createNewUser() throws IOException {
        conn.sendCreateUserRequest();
        switch (conn.readData()[1]) {
            case MessageRequest.THERE_IS_ADMIN_CODE:
                login.setAdminTrue();
                break;
            case MessageRequest.THERE_ISNT_ADMIN_CODE:
                login.setAdminFalse();
                break;
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}