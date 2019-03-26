package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private static Logger fileLogger = Logger.getLogger("logfile");
    private int globalIDs = 0;

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private Map<SocketChannel, Integer> clientIDs = new LinkedHashMap<>();
    private Map<SocketChannel, String> clientNicks = new LinkedHashMap<>();
    private static final int BUFFER_SIZE = 500;
    public static final int PORT = 3030;


    public Server() throws IOException {
        try {
            fileLogger.log(Level.CONFIG, "Server started");

            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            int ops = serverSocketChannel.validOps();
            serverSocketChannel.register(selector, ops, null);
            System.out.println("Server started.");
            while (true) {
                int num = selector.select();
                if (num == 0)
                    continue;
                Set keys = selector.selectedKeys();
                Iterator keysIterator = keys.iterator();
                while (keysIterator.hasNext()) {
                    SelectionKey key = (SelectionKey) keysIterator.next();
                    if (key.isAcceptable()) {
                        acceptNewClient(++globalIDs);
                    } else if (key.isReadable()) {
                        readMessage(key);
                    }
                    keysIterator.remove();
                }
                keys.clear();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * acceptNewClient add new client to the client list
     * */

    private void acceptNewClient(int clientID) {
        System.out.println("New client connected");
        SocketChannel client;
        try {
            client = serverSocketChannel.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            clientIDs.put(client, clientID);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            fileLogger.log(Level.CONFIG, e.getMessage());
        }
    }

    /**
     * readMessage defines the processing of messages got from client
     * */

    private void readMessage(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        try {
            client.read(buffer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String msg = new String(buffer.array()).trim();

        if (msg.length() > 11) {
            if (msg.substring(0, 11).equals("ClientName:")) {
                clientNicks.put(client, msg.substring(11));
                broadcastSendList();
                buffer.clear();
            }
        } if (msg.length() > 0) {
            if (msg.equals("exit")) {
                System.out.println("Client exited");
                clientIDs.remove(client);
                clientNicks.remove(client);
                broadcastSendList();
            } else {
                String contents[] = msg.split("@");
                if(contents.length > 1)
                    if(!sendToReceiver(contents[0], contents[1]))
                        send(client, "cannot find receiver");
                System.out.println(msg);
            }
            buffer.clear();
            System.out.println("cleared buff");
        }
    }

    private void send(SocketChannel socketChannel, String message) {
        try {
            socketChannel.write(ByteBuffer.wrap(message.getBytes()));
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    private boolean sendToReceiver(String index, String message) {
        try {
            int ind = Integer.parseInt(index);
            boolean sent = false;
            for(SocketChannel socketChannel: clientIDs.keySet()) {
                if(clientIDs.get(socketChannel) == ind) {
                    socketChannel.write(ByteBuffer.wrap(message.getBytes()));
                    sent = true;
                    break;
                }
            }
            return sent;
        } catch (IOException e) {
            System.out.println("error");
            return false;
        }
    }

    private void broadcastSendList() {
        StringBuilder list = new StringBuilder();
        for(SocketChannel socketChannel: clientIDs.keySet()) {
            list.append(clientIDs.get(socketChannel) + ") " + clientNicks.get(socketChannel) + "\n");
        }
        String clientList = list.toString();
        try {
            for (SocketChannel socketChannel : clientIDs.keySet()) {
                socketChannel.write(ByteBuffer.wrap(clientList.getBytes()));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) throws IOException {
        new Server();
    }

}
