package de.ralfhergert.staterunner.gw2.model.modifier;

import de.ralfhergert.staterunner.gw2.model.Action;
import de.ralfhergert.staterunner.gw2.model.transaction.MinimumCoolDownTransaction;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.time.Duration;

public class CooldownModifierTest {

    @Test
    public void testUnmarshalling() throws JAXBException {
        final String xml =
            "<cooldown-modifier " +
            "    xmlns=\"http://ralfhergert.de/staterunner/gw2\" " +
            "    description=\"all fire weapon skills have 20% reduced cooldown\">" +
            "    <predicate><and><isAction/><inGroup group=\"fire skills\"/></and></predicate>" +
            "    <multiplyWith factor=\"0.8\"/>" +
            "</cooldown-modifier>";

        Unmarshaller unmarshaller = JAXBContext.newInstance(CooldownModifier.class).createUnmarshaller();

        CooldownModifier modifier = (CooldownModifier)unmarshaller.unmarshal(new StringReader(xml));

        Assertions.assertNotNull(modifier, "modifier should not be null");

        final Action fireAction = new Action().addGroup("fire skills");
        Assertions.assertEquals(Duration.ZERO, fireAction.getCoolDownRemaining(), "remaining coolDown on action");

        final MinimumCoolDownTransaction t = modifier.handle(new MinimumCoolDownTransaction()
            .setTarget(fireAction)
            .setDuration(Duration.ofSeconds(6)));

        Assertions.assertEquals(Duration.ofMillis(4800), t.getDuration(), "duration on transaction");

        t.perform();

        Assertions.assertEquals(Duration.ofMillis(4800), fireAction.getCoolDownRemaining(), "remaining coolDown on action");
    }
}
