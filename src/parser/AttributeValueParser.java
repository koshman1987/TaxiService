package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttributeValueParser {
    private Object value;
    private static final Pattern ID_PATTERN = Pattern.compile(" Id :(.)+");
    private static final Pattern COLOR_PATTERN = Pattern.compile("Color :(.)+");
    private static final String ID = "Id";
    private static final String COLOR = "Color";
    private static final Integer SIX = 6;
    private static final Integer EIGHT = 8;

    public Object getValue(String valueName, String stringValue) {
        switch (valueName) {
            case ID:
                Matcher idMatcher = ID_PATTERN.matcher(stringValue);

                while (idMatcher.find()) {
                    value = idMatcher.group().substring(SIX, EIGHT);
                }

                break;

            case COLOR:
                Matcher colorMatcher = COLOR_PATTERN.matcher(stringValue);

                while (colorMatcher.find()) {
                    value = colorMatcher.group().substring(EIGHT);
                }

                break;
        }

        return value;
    }
}