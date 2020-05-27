package Network;

import MetaData.NetworkMetaData;

import java.io.IOException;
import java.net.Socket;

public class DataManager {
    private static DataManager dataManager = null;
    private Socket socket;
    private DataManager() {
        try {
            socket = new Socket(NetworkMetaData.SERVER_URL, NetworkMetaData.SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DataManager getInstance(){
        if(dataManager == null)
            dataManager = new DataManager();
        return dataManager;
    }

    public void sendData(Object object){

    }

    public void receiveData(){

    }

}
