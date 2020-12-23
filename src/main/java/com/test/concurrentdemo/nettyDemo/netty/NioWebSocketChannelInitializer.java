package com.test.concurrentdemo.nettyDemo.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class NioWebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        // 获取管道，将一个一个的ChannelHandler添加到管道中
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 添加一个Http的编解码器,用于支持Http协议
        pipeline.addLast(new HttpServerCodec());
        // 添加一个用于支持大数据流的处理器(在htpp上有一些数据流产生，netty对数据流写提供支持)
        pipeline.addLast(new ChunkedWriteHandler());
        // 添加一个聚合器，这个聚合器只要是将HttpMessage聚合成FullHttpMessageRequest/FullHttpMessageResponse
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));

        // 增加心跳支持
        // arg0：单位为：秒，读空闲超时，(超过一定的时间会发送对应的时间消息)
        // arg1：单位为：秒，写空闲超时
        // arg2：单位为：秒，读写空闲超时
        pipeline.addLast(new IdleStateHandler(40,60,120));

        // ---------------支持 WebSocket---------------
        // 本handler会帮你处理一些握手动作：handshaking (close,ping,pong)ping+pong = 心跳
        // 对于WebSocket来讲，都是以frames进行传输的，不同的数据类型对应的frames也不同
        // 需要指定接收请求的路由：必须使用wx后缀结尾的url才能访问
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        // 自定义空闲状态检测handler
        pipeline.addLast(new HearBeatHandler());

        // 自定义业务
        pipeline.addLast(new NioWebSocketHandler());
    }
}
