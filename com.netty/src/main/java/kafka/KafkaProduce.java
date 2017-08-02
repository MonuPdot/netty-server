package kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import io.netty.buffer.ByteBuf;

/**
 * @Project_Name com.netty
 * @Package_Name kafka  kafka
 * @File_name KafkaProducer.java kafka
 * @author Monu.C
 * @Created_date_time Jul 30, 2017 7:31:09 PM
 */
public class KafkaProduce {
	
	

    public void produceKafka(ByteBuf json){
    	
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:8008");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
  
    	   Producer<String, String> producer = null;
    	    
    	    
    	    try {
    	      producer = new KafkaProducer<>(props);
    	      producer.send(new ProducerRecord<String, String>("JsonTopicName",String.valueOf( json)));
    	    /*  for (int i = 0; i < 100; i++) {
    	        String msg = "Message " + i;
    	        producer.send(new ProducerRecord<String, String>("HelloKafkaTopic", msg));
    	        System.out.println("Sent:" + msg);
    	      }*/
    	    } catch (Exception e) {
    	      e.printStackTrace();

    	    } finally {
    	      producer.close();
    	    }
    }
 
}
