package com.authorizationservice.configs;

import net.spy.memcached.MemcachedClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;

@Configuration
public class SpringBeans {

    @Bean
    public MemcachedClient getMemcachedClient() throws IOException {

        String endPoint = System.getenv("SESSION_CACHE_ENDPOINT");
        int port = Integer.parseInt(System.getenv("SESSION_CACHE_PORT"));

        return new MemcachedClient(new InetSocketAddress(endPoint, port));

    }
}
