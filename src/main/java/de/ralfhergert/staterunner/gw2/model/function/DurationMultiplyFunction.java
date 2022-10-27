package de.ralfhergert.staterunner.gw2.model.function;

import de.ralfhergert.staterunner.gw2.model.StateRunner;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.Duration;

@XmlRootElement(name = "multiplyWith", namespace = StateRunner.NS)
@XmlAccessorType(XmlAccessType.FIELD)
public class DurationMultiplyFunction extends DurationFunction {

    @XmlAttribute
    private double factor = 1;

    public double getFactor() {
        return factor;
    }

    public DurationMultiplyFunction setFactor(double factor) {
        this.factor = factor;
        return this;
    }

    @Override
    public Duration apply(Duration duration) {
        return Duration.ofNanos((long)(duration.toNanos() * factor));
    }
}
