package com.example.xmlparse.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.restlet.Component;
import org.restlet.ext.spring.SpringServerServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.xmlparse.repository.mongo")
@EnableJpaRepositories(basePackages = "com.example.xmlparse.repository.jpa")
public class ApplicationConfigurationFile {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {

        SpringServerServlet serverServlet = new SpringServerServlet();
        ServletRegistrationBean regBean = new ServletRegistrationBean(serverServlet, "/*");

        Map<String, String> params = new HashMap<>();
        params.put("org.restlet.component", "restletComponent");
        regBean.setInitParameters(params);

        return regBean;
    }

    @Bean
    public Component restletComponent() {
        return new Component();
    }

    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/json_parse");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "json_parse");
    }
}
