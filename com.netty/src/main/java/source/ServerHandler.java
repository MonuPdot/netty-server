package source;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import kafka.KafkaProduce;

/**
 * @Project_Name com.netty
 * @Package_Name source  source
 * @File_name ServerHandler.java source
 * @author Monu.C
 * @Created_date_time Jul 30, 2017 2:30:34 PM
 */


@Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	      public void channelRead(ChannelHandlerContext ctx, Object msg) {
	          try {
				ByteBuf in = (ByteBuf) msg;
				System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
				ctx.write(in.retain());
				KafkaProduce kafkaProduce=new KafkaProduce(); 
				kafkaProduce.produceKafka(in);
				in.release();
			} finally {
				((ByteBuf)msg).release();
		        ReferenceCountUtil.release(msg);
			}
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
