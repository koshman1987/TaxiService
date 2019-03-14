package parser;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarsFileReader {
    private List<String> cars = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(CarsFileReader.class.getName());

    public List<String> parseCarList(final String filePath) {
        try {
            cars = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }

        return cars;
    }
}