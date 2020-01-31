package org.gatling;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.inject.Inject;

@Controller("/")
public class ApiController {

    private final MongoCollection<DataEntry> collection;

    @Inject
    public ApiController(MongoClient client) {
        collection = client.getDatabase("testDB")
            .getCollection("documents", DataEntry.class);
    }


    @Get(uri = "/record/{data}")
    public void record(String data) {
        collection.insertOne(new DataEntry(data));
    }
}
