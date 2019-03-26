package handlers;

import java.io.*;
import java.net.Socket;

/**
 * Class {@code ClientHandler} includes different
 * methods for connecting with client
 * */

class ClientHandler {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader consoleInput;
    private String address;
    private int port;
    private String userName;

    public ClientHandler(String address, int port) {
        this.address = address;
        this.port = port;
        try {
            this.socket = new Socket(address, port);
        } catch (IOException e) {
            System.err.println("Socket creation failed");
        }
        try {
            consoleInput = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            enterName();
            new Reader().start();
            new Writer().start();
        } catch (IOException e) {
            shutdown();
        }
    }

    private void enterName() throws IOException {
        System.out.print("Enter your name: ");
        userName = consoleInput.readLine();
        out.write(userName + "\n");
        out.flush();
    }

    private void shutdown() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private class Reader extends Thread {
        @Override
        public void run() {
            String str;
            try {
                while (true) {
                    str = in.readLine();
                    System.out.println(str);
                }
            } catch (IOException e) {
                shutdown();
            }
        }
    }

    public class Writer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("Enter receiver name:");
                    String receiverName = consoleInput.readLine();
                    System.out.println("Enter message:");
                    String message = consoleInput.readLine();
                    if (message.equalsIgnoreCase("exit")) {
                        out.write("exit" + "\n");
                        shutdown();
                        break;
                    } else {
                        out.write(receiverName + ":" + message + "\n");
                    }
                    out.flush();
                } catch (IOException e) {
                    shutdown();
                }
            }
        }
    }
}
