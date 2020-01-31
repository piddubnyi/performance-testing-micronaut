package org.gatling;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

import javax.validation.constraints.NotBlank;

@Client("/")
public interface RecordingClient {

    @Get("/record/{data}")
    Single<Void> record(@NotBlank String data);
}
