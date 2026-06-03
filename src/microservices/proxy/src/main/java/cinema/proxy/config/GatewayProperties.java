package cinema.proxy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "gateway")
@Data
public class GatewayProperties {
    private boolean gradualMigration;
    private int moviesMigrationPercent;
    private String moviesServiceUrl;
    private String monolithUrl;
}