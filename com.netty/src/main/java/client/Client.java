package client;

import Model.Person;
import enc_decoder.JsonDecoder;
import enc_decoder.JsonEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

/**
 * @Project_Name com.netty
 * @Package_Name client  client
 * @File_name client.java client
 * @author Monu.C
 * @Created_date_time Jul 30, 2017 2:16:41 PM
 */
public final class Client {
   
      static final String HOST = System.getProperty("host", "127.0.0.1");
      static final int PORT = Integer.parseInt(System.getProperty("port", "8008"));
  
      public static void main(String[] args) throws Exception {
  
          // Configure the client.
          EventLoopGroup group = new NioEventLoopGroup();
          
          try {
              Bootstrap b = new Bootstrap();
              b.group(group);
              
              b.channel(NioSocketChannel.class);

              b.handler(new ChannelInitializer<SocketChannel>() {
                   @Override
                   public void initChannel(SocketChannel ch) throws Exception {
                      ChannelPipeline p = ch.pipeline();
                      
                           p.addLast(new JsonDecoder());
                           p.addLast(new JsonEncoder());
                       p.addLast(new ClientHandler());
                   }
               });
  
              
              b.option(ChannelOption.SO_KEEPALIVE, true);
             
              // Start the client.
              ChannelFuture f = b.connect(HOST, PORT).sync();
  
               //Wait until the connection is closed.
              f.channel().closeFuture().sync();
          } finally {
              // Shut down the event loop to terminate all threads.
              group.shutdownGracefully();
          }
      }
 }

