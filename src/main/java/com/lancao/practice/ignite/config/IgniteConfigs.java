package com.lancao.practice.ignite.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableScheduling
@Configuration
public class IgniteConfigs {
    @Bean(name = "igniteClient")
    public Ignite igniteClient() {
        Ignite ignite = Ignition.start("C:\\project\\tools\\apache-ignite-2.16.0-bin\\examples\\config\\example-ignite.xml");
        ignite.cluster().state(ClusterState.ACTIVE);
        return ignite;
    }

    @Bean(name = "igniteExecutor")
    public Executor igniteExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(1000);
        executor.setThreadNamePrefix("igniteExecutor-");
        executor.initialize();
        return executor;
    }

    @Bean(name = "unsafeIgniteExecutor")
    public Executor unsafeIgniteExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(1000);
        executor.setThreadNamePrefix("unsafeIgniteExecutor-");
        executor.initialize();
        return executor;
    }
}
