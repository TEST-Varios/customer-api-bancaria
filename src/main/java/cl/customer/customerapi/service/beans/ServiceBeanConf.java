package cl.customer.customerapi.service.beans;

import java.nio.charset.Charset;

import cl.customer.customerapi.config.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ServiceBeanConf {

    @Bean
    public RestTemplate obtenerRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add (0,new StringHttpMessageConverter(Charset.forName(Constants.UTF_UNICODE)));


        SimpleClientHttpRequestFactory clientHttpRequestFactory
                = new SimpleClientHttpRequestFactory();
        /***Connect timeout***/
        clientHttpRequestFactory.setConnectTimeout(10000);

        /***Read timeout***/
        clientHttpRequestFactory.setReadTimeout(10000);
        restTemplate.setRequestFactory(clientHttpRequestFactory);

        return restTemplate;
    }
}
