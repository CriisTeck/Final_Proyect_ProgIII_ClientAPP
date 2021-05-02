package net;

import models.TypeAccount;
import models.User;
import persistence.WriterLog;
import utils.CodeRequest;
import utils.JSONUtils;
import utils.MessageRequest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection {
    private static final int PORT = 25850;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private WriterLog writerLog;

    public Connection(String host, WriterLog writerLog) throws IOException {
        this.socket = new Socket(host, PORT);
        this.writerLog = writerLog;
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
    }

    public String[] readData() throws IOException {
        return (String[]) JSONUtils.objectFromJSON(input.readUTF(), String[].class);
    }

    public void sendData(Object data) {

    }

    public void closeSocket() throws IOException {
        sendRequest(CodeRequest.REQUEST,MessageRequest.REQUEST_CLOSE_CONEXION_CODE);
        socket.close();
    }

    public boolean isRunning() throws IOException {
        return !socket.isClosed();
    }


    public void sendRequest(String typeRequest, String messageRequest) throws IOException {
        output.writeUTF(JSONUtils.requestToJSON(typeRequest, messageRequest));
    }

    public void sendUser(String id, String name, String email, TypeAccount role) throws IOException {
            output.writeUTF(JSONUtils.objectToJSON(new User(id, name,email,role), User.class));
    }

    public void sendLoginData(String username, String password) throws IOException {
        sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_SIGN_IN_CODE);
        output.writeUTF(JSONUtils.objectToJSON(new String[]{username, password}, String[].class));
    }

    public boolean inputAvalaible() throws IOException {
        return input.available() > 0;
    }

    public void sendRecoverEmail(String emailToRecover) throws IOException {
        sendRequest(CodeRequest.REQUEST,MessageRequest.REQUEST_SEND_EMAIL_RECOVER_CODE);
        output.writeUTF(JSONUtils.objectToJSON(emailToRecover, String.class));
    }

    public void sendCreateUserRequest() throws IOException {
        sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_NEW_USER_CODE);
    }

}
