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

	        byte[] frame = new byte[in.writerIndex()-in.readerIndex()];

	        in.readBytes(frame);

	        baos.write(frame);

	        try{
	            out.add(SerializationUtils.deserialize(baos.toByteArray()));
	        }
	        catch(Exception e){}
	    }
	
	}
}
