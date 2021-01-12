package com.ahianzhang;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 1/12/21 12:51 PM
 **/
public class MergeRequestHandler extends SimpleChannelInboundHandler<HttpContent> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpContent msg) throws Exception {
    System.out.println(msg);
        ByteBuf echoMsg = msg.content();
        System.out.println(new String(ByteBufUtil.getBytes(echoMsg)));
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.copiedBuffer(echoMsg));
        response.headers().set("Content-Type", "text/plain");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
