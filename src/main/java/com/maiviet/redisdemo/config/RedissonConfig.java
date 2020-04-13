package com.maiviet.redisdemo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.FstCodec;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableCaching
public class RedissonConfig extends CachingConfigurerSupport {

    @Value("${redisson.address}")
    private String redisAddress;

    @Value("${redisson.idleConnectionTimeout}")
    private Integer idleConnectionTimeout;

    @Value("${redisson.connectTimeout}")
    private Integer connectionTimeout;

    @Value("${redisson.timeout}")
    private Integer timeout;

    @Value("${redisson.retryAttempts}")
    private Integer retryAttempts;

    @Value("${redisson.retryInterval}")
    private Integer retryInterval;

    @Value("${redisson.password}")
    private String password;

    @Value("${redisson.subscriptionsPerConnection}")
    private Integer subcriptionsPerConnection;

    @Value("${redisson.clientName}")
    private String clientName;

    @Value("${redisson.subscriptionConnectionMinimumIdleSize}")
    private Integer subscriptionConnectionMinimumIdleSize;

    @Value("${redisson.subscriptionConnectionPoolSize}")
    private Integer subscriptionConnectionPoolSize;

    @Value("${redisson.connectionMinimumIdleSize}")
    private Integer connectionMinimumIdleSize;

    @Value("${redisson.connectionPoolSize}")
    private Integer connectionPoolSize;

    @Value("${redisson.database}")
    private Integer database;

    @Value("${redisson.dnsMonitoringInterval}")
    private Integer dnsMonitoringIntegerval;

    @Value("${redisson.threads}")
    private String threads;

    @Value("${redisson.codec}")
    private String codec;

    @Value("${redisson.eventLoopGroup}")
    private String eventLoopGroup;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(redisAddress)
                .setIdleConnectionTimeout(idleConnectionTimeout)
                .setConnectTimeout(connectionTimeout)
                .setTimeout(timeout)
                .setRetryAttempts(retryAttempts)
                .setRetryInterval(retryInterval)
                .setSubscriptionsPerConnection(subcriptionsPerConnection)
                .setClientName(clientName)
                .setSubscriptionConnectionMinimumIdleSize(subscriptionConnectionMinimumIdleSize)
                .setSubscriptionConnectionPoolSize(subscriptionConnectionPoolSize)
                .setConnectionMinimumIdleSize(connectionMinimumIdleSize)
                .setConnectionPoolSize(connectionPoolSize)
                .setDatabase(database)
                .setDnsMonitoringInterval(dnsMonitoringIntegerval)
        ;

        config.setCodec(new FstCodec());

        return Redisson.create(config);
    }

    @Bean
    CacheManager cacheManager(RedissonClient redissonClient) {
        Map<String, CacheConfig> cacheConfig = new HashMap<>();
        // create "testMap" spring cache with ttl = 24 minutes and maxIdleTime = 12 minutes
        cacheConfig.put("testMap", new CacheConfig(24*60*1000, 12*60*1000));
        return new RedissonSpringCacheManager(redissonClient, cacheConfig);
    }


}
