package network;
import network.messages.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;

public class AbstractSocket implements Runnable {

    protected static final String ip = "127.0.0.1";
    protected static final int port = 3333;
    protected Socket socket = null;

    protected BufferedWriter outputStream;
    protected BufferedReader inputStream;
    private String buffer;
    protected Thread thread;

    protected Vector<NetworkListener> listeners;

    public AbstractSocket() throws IOException {
        if (socket == null) {
            //createSocket();
        }

        assert (socket != null);
        inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        assert (inputStream != null);
        assert (outputStream != null);

        listeners = new Vector<NetworkListener>();

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String line = inputStream.readLine();
                AbstractMessage message = MessageFactory.getInstance().deserialize(line);

                if (message != null) {
                    notifyNewMessage(message);
                }
            } catch (IOException ioe) {
                System.out
                        .println("Error while reading the socket input stream at ip="
                                + ip + " and port=" + port);
            }
        }
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }

    public String getBuffer() {
        return buffer;
    }

    protected void createSocket() throws IOException {
        socket = new Socket(ip, port);
    }

    public void send(AbstractMessage message) throws IOException {
        String line = MessageFactory.getInstance().serialize(message) + "\n";
        outputStream.write(line);
        outputStream.flush();
    }

    public void removeListener(NetworkListener listener) {
        listeners.remove(listener);
    }

    public void addListener(NetworkListener listener) {
        if (!listeners.contains(listener))
            listeners.add(listener);
    }

    protected void notifyNewMessage(AbstractMessage message) {
        Iterator<NetworkListener> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().notifyMessageArrived(this, message);
        }
    }


}

