package com.ahianzhang.http;

import com.ahianzhang.model.ActionCard;
import com.ahianzhang.model.DingTalkNotification;
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

import java.io.IOException;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 1/12/21 12:51 PM
 */
public class MergeRequestHandler extends SimpleChannelInboundHandler<HttpContent> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, HttpContent msg) throws Exception {
    ByteBuf echoMsg = msg.content();
    String jsonStr = new String(ByteBufUtil.getBytes(echoMsg));

    HttpUtil.post(concat(jsonStr));
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

  private String concat(String json) throws IOException {
    DingTalkNotification currentMergeRequest =
        new DingTalkNotification().getCurrentMergeRequest(json);
    String projectName = "项目名称：" + currentMergeRequest.getProjectName();
    String committer = "提交人：" + currentMergeRequest.getCommitter();
    String approvalMember = "审批人：" + currentMergeRequest.getApprovalMember();
    String content = "内容" + currentMergeRequest.getContent();
    String crUrl = "地址" + currentMergeRequest.getCrUrl();
    String toJson = new ActionCard()
            .appendRow(projectName)
            .appendRow(committer)
            .appendRow(approvalMember)
            .appendRow(content)
            .appendRow(crUrl)
            .singleURL(currentMergeRequest.getCrUrl()).toJson();

  return toJson;
  }
}
