package source;

import io.netty.bootstrap.ServerBootstrap;
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
          // Configure SSL.
          final SslContext sslCtx;
          if (SSL) {
              SelfSignedCertificate ssc = new SelfSignedCertificate();
              sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
          } else {
              sslCtx = null;
          }
  
          // Configure the server.
          EventLoopGroup bossGroup = new NioEventLoopGroup(1);          
          EventLoopGroup workerGroup = new NioEventLoopGroup();
          try {
              ServerBootstrap b = new ServerBootstrap();
              b.group(bossGroup, workerGroup)
               .channel(NioServerSocketChannel.class)
              // .option(ChannelOption.SO_BACKLOG, 100)
               .handler(new LoggingHandler(LogLevel.INFO))
               .childHandler(new HttpKafkaInitializer());
              
              
              /*
               .childOption(ChannelOption.SO_KEEPALIVE, true)*/
        /*       .childHandler(new ChannelInitializer<SocketChannel>() {
                   @Override
                   public void initChannel(SocketChannel ch) throws Exception {
                	   System.out.println("New client connected: " + ch.localAddress());
                       ChannelPipeline p = ch.pipeline();
                       
                       
                       if (sslCtx != null) {
                           p.addLast(sslCtx.newHandler(ch.alloc()));// add with name
                       p.addLast("idleStateHandler",new IdleStateHandler(0,0,5)); // add with name                          
                       p.addLast(new JsonDecoder());
                       p.addLast(new JsonEncoder());
                       }
                       //p.addLast(new LoggingHandler(LogLevel.INFO));
                       p.addLast(new ServerHandler());
                   }
               });*/
  
              // Start the server.
             // ChannelFuture f = b.bind(PORT).sync();
              
              
      	      b.bind(PORT).sync();
              System.out.println("Server Started!");
              // Wait until the server socket is closed.
              //f.channel().closeFuture().sync();
          } 
          finally {
              // Shut down all event loops to terminate all threads.
             /* bossGroup.shutdownGracefully();
              workerGroup.shutdownGracefully();*/
          }
      }
}
