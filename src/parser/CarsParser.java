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
    private static List<Car> cars = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(Company.class.getName());
    private static final AttributeValueParser<Integer> idParser = new AttributeValueParser();
    private static final AttributeValueParser<String> colorParser = new AttributeValueParser();
    private static final String CAR_REGEX = "\\d+";

    public List<Car> getCars(final List<String> carList) {
        final Pattern pattern = Pattern.compile(CAR_REGEX);

        for (final String fileLine : carList) {
            final String[] splitedText = fileLine.split(",");

            for (int i = 0; i < splitedText.length; i++) {
                final Matcher matcher = pattern.matcher(splitedText[i]);

                while (matcher.find()) {
                    Car car = new Car(Integer.parseInt(matcher.group()));
                    cars.add(car);
                }

                LOGGER.log(Level.INFO, String.valueOf(idParser.getValue(ID, splitedText[i])));
                LOGGER.log(Level.INFO, colorParser.getValue(COLOR, splitedText[i]));
            }
        }

        return cars;
    }
}