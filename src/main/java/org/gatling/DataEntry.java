package org.gatling;

import java.util.Objects;

public class DataEntry {

    private final String data;

    public DataEntry(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DataEntry dataEntry = (DataEntry) o;
        return Objects.equals(data, dataEntry.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
