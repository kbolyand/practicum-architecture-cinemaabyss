package cinema.events.dto;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record Payment(int paymentId, int userId, double amount, String status, String timestamp, String action,
                      String method_type) {
}
