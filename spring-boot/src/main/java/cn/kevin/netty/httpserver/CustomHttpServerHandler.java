package cn.kevin.netty.httpserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.util.CharsetUtil;
import org.eclipse.jetty.http.HttpContent;
import org.springframework.http.HttpRequest;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yongkang.zhang
 * date 2022/5/25
 */
public class CustomHttpServerHandler extends SimpleChannelInboundHandler {

    private HttpRequest request;
    private StringBuilder responseData = new StringBuilder();


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        /*if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;

            // 判断如果是100 继续请求，那么就写返回值
            if (HttpUtil.is100ContinueExpected(request)) {
                writeResponse(channelHandlerContext);
            }
            responseData.setLength(0);
            responseData.append(formatParams(request));

        }
        responseData.append(evluateDecoderResult(request));

        if (msg instanceof io.netty.handler.codec.http.HttpContent) {
            HttpContent httpContent = (HttpContent) msg;
            responseData.append(formatBody(httpContent));
            responseData.append(evaluateDecoderResult(request));

            if (msg instanceof LastHttpContent) {
                LastHttpContent trailer = (LastHttpContent) msg;
                responseData.append(prepareLastResponse(request, trailer));
                writeResponse(ctx, trailer, responseData);
            }
        }*/
    }

    private void writeResponse(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE,
                Unpooled.EMPTY_BUFFER);
        ctx.write(response);
    }

    StringBuilder formatParams(HttpRequest request) {
        StringBuilder responseData = new StringBuilder();
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.getURI());
        Map<String, List<String>> parameters = queryStringDecoder.parameters();
        if (!parameters.isEmpty()) {
            Set<Map.Entry<String, List<String>>> entries = parameters.entrySet();
            for (Map.Entry<String, List<String>> p : parameters.entrySet()) {
                String key = p.getKey();
                List<String> values = p.getValue();
                for (String value : values) {
                    responseData.append("Parameter: ").append(key.toUpperCase()).append(" = ")
                            .append(value.toUpperCase()).append("\r\n");
                }
            }
            responseData.append("\r\n");
        }
        return responseData;
    }

    StringBuilder formatBody(HttpContent httpContext) {
        /*StringBuilder builder = new StringBuilder();
        ByteBuf content = httpContext.content();
        if (content.isReadable()) {
            responseData.append(content.toString(CharsetUtil.UTF_8).toUpperCase())
                    .append("\r\n");
        }
        return responseData;*/
        return null;
    }

    StringBuilder prepareLastResponse(HttpRequest request, LastHttpContent trailer) {
        StringBuilder responseData = new StringBuilder();
        responseData.append("Good Bye!\r\n");
        if (!trailer.trailingHeaders().isEmpty()) {
            responseData.append("\r\n");
            for (CharSequence name : trailer.trailingHeaders().names()) {
                for (CharSequence value : trailer.trailingHeaders().getAll(name)) {
                    responseData.append("P.S. Trailing Header: ");
                    responseData.append(name).append(" = ").append(value).append("\r\n");
                }
            }
            responseData.append("\r\n");
        }

        return responseData;
    }

    private void writeResponse(ChannelHandlerContext ctx, LastHttpContent trailer,
                               StringBuilder responseData) {
        /*boolean keepAlive = HttpUtil.isKeepAlive(request);
        FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                trailer.decoderResult().isSuccess() ? HttpResponseStatus.OK : HttpResponseStatus.BAD_REQUEST,
                Unpooled.copiedBuffer(responseData.toString(), CharsetUtil.UTF_8));
        httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
        if (keepAlive) {
            httpResponse.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, httpResponse.content().readableBytes());
            httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

        ctx.write(responseData);
        if (!keepAlive) {
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }*/
    }

    static StringBuilder evaluateDecoderResult(HttpObject o) {
        StringBuilder responseData = new StringBuilder();
        DecoderResult result = o.decoderResult();

        if (!result.isSuccess()) {
            responseData.append("..Decoder Failure: ");
            responseData.append(result.cause());
            responseData.append("\r\n");
        }

        return responseData;
    }
}
