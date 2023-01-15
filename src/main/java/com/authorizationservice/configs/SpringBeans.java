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
        String endPoint = "sessions-cache.4fhyap.cfg.aps1.cache.amazonaws.com";
        int port = 11211;
        return new MemcachedClient(new InetSocketAddress(endPoint, port));
    }
}
