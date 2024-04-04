package br.com.mobiauto.config;

import br.com.mobiauto.domain.model.OportunidadeModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.Queue;

@Configuration
public class QueueConfig {

  @Bean
  public Queue<OportunidadeModel> filaDeOportunidades() {
    return new LinkedList<>();
  }
}
