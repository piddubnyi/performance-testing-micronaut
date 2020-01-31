package org.gatling;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.gatling.ApiController.FILE_NAME;

@MicronautTest
public class ApplicationTest {

    @Inject
    RecordingClient client;

    @Test
    void testSingleCall() throws IOException {
        client.record("some data").blockingGet();
        Assertions.assertEquals(List.of("some data"), Files.readAllLines(Paths.get(FILE_NAME)));
    }

    @AfterEach
    void tearDown() {
        new File(FILE_NAME).delete();
    }
}
