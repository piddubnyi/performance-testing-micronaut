package org.gatling;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.RxHttpClient;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.codahale.metrics.MetricRegistry.name;

public class ApplicationPerformanceTest {

    private final BlockingHttpClient httpClient = RxHttpClient.create(URI.create("http://127.0.0.1:8080").toURL()).toBlocking();
    private final MetricRegistry registry = new MetricRegistry();
    private final Timer responses = registry.timer(name(ApplicationPerformanceTest.class, "request"));

    public ApplicationPerformanceTest() throws MalformedURLException {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS)
            .build();
        reporter.start(1, TimeUnit.SECONDS);
    }

    @Test
    void produceLoadForPerformanceTest() throws InterruptedException {
        final int threads = 3;
        final ExecutorService executorService = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < threads; i++) {
            executorService.submit(this::sendEndlessRequests);
        }
        Thread.sleep(TimeUnit.MINUTES.toMillis(10));
    }

    private void sendEndlessRequests() {
        while (!Thread.currentThread().isInterrupted()) {
            final Timer.Context context = responses.time();
            httpClient.exchange(HttpRequest.GET("/record/" + RandomStringUtils.randomAlphanumeric(10)));
            context.stop();
        }
    }
}
