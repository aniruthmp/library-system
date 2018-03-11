package io.pivotal.inventory.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
public class MongoConfiguration extends AbstractReactiveMongoConfiguration {

    @Autowired
    Environment environment;

    @Override
    public MongoClient reactiveMongoClient() {
        int port = environment.getProperty("spring.data.mongodb.port", Integer.class);
        String host = environment.getProperty("spring.data.mongodb.host", String.class);
        return MongoClients.create(String.format("mongodb://%s:%d", host, port));
    }

    @Override
    protected String getDatabaseName() {
        String database = environment.getProperty("spring.data.mongodb.database", String.class);
        return database;
    }
}
