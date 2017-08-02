package client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * @Project_Name com.netty
 * @Package_Name client  client
 * @File_name ClientHandler.java client
 * @author Monu.C
 * @Created_date_time Jul 30, 2017 2:19:04 PM
 */
public class ClientHandler  extends ChannelInboundHandlerAdapter {
	
	private final ByteBuf firstMessage;
	
	public ClientHandler() {
		          firstMessage = Unpooled.buffer(Client.SIZE);
		           for (int i = 0; i < firstMessage.capacity(); i ++) {
		               firstMessage.writeByte((byte) i);
		           }
		       }
		   @Override
	       public void channelActive(ChannelHandlerContext ctx) {
			   
	           ctx.writeAndFlush(firstMessage.retain());
	           //ctx.writeAndFlush( Unpooled.copiedBuffer("Netty MAY rock!", CharsetUtil.UTF_8));
	          
	       }
	
	    
	        @Override
	        public void channelRead(ChannelHandlerContext ctx, Object msg) {
	        	 try {
					ByteBuf in = (ByteBuf) msg;
					System.out.println("client received: " + in.toString(CharsetUtil.UTF_8));
					ctx.writeAndFlush(in.retain());
					ctx.writeAndFlush(msg);
				} finally {
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
