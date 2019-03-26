package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Class {@code Server} starts the server
 * that redirects user messages
 */

public class Client {


    SocketChannel clientSocketChannel;
    Scanner reader;
    public static final Logger logger = LogManager.getLogManager().getLogger("Client");

    private static final int BUFFER_SIZE = 500;
    public static int port = 3030;
    private static boolean working = true;

    public Client() {
        try {
            clientSocketChannel = SocketChannel.open(new InetSocketAddress(InetAddress.getLocalHost(), port));
            clientSocketChannel.configureBlocking(false);
            reader = new Scanner(System.in);
            new Writer().start();
            while (working) {
                ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                clientSocketChannel.read(buffer);
                String msg = new String(buffer.array()).trim();
                buffer.clear();
                if (msg.length() > 0) {
                    System.out.println(msg);
                }
            }
        } catch (IOException e) {

            System.out.println(e.getMessage());
        } finally {
            shutdown();
        }
    }

    public static void main(String[] args) {
        new Client();
    }


    private void shutdown() {
        try {
            working = false;
            clientSocketChannel.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public class Writer extends Thread {
        @Override
        public void run() {
            try {
                ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                System.out.println("Enter your nickname:");
                String nickname = "ClientName:" + reader.nextLine();
                buffer.put(nickname.getBytes());
                buffer.flip();
                clientSocketChannel.write(buffer);
                while (true) {
                    System.out.println("Enter receiver index(or 0 to exit)");
                    if (reader.hasNextInt()) {
                        buffer = ByteBuffer.allocate(BUFFER_SIZE);
                        int receiver = reader.nextInt();
                        if(receiver == 0) {
                            shutdown();
                            break;
                        }
                        System.out.println("Enter message:");
                        reader.nextLine();
                        String msg = reader.nextLine();
                        buffer.put((receiver + "@" + msg).getBytes());
                        buffer.flip();
                        clientSocketChannel.write(buffer);
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}