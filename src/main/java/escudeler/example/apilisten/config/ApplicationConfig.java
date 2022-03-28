package escudeler.example.apilisten.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class ApplicationConfig {

    @Value("${http.speakAddress}")
    private String httpSpeakAddress;

}
