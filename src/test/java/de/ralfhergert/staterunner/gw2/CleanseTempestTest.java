package de.ralfhergert.staterunner.gw2;

import de.ralfhergert.staterunner.gw2.model.Action;
import de.ralfhergert.staterunner.gw2.model.Simulation;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class CleanseTempestTest {

    @Test
    public void testLoadingModelFile() throws JAXBException {
        Unmarshaller unmarshaller = JAXBContext.newInstance(Simulation.class).createUnmarshaller();

        Simulation simulation = (Simulation)unmarshaller.unmarshal(getClass().getResourceAsStream("/cleanse-tempest.xml"));

        Assertions.assertNotNull(simulation, "simulation should not be null");
        Assertions.assertNotNull(simulation.getBuild(), "simulation should have a build");
        Assertions.assertTrue(simulation.getBuild().getActions().findAny().isPresent(), "build should have actions");

        simulation.getBuild().triggerAction("Water Attunement");

        // check the cooldowns and availability on the profession mechanic skills
        Action fa = simulation.getBuild().findAction("Fire Attunement").get();
        // TODO check if 10s is correct here
        Assertions.assertEquals(Duration.ofSeconds(10), fa.getCoolDownRemaining(), "cooldown on fire attunement");
        Assertions.assertTrue(fa.isVisible(), "fire attunement should be visibile");
        Assertions.assertTrue(fa.isAvailable(), "fire attunement should be available");
        Action aa = simulation.getBuild().findAction("Air Attunement").get();
        Assertions.assertEquals(Duration.ofSeconds(2), aa.getCoolDownRemaining(), "cooldown on air attunement");
        Assertions.assertTrue(aa.isVisible(), "air attunement should be visibile");
        Assertions.assertTrue(aa.isAvailable(), "air attunement should be available");
        Action ea = simulation.getBuild().findAction("Earth Attunement").get();
        Assertions.assertEquals(Duration.ofSeconds(2), ea.getCoolDownRemaining(), "cooldown on earth attunement");
        Assertions.assertTrue(ea.isVisible(), "earth attunement should be visibile");
        Assertions.assertTrue(ea.isAvailable(), "earth attunement should be available");

    }
}
