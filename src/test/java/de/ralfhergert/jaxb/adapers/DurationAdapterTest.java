package de.ralfhergert.jaxb.adapers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

/**
 * Ensures the {@link DurationAdapter} is working correctly.
 */
public class DurationAdapterTest {

    @Test
    public void testParsing6s() throws Exception {
        Assertions.assertEquals(Duration.ofSeconds(16), new DurationAdapter().unmarshal("16s"));
    }

    @Test
    public void testParsingNeg6s() throws Exception {
        Assertions.assertEquals(Duration.ofSeconds(-6), new DurationAdapter().unmarshal("-6s"));
    }
}
