package ar.com.gha.auditms.infrastructure.input.listener;

import ar.com.gha.auditms.infrastructure.output.persistence.adapter.repository.AuditCRUDRepository;
import ar.com.gha.auditms.infrastructure.output.persistence.entity.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@AllArgsConstructor
@Configuration
@Slf4j
public class HeroeListener {

    private final AuditCRUDRepository auditCRUDRepository;

    @Bean
    public Consumer<String> heroes(){

        return event -> {
            log.info("Guardando event: {}", event);
            this.auditCRUDRepository.save(
                    AuditEntity.builder()
                            .evento("Eventooo")
                            .mensaje(event)
                            .build()
            );
            log.info("Saved event: {}", event);
        };
    }

}
