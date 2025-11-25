package ar.com.gha.heroes.infrastructure.out.event;

import ar.com.gha.heroes.domain.event.HeroCreatedEvent;
import ar.com.gha.heroes.domain.port.HeroEventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@AllArgsConstructor
@Slf4j
public class KafkaHeroEventPublisher implements HeroEventPublisher {

    private final StreamBridge streamBridge;
    private final ObjectMapper objectMapper;
    private static final String BINDING_NAME = "heroes-out-0";

    @Override
    public void publishHeroCreated(HeroCreatedEvent event) {

        try {
            String payload = objectMapper.writeValueAsString(event);
            var enviado = this.streamBridge.send(BINDING_NAME, payload);

            if(!enviado){
                log.error("Error publicando evento en {}", BINDING_NAME);
                throw new RuntimeException("Error publicando evento");
            }

        } catch (JsonProcessingException e) {
            log.error("Error serializando evento", e);
            throw new RuntimeException("Error serializando evento", e);
        }
    }
}
