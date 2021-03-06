package source;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

/**
 * @Project_Name com.netty
 * @Package_Name source  source
 * @File_name Server.java source
 * @author Monu.C
 * @Created_date_time Jul 30, 2017 2:29:21 PM
 */
public class Server {
    static final boolean SSL = System.getProperty("ssl") != null;
      static final int PORT = Integer.parseInt(System.getProperty("port", "8008"));
  
      public static void main(String[] args) throws Exception {
         
          // Configure the server.
          EventLoopGroup bossGroup = new NioEventLoopGroup(1);          
          EventLoopGroup workerGroup = new NioEventLoopGroup();
          try {
              ServerBootstrap b = new ServerBootstrap();
              b.group(bossGroup, workerGroup)
               .channel(NioServerSocketChannel.class)
               .handler(new LoggingHandler(LogLevel.INFO))
               .childHandler(new HttpKafkaInitializer());
             
              
              ChannelFuture f=b.bind(PORT).sync();
              System.out.println("Server Started!");
              // Wait until the server socket is closed.
              f.channel().closeFuture().sync();
          } 
          finally {
              // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
              workerGroup.shutdownGracefully();
          }
      }
}
