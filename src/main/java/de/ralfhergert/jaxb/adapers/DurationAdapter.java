package de.ralfhergert.jaxb.adapers;

import jakarta.xml.bind.UnmarshalException;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DurationAdapter extends XmlAdapter<String,Duration> {

    private final Pattern pattern = Pattern.compile("(-?\\d+)([ms]+)");

    @Override
    public Duration unmarshal(String v) throws Exception {
        Matcher matcher = pattern.matcher(v);
        if (matcher.find()) {
            final Long number = Long.valueOf(matcher.group(1));
            final String unit = matcher.group(2);
            switch (matcher.group(2)) {
                case "ms": return Duration.ofMillis(number);
                case "s": return Duration.ofSeconds(number);
                case "m": return Duration.ofMinutes(number);
                default: throw new UnmarshalException("unknown timeunit '" + matcher.group(2) + "', use 'ms', 's' or 'm' instead.");
            }
        }
        throw new UnmarshalException("could not parse given duration '" + v + "'.");
    }

    @Override
    public String marshal(Duration v) throws Exception {
        return v.toString();
    }
}
