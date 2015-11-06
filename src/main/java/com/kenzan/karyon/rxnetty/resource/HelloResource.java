package com.kenzan.karyon.rxnetty.resource;

import java.util.regex.Pattern;

import netflix.karyon.transport.http.SimpleUriRouter;

import com.sun.jersey.api.uri.UriPattern;

import rx.Observable;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;

public class HelloResource implements RequestHandler<ByteBuf, ByteBuf>{

    private final SimpleUriRouter<ByteBuf, ByteBuf> delegate;

    public HelloResource() {
        delegate = new SimpleUriRouter<>();

        delegate
        .addUri("/hello", (request, response) -> {
            response.writeString("Hello");
            return response.close();
        })
        .addUriRegex("/hello/(.*)", (request, response) -> {

            UriPattern pattern = new UriPattern(Pattern.compile("/hello/(.*)"));
            String name = pattern.match(request.getUri()).group(1);
            response.writeString("Hello " + name);

            return response.close();
        });
    }
    @Override
    public Observable<Void> handle(HttpServerRequest<ByteBuf> request,
            HttpServerResponse<ByteBuf> response) {
        return delegate.handle(request, response);
    }

}
