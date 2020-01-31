package org.gatling;

import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.Success;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Controller("/")
public class ApiController {

    @Get(uri = "/record/{data}")
    public void record(String data) {
        MongoClients.create("mongodb://localhost:27017")
            .getDatabase("testDB")
            .getCollection("documents", DataEntry.class)
            .insertOne(new DataEntry(data))
        .subscribe(new Subscriber<>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(Success success) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
