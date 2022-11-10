package cn.yulam;

import io.debezium.config.Configuration;
import io.debezium.engine.ChangeEvent;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.format.Json;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        Configuration config = io.debezium.config.Configuration.create().build();
// Define the configuration for the Debezium Engine with MySQL connector...
        final Properties props = config.asProperties();
        props.setProperty("name", "engine");
        props.setProperty("connector.class", "io.debezium.connector.mysql.MySqlConnector");
        props.setProperty("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore");
        props.setProperty("offset.storage.file.filename", "/tmp/offsets.dat");
        props.setProperty("offset.flush.interval.ms", "60000");
        /* begin connector properties */
        props.setProperty("database.hostname", "localhost");
        props.setProperty("database.port", "3306");
        props.setProperty("database.user", "root");
        props.setProperty("database.password", "123456");
        props.setProperty("database.server.id", "85744");
        props.setProperty("database.server.name", "my-app-connector");
        props.setProperty("database.history", "io.debezium.relational.history.FileDatabaseHistory");
        props.setProperty("database.history.file.filename", "/path/to/storage/dbhistory.dat");
//        props.setProperty()

// Create the engine with this configuration ...
        try (DebeziumEngine<ChangeEvent<String, String>> engine = DebeziumEngine.create(Json.class)
                .using(props)
                .notifying((records, committer) -> {
                    for (ChangeEvent<String, String> r : records) {
                        System.out.println("Key = '" + r.key() + "' value = '" + r.value() + "'");
                        committer.markProcessed(r);
                    }
                }).build()
        ) {
            // Run the engine asynchronously ...
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(engine);

            // Do something else or wait for a signal or an event
        }
// Engine is stopped when the main code is finished


    }
}