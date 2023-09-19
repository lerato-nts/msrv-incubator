package za.co.msrv.incubator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncRequestConfig {
    @Value("${threadpool.coreSize}")
    private int coreSize;
    @Value("${threadpool.maxSize}")
    private int maxSize;
    @Value("${threadpool.queueCapacity}")
    private int queueCapacity;

    @Bean("requestExecutor")
    public Executor asyncReqExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(coreSize);
        executor.setMaxPoolSize(maxSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("AsyncRequestThread-");
        executor.initialize();

        return executor;
    }
}
