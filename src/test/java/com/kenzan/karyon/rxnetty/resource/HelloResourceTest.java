package com.kenzan.karyon.rxnetty.resource;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.kenzan.karyon.rxnetty.resource.HelloResource;

@RunWith(MockitoJUnitRunner.class)
public class HelloResourceTest {

    @Mock
    private HttpServerRequest<ByteBuf> request;

    @Mock
    private HttpServerResponse<ByteBuf> response;

    @Mock
    private Attribute<Object> attribute;

    @Mock
    private Channel channel;

    @Before
    public void setup() {
        Mockito.when(channel.attr(Matchers.any())).thenReturn(attribute);
        Mockito.when(response.getChannel()).thenReturn(channel);
    }

    @Test
    public void helloTest() {
        HelloResource helloResource = new HelloResource();

        Mockito.when(request.getUri()).thenReturn("/hello");

        helloResource.handle(request, response);
    }

    @Test
    public void helloNameTest() {
        HelloResource helloResource = new HelloResource();

        Mockito.when(request.getUri()).thenReturn("/hello/name");

        helloResource.handle(request, response);
    }
}
