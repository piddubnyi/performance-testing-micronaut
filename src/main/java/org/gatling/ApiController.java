package org.gatling;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClients;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.pojo.PojoCodecProvider;
import reactor.core.publisher.Mono;

@Controller("/")
public class ApiController {

    @Get(uri = "/record/{data}")
    public void record(String data) {
        Mono.from(
            MongoClients.create(
                MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString("mongodb://localhost:27017"))
                    .codecRegistry(
                        CodecRegistries.fromRegistries(
                            MongoClientSettings.getDefaultCodecRegistry(),
                            CodecRegistries.fromProviders(
                                PojoCodecProvider.builder().automatic(true).build()
                            )
                        )
                    ).build()
            ).getDatabase("testDB")
                .getCollection("documents", DataEntry.class)
                .insertOne(new DataEntry(data))
        ).block();
    }
}
