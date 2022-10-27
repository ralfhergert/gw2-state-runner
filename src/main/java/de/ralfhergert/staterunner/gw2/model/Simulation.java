package de.ralfhergert.staterunner.gw2.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "simulation", namespace = StateRunner.NS)
@XmlAccessorType(XmlAccessType.FIELD)
public class Simulation {

    @XmlElement(name = "build", namespace = StateRunner.NS)
    private Build build;

    public Build getBuild() {
        return build;
    }

    public void setBuild(Build build) {
        this.build = build;
    }
}
