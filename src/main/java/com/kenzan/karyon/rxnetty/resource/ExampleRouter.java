package com.kenzan.karyon.rxnetty.resource;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import netflix.karyon.transport.http.SimpleUriRouter;
import netflix.karyon.transport.http.health.HealthCheckEndpoint;
import rx.Observable;

import com.kenzan.karyon.rxnetty.health.HealthCheck;


public class ExampleRouter implements RequestHandler<ByteBuf, ByteBuf>{

    private final SimpleUriRouter<ByteBuf, ByteBuf> delegate;

    public ExampleRouter() {
        delegate = new SimpleUriRouter<>();
        HealthCheck healthCheckHandler = new HealthCheck();

        delegate
        .addUri("/hello/*", new HelloResource())
        .addUri("/healthcheck", new HealthCheckEndpoint(healthCheckHandler));
    }
    @Override
    public Observable<Void> handle(HttpServerRequest<ByteBuf> request,
            HttpServerResponse<ByteBuf> response) {
        return delegate.handle(request, response);
    }

}
