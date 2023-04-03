package br.com.debut.pagamentos.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansFactory {
    @Bean
    public ModelMapper metodoQueCriaUmModelMapper(){
        return new ModelMapper();
    }
}
