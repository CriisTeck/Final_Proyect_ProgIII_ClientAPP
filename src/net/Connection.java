package net;

import models.TypeAccount;
import models.User;
import persistence.WriterLog;
import utils.EncrypterString;
import utils.JSONUtils;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Connection {
    private static final int PORT = 25850;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private WriterLog writerLog;

    public Connection(String host, WriterLog writerLog) throws IOException {
        this.socket = new Socket(host,PORT);
        this.writerLog = writerLog;
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
    }

    public String readData(){
        return "";
    }

    public void sendData(Object data){

    }

    public void closeSocket() throws IOException {
        socket.close();
    }

    public boolean isRunning() throws IOException {
        return !socket.isClosed();
    }


    public void sendRequest(String requestNewUserCode, String requestNewUserTitle) {
        try {
            output.writeUTF(JSONUtils.requestToJSON(requestNewUserCode,requestNewUserTitle));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendUser() {
        Scanner in = new Scanner(System.in);
        System.out.println("Usuario: ");
        String user = in.nextLine();
        System.out.println("Contraseña: ");
        String pass = in.nextLine();
        String id = user+"@"+ EncrypterString.encrypt(pass);
        System.out.println("NOmbre: ");
        String nombre = in.nextLine();
        User userd = new User(id,nombre, TypeAccount.ADMIN);
        try {
            output.writeUTF(JSONUtils.objectToJSON(userd,User.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
