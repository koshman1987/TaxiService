package parser;

import static parser.AttributeValueParser.*;

import entity.Car;
import entity.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarsParser {
    private static final Logger LOGGER = Logger.getLogger(Company.class.getName());
    private static final AttributeValueParser parser = new AttributeValueParser();
    private static final String CAR_REGEX = "\\d+";

    public List<Car> getCars(final List<String> carList) {
        final List<Car> cars = new ArrayList<>();
        final Pattern pattern = Pattern.compile(CAR_REGEX);

        for (final String fileLine : carList) {
            final String[] splitedText = fileLine.split(",");

            for (int i = 0; i < splitedText.length; i++) {
                final Matcher matcher = pattern.matcher(splitedText[i]);

                while (matcher.find()) {
                    final Car car = new Car(Integer.parseInt(matcher.group()));
                    cars.add(car);
                }

                LOGGER.log(Level.INFO, String.valueOf(parser.getValue(ID, splitedText[i])));
                LOGGER.log(Level.INFO, parser.getValue(COLOR, splitedText[i]).toString());
            }
        }

        return cars;
    }
}