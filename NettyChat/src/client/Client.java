package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * The class {@code Client} defines client
 * that connects to the server
 * */

public class Client {

    public static void main(String[] args) throws Exception{
        new Client("localhost", 3030).run();
    }

    private final String host;
    private final int port;
    private volatile boolean work = true;
    /**
     * scanner reading from console
     * */
    private Scanner reader = new Scanner(System.in);

    /**
     * current channel to connect to the server
     * */

    private Channel channel;


    public Client(String host, int port){
        this.host = host;
        this.port = port;
    }


    /**
     * class {@code Writer} that writes
     * messages from console
     * */

    public class Writer extends Thread {
        ChannelHandlerContext ctx;

        Writer(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }
        /**
         * method run where writer starts execution
         * */
        @Override
        public void run() {
            try {
                introduce(ctx);
                while(true) {
                    try {
                        System.out.println("Enter receiver name");
                        String receiver = reader.nextLine();
                        if (reader.hasNext()) {
                            String message = reader.nextLine();
                            if(message.equals("exit")) {
                                work = false;
                                System.out.println("break");
                                break;
                            }
                            else {
                                ctx.fireUserEventTriggered(receiver + ":" + message);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }


    public class ConsoleHandler extends ChannelInboundHandlerAdapter {
        /**
         * Sends client message to server
         * @param ctx class wrapper for client's socket
         * @param evt message
         */
        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(((String)evt).getBytes());
            buffer.flip();
            channel.writeAndFlush(Unpooled.copiedBuffer(buffer));
        }
    }

    public class ClientHandler extends ChannelInboundHandlerAdapter {
        /**
         * Reads message from client
         * @param channelHandlerContext class wrapper for client's socket
         * @param msg message
         */
        @Override
        public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) {
            String message = ((ByteBuf) msg).toString(CharsetUtil.UTF_8);
            System.out.println(message);
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.fireChannelActive();
            new Writer(ctx).start();
        }

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            System.out.println(evt);
        }
    }


    /**
     * method that runs the client
     * */

    public void run() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap  = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new ClientHandler());
                    socketChannel.pipeline().addLast(new ConsoleHandler());
                }
            });
            channel = bootstrap.connect(host, port).sync().channel();
            while (true) {
                if(!work) {
                    System.out.println("break");
                    break;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }

    /**
     * method for client to enter his name
     * */

    private void introduce(ChannelHandlerContext ctx) {
        System.out.println("Enter your name");
        String name = reader.nextLine();
        ctx.fireUserEventTriggered("username:" + name);
    }

}