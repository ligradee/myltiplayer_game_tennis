package network;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class GameServer {

    private DatagramSocket serverSocket;
    private static InetAddress IPAddress;
    private static int port;

    private String message;
    private ArrayList<String> users;
    private ArrayList<Table> tables;


    public GameServer(String _port) {
        message = "";
        try {
            serverSocket = new DatagramSocket(Integer.parseInt(_port));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Connected!");
        tables = new ArrayList<Table>();
        tables.add(new Table());
        tables.add(new Table());

    }

    public static void main(String[] args) throws Exception {
        final GameServer server = new GameServer(args[0]);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                    try {
                        server.serverSocket.receive(receivePacket);
                    } catch (Exception e) {
                        break;
                    }

                    server.message = new String(receivePacket.getData());

                    if(!server.message.contains("@"))
                        System.out.println(server.message);

                    IPAddress = receivePacket.getAddress();
                    port = receivePacket.getPort();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (!server.message.equals("@quit")) {
                    byte[] sendData = "Test".getBytes();
                    if(server.message.contains("@getTables")) {
                        StringBuilder buf = new StringBuilder();
                        for (int i = 0; i < server.tables.size(); ++i)
                            buf.append("Table ").append(i).append("&");
                        sendData = buf.toString().getBytes();
                    }

                    if(server.message.contains("@new")){
                        System.out.println("New table added");
                        server.tables.add(new Table());
                        sendData = "newTable".getBytes();
                        server.message = "";
                    }
                    if(server.message.contains("@add")){
                        System.out.println("Player added to table");

                    }

                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    try {
                        server.serverSocket.send(sendPacket);
                    } catch (Exception e) {
                    }
                }
                server.serverSocket.close();
            }
        }.start();
    }
}