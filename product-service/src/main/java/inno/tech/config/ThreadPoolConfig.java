package inno.tech.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ThreadPoolProperties.class)
public class ThreadPoolConfig {

    private final ThreadPoolProperties properties;

    @Bean
    public Scheduler scheduler() {
        return Schedulers.newBoundedElastic(properties.getMax(),
                properties.getTaskCap(),
                ThreadPoolProperties.WORKER_NAME);
    }
}
