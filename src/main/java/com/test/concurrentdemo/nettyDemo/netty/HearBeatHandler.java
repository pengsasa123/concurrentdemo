package com.test.concurrentdemo.nettyDemo.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HearBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                log.info("读空闲事件触发····");
                log.info("channel长Id为：[{}]的用户进入读空闲", ctx.channel().id().asLongText());
            } else if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
                log.info("写空闲事件触发····");
                log.info("channel长Id为：[{}]的用户进入写空闲", ctx.channel().id().asLongText());
            } else if (idleStateEvent.state() == IdleState.ALL_IDLE) {
                log.info("读写空闲事假触发,关闭通道资源");
                log.info("channel 关闭之前 users 的数量为: : {}", ChannelSupervise.GlobalGroup.size());
                // 释放资源
                ctx.channel().close();
                log.info("channel 关闭之后 users 的数量为: {}", ChannelSupervise.GlobalGroup.size());
            }
        }
    }
}
