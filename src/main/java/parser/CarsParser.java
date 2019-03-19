package parser;

import entity.Car;
import entity.Company;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static parser.AttributeValueParser.COLOR;
import static parser.AttributeValueParser.ID;

public class CarsParser {
    private static final Logger LOGGER = LogManager.getLogger(Company.class);
    private static final AttributeValueParser parser = new AttributeValueParser();
    private static final String CAR_REGEX = "\\d+";

    public List<Car> parseCars(final List<String> carList) {
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

                LOGGER.info(parser.getValue(ID, splitedText[i]));
                LOGGER.info(parser.getValue(COLOR, splitedText[i]).toString());
            }
        }

        return cars;
    }
}