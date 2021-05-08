package net;

import models.Article;
import models.Expense;
import models.TypeAccount;
import models.User;
import utils.CodeRequest;
import utils.JSONUtils;
import utils.MessageRequest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

public class Connection {
    private static final int PORT = 25850;
    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;

    public Connection(String host) throws IOException {
        this.socket = new Socket(host, PORT);
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
    }

    public String[] readData() throws IOException {
        String i = input.readUTF();
        return (String[]) JSONUtils.objectFromJSON(i, String[].class);
    }

    public void closeSocket() throws IOException {
        sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_CLOSE_CONEXION_CODE);
        socket.close();
    }

    public boolean isRunning() throws IOException {
        return !socket.isClosed();
    }


    public void sendRequest(String typeRequest, String messageRequest) throws IOException {
        output.writeUTF(JSONUtils.requestToJSON(typeRequest, messageRequest));
    }

    public void sendUser(String id, String name, String email, TypeAccount role) throws IOException {
        output.writeUTF(JSONUtils.objectToJSON(new User(id, name, email, role), User.class));
    }

    public void sendLoginData(String username, String password) throws IOException {
        sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_SIGN_IN_CODE);
        output.writeUTF(JSONUtils.objectToJSON(new String[]{username, password}, String[].class));
    }

    public boolean inputAvalaible() throws IOException {
        return input.available() > 0;
    }

    public void sendRecoverEmail(String emailToRecover) throws IOException {
        sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_SEND_EMAIL_RECOVER_CODE);
        output.writeUTF(JSONUtils.objectToJSON(emailToRecover, String.class));
    }

    public void sendCreateUserRequest() throws IOException {
        sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_NEW_USER_CODE);
    }

    public void finishConexion() throws IOException {
        closeSocket();
    }

    public String readObjectJson() throws IOException {
        return input.readUTF();
    }

    public void sendArticle(String nameArticle, int costArticle, int quantityArticle) throws IOException {
        output.writeUTF(JSONUtils.objectToJSON(new Article(quantityArticle, nameArticle, costArticle), Article.class));
    }

    public void sendExpense(int mount, LocalDateTime date, String description) throws IOException {
        Expense expense = new Expense(mount, date, description);
        output.writeUTF(JSONUtils.objectToJSON(expense, expense.getClass()));
    }

    public void sendDataUser(String idToEdit) throws IOException {
        output.writeUTF(JSONUtils.objectToJSON(idToEdit, String.class));
    }

    public void sendArray(String idMount, long maximumMount) throws IOException {
        output.writeUTF(JSONUtils.objectToJSON(new String[]{idMount, String.valueOf(maximumMount)}, String[].class));
    }

    public void sendSignOutSignal() throws IOException {
        sendRequest(CodeRequest.REQUEST, MessageRequest.REQUEST_SIGN_OUT);
    }
}
