package org.gatling;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@Controller("/")
public class ApiController {

    public static final String FILE_NAME = "data.txt";

    @Get(uri = "/record/{data}")
    public void record(String data) {
        try (Writer dataFileWriter = new BufferedWriter(new FileWriter(FILE_NAME, true));) {
            dataFileWriter.append(data).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
