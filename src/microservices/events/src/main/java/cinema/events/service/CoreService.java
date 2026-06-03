package cinema.events.service;

import cinema.events.dto.Movie;
import cinema.events.dto.Payment;
import cinema.events.dto.User;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;


@Service
@AllArgsConstructor
public class CoreService {
    private ObjectMapper mapper;
    private StreamBridge streamBridge;

    public void processEvent(Object o) {
        String bindingName = "";
        if (o instanceof Movie) {
            bindingName = "messageSupplier-out-0";
        } else if (o instanceof User) {
            bindingName = "messageSupplier-out-1";
        } else if (o instanceof Payment) {
            bindingName = "messageSupplier-out-2";
        }
        String json = mapper.writeValueAsString(o);
        streamBridge.send(bindingName, json);
    }
}
