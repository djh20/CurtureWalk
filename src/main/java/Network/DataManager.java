package Network;

import MetaData.NetworkMetaData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DataManager {
    private static DataManager dataManager = null;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois= null;
    private Socket socket;


    private DataManager() {
        try {
            socket = new Socket(NetworkMetaData.SERVER_URL, NetworkMetaData.SERVER_PORT);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DataManager getInstance(){
        if(dataManager == null)
            dataManager = new DataManager();
        return dataManager;
    }

    synchronized public Packet requestData(Packet packet){
        try {
            oos.writeObject(packet);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listenResponse();
    }

    public Packet listenResponse() {
        Packet receive = null;
        try {
            receive = (Packet) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receive;
    }
}
