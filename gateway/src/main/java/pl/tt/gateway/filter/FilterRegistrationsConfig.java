package pl.tt.gateway.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tt.gateway.auth.feign.AuthFeignClient;

@Configuration
@RequiredArgsConstructor
public class FilterRegistrationsConfig {

//    private final AuthenticationFilter authenticationFilter;
//
//    private final AuthFeignClient authFeignClient;
//
//    @Bean
//    public FilterRegistrationBean<AuthenticationFilter> filterRegistrationConfig(){
//        FilterRegistrationBean<AuthenticationFilter> filterRegistrationBean = new FilterRegistrationBean<>();
//        filterRegistrationBean.setFilter(new AuthenticationFilter(authFeignClient));
//        return filterRegistrationBean;
//    }


    
}
