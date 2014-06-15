package com.netflix.ribbonclientextensions.http;

import io.reactivex.netty.protocol.http.client.HttpClient;
import io.reactivex.netty.protocol.http.client.HttpClientRequest;

import com.netflix.ribbonclientextensions.RequestTemplate.RequestBuilder;
import com.netflix.ribbonclientextensions.RibbonRequest;

class HttpRequestBuilder<I, O> extends RequestBuilder<O> {

    private HttpRequestTemplate<I, O> requestTemplate;
    private HttpClient<I, O> client;
    
    HttpRequestBuilder(HttpClient<I, O> client, HttpRequestTemplate<I, O> requestTemplate) {
        this.requestTemplate = requestTemplate;
        this.client = client;
    }
    
    RibbonHystrixObservableCommand<I, O> createHystrixCommand() {
        return new RibbonHystrixObservableCommand<I, O>(client, requestTemplate, this);
    }

    @Override
    public RequestBuilder<O> withValue(
            String key, Object value) {
        return null;
    }

    @Override
    public RibbonRequest<O> build() {
        return new HttpRequest<I, O>(createHystrixCommand());
    }
    
    HttpClientRequest<I> createClientRequest() {
        return null;
    }
}
