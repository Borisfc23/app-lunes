package com.synopsis.capacitacion.customer.webclient.impl;
import com.fasterxml.jackson.databind.JsonNode;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import com.synopsis.capacitacion.customer.webclient.IWebClientCustomer;

public class WebClientCustomer implements IWebClientCustomer {
    
    @Autowired
    private Environment env;
    @Autowired
    private WebClient.Builder webClientBuilder;

    HttpClient client = HttpClient.create()
    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000)
    .option(ChannelOption.SO_KEEPALIVE, true)
    .option(EpollChannelOption.TCP_KEEPIDLE, 300)
    .option(EpollChannelOption.TCP_KEEPINTVL,60)
    .responseTimeout(Duration.ofSeconds(1))
    .doOnConnected(connection -> {
        connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
        connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
    });
    
    @Override
    public String getProductName (long id){

        WebClient build = webClientBuilder.clientConnector(new ReactorClientHttpConnector(client))
                .baseUrl(env.getProperty("endpoint.ms-product.base-path"))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url",env.getProperty("endpoint.ms-product.base-path")))
                .build();

        JsonNode block = build.method(HttpMethod.GET).uri(env.getProperty("endpoint.ms-product.path")+ id)
                .retrieve().bodyToMono(JsonNode.class).block();       

        String name = block.get("name").asText();
        return name;
    }

    @Override
    public JsonNode getListTransaction(String accountNumber) {
        WebClient build = webClientBuilder.clientConnector(new ReactorClientHttpConnector(client))
                .baseUrl(env.getProperty("endpoint.ms-transaction.base-path"))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url",env.getProperty("endpoint.ms-transaction.base-path")))
                .build();

        JsonNode block = build.method(HttpMethod.GET).uri(env.getProperty("endpoint.ms-transaction.path")+ accountNumber)
                .retrieve().bodyToMono(JsonNode.class).block();

        return block;
    }
}
