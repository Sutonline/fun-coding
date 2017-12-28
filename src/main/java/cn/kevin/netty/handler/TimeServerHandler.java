package cn.kevin.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 时间服务器
 * created by yongkang.zhang
 * added at 2017/12/27
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * channelActivity() 方法将会在连接建立并且准备通信时调用
     * @param ctx ctx
     * @throws Exception 异常
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 缓冲
        ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        final ChannelFuture f = ctx.writeAndFlush(time);
        // write和writeAndFlush都会返回一个ChannelFuture对象，并不会立即执行。为了保证
        // 先写再关闭，使用listener进行关闭
        f.addListener(future -> {
           assert f == future;
           ctx.close();
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
