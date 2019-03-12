package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarsFileReader {
    private static CarsFileReader carsFileReader;
    private static final Logger LOGGER = Logger.getLogger(CarsFileReader.class.getName());

    private CarsFileReader() {
    }

    public static CarsFileReader getInstance() {
        if (carsFileReader == null) {
            carsFileReader = new CarsFileReader();
        }

        return carsFileReader;
    }

    public static List<String> parseCarList(String filePath) {
        List<String> carList = new ArrayList<>();

        try {
            carList = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }

        return carList;
    }
}