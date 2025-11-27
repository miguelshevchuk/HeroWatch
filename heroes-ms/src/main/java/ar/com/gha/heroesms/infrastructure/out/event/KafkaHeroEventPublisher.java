package ar.com.gha.heroesms.infrastructure.out.event;

import ar.com.gha.heroesms.domain.event.HeroEvent;
import ar.com.gha.heroesms.domain.port.HeroEventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class KafkaHeroEventPublisher implements HeroEventPublisher {

    private final StreamBridge streamBridge;
    private final ObjectMapper objectMapper;
    private static final String BINDING_NAME = "heroes-out-0";

    @Override
    public void publishHeroCreated(HeroEvent event) {

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

    @Override
    public void publishHeroUpdated(HeroEvent event) {
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
