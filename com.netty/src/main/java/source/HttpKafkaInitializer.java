package source;

import enc_decoder.JsonDecoder;
import enc_decoder.JsonEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @Project_Name com.netty
 * @Package_Name kafka  kafka
 * @File_name HttpKafkaInitializer.java kafka
 * @author Monu.C
 * @Created_date_time Aug 2, 2017 6:49:05 PM
 */
public class HttpKafkaInitializer extends ChannelInitializer<SocketChannel>{

  

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
     System.out.println("New client connected: " + ch.localAddress());
          
    	ChannelPipeline p = ch.pipeline();
        p.addLast(new JsonDecoder());
        p.addLast(new JsonEncoder());
        p.addLast(new HttpKafkaServerHandler());
}
}
