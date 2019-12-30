package br.com.napalm23zero.GameStore.config;

import java.util.Arrays;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.napalm23zero.gamestore.*.domain.repository")
@ComponentScan("com.napalm23zero.gamestore.*.domain.model")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.username}")
    private String username;
    @Value("${spring.data.mongodb.password}")
    private String password;
    @Value("${spring.data.mongodb.database}")
    private String database;
    @Value("${spring.data.mongodb.host}")
    private String host;
    @Value("${spring.data.mongodb.port}")
    private Integer port;

    @Override
    public MongoClient mongoClient() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(MongoCredential.createCredential(username, database, password.toCharArray()))
                .applyToClusterSettings(builder -> builder.hosts(Arrays.asList(new ServerAddress(host, port))))
                .applicationName("gamestore").build();
        return MongoClients.create(settings);
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }
}