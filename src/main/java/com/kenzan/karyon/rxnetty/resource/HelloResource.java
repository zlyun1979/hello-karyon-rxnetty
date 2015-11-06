/**
 * Copyright 2015 Kenzan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
