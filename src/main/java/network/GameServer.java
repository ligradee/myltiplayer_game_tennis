package network;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameServer {

    private DatagramSocket serverSocket;
    private static InetAddress IPAddress;
    private static int port;

    private String message;
    private ArrayList<String> users;
    private ArrayList<Table> tables;
    private HashMap<String, Integer> timetable;
    private String player1;
    private String player2;


    public GameServer(String _port) {
        message = "";
        try {
            serverSocket = new DatagramSocket(Integer.parseInt(_port));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Connected");
        users = new ArrayList<String>();
        timetable = new HashMap<>();
        tables = new ArrayList<Table>();
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
                    if (!server.message.equals("")) {
                       // System.out.println(server.message);
                        if (server.message.indexOf(" ") != -1){
                            server.player1 = server.message.split(" ")[0];
                            System.out.println(server.player1);
                            String message = "@" + server.message.split(" ")[1];
                            System.out.println(message);
                            if (server.users.size() > 0) {
                                int eq = 0;
                                for (int i = 0; i < server.users.size(); i++) {
                                    if (server.users.get(i).contains(server.player1)) {
                                        eq = 1;
                                        break;
                                    }
                                }
                                if (eq == 0) {
                                    server.users.add(server.player1);
                                }
                            }
                        }



                    }

                    IPAddress = receivePacket.getAddress();
                    port = receivePacket.getPort();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {


                    while (!server.message.equals("@quit")) {
                        byte[] sendData = "T".getBytes();

                        if(server.message.contains("@getTables")) {
                            StringBuilder buf = new StringBuilder();
                            for (int i = 0; i < server.tables.size(); ++i)
                                buf.append("Table ").append(i).append("&");
                            sendData = buf.toString().getBytes();
                        }

                        if (server.message.indexOf("X") != -1){
                            sendData= server.message.getBytes();
                        }
                        if(server.message.contains("@new")){
                            if (!server.message.equals("")) {
                                String name = server.message.split("@")[0];
                                //System.out.println("New table added");
                                server.tables.add(new Table());
                                int num = (new Random()).nextInt(14);
                                server.timetable.put(name, num);
                            }

                        }
                        if(server.message.contains("@add")){
                            if (!server.message.equals("")) {
                                String name = server.message.split("@")[0];
                                server.message = "@" + server.message.split("@")[1];
                                int num = 0;
                                //System.out.println("Player added to table");
                                for (HashMap.Entry<String, Integer> item : server.timetable.entrySet()) {
                                    num = item.getValue();
                                }
                                server.timetable.put(name, num);
                            }

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