package com.kenzan.karyon.rxnetty;

import netflix.karyon.transport.http.HttpInterceptorSupport;
import netflix.karyon.transport.http.HttpRequestHandler;
import netflix.karyon.transport.http.HttpRequestHandlerBuilder;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import rx.Observable;

public class ExampleRouteInterceptor implements RequestHandler<ByteBuf, ByteBuf>{

    private final HttpRequestHandler<ByteBuf, ByteBuf> delegate;

    public ExampleRouteInterceptor() {
        ExampleRouter router = new ExampleRouter();
        HttpInterceptorSupport<ByteBuf, ByteBuf> interceptorSupport = new HttpInterceptorSupport<ByteBuf, ByteBuf>();

        interceptorSupport.forUri("/auth/*").intercept(new AuthInterceptor());

        delegate = new HttpRequestHandler<ByteBuf, ByteBuf>(router, interceptorSupport);
    }

    @Override
    public Observable<Void> handle(HttpServerRequest<ByteBuf> request,
            HttpServerResponse<ByteBuf> response) {
        return delegate.handle(request, response);
    }

}
