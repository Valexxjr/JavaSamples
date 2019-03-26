package handlers;

import server.Server;

import java.io.*;
import java.net.*;


class ServerHandler extends Thread {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private String userName = null;

    public ServerHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        try {
            userName = in.readLine();
            while (true) {
                listUsers();
                String message = in.readLine();
                String[] contents = message.split(":");
                if (contents[1].equals("exit")) {
                    shutdown();
                    break;
                } else {
                    boolean found = false;
                    /*for (ServerHandler user : null) {
                        if (user.userName.equals(contents[0])) {
                            user.send(contents[1]);
                            found = true;
                        }
                    }*/
                    if (!found) {
                        send("Receiver not found: " + contents[0]);
                    }
                }
            }

        } catch (Exception e) {
            shutdown();
        }
    }

    private void send(String msg) throws IOException{
        out.write(msg + "\n");
        out.flush();
    }

    private void listUsers() throws IOException {
        StringBuilder userList = new StringBuilder();
        /*for (int i = 0; i < Server.serverList.size(); i++) {
            userList.append(i + ") " + Server.serverList.get(i).userName + " ");
        }*/
        send("List of available users: " + userList.toString());
    }

    private void shutdown() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                /*for (ServerHandler vr : Server.serverList) {
                    if (vr.equals(this))
                        vr.interrupt();
                    Server.serverList.remove(this);
                }*/
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}