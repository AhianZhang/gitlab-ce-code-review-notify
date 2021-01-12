package com.ahianzhang.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import java.util.Map;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 1/12/21 12:51 PM
 */
public class MergeRequestHandler extends SimpleChannelInboundHandler<HttpContent> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, HttpContent msg) throws Exception {
    System.out.println(msg);
    ByteBuf echoMsg = msg.content();
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonStr = new String(ByteBufUtil.getBytes(echoMsg));

    Map<String, Object> map =
        objectMapper.readValue(jsonStr, new TypeReference<Map<String, Object>>() {});

    System.out.println(map.toString());
    System.out.println(new String(ByteBufUtil.getBytes(echoMsg)));

    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(
            HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.copiedBuffer(echoMsg));
    response.headers().set("Content-Type", "text/plain");
    ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}
