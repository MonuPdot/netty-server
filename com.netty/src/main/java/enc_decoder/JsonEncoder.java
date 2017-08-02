package enc_decoder;

import Model.Person;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

/**
 * @Project_Name com.netty
 * @Package_Name enc_decoder  enc_decoder
 * @File_name JsonEncoder.java enc_decoder
 * @author Monu.C
 * @Created_date_time Aug 2, 2017 2:04:35 PM
 */
public class JsonEncoder extends MessageToByteEncoder<Person> {


	@Override
	protected void encode(ChannelHandlerContext ctx, Person msg, ByteBuf out) throws Exception {	
	
	    System.out.println("encoding: " +  msg.toByteArray(msg));
		out.writeBytes(msg.toByteArray(msg));
	}

}
