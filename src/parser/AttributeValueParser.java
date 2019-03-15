package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttributeValueParser<T> {
    private static final String ID_REGEX = " Id :(.)+";
    private static final String COLOR_REGEX = "Color :(.)+";
    private static final String DEFAULT_MESSAGE = "Value not found!";
    private static final Pattern ID_PATTERN = Pattern.compile(ID_REGEX);
    private static final Pattern COLOR_PATTERN = Pattern.compile(COLOR_REGEX);
    static final String ID = "Id";
    static final String COLOR = "Color";
    private static final Integer ID_GROUP_INDEX = 6;
    private static final Integer COLOR_GROUP_INDEX = 8;

    public T getValue(final String valueName, final String stringValue) {
        Matcher matcher;

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
                return (T) DEFAULT_MESSAGE;
        }
    }
}