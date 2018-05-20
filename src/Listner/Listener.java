package Listner;


import ChatClient.ChatBoxController;
import Entites.User.Utilisateur;
import VIEWS.Ui_MainFrame_FOController;
import com.messages.Message;
import com.messages.MessageType;
import com.messages.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

import static com.messages.MessageType.CONNECTED;
import mysoulmate.MySoulMate;

public class Listener implements Runnable{

    private static final String HASCONNECTED = "has connected";
    private Socket socket;
    public String hostname;
    public static Utilisateur client;
    public int port;   
    public Ui_MainFrame_FOController controller;
    private static ObjectOutputStream oos;
    private InputStream is;
    private ObjectInputStream input;
    private OutputStream outputStream;
    Logger logger = LoggerFactory.getLogger(Listener.class);
    public static Utilisateur getClient()
    {
        return client;
    }
    public Listener(String hostname, int port, Utilisateur client, Ui_MainFrame_FOController controller) {
        this.hostname = hostname;
        this.port = port;
        Listener.client=client;
        this.controller = controller;
    }
    @Override
    public void run() {
        try {
            socket = new Socket(hostname, port);
          //  LoginController.getInstance().showScene();
            outputStream = socket.getOutputStream();
            oos = new ObjectOutputStream(outputStream);
            is = socket.getInputStream();
            input = new ObjectInputStream(is);
        } catch (IOException e) {
        //    LoginController.getInstance().showErrorDialog("Could not connect to server");
            logger.error("Could not Connect");
        }
        logger.info("Connection accepted " + socket.getInetAddress() + ":" + socket.getPort());

        try {
            connect();
            logger.info("Sockets in and out ready!");
            while (socket.isConnected()) {
                Message message = null;
                message = (Message) input.readObject();

                if (message != null) {
                    logger.debug("Message recieved:" + message.getMsg() + " MessageType:" + message.getType() + "Sender:" + message.getID());
                    switch (message.getType()) {
                        case USER:
                            MySoulMate.getMainController().addToChat(message);
                         for (ChatBoxController x : Ui_MainFrame_FOController.getStatic_Controllerschat_windows()) {    
                            x.addToChat(message);     
                    }
                            break;
                        case VOICE:
                            System.out.println("voice is being sent");
                            MySoulMate.getMainController().addToChat(message);
                    for (ChatBoxController x : Ui_MainFrame_FOController.getStatic_Controllerschat_windows()) {
                            System.out.println("writing voice into proper window");
                            System.out.println(message.getSender());
                            System.out.println(message.getReciver());
                            x.addToChat(message);
                    }
                            break;
                        case NOTIFICATION:
                            System.out.println("sender id :"+message.getID());
                           controller.newUserNotification(message);
                            break;
                        case SERVER:
                        for (ChatBoxController x : Ui_MainFrame_FOController.getStatic_Controllerschat_windows()) 
                               x.addAsServer(message);
                            break;
                        case CONNECTED:
                           controller.setUserList(message);
                            break;
                        case DISCONNECTED:
                           controller.setUserList(message);
                            break;
                        case STATUS:
                         controller.setUserList(message);
                            break;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        //    controller.logoutScene();
        }
    }

    /* This method is used for sending a normal Message
     * @param msg - The message which the user generates
     */
    public static void send(String msg,Utilisateur reciver) throws IOException {
        Message createMessage = new Message();
        
        createMessage.setSender(client);
        createMessage.setReciver(reciver);
        createMessage.setType(MessageType.USER);
        createMessage.setStatus(Status.AWAY);
        createMessage.setMsg(msg);
        oos.writeObject(createMessage);
        oos.flush();
    }

    /* This method is used for sending a voice Message
 * @param msg - The message which the user generates
 */
    public static void sendVoiceMessage(byte[] audio,Utilisateur reciver) throws IOException {
        Message createMessage = new Message();
        createMessage.setSender(client);
        createMessage.setReciver(reciver);
        createMessage.setType(MessageType.VOICE);
        createMessage.setStatus(Status.AWAY);
        createMessage.setVoiceMsg(audio);
        oos.writeObject(createMessage);
        oos.flush();
    }

    /* This method is used for sending a normal Message
 * @param msg - The message which the user generates
 */
    public static void sendStatusUpdate(Status status) throws IOException {
        Message createMessage = new Message();
        createMessage.setSender(client);
        createMessage.setType(MessageType.STATUS);
        createMessage.setStatus(status);
        oos.writeObject(createMessage);
        oos.flush();
    }

    /* This method is used to send a connecting message */
    public static void connect() throws IOException {
        Message createMessage = new Message();       
        createMessage.setSender(client);
        createMessage.setType(CONNECTED);
        createMessage.setMsg(HASCONNECTED);
        oos.writeObject(createMessage);
    }
    public void stop() throws IOException
    {
        socket.close();
    }
    

}
