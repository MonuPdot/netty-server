package enc_decoder;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

import Model.Person;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

/**
 * @Project_Name com.netty
 * @Package_Name enc_decoder  enc_decoder
 * @File_name JsonDecoder.java enc_decoder
 * @author Monu.C
 * @Created_date_time Aug 2, 2017 2:03:50 PM
 */
public class JsonDecoder extends ByteToMessageDecoder {
	
	
	private ByteArrayOutputStream baos = new ByteArrayOutputStream();
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		System.out.println("server received: " + in.toString(CharsetUtil.UTF_8));
	    if(in.isReadable()){

	        // wrIdx - rdIdx instead of .readableBytes()
	        byte[] frame = new byte[in.writerIndex()-in.readerIndex()];

	        // I read just what I get, so the signal is never thrown
	        in.readBytes(frame);

	        // collecting bytes
	        baos.write(frame);

	        // it'll achieve this only when all the bytes from
	        // the incoming object have been collected
	        try{
	            out.add(SerializationUtils.deserialize(baos.toByteArray()));
	        }
	        catch(Exception e){}
	    }
		


		/*final int messageLength = Long.SIZE/Byte.SIZE *2;
		    if (in.readableBytes() < messageLength) {
		      return;
		    }
		 
		     
		    byte [] ba = new byte[messageLength];
		    in.readBytes(ba, 0, messageLength);  // block until read 16 bytes from sockets
		    Person person = new Person();
		    person.fromByteArray(ba);
		    out.add(person);*/
	/*	
		
		 while(true){		 
			    byte [] ba = new byte[ in.capacity()];
				in.readBytes(ba, 0, ba.length);
				out.add((Person) SerializationUtils.deserialize(ba));
		 }
		 */
		 
		   
	}
/*
	 public  Person deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
	    	System.out.println("inside deserialize");
	        try(ByteArrayInputStream b = new ByteArrayInputStream(bytes)){
	            try(ObjectInputStream o = new ObjectInputStream(b)){
	                return (Person) o.readObject();
	            }
	        }
	    }*/
}
