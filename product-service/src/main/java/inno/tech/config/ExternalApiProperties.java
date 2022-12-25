package inno.tech.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@RequiredArgsConstructor
@ConfigurationProperties("api")
public class ExternalApiProperties {

    private AdidasApi adidas;

    private ReviewApi review;

    @Getter
    @Setter
    public static class AdidasApi {
        private String host;
        private String basePath;
    }

    @Getter
    @Setter
    public static class ReviewApi {
        private String host;
        private String reportPath = "/products/%s/reviews/report";
    }
}
