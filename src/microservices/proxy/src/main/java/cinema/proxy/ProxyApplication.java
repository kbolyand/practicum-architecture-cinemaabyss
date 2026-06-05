package cinema.proxy;

import cinema.proxy.config.GatewayProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(GatewayProperties.class)
public class ProxyApplication {

	static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

}
