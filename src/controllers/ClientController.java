package controllers;

import exceptions.*;
import net.Connection;
import persistence.WriterLog;
import utils.CodeRequest;
import utils.MessageRequest;
import utils.StringConstants;
import views.*;
import views.windowUtilitary.BugDesk;

import java.awt.event.*;
import java.io.IOException;

public class ClientController extends WindowAdapter implements IObserver, ActionListener, MouseListener {
    private PrincipalWindow view;
    private LoginWindow login;
    private Connection conn;

    public ClientController() {
        try {
            conn = new Connection("localhost", null);
            login = new LoginWindow(this, this, this);
            if (conn.isRunning()) {
                init();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void init() throws InterruptedException {
        while (true) {
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
                    }
                }
            } catch (IOException e) {
                Thread.sleep(1000);
                e.printStackTrace();
            }
        }
    }

    private void showAdvice(String message) throws IOException {
        if (message.equals(MessageRequest.ADVICE_CLIENT_LAUNCHED)) {
            startClient();
            return;
        } else if (message.equals(MessageRequest.ADVICE_EMAIL_SENDED)) {
            BugDesk.showAdvice(StringConstants.EMAIL_SENDED, login.getActiveComponent());
            login.disposeEmailWindow();
        } else if (login.isVisible())
            BugDesk.showAdvice(message, login.getActiveComponent());
        else if (view.isVisible())
            BugDesk.showAdvice(message, view.getActiveComponent());

    }

    private void startClient() throws IOException {
        String[] code = conn.readData();
        if (code[1].equals("MEMBER_TYPE"))
            view = new PrincipalMemberWindow();
        else if (code[1].equals("ADMIN_TYPE"))
            view = new PrincipalAdminWindow();
        login.dispose();
    }

    private void showError(String message) throws IOException {
        if (login.isVisible())
            BugDesk.showError(message, login.getActiveComponent());
        else if (view.isVisible())
            BugDesk.showError(message, view.getActiveComponent());
        if (message.equals(new EmailAlreadyRegisteredException().getMessage()) || message.equals(new UserAlreadyExistsException().getMessage()))
            conn.sendCreateUserRequest();
        if (message.equals(new EmailNotRegisteredException().getMessage()))
            login.enableBtn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (e.getActionCommand()) {
                case "Login":
                    conn.sendLoginData(login.getUsername(), login.getPassword());
                    break;
                case "SendEmail":
                    conn.sendRecoverEmail(login.getEmailToRecover());
                    break;
                case "CreateUser":
                    conn.sendUser(login.getId(), login.getUserName(), login.getEmail(), login.getRole());
                    break;
            }
        } catch (PasswordFieldEmptyException
                | UsernameFieldEmptyException
                | IOException
                | BadEmailFormatException
                | PasswordNotEqualsException
                | UserFieldEmptyException
                | EmailFieldEmptyException
                | NotRoleSelectedException ex) {
            try {
                showError(ex.getMessage());
            } catch (IOException ioException) {
                WriterLog.writeLog();
            }
        }
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
