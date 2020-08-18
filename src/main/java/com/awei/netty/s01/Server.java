package com.awei.netty.s01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.io.IOException;

public class Server {

    // ChannelGroup表示一个通道组，里面装的是所有连接server的client通道
    // GlobalEventExecutor.INSTANCE表示指定一个默认的线程来处理通道组上的事件
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static void main(String[] args) throws IOException {
        //负责连接的线程池
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //负责连接之后的事件处理的线程池
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //辅助启动类
        ServerBootstrap sb = new ServerBootstrap();

        try {
            ChannelFuture f = sb.group(bossGroup, workerGroup)
                    //通道的类型
                    .channel(NioServerSocketChannel.class)
                    //handler表示对所有的链接以及整个插排的处理
                    /*.handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                        }
                    })*/
                    //childHandler表示具体连接的处理
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            //获取这个连接的管道
                            ChannelPipeline pl = sc.pipeline();
                            //在管道的末尾加上处理器
                            pl.addLast(new ServerChildHandler());
                        }
                    })
                    .bind("localhost", 8888)
                    .sync();

            System.out.println("server started");

            //f.channel()拿到了绑定在这个ChannelFuture上的channel
            //closeFuture()-->如果有人调了ChannelFuture的close方法，则返回一个closeFuture,
            //如果没有调用，则线程永远阻塞在这里，等待close方法被调用
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

/**
 * 管道事件处理器
 */
class ServerChildHandler extends ChannelInboundHandlerAdapter {

    //一个通道可以使用时就把这个通道加到通道组中
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Server.clients.add(ctx.channel());
    }

    //在client写往server端写数据的时候会调用此方法，将数据保存在msg中
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = null;
        try {
            buf = (ByteBuf) msg;
            //buf.readableBytes()表示buf中可读的字节长度
            byte[] bytes = new byte[buf.readableBytes()];
            //将buf中的字节读到bytes中，buf.readerIndex()为可读的起始位置
            buf.getBytes(buf.readerIndex(),bytes);
            System.out.println(new String(bytes));

            //writeAndFlush会自动释放buf，如果只是读，没有writeAndFlush操作，则需要下面finally中的代码手动释放buf
            //ctx.writeAndFlush(buf);
            //将通道组中的所有通道拿出来，一个个往外写数据
            Server.clients.writeAndFlush(buf);
        } finally {
            /*if (buf != null) {
                //释放buf的引用
                ReferenceCountUtil.release(buf);
                //buf的引用--有几个引用指向它，如果不是0，则表示有引用内有释放
                System.out.println(buf.refCnt());
            }*/
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //在通道抛出异常时关闭此通道，在调用此方法时，client中的f.channel().closeFuture().sync();将不再阻塞
        ctx.close();
    }
}
