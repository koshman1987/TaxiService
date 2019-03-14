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
    private static List<Car> cars = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(Company.class.getName());
    private static final AttributeValueParser attributeValueParser = new AttributeValueParser();

    public List<Car> getCars(final List<String> carList) {
        Pattern pattern = Pattern.compile("\\d+");

        for (final String fileLine : carList) {
            String[] splitedText = fileLine.split(",");

            for (int i = 0; i < splitedText.length; i++) {
                Matcher matcher = pattern.matcher(splitedText[i]);

                while (matcher.find()) {
                    Car car = new Car(Integer.parseInt(matcher.group()));
                    cars.add(car);
                }

                LOGGER.log(Level.INFO, attributeValueParser.getValue("Id", splitedText[i]).toString());
                LOGGER.log(Level.INFO, attributeValueParser.getValue("Color", splitedText[i]).toString());
            }
        }

        return cars;
    }
}