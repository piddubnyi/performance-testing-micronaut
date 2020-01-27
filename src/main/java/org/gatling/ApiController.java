package org.gatling;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@Controller("/")
public class ApiController {

    @Get(uri = "/record/{data}")
    public void hello(String data) {
        try (Writer dataFileWriter = new BufferedWriter(new FileWriter("data.txt", true));) {
            dataFileWriter.append(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
