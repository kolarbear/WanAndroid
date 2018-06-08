package com.kolarbear.mylibrary.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import timber.log.Timber;

import static com.kolarbear.mylibrary.http.CharactorHandler.jsonFormat;


/**
 * 网络拦截器
 * Created by Administrator on 2018/2/5.
 */

public class RequestInterceptor implements okhttp3.Interceptor{
    private HttpGlobalHandler mHandler;


    public RequestInterceptor(HttpGlobalHandler mHandler)
    {
        this.mHandler = mHandler;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Buffer requestbuffer = new Buffer();
        if (request.body() != null) {
            request.body().writeTo(requestbuffer);
        } else {
            Timber.tag("Data").w("request.body() == null");
        }

        if (mHandler != null)//在请求服务器之前可以拿到request,做一些操作比如给request添加header,如果不做操作则返回参数中的request
            request = mHandler.beforeRequest(chain, request);
        //打印url信息
        Timber.tag("Request")
                .w("Sending Data %s on %n Params --->  %s%n Connection ---> %s%n Headers ---> %s", request.url(), request.body() != null ? requestbuffer.readUtf8() : "null", chain
                        .connection(), request.headers());

        long t1 = System.nanoTime();
        Response originalResponse = chain.proceed(request);
        long t2 = System.nanoTime();
        //打赢响应时间
        Timber.tag("Response").w("Received response  in %.1fms%n%s", (t2 - t1) / 1e6d, originalResponse.headers());

        //读取服务器返回的结果
        ResponseBody responseBody = originalResponse.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();

        //获取content的压缩类型
        String encoding = originalResponse.headers().get("Content-Encoding");

        Buffer clone = buffer.clone();
        String bodyString;

        //解析response content
        if (encoding != null && encoding.equalsIgnoreCase("gzip")) {//content使用gzip压缩
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            clone.writeTo(outputStream);
            byte[] bytes = outputStream.toByteArray();
            bodyString = ZipHelper.decompressForGzip(bytes);//解压
            outputStream.close();
        } else if (encoding != null && encoding.equalsIgnoreCase("zlib")) {//content使用zlib压缩
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            clone.writeTo(outputStream);
            byte[] bytes = outputStream.toByteArray();
            bodyString = ZipHelper.decompressToStringForZlib(bytes);//解压
            outputStream.close();
        } else {//content没有被压缩
            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            bodyString = clone.readString(charset);
        }


        Timber.tag("Result").w(jsonFormat(bodyString));
        // 如果images返回的为""，则替换为[]
//        if(bodyString != null && bodyString.contains("\"images\":\"\"")){
//            bodyString = bodyString.replaceAll("\"images\":\"\"", "\"images\":[]");
//            Log.i("去引号后 ", bodyString);
//            originalResponse = originalResponse.newBuilder()
//                    .body(ResponseBody.create(originalResponse.body().contentType(), bodyString))
//                    .header("Content-Encoding", "UTF-8")
//                    .build();
//        }
        if (mHandler != null)//这里可以比客户端提前一步拿到服务器返回的结果,可以做一些操作,比如token超时,重新获取
            return mHandler.beforeResponse(bodyString, chain, originalResponse);

        return originalResponse;
    }
}
