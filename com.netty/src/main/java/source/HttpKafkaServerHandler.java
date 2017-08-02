package source;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import Model.Person;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;


/**
 * @Project_Name com.netty
 * @Package_Name kafka  kafka
 * @File_name HttpKafkaServerHandler.java kafka
 * @author Monu.C
 * @Created_date_time Aug 2, 2017 6:47:17 PM
 */
@Sharable
public class HttpKafkaServerHandler extends ChannelInboundHandlerAdapter {
	
	 private Producer<String, String> kafkaProducer;
	 
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws JsonParseException, JsonMappingException, IOException {
	  
	    Person person=(Person)msg;
	    System.out.println("Server received: " + person.getName());
	    System.out.println("Server received: " +person.getEmail());
	    System.out.println("Server received: " + person.getPlace());
	    
		Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:8008");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
  
        kafkaProducer = new KafkaProducer<String, String>(props);
        kafkaProducer.send(new ProducerRecord<String, String>("PersonName",person.getName()));

        
	/*    if (msg instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) msg;

            String uri = httpRequest.getUri();
            
        
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
        }*/
	}
	   @Override
	      public void channelReadComplete(ChannelHandlerContext ctx) {
	          ctx.flush();
	      }
	  
	      @Override
	      public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	          // Close the connection when an exception is raised.
	          cause.printStackTrace();
	          ctx.close();
	      }
}
