package parser;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

public class CarsFileReader {
    public List<String> parseCarList(final String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}