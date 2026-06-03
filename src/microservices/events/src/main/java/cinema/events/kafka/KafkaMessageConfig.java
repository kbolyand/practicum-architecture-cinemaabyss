package cinema.events.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Consumer;

@Configuration
@Slf4j
public class KafkaMessageConfig {
    @Bean
    public Consumer<String> processMessage() {
        return message -> log.info("Received message: {}", message);
    }
}
