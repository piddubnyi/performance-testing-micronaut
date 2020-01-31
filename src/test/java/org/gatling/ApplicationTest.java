package org.gatling;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.IOException;

@MicronautTest
public class ApplicationTest {

    @Inject
    RecordingClient client;

    @Test
    void testSingleCall() throws IOException {
        client.record("some data").blockingGet();
    }
}
