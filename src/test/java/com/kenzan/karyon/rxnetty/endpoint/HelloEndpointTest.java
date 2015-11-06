package com.kenzan.karyon.rxnetty.endpoint;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;

@RunWith(MockitoJUnitRunner.class)
public class HelloEndpointTest {

    @Mock
    private HttpServerRequest<ByteBuf> request;

    @Test
    public void helloTest() {
        HelloEndpoint helloEndpoint = new HelloEndpoint();

        Observable<String> hello = helloEndpoint.getHello();

        Assert.assertEquals("Hello", hello.toBlocking().first());
    }

    @Test
    public void helloNameTest() {
        HelloEndpoint helloEndpoint = new HelloEndpoint();

        Mockito.when(request.getUri()).thenReturn("/hello/name");

        Observable<String> hello = helloEndpoint.getHelloName(request);

        Assert.assertEquals("Hello name", hello.toBlocking().first());
    }
}
