package controllers;

import net.Connection;
import utils.CodeRequest;
import utils.TitleRequest;
import views.Console;

import java.io.IOException;

public class ClientController implements IObserver{
    private Console view;
    private Connection conn;
    public ClientController() {
        try {
        conn = new Connection("localhost",null);
        view = new Console();
            if(conn.isRunning()){
                conn.sendRequest(CodeRequest.REQUEST_NEW_USER_CODE, TitleRequest.REQUEST_NEW_USER_TITLE);
                conn.sendUser();
                init();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        while(true){
            try {
                Thread.sleep(100);
                System.out.println("a");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
