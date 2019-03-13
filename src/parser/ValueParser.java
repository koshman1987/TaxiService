package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueParser {
    private static ValueParser valueParser;
    private Object value;

    private ValueParser() {
    }

    public static ValueParser getInstance() {
        if (valueParser == null) {
            valueParser = new ValueParser();
        }

        return valueParser;
    }

    public Object getValue(String valueName, String stringValue) {
        switch (valueName) {
            case "Id":
                Pattern idPattern = Pattern.compile(" Id :(.)+");
                Matcher idMatcher = idPattern.matcher(stringValue);

                if (idMatcher.find()) {
                    value = idMatcher.group().substring(6, 8);
                }

                break;

            case "Color":
                Pattern colorPattern = Pattern.compile("Color :(.)+");
                Matcher colorMatcher = colorPattern.matcher(stringValue);

                if (colorMatcher.find()) {
                    value = colorMatcher.group().substring(8);
                }

                break;
        }

        return value;
    }
}