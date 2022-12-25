package inno.tech.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "threading")
@Getter
@Setter
public class ThreadPoolProperties {
    public static final String WORKER_NAME = "callApiWorker";
    private int max;
    private int taskCap;
}
