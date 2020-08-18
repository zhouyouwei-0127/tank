package com.awei.netty.s01;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class client {
    public static void main(String[] args) {
        //事件处理的线程池
        EventLoopGroup group = new NioEventLoopGroup();

        //辅助启动类
        Bootstrap b = new Bootstrap();

        try {
            ChannelFuture f = b.group(group)
                    //通道类型
                    .channel(NioSocketChannel.class)
                    //表示当channel上有事件的时候交给谁去处理
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            //获取这个连接的管道
                            ChannelPipeline pl = sc.pipeline();
                            //在管道的末尾加上处理器
                            pl.addLast(new ClientHandler());
                        }
                    })
                    .connect("localhost", 8888);

            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture cf) throws Exception {
                    if (cf.isSuccess()) {
                        System.out.println("connected");
                    } else {
                        System.out.println("not connected");
                    }
                }
            });
            f.sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭线程池中的线程
            group.shutdownGracefully();
        }
    }
}

/**
 * 管道事件处理器
 */
class ClientHandler extends ChannelInboundHandlerAdapter {
    //在channel第一次连上的时候调用此方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //网络中写数据，是写给OS，jvm想要拿到这份数据，就要从OS中将数据copy到jvm的内存中
        //而ByteBuf可以直接访问操作系统的内存，所以它的效率非常高
        //在netty中，所有数据的读写都要使用ByteBuf，因为接收方法中用的就是ByteBuf

        //往server端写数据
        ByteBuf buf = Unpooled.copiedBuffer("hello".getBytes());
        //writeAndFlush会自动释放buf
        ctx.writeAndFlush(buf);
    }

    //在server向client写数据时会调用此方法
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.getBytes(buf.readerIndex(), bytes);
        System.out.println(new String(bytes));
    }
}
