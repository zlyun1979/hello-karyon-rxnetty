package com.kenzan.karyon.rxnetty.endpoint;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;

import java.util.regex.Pattern;

import com.sun.jersey.api.uri.UriPattern;

import rx.Observable;

public class HelloEndpoint {

    public Observable<String> getHello() {
        return Observable.just("Hello");
    }

    public Observable<String> getHelloName(HttpServerRequest<ByteBuf> request) {
        UriPattern pattern = new UriPattern(Pattern.compile("/hello/(.*)"));
        String name = pattern.match(request.getUri()).group(1);

        return Observable.just("Hello " + name);
    }
}
