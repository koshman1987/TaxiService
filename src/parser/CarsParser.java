package parser;

import entity.Car;
import entity.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarsParser {
    private static CarsParser carsParser;
    private static List<Car> cars = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(Company.class.getName());

    private CarsParser() {
    }

    public static CarsParser getInstance() {
        if (carsParser == null) {
            carsParser = new CarsParser();
        }

        return carsParser;
    }

    public static List<Car> getCars(List<String> carList) {
        Pattern pattern = Pattern.compile("\\d+");

        for (String fileLine : carList) {
            String[] splitedText = fileLine.split(",");

            for (int i = 0; i < splitedText.length; i++) {
                Matcher matcher = pattern.matcher(splitedText[i]);
                matcher.find();
                Car car = new Car(Integer.parseInt(matcher.group()));
                cars.add(car);
                LOGGER.log(Level.INFO, ValueParser.getInstance().getValue("Id", splitedText[i]).toString());
                LOGGER.log(Level.INFO, ValueParser.getInstance().getValue("Color", splitedText[i]).toString());
            }
        }

        return cars;
    }
}