package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * The class {@code Server}
 * includes
 * */

public class Server {

    private final int port;
    private Map<String, SocketChannel> clients = new HashMap<>();
    private static Logger fileLogger = LogManager.getRootLogger();
    private static Logger consoleLogger = Logger.getLogger("logfile");

    class ServerHandler extends ChannelInboundHandlerAdapter {

        /**
         * Reads message from client
         * @param channelHandlerContext class wrapper for client's socket
         * @param message String message
         */
        @Override
        public void channelRead(ChannelHandlerContext channelHandlerContext, Object message) {
            ByteBuf inBuffer = (ByteBuf) message;
            String received = inBuffer.toString(CharsetUtil.UTF_8);
            SocketChannel client = (SocketChannel) channelHandlerContext.channel();
            if(received.startsWith("username:")) {
                String name = received.replaceFirst("username:","");
                clients.put(name, client);
                broadcastSendList();
            }
            else {
                String[] contents = received.split(":");
                System.out.println(contents[0]);
                if(contents[1].equals("exit")) {
                    for(String name: clients.keySet()) {
                        if(clients.get(name).equals(client)) {
                            System.out.println("client disconnected");
                            clients.remove(name);
                            broadcastSendList();
                        }
                    }
                }
                else {
                if(clients.containsKey(contents[0])) {
                    clients.get(contents[0]).writeAndFlush(Unpooled.copiedBuffer(ByteBuffer.wrap(contents[1].getBytes())));
                }
                else {
                    System.out.println("cannot find user " + contents[0]);
                    client.writeAndFlush(Unpooled.copiedBuffer(ByteBuffer.wrap(("cannot find user " + contents[0]).getBytes())));
                }
                }
            }
            System.out.println(received);
            fileLogger.info(received);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Server(3030).run();
    }

    public Server(int port){
        this.port = port;
    }

    /**
     * the method run starts the server execution
     * */

    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new ServerHandler());
                }
            });
            consoleLogger.info("server started");
            fileLogger.info("server started");
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        }
        finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    /**
     * method send all the clients client list
     * */

    private void broadcastSendList() {
        StringBuilder list = new StringBuilder();
        int i = 0;
        for(String name: clients.keySet()) {
            list.append((i++) + " " + name + "\n");
        }
        for(String name: clients.keySet()) {
            clients.get(name).writeAndFlush(Unpooled.copiedBuffer(ByteBuffer.wrap(list.toString().getBytes())));
        }
    }

}
