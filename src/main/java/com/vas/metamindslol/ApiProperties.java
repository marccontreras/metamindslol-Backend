package com.vas.metamindslol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("classpath:api.properties")
public class ApiProperties {
    @Value("${api.key}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}