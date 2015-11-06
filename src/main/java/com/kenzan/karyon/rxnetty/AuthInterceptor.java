package com.kenzan.karyon.rxnetty;

import rx.Observable;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import netflix.karyon.transport.interceptor.InboundInterceptor;

public class AuthInterceptor implements InboundInterceptor<HttpServerRequest<ByteBuf>, HttpServerResponse<ByteBuf>>{

    @Override
    public Observable<Void> in(HttpServerRequest<ByteBuf> request,
            HttpServerResponse<ByteBuf> response) {

        if(request.getHeaders().contains("X-Session")) {
            return Observable.empty();
        }

        response.setStatus(HttpResponseStatus.UNAUTHORIZED);
        response.writeString("Unauthorized");

        return response.close();
    }

}
