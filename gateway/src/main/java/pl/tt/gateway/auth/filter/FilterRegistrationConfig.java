package pl.tt.gateway.auth.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterRegistrationConfig {

    private final AuthenticationFilter authenticationFilter;

    @Bean
    public FilterRegistrationBean filterRegistrationConfig(){
        return new FilterRegistrationBean(authenticationFilter);
    }
    
}
