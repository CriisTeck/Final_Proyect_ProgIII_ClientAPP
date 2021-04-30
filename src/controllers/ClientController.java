package controllers;

import exceptions.BadEmailFormatException;
import exceptions.PasswordFieldEmptyException;
import exceptions.UsernameFieldEmpty_onLoginException;
import net.Connection;
import views.LoginWindow;
import views.PrincipalWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ClientController extends WindowAdapter implements IObserver, ActionListener {
    private PrincipalWindow view;
    private LoginWindow login;
    private Connection conn;

    public ClientController() {
        try {
            conn = new Connection("localhost", null);
            view = new PrincipalWindow();
            login = new LoginWindow(this,this);
            if (conn.isRunning()) {
                init();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        while (true) {
            try {
                if (conn.inputAvalaible()) {
                    String code = conn.readData()[0];
                }
                Thread.sleep(1000);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
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
            }
        } catch (PasswordFieldEmptyException | UsernameFieldEmpty_onLoginException | IOException | BadEmailFormatException ex) {
            ex.printStackTrace();
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
}
