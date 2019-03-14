package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttributeValueParser<T> {
    private static String idRegex = " Id :(.)+";
    private static String colorRegex = "Color :(.)+";
    private static String defaultMessage = "Value not found!";
    private static final Pattern ID_PATTERN = Pattern.compile(idRegex);
    private static final Pattern COLOR_PATTERN = Pattern.compile(colorRegex);
    private static Matcher matcher;
    static final String ID = "Id";
    static final String COLOR = "Color";
    private static final Integer ID_GROUP_INDEX = 6;
    private static final Integer COLOR_GROUP_INDEX = 8;

    public T getValue(String valueName, String stringValue) {
        switch (valueName) {
            case ID:
                matcher = ID_PATTERN.matcher(stringValue);
                if (matcher.find()) {
                    return (T) matcher.group().substring(ID_GROUP_INDEX, COLOR_GROUP_INDEX);
                }

            case COLOR:
                matcher = COLOR_PATTERN.matcher(stringValue);
                if (matcher.find()) {
                    return (T) matcher.group().substring(COLOR_GROUP_INDEX);
                }

            default:
                return (T) defaultMessage;
        }
    }
}