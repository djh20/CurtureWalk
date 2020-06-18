package Network;

import java.io.Serializable;

public class Packet<E> implements Serializable {

    private static final long serialVersionUID = -8369327040105447949L;
    private  int type;
    private E[] data;

    public Packet(int type, E[] data) {
        this.type = type;
        this.data = data;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public E[] getData() {
        return data;
    }

    public void setData(E[] data) {
        this.data = data;
    }
}
