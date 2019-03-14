package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttributeValueParser<T> {
    private T value;
    private static String idRegex = " Id :(.)+";
    private static String colorRegex = "Color :(.)+";
    private static final Pattern ID_PATTERN = Pattern.compile(idRegex);
    private static final Pattern COLOR_PATTERN = Pattern.compile(colorRegex);
    static final String ID = "Id";
    static final String COLOR = "Color";
    private static final Integer ID_GROUP_INDEX = 6;
    private static final Integer COLOR_GROUP_INDEX = 8;

    public T getValue(String valueName, String stringValue) {
        Matcher idMatcher = ID_PATTERN.matcher(stringValue);
        Matcher colorMatcher = COLOR_PATTERN.matcher(stringValue);

        switch (valueName) {
            case ID:
                if (idMatcher.find()) {
                    value = (T) idMatcher.group().substring(ID_GROUP_INDEX, COLOR_GROUP_INDEX);
                }

                break;

            case COLOR:
                if (colorMatcher.find()) {
                    value = (T) colorMatcher.group().substring(COLOR_GROUP_INDEX);
                }

                break;
        }

        return value;
    }
}